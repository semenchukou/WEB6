<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>
 <head>
    <meta charset="UTF-8">
    <title>Workers List</title>
     <link rel="stylesheet" type="text/css" href="styles.css"/>
 </head>
 <body>
    <h2>Список рабочих</h2>
 
    <p style="color: red;">${errorString}</p>

    <form action="${pageContext.request.contextPath}/?command=workersList" method="post">
        <p>Введите тип работ:
            <input type="text" name="profile" /></p>
    </form>
    <div class="page-table">
        <table width="900px">
            <thead style="color: #007bff;">
               <tr>
                  <th>ID</th>
                  <th>Name</th>
                   <th>Profile</th>
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

    <a href="${pageContext.request.contextPath}/?command=home">Home</a>
 </body>
</html>