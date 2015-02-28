$("#dialog").dialog({
   autoOpen: false,
   modal: true,
   buttons : {
        "Confirm" : function() {
            alert("You have confirmed!");            
        },
        "Cancel" : function() {
          $(this).dialog("close");
        }
      }
    });

$("#callConfirm").on("click", function(e) {
    e.preventDefault();
    $("#dialog").dialog("open");
});
