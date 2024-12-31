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
        return todoService.convertToDTO(todoService.createTodo(request));
    }

    @GetMapping
    public List<TodoDTO> getAllTodos() {
        return todoService.getAllTodos().stream()
                .map(todoService::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/by-username")
    public List<TodoDTO> getTodosByUserName(@RequestParam String username) {
        return todoService.getTodosByUserName(username).stream()
                .map(todoService::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public TodoDTO getTodoById(@PathVariable Long id) {
        return todoService.convertToDTO(todoService.getTodoById(id));
    }

    @PutMapping("/{id}")
    public TodoDTO updateTodo(@PathVariable Long id, @RequestBody UpdateTodoRequest request) {
        Todo todo = todoService.getTodoById(id);
        todo.setTitle(request.title());
        todo.setDescription(request.description());
        todo.setPriority(request.priority());
        todo.setDueDate((LocalDateTime) request.dueDate());
        return todoService.convertToDTO(todoService.updateTodo(id, todo));
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
    }

    @PatchMapping("/{id}/complete")
    public TodoDTO completeTodo(@PathVariable Long id) {
        return todoService.convertToDTO(todoService.completeTodo(id));
    }

    @PatchMapping("/{id}/reopen")
    public TodoDTO reopenTodo(@PathVariable Long id) {
        return todoService.convertToDTO(todoService.reopenTodo(id));
    }

    @GetMapping("/search")
    public List<TodoDTO> searchTodos(@RequestParam String keyword) {
        return todoService.searchTodos(keyword).stream()
                .map(todoService::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/today")
    public List<TodoDTO> getTodayTodos() {
        return todoService.getTodayTodos().stream()
                .map(todoService::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/completed")
    public List<TodoDTO> getCompletedTodos() {
        return todoService.getCompletedTodos().stream()
                .map(todoService::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/pending")
    public List<TodoDTO> getPendingTodos() {
        return todoService.getPendingTodos().stream()
                .map(todoService::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/statistics")
    public Map<String, Object> getStatistics() {
        return todoService.getStatistics();
    }

    @PostMapping("/{id}/attachments")
    public TodoDTO addAttachment(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException {
        return todoService.convertToDTO(todoService.addAttachment(id, file));
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
                .map(todoService::convertToAttachmentDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/sorted")
    public List<TodoDTO> getAllTodosSorted(@RequestParam(defaultValue = "newest") String sortBy) {
        return todoService.getAllTodosSortedBy(sortBy).stream()
                .map(todoService::convertToDTO)
                .collect(Collectors.toList());
    }

    @PatchMapping("/{id}/view")
    public TodoDTO incrementViewCount(@PathVariable Long id) {
        return todoService.convertToDTO(todoService.incrementViewCount(id));
    }

    @PatchMapping("/{id}/like")
    public TodoDTO incrementLikeCount(@PathVariable Long id) {
        return todoService.convertToDTO(todoService.incrementLikeCount(id));
    }
}
