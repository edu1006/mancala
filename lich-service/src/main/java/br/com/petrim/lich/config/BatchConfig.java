package br.com.petrim.lich.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Bean("batchAsyncExecutor")
    public TaskExecutor batchAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(2); //FIXME: Informar a partir de arquivo de propriedade.
        executor.setMaxPoolSize(5); //FIXME: Informar a partir de arquivo de propriedade.

        executor.setWaitForTasksToCompleteOnShutdown(Boolean.TRUE);
        executor.setThreadNamePrefix("Lich_BA_Async-");

        return executor;
    }

    @Bean("batchJobLauncher")
    public JobLauncher batchJobLauncher(ApplicationContext applicationContext) throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();

        jobLauncher.setJobRepository(applicationContext.getBean(JobRepository.class));
        jobLauncher.setTaskExecutor((TaskExecutor) applicationContext.getBean("batchAsyncExecutor"));
        jobLauncher.afterPropertiesSet();

        return jobLauncher;
    }

}
