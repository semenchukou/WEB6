<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${userLocale}"/>
<fmt:setBundle basename="locale"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Info</title>
</head>
<body>

<h3><fmt:message key="info.hello"/>, ${username}!</h3>
<p><fmt:message key="info.last_login"/>: ${time}</p>

<a href="${pageContext.request.contextPath}/?command=home"><fmt:message key="bt.home"/></a>
<p style="color: red;">${errorMessage}</p>

</body>
</html>