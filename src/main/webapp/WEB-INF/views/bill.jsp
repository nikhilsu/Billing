<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="header.jsp" %>

<body>
<h4>${message}</h4>
<c:forEach var="bill" items="${result}">
    <div>
        <p>Date: ${bill.createdOn}</p>
        <p>Total Cost: Rs. ${bill.totalCost()}</p>
    </div>
</c:forEach>
</body>
