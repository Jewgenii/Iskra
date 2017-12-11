<%-- 
    Document   : user
    Created on : 26.07.2017, 14:01:44
    Author     : u27brvz17
--%>

<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <style>
        <%@include file='css/style.css' %>
    </style>

    <head class="head">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <%@include file="menu.jsp" %>


    </head>
    <title>Добавление</title>
    <body>

        <form method="POST" action='SkisqlController' name="frmAddSkisql">
            <table id="add">
                <tr class="add">
                    <td class="add2"><label>Номер замовлення: </label></td> 
                    <td><input type="text" name="nzak" value="<c:out value="${skisql.nzak}" />" /> </td></tr>
                <tr class="add">
                    <td class="add2"><label>ОСД-тип : </label></td> 
                    <td><input type="text" name="osd_t" value="<c:out value="${skisql.osd_t}" />" /> </td></tr>
                <tr class="add">
                    <td class="add2"><label>ОСД-індекс : </label></td> 
                    <td><input type="text" name="osd_c" value="<c:out value="${skisql.osd_c}" />" /> </td></tr>
                <tr class="add">
                    <td class="add2"><label>ОСД-залишок : </label></td> 
                    <td><input type="text" name="osd_r" value="<c:out value="${skisql.osd_r}" />" /> </td></tr>
                <tr class="add">
                    <td class="add2"><label>Код виробу : </label></td> 
                    <td><input type="text" name="kiz" value="<c:out value="${skisql.kiz}" />" /> </td></tr>
                <tr class="add">
                    <td class="add2"><label>Пріоритет : </label></td> 
                    <td><input type="text" name="pri" value="<c:out value="${skisql.pri}" />" /> </td></tr>
                <tr class="add">
                    <td class="add2"><label>Номер ВПР : </label></td> 
                    <td><input type="text" name="nvp" value="<c:out value="${skisql.nvp}" />" /> </td></tr>
                <tr class="add">
                    <td class="add2"><label>Найменування : </label></td> 
                    <td><input type="text" name="naim" value="<c:out value="${skisql.naim}" />" /> </td></tr>
                <tr class="add">
                    <td class="add2"><label>Номер повідомлення : </label></td> 
                    <td><input type="text" name="nizv" value="<c:out value="${skisql.nizv}" />"/> </td></tr>
                        <%-- <tr class="add">
                             <td class="add2"><label>Користувач : </label></td> 
                             <td><input type="text" name="user" value="<c:out value="${skisql.user}" />" /> </td></tr>
                         <tr class="add">
                             <td class="add2"><label>Дата завантаження : </label></td> 
                             <td><input type="date" name="datez" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${skisql.datez}" />" /></td></tr> 
                         <tr class="add">
                             <td class="add2"s><label>Мережева адреса : </label></td> 
                             <td><input type="text" name="user_id" value="<c:out value="${skisql.user_ip}" />" /></td></tr> --%>
                <td class="add2"><input type="submit" value="Сохранить" /></td>
            </table>  
        </form>
    </body>
</html>
