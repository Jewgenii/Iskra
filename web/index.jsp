<%-- 
    Document   : index
    Created on : 26.07.2017, 13:49:55
    Author     : u27brvz17
--%>

<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Добро пожалывать</title>

        <%@include file="menu.jsp" %>

    </head>
    
    <body>
        <div class="container-fluid">    
            <h3>Добро пожаловать в систему <%--<strong>${sessionScope.sessname}</strong>--%></h3>
            <%--<jsp:forward page="/SkisqlController?action=listSkisql" />--%>  
        </div>

    </body>
</html>
