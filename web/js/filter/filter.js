/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function ($) {

    $.extend(
            {
                getFilters: (elements) => {
                    var filters = new Array();
                    $(elements).each(function (nm, vl)
                    {
                        if ($(vl).is("[data-filter=true]"))
                        {
                            var type = $(vl).data("type");//filter type
                            var field = $(vl).data("field");//column name
                            var inputValue = $(vl).val();
                            var valArr = $.extend([], inputValue.toString().split(','));
                            filters.push({"type": type, "field": field, "values": valArr});
                        }
                    });
                    return  JSON.stringify(filters);
                }
            });
}(jQuery));




