//Creates an object that allows the use of
//database specific methods like save(), findById()...

package spring.learn.todo_api;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
    
}
