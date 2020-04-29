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

<form action="${pageContext.request.contextPath}/?command=editChosenRequest" method="post">
    <table>
        <tbody>
        <tr>
            <td>id</td>
            <td>${request.request_id}<input type="hidden" name="request_id" value="${request.request_id}"></td>
        </tr>
        <tr>
            <td><fmt:message key="request.job_type"/></td>
            <td><input type="text" name="job_type" value=${request.job_type}></td>
        </tr>
        <tr>
            <td><fmt:message key="request.job_descr"/></td>
            <td><input type="text" name="job_descr" value=${request.job_descr}></td>
        </tr>
        <tr>
            <td><fmt:message key="request.date"/></td>
            <td><input type="date" name="to_date" value=${request.to_date}></td>
        </tr>
        <tr>
            <td><fmt:message key="request.tenant_id"/></td>
            <td><input type="number" name="tenant_id" min="1" value=${request.tenant_id}></td>
        </tr>
        <tr>
            <td><fmt:message key="request.status"/></td>
            <td><input type="number" name="status" max="3" min="0" value=${request.status}></td>
        </tr>
        <tr>
            <td><button name="bt" value="Cancel"><fmt:message key="bt.cancel"/></button></td>
            <td><button name="bt" value="Save"><fmt:message key="bt.save"/></button></td>
        </tr>
        </tbody>
    </table>
</form>

<a href="${pageContext.request.contextPath}/?command=home">Home</a>
<p style="color: red;">${errorMessage}</p>

</body>
</html>