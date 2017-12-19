<%-- 
    Document   : menu
    Created on : 14.08.2017, 9:15:51
    Author     : u27brvz18
--%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"/>-->
        <!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
         <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
         <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>-->
        <style>
            <%@include file='css/bootstrap.min.css' %>
            <%@include file='css/menu.css' %>  
            <%@include file='css/style.css' %>     
        </style>

        <script language="javascript" type="text/javascript" src="js/3.2.1_jquery.min.js"></script>

        <script language="javascript" type="text/javascript" src="js/bootstrap.min.js"></script>
    </head>
    <body>        
        <nav class="navbar navbar-my">    
            <div class="container-fluid"> 
                <div class="navbar-header">

                    <a id="projname" class="navbar-brand disabled" href="#">Тестовый проект</a>
                </div>
                <ul class="nav navbar-nav">
                    <!--Дата по умолчанию-->
            <%long curTime = System.currentTimeMillis();
            String curStringDate = new SimpleDateFormat("dd.MM.yyyy").format(curTime);%>
                    <li><a href="index.jsp" >Главная</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Просмотр данных<span class="caret"></span></a> 
                        <ul class="dropdown-menu">
                            <li><a href="SkisqlController?action=list&page=1">Просмотр базы данных Skisql</a></li>
                            <li><a href="Search?page=1&count=0&osdch=${osdch}&osdk=${osdk}&kiz=${kiz}&svi=<%=curStringDate%>">Просмотр базы данных Vp44150sql</a></li>
                        </ul>
                    </li> 
                    <li>
                        <form class="frm" method="post" name="exit" action="login.jsp">
                            <button id="justbutton" class="btn btn-default btn-xs navbar-btn " >Выход</button>
                            <script>
                                //setTimeout(function myd() {
                                //  document.getElementById('justbutton').click()
                                //},100000);
                            </script>
                        </form>
                    </li>
                </ul>        
            </div>
        </nav>

        <!-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>  
         <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>-->

    </body>
</html>
