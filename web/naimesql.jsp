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
        <title>Виробничі та стандартні позиції специфікації.Найменування</title>
        <link rel="stylesheet" href="css/jquery-ui.css"/>
        <link rel="stylesheet" href="css/menu.css"/>
        <link rel="stylesheet" href="css/rawSpanStyle.css"/>  
        <link rel="stylesheet" href="css/table/iskra-table.css"/>
    </head>
    <body>
        <div id="menu"> <%@include file="menu.jsp" %></div>
        <div class="container-fluid iskra-filterContainer"></div>
        <div class="container-fluid">
            <div class=" pull-right"> 
                <div class="rowsOnPage"></div>
                <div class="iskra-paginationContainer"></div>
            </div>
        </div>
        <div class="container-fluid iskra-tableContainer"></div> 
        <div class="container-fluid">
            <div class="iskra-paginationContainer"></div>
        </div>

        <script  type="text/javascript" src="js/table/jquery.stickytableheaders.js"></script>
        <script  type="text/javascript" src="js/table/StickyColumn.js"></script>
        <script  type="text/javascript" src="js/table/BuildTable.js"></script>
        <script  type="text/javascript" src="js/pagination/pagination.js"></script> 
        <script  type="text/javascript" src="js/naimesql/naimesql.js"></script> 
    </body>
</html>
