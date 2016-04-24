package tutorial.domain.service.todo;

import java.util.Collection;
import java.util.Date;

import tutorial.domain.model.Todo;

public interface TodoService {

    Collection<Todo> findAll();
    
    Todo findOne(String todoId);

    Todo create(Todo todo);

    Todo finish(String todoId, Date updatedAt);

    void delete(String todoId, Date updatedAt);
    
    Todo update(Todo todo);
}
