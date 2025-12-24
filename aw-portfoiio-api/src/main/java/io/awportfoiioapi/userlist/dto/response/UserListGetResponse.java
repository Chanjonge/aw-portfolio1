package io.awportfoiioapi.userlist.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class UserListGetResponse {
    private Long id;
    private String email;
    private String name;
    private String role;
    private LocalDateTime createdAt;
    
    @QueryProjection
    public UserListGetResponse(Long id, String email, String name, String role, LocalDateTime createdAt) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.role = role;
        this.createdAt = createdAt;
    }
}
