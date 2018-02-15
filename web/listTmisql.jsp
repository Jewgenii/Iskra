<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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

        <title>Перегляд даних tmisql</title>      
    </head>
    <body>
        <script type="text/javascript">
            $(function () {
                $("table").stickyTableHeaders();
            });
        </script>
        <div class="container-fluid">
            <nav class="pag" role='pagination'>
                <ul class="pagination">
                    <c:choose>
                        <c:when test="${page == 1}"><li><a class="disabled" href="TmisqlController?action=list&page=1">Первая</a></li></c:when> 
                        <c:otherwise><li><a class="a2" href="TmisqlController?action=list&page=1">Первая</a></li></c:otherwise>
                        </c:choose>

                    <c:choose>
                        <c:when test="${page <=1}"><li class="li1"><a class="disabled" href=TmisqlController?action=list&page=${page-1}>Назад</a></li></c:when> 
                        <c:otherwise><li class="li1"><a class="a2" href=TmisqlController?action=list&page=${page-1}>Назад</a></li></c:otherwise> 
                        </c:choose>

                    <c:set var="firstPageNum" scope="request" value="${ param.page - 2 }"/>
                    <c:set var="lastPageNum" scope="request" value="${ empty param.page ? 3 : param.page + 2 }"/>
                    <c:forEach var="i" begin="${ firstPageNum > 0 ? firstPageNum : 1 }" 
                               end="${ lastPageNum > counts ? counts : lastPageNum }">
                        <li class="li1"><a class="a2" href="TmisqlController?action=list&page=${i}">${i}</a></li>
                        </c:forEach>

                    <c:choose>
                        <c:when test="${page >= counts}"><li class="li1"><a class="disabled" href=TmisqlController?action=list&page=${page+1}>Вперед</a></li></c:when> 
                        <c:otherwise><li><a class="a2" href=TmisqlController?action=list&page=${page+1}>Вперед</a></li></c:otherwise>
                        </c:choose>

                    <li><a>Страница №: ${page}</a></li>

                    <li><button id="excel2" class="btn btn-success btn-md" onclick="fnExcelReport3();">Экспорт в excel</button></li>

                </ul>
            </nav>
        </div>


        <div class="container-fluid">
            <table border=1 id="tableList" class="table table-striped table-bordered" acceptCharset="UTF-8">
                <thead id="backgroundHeadTmi">

                    <tr>
                        <th>Одиниця складова(деталь).Позначення.<br> 
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
                        <c:when test="${not empty tmisqls}">
                            <c:forEach items="${tmisqls}" var="tmisql" >
                                <tr>
                                    <td><c:out value="${tmisql.osdch}" /><br>
                                        <c:out value="${tmisql.naim}" /></td>
                                    <td><c:out value="${tmisql.ncm}" /></td>
                                    <td><fmt:formatDate pattern="MM.yyyy" value="${tmisql.svi}" /></td>
                                    <td><c:out value="${tmisql.nizv}" /></td>
                                 
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
                        <c:when test="${page == 1}"><li><a class="disabled" href="TmisqlController?action=list&page=1">Первая</a></li></c:when> 
                        <c:otherwise><li><a class="a2" href="TmisqlController?action=list&page=1">Первая</a></li></c:otherwise>
                        </c:choose>

                    <c:choose>
                        <c:when test="${page <=1}"><li class="li1"><a class="disabled" href=TmisqlController?action=list&page=${page-1}>Назад</a></li></c:when> 
                        <c:otherwise><li class="li1"><a class="a2" href=TmisqlController?action=list&page=${page-1}>Назад</a></li></c:otherwise> 
                        </c:choose>

                    <c:set var="firstPageNum" scope="request" value="${ param.page - 2 }"/>
                    <c:set var="lastPageNum" scope="request" value="${ empty param.page ? 3 : param.page + 2 }"/>
                    <c:forEach var="i" begin="${ firstPageNum > 0 ? firstPageNum : 1 }" 
                               end="${ lastPageNum > counts ? counts : lastPageNum }">
                        <li class="li1"><a class="a2" href="TmisqlController?action=list&page=${i}">${i}</a></li>
                        </c:forEach>


                    <c:choose>
                        <c:when test="${page >= counts}"><li class="li1"><a class="disabled" href=TmisqlController?action=list&page=${page+1}>Вперед</a></li></c:when> 
                        <c:otherwise><li><a class="a2" href=TmisqlController?action=list&page=${page+1}>Вперед</a></li></c:otherwise>
                        </c:choose>

                    <%-- <c:choose>
                         <c:when test="${page == counts}"><li><a class="disabled" href=TmisqlController?action=list&page=${counts}>Последняя</a></li></c:when> 
                         <c:otherwise><li><a class="a2" href=TmisqlController?action=list&page=${counts}>Последняя</a></li></c:otherwise>
                         </c:choose>--%>

                    <li><a>Страница №: ${page}</a></li>

                    <li><button class="btn btn-success btn-md" onclick="fnExcelReport3();">Экспорт в excel</button></li>
                </ul>
            </nav>
        </div>
    </body>
</html>