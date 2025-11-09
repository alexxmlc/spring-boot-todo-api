//HANDLES  WEB REQUESTS(HTTP)
package spring.learn.todo_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;

import java.util.List;

@RestController // Tells spring this is a controller for REST requests
public class TaskController {

    @Autowired
    private TaskService taskService;

    // The controler's only job is to call the service
    @GetMapping("/user/{userId}/tasks")
    public List<Task> getAllTasks(@PathVariable Long userId) {
        return taskService.getAllTasks(userId);
    }

    @PostMapping("/user/{userId}/tasks")
    public Task createTask(@Valid @RequestBody Task task, @PathVariable Long userId) {
        return taskService.createTask(task, userId);
    }

    @PutMapping("/user/{userId}/tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long userId, @PathVariable Long id,
            @Valid @RequestBody Task taskDetails) {
        return taskService.updateTask(userId, id, taskDetails);
    }

    @DeleteMapping("/user/{userId}/tasks/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long userId, @PathVariable Long id) {
        return taskService.deleteTask(userId, id);
    }
}
