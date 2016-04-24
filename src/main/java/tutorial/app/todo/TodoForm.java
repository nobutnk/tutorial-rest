package tutorial.app.todo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.terasoluna.gfw.common.codelist.ExistInCodeList;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TodoForm implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public static interface TodoCreate {
    };

    public static interface TodoFinish {
    };
    
    public static interface TodoUpdate {
    };
    
    public static interface TodoUpdateForm {
    };

    public static interface TodoDelete {
    };
    
    @NotNull(groups = { TodoFinish.class, TodoUpdate.class, TodoUpdateForm.class, TodoDelete.class })
    private String todoId;

    @NotNull(groups = { TodoCreate.class, TodoUpdate.class })
    @Size(min = 1, max = 30, groups = { TodoFinish.class, TodoUpdate.class })
    private String todoTitle;
    
    @NotNull(groups = { TodoCreate.class, TodoUpdate.class })
    @ExistInCodeList(codeListId = "CL_TODO_CATEGORIES", groups = { TodoFinish.class, TodoUpdate.class })
    private String todoCategory;
    
    @NotNull(groups = { TodoCreate.class, TodoUpdate.class })
    @Size(min = 1, max = 100, groups = { TodoFinish.class, TodoUpdate.class })
    private String todoDetail;
    
    @NotNull(groups = { TodoFinish.class, TodoUpdate.class, TodoDelete.class })
    private String updatedAt;
    
}
