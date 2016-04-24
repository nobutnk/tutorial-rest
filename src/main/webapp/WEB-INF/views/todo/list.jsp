<h1>Todo List</h1>
<div id="todoForm">
    <%-- messages --%>
    <t:messagesPanel/>
    <div class="container">
    <form:form
       action="${pageContext.request.contextPath}/todo/create"
        method="post" modelAttribute="todoForm">
        <form:input path="todoTitle" />
        <form:button>Search Todo</form:button>
    </form:form>
    </div>
<hr />
    <div class="container" id="todoList">
    <table class="table table-hover">
    <thead>
    <tr>
        <th>id</th>
        <th>title</th>
        <th>category</th>
        <th>detail</th>
        <th>finished</th>
        <th>updated_at</th>
        <th>created_at</th>
        <th>operation</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${todos}" var="todo">
    
    <%-- for style --%>
    <c:choose>
        <c:when test="${todo.finished}">
            <c:set var="trStyle" value="warning" />
        </c:when>
        <c:otherwise>
            <c:set var="trStyle" value="" />
        </c:otherwise>
    </c:choose>
    <%-- /for style --%>
    
    <tr class="${trStyle}">
        <td>${f:h(todo.todoId)}</td>
        <td>${f:h(todo.todoTitle)}</td>
        <td>${f:h(CL_TODO_CATEGORIES[todo.todoCategory])}</td>
        <td>${f:h(todo.todoDetail)}</td>
        <td>${f:h(todo.finished)}</td>
        <td><fmt:formatDate pattern="yyyy/MM/dd HH:mm:ss" value="${todo.updatedAt}" /></td>
        <td><fmt:formatDate pattern="yyyy/MM/dd HH:mm:ss" value="${todo.createdAt}" /></td>
        <td><input
                type="button" value="update" class="btn btn-info" id="update-${f:h(todo.todoId)}"
                data-n="${f:h(todo.todoId)}">
            <input
                type="button" value="finish" class="btn btn-success" id="finish-${f:h(todo.todoId)}"
                data-n="${f:h(todo.todoId)}" data-m='<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss.SSS" value="${todo.updatedAt}" />'>
            <input
                type="button" value="delete" class="btn btn-warning" id="delete-${f:h(todo.todoId)}"
                data-n="${f:h(todo.todoId)}" data-m='<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss.SSS" value="${todo.updatedAt}" />'>
                </td>
    </tr>
    </c:forEach>
    </tbody>
    </table>
    
    <a href="${pageContext.request.contextPath}/todo/list?csv">Download csv</a>
    
    <script type="text/javascript">
    jQuery('[id^=update-]').click(function(){
        $('#updateTodoId').val($(this).data("n"));
        $('#updateTodoForm').submit();
    });
    jQuery('[id^=finish-]').click(function(){
        $('#finishTodoId').val($(this).data("n"));
        $('#finishUpdatedAt').val($(this).data("m"));
        $('#finishTodoForm').submit();
    });
    jQuery('[id^=delete-]').click(function(){
        $('#deleteTodoId').val($(this).data("n"));
        $('#deleteUpdatedAt').val($(this).data("m"));
        $('#deleteTodoForm').submit();
    });
    </script>
    
    <%-- for update form --%>
    <form:form id="updateTodoForm" method="get" action="${pageContext.request.contextPath}/todo/update">
        <input type="hidden" name="todoId" id="updateTodoId">
        <input type="hidden" name="form" >
    </form:form>
    <%-- /for update form --%>
    <%-- for finish form --%>
    <form:form id="finishTodoForm" method="post" action="${pageContext.request.contextPath}/todo/finish">
        <input type="hidden" name="todoId" id="finishTodoId">
        <input type="hidden" name="updatedAt" id="finishUpdatedAt">
    </form:form>
    <%-- /for finish form --%>
    <%-- for delete form --%>
    <form:form id="deleteTodoForm" method="post" action="${pageContext.request.contextPath}/todo/delete">
        <input type="hidden" name="todoId" id="deleteTodoId">
        <input type="hidden" name="updatedAt" id="deleteUpdatedAt">
    </form:form>
    <%-- /for delete form --%>
    </div>
</div>