
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

    const collectData = (element) => {
        if ($(element).attr("disabled")) {
            return;
        }
        var offset = $(element).attr("offset");
        var limit = $(element).attr("limit");
        if (offset < 0) {
            offset = 0;
        }
        var pagination = JSON.stringify({"offset": offset, "limit": limit});
        return pagination;
    };
    const sendData = function (pagination, filters)
    {
        return new Promise(function (resolve, reject)
        {
            $.ajax({
                method: "post",
                url: window.location.pathname,
                dataType: "json",
                data: {
                    "pagination": pagination,
                    "filters": filters,
                    "action": "getTable"
                },
                success: function (data) {
                    var paginationObject = JSON.parse(data.pagination);
                    var talbeContent = JSON.parse(data.tableContent);

                    $(".iskra-paginationContainer").page_paginate(paginationObject);
                    $(".iskra-tableContainer").buildTable(talbeContent, jsonTableHead);
                    resolve(data); //Event handler for success of the Promise
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
                    //reject();//Event handler for failure of the Promise
                },
                complete: () => {
                    $(".iskra-Iconloader").fadeOut("medium", () => $(".iskra-Iconloader").remove());
                    afterSentData();
                }
            });
        });
    };

    const afterSentData = (data) => {
        $(".table").stickyTableHeaders(); //initialize table header
        $(window).trigger('resize'); // initialize row content top property accrodingly to table header size
    };

    $(document).ready(() => {
        sendData()
                .then((data) =>
                {
                    afterSentData(data);
                })
                .catch(p => {
                    console.log(p);
                });
        $(document).on("click", "ul.iskra-pagination>li", function () {
            var pagination = collectData($(this));
            if (typeof pagination !== "undefined")
            {
                sendData(pagination, "").then((data) => afterSentData());
            }
        });
        $(document).on("change", "select.iskra-rowsOnPage", function () {
            var liGroup = $("ul.iskra-pagination>li");
            var limit = $(this).find("option:selected").val();
            $(liGroup).attr("limit", limit);
            var pagination = JSON.stringify({"offset": 0, "limit": limit});
            sendData(pagination, "").then((data) => afterSentData());

        });
    });
}(jQuery) );

       


