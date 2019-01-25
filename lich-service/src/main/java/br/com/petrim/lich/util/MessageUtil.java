package br.com.petrim.lich.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageUtil {

    private static ResourceBundleMessageSource messageSource;

    @Autowired
    public MessageUtil(ResourceBundleMessageSource messageSource) {
        MessageUtil.messageSource = messageSource;
    }

    public static String getMessage(String msg, Object...params) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(msg, params, locale);
    }

}
