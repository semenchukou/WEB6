<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Home Page</title>
    </head>
    <body>
        <h2>Жилищно-коммунальные услуги.</h2>
        <form action="${pageContext.request.contextPath}/?command=home" method="post">
            <table>
                <tr>
                    <td><button name="bt" value="Login">Login</button></td>
                    <td><button name="bt" value="Logout">Logout</button></td>
                </tr>
            </table>
        </form>
        <p style="color: red;">${errorString}</p>
        <ul>
            <li>
                <a href="${pageContext.request.contextPath}?command=requestsList">Просмотреть заявки на выполнение работ.</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}?command=workersList">Вывести список рабочих, выполняющих заданную работу.</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}?command=outdatedList">Вывести список просроченных заявок.</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}?command=setWorker">Назначить рабочего на выполнение работы.</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}?command=declineRequest">Отклонить заявку.</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}?command=userInfo">Информация о клиенте.</a>
            </li>
        </ul>
    </body>
</html>