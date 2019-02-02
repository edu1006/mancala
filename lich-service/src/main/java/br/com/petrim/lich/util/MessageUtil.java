package br.com.petrim.lich.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageUtil {

    @Autowired
    private MessageUtil(ResourceBundleMessageSource messageSource) {
        MessageResourceUtil.messageSource = messageSource;
    }

    public static String getMessage(String msg, Object...params) {
        Locale locale = LocaleContextHolder.getLocale();
        return MessageResourceUtil.messageSource.getMessage(msg, params, locale);
    }

}
