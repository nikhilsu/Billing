<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="Register"/>
<%@ include file="styles.jsp" %>

<body>
<div class="login-page">
    <div class="form">
        <p class="login-form-message">Registration</p>
        <c:if test="${result == false}">
            <p class="login-error-message">${message}</p>
        </c:if>
        <form action="${pageContext.request.contextPath}/register" method="post">
            <input type="text" placeholder="First Name" name="firstName"/>
            <input type="text" placeholder="Last Name" name="lastName"/>
            <input type="text" placeholder="User Id" name="userId"/>
            <input type="password" placeholder="Password" name="password"/>
            <button>Register</button>
        </form>
    </div>
</div>
</body>
