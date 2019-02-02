package br.com.petrim.lich.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextUtil {

    @Autowired
    public SpringContextUtil(ApplicationContext applicationContext) {
        ApplicationContextUtil.context = applicationContext;
    }

    public static Object getBean(String beanName) {
        return ApplicationContextUtil.context.getBean(beanName);
    }

    public static <T extends Object> T getBean(Class<T> beanClass) {
        return ApplicationContextUtil.context.getBean(beanClass);
    }
}
