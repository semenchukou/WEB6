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
    <title>Login</title>
</head>
<body>
<h3><fmt:message key="login.title"/></h3>
<p style="color: red;">${errorString}</p>


<form action="${pageContext.request.contextPath}/?command=login" method="post">
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
            <td colspan ="2">
                <button name="bt" value="Submit"><fmt:message key="bt.submit"/></button>
            </td>
        </tr>
        <tr>
            <td>
                <button name="bt" value="Guest"><fmt:message key="bt.guest"/></button>
            </td>
            <td>
                <button name="bt" value="Register"><fmt:message key="bt.register"/></button>
            </td>
        </tr>
    </table>
</form>
<form>
    <div class="form-group">
        <select class="form-control" name='lang' onchange='this.form.submit()'>
            <c:choose>
            <c:when test="${userLocale.language == 'ru'}">
            <option value='ru' selected>Русский
            <option value='en'>English
                </c:when>
                <c:otherwise>
            <option value='ru'>Русский
            <option value='en' selected>English
                </c:otherwise>
                </c:choose>
        </select>
    </div>
</form>
</body>
</html>