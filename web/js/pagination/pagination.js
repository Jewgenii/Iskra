
(function ($) {
    const undf = "undefined";
    $.fn.updatePagination = function (data) {

        localStorage.setItem("paginationLimit", JSON.stringify(data.limit));
        $("select.iskra-rowsOnPage").val(data.limit);

        var toPrevVal = data.offset - data.limit;
        var toNextVal = data.offset + data.limit;

        var li_toFirstPage = $("li.toFirstPage").attr({"offset": 0, "limit": data.limit});
        var li_toPrevPage = $("li.toPrevPage").attr({"offset": toPrevVal, "limit": data.limit});
        var li_toNextPage = $("li.toNextPage").attr({"offset": toNextVal, "limit": data.limit});

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
            limit = JSON.parse(limit);
        } catch (e) {
            localStorage.removeItem('paginationLimit');
            limit = 50;
        }
        data = {"offset": 0, "limit": limit};

        var options = $.extend({
            "display": "none",
            "class": "pagination iskra-pagination"
        }, _options);

        initRowOnPage(data.limit);

        var ul = $("<ul>").addClass(options.class).css({"display": options.display});

        var span =
                [
                    $("<span>").addClass("glyphicon").html("Перша"),
                    $("<span>").addClass("glyphicon glyphicon-chevron-left"),
                    $("<span>").addClass("glyphicon glyphicon-chevron-right")
                ];

        var li_toFirstPage = $("<li>").attr({"offset": 0, "limit": data.limit, "class": "toFirstPage"}).append(span[0]);
        var li_toPrevPage = $("<li>").attr({"offset": data.offset - data.limit, "limit": data.limit, "class": "toPrevPage"}).append(span[1]);
        var li_toNextPage = $("<li>").attr({"offset": data.offset + data.limit, "limit": data.limit, "class": "toNextPage"}).append(span[2]);

        $(ul).append(li_toFirstPage);
        $(ul).append(li_toPrevPage);
        $(ul).append(li_toNextPage);

        return $(this).toArray().forEach((el) => {
            $(el).append($(ul).clone());
        });
    };

    function initRowOnPage(limit) {

        var div = $("div.rowsOnPage");
        var label = $("<label>").addClass("pull-left").html("Оберіть кількість записів на сторінці");
        var select = $("<select>").addClass("pull-left iskra-rowsOnPage");

        var optionsGroup = [
            $("<option>").attr({"value": 15}).html(15),
            $("<option>").attr({"value": 25}).html(25),
            $("<option>").attr({"value": 50}).html(50),
            $("<option>").attr({"value": 100}).html(100)
        ];
        $.each(optionsGroup, (index, value) => {
            select.append(value);
        });
        div.append(label).append(select);
    }

}(jQuery));




