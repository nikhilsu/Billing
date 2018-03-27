<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="header.jsp" %>

<body>
<div class="create-patient">
    <span class="index-page-header-less-margin">Create Patient</span>
    <c:if test="${isError == true}">
        <span class="error-message">${message}</span>
    </c:if>
    <form action="${pageContext.request.contextPath}/patient" method="post">
        <div class="create-patient-labels">
            <label for="full-name">Full Name</label>
            <label for="age">Age</label>
            <label for="phone-number">Phone number</label>
            <label for="email">Email</label>
        </div>
        <div class="create-patient-input-div">
            <input class="create-patient-input" type="text" id="full-name" name="name"/><br/>
            <input class="create-patient-input" type="number" id="age" name="age"/><br/>
            <input class="create-patient-input" type="tel" id="phone-number"
                   name="phoneNumber"/><br/>
            <input class="create-patient-input" type="email" id="email" name="email"/><br/>
        </div>
        <button type="submit" class="index-submit">Create</button>
    </form>
</div>
</body>
