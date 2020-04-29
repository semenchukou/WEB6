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
        <title>Home Page</title>
    </head>
    <body>
        <h2><fmt:message key="home.title"/></h2>
        <form action="${pageContext.request.contextPath}/?command=home" method="post">
            <table>
                <tr>
                    <td><button name="bt" value="Login"><fmt:message key="bt.login"/></button></td>
                    <td><button name="bt" value="Logout"><fmt:message key="bt.logout"/></button></td>
                </tr>
            </table>
        </form>
        <p style="color: red;">${errorString}</p>
        <ul>
            <li>
                <a href="${pageContext.request.contextPath}?command=requestsList"><fmt:message key="command.requestsList"/></a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}?command=requestsListWithTag"><fmt:message key="command.requestsListWithTag"/></a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}?command=workersList"><fmt:message key="command.workersList"/></a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}?command=outdatedList"><fmt:message key="command.outdatedList"/></a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}?command=setWorker"><fmt:message key="command.setWorker"/></a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}?command=declineRequest"><fmt:message key="command.declineRequest"/></a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}?command=userInfo"><fmt:message key="command.userInfo"/></a>
            </li>

        </ul>
    </body>

</html>