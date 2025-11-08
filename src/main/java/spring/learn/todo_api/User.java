package spring.learn.todo_api;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "app_user") //Good practice to prevent conflicts with "USER" in sql
@Data
@NoArgsConstructor
public class User {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @NotBlank(message = "Username cannot be blank")
    private String username;

    @NotBlank(message = "Password cannot be blank")
    private String password;

    @NotBlank(message = "Email cannot be blank")
    private String email;
}
