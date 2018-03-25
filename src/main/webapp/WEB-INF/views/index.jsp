<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<%@ include file="header.jsp" %>
<head>
    <link rel="stylesheet" type="text/css" href="src/main/webapp/resources/css/main.css"/>
    <link rel="stylesheet" type="text/css" href="src/main/webapp/resources/css/main.css"/>
    <link rel="stylesheet" type="text/css" href="src/main/webapp/resources/css/main.css"/>
    <link rel="stylesheet" type="text/css" href="src/main/webapp/resources/css/main.css"/>
</head>
<body>
<span>${message}</span>
<div class="index-container">
    <div class="create-bill">
        <span class="index-page-header">Create Bill</span>
        <span class="index-help-text">
        Enter phone number of existing Patient OR
        <a class="new-patient-link" href="${pageContext.request.contextPath}/new-patient">Create new Patient</a>
    </span>
        <form action="${pageContext.request.contextPath}/patient" method="get">
            <input class="patient-phone-number" placeholder="Phone number" type="tel" name="phoneNumber">
            <br/>
            <button class="index-submit" type="submit">Search</button>
        </form>

    </div>
    <div class="bill-details">
        <span class="index-page-header">Bill details</span>
        <form action="${pageContext.request.contextPath}/bill" method="get">
            <label>
                <span class="index-help-text">StartDate:</span>
                <input class="date-range" type="date" name="startDate">
            </label>
            <br/>
            <label>
                <span class="index-help-text">End Date:</span>
                <input class="date-range" type="date" name="endDate">
            </label>
            <br/>
            <button class="index-submit" type="submit">Fetch</button>
        </form>
    </div>
</div>
</body>
</html>
