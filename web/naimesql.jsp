<%-- 
    Document   : naimesql
    Created on : 13.04.2018, 11:44:51
    Author     : u27brvz14
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=utf-8" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Виробничі та стандартні позиціх специфікації.Найменування</title>

        <%@include file="menu.jsp" %>
        <style><%@include file='css/jquery-ui.css' %></style>

        <script language="javascript" type="text/javascript" src="js/exportExcel_1.js"></script>
        <script language="javascript" type="text/javascript" src="js/autocomplitKiz.js"></script>
        <script language="javascript" type="text/javascript" src="js/jquery.stickytableheaders.js"></script>

        <style>
            <%@include file="css/menu.css"%>
            <%@include file="css/rawSpanStyle.css"%>
        </style>

        <title>Фільтр vp44150sql</title>
    </head>
    <body>
        <c:forEach items="${naimesql}" var="naim">
            <p>
                <c:out value="${naim.naim}"/>
            </p>
            
            
        </c:forEach>
            
    </body>
</html>
