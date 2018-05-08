
(function ($) {

    var jsonTableHead = {
        tr: [
            {
                th: [
                    {
                        name: 'Одиниця складова(деталь)',
                        colspan: 2
                    },
                    {
                        name: 'Номер повідомлення про зміну',
                        rowspan: 2
                    }
                ]
            },
            {
                th: [
                    {
                        name: 'Позначення'
                    },
                    {
                        name: 'Найменування'
                    }
                ]
            }
        ]
    };



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
                //  $(".iskra-filterContainer").iskra_filters(filters);
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

    const getLimOffset = (element) => {

        var offset = $(element).attr("offset");
        var limit = $(element).attr("limit");
        if (offset < 0)
            offset = 0;
        return JSON.stringify({"offset": offset, "limit": limit});
    };

    $(document).ready(() => {

        $(".iskra-paginationContainer").createPagination();
      //  $(".iskra-tableContainer").createTable(jsonTableHead);
      

        $(".table").stickyTableHeaders(); //initialize table header
        $(window).trigger('resize'); // initialize row content top property accrodingly to table header size

        var pag = localStorage.getItem("paginationLimit");
        if (!pag)
            pag = 50;
        var paginationObj = JSON.stringify({offset: 0, limit: pag});

        sendData(paginationObj);

        $(document).on("click", "ul.iskra-pagination>li", function () {
            if (!$(this).is("[disabled]")) 
            {
                var pagination = getLimOffset($(this));
                if (pagination){
                    sendData(pagination, "filtersObj");
                }
                    
            }
        });

        $(document).on("change", "select.iskra-rowsOnPage", function () {
            var liGroup = $("ul.iskra-pagination>li");
            var limit = $(this).find("option:selected").val();
            $(liGroup).attr("limit", limit);
            var pagination = JSON.stringify({"offset": 0, "limit": limit});
            sendData(pagination, "filtersObj");
        });
    });
}(jQuery));




