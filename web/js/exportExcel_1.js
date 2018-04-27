function fnExcelReport2()
{
    var tab_text = "<table border='2px'><tr bgcolor='#FFFFFF'>";
    // var textRange; 
    var j = 0;
    tab = document.getElementById('search'); // id of table

    for (j = 2; j < tab.rows.length; j++)
    {
        tab_text = tab_text + tab.rows[j].innerHTML + "</tr>";
        //tab_text=tab_text+"</tr>";
    }

    tab_text = tab_text + "</table>";
    tab_text = tab_text.replace(/<A[^>]*>|<\/A>/g, "");
    tab_text = tab_text.replace(/<img[^>]*>/gi, "");
    tab_text = tab_text.replace(/<input[^>]*>|<\/input>/gi, "");

   /* var ua = window.navigator.userAgent;
    var msie = ua.indexOf("MSIE "); 

    if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./))      // If Internet Explorer
    {
        txtArea1.document.open("txt/html","replace");
        txtArea1.document.write(tab_text);
        txtArea1.document.close();
        txtArea1.focus(); 
        sa=txtArea1.document.execCommand("SaveAs",true,"Say Thanks to Sumit.xls");
}
    else                 //other browser not tested on IE 11
        sa = window.open('data:application/vnd.ms-excel,' + encodeURIComponent(tab_text));  

    return (sa);
}*/
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
//fnExcelReport2();