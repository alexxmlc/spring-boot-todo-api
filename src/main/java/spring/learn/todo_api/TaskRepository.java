/*
Database remote control
This file helps us save, delete and find Task objects in our database
JpaRepository<Task, Long> by extending with this we tell spring to give us
a complete set of database tools like save(), findById() etc. that work
with the Task class whose Id is of type long
*/

package spring.learn.todo_api;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long>{

}