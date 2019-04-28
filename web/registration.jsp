<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
</head>
<body>
    <div align="center">
        Registration form: <br>
        <form action="/registration" method="post" accept-charset="UTF-8">
            Login <br> <input type="text" placeholder="Login" required size="20" name="login"/> <br><br>
            Email <br> <input type="email" placeholder="Email" required size="20" name="email"/> <br><br>
            Password <br>  <input type="password" placeholder="Password" required size="20" name="password"/> <br>
            <c:if test="${samePass == false}">
                <h4 style="color: red">Password are not the same</h4>
            </c:if>
            Repeat Password <br> <input type="password" placeholder="Repeat password" required size="20" name="repassword"/> <br><br>
            Country <br>
            <select size="1" name="country">
                <option value="Ukraine">Ukraine</option>
                <option value="Poland">Poland</option>
                <option value="Russia">Russia</option>
                <option value="USA">USA</option>
            </select> <br><br>
            <input type="submit" name="submit" value="SignUp" size="10">
        </form>
        <br>
        <a href="login.jsp"><button>LogIn</button></a>
        <a href="/"><button>To main</button></a>
    </div>
</body>
</html>