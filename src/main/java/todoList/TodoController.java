package todoList;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public TodoDTO createTodo(@RequestBody CreateTodoRequest request) {
        return convertToDTO(todoService.createTodo(request));
    }

    @GetMapping
    public List<TodoDTO> getAllTodos() {
        return todoService.getAllTodos().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/by-username")
    public List<TodoDTO> getTodosByUserName(@RequestParam String username) {
        return todoService.getTodosByUserName(username).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public TodoDTO getTodoById(@PathVariable Long id) {
        return convertToDTO(todoService.getTodoById(id));
    }

    @PutMapping("/{id}")
    public TodoDTO updateTodo(@PathVariable Long id, @RequestBody UpdateTodoRequest request) {
        Todo todo = todoService.getTodoById(id);
        todo.setTitle(request.title());
        todo.setDescription(request.description());
        todo.setPriority(request.priority());
        todo.setDueDate((LocalDateTime) request.dueDate());
        return convertToDTO(todoService.updateTodo(id, todo));
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
    }

    @PatchMapping("/{id}/complete")
    public TodoDTO completeTodo(@PathVariable Long id) {
        return convertToDTO(todoService.completeTodo(id));
    }

    @PatchMapping("/{id}/reopen")
    public TodoDTO reopenTodo(@PathVariable Long id) {
        return convertToDTO(todoService.reopenTodo(id));
    }

    @GetMapping("/search")
    public List<TodoDTO> searchTodos(@RequestParam String keyword) {
        return todoService.searchTodos(keyword).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/today")
    public List<TodoDTO> getTodayTodos() {
        return todoService.getTodayTodos().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/completed")
    public List<TodoDTO> getCompletedTodos() {
        return todoService.getCompletedTodos().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/pending")
    public List<TodoDTO> getPendingTodos() {
        return todoService.getPendingTodos().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/statistics")
    public Map<String, Object> getStatistics() {
        return todoService.getStatistics();
    }

    @PostMapping("/{id}/attachments")
    public TodoDTO addAttachment(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException {
        return convertToDTO(todoService.addAttachment(id, file));
    }

    @GetMapping("/{todoId}/attachments/{attachmentId}")
    public ResponseEntity<byte[]> getAttachment(@PathVariable Long todoId, @PathVariable Long attachmentId) {
        Todo.Attachment attachment = todoService.getAttachment(todoId, attachmentId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachment.getFileName() + "\"")
                .body(attachment.getData());
    }

    @GetMapping("/{id}/attachments")
    public List<AttachmentDTO> getTodoAttachments(@PathVariable Long id) {
        Todo todo = todoService.getTodoById(id);
        return todo.getAttachments().stream()
                .map(this::convertToAttachmentDTO)
                .collect(Collectors.toList());
    }

    private TodoDTO convertToDTO(Todo todo) {
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

    private AttachmentDTO convertToAttachmentDTO(Todo.Attachment attachment) {
        return new AttachmentDTO(
                attachment.getId(),
                attachment.getFileName(),
                attachment.getFileType()
        );
    }
}
