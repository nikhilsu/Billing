<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="styles.jsp" %>

<div class="header">
    <div class="home">
        <a href="${pageContext.request.contextPath}/"><img class="logo" src="images/logo.jpg"></a>
    </div>
    <div class="header-opts">
        <a href="${pageContext.request.contextPath}/change-password">Change Password</a>
        <a href="${pageContext.request.contextPath}/logout">Logout</a>
    </div>
</div>