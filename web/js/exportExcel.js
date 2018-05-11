
(function ($) {


    $.fn.ExcelReport = function ()
    {
        var tab_text = "<table border='2px'><tr bgcolor='#FFFFFF'>";
        var j = 0;
        var tab = $("table"); // id of table

        for (j = 2; j < tab.rows.length; j++)
        {
            tab_text = tab_text + tab.rows[j].innerHTML + "</tr>";
        }

        tab_text = tab_text + "</table>";
        tab_text = tab_text.replace(/<A[^>]*>|<\/A>/g, "");
        tab_text = tab_text.replace(/<img[^>]*>/gi, "");
        tab_text = tab_text.replace(/<input[^>]*>|<\/input>/gi, "");
        var uri = 'data:application/vnd.ms-excel,';
        var a = document.createElement('a');
        var d = new Date();
        var mm = d.getMonth() + 1;
        a.setAttribute("href", uri + '\uFEFF' + encodeURIComponent(tab_text));
        a.setAttribute('download', "CSXL03010_" +
                d.getDate() + "." +
                mm + "." +
                d.getFullYear() + "_" +
                d.getHours() + "." +
                d.getMinutes() + "." +
                d.getSeconds() + '.xls');
        document.body.appendChild(a);
        a.click();
    }
}
(jQuery));

//fnExcelReport();