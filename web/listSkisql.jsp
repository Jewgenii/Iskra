
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head class="head">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <script language="javascript" type="text/javascript" src="js/exportExcel.js"></script>

        <title>Просмотр данных skisql</title>
        <style>
            <%@include file='css/table.css'%>
        </style>
        <%@include file="menu.jsp"%>
    </head>
    <body>
        <div class="container-fluid">
            <nav class="pag" role='pagination'>
                <ul class="pagination">
                    <c:choose>
                        <c:when test="${page == 1}"><li><a class="disabled" href="SkisqlController?action=list&page=1">Первая</a></li></c:when> 
                        <c:otherwise><li><a class="a2" href="SkisqlController?action=list&page=1">Первая</a></li></c:otherwise>
                        </c:choose>

                    <c:choose>
                        <c:when test="${page <=1}"><li class="li1"><a class="disabled" href=SkisqlController?action=list&page=${page-1}>Назад</a></li></c:when> 
                        <c:otherwise><li class="li1"><a class="a2" href=SkisqlController?action=list&page=${page-1}>Назад</a></li></c:otherwise> 
                        </c:choose>

                    <c:set var="firstPageNum" scope="request" value="${ param.page - 2 }"/>
                    <c:set var="lastPageNum" scope="request" value="${ empty param.page ? 3 : param.page + 2 }"/>
                    <c:forEach var="i" begin="${ firstPageNum > 0 ? firstPageNum : 1 }" 
                               end="${ lastPageNum > counts ? counts : lastPageNum }">
                        <li class="li1"><a class="a2" href="SkisqlController?action=list&page=${i}">${i}</a></li>
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
                        <c:when test="${page >= counts}"><li class="li1"><a class="disabled" href=SkisqlController?action=list&page=${page+1}>Вперед</a></li></c:when> 
                        <c:otherwise><li><a class="a2" href=SkisqlController?action=list&page=${page+1}>Вперед</a></li></c:otherwise>
                        </c:choose>

                    <c:choose>
                        <c:when test="${page == counts}"><li><a class="disabled" href=SkisqlController?action=list&page=${counts}>Последняя</a></li></c:when> 
                        <c:otherwise><li><a class="a2" href=SkisqlController?action=list&page=${counts}>Последняя</a></li></c:otherwise>
                        </c:choose>

                    <li><a>Страница №: ${page}</a></li>

                    <li><button class="btn btn-success btn-md" onclick="fnExcelReport();">Экспорт в excel</button></li>
                        <%--<li><input type="button" onclick="tableToExcel('testTable', 'W3C Example Table')" value="Export to Excel"></li>--%>
                </ul>
            </nav>
        </div>

        <script type="text/javascript">
            //$(function () {
            //	$("table").stickyTableHeaders();
            //});
        </script>
        <div class="container-fluid">
            <table border=1 id="tableList" class="table table-striped table-bordered" acceptCharset="UTF-8">
                <thead>

                    <tr>
                        <th>Номер замовлення</th>
                        <th>ОСД-індекс(ОСД-залишок)</th>
                        <th>Код виробу</th><th>Пріоритет</th>
                        <th>Номер ВПР</th><th>Найменування</th>
                        <th>Номер повідомлення</th>
                        <!--<th>Користувач</th>
                        <th>Дата завантаження</th>
                        <th>Мережева адреса користувача</th>
                        <th colspan=2>Редактирование-удаление</th>-->
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${skisqls}" var="skisql" >
                        <tr>
                            <td><c:out value="${skisql.nzak}" /></td>
                            <%--<td><c:out value="${skisql.osd_t}" /></td>--%>
                            <td><c:out value="${skisql.osd_c}${skisql.osd_r}" /></td>
                            <%--<td><c:out value="${skisql.osd_r}" /></td>--%>
                            <td><c:out value="${skisql.kiz}" /></td>
                            <td><c:out value="${skisql.pri}" /></td>
                            <td><c:out value="${skisql.nvp}" /></td>
                            <td><c:out value="${skisql.naim}" /></td>
                            <td><c:out value="${skisql.nizv}" /></td>
                            <%--<td><c:out value="${skisql.user}" /></td>
                            <td><fmt:formatDate pattern="dd.MM.yyyy" value="${skisql.datez}" /></td>
                            <td><c:out value="${skisql.user_ip}" /></td>
                            <td><a id="links" href="UpdCont?action=edit&kiz=<c:out value="${skisql.kiz}"/>">Изменить</a></td>
                            <td><a id="links" href="SkisqlController?action=delete&kiz=<c:out value="${skisql.kiz}"/>">Удалить</a></td>--%>
                        </tr>
                    </c:forEach>
                </tbody>

            </table>
        </div>
        <div class="container-fluid">
            <nav class="pag2" role='pagination'>
                <ul class="pagination">
                    <c:choose>
                        <c:when test="${page == 1}"><li><a class="disabled" href="SkisqlController?action=list&page=1">Первая</a></li></c:when> 
                        <c:otherwise><li><a class="a2" href="SkisqlController?action=list&page=1">Первая</a></li></c:otherwise>
                        </c:choose>

                    <c:choose>
                        <c:when test="${page <=1}"><li class="li1"><a class="disabled" href=SkisqlController?action=list&page=${page-1}>Назад</a></li></c:when> 
                        <c:otherwise><li class="li1"><a class="a2" href=SkisqlController?action=list&page=${page-1}>Назад</a></li></c:otherwise> 
                        </c:choose>

                    <c:set var="firstPageNum" scope="request" value="${ param.page - 2 }"/>
                    <c:set var="lastPageNum" scope="request" value="${ empty param.page ? 3 : param.page + 2 }"/>
                    <c:forEach var="i" begin="${ firstPageNum > 0 ? firstPageNum : 1 }" 
                               end="${ lastPageNum > counts ? counts : lastPageNum }">
                        <li class="li1"><a class="a2" href="SkisqlController?action=list&page=${i}">${i}</a></li>
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
                        <c:when test="${page >= counts}"><li class="li1"><a class="disabled" href=SkisqlController?action=list&page=${page+1}>Вперед</a></li></c:when> 
                        <c:otherwise><li><a class="a2" href=SkisqlController?action=list&page=${page+1}>Вперед</a></li></c:otherwise>
                        </c:choose>

                    <c:choose>
                        <c:when test="${page == counts}"><li><a class="disabled" href=SkisqlController?action=list&page=${counts}>Последняя</a></li></c:when> 
                        <c:otherwise><li><a class="a2" href=SkisqlController?action=list&page=${counts}>Последняя</a></li></c:otherwise>
                        </c:choose>

                    <li><a>Страница №: ${page}</a></li>

                    <li><button class="btn btn-success btn-md" onclick="fnExcelReport();">Экспорт в excel</button></li>
                        <%--<li><input type="button" onclick="tableToExcel('testTable', 'W3C Example Table')" value="Export to Excel"></li>--%>
                </ul>
            </nav>
        </div>
    </body>
</html>