package tutorial.domain.repository.todo;

import java.util.Collection;

import tutorial.domain.model.Todo;

public interface TodoRepository {

    Todo findOne(String todoId);

    Collection<Todo> findAll();

    void create(Todo todo);

    int update(Todo todo);

    int delete(Todo todo);

    long countByFinished(boolean finished);
}
