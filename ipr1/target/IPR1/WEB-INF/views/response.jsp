<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Methods</title>
</head>
<body>
<c:if test="${message != null}">
    <p>${message}</p>
</c:if>
<c:if test="${methods != null}">
    <c:forEach var="method" items="${methods}">
        <p>${method}</p>
    </c:forEach>
</c:if>
<a href="/index">Back</a>
</body>
</html>
