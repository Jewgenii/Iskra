/*$( function() {
    
    var availableTags = [
      "ИК6","ИК61","ИК62","ИК63","ИК64","ИК65",
      "ТРК"
    ];
    $( "#kiz" ).autocomplete({
      source: availableTags
    });
  } );*/
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