// inserts table into this 
//e.a. $("div").buildTable(jsonSource,headerJson); // appends table to each div
(function ($) {

    $.fn.updateTable = function update(_JsonSource) { // this === table  //must have

        _JsonSource = typeof _JsonSource === "string" ? "" : _JsonSource;
        var JsonSource = $.extend([], _JsonSource);

        var tbody = $(this).find("tbody");
        console.log($(this).is(".iskra-tableContainer"));
        $(tbody).empty();

        var tr = $("<tr>");

        if (JsonSource.length === 0) {
            var span_message = $("<span>").html("Інформація за вказаним контекстом відсутня");
            var clCnt = 0;
            $($(this).find("thead")).find("tr:first>th").toArray().forEach(function (item) {
                clCnt += ($(item).is("[colspan]") && $(item).attr("colspan") > 0) ?
                        1 * +$(item).attr("colspan") : 1;
            });//stretch message to occupy the whole columns length
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

    $.fn.createTable = function (_headerSettings, _options) {

        // for smooth page rendering
        var options = $.extend({
            "class": "table table-striped table-bordered table-condensed iskra-table",
            "display": "table"
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
        $(headerSettings.tr).toArray().forEach(tr_item => {
            var tr = $("<tr>");

            $(tr_item.th).toArray().forEach(th_item => {
                var th = $("<th>");
                $(th).html(th_item.name);

                if (th_item.hasOwnProperty("colspan")) {
                    $(th).attr({"colspan": th_item.colspan});
                }
                if (th_item.hasOwnProperty("rowspan")) {
                    $(th).attr({"rowspan": th_item.rowspan});
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
        $(this).append(table);
        return this;
    };
}(jQuery));
