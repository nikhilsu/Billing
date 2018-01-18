<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<body>
<h1>Create Bill</h1>
<h3>${message}</h3>
<h3>Enter phone number of existing Patient</h3>
<form action="${pageContext.request.contextPath}/patient" method="get">
    <input placeholder="Phone number" type="tel" name="phoneNumber">
    <input type="submit" value="go">
</form>

<h3>OR</h3>
<h3><a href="${pageContext.request.contextPath}/new-patient">Create new Patient</a></h3>

<h1>Get Bill details</h1>
<form action="${pageContext.request.contextPath}/bill" method="get">
    <label>
        StartDate:
        <input type="date" name="startDate">
    </label>
    <label>
        End Date:
        <input type="date" name="endDate">
    </label>
    <input type="submit">
</form>

</body>
</html>
