
(function ($) {
    const undf = "undefined";
    $.fn.updatePagination = function (_data) {

        var data = $.extend({
            limit: 50,
            offset: 0
        }, _data);

        localStorage.setItem("paginationLimit", JSON.stringify(data.limit));

        var li_toFirstPage = $("#toFirstPage").attr({"offset": 0, "limit": data.limit, id: "toFirstPage"});
        var li_toPrevPage = $("#toPrevPage").attr({"offset": data.offset - data.limit, "limit": data.limit, id: "toPrevPage"});
        var li_toNextPage = $("#toNextPage").attr({"offset": data.offset + data.limit, "limit": data.limit, id: "toNextPage"});

        $("ul.iskra-pagination").css({"display": "inline-block"});
        if (data.offset === 0) {
            $(li_toFirstPage).attr({"disabled": "disabled"});
            $(li_toPrevPage).attr({"disabled": "disabled"});
        } else {
            $(li_toFirstPage).removeAttr("disabled");
            $(li_toPrevPage).removeAttr("disabled");
        }
        return this;
    };

    $.fn.createPagination = function (_options) {
        var data = null;
        try {
            var limit = localStorage.getItem("paginationLimit");
            data = JSON.parse(limit);
        } catch (e) {
            localStorage.removeItem('paginationLimit');
        }

        if (!data) {
            var paginationData = {limit: 50, offset: 0};
            data = paginationData;
            localStorage.setItem("paginationData", JSON.stringify(paginationData));
        }
        // for smooth page rendering
        var options = $.extend({
            "display": "none",
            "class": "pagination iskra-pagination"
        }, _options);

        rowsOnPage(data.limit);

        var ul = $("<ul>").addClass(options.class).css({"display": options.display});

        var span =
                [
                    $("<span>").addClass("glyphicon").html("Перша"),
                    $("<span>").addClass("glyphicon glyphicon-chevron-left"),
                    $("<span>").addClass("glyphicon glyphicon-chevron-right")
                ];

        var li_toFirstPage = $("<li>").attr({"offset": 0, "limit": data.limit, id: "toFirstPage"}).append(span[0]);
        var li_toPrevPage = $("<li>").attr({"offset": data.offset - data.limit, "limit": data.limit, id: "toPrevPage"}).append(span[1]);
        var li_toNextPage = $("<li>").attr({"offset": data.offset + data.limit, "limit": data.limit, id: "toNextPage"}).append(span[2]);


        $(li_toFirstPage).attr({"disabled": "disabled"});
        $(li_toPrevPage).attr({"disabled": "disabled"});

        $(ul).append(li_toFirstPage);
        $(ul).append(li_toPrevPage);
        $(ul).append(li_toNextPage);

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




