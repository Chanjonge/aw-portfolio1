package io.awportfoiioapi.login;

import io.awportfoiioapi.ControllerTestSupport;
import io.awportfoiioapi.security.filter.request.PortfolioAuthenticationRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

public class LoginUserTest extends ControllerTestSupport {

    
    @DisplayName("유저 로그인")
    @Test
    void test1() throws Exception {
        PortfolioAuthenticationRequest request = new PortfolioAuthenticationRequest("test", "1234");
          
          String json = objectMapper.writeValueAsString(request);
          mockMvc.perform(post("/api/user-login")
                  .contentType(MediaType.APPLICATION_JSON)
                  .characterEncoding(StandardCharsets.UTF_8.name())
                  .content(json)
                  .accept(MediaType.APPLICATION_JSON)
          )
                  .andDo(print());
    
    }
}
