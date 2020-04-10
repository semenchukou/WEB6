<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Info</title>
</head>
<body>

<h3>Hello, ${username}!</h3>
<p>Last login: ${time}</p>

<a href="${pageContext.request.contextPath}/?command=home">Home</a>
<p style="color: red;">${errorMessage}</p>

</body>
</html>