/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function ($) {
    const undf = "undefined";

    $.fn.iskra_filters = function (_data) {

        var data = $.extend([
            {
                caption:"none",//text 
                name:"none",// greater then or less or equels
                //type:"none", // > , < , >= , between
                values:[] // values in the filter
            }
        ], _data);
        
        rowsOnPage(data.limit);
        var ul = $("<ul>").addClass("pagination iskra-pagination pull-right");

        var span =
                [
                    $("<span>").addClass("glyphicon").html("Перша"),
                    $("<span>").addClass("glyphicon glyphicon-chevron-left"),
                    $("<span>").addClass("glyphicon glyphicon-chevron-right")
                ];

        var li_first = $("<li>").attr({"offset": 0, "limit": dataSettings.limit}).append(span[0]);
        var li_second = $("<li>").attr({"offset": dataSettings.offset - dataSettings.limit, "limit": dataSettings.limit}).append(span[1]);
        var li_last = $("<li>").attr({"offset": dataSettings.offset + dataSettings.limit, "limit": dataSettings.limit}).append(span[2]);

        if (dataSettings.offset === 0) {
            $(li_first).attr({"disabled": "disabled"});
            $(li_second).attr({"disabled": "disabled"});
        }

        $(ul).append(li_first);
        $(ul).append(li_second);
        $(ul).append(li_last);

        $(this).empty();
        return $(this).toArray().forEach((el) => {
            $(el).append($(ul).clone());
        });
    };

    function  rowsOnPage(limit) {
        var div = $("div.rowsOnPage");
        div.empty();
        var label = $("<label>").addClass("pull-left").html("Оберіть кількість записів на сторінці");
        var select = $("<select>").addClass("pull-left iskra-rowsOnPage");

        var optionsGroup = [
            $("<option>").attr({"value": 15}).html(15),
            $("<option>").attr({"value": 25}).html(25),
            $("<option>").attr({"value": 50}).html(50),
            $("<option>").attr({"value": 100}).html(100)
        ];

        optionsGroup.forEach((element) => {
            var el_val = $(element).val();

            if (+el_val === +limit)
            {
                $(element).attr({"selected": "selected"})
                        .css({"background-color": "#58ba5c", "color": "white"});
            }
            select.append(element);
        });

        div.append(label).append(select);
    }

}(jQuery));




