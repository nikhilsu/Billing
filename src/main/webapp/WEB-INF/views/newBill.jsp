<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="header.jsp" %>
<link rel="stylesheet" type="text/css" href="css/list.css"/>
<script src="js/list.js"></script>

<body>
<c:if test="${isAdmin == true}">
    <a href="${pageContext.request.contextPath}/bill-category">Create new bill category</a>
</c:if>

<div id="container">
    <h1 class="heading contrast-background">Gubbi's clinic bill</h1>
    <div class="patient-details">
        <span class="patient-name">Name: ${patient.name}</span>
        <span class="patient-phone">Phone Number: ${patient.phoneNumber}</span>
    </div>
    <form action="${pageContext.request.contextPath}/bill" method="post">
        <ul>
            <c:forEach var="category" items="${billCategories}">
                <li>
                    <label class="bill-categories">
                        <input class="category-checkbox" type="checkbox" name="billCategories"
                           value="${category.id}" cost="${category.cost}">
                        <div class="category-name">${category.name}</div>
                        <div class="cost">Rs.${category.cost}</div>
                    </label>
                </li>
            </c:forEach>
            <li>
                <div id="total"></div>
            </li>
        </ul>
        <input type="hidden" name="patientId" value="${patient.id}">
        <button type="submit" class="submit-btn contrast-background disabled" disabled="disabled">Create</button>
    </form>
</div>
</body>