package spring.learn.todo_api;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity // Tells springthis is a database entity
@Data // Generates all getters/settters/cons
@NoArgsConstructor // Just empty constructor
public class Task {
    @Id // Mark this as primary key
    @GeneratedValue // Tells spring to autogenerate this value

    private Long id;
    @NotBlank(message = "Descriprion can't be blank ya shithead")
    private String description;
    private boolean completed;

    @ManyToOne
    @JoinColumn(name = "user_id") // Creates user_id column in task table
    @JsonIgnore // When we ask for a task we don't want it to return the user
    @ToString.Exclude 
    @EqualsAndHashCode.Exclude 
    private User user;
}
