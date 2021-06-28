$(function () {

    $('#save-note').click(function()
    {
        const data = JSON.stringify($("#noteForm").serializeArray());
        $.ajax({
            method: "POST",
            url: '/user/',
            data: data,
            success: function() {},
            dataType: "json"
        });
    });
})