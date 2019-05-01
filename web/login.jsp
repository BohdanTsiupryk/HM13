<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>LogIn</title>
</head>
<body>
    <div align="center">
        <c:if test="${badPass}">
            <b style="color:red">Bad login/password try again or <a href="/registration.jsp">SignUp</a></b> <br><br>
        </c:if>
        ${successReg}<br>
        Please enter your: <br>
        <form action="/login" method="post" accept-charset="UTF-8">
            Login <br> <input type="text" placeholder="Login" required size="20" name="login"/> <br><br>
            Password <br> <input type="password" placeholder="Password" required size="20" name="password"/> <br><br>
            <input type="submit" value="LogIn">
        </form>
        <br><br>
        <a href="/"><button>To main</button></a>
        <a href="registration.jsp"><button>Registration</button></a>
    </div>
</body>
</html>