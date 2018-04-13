/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




    $(window).resize(() =>
    {
        var height = $("#backgroundHead").height();
        $(".stickyColumn").css({"top": height});
    });
    
    $(window).trigger('resize');