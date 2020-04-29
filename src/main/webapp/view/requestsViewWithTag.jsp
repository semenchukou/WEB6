<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="/WEB-INF/tld/allRequests.tld" prefix="allRequests" %>
<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${userLocale}"/>
<fmt:setBundle basename="locale"/>

<!DOCTYPE html>

<html>
 <head>
    <meta charset="UTF-8">
    <title>Requests List</title>
     <link rel="stylesheet" type="text/css" href="styles.css"/>
 </head>
 <body>
    <h2><fmt:message key="command.requestsListWithTag"/></h2>
 
    <p style="color: red;">${errorString}</p>

    <div class="page-table">
        <allRequests:allRequests/>
    </div>


    <a href="${pageContext.request.contextPath}/?command=home"><fmt:message key="bt.home"/></a>
 </body>
</html>