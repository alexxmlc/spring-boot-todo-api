package spring.learn.todo_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.List;

@Service // Tells spring this holds the business logic
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Task> getAllTasks(Long userId) {
        return taskRepository.findByUserId(userId);
    }

    public Task createTask(Task task, Long userId) {

        // Optional<User> optionalUser = userRepository.findById(userId);
        // if(optionalUser.isPresent()){
        // User existingUser = optionalUser.get();
        // task.setUser(existingUser);
        // taskRepository.save(task);
        // return ResponseEntity.ok(task);
        // }else{
        // return ResponseEntity.notFound().build();
        // }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        user.getTasks().add(task);
        task.setUser(user);

        return taskRepository.save(task);

    }

    public ResponseEntity<Task> updateTask(Long userId, Long id, Task taskDetails) {

        Optional<Task> optionalTask = taskRepository.findByIdAndUserId(id, userId);

        if (optionalTask.isPresent()) {
            Task existingTask = optionalTask.get();

            existingTask.setDescription(taskDetails.getDescription());
            existingTask.setCompleted(taskDetails.isCompleted());

            taskRepository.save(existingTask);

            return ResponseEntity.ok(existingTask);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    public ResponseEntity<Void> deleteTask(Long userId, Long id) {

        Optional<Task> optionalTask = taskRepository.findByIdAndUserId(id, userId);

        if (optionalTask.isPresent()) {
            taskRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
