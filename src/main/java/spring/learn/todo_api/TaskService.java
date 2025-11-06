package spring.learn.todo_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;
import java.util.List;


@Service    // Tells spring this is a service (it should create a "bean")
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task createTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        
        Optional<Task> optionalTask = taskRepository.findById(id);

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
