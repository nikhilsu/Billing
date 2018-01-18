<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="Login"/>
<%@ include file="header.jsp" %>

<body>
<div class="login-page">
    <div class="form">
        <c:if test="${result == false}">
            <p class="login-error-message login-form-message">Invalid user id or password</p>
        </c:if>
        <form action="${pageContext.request.contextPath}/login" method="post">
            <input type="text" placeholder="user id" name="userId"/>
            <input type="password" placeholder="password" name="password"/>
            <button>login</button>
            <p class="message">Not registered? <a href="${pageContext.request.contextPath}/register">Create an account</a></p>
        </form>
    </div>
</div>
</body>
