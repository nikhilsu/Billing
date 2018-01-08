<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="Register"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
</head>
<body>
<h1>Registration</h1>
<div>
    <form action="${pageContext.request.contextPath}/register" method="post">
        <input placeholder="First Name" type="text" name="firstName"/>
        <input placeholder="Last Name" type="text" name="lastName"/>
        <input placeholder="User Id" type="text" name="userId"/>
        <input type="password" placeholder="Password" name="password"/>
        <input type="submit" value="Register"/>
    </form>
</div>
</body>
</html>
