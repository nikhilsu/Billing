<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<body>

<h3>All categories</h3>
<c:forEach var="category" items="${result}">
    <div>
        <p>${category.name}</p>
        <p>${category.cost}</p>
        <p>${category.categoryType.toString()}</p>
        <c:if test="${isAdmin == true}">
            <a href="${pageContext.request.contextPath}/bill-category/${category.id}">Edit</a>
        </c:if>
    </div>
</c:forEach>

<c:if test="${isAdmin == true}">
    <h3>Create new category</h3>
    <form action="${pageContext.request.contextPath}/bill-category" method="post">
        <label for="category-name">Name</label>
        <input placeholder="Name" type="text" id="category-name" name="name"/>
        <label for="category-cost">Cost</label>
        <input placeholder="Cost" type="text" id="category-cost" name="cost"/>
        <label for="category-type">Type</label>
        <select name="type" id="category-type">
            <option value="lab">Lab</option>
            <option value="clinic" selected="selected">Clinic</option>
        </select>
        <input type="submit" value="Create"/>
    </form>
</c:if>

</body>
