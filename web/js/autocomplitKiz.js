
$(document).ready(function() {
     $(function() {
         
         $("#kiz").autocomplete({     
             source : function(request, response) {
                 //$("#loading").show();
                 $(".ui-helper-hidden-accessible").empty();
                 
               $.ajax({
                    url : "AutocompleteKizController",
                    type : "GET",
                    data : {
                           term : request.term   
                           
                    },
                    dataType : "json",
                    success : function(data) {
                          response(data);
                       //   $("#loading").hide();
                    }
             });
          }        
      });
   });
});