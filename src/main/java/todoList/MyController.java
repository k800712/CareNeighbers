package todoList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/todos")
public class MyController {
    private final TodoService todoService;

    @Autowired
    public MyController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public Todo createTodo(@RequestBody Todo todo) {
        return todoService.createTodo(todo);
    }

    @GetMapping
    public List<Todo> getAllTodos() {
        return todoService.getAllTodos();
    }

    @GetMapping("/{id}")
    public Todo getTodoById(@PathVariable Long id) {
        return todoService.getTodoById(id);
    }

    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable Long id, @RequestBody Todo todoDetails) {
        return todoService.updateTodo(id, todoDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
    }

    @PatchMapping("/{id}/complete")
    public Todo completeTodo(@PathVariable Long id) {
        return todoService.completeTodo(id);
    }

    @PatchMapping("/{id}/reopen")
    public Todo reopenTodo(@PathVariable Long id) {
        return todoService.reopenTodo(id);
    }

    @GetMapping("/{id}/completed-time")
    public LocalDateTime getCompletedTime(@PathVariable Long id) {
        Todo todo = todoService.getTodoById(id);
        if (todo.getStatus() != Todo.Status.COMPLETED) {
            throw new RuntimeException("This todo is not completed yet");
        }
        return todo.getCompletedAt();
    }

    @GetMapping("/search")
    public List<Todo> searchTodos(@RequestParam String keyword) {
        return todoService.searchTodos(keyword);
    }

    @GetMapping("/today")
    public List<Todo> getTodayTodos() {
        return todoService.getTodayTodos();
    }

    @GetMapping("/completed")
    public List<Todo> getCompletedTodos() {
        return todoService.getCompletedTodos();
    }

    @GetMapping("/pending")
    public List<Todo> getPendingTodos() {
        return todoService.getPendingTodos();
    }

    @GetMapping("/statistics")
    public Map<String, Object> getStatistics() {
        return todoService.getStatistics();
    }

    @PostMapping("/{id}/attachments")
    public Todo addAttachment(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException {
        return todoService.addAttachment(id, file);
    }

    @GetMapping("/{todoId}/attachments/{attachmentId}")
    public ResponseEntity<byte[]> getAttachment(@PathVariable Long todoId, @PathVariable Long attachmentId) {
        Todo.Attachment attachment = todoService.getAttachment(todoId, attachmentId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachment.getFileName() + "\"")
                .body(attachment.getData());
    }
}
