<%-- 
    Document   : menu
    Created on : 14.08.2017, 9:15:51
    Author     : Sergey Nikonenko
--%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


        
        <link rel="stylesheet" href="css/bootstrap.min.css?fdfdf=fff"/>
        <link rel="stylesheet" href="css/menu.css?fdfdf=fff"/>
        <link rel="stylesheet" href="css/style.css?fdfdf=fff"/>
        <link rel="stylesheet" href="css/dropdownMenu.css?fdfdf=fff"/>  
        
        <nav id="colorAMenu" class="navbar navbar-my">    
            <div class="container-fluid"> 
                <div class="navbar-header">

                    <script language="javascript" type="text/javascript" src="js/dropdownMenu.js">
                    </script>
                    
                    <a class="navbar-brand disabled" href="#">Бази даних. Перегляд</a>
                </div>
                <ul class="nav navbar-nav">
                    <!--Дата по умолчанию-->
                    <%long curTime = System.currentTimeMillis();
                        String curStringDate = new SimpleDateFormat("dd.MM.yyyy").format(curTime);
                        
                    %>
                    <li hover><a href="index.jsp" >Головна</a></li>
                    <li class="dropdown" hover>
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Перегляд даних<span class="caret"></span></a> 
                        <ul id="dropAColor" class="dropdown-menu">
                            <%--<li><a href="SkisqlController?action=list&page=1">Перегляд бази Skisql</a></li>--%>
                            <li><a href="Search?page=1&count=0&osdch=${osdch}&osdk=${osdk}&kiz=${kiz}&svi=<%=curStringDate%>&pages=25">Кількість одиниць складових(деталей) у виробі</a></li>
                            <li><a href="SearchTmi?page=1&count=0&osdch=${osdch}&nc=${nc}&svi=<%=curStringDate%>">Технологічний маршрут виготовлення</a></li>
                            <li><a href="modeTmpSelect.jsp">Технологічний маршрут споживання</a></li>
                            <li><a href="SearchNaimesql">Виробничі та стандартні позиції специфікації.Найменування</a></li>
                           <%-- <li class="dropdown dropdown-submenu"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Технологічний маршрут виготовлення(TMPSQL)</a>
                                <ul class="dropdown-menu">
                                    <li><a href="SearchTmpCh?page=1&count=0&osdch=${osdch}&osdk=${osdk}&cp=${cp}&svi=<%=curStringDate%>">Перегляд "Що"-"Куди"</a></li>
                                    <li><a href="SearchTmpK?page=1&count=0&osdch=${osdch}&osdk=${osdk}&cp=${cp}&svi=<%=curStringDate%>">Перегляд "Куди"-"Що"</a></li>
                                </ul>
                            </li>--%>
                        </ul>
                    </li> 
                </ul>  

                <!--Скрипт для подсказок-->      
                <script>
                    $(function () {
                        $('[data-toggle="tooltip"]').tooltip();
                    });
                </script>

                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <form class="frm" method="post" name="exit" action="login.jsp">
                            <!-- <button id="justbutton" class="btn  btn-sm btn-success navbar-btn " >Вихід</button>-->
                            <button id="justbutton" class="btn  btn-sm btn-success navbar-btn" data-toggle="tooltip" data-placement="left" title="Вихід" >
                                <span class="glyphicon glyphicon-log-out"></span>
                            </button>
                        </form>
                    </li>
                </ul>
            </div>
        </nav>
        <script  type="text/javascript" src="js/3.2.1_jquery.min.js"></script>
        <script  type="text/javascript" src="js/jquary_ui.js"></script>
        <script  type="text/javascript" src="js/bootstrap.min.js"></script>