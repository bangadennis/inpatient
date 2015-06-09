<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp" %>
<%@ include file="template/localHeader.jsp"%>

<script type="text/javascript">
    (function ($) {
        $(document).ready(function() {
            $('#findpatient_table').dataTable({
                "sDom": 'T<"clear">frtip',
                "bSort":true,
                "bPaginate":true,
                "oTableTools": {
//                    "aButtons":  ['pdf']
                }
            });


            $('#patientSearch').keyup( function() {
                if( this.value.length >2 ){

                    DWRPatientService.findPatients(this.value, false, function(patients){
                        $("#data_table").dataTable().fnClearTable();
                        if(patients.length >0)
                        {
                            patients.forEach(function(patient){
                                $("#data_table").dataTable().fnAddData([
                                    patient.patientId,
                                    patient.givenName,
                                    patient.middleName,
                                    patient.familyName,
                                    patient.age,
                                    patient.gender,
                                    patient.birthdateString]);
                            });
                        }
                    });
                }
            });


            $('#data_table').dataTable({
                "fnRowCallback": function (nRow, aData, iDisplayIndex) {
                    // Bind click event
                    $(nRow).click(function() {
                        // alert( 'You clicked on '+aData+'\'s row' );
                        window.location="/openmrs/module/inpatient/processRequest.form?id="+aData[0];
                    });
                    return nRow;
                },
                "searching":false,
                "pagination":false
            });



        });

    }(jQuery));

</script>

<div class="form-group">
    <form class="col-md-5 form-horizontal">
        <label>Patient Search</label>
        <input type="text" id="patientSearch" class="form-control" placeholder="Enter patient Name or Patient Identifier">
    </form>
</div>



<table class="table table-condensed table-striped table-hover" id="data_table">
            <thead>
            <tr>

                <th style="width:10%">ID</th>
                <th style="width:10%">Given Name</th>
                <th style="width:10%">Middle Name</th>
                <th style="width:10%">Family Name</th>
                <th style="width:10%">Age</th>
                <th style="width:10%">Gender</th>
                <th style="width:10%">Birth Date</th>


            </tr>
            </thead>
            <tbody>

            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            </tbody>
</table>





<%--<table class="table table-striped table-responsive table-hover" id="findpatient_table">--%>
    <%--<thead>--%>
    <%--<tr>--%>
        <%--<th>First Name</th>--%>
        <%--<th>Middle Name</th>--%>
        <%--<th>Family Name</th>--%>
        <%--<th>Gender</th>--%>
        <%--<th>ADD</th>--%>
    <%--</tr>--%>
    <%--</thead>--%>
    <%--<tbody>--%>
    <%--<c:forEach var="patient" items="${patientList}" varStatus="status">--%>
        <%--<tr>--%>
            <%--<td>${patient.givenName}</td>--%>
            <%--<td>${patient.middleName}</td>--%>
            <%--<td>${patient.familyName}</td>--%>
            <%--<td>${patient.gender}</td>--%>

            <%--<td><a href="<c:url value='/module/inpatient/inpatient.form?id=${patient.patientId}' />">--%>
                <%--<button class="btn btn-success"><span class="glyphicon glyphicon-plus">Inpatient</span></button>--%>
            <%--</a> </td>--%>

        <%--</tr>--%>
    <%--</c:forEach>--%>
    <%--</tbody>--%>
<%--</table>--%>

<%@ include file="/WEB-INF/template/footer.jsp"%>