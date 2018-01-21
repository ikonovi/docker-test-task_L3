$(document).ready(function () {
   $('#startPerformance').click(function () {
       $('#startPerformance').prop('disabled', true);
       var formData = JSON.stringify($(document.forms.performanceForm).serializeArray());
       $.ajax({url: "startPerformance",
           type: 'POST',
           data: { content: formData},
           success: function(result){
           }
       })
   });

    $('#startAuto').click(function () {
        $('#startAuto').prop('disabled', true);
        var formData = JSON.stringify($(document.forms.autoForm).serializeArray());
        $.ajax({url: "startAuto",
            type: 'POST',
            data: { content: formData},
            success: function(result){
            }
        })
    });

    $('#stopPerformance').click(function () {
        $('#stopPerformance').prop('disabled', true);
        $.ajax({url: "stopPerformance",
            type: 'POST',
            success: function(result){
            }
        })
    });

    $('#stopAuto').click(function () {
        $('#stopPerformance').prop('disabled', true);
        $.ajax({url: "stopPerformance",
            type: 'POST',
            success: function(result){
            }
        })
    });
});

var UPDATE_INTERVAL = 1000;

setInterval(function getStatus() {
    $.ajax({url: "status",
        type: 'POST',
        async: false,
        success: function(result){
            var status = $.parseJSON(result);
            var running = status['running'];
            var stopping = status['stopping'];
            $('#startPerformance').prop('disabled', running);
            $('#stopPerformance').prop('disabled', stopping || !running);
            $('#startAuto').prop('disabled', running);
            $('#stopAuto').prop('disabled', stopping || !running);
        }
    });
    return getStatus;
}(), UPDATE_INTERVAL);