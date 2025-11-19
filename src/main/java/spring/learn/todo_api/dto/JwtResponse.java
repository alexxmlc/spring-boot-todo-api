package spring.learn.todo_api.dto;

import java.util.List;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class JwtResponse {

    private String token;
    private Long id;
    private String username;
    private String email;
    private List<String> roles;
}
