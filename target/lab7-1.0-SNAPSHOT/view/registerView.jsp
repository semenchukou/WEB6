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
    <title>Register</title>
</head>
<body>
<h3><fmt:message key="bt.register"/></h3>
<p style="color: red;">${errorString}</p>


<form action="${pageContext.request.contextPath}/?command=register" method="post">
    <table border="0">
        <tr>
            <td><fmt:message key="login.user"/></td>
            <td><input type="text" name="username" /> </td>
        </tr>
        <tr>
            <td><fmt:message key="login.ps"/></td>
            <td><input type="password" name="password" /> </td>
        </tr>
        <tr>
            <td><fmt:message key="login.re_ps"/></td>
            <td><input type="password" name="repassword" /> </td>
        </tr>
        <tr>
            <td>Admin</td>
            <td><input type="checkbox" name="admin" value="admin"/></td>
        </tr>
        <tr>
            <td colspan ="2">
                <button name="bt" value="Submit"><fmt:message key="bt.submit"/></button>
            </td>
        </tr>
    </table>
</form>
<a href="${pageContext.request.contextPath}/?command=login"><button name="bt" value="Submit"><fmt:message key="bt.cancel"/></button></a>
</body>
</html>