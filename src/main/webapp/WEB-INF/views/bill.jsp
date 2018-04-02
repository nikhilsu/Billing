<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="header.jsp" %>
<link rel="stylesheet" type="text/css" href="css/list.css"/>

<body>
<h4>${message}</h4>
<c:if test="${!empty result}">
    <div class="big-container">
        <h1 class="heading contrast-background">Bills from ${param.startDate} to ${param.endDate}</h1>

        <table class="categories-table">
            <th>Bill Id</th>
            <th>Date</th>
            <th>Patient Name</th>
            <th>Total cost(in Rs.)</th>
            <th></th>
            <c:forEach var="billByMaskedId" items="${result}">
                <tr>
                    <td>${billByMaskedId.key}</td>
                    <td>${billByMaskedId.value.createdOnDateString}</td>
                    <td>${billByMaskedId.value.patient.name}</td>
                    <td>${billByMaskedId.value.totalCost()}</td>
                    <td><a class="link-with-btn" href="${pageContext.request.contextPath}/bill/${billByMaskedId.value.id}" target="_blank">
                        <span class="view-bill-button">View PDF</span>
                    </a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</c:if>
</body>
