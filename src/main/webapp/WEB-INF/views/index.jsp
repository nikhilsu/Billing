<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<%@ include file="header.jsp" %>
<head>
    <link rel="stylesheet" type="text/css" href="src/main/webapp/resources/css/main.css"/>
</head>
<body>
<span>${message}</span>
<div class="index-container">
    <div class="create-bill">
        <span class="index-page-header">Create Bill</span>
        <span class="error-message bold-big">${param.findPatientMessage}</span>
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
    <c:if test="${isAdmin == true}">
        <div class="create-category">
            <span class="index-page-header-less-margin">Test types</span>
            <span class="<c:out value="${isError == true ? 'error-message': 'success-message'}"/>">
                    ${testCategoryMessage}
            </span>
            <form action="${pageContext.request.contextPath}/bill-category" method="post">
                <div class="create-category-labels">
                    <label for="category-name">Name</label>
                    <label for="category-cost">Cost</label>
                    <label for="category-type">Type</label>
                </div>
                <div class="create-category-input-div">
                    <input class="create-category-input" placeholder="Name" type="text" id="category-name" name="name"/>
                    <input class="create-category-input" placeholder="Cost" type="text" id="category-cost" name="cost"/>
                    <select class="create-category-input center-align" name="type" id="category-type">
                        <option value="lab">Lab</option>
                        <option value="clinic" selected="selected">Clinic</option>
                    </select>
                </div>
                <button class="index-submit" type="submit">Create</button>
                OR
                <a class="link-with-btn" href="${pageContext.request.contextPath}/bill-category">
                    <span class="index-submit">View all</span>
                </a>
            </form>
        </div>

        <div class="bill-details">
            <span class="index-page-header">Bill details</span>
            <form action="${pageContext.request.contextPath}/bill" method="get">
                <label>
                    <span class="index-help-text">Start Date:</span>
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
    </c:if>
</div>
</body>
</html>
