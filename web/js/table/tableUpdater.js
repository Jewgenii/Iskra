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
        return this;
    };
}(jQuery));
