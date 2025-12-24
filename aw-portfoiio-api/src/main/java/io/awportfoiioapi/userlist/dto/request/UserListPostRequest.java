package io.awportfoiioapi.userlist.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserListPostRequest {
    
    private String email;
    private String password;
    private String name;
    private String role;
    
}
