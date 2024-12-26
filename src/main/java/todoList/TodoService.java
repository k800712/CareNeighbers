package todoList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Todo getTodoById(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));
    }

    public Todo updateTodo(Long id, Todo todoDetails) {
        Todo todo = getTodoById(id);
        todo.setTitle(todoDetails.getTitle());
        todo.setDescription(todoDetails.getDescription());
        todo.setDueDate(todoDetails.getDueDate());
        todo.setPriority(todoDetails.getPriority());
        return todoRepository.save(todo);
    }

    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }

    public Todo completeTodo(Long id) {
        Todo todo = getTodoById(id);
        todo.setStatus(Status.COMPLETED);
        todo.setCompletedAt(LocalDateTime.now());
        return todoRepository.save(todo);
    }

    public List<Todo> searchTodos(String keyword) {
        return todoRepository.findAll().stream()
                .filter(todo -> todo.getTitle().contains(keyword) ||
                        (todo.getDescription() != null &&
                                todo.getDescription().contains(keyword)))
                .collect(Collectors.toList());
    }

    public List<Todo> getTodayTodos() {
        LocalDateTime today = LocalDateTime.now();
        return todoRepository.findAll().stream()
                .filter(todo -> todo.getDueDate() != null &&
                        today.toLocalDate().equals(todo.getDueDate().toLocalDate()))
                .collect(Collectors.toList());
    }

    public List<Todo> getCompletedTodos() {
        return todoRepository.findAll().stream()
                .filter(todo -> Status.COMPLETED.equals(todo.getStatus()))
                .collect(Collectors.toList());
    }
    public List<Todo> getPendingTodos() {
        return todoRepository.findAll().stream()
                .filter(todo -> Status.PENDING.equals(todo.getStatus()))
                .collect(Collectors.toList());
    }
}
