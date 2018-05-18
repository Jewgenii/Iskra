<%-- 
    Document   : searchview
    Created on : 15.08.2017, 8:55:42
    Author     : Sergey Nikonenko
--%>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=utf-8" %>
<jsp:useBean id="grouper" class="Bean.TriadBean">
    <jsp:setProperty name="grouper" property="delimiter" value="-"/>
    <jsp:setProperty name="grouper" property="size" value="3"/>
</jsp:useBean>
<jsp:useBean id="triadCp" class="Bean.TriadBeanCp">
    <jsp:setProperty name="triadCp" property="delimiter" value="-"/>
    <jsp:setProperty name="triadCp" property="size" value="3"/>
</jsp:useBean>
<jsp:useBean id="triadNcK" class="Bean.TriadBeanNcK">
    <jsp:setProperty name="triadNcK" property="delimiter" value="-"/>
    <jsp:setProperty name="triadNcK" property="size" value="3"/>
</jsp:useBean>
<!DOCTYPE HTML>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Фільтр vp44150sql</title>
        <style>
            <%@include file='css/jquery-ui.css' %>
            <%@include file="css/menu.css"%>
            <%@include file="css/rawSpanStyle.css"%>
        </style>
        <%@include file="menu.jsp" %>
        <link rel="stylesheet" href="css/table/iskra-table.css"/>
    </head>

    <body>

        <div class="row">
            <div class="col-md-2">test</div>
            <div class="col-md-10">
                <div class="container-fluid ">
                    <div class="panel-group">
                        <div class="panel panel-default">
                            <div id="headFiltPan" class="panel-heading">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" href="#collapse1">Фільтр</a>
                                </h4>
                            </div>



                            <div id="collapse1" class="panel-collapse collapse in">
                                <div class="panel-body">
                                    <div class="container-fluid">
                                        <div class="row">

                                            <form class="search pull-left" style="padding-right: 5px;" method="get" name="frm" action="Search">
                                                <label>Що</label> <input type="text" minlength="3" size="12px" name="osdch" value="${osdch}" id="osdch" align="middle" placeholder="Осд(Що)">

                                                <label>Куди</label> <input type="text" minlength="3" size="12px" name="osdk" value="${osdk}" id="osdk" align="middle" placeholder="Осд(Куди)">

                                                <label>Код виробу</label> <input type="text" size="12px" name="kiz" value="${kiz}" id="kiz" align="middle" placeholder="Код виробу">

                                                <input type="hidden" name="page" id="page" value="1">

                                                <input type="hidden" name="count" id="count" value="0">

                                                <input class="rawOnpage" type="hidden" name="pages" value="${pages}">

                                                <%                                            long curTime2 = System.currentTimeMillis();
                                                    String svi = new SimpleDateFormat("dd.MM.yyyy").format(curTime2);
                                                %>

                                                <input type="hidden" name="svi" id="svi" value="<%=svi%>">

                                                <button id="filtAccept" class="btn  btn-md btn-success" data-toggle="tooltip" data-placement="bottom" title="Виконати фільтр">
                                                    <span class="glyphicon glyphicon-ok"></span></button>

                                                <!--<input id="searchClear" class="btn btn-warning btn-md" type="button" value="Відмінити фільтр" onclick="window.location.href = 'Vp44150sqlController?action=list&page=1'" />-->
                                            </form>


                                            <a class="pull-left" href="Search?page=1&count=0&osdch=&osdk=&kiz=&svi=<%=svi%>&pages=${pages}">
                                                <button class="btn  btn-md btn-success" data-toggle="tooltip" data-placement="bottom" title="Відмінити фільтр">
                                                    <span class="glyphicon glyphicon-remove"></span></button></a>    
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="container-fluid" style="margin-bottom: 5px;">
                    <label style="margin-right: 5px; font-size: 15px;">Оберіть кількість записів на сторінці</label>
                    <select id="rowPerPage">
                        <option style="background-color: #58ba5c; color: white;" value="pages">${pages}</option>
                        <option value="15">15</option>
                        <option value="25">25</option>
                        <option value="50">50</option>
                        <option value="100">100</option>
                    </select>  
                </div>
                <div class="container-fluid"> 

                    <nav class="pag" >
                        <ul class="pagination">
                            <c:choose>
                                <c:when test="${page == 1}"><li><a class="disabled" href="Search?page=1&count=0&osdch=${osdch}&osdk=${osdk}&kiz=${kiz}&svi=${svi}&pages=${pages}">Перша</a></li></c:when> 
                                <c:otherwise><li><a class="a2" href="Search?page=1&count=0&osdch=${osdch}&osdk=${osdk}&kiz=${kiz}&svi=${svi}&pages=${pages}">Перша</a></li></c:otherwise>
                                </c:choose>

                            <c:choose>
                                <c:when test="${page <=1}"><li class="li1"><a class="disabled" href="Search?page=${page-1}&count=${count-pages}&osdch=${osdch}&osdk=${osdk}&kiz=${kiz}&svi=${svi}&pages=${pages}">
                                            <span class="glyphicon glyphicon-chevron-left"></span></a></li>
                                        </c:when> 
                                        <c:otherwise><li class="li1"><a class="a2" href="Search?page=${page-1}&count=${count-pages}&osdch=${osdch}&osdk=${osdk}&kiz=${kiz}&svi=${svi}&pages=${pages}">
                                            <span class="glyphicon glyphicon-chevron-left"></span></a></li></c:otherwise> 
                                    </c:choose>



                            <!--Добавление страниц к первой-->
                            <%-- <c:choose><c:when test="${page == 1}">
                                     <c:set var="lastPageNum" scope="request" value="${ empty param.page ? 6 : param.page + 4 }"/></c:when>
                                 <c:otherwise>--%>

                            <c:set var="firstPageNum" scope="request" value="${ param.page - 2 }"/>
                            <c:set var="lastPageNum" scope="request" value="${ empty param.page ? 3 : param.page + 2 }"/>
                            <%--</c:otherwise>
                        </c:choose>--%>
                            <c:forEach var="i" begin="${ firstPageNum > 0 ? firstPageNum : 1 }" 
                                       end="${ lastPageNum > size ? size : lastPageNum }">  
                                <c:choose>
                                    <c:when test="${i == page}"><li class="li1"><a class="a2" style="color: #2e6da4;" href="Search?page=${i}&count=${(i-1)*pages}&osdch=${osdch}&osdk=${osdk}&kiz=${kiz}&svi=${svi}&pages=${pages}"><b>${i}</b></a></li></c:when> 
                                    <c:otherwise><li class="li1"><a class="a2" href="Search?page=${i}&count=${(i-1)*pages}&osdch=${osdch}&osdk=${osdk}&kiz=${kiz}&svi=${svi}&pages=${pages}">${i}</a></li></c:otherwise> 
                                    </c:choose>    
                                </c:forEach>


                            <c:choose>
                                <c:when test="${page >= size}"><li class="li1"><a class="disabled" href=Search?page=${page+1}&count=${count+pages}&osdch=${osdch}&osdk=${osdk}&kiz=${kiz}&svi=${svi}&pages=${pages}>
                                            <span class="glyphicon glyphicon-chevron-right"></span></a></li></c:when> 
                                        <%--<c:otherwise><li><a class="a2" href=Search?page=${page+1}&osdch=${osdch}&osdk=${osdk}&kiz=${kiz}&svi=${svi}>Вперед</a></li></c:otherwise>--%>
                                        <c:otherwise><li><a class="a2" href=Search?page=${page+1}&count=${count+pages}&osdch=${osdch}&osdk=${osdk}&kiz=${kiz}&svi=${svi}&pages=${pages}>
                                            <span class="glyphicon glyphicon-chevron-right"></span></a></li></c:otherwise>
                                    </c:choose>

                            <%--<c:choose>
                            <c:when test="${page == counts}"><li><a class="disabled" href=Search?page=${counts}&osdch=${osdch}&osdk=${osdk}&kiz=${kiz}&svi=${svi}>Последняя</a></li></c:when> 
                            <c:otherwise><li><a class="a2" href=Search?page=${counts}&osdch=${osdch}&osdk=${osdk}&kiz=${kiz}&svi=${svi}>Последняя</a></li></c:otherwise>
                            </c:choose>--%>


                            <li><button id="excell" class="btn btn-success btn-md" data-toggle="tooltip" data-placement="right" title="XLS-файл.Експорт поточної сторінки" onclick="fnExcelReport2();"><span class="glyphicon glyphicon-list-alt"></span></button></li>
                        </ul>
                    </nav>
                </div>
                <div class="container-fluid">
                    <table border=1 acceptCharset="UTF-8" id="search" class="table table-striped table-bordered table-condensed iskra-table">

                        <thead class="iskra-thead">
                            <tr>
                                <!--<th>ОСД-тип(Що)</th>-->                    
                                <th colspan=2>Одиниця складова(деталь). Позначення. Технологічні маршрути виготовлення</th>
                                <th class="rowCenter" rowspan=2>Технологічний маршрут споживання "Що"-"Куди"</th>
                                <th class="rowCenter" rowspan=2>Код виробу</th>
                                <th colspan=2>Кількість</th>
                                <th class="rowCenter" rowspan=2>Строк впровадження повідомлення про зміну</th>
                            </tr>

                            <tr>
                                <!--<th>ОСД-тип(Що)</th>-->

                                <th>"Що"</th>
                                <!--<th>ОСД-залишок(Що)</th>-->
                                <!--<th>ОСД-тип(Куди)</th>-->
                                <th>"Куди"</th>
                                <!--<th>ОСД-залишок(Куди)</th>-->

                                <th>"Що" в "Куди"</th>
                                <th>На виріб</th>
                                <!--<th>Користувач</th>
                                <th>Дата завантаження</th>
                                <th>Мережева адреса користувача</th>
                                <th colspan=2>Редактирование-удаление</th>-->
                            </tr>
                        </thead>


                        <tbody>
                            <c:choose>
                                <c:when test="${not empty vp44150sqlS}">
                                    <c:forEach items="${vp44150sqlS}" var="vp44150sql" >
                                        <tr paint>

                                            <c:if test="${not empty vp44150sql.osdch}">
                                                <td rowspan="${vp44150sql.osdch_cnt}" paint>
                                                    <div class="stickyColumn"> 
                                                        <span class="test">
                                                            <c:out value="${vp44150sql.osdch}" />
                                                        </span>
                                                        <br>
                                                        <c:out value="${vp44150sql.naim}" /><br>


                                                        <c:if test="${not empty vp44150sql.nc}">
                                                            <c:set target="${grouper}" property="chunks" value="${vp44150sql.nc}" />
                                                            <c:out value="${grouper.chunks}"/>
                                                        </c:if>
                                                    </div>
                                                </td>
                                            </c:if>

                                            <c:if test="${not empty vp44150sql.osdk}">  
                                                <td rowspan="${vp44150sql.osdk_cnt}" paint>

                                                    <div class="stickyColumn">

                                                        <c:out value="${vp44150sql.osdk}" />

                                                        <br>
                                                        <c:out value="${vp44150sql.naimk}" /><br>


                                                        <c:if test="${not empty vp44150sql.ncK}">
                                                            <c:set target="${triadNcK}" property="chunks" value="${vp44150sql.ncK}" />
                                                            <c:out value="${triadNcK.chunks}"/>
                                                        </c:if>
                                                    </div>
                                                </td>
                                            </c:if>

                                            <td>
                                                <c:if test="${not empty vp44150sql.cp}">
                                                    <c:out value="/" />
                                                    <c:if test="${not empty vp44150sql.cp}">
                                                        <c:set target="${triadCp}" property="chunks" value="${vp44150sql.cp}" />
                                                        <c:out value="${triadCp.chunks}"/>
                                                    </c:if>
                                                </c:if>
                                            </td>

                                            <td>
                                                <c:out value="${vp44150sql.kiz}" />
                                            </td>

                                            <td>
                                                <fmt:formatNumber value="${vp44150sql.kol}" type="number"/>
                                            </td>

                                            <td>
                                                <fmt:formatNumber value="${vp44150sql.koliz}" type="number"/>
                                            </td>

                                            <td>
                                                <fmt:formatDate pattern="MM.yyyy" value="${vp44150sql.svi}" />
                                            </td>   
                                        </tr>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <tr paint>
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
                                <c:when test="${page == 1}"><li><a class="disabled" href="Search?page=1&count=0&osdch=${osdch}&osdk=${osdk}&kiz=${kiz}&svi=${svi}&pages=${pages}">Перша</a></li></c:when> 
                                <c:otherwise><li><a class="a2" href="Search?page=1&count=0&osdch=${osdch}&osdk=${osdk}&kiz=${kiz}&svi=${svi}&pages=${pages}">Перша</a></li></c:otherwise>
                                </c:choose>

                            <c:choose>
                                <c:when test="${page <=1}"><li class="li1"><a class="disabled" href="Search?page=${page-1}&count=${count-pages}&osdch=${osdch}&osdk=${osdk}&kiz=${kiz}&svi=${svi}&pages=${pages}">
                                            <span class="glyphicon glyphicon-chevron-left"></span></a></li></c:when> 
                                <c:otherwise><li class="li1"><a class="a2" href="Search?page=${page-1}&count=${count-pages}&osdch=${osdch}&osdk=${osdk}&kiz=${kiz}&svi=${svi}&pages=${pages}">
                                            <span class="glyphicon glyphicon-chevron-left"></span></a></li></c:otherwise> 
                                    </c:choose>



                            <!--Добавление страниц к первой-->
                            <%-- <c:choose><c:when test="${page == 1}">
                                     <c:set var="lastPageNum" scope="request" value="${ empty param.page ? 6 : param.page + 4 }"/></c:when>
                                 <c:otherwise>--%>

                            <c:set var="firstPageNum" scope="request" value="${ param.page - 2 }"/>
                            <c:set var="lastPageNum" scope="request" value="${ empty param.page ? 3 : param.page + 2 }"/>
                            <%--</c:otherwise>
                        </c:choose>--%>
                            <c:forEach var="i" begin="${ firstPageNum > 0 ? firstPageNum : 1 }" 
                                       end="${ lastPageNum > size ? size : lastPageNum }">  
                                <c:choose>
                                    <c:when test="${i == page}"><li class="li1"><a class="a2" style="color: #2e6da4;" href="Search?page=${i}&count=${(i-1)*pages}&osdch=${osdch}&osdk=${osdk}&kiz=${kiz}&svi=${svi}&pages=${pages}"><b>${i}</b></a></li></c:when> 
                                    <c:otherwise><li class="li1"><a class="a2" href="Search?page=${i}&count=${(i-1)*pages}&osdch=${osdch}&osdk=${osdk}&kiz=${kiz}&svi=${svi}&pages=${pages}">${i}</a></li></c:otherwise> 
                                    </c:choose>    
                                </c:forEach>


                            <c:choose>
                                <c:when test="${page >= size}"><li class="li1"><a class="disabled" href=Search?page=${page+1}&count=${count+pages}&osdch=${osdch}&osdk=${osdk}&kiz=${kiz}&svi=${svi}&pages=${pages}>
                                            <span class="glyphicon glyphicon-chevron-right"></span></a></li></c:when> 
                                        <%--<c:otherwise><li><a class="a2" href=Search?page=${page+1}&osdch=${osdch}&osdk=${osdk}&kiz=${kiz}&svi=${svi}>Вперед</a></li></c:otherwise>--%>
                                        <c:otherwise><li><a class="a2" href=Search?page=${page+1}&count=${count+pages}&osdch=${osdch}&osdk=${osdk}&kiz=${kiz}&svi=${svi}&pages=${pages}>
                                            <span class="glyphicon glyphicon-chevron-right"></span></a></li></c:otherwise>
                                    </c:choose>

                            <%--<c:choose>
                            <c:when test="${page == counts}"><li><a class="disabled" href=Search?page=${counts}&osdch=${osdch}&osdk=${osdk}&kiz=${kiz}&svi=${svi}>Последняя</a></li></c:when> 
                            <c:otherwise><li><a class="a2" href=Search?page=${counts}&osdch=${osdch}&osdk=${osdk}&kiz=${kiz}&svi=${svi}>Последняя</a></li></c:otherwise>
                            </c:choose>--%>


                            <li><button id="excell" class="btn btn-success btn-md" data-toggle="tooltip" data-placement="right" title="XLS-файл.Експорт поточної сторінки" onclick="fnExcelReport2();"><span class="glyphicon glyphicon-list-alt"></span></button></li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>



        <script  type="text/javascript" src="js/exportExcel_1.js"></script>
        <script  type="text/javascript" src="js/autocomplitKiz.js"></script>
        <script  type="text/javascript" src="js/table/jquery.stickytableheaders.js"></script>
        <script  type="text/javascript" src="js/table/StickyColumn.js"></script>
        <script  type="text/javascript" src="js/rawsOnPage.js"></script>  

        <script type="text/javascript">
                                $(function () {
                                    $("table").stickyTableHeaders();
                                });
        </script>


        <script  type="text/javascript" src="js/tree/treeKiz.js"></script>  



    </body>
</html>
