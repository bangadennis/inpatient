$(document).ready(function() {

    $('#admission_table').dataTable({
        "sDom": 'T<"clear">frtip',
        "bSort":true,
        "bPaginate":true,
        "oTableTools": {
            "aButtons":  ['pdf']
        }
    });

});