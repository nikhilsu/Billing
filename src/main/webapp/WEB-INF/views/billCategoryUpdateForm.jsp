<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>

<body>
<form action="${pageContext.request.contextPath}/bill-category/${result.id}" method="post">
    <label for="category-name">Name</label>
    <input placeholder="Name" type="text" id="category-name" name="name" value="${result.name}"/>
    <label for="category-cost">Cost</label>
    <input placeholder="Cost" type="text" id="category-cost" name="cost" value="${result.cost}"/>
    <label for="category-type">Type</label>
    <select name="type" id="category-type">
        <c:choose>
            <c:when test="${result.categoryType == 'LAB'}">
                <option value="lab" selected="selected">Lab</option>
                <option value="clinic">Clinic</option>
            </c:when>
            <c:otherwise>
                <option value="lab">Lab</option>
                <option value="clinic" selected="selected">Clinic</option>
            </c:otherwise>
        </c:choose>
    </select>
    <input type="submit" value="Update"/>
</form>
</body>
