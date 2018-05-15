/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function ($) {

    $.extend({
        getFilters: (elements) => {
            var filters = new Array();

            $(elements).each(function (propName, propValue)
            {
                if ($(propValue).is("[data-filter=true]"))
                {
                    var type = $(propValue).data("type");//filter type
                    var field = $(propValue).data("field");//column name
                    var tmp = $(propValue).val();
                    var arrValues = new Array();
                    
                    var val = $.extend([], tmp.toString().split(','));

                    filters.push({"type": type, "field": field, "values": val});
                }
            });
            return  JSON.stringify(filters);
        }});

    $.fn.iskra_filters = function (_data, _options)
    {
        var data = $.extend([
            {
                caption: "none", //text 
                name: "none", // greater then or less or equels
                type: "none", // select,input
                autoCompleteSource: "", // remote or local
                values: [] // values in the filter
            }
        ], _data);

        var opstions = $.extend({
            display: "static"
        }, _options);

        var panelBody = $("div").addClass("panel-body");

        data = [
            {
                caption: "test1",
                name: "greaterthen",
                type: "button",
                values: [
                ]
            },
            {
                caption: "test2",
                name: "greaterthen",
                type: "input",
                values: [
                ]
            },
            {
                caption: "test3",
                name: "greaterthen",
                type: "select",
                values: [
                    1, 2
                ]
            }
        ];

        $(data).toArray().forEach(elem =>
        {
            var filter = $("<span>").css({"background-color": "red"});
            var span = $("<span>").html(elem.caption);
            var input = $("<" + elem.type + ">").attr({id: elem.name});

            switch (elem.type)
            {
                case 'select':
                    $(elem.values).toArray().forEach(vl => {
                        var option = $("<option>").val(vl).html(vl);
                        $(input).append(option);
                    });
                    break;
                case 'input':
                    var option = $("<input>").val(elem.values[0]).html(elem.values[0]);
                    $(input).append(option);

                    if (elem.autoCompleteSource !== "undefined") {
                        $("#" + input.name).autocomplete({
                            source: (request, response) =>
                            {
                                $.post("AutocompleteSourceController", {"term": request.term, "filters": ""},
                                        function (data) {
                                            response(data);
                                        }, "json");
                            },
                            minLength: 2,
                            delay: 1000
                        });
                    }
                    break;
            }
            $(panelBody).append(span).append(input);
        }

        );
    };

}(jQuery));




