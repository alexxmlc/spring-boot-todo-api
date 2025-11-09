//Creates an object that allows the use of
//database specific methods like save(), findById()...

package spring.learn.todo_api;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUserId(Long userId);

    Optional<Task> findByIdAndUserId(Long id, Long userId);
}
