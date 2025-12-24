package io.awportfoiioapi.userlist.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserListPostRequest {
    
    @NotBlank(message = "이메일은 필수 입력입니다.")
    private String email;
    @NotBlank(message = "비밀번호는 필수 입력입니다.")
    private String password;
    @NotBlank(message = "이름은 필수 입력입니다.")
    private String name;
    @NotBlank(message = "권한은 필수 입력입니다.")
    private String role;
    
}
