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
        <link rel="stylesheet" href="css/table/iskra-table.css"/>
    </head>
    <body>
        <div id="menu"> <%@include file="menu.jsp" %></div>
        <div class="container-fluid iskra-filterContainer">
            <div class="panel">
                <h4 class="panel-heading"> test panel </h4>
                <div class="panel-body"> 
                    <span>  позначення   </span>  <input type="text" data-type="like" data-field="osd"  data-filter="true" autocomplete>
                    <span>  найменування </span>  <input type="text" data-type="like" data-field="naim" data-filter="true" autocomplete>
                </div>
            </div>
        </div>

        <div class="container-fluid">
            <div class="pull-left iskra-paginationContainer">
                <ul class="pagination iskra-pagination">
                    <li offset = 0 limit class="toFirstPage">
                        <span class="glyphicon">Перша</span>
                    </li>
                    <li offset limit class="toPrevPage">
                        <span class="glyphicon glyphicon-chevron-left"></span>
                    </li>
                    <li offset limit class="toNextPage">
                        <span class="glyphicon glyphicon-chevron-right"></span>
                    </li>
                    <li>
                        <button class="btn btn-success excell" data-toggle="tooltip" data-placement="right" title="XLS-файл.Експорт поточної сторінки">
                            <span class="glyphicon glyphicon-list-alt"></span>
                        </button>
                    </li>
                </ul>
            </div>
            <div class="pull-right rowsOnPage" style="margin-top:30px">
                <label>Оберіть кількість записів на сторінці</label>
                <select class="iskra-rowsOnPage">
                    <option value="15">15</option>
                    <option value="25">25</option>
                    <option value="50">50</option>
                    <option value="100">100</option>
                </select>
            </div>
        </div>
        <div class="container-fluid iskra-tableContainer">
            <table class="table table-striped table-bordered table-condensed iskra-table">
                <thead>
                    <tr>
                        <th colspan="2">Одиниця складова(деталь)</th>
                        <th rowspan="2">Номер повідомлення про зміну</th>
                    </tr>
                    <tr>
                        <th>Позначення</th>
                        <th>Найменування</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div> 

        <div class="iskra-paginationContainer container-fluid">
            <ul class="pagination iskra-pagination">
                <li offset = 0 limit class="toFirstPage">
                    <span class="glyphicon">Перша</span>
                </li>
                <li offset limit class="toPrevPage">
                    <span class="glyphicon glyphicon-chevron-left"></span>
                </li>
                <li offset limit class="toNextPage">
                    <span class="glyphicon glyphicon-chevron-right"></span>
                </li>
                <li>
                    <button class="btn btn-success btn-md excell" data-toggle="tooltip" data-placement="right" title="XLS-файл.Експорт поточної сторінки">
                        <span class="glyphicon glyphicon-list-alt"></span>
                    </button>
                </li>
            </ul>
        </div>
        <script  type="text/javascript" src="js/exportExcel.js"></script>
        <script  type="text/javascript" src="js/table/jquery.stickytableheaders.js"></script>
        <script  type="text/javascript" src="js/table/StickyColumn.js"></script>
        <script  type="text/javascript" src="js/table/tableUpdater.js"></script>
        <script  type="text/javascript" src="js/filter/filter.js"></script>
        <script  type="text/javascript" src="js/pagination/paginationUpdater.js"></script>
        <script  type="text/javascript" src="js/naimesql/naimesql.js"></script>
    </body>
</html>
