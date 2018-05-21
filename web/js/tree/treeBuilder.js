/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function ($) {

    $.fn.addTreeNode = function (_data, _options) {
        const node = $(this);

        var data = $.extend({
            osdch: "",
            kiz: ""
        }, _data);

        var options = $.extend({
            url: window.location.pathname,
            method: "post"
        }, _options);

        $.ajax({
            method: options.method,
            url: options.url,
            dataType: "json",
            data: {
                "osdch": data.osdch,
                "kiz": data.kiz
            }, success: function (data) {

            }

        });


    };

    $.fn.initizlizeTree = function (_data, _options) {
        var data = $.extend({
            osdch: "",
            kiz: ""
        }, _data);

    };
}(jQuery));




