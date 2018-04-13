/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(() =>
{
    $("#rowPerPage").on("change", () =>
    {
        var pages = $("#rowPerPage>option:selected").text();
        //var pages = 15;
        var form = $("form.search.pull-left");

        var input = $("input.rawOnpage").attr(
                {"value": pages}
        );
        $(input).appendTo(form);
        document.getElementById("filtAccept").click();
    });
});

