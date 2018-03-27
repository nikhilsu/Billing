<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="styles.jsp" %>

<body>
<div class="login-page">
    <div class="form">
        <p class="login-form-message">Change Password</p>
        <c:if test="${isError == true}">
            <p class="login-error-message">${message}</p>
        </c:if>
        <form action="${pageContext.request.contextPath}/change-password" method="post">
            <input type="password" placeholder="Password" name="password"/>
            <input type="password" placeholder="Confirm Password" name="confirmPassword"/>
            <button>Change</button>
        </form>
        <a href="${pageContext.request.contextPath}/"><button>Cancel</button></a>
    </div>
</div>
</body>
