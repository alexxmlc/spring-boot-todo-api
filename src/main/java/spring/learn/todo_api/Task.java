package spring.learn.todo_api;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity                 //Tells springthis is a database entity
@Data                   //Generates all getters/settters/cons
@NoArgsConstructor      //Just empty constructor
public class Task{
    @Id                 //Mark this as primary key
    @GeneratedValue     //Tells spring to autogenerate this value
    
    private Long id;
    private String description;
    private boolean completed;
}
