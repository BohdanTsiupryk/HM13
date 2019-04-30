<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hello ${helloLogin}</title>
    <style>
        table {
            border-collapse: collapse;
        }

        table, th, td {
            border: 1px solid black;
        }
    </style>
</head>
<body>
    <table>
        <tr>
            <th>id</th>
            <th>Login</th>
            <th>Password</th>
            <th>E-mail</th>
            <th>Country</th>
            <th>Role</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
            <td>${user.getId()}</td>
            <td>${user.getLogin()}</td>
            <td>${user.getPassword()}</td>
            <td>${user.getEmail()}</td>
            <td>${user.getCountry()}</td>
            <td>${user.getRole()}</td>
            <td>
                <form action="/edit" accept-charset="UTF-8">
                    <button value="${user.getLogin()}" name="edit">edit</button>
                </form>
            </td>
            <td>
                <form action="/delete" accept-charset="UTF-8">
                    <button value="${user.getId()}" name="delete">delete</button>
                </form>
            </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>