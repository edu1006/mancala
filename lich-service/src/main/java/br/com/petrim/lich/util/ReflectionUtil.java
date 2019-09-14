package br.com.petrim.lich.util;

import br.com.petrim.lich.exception.GeneralException;
import br.com.petrim.lich.model.StepProcess;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectionUtil {

    public static Object newInstanceOfTasklet(String className, StepProcess stepProcess) {
        try {

            Class<?> clazz = Thread
                    .currentThread().getContextClassLoader()
                    .loadClass(className);

            Constructor<?> constructor = clazz.getConstructor(StepProcess.class);
            return constructor.newInstance(stepProcess);

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            throw new GeneralException("Class not found: " + className, e);
        }

    }

}
