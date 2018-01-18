<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<body>
<h4>${message}</h4>
<c:forEach var="patient" items="${result}">
    <div>
        <p>Name: ${patient.name}</p>
        <p>Age: ${patient.age}</p>
        <p>Phone number: ${patient.phoneNumber}</p>
        <p>Email: ${patient.email}</p>
        <a href="${pageContext.request.contextPath}/new-bill?patientId=${patient.id}">Select</a>
    </div>
</c:forEach>
</body>
