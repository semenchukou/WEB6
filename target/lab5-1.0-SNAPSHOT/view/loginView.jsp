<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
<h3>Login Page</h3>
<p style="color: red;">${errorString}</p>


<form action="${pageContext.request.contextPath}/?command=login" method="post">
    <table border="0">
        <tr>
            <td>User Name</td>
            <td><input type="text" name="username" /> </td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password" /> </td>
        </tr>
        <tr>
            <td>Remember me</td>
            <td><input type="checkbox" name="rememberMe" value= "Y" /> </td>
        </tr>
        <tr>
            <td colspan ="2">
                <input type="submit" name = "bt" value= "Submit" />
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" name = "bt" value= "Guest" />
            </td>
            <td>
                <input type="submit" name = "bt" value= "Register" />
            </td>
        </tr>
    </table>
</form>
</body>
</html>