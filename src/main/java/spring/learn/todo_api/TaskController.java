package spring.learn.todo_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.ResponseEntity;
import java.util.Optional;
import java.util.List;



@RestController             //Tells spring this is a controller for REST requests
@RequestMapping("/tasks")   //Makes all methods in this class start with http://.../tasks
public class TaskController{

    //Ask spring to give us the TaskRepository
    //Basically dependency injection but with less steps and easier
    @Autowired
    TaskRepository taskRepository;

    //First endpoint
    //This method will run when someone sends a GET equest to /tasks
    @GetMapping
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    @PostMapping
    public Task createTask(@RequestBody Task task){
        //1.Take the task obj from the request body
        //2.Save it to the database using the repository
        //3.Return the saved task
        return taskRepository.save(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetails){
        //1.Find existing tasks by id
        //Optional is like a box that might contain or not an obj
        Optional<Task> optionalTask = taskRepository.findById(id);

        //2.Check if the task was found
        if(optionalTask.isPresent()){
            Task existingTask = optionalTask.get();

            existingTask.setDescription(taskDetails.getDescription());
            existingTask.setCompleted(taskDetails.isCompleted());   

            taskRepository.save(existingTask);
            return ResponseEntity.ok(existingTask);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable Long id){
        
        Optional<Task> optionalTask = taskRepository.findById(id);

        if(optionalTask.isPresent()){
            taskRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}