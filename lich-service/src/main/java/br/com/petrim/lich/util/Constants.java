package br.com.petrim.lich.util;

public interface Constants {

    String TIME = "time";

    String USER = "user";

    String PROCESS_DATE_TODAY = "processDateToday";

    String JOB_PROCESS = "idJobProcess";

    String JOB_PARENT = "idJobParent";

    String INNER_JOB_PROCESS = "idInnerJobProcess";

    String JOB_PROTOCOL = "jobProtocol";

    String PREFIX_INNER_JOB = "lich_ij_";

    String STEP_EXECUTION = "idStepExecution";

    String WARNING_STATUS = "WARNING";

    Long STEP_SCRIPT_TIME_WAIT = 5000L; // FIXME: transform to property on properties file.

}
