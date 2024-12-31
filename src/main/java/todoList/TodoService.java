package todoList;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TodoService {
    private final TodoRepository todoRepository;


    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }
    @Transactional
    public Todo createTodo(CreateTodoRequest request) {
        Todo todo = new Todo(request.createdUserName(), request.title(), request.description(), request.priority());
        return todoRepository.save(todo);
    }

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public List<Todo> getTodosByUserName(String createdUserName) {
        return todoRepository.findAll().stream()
                .filter(todo -> todo.getCreatedUserName().contains(createdUserName))
                .collect(Collectors.toList());
    }

    public Todo getTodoById(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));
    }
    @Transactional
    public Todo updateTodo(Long id, Todo todoDetails) {
        Todo todo = getTodoById(id);
        todo.setTitle(todoDetails.getTitle());
        todo.setDescription(todoDetails.getDescription());
        todo.setDueDate(todoDetails.getDueDate());
        todo.setPriority(todoDetails.getPriority());
        return todoRepository.save(todo);
    }
    @Transactional
    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }
    @Transactional
    public Todo completeTodo(Long id) {
        Todo todo = getTodoById(id);
        todo.setStatus(Todo.Status.COMPLETED);
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
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime startOfNextDay = today.plusDays(1).atStartOfDay();
        return todoRepository.findTodayTodos(startOfDay, startOfNextDay);
    }

    public List<Todo> getCompletedTodos() {
        return todoRepository.findAll().stream()
                .filter(todo -> Todo.Status.COMPLETED.equals(todo.getStatus()))
                .collect(Collectors.toList());
    }

    public List<Todo> getPendingTodos() {
        return todoRepository.findAll().stream()
                .filter(todo -> Todo.Status.PENDING.equals(todo.getStatus()))
                .collect(Collectors.toList());
    }

    public Map<String, Object> getStatistics() {
        Map<String, Object> stats = new HashMap<>();
        long totalTodos = todoRepository.count();
        long completedTodos = todoRepository.countByStatus(Todo.Status.COMPLETED);
        long pendingTodos = todoRepository.countByStatus(Todo.Status.PENDING);
        long highPriorityTodos = todoRepository.countByPriority(Todo.Priority.HIGH);
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
        List<Todo> completedTodos = todoRepository.findByStatus(Todo.Status.COMPLETED);
        if (completedTodos.isEmpty()) {
            return 0;
        }
        long totalCompletionTime = completedTodos.stream()
                .mapToLong(todo -> ChronoUnit.MINUTES.between(todo.getCreatedAt(),
                        todo.getCompletedAt()))
                .sum();
        return (double) totalCompletionTime / completedTodos.size();
    }
    @Transactional
    public Todo reopenTodo(Long id) {
        Todo todo = getTodoById(id);
        if (todo.getStatus() != Todo.Status.COMPLETED) {
            throw new RuntimeException("This todo is not completed");
        }
        todo.setStatus(Todo.Status.PENDING);
        todo.setCompletedAt(null);
        return todoRepository.save(todo);
    }

    public Todo addAttachment(Long todoId, MultipartFile file) throws IOException {
        Todo todo = getTodoById(todoId);
        Todo.Attachment attachment = new Todo.Attachment();
        attachment.setFileName(file.getOriginalFilename());
        attachment.setFileType(file.getContentType());
        attachment.setData(file.getBytes());
        todo.getAttachments().add(attachment);
        return todoRepository.save(todo);
    }

    public Todo.Attachment getAttachment(Long todoId, Long attachmentId) {
        Todo todo = getTodoById(todoId);
        return todo.getAttachments().stream()
                .filter(attachment -> attachment.getId().equals(attachmentId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Attachment not found"));

    }
    public TodoDTO convertToDTO(Todo todo) {
        return new TodoDTO(
                todo.getId(),
                todo.getCreatedUserName(),
                todo.getTitle(),
                todo.getDescription(),
                todo.getPriority(),
                todo.getStatus(),
                todo.getCreatedAt(),
                todo.getDueDate(),
                todo.getCompletedAt()

        );
    }

    public AttachmentDTO convertToAttachmentDTO(Todo.Attachment attachment) {
        return new AttachmentDTO(
                attachment.getId(),
                attachment.getFileName(),
                attachment.getFileType()
        );
    }
}
