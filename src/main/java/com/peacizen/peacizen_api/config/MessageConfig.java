package com.peacizen.peacizen_api.config;

import com.peacizen.peacizen_api.util.MessageUtil;
import org.springframework.boot.web.servlet.server.Encoding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

@Configuration
public class MessageConfig {

    @Bean
    public LocaleResolver localeResolver(){
        AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
        localeResolver.setDefaultLocale(Locale.KOREAN);
        return localeResolver;
    }

    @Bean
    public ReloadableResourceBundleMessageSource messageSource(){
        Locale.setDefault(Locale.KOREAN);
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/messages/messages");
        messageSource.setDefaultEncoding(Encoding.DEFAULT_CHARSET.toString());
        messageSource.setDefaultLocale(Locale.getDefault());
        messageSource.setCacheSeconds(600);

        return messageSource;
    }

    @Bean
    public MessageSourceAccessor messageSourceAccessor(){
        return new MessageSourceAccessor(messageSource());
    }

    @Bean
    public void messageUtil(){
        MessageUtil.setMessageSourceAccessor(this.messageSourceAccessor());
    }
}
