<%-- 
    Document   : listVp44150sql
    Created on : 25.09.2017, 13:02:49
    Author     : Sergey Nikonenko
--%>

<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<!DOCTYPE html>
<html>

    <head class="head">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <%@include file="menu.jsp"%>
        <!-- <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"> --> 
        <!--<script language="javascript" type="text/javascript" src="js/exportExcel_1_1.js"></script>-->
        <!--<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>-->
        <style>
            <%@include file='css/jquery-ui.css' %>     
        </style>
        <!--<script language="javascript" type="text/javascript" src="js/jquary_ui.js"></script>-->
        <script language="javascript" type="text/javascript" src="js/exportExcel.js"></script>
        <!--<script language="javascript" type="text/javascript" src="js/jquery-3.2.1.min.js"></script>-->
        <script language="javascript" type="text/javascript" src="js/autocomplitKiz.js"></script>
        <script language="javascript" type="text/javascript" src="js/jquery.stickytableheaders.js"></script>

        <title>Просмотр данных vp44150sql</title>

        <style>
            <%@include file='css/table.css'%>
            <%@include file="css/rawSpanStyle.css"%>
        </style>

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
                            <form  class="search" method="get" name="frm" action="Search">
                                <label>Що</label> <input type="text" size="12px" name="osdch" id="osdch" align="middle" placeholder="Осд(Що)">

                                <label>Куди</label> <input type="text" size="12px" name="osdk" id="osdk" align="middle" placeholder="Осд(Куди)">

                                <label>Код виробу</label> <input id="kiz" type="text" size="12px" name="kiz" class="kiz"  align="middle" placeholder="Код виробу">
                                <%--<img id="loading" width="20" height="20" style="display: none" src="${pageContext.servletContext.contextPath}/img/load2.gif" />--%>
                               
                                <!--<th colspan=2></th>-->                            
                                <input type="hidden" name="page" id="page" value="1">

                                <input type="hidden" name="count" id="count" value="0">
                                  <%                                             
                                            long curTime2 = System.currentTimeMillis();
                                            String svi = new SimpleDateFormat("dd.MM.yyyy").format(curTime2);
                                        %>

                                        <input type="hidden" name="svi" id="svi" value="<%=svi%>">

                                <button class="btn  btn-md btn-success" data-toggle="tooltip" data-placement="bottom" title="Виконати фільтр">
                                    <span class="glyphicon glyphicon-ok"></span></button>

                               <!-- <input id="searchClear" class="btn btn-warning btn-md" type="button" value="Відмінити фільтр" onclick="window.location.href = 'Vp44150sqlController?action=list&page=1'" />-->
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
                <ul class="pagination" >
                    <c:choose>
                        <c:when test="${page == 1}"><li><a class="disabled" href="Vp44150sqlController?action=list&page=1">Перша</a></li></c:when> 
                        <c:otherwise><li><a class="a2" href="Vp44150sqlController?action=list&page=1">Перша</a></li></c:otherwise>
                        </c:choose>

                    <c:choose>
                        <c:when test="${page <=1}"><li class="li1"><a class="disabled" href=Vp44150sqlController?action=list&page=${page-1}><span class="glyphicon glyphicon-chevron-left"></span></a></li></c:when> 
                        <c:otherwise><li class="li1"><a class="a2" href=Vp44150sqlController?action=list&page=${page-1}><span class="glyphicon glyphicon-chevron-left"></span></a></li></c:otherwise> 
                        </c:choose>

                    <c:set var="firstPageNum" scope="request" value="${ param.page - 2 }"/>
                    <c:set var="lastPageNum" scope="request" value="${ empty param.page ? 3 : param.page + 2 }"/>
                    <c:forEach var="i" begin="${ firstPageNum > 0 ? firstPageNum : 1 }" 
                               end="${ lastPageNum > counts ? counts : lastPageNum }">
                        <c:choose>
                            <c:when test="${i == page}"><li class="li1"><a class="a2" style="color: #2e6da4;" href="Vp44150sqlController?action=list&page=${i}"><b>${i}</b></a></li></c:when>
                            <c:otherwise> <li class="li1"><a class="a2" href="Vp44150sqlController?action=list&page=${i}">${i}</a></li></c:otherwise>
                            </c:choose>  
                        </c:forEach>

                    <c:choose>
                        <c:when test="${page >= counts}"><li class="li1"><a class="disabled" href=Vp44150sqlController?action=list&page=${page+1}><span class="glyphicon glyphicon-chevron-right"></span></a></li></c:when> 
                        <c:otherwise><li><a class="a2" href=Vp44150sqlController?action=list&page=${page+1}><span class="glyphicon glyphicon-chevron-right"></span></a></li></c:otherwise>
                        </c:choose>

                    <%-- <c:choose>
                         <c:when test="${page == counts}"><li><a class="disabled" href=Vp44150sqlController?action=list&page=${counts}>Последняя</a></li></c:when> 
                         <c:otherwise><li><a class="a2" href=Vp44150sqlController?action=list&page=${counts}>Последняя</a></li></c:otherwise>
                         </c:choose> --%>

                
                    <li><button id="excell" class="btn btn-success btn-md" data-toggle="tooltip" data-placement="right" title="XLS-файл.Експорт поточної сторінки" onclick="fnExcelReport();"><span class="glyphicon glyphicon-list-alt"></span></button></button></li>
                        <%--<li><input type="button" onclick="tableToExcel('testTable', 'W3C Example Table')" value="Export to Excel"></li>--%>
                </ul>
            </nav>

            <table  id="tableList" class="table table-striped table-bordered " acceptCharset="UTF-8">
                <thead id="backgroundHead">

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
                        <c:when test="${not empty vp44150sqls}">
                            <c:forEach items="${vp44150sqls}" var="vp44150sql" >
                                <tr paint>
                                    <%-- <td rowspan="${vp44150sql.osdch_cnt}">--%>
                                    <c:if test="${not empty vp44150sql.osdch}">
                                        <td rowspan="${vp44150sql.osdch_cnt}" paint>
                                            <div class="stickyColumn"> 
                                                <c:out value="${vp44150sql.osdch}" /><br>
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
                                            <%--  <td rowspan="${vp44150sql.osdk_cnt}">--%>
                                            <div class="stickyColumn">
                                                <c:out value="${vp44150sql.osdk}" /><br>
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
                                    <%--<td><c:out value="${skisql.user}" /></td>
                                    <td><fmt:formatDate pattern="dd.MM.yyyy" value="${skisql.datez}" /></td>
                                    <td><c:out value="${skisql.user_ip}" /></td>
                                    <td><a id="links" href="UpdCont?action=edit&kiz=<c:out value="${skisql.kiz}"/>">Изменить</a></td>
                                    <td><a id="links" href="SkisqlController?action=delete&kiz=<c:out value="${skisql.kiz}"/>">Удалить</a></td>--%>
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
            <nav class="pag2">
                <ul class="pagination">
                    <c:choose>
                        <c:when test="${page == 1}"><li><a class="disabled" href="Vp44150sqlController?action=list&page=1">Перша</a></li></c:when> 
                        <c:otherwise><li><a class="a2" href="Vp44150sqlController?action=list&page=1">Перша</a></li></c:otherwise>
                        </c:choose>

                    <c:choose>
                        <c:when test="${page <=1}"><li class="li1"><a class="disabled" href=Vp44150sqlController?action=list&page=${page-1}><span class="glyphicon glyphicon-chevron-left"></span></a></li></c:when> 
                        <c:otherwise><li class="li1"><a class="a2" href=Vp44150sqlController?action=list&page=${page-1}><span class="glyphicon glyphicon-chevron-left"></span></a></li></c:otherwise> 
                        </c:choose>

                    <c:set var="firstPageNum" scope="request" value="${ param.page - 2 }"/>
                    <c:set var="lastPageNum" scope="request" value="${ empty param.page ? 3 : param.page + 2 }"/>
                    <c:forEach var="i" begin="${ firstPageNum > 0 ? firstPageNum : 1 }" 
                               end="${ lastPageNum > counts ? counts : lastPageNum }">
                        
                        
                    <c:choose>
                            <c:when test="${i == page}"><li class="li1"><a class="a2" style="color: #2e6da4;" href="Vp44150sqlController?action=list&page=${i}"><b>${i}</b></a></li></c:when>
                            <c:otherwise> <li class="li1"><a class="a2" href="Vp44150sqlController?action=list&page=${i}">${i}</a></li></c:otherwise>
                            </c:choose>   
                    
                    
                    </c:forEach>

                    <%--<% 
                           for (int i = Integer.parseInt(request.getParameter("page")) - 2; 
                                   i <= Integer.parseInt(request.getParameter("page")) + 2; 
                                   i++) {    if(i > 0){
                       %>

                <li class="li1"><a class="a2" href=SkisqlController?action=list&page=<%=i%>><%=i%></a></li>
                    <%
                        } 
                            }
                    %>--%>

                    <c:choose>
                        <c:when test="${page >= counts}"><li class="li1"><a class="disabled" href=Vp44150sqlController?action=list&page=${page+1}><span class="glyphicon glyphicon-chevron-right"></span></a></li></c:when> 
                        <c:otherwise><li><a class="a2" href=Vp44150sqlController?action=list&page=${page+1}><span class="glyphicon glyphicon-chevron-right"></span></a></li></c:otherwise>
                        </c:choose>

                    <%--<c:choose>
                        <c:when test="${page == counts}"><li><a class="disabled" href=Vp44150sqlController?action=list&page=${counts}>Последняя</a></li></c:when> 
                        <c:otherwise><li><a class="a2" href=Vp44150sqlController?action=list&page=${counts}>Последняя</a></li></c:otherwise>
                        </c:choose>--%>

                   
                    <li><button id="excell" class="btn btn-success btn-md" data-toggle="tooltip" data-placement="right" title="XLS-файл.Експорт поточної сторінки" onclick="fnExcelReport();"><span class="glyphicon glyphicon-list-alt"></span></button></li>
                        <%--<li><input type="button" onclick="tableToExcel('testTable', 'W3C Example Table')" value="Export to Excel"></li>--%>
                </ul>
            </nav>
        </div>
                <script language="javascript" type="text/javascript" src="js/StickyColumnList.js"></script>
    </body>
</html>
