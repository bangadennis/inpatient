<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp" %>
<%@ include file="template/localHeader.jsp"%>

<script type="text/javascript">

    (function ($) {
        $(document).ready(function() {

            $('#wardPatient_table').dataTable();

            $(".clickable-row").click(function() {
                window.document.location = $(this).data("href");
            });

        });

    }(jQuery));

</script>

<div class="row">
    <div class="well well-sm">
        <h4>Ward:${ward.wardName}&nbsp; Speciality:${ward.speciality}&nbsp; Category:${ward.category}</h4>
    </div>
    <table class="table table-striped table-responsive table-hover" id="wardPatient_table">
        <thead>
            <th>#</th>
            <th>Inpatient ID</th>
            <th>Given Name</th>
            <td Middle Name</td>
            <th>Family Name</th>
            <th>Age</th>
            <th>Gender</th>
            <th>View</th>
        </thead>
        <tbody>
        <c:set var="count" value="0" scope="page" />
        <c:forEach var="inpatient" items="${patientList}" varStatus="status">
            <tr  class='clickable-row'
                 data-href="<c:url value='/module/inpatient/inpatientDashboardForm.form?id=${inpatient.outPatientId}' />">
                <c:set var="count" value="${count + 1}" scope="page"/>
                <td>${count}</td>
                <td>${inpatient.inpatientId}</td>
                <td>${inpatient.patient.givenName}</td>
                <td>${inpatient.patient.middleName}</td>
                <td>${inpatient.patient.familyName}</td>
                <td>${inpatient.patient.age}</td>
                <td>${inpatient.patient.gender}</td>
                <!-- Button trigger modal -->
                <td> <button type="button" class="btn btn-info" data-toggle="modal" data-target="#viewModal_${count}">
                    <i class="fa fa-eye"></i>View
                </button></td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<!-- Modal for View -->
<c:set var="count" value="0" scope="page" />
<c:forEach var="inpatient" items="${patientList}" varStatus="status">
    <c:set var="count" value="${count + 1}" scope="page"/>
    <div class="modal fade" id="viewModal_${count}" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="viewModalLabel">Patient Overview</h4>
                </div>
                <div class="modal-body">
                    <div class="text-center">
                        <h4>Patient Details</h4>
                        <p>Inpatient ID:${inpatient.inpatientId}</p>
                        <p>Given Name:${inpatient.patient.givenName}</p>
                        <p>Middle Name:${inpatient.patient.middleName}</p>
                        <p>Family Name:${inpatient.patient.familyName}</p>
                        <p>Phone Number:${inpatient.phoneNumber}</p>
                    </div>


                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <%--<button type="button" class="btn btn-primary">Save changes</button>--%>
                </div>
            </div>
        </div>
    </div>
</c:forEach>



<%@ include file="/WEB-INF/template/footer.jsp"%>