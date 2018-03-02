<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:useBean id="triadTmiNc" class="Bean.TriadBeanTmiNc">
    <jsp:setProperty name="triadTmiNc" property="delimiter" value="-"/>
    <jsp:setProperty name="triadTmiNc" property="size" value="3"/>
</jsp:useBean>

<!DOCTYPE html>
<html>
    <head class="head">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="menu.jsp"%>
        <style>
            <%@include file='css/jquery-ui.css' %> 
            <%@include file='css/table.css'%>
        </style>
        <script language="javascript" type="text/javascript" src="js/jquary_ui.js"></script>
        <!--<script language="javascript" type="text/javascript" src="js/jquery-3.2.1.min.js"></script>-->
        <script language="javascript" type="text/javascript" src="js/autocomplitKiz.js"></script>
        <script language="javascript" type="text/javascript" src="js/jquery.stickytableheaders.js"></script>
        <script language="javascript" type="text/javascript" src="js/exportExcel_Tmi.js"></script>

        <title>Фільтр tmisql</title>      
    </head>
    <body>
        <div class="container-fluid " >
            <div class="panel panel-default">
                <div id="headFiltPan" class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse" href="#collapse1">Фільтр</a>
                    </h4>
                </div>

                <div id="collapse1" class="panel-collapse collapse in">
                    <div class="panel-body">
                        <div class="container-fluid">
                            <form  class="searchTmi" method="get" name="frm" action="SearchTmi">
                                <label>Позначення</label> <input type="text" minlength="3" size="12px" name="osdch" value="${osdch}" id="osdch" align="middle" placeholder="ОСД-Що">

                                <label>Маршрут</label> <input type="text" size="12px" name="nc" value="${nc}" id="nc" align="middle" placeholder="Тех. маршрут">

                                <label>Строк</label> <input type="text" size="12px" name="svi" value="${svi}" id="svi" align="middle" required placeholder="Строк впровадження повідомлення">

                                <input type="hidden" name="page" id="page" value="1">

                                <input type="hidden" name="count" id="count" value="0">
                                
                                <button class="btn btn-primary btn-md">Выполнить фильтр</button>

                                <input id="searchClear" class="btn btn-warning btn-md" type="button" value="Сбросить фильтр" onclick="window.location.href = 'TmisqlController?action=list&page=1'" />
                            </form>
                        </div>
                    </div>
                </div>
            </div>  
        </div>
        <script type="text/javascript">
            $(function () {
                $("table").stickyTableHeaders();
            });
        </script>
        <div class="container-fluid">
            <nav class="pag" >
                <ul class="pagination">
                    <c:choose>
                        <c:when test="${page == 1}"><li><a class="disabled" href="SearchTmi?page=1&count=0&osdch=${osdch}&nc=${nc}&svi=${svi}">Первая</a></li></c:when> 
                        <c:otherwise><li><a class="a2" href="SearchTmi?page=1&count=0&osdch=${osdch}&nc=${nc}&svi=${svi}">Первая</a></li></c:otherwise>
                        </c:choose>

                    <c:choose>
                        <c:when test="${page <=1}"><li class="li1"><a class="disabled" href="SearchTmi?page=${page-1}&count=${count-100}&osdch=${osdch}&nc=${nc}&svi=${svi}">Назад</a></li></c:when> 
                        <c:otherwise><li class="li1"><a class="a2" href="SearchTmi?page=${page-1}&count=${count-100}&osdch=${osdch}&nc=${nc}&svi=${svi}">Назад</a></li></c:otherwise> 
                        </c:choose>

                    <c:set var="firstPageNum" scope="request" value="${ param.page - 2 }"/>
                    <c:set var="lastPageNum" scope="request" value="${ empty param.page ? 3 : param.page + 2 }"/>
                    <c:forEach var="i" begin="${ firstPageNum > 0 ? firstPageNum : 1 }" 
                               end="${ lastPageNum > size ? size : lastPageNum }">  
                        <li class="li1"><a class="a2" href="SearchTmi?page=${i}&count=${(i-1)*100}&osdch=${osdch}&nc=${nc}&svi=${svi}">${i}</a></li>   
                        </c:forEach>

                    <c:choose>
                        <c:when test="${page >= size}"><li class="li1"><a class="disabled" href="SearchTmi?page=${page+1}&count=${count+100}&osdch=${osdch}&nc=${nc}&svi=${svi}">Вперед</a></li></c:when> 

                        <c:otherwise><li><a class="a2" href="SearchTmi?page=${page+1}&count=${count+100}&osdch=${osdch}&nc=${nc}&svi=${svi}">Вперед</a></li></c:otherwise>
                        </c:choose>

                    <%--<c:choose>
                    <c:when test="${page == counts}"><li><a class="disabled" href=Search?page=${counts}&osdch=${osdch}&osdk=${osdk}&kiz=${kiz}&svi=${svi}>Последняя</a></li></c:when> 
                    <c:otherwise><li><a class="a2" href=Search?page=${counts}&osdch=${osdch}&osdk=${osdk}&kiz=${kiz}&svi=${svi}>Последняя</a></li></c:otherwise>
                    </c:choose>--%>

                    <li><a>Страница №: ${page}</a></li>
                    <li><button id="excel2" class="btn btn-success btn-md" onclick="fnExcelReport3();">Экспорт в excel</button></li>
                </ul>
            </nav>
        </div>

        <div class="container-fluid">
            <table border=1 id="tableList" class="table table-striped table-bordered" acceptCharset="UTF-8">
                <thead id="backgroundHeadTmi">

                    <tr>
                        <th>Одиниця складова(деталь). Позначення.<br> 
                            Найменування</th>
                        <th>Технологічний маршрут<br> 
                            виготовлення</th>
                        <th>Строк впровадження повідомлення<br> 
                            про зміну</th>
                        <th>Номер повідомлення<br> 
                            про зміну</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${not empty tmisqlS}">
                            <c:forEach items="${tmisqlS}" var="tmisql" >
                                <tr>

                                    <td><c:out value="${tmisql.osdch}" /><br>
                                        <c:out value="${tmisql.naim}" />
                                    </td>

                                    <td><c:set target="${triadTmiNc}" property="chunks" value="${tmisql.nc}" />
                                        <c:out value="${triadTmiNc.chunks}"/>
                                    </td>  

                                    <%--<td><c:out value="${tmisql.nc}" /></td>--%>
                                    <td><fmt:formatDate pattern="MM.yyyy" value="${tmisql.svi}" />
                                    </td>

                                    <td><c:out value="${tmisql.nizv}" />
                                    </td>

                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="7" align="center">Інформація за вказаним контекстом відсутня</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>            
                </tbody>
            </table>
        </div>
        <div class="container-fluid">
            <nav class="pag2" >

                <ul class="pagination">
                    <c:choose>
                        <c:when test="${page == 1}"><li><a class="disabled" href="SearchTmi?page=1&count=0&osdch=${osdch}&nc=${nc}&svi=${svi}">Первая</a></li></c:when> 
                        <c:otherwise><li><a class="a2" href="SearchTmi?page=1&count=0&osdch=${osdch}&nc=${nc}&svi=${svi}">Первая</a></li></c:otherwise>
                        </c:choose>

                    <c:choose>
                        <c:when test="${page <=1}"><li class="li1"><a class="disabled" href="SearchTmi?page=${page-1}&count=${count-100}&osdch=${osdch}&nc=${nc}&svi=${svi}">Назад</a></li></c:when> 
                        <c:otherwise><li class="li1"><a class="a2" href="SearchTmi?page=${page-1}&count=${count-100}&osdch=${osdch}&nc=${nc}&svi=${svi}">Назад</a></li></c:otherwise> 
                        </c:choose>

                    <c:set var="firstPageNum" scope="request" value="${ param.page - 2 }"/>
                    <c:set var="lastPageNum" scope="request" value="${ empty param.page ? 3 : param.page + 2 }"/>
                    <c:forEach var="i" begin="${ firstPageNum > 0 ? firstPageNum : 1 }" 
                               end="${ lastPageNum > size ? size : lastPageNum }">  
                        <li class="li1"><a class="a2" href="SearchTmi?page=${i}&count=${(i-1)*100}&osdch=${osdch}&nc=${nc}&svi=${svi}">${i}</a></li>   
                        </c:forEach>

                    <c:choose>
                        <c:when test="${page >= size}"><li class="li1"><a class="disabled" href="SearchTmi?page=${page+1}&count=${count+100}&osdch=${osdch}&nc=${nc}&svi=${svi}">Вперед</a></li></c:when> 

                        <c:otherwise><li><a class="a2" href="SearchTmi?page=${page+1}&count=${count+100}&osdch=${osdch}&nc=${nc}&svi=${svi}">Вперед</a></li></c:otherwise>
                        </c:choose>

                    <%--<c:choose>
                    <c:when test="${page == counts}"><li><a class="disabled" href=Search?page=${counts}&osdch=${osdch}&osdk=${osdk}&kiz=${kiz}&svi=${svi}>Последняя</a></li></c:when> 
                    <c:otherwise><li><a class="a2" href=Search?page=${counts}&osdch=${osdch}&osdk=${osdk}&kiz=${kiz}&svi=${svi}>Последняя</a></li></c:otherwise>
                    </c:choose>--%>

                    <li><a>Страница №: ${page}</a></li>
                    <li><button id="excel2" class="btn btn-success btn-md" onclick="fnExcelReport3();">Экспорт в excel</button></li>
                </ul>

            </nav>
        </div>
    </body>
</html>