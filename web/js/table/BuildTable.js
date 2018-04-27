
(function ($) {

    $.fn.buildTable = function (_JsonSource, headerData, _options) {

        _JsonSource = typeof _JsonSource === "string" ? "" : _JsonSource;

        var options = $.extend({
            "class": "table table-striped table-bordered table-condensed iskra-table"
        }, _options);

        var JsonSource = $.extend([], _JsonSource);
        var columnsLength = 0;
        $.each(JsonSource[0], (propertyName, propertyValue) => {
            columnsLength++;
        });

        var HeaderSettings = $.extend({
            tr: [
                {
                    th: [
                        {
                            name: 'No name header',
                            colspan: columnsLength
                        }
                    ]
                }
            ]
        }, headerData);

        var thead = $("<thead>").addClass("iskra-thead");
        $(HeaderSettings.tr).toArray().forEach(tr_item => {
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

        var tbody = $("<tbody>");
        var tr = $("<tr>");

        if (JsonSource.length === 0) {
            var span_message = $("<span>").html("Інформація за вказаним контекстом відсутня");
            var clCnt = 0;
            $(thead).find("tr:first>th").toArray().forEach(function (item) {
                clCnt += ($(item).is("[colspan]") && $(item).attr("colspan") > 0) ?
                        1 * +$(item).attr("colspan") : 1;
            });//stretch message to occupy the whole columns length
            $(tr).append($("<td>").attr({"colspan": clCnt, "align": "center"}).append(span_message));//must have header column count in colspan prop
            $(tbody).append(tr);
        }

        for (var i = 0; i < JsonSource.length; i++) {
            tr = $("<tr>");
            $.each(JsonSource[i], (propertyName, propertyValue) => {
                var td = $("<td>").append($("<span>").html(propertyValue));
                $(tr).append(td);
            });
            $(tbody).append(tr);
        }
        var table = $("<table>")
                .addClass(options.class)
                .append(thead)
                .append(tbody);
        
        $(this).empty();
        $(this).append(table);
        return this;
    };
}(jQuery));
