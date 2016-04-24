<h1>Add Todo</h1>
<div id="todoForm">
    <t:messagesPanel />
    <div class="container">
    
    <%-- for action --%>
    <c:choose>
        <c:when test="${!empty todoForm.todoId}">
            <c:set var="targetAction" value="update" />
        </c:when>
        <c:otherwise>
            <c:set var="targetAction" value="create" />
        </c:otherwise>
    </c:choose>
    <%-- /for action --%>
    
    
    <form:form
       action="${pageContext.request.contextPath}/todo/${targetAction}"
        method="post" modelAttribute="todoForm">
        
        <form:errors path="*" element="div" cssClass="error-message-list" />
        <div class="form-group">
            <label for="todoTitle">Title</label>
            <form:input path="todoTitle" class="form-control"/>
            <form:errors path="todoTitle" cssClass="text-danger"/>
        </div>
        <div class="form-group">
            <label for="todoCategory">Category</label>
            <form:select path="todoCategory" class="form-control">
                <form:option value="" label="--Select--" />
                <form:options items="${CL_TODO_CATEGORIES}" />
            </form:select>
        </div>
        <div class="form-group">
            <label for="todoDetail">Detail</label>
            <form:input path="todoDetail" class="form-control"/>
            <form:errors path="todoDetail" cssClass="text-danger"/>
        </div>
        <form:button class="btn btn-info">Create Todo</form:button>
    </form:form>
    </div>
</div>