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
        <link rel="stylesheet" href="css/tree/kizTree.css"/> 
        <link rel="stylesheet" href="plugins/jsTree/themes/default/style.min.css">
    </head>
    <body>
        <div id="menu"> <%@include file="menu.jsp" %></div>
        <div class="container-fluid iskra-filterContainer">
            <div class="panel">
                <h4 class="panel-heading"> Дерево ОС</h4>
                <div class="panel-body"> 
                    <span>  Одиниця складова(деталь)   </span>  
                    <input type="text" value="${osdch}"  data-field="osdch" autocomplete>
                    <span>  код виробу </span>  
                    <input type="text" value="${kiz}" data-field="kiz" autocomplete>
                    <button id='buildTree' clas='btn btn-md btn-success' title='Виконати фільтр'>
                        <span class="glyphicon glyphicon-ok"></span>
                    </button>
                </div>
            </div>
        </div>
        <div id="jstree"></div>

        <script type="text/javascript" src="plugins/jsTree/jstree.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {

                $("input[autocomplete]").autocomplete({
                    source: function (request, response)
                    {
                        var field = $(this.element).data("field");
                        $.post("AutocompleteVp44150sql", {"term": request.term, "field": field},
                                function (data) {
                                    response(data);
                                }, "json");
                    },
                    minLength: 2,
                    delay: 500
                });

                $("body").on("click", "#buildTree", function (e) {

                    var osdch = $("input[data-field=osdch]").val();
                    var kiz = $("input[data-field=kiz]").val();

                    var url = window.location.pathname;
                    $.jstree.destroy();

                    $('#jstree').jstree({
                        'core': {
                            "themes": {"stripes": true},
                            'data': {// accepts $.ajax object with its standart properties
                                method: "post",
                                url: url,
                                dataType: "json",
                                data: function (node) {
                                    if (node.id !== '#') {
                                        osdch = node.li_attr.osdch;
                                        kiz = node.li_attr.kiz;
                                    }
                                    return {"kiz": kiz, "osdch": osdch};
                                },
                                success: function (data) {
                                    console.log(data);
                                    return data;
                                },
                                error: function (node) {
                                    // destroy node or entire tree???
                                    //$.jstree.destroy();
                                }
                            }
                        }
                    });

                });

            });
        </script>
    </body>
</html>


