<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<script>
    $(document).ready(function() {
        $('#total').text('Total: Rs. 0.0');
        $('.category-checkbox:checkbox').each(function () {
            $(this).change(function () {
                var totalCost = 0.0;
                $('.category-checkbox:checkbox:checked').each(function () {
                    totalCost += parseFloat($(this).attr('cost'));
                });
                $('#total').text('Total: Rs. ' + totalCost);
            });
        });
    });
</script>
<body>
<form action="${pageContext.request.contextPath}/bill" method="post">
    <c:forEach var="category" items="${billCategories}">
        <label>
            <input class="category-checkbox" type="checkbox" name="billCategories" value="${category.id}" cost="${category.cost}">
            ${category.name} = Rs.${category.cost}
        </label>
    </c:forEach>
    <input type="hidden" name="patientId" value="${patient.id}">
    <input type="submit" value="Create">
</form>
<h4 id="total"></h4>
</body>
