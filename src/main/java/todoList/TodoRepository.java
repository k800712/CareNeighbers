package todoList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    long countByStatus(Todo.Status status);
    long countByPriority(Todo.Priority priority);
    long countByDueDateBefore(LocalDateTime date);
    List<Todo> findByStatus(Todo.Status status);

    @Query("SELECT t FROM Todo t WHERE t.dueDate >= :startOfDay AND t.dueDate < :startOfNextDay")
    List<Todo> findTodayTodos(@Param("startOfDay") LocalDateTime startOfDay, @Param("startOfNextDay") LocalDateTime startOfNextDay);

}
