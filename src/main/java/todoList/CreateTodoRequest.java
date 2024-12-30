package todoList;

public record CreateTodoRequest(
        String createdUserName,
        String title,
        String description,
        Todo.Priority priority
) {
}
