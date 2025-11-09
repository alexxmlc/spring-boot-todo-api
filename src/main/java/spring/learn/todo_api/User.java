package spring.learn.todo_api;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "app_user") // Good practice to prevent conflicts with "USER" in sql
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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @ToString.Exclude 
    @EqualsAndHashCode.Exclude 
    private List<Task> tasks = new ArrayList<>();
}
