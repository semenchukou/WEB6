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
    <title>Requests List</title>
     <link rel="stylesheet" type="text/css" href="styles.css"/>
 </head>
 <body>
    <h2><fmt:message key="command.requestsList"/></h2>
 
    <p style="color: red;">${errorString}</p>

    <div class="page-table">
        <table width="900px">
            <thead style="color: #007bff;">
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col"><fmt:message key="request.job_type"/></th>
                    <th scope="col"><fmt:message key="request.job_descr"/></th>
                    <th scope="col"><fmt:message key="request.date"/></th>
                    <th scope="col"><fmt:message key="request.status"/></th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestsList}" var="request" >
                <tr>
                    <td scope="col">${request.request_id}</td>
                    <td scope="col">${request.job_type}</td>
                    <td scope="col">${request.job_descr}</td>
                    <td scope="col">${request.to_date}</td>
                    <td scope="col">${request.status}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    </div>

    <a href="${pageContext.request.contextPath}/?command=home"><fmt:message key="bt.home"/></a>
 </body>
</html>