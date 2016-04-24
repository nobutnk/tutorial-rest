package tutorial.domain.service.todo;

import java.util.Collection;
import java.util.Date;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;
import org.terasoluna.gfw.common.message.ResultMessage;
import org.terasoluna.gfw.common.message.ResultMessages;

import tutorial.domain.model.Todo;
import tutorial.domain.repository.todo.TodoRepository;

@Service
@Transactional
public class TodoServiceImpl implements TodoService {

    private static final long MAX_UNFINISHED_COUNT = 100;
    
    @Inject
    TodoRepository todoRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Todo findOne(String todoId) {
        Todo todo = todoRepository.findOne(todoId);
        if (todo == null) {
            ResultMessages messages = ResultMessages.error();
            messages.add(ResultMessage
                    .fromText("[E404] The requested Todo is not found. (id="
                            + todoId + ")"));
            throw new ResourceNotFoundException(messages);
        }
        return todo;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Collection<Todo> findAll() {
        return todoRepository.findAll();
    }

    @Override
    public Todo create(Todo todo) {
        long unfinishedCount = todoRepository.countByFinished(false);
        if (unfinishedCount >= MAX_UNFINISHED_COUNT) {
            ResultMessages messages = ResultMessages.error();
            messages.add(ResultMessage
                    .fromText("[E001] The count of un-finished Todo must not be over "
                            + MAX_UNFINISHED_COUNT + "."));
            throw new BusinessException(messages);
        }

        Integer todoId = todoRepository.createTodoId();
        Date createdAt = new Date();

        todo.setTodoId(todoId.toString());
        todo.setCreatedAt(createdAt);
        todo.setUpdatedAt(createdAt);
        todo.setFinished(false);

        todoRepository.create(todo);

        return todo;
    }

    @Override
    public Todo finish(String todoId, Date updatedAt) {
        Todo todo = findOne(todoId);
        if (todo.isFinished()) {
            ResultMessages messages = ResultMessages.error();
            messages.add(ResultMessage
                    .fromText("[E002] The requested Todo is already finished. (id="
                            + todoId + ")"));
            throw new BusinessException(messages);
        }
        todo.setFinished(true);
        todo.setUpdatedAt(updatedAt);
        int resultNum = todoRepository.update(todo);
        if (resultNum != 1) {
            ResultMessages messages = ResultMessages.error();
            messages.add(ResultMessage
                    .fromText("[E003] The requested Todo is already updated. (id="
                            + todo.getTodoId() + ")"));
            throw new BusinessException(messages);
        }
        
        return todo;
    }

    @Override
    public void delete(String todoId, Date updatedAt) {
        Todo todo = findOne(todoId);
        todo.setUpdatedAt(updatedAt);
        int resultNum = todoRepository.delete(todo);
        if (resultNum != 1) {
            ResultMessages messages = ResultMessages.error();
            messages.add(ResultMessage
                    .fromText("[E003] The requested Todo is already updated. (id="
                            + todo.getTodoId() + ")"));
            throw new BusinessException(messages);
        }
    }

    @Override
    public Todo update(Todo todo) {
        int resultNum = todoRepository.update(todo);
        if (resultNum != 1) {
            ResultMessages messages = ResultMessages.error();
            messages.add(ResultMessage
                    .fromText("[E003] The requested Todo is already updated. (id="
                            + todo.getTodoId() + ")"));
            throw new BusinessException(messages);
        }
        
        return todo;
    }

}
