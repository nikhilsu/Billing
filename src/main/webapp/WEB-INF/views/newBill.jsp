<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<link rel="stylesheet" type="text/css" href="css/list.css"/>
<script>
    $(document).ready(function () {
        $('#total').text('Total: Rs. 0.0');

        $('.submit-btn').mouseover(function () {
            if (!$(this).hasClass('disabled')) {
                $(this).addClass('hovered-background');
                $(this).removeClass('contrast-background');
            }
        }).mouseout(function () {
            if (!$(this).hasClass('disabled')) {
                $(this).addClass('contrast-background');
                $(this).removeClass('hovered-background');
            }
        });

        $('.category-checkbox:checkbox').each(function () {
            $(this).change(function () {
                var totalCost = 0.0;
                $('.category-checkbox:checkbox:checked').each(function () {
                    totalCost += parseFloat($(this).attr('cost'));
                });
                $('#total').text('Total: Rs. ' + totalCost);
                if ($('.category-checkbox:checkbox:checked').length > 0) {
                    $('.submit-btn').removeClass('disabled');
                    $('.submit-btn').prop('disabled', false);
                }
                else {
                    $('.submit-btn').prop('disabled', true);
                    $('.submit-btn').addClass('disabled');
                }
            });
        });
    });
</script>
<body>
<c:if test="${isAdmin == true}">
    <a href="${pageContext.request.contextPath}/bill-category">Create new bill category</a>
</c:if>

<div id="container">
    <h1 class="header contrast-background">Gubbi's clinic bill</h1>
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