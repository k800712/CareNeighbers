package todoList;

public record CreateTodoRequest(
        String title,
        String description,
        Todo.Priority priority
) {
}
