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
    <title>Workers List</title>
     <link rel="stylesheet" type="text/css" href="styles.css"/>
 </head>
 <body>
    <h2><fmt:message key="command.workersList"/></h2>
 
    <p style="color: red;">${errorString}</p>

    <form action="${pageContext.request.contextPath}/?command=workersList" method="post">
        <p><fmt:message key="worker.profile"/>:
            <input type="text" name="profile" /></p>
    </form>
    <div class="page-table">
        <table width="900px">
            <thead style="color: #007bff;">
               <tr>
                  <th>ID</th>
                  <th><fmt:message key="worker.name"/></th>
                   <th><fmt:message key="worker.profile"/></th>
               </tr>
            </thead>
            <tbody>
               <c:forEach items="${workersList}" var="worker" >
                  <tr>
                     <td>${worker.worker_id}</td>
                     <td>${worker.worker_name}</td>
                      <td>${worker.worker_profile}</td>
                  </tr>
               </c:forEach>
            </tbody>
        </table>
    </div>

    <a href="${pageContext.request.contextPath}/?command=home"><fmt:message key="bt.home"/></a>
 </body>
</html>