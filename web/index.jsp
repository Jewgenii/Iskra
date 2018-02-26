<%-- 
    Document   : index
    Created on : 26.07.2017, 13:49:55
    Author     : Sergey Nikonenko
--%>

<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ласкаво просимо</title>
        <script language="javascript" type="text/javascript" src="js/TweenMax.min.js"></script>
        
        <%@include file="menu.jsp" %>

    </head>
    
    <body>
        <div class="container-fluid">    
            <h3>Ласкаво просимо до системи перегляду баз даних<%--<strong>${sessionScope.sessname}</strong>--%></h3>
            <div id="introR" class="super_back"><canvas id="pollyfill-canvas"></canvas></div>
            <%--<jsp:forward page="/SkisqlController?action=listSkisql" />--%>  
        </div>
<script language="javascript" type="text/javascript" src="js/fon_index.js"></script>
    </body>
    
</html>
