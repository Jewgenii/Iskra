
(function ($) {

    $.extend({
        getLimitOffset: function (element) {
            var offset = $(element).attr("offset");
            var limit = $(element).attr("limit");
            offset = offset < 0 ? 0 : offset;
            limit = limit < 0 ? 50 : limit;
            
            return JSON.stringify({"offset": offset, "limit": limit});
        }
    });

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

}(jQuery));




