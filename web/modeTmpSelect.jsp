<%-- 
    Document   : modeSelect
    Created on : 11.04.2018, 9:04:07
    Author     : u27brvz18
--%>

<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Режим перегляду</title>
        <script language="javascript" type="text/javascript" src="js/TweenMax.min.js"></script>

        <%@include file="menu.jsp" %>
        <style>
            <%@include file='css/button.css' %> 
        </style>
    </head>

    <body>
        <div class="container-fluid">    
            <h3>Оберіть режим перегляду технологічного маршруту споживання<%--<strong>${sessionScope.sessname}</strong>--%></h3>

            <%long curTime3 = System.currentTimeMillis();
                String svi = new SimpleDateFormat("dd.MM.yyyy").format(curTime3);
            %>               


            <a  href="SearchTmpCh?page=1&count=0&osdch=${osdch}&osdk=${osdk}&cp=${cp}&svi=<%=svi%>" style="padding-right: 10px;">
                <button id="leftBtn" class="left">Режим "Що-Куди"</button></a>    
            <a href="SearchTmpK?page=1&count=0&osdch=${osdch}&osdk=${osdk}&cp=${cp}&svi=<%=svi%>">
                <button id="leftBtn" class="left">Режим "Куди-Що"</button></a> 

            <!--<input id="searchClear" class="btn btn-warning btn-md" type="button" value="Відмінити фільтр" onclick="window.location.href = 'TmisqlController?action=list&page=1'" />-->

            <div id="introR" class="super_back"><canvas id="pollyfill-canvas"></canvas></div>
                    <%--<jsp:forward page="/SkisqlController?action=listSkisql" />--%>  
        </div>
        <!--<script language="javascript" type="text/javascript" src="js/fon_index.js"></script>-->
    </body>

</html>