<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>
 <head>
    <meta charset="UTF-8">
    <title>Requests List</title>
     <link rel="stylesheet" type="text/css" href="styles.css"/>
 </head>
 <body>
    <h2>Список запросов на выполнение работ</h2>
 
    <p style="color: red;">${errorString}</p>

    <div class="page-table">
        <table width="900px">
            <thead style="color: #007bff;">
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Job type</th>
                    <th scope="col">Job description</th>
                    <th scope="col">Date</th>
                    <th scope="col">Status(0 - new, 1 - in process, 2 - completed, 3 - canceled)</th>
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

    <a href="${pageContext.request.contextPath}/?command=home">Home</a>
 </body>
</html>