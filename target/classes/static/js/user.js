$(function() {

    $('#save-note').click(function()
    {
        var dataArray = $("form").serializeArray();
        var jsonToSent = {};

        console.log("SER" + dataArray)

        $.map(dataArray, function(n, i){
            jsonToSent[n['name']] = n['value'];
        });

        $.ajax({
            method: "POST",
            url: "/user/",
            data: JSON.stringify(jsonToSent),
            success: function(){},
            dataType: "json",
            contentType : "application/json"
        });
        //window.location.reload(true);
    });

    $('#delete-all-notes').click(function ()
    {
        $.ajax({
           method: "DELETE",
           url: "/user/",
           success: function (){}
        });

        //window.location.reload(true);
    });
});