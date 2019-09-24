<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Class methods</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/response">
    <label>
        <input name="class" placeholder="Write the class">
    </label>
    <button type="submit">Submit</button>
</form>
</body>
</html>
