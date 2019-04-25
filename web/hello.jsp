<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hello ${helloLogin}</title>
</head>
<body>
    <table>
        <tr>
            <th>ID</th>
            <th>Login</th>
            <th>Password</th>
            <th>E-mail</th>
            <th>Country</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
            <td><c:out value="${user.getID()}"/></td>
            <td><c:out value="${user.getLogin()}"/></td>
            <td><c:out value="${user.getPassword()}"/></td>
            <td><c:out value="${user.getEmail()}"/></td>
            <td><c:out value="${user.getCountry()}"/></td>
            <td>
                <form action="/edit" accept-charset="UTF-8">
                    <button value="${user.getLogin()}" name="edit">edit</button>
                </form>
            </td>
            <td>
                <form action="/delete" accept-charset="UTF-8">
                    <button value="${user.getID()}" name="delete">delete</button>
                </form>
            </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>