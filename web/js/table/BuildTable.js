// inserts table into this 
//e.a. $("div").buildTable(jsonSource,headerJson); // appends table to each div
(function ($) {

    $.fn.updateTable = function update(_JsonSource) { // this === table  //must have

        _JsonSource = typeof _JsonSource === "string" ? "" : _JsonSource;
        var JsonSource = $.extend([], _JsonSource);

        var tbody = $(this).find("tbody");
        $(tbody).empty();

        var tr = $("<tr>");

        if (JsonSource.length === 0) {
            var fistTheadRowColumns = $(this).find("thead>tr:first>th");
            var span_message = $("<span>").html("Інформація за вказаним контекстом відсутня");
            var clCnt = 0;
            $.each(fistTheadRowColumns, function (propName, propValue) // get length of the columns
            {
                clCnt += ($(propValue).is("[colspan]") && $(propValue).attr("colspan") > 0) ? $(propValue).attr("colspan") : 1;
            });
            $(tr).append($("<td>").attr({"colspan": clCnt, "align": "center"}).append(span_message));//must have header column count in colspan prop
            $(tbody).append(tr);

        } else {
            for (var i = 0; i < JsonSource.length; i++) {
                tr = $("<tr>");
                $.each(JsonSource[i], (propertyName, propertyValue) => {
                    var td = $("<td>").append($("<span>").html(propertyValue));
                    $(tr).append(td);
                });
                $(tbody).append(tr);
            }
        }
        return $(this).css({"display": "table"});
    };



 // depracated 
    $.fn.createTable = function (_headerSettings, _options) {

        // for smooth page rendering
        var options = $.extend({
            "class": "table table-striped table-bordered table-condensed iskra-table",
            "display": "none"
        }, _options);
        var headerSettings = $.extend({
            tr: [
                {
                    th: [
                        {
                            name: 'No name header',
                            colspan: 1 //columnsLength
                        }
                    ]
                }
            ]
        }, _headerSettings);
        var tbody = $("<tbody>");
        //head BEGIN
        var thead = $("<thead>").addClass("iskra-thead");
        $.each(headerSettings.tr, (tr_name, tr_value) => {
            var tr = $("<tr>");

            $.each(tr_value.th, (th_name, th_value) => {
                var th = $("<th>");
                $(th).html(th_value.name);

                if (th_value.hasOwnProperty("colspan")) {
                    $(th).attr({"colspan": th_value.colspan});
                }
                if (th_value.hasOwnProperty("rowspan")) {
                    $(th).attr({"rowspan": th_value.rowspan});
                }
                $(tr).append(th);
            });
            $(thead).append(tr);
        });
        //head END
        var table = $("<table>")
                .addClass(options.class)
                .css({"display": options.display})
                .append(thead)
                .append(tbody);

        return $(this).append(table);
    };
    
}(jQuery));
