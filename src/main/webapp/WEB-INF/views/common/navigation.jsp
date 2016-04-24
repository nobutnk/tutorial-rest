<nav class="navbar navbar-inverse">
<div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbarEexample5">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">
                Tutorial
            </a>
        </div>
        
        <div class="collapse navbar-collapse" id="navbarEexample5">
            <ul class="nav navbar-nav">
                <sec:authorize access="isAuthenticated()" var="isAuthenticated">
                <li><a href="${pageContext.request.contextPath}">Top Page</a></li>
                <li><a href="${pageContext.request.contextPath}/todo/list">Todo List</a></li>
                <li><a href="${pageContext.request.contextPath}/todo/create?form">Add Todo</a></li>
                <li><a href="${pageContext.request.contextPath}">Shop Page</a></li>
                <li><a href="${pageContext.request.contextPath}/item/">Item</a></li>
                <li><a href="${pageContext.request.contextPath}/cart/">Cart</a></li>
                <li><a href="${pageContext.request.contextPath}/article/list">Article</a></li>
                </sec:authorize>
                <li><a href="${pageContext.request.contextPath}/signup?form">Signup</a></li>
            </ul>
            <sec:authorize access="isAuthenticated()" var="isAuthenticated">
            <p class="navbar-text">ようこそ <sec:authentication property="principal.account.username"/> さん。</p>
            </sec:authorize>
            <form:form class="navbar-form navbar-left" action="${pageContext.request.contextPath}/logout" method="post">
                <button class="btn btn-default">ログアウト</button>
            </form:form>
        </div>
    </div>
</nav>
