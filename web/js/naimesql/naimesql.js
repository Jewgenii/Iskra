
(function ($) {
    const sendData = function (pagination, filters)
    {
        $.ajax({
            method: "post",
            url: window.location.pathname,
            dataType: "json",
            data: {
                "pagination": pagination,
                "filters": filters
            },
            success: function (data) {

                var paginationObject = JSON.parse(data.pagination);
                var talbeContent = JSON.parse(data.tableContent);
                //var filters = JSON.parse(data.filters);

                $(".iskra-paginationContainer").updatePagination(paginationObject);
                $(".iskra-tableContainer table").updateTable(talbeContent);
                //$(".iskra-filterContainer").updateFilters(filters);
            },
            beforeSend: function () {
                var div = $("<div>").addClass("iskra-Iconloader");
                var img = $("<img>").attr("src", "/" + window.location.pathname.split('/')[1] + "/img/spinner/30px.gif");
                $(div).css({"top": "50%", "left": "50%", "display": "none", "position": "absolute"})
                        .append(img)
                        .appendTo("body");
                $(div).fadeIn("medium");
            },
            error: (e) => {
                console.log(e);
            },
            complete: () => {
                $(".iskra-Iconloader").fadeOut("medium", () => $(".iskra-Iconloader").remove());
            }
        });
    };

    $(document).ready(() => {

        $(".table").stickyTableHeaders(); //initialize table header
        $(window).trigger('resize'); // initialize row content top property accrodingly to table header size

        var pagLimit = sessionStorage.getItem("paginationLimit");
        var pagOffset = sessionStorage.getItem("paginationOffset");

        pagLimit = !pagLimit ? 50 : pagLimit;
        pagOffset = !pagOffset ? 0 : pagOffset;

        var pagination = JSON.stringify({offset: pagOffset, limit: pagLimit});

        var filters = $.getFilters("input");
        sendData(pagination, filters);

        $(document).on("click", "ul.iskra-pagination>li[offset]", function () {
            if (!$(this).is("[disabled]"))
            {
                var pagination = $.getLimitOffset(this);
                var filters = $.getFilters("input");
                sendData(pagination, filters);
            }
        });

        $(document).on("change", "select.iskra-rowsOnPage", function () {
            var liGroup = $("ul.iskra-pagination>li");
            var limit = $(this).find("option:selected").val();
            $(liGroup).attr("limit", limit);
            var pagination = JSON.stringify({"offset": 0, "limit": limit});
             var filters = $.getFilters("input");
            sendData(pagination, filters);
        });

        $("input[autocomplete]").autocomplete({
            source: function (request, response)
            {
                var field = $(this.element).data("field");
                $.post("AutocompleteNaimesql", {"term": request.term, "field": field},
                        function (data) {
                            response(data);
                        }, "json");
            },
            minLength: 3,
            delay: 500
        });


        //  $("body").on("click", ".excell", excel.fnExcelReport);

    });

}(jQuery));




