<%--
  Created by IntelliJ IDEA.
  User: tsiupryk
  Date: 24.04.19
  Time: 21:21
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
</head>
<body aling="center">
EDIT INFORMATION ABOUT USER(${user.getLogin()}) <br><br>
<form action="/edit" method="post" accept-charset="UTF-8">
    <input type="hidden" value="${user.getId()}" name="id"/>
    Login <br> <input type="text" value="${user.getLogin()}" placeholder="Login" required size="20" name="login"/> <br><br>
    Email <br> <input type="text" value="${user.getEmail()}" placeholder="Email" required size="20" name="email"/> <br><br>
    Country <br> <input type="text" value="${user.getCountry()}" placeholder="Country" required size="20" name="country"/> <br><br>
    Password <br> <input type="text" value="${user.getPassword()}" placeholder="Password" required size="20" name="password"/> <br><br>
    <input type="submit" name="submit" value="Save Changes" size="10">
</form>
</body>
</html>
