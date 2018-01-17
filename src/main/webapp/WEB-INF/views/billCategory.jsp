<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<body>

<c:choose>

    <c:when test="${update == false}">
        <form action="${pageContext.request.contextPath}/bill-category" method="post">
            <label for="category-name">Name</label>
            <input placeholder="Name" type="text" id="category-name" name="name"/>
            <label for="category-cost">Cost</label>
            <input placeholder="Cost" type="text" id="category-cost" name="cost"/>
            <label for="category-type">Cost</label>
            <select name="type" id="category-type">
                <option value="lab">Lab</option>
                <option value="clinic" selected="selected">Clinic</option>
            </select>
            <input type="submit" value="Create"/>
        </form>
    </c:when>

    <c:otherwise>
        <form action="${pageContext.request.contextPath}/bill-category${result.id}" method="post">
            <label for="category-name">Name</label>
            <input placeholder="Name" type="text" id="category-name" name="name" value="${result.name}"/>
            <label for="category-cost">Cost</label>
            <input placeholder="Cost" type="text" id="category-cost" name="cost" value="${result.cost}"/>
            <label for="category-type">Cost</label>
            <input type="submit" value="Create"/>
            <select name="type" id="category-type">
                <c:choose>
                    <c:when test="${result.categoryType == \"lab\"}">
                        <option value="lab" selected="selected">Lab</option>
                        <option value="clinic">Clinic</option>
                    </c:when>
                    <c:otherwise>
                        <option value="lab">Lab</option>
                        <option value="clinic" selected="selected">Clinic</option>
                    </c:otherwise>
                </c:choose>
            </select>
        </form>
    </c:otherwise>
</c:choose>
</body>
