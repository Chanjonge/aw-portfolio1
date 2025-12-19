package io.awportfoiioapi.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.awportfoiioapi.advice.exception.ValidationException;
import io.awportfoiioapi.advice.response.ErrorMessageResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component("portfolioAuthenticationFailureHandler")
@RequiredArgsConstructor
public class PortfolioAuthenticationFailureHandler implements AuthenticationFailureHandler {
    
    private final ObjectMapper mapper;
    
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        
        // validation 에러
      if (exception.getCause() instanceof ValidationException ve) {
          response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
          mapper.writeValue(response.getWriter(), ve.getErrorResponse());
          return;
      }
  
      // 인증 실패 (아이디/비번 틀림)
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      mapper.writeValue(
          response.getWriter(),
          ErrorMessageResponse.messageError(
              "401",
              exception.getMessage()
          )
      );
    }
}
