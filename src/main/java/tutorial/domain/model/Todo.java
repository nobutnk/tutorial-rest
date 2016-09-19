package tutorial.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Todo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String todoId;

    private String todoTitle;
    
    private String todoCategory;
    
    private String todoDetail;

    private boolean finished;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    private LocalDateTime createdAt;

}
