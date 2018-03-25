<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>

<body>
<form action="${pageContext.request.contextPath}/patient" method="post">
    <label for="full-name">Full Name</label>
    <input placeholder="Full name" type="text" id="full-name" name="name"/>
    <label for="age">Age</label>
    <input placeholder="Age" type="number" id="age" name="age"/>
    <label for="phone-number">Phone number</label>
    <input placeholder="Phone number" type="tel" id="phone-number" name="phoneNumber"/>
    <label for="email">Email</label>
    <input placeholder="Email" type="email" id="email" name="email"/>
    <input type="submit" value="Create"/>
</form>
</body>
