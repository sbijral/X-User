package com.yolo.XUser.config;

import feign.Logger;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by lukman.h on 4/21/2017.
 */
@ConfigurationProperties("feign.auth")
@Component
public class FeignAuthProperties {

  private Long connectTimeout = 2000L;

  private TimeUnit timeUnit = TimeUnit.MILLISECONDS;

  private Long readTimeout = 2000L;

  private Logger.Level level = Logger.Level.BASIC;

  public Logger.Level getLevel() {
    return level;
  }

  public void setLevel(Logger.Level level) {
    this.level = level;
  }

  public Long getConnectTimeout() {
    return connectTimeout;
  }

  public void setConnectTimeout(Long connectTimeout) {
    this.connectTimeout = connectTimeout;
  }

  public TimeUnit getTimeUnit() {
    return timeUnit;
  }

  public void setTimeUnit(TimeUnit timeUnit) {
    this.timeUnit = timeUnit;
  }

  public Long getReadTimeout() {
    return readTimeout;
  }

  public void setReadTimeout(Long readTimeout) {
    this.readTimeout = readTimeout;
  }

}
