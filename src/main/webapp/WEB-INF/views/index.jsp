<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<body>
<h1>Hello world</h1>
<h3>${message}</h3>
<h3>Enter phone number of existing Patient</h3>
<form action="${pageContext.request.contextPath}/bill" method="get">
    <input placeholder="Phone number" type="tel" name="phoneNumber">
    <input type="submit" value="go">
</form>

<h3>OR</h3>
<h3><a href="${pageContext.request.contextPath}/patient">Create new Patient</a></h3>


</body>
</html>
