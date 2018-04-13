/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function ($) {
                            $(document).ready(function () {
                                $('ul.dropdown-menu [data-toggle=dropdown]').on('click', function (event) {
                                    event.preventDefault();
                                    event.stopPropagation();
                                    $(this).parent().siblings().removeClass('open');
                                    $(this).parent().toggleClass('open');
                                });
                            });
                        })(jQuery);

