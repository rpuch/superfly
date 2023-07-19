package com.payneteasy.superfly.web.security.logout;

import com.payneteasy.superfly.service.LoggerSink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SuperflyLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
  private static final Logger logger = LoggerFactory.getLogger(SuperflyLogoutSuccessHandler.class);
  private LoggerSink loggerSink;

  /**
   * @param targetUrl the target URL
   */
  public SuperflyLogoutSuccessHandler(String targetUrl) {
    super();
    setDefaultTargetUrl(targetUrl);
  }

  @Override
  public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException, ServletException {
    if (authentication != null) {
      loggerSink.info(logger, "LOCAL_LOGOUT", true, authentication.getName());
    }
    super.onLogoutSuccess(request, response, authentication);
  }

  public void setLoggerSink(LoggerSink loggerSink) {
    this.loggerSink = loggerSink;
  }
}
