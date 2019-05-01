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
    Country <br>
    <select size="1" name="country">
        <option <c:if test="${user.getCountry().equals(\"Ukraine\")}"> selected </c:if> value="Ukraine">Ukraine</option>
        <option <c:if test="${user.getCountry().equals(\"Poland\")}"> selected </c:if> value="Poland">Poland</option>
        <option <c:if test="${user.getCountry().equals(\"Russia\")}"> selected </c:if> value="Russia">Russia</option>
        <option <c:if test="${user.getCountry().equals(\"USA\")}"> selected </c:if> value="USA">USA</option>
    </select>  <br><br>
    Password <br> <input type="text" value="${user.getPassword()}" placeholder="Password" required size="20" name="password"/> <br><br>
    <c:if test="${editorRole == 'ADMIN'}">
        Role <br>
        <select size="1" name="role" >
            <option value="USER">USER</option>
            <option value="ADMIN">ADMIN</option>
        </select> <br><br>
    </c:if>
    <input type="submit" name="submit" value="Save Changes" size="10">
</form>
</body>
</html>
