/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(() =>
        {
            $("table tbody").on("click", "tr",
                    function () {

                        //  $(this).css({"background-color": "blue"});
                        var url = "/" + window.location.pathname.split('/')[1] + "/kizTreeController";
                        var val = $(this).find(".test").html();
                        var param = {"osd": val};
                        if (val)
                            $.post(url, param, function (data) {
                                console.log(data);
                            });
                    });

        });
