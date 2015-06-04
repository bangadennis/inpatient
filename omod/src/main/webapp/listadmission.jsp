<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp" %>
<%@ include file="template/localHeader.jsp"%>


<script type="text/javascript">

    (function ($) {

        $(document).ready(function() {
            $('#admission_table').dataTable({
                "sDom": 'T<"clear">frtip',
                "bSort":true,
                "bPaginate":true,
                "oTableTools": {
//                    "aButtons":  ['pdf']
                }
            });

        });

    }(jQuery));

</script>


<table class="table table-striped table-responsive table-hover" id="admission_table">
    <thead>
    <tr>
        <th>#</th>
        <th>Inpatient ID</th>
        <th>Given Name</th>
        <th>Family Name</th>
        <th>Ward Name</th>
        <th>Launch</th>
        <th>View</th>
        <th>Discharge</th>
    </tr>
    </thead>
    <tbody>
    <c:set var="count" value="0" scope="page" />
    <c:forEach var="admission" items="${admissionList}" varStatus="status">
        <tr>
            <c:set var="count" value="${count + 1}" scope="page"/>
            <td>${count}</td>
            <td>${admission.inpatient.inpatientId}</td>
            <td>${admission.inpatient.patient.givenName}</td>
            <td>${admission.inpatient.patient.familyName}</td>
            <td>${admission.ward.wardName}</td>

            <!-- Button trigger modal -->
            <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
                Launch
            </button>

            <td><a href="<c:url value='/module/inpatient/viewAdmission.form?id=${admission.admissionId}' />">
                <button class="btn btn-info"><i class="fa fa-eye"></i>View</button>
            </a> </td>
            <td><a href="<c:url value='/module/inpatient/discharge.form?id=${admission.admissionId}' />">
                <button class="btn btn-success"> <i class="fa fa-check-square-o"></i> Discharge</button>
            </a> </td>
        </tr>
    </c:forEach>
    </tbody>
</table>



<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Modal title</h4>
            </div>
            <div class="modal-body">
                ...
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div>


<%@ include file="/WEB-INF/template/footer.jsp"%>