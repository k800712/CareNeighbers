package todoList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public Map<String, Object> getStatistics() {
        Map<String, Object> stats = new HashMap<>();
        long totalTodos = todoRepository.count();
        long completedTodos = todoRepository.countByStatus(Status.COMPLETED);
        long pendingTodos = todoRepository.countByStatus(Status.PENDING);
        long highPriorityTodos = todoRepository.countByPriority(Priority.HIGH);
        long overdueTodos = todoRepository.countByDueDateBefore(LocalDateTime.now());

        stats.put("totalTodos", totalTodos);
        stats.put("completedTodos", completedTodos);
        stats.put("pendingTodos", pendingTodos);
        stats.put("highPriorityTodos", highPriorityTodos);
        stats.put("overdueTodos", overdueTodos);
        stats.put("completionRate", calculateCompletionRate(totalTodos, completedTodos));
        stats.put("averageCompletionTime", calculateAverageCompletionTime());

        return stats;
    }

    private double calculateCompletionRate(long totalTodos, long completedTodos) {
        return totalTodos > 0 ? (double) completedTodos / totalTodos * 100 : 0;
    }

    private double calculateAverageCompletionTime() {
        List<Todo> completedTodos = todoRepository.findByStatus(Status.COMPLETED);
        if (completedTodos.isEmpty()) {
            return 0;
        }
        long totalCompletionTime = completedTodos.stream()
                .mapToLong(todo -> ChronoUnit.MINUTES.between(todo.getCreatedAt(), todo.getCompletedAt()))
                .sum();
        return (double) totalCompletionTime / completedTodos.size();
    }

    public Todo reopenTodo(Long id) {
        Todo todo = getTodoById(id);
        if (todo.getStatus() != Status.COMPLETED) {
            throw new RuntimeException("This todo is not completed");
        }
        todo.setStatus(Status.PENDING);
        todo.setCompletedAt(null);
        return todoRepository.save(todo);
    }
    public Todo addAttachment(Long todoId, MultipartFile file) throws IOException {
        Todo todo = getTodoById(todoId);
        Attachment attachment = new Attachment();
        attachment.setFileName(file.getOriginalFilename());
        attachment.setFileType(file.getContentType());
        attachment.setData(file.getBytes());
        todo.getAttachments().add(attachment);
        return todoRepository.save(todo);
    }

    public Attachment getAttachment(Long todoId, Long attachmentId) {
        Todo todo = getTodoById(todoId);
        return todo.getAttachments().stream()
                .filter(attachment -> attachment.getId().equals(attachmentId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Attachment not found"));
    }

}
