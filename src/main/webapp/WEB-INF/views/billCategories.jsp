<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet" type="text/css" href="css/list.css"/>
<%@ include file="header.jsp" %>
<script>
    $(document).ready(function () {
        setTimeout(function() {
            $('#flash-message').slideUp();
        }, 5000);
    })
</script>



<body>

<div id="flash-message" class="
        <c:choose>
            <c:when test="${param.isError == true}">
                error-message
            </c:when>
            <c:otherwise>
                success-message
            </c:otherwise>
        </c:choose>">
    ${param.message}
</div>

<div class="big-container">
    <h1 class="heading contrast-background">All categories</h1>
    <table class="categories-table">
        <th>Name</th>
        <th>Type</th>
        <th>cost(in Rs.)</th>
        <c:if test="${isAdmin == true}">
            <th>Update</th>
        </c:if>
        <c:set var="i" scope="session" value="0"/>
        <c:forEach var="category" items="${result}">
            <c:set var="i" scope="session" value="${i + 1}"/>
            <c:choose>
                <c:when test="${isAdmin == true}">
                    <tr>
                        <td>
                            <form id="edit-${i}"
                                  action="${pageContext.request.contextPath}/bill-category/${category.id}"
                                  method="post">
                                <input class="edit-category-input" type="text" id="category-name" name="name"
                                       value="${category.name}" title="name"/>
                            </form>
                        </td>
                        <td>
                            <select form="edit-${i}" name="type" title="type">
                                <option value="lab"
                                        <c:if test="${fn:toLowerCase(category.categoryType.toString())} == 'lab'">selected</c:if>>
                                    Lab
                                </option>
                                <option value="clinic"
                                        <c:if test="${fn:toLowerCase(category.categoryType.toString())} == 'clinic'">selected</c:if>>
                                    Clinic
                                </option>
                            </select>
                        </td>
                        <td>
                            <input form="edit-${i}" title="cost" class="edit-category-input" type="text"
                                   id="category-cost" name="cost"
                                   value="${category.cost}"/>
                        </td>
                        <td>
                            <button type="submit" form="edit-${i}" class="index-submit">Edit</button>
                        </td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td>
                            <div>${category.name}</div>
                        </td>
                        <td>
                            <div>${category.categoryType.toString()}</div>
                        </td>
                        <td>
                            <div>Rs.${category.cost}</div>
                        </td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </table>
</div>
</body>
