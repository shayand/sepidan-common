package net.sepidan.common.util;

import java.util.Locale;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class MessageSourceUtils implements ApplicationContextAware {

  private static MessageSource messageSource;

  public static String getMessage(String key) {
    return messageSource.getMessage(key, null, Locale.forLanguageTag("fa"));
  }

  public static String getMessage(String key, String locale) {
    return messageSource.getMessage(key, null, Locale.forLanguageTag(locale));
  }

  public static String getMessage(String key, Locale locale) {
    return messageSource.getMessage(key, null, locale);
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) {
    messageSource = applicationContext.getBean(MessageSource.class);
  }
}
