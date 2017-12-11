<%-- 
    Document   : errorLogin
    Created on : 14.09.2017, 8:24:08
    Author     : u27brvz18
--%>

<%@ page contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    <img src="img/logo.png" id="logo"><br>
    <style>
        <%@include file='css/login.css' %>         
    </style>
</head>
<body>
    <h3>Вы ввели не верное имя пользователя, попробуйте еще раз</h3>
    <div class="login">

        <form method="POST" name='form-login' action='AuthServlet'>
            <span class="fontawesome-user"></span>
            <input type="text" name="usename" id="user" placeholder="Имя пользователя">

            <!--<span class="fontawesome-lock"></span>
              <input type="password" id="pass" placeholder="Password">-->

            <input class="login-btn" type="submit" value="Войти">


        </form>
    </div>

</body>
</html>
