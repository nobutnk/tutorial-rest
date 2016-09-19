package tutorial.domain.service.todo;

import java.time.LocalDateTime;
import java.util.Collection;

import tutorial.domain.model.Todo;

public interface TodoService {

    Collection<Todo> findAll();
    
    Todo findOne(String todoId);

    Todo create(Todo todo);

    Todo finish(String todoId, LocalDateTime updatedAt);

    void delete(String todoId, LocalDateTime updatedAt);
    
    Todo update(Todo todo);
}
