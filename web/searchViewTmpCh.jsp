<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:useBean id="triadTmiNc" class="Bean.TriadBeanTmiNc">
    <jsp:setProperty name="triadTmiNc" property="delimiter" value="-"/>
    <jsp:setProperty name="triadTmiNc" property="size" value="3"/>
</jsp:useBean>


<jsp:useBean id="triadTmpCp" class="Bean.TriadBeanCp">
    <jsp:setProperty name="triadTmpCp" property="delimiter" value="-"/>
    <jsp:setProperty name="triadTmpCp" property="size" value="3"/>
</jsp:useBean>

<!DOCTYPE html>
<html>
    <head class="head">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="menu.jsp"%>
        <style>
            <%@include file='css/jquery-ui.css' %> 
            <%@include file='css/table.css'%>
            <%@include file='css/rawSpanStyle.css'%>
        </style>
        <!--<script language="javascript" type="text/javascript" src="js/jquary_ui.js"></script>-->

        <!--<script language="javascript" type="text/javascript" src="js/jquery-3.2.1.min.js"></script>-->
        <script language="javascript" type="text/javascript" src="js/autocomplitKiz.js"></script>
        <script language="javascript" type="text/javascript" src="js/jquery.stickytableheaders.js"></script>
        <script language="javascript" type="text/javascript" src="js/exportExcel_Tmp.js"></script>

        <title>Перегляд даних tmpsql</title>      
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
                            <div class="row">
                                <form  class="search pull-left" style="padding-right: 5px;" method="get" name="frm" action="SearchTmpCh">
                                    <label>Позначення</label> <input type="text" minlength="3" size="12px" name="osdch" id="osdch" value="${osdch}" align="middle" placeholder="ОСД-Що">

                                    <label>Позначення</label> <input type="text" minlength="3" size="12px" name="osdk" id="osdk" value="${osdk}" align="middle" placeholder="ОСД-Куди">

                                    <label>Маршрут</label> <input id="cp" type="text" size="12px" name="cp" class="cp" value="${cp}" align="middle" placeholder="Тех. маршрут">
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

                                    <!--<input id="searchClear" class="btn btn-warning btn-md" type="button" value="Відмінити фільтр" onclick="window.location.href = 'TmpsqlChController?action=list&page=1'" />-->
                                </form>

                                <a class="pull-left" href="SearchTmpCh?page=1&count=0&osdch=&osdk=&cp=&svi=<%=svi%>">
                                    <button class="btn  btn-md btn-success" data-toggle="tooltip" data-placement="bottom" title="Відмінити фільтр">
                                        <span class="glyphicon glyphicon-remove"></span></button></a>     

                            </div>
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
            <nav class="pag" role='pagination'>
                <ul class="pagination">
                    <c:choose>
                        <c:when test="${page == 1}"><li><a class="disabled" href="SearchTmpCh?page=1&count=0&osdch=${osdch}&osdk=${osdk}&cp=${cp}&svi=${svi}">Перша</a></li></c:when> 
                        <c:otherwise><li><a class="a2" href="SearchTmpCh?page=1&count=0&osdch=${osdch}&osdk=${osdk}&cp=${cp}&svi=${svi}">Перша</a></li></c:otherwise>
                        </c:choose>

                    <c:choose>
                        <c:when test="${page <=1}"><li class="li1"><a class="disabled" href="SearchTmpCh?page=${page-1}&count=${count-100}&osdch=${osdch}&osdk=${osdk}&cp=${cp}&svi=${svi}"><span class="glyphicon glyphicon-chevron-left"></span></a></li></c:when> 
                        <c:otherwise><li class="li1"><a class="a2" href="SearchTmpCh?page=${page-1}&count=${count-100}&osdch=${osdch}&osdk=${osdk}&cp=${cp}&svi=${svi}"><span class="glyphicon glyphicon-chevron-left"></span></a></li></c:otherwise> 
                            </c:choose>

                    <c:set var="firstPageNum" scope="request" value="${ param.page - 2 }"/>
                    <c:set var="lastPageNum" scope="request" value="${ empty param.page ? 3 : param.page + 2 }"/>
                    <c:forEach var="i" begin="${ firstPageNum > 0 ? firstPageNum : 1 }" 
                               end="${ lastPageNum > size ? size : lastPageNum }">  


                        <c:choose>
                            <c:when test="${i == page}"><li class="li1"><a class="a2" style="color: #2e6da4;" href="SearchTmpCh?page=${i}&count=${(i-1)*100}&osdch=${osdch}&osdk=${osdk}&cp=${cp}&svi=${svi}"><b>${i}</b></a></li>   </c:when> 
                            <c:otherwise><li class="li1"><a class="a2" href="SearchTmpCh?page=${i}&count=${(i-1)*100}&osdch=${osdch}&osdk=${osdk}&cp=${cp}&svi=${svi}">${i}</a></li></c:otherwise> 
                            </c:choose>    


                    </c:forEach>

                    <c:choose>
                        <c:when test="${page >= size}"><li class="li1"><a class="disabled" href="SearchTmpCh?page=${page+1}&count=${count+100}&osdch=${osdch}&osdk=${osdk}&cp=${cp}&svi=${svi}"><span class="glyphicon glyphicon-chevron-right"></span></a></li></c:when> 

                        <c:otherwise><li><a class="a2" href="SearchTmpCh?page=${page+1}&count=${count+100}&osdch=${osdch}&osdk=${osdk}&cp=${cp}&svi=${svi}"><span class="glyphicon glyphicon-chevron-right"></span></a></li></c:otherwise>
                            </c:choose>




                    <li><button id="excel2" class="btn btn-success btn-md" data-toggle="tooltip" data-placement="right" title="XLS-файл.Експорт поточної сторінки" onclick="fnExcelReport4();"><span class="glyphicon glyphicon-list-alt"></span></button></li>

                </ul>
            </nav>
        </div>


        <div class="container-fluid">
            <table border=1 id="tableList" class="table table-striped table-bordered" acceptCharset="UTF-8">
                <thead id="backgroundSearchHead">

                    <tr>
                        <!--<th>ОСД-тип(Що)</th>-->                    
                        <th colspan=2>Одиниця складова(деталь). Позначення</th>
                        <th class="rowCenter" rowspan=2>Технологічний маршрут споживання "Що"-"Куди"</th>
                        <th class="rowCenter" rowspan=2>Строк впровадження повідомлення про зміну</th>
                        <th class="rowCenter" rowspan=2>Номер повідомлення про зміну</th>
                    </tr>

                    <tr>
                        <!--<th>ОСД-тип(Що)</th>-->

                        <th>"Що"</th>
                        <!--<th>ОСД-залишок(Що)</th>-->
                        <!--<th>ОСД-тип(Куди)</th>-->
                        <th>"Куди"</th>
                        <!--<th>ОСД-залишок(Куди)</th>-->


                        <!--<th>Користувач</th>
                        <th>Дата завантаження</th>
                        <th>Мережева адреса користувача</th>
                        <th colspan=2>Редактирование-удаление</th>-->
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${not empty tmpsqlChS}">
                            <c:forEach items="${tmpsqlChS}" var="tmpsqlCh" >
                                <tr paint>

                                    <c:if test="${not empty tmpsqlCh.osdch}">

                                        <td rowspan="${tmpsqlCh.osdch_cnt}" paint>
                                            <div class="stickyColumn">
                                                <c:out value="${tmpsqlCh.osdch}" /><br>
                                                <c:out value="${tmpsqlCh.naim_ch}" /><br>
                                                <c:if test="${not empty tmpsqlCh.nc_ch}">
                                                    <c:set target="${triadTmiNc}" property="chunks" value="${tmpsqlCh.nc_ch}" />
                                                    <c:out value="${triadTmiNc.chunks}"/>
                                                </c:if>  
                                            </div>                                        
                                        </td>

                                    </c:if>


                                    <c:if test="${not empty tmpsqlCh.osdk}">

                                        <td rowspan="${tmpsqlCh.osdk_cnt}" paint>
                                            <div class="stickyColumn">
                                                <c:out value="${tmpsqlCh.osdk}" /><br>
                                                <c:out value="${tmpsqlCh.naim_k}" /><br>
                                                <c:if test="${not empty tmpsqlCh.nc_k}">
                                                    <c:set target="${triadTmiNc}" property="chunks" value="${tmpsqlCh.nc_k}" />
                                                    <c:out value="${triadTmiNc.chunks}"/>
                                                </c:if>
                                            </div>
                                        </td>  
                                    </c:if>

                                    <td>

                                        <c:if test="${not empty tmpsqlCh.cp}">
                                            <c:out value="/" />

                                            <c:if test="${not empty tmpsqlCh.cp}">
                                                <c:set target="${triadTmpCp}" property="chunks" value="${tmpsqlCh.cp}" />
                                                <c:out value="${triadTmpCp.chunks}"/>
                                            </c:if>

                                        </c:if>  

                                    </td>
                                    <%--<td><c:out value="${tmisql.nc}" /></td>--%>
                                    <td><fmt:formatDate pattern="MM.yyyy" value="${tmpsqlCh.svi}" />
                                    </td>

                                    <td><c:out value="${tmpsqlCh.nizv}" />
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
                        <c:when test="${page == 1}"><li><a class="disabled" href="SearchTmpCh?page=1&count=0&osdch=${osdch}&osdk=${osdk}&cp=${cp}&svi=${svi}">Перша</a></li></c:when> 
                        <c:otherwise><li><a class="a2" href="SearchTmpCh?page=1&count=0&osdch=${osdch}&osdk=${osdk}&cp=${cp}&svi=${svi}">Перша</a></li></c:otherwise>
                        </c:choose>

                    <c:choose>
                        <c:when test="${page <=1}"><li class="li1"><a class="disabled" href="SearchTmpCh?page=${page-1}&count=${count-100}&osdch=${osdch}&osdk=${osdk}&cp=${cp}&svi=${svi}"><span class="glyphicon glyphicon-chevron-left"></span></a></li></c:when> 
                        <c:otherwise><li class="li1"><a class="a2" href="SearchTmpCh?page=${page-1}&count=${count-100}&osdch=${osdch}&osdk=${osdk}&cp=${cp}&svi=${svi}"><span class="glyphicon glyphicon-chevron-left"></span></a></li></c:otherwise> 
                            </c:choose>

                    <c:set var="firstPageNum" scope="request" value="${ param.page - 2 }"/>
                    <c:set var="lastPageNum" scope="request" value="${ empty param.page ? 3 : param.page + 2 }"/>
                    <c:forEach var="i" begin="${ firstPageNum > 0 ? firstPageNum : 1 }" 
                               end="${ lastPageNum > size ? size : lastPageNum }">  
                        <c:choose>
                            <c:when test="${i == page}"><li class="li1"><a class="a2" style="color: #2e6da4;" href="SearchTmpCh?page=${i}&count=${(i-1)*100}&osdch=${osdch}&osdk=${osdk}&cp=${cp}&svi=${svi}"><b>${i}</b></a></li>   </c:when> 
                            <c:otherwise><li class="li1"><a class="a2" href="SearchTmpCh?page=${i}&count=${(i-1)*100}&osdch=${osdch}&osdk=${osdk}&cp=${cp}&svi=${svi}">${i}</a></li></c:otherwise> 
                            </c:choose>     
                        </c:forEach>

                    <c:choose>
                        <c:when test="${page >= size}"><li class="li1"><a class="disabled" href="SearchTmpCh?page=${page+1}&count=${count+100}&osdch=${osdch}&osdk=${osdk}&cp=${cp}&svi=${svi}"><span class="glyphicon glyphicon-chevron-right"></span></a></li></c:when> 

                        <c:otherwise><li><a class="a2" href="SearchTmpCh?page=${page+1}&count=${count+100}&osdch=${osdch}&osdk=${osdk}&cp=${cp}&svi=${svi}"><span class="glyphicon glyphicon-chevron-right"></span></a></li></c:otherwise>
                            </c:choose>



                    <li><button id="excel2" class="btn btn-success btn-md" data-toggle="tooltip" data-placement="right" title="XLS-файл.Експорт поточної сторінки" onclick="fnExcelReport4();"><span class="glyphicon glyphicon-list-alt"></span></button></li>
                </ul>
            </nav>
        </div>
        <script language="javascript" type="text/javascript" src="js/StickyColumn.js"></script>
    </body>
</html>