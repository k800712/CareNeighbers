package todoList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    long countByStatus(Status status);
    long countByPriority(Priority priority);
    long countByDueDateBefore(LocalDateTime date);
    List<Todo> findByStatus(Status status);
}
