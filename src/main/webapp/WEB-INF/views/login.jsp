<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="Login"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
</head>
<body>
    <form action="${pageContext.request.contextPath}/login" method="post">
        <input placeholder="User id" type="text" name="userId" />
        <input type="password" placeholder="Password" name="password" />
        <input type="submit" value="Login"/>
    </form>
</body>
</html>
