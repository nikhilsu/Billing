<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="header.jsp" %>

<body>
<h4>${message}</h4>
<c:forEach var="patient" items="${result}">
    <div class="select-patient-div">
        <p><b>Name:</b> ${patient.name}</p>
        <p><b>Age:</b> ${patient.age}</p>
        <p><b>Phone number:</b> ${patient.phoneNumber}</p>
        <p><b>Email:</b> ${patient.email}</p>
        <a href="${pageContext.request.contextPath}/new-bill?patientId=${patient.id}" class="link-with-btn">
            <button class="index-submit">Select</button>
        </a>
    </div>
</c:forEach>
</body>
