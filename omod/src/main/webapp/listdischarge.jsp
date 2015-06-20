<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp" %>
<%@ include file="template/localHeader.jsp"%>


<script type="text/javascript">

    (function ($) {

        $(document).ready(function() {

            $('#discharge_table').dataTable({
                "dom": "<fl<t>ip>"
            });

        });

        $(".clickable-row").click(function() {
            window.document.location = $(this).data("href");
        });


    }(jQuery));

</script>


<table class="table table-striped table-responsive table-hover" id="discharge_table">
    <thead>
    <tr>
        <th>#</th>
        <th>Inpatient ID</th>
        <td>Admission Date</td>
        <td>Discharge Date</td>
        <th>Given Name</th>
        <th>Family Name</th>
        <th>Ward Name</th>
        <th>Remarks</th>
        <th>view</th>
    </tr>
    </thead>
    <tbody>
    <c:set var="count" value="0" scope="page" />
    <c:forEach var="admission" items="${admissionList}" varStatus="status">
        <tr>
            <c:set var="count" value="${count + 1}" scope="page"/>
            <td class='clickable-row bg-info'
            data-href="<c:url value='/module/inpatient/listEncounter.form?id=${admission.admissionId}' />">
            ${count}
            </td>
            <td>${admission.inpatient.inpatientId}</td>
            <td>${admission.admissionDate}</td>
            <td>${admission.discharge.dischargeDate}</td>
            <td>${admission.inpatient.patient.givenName}</td>
            <td>${admission.inpatient.patient.familyName}</td>
            <td>${admission.ward.wardName}</td>
            <td>${admission.discharge.remarks}</td>
            <!-- Button trigger modal -->
            <td> <button type="button" class="btn btn-info" data-toggle="modal" data-target="#viewDischargeModal_${count}">
                <i class="fa fa-eye"></i>View
            </button></td>

        </tr>
    </c:forEach>
    </tbody>
</table>

<!-- Modal for View -->
<c:set var="count" value="0" scope="page" />
<c:forEach var="admission" items="${admissionList}" varStatus="status">
    <c:set var="count" value="${count + 1}" scope="page"/>
    <div class="modal fade" id="viewDischargeModal_${count}" tabindex="-1" role="dialog" aria-labelledby="viewDischargeModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="viewDischargeModalLabel">Patient Admission/Discharge Details</h4>
                </div>
                <div class="modal-body">
                    <div class="text-center">
                        <h4>Patient Details</h4>
                        <hr/>
                        <p>Inpatient ID:${admission.inpatient.inpatientId}</p>
                        <p>Given Name:${admission.inpatient.patient.givenName}</p>
                        <p>Middle Name:${admission.inpatient.patient.middleName}</p>
                        <p>Family Name:${admission.inpatient.patient.familyName}</p>
                        <p>Phone Number:${admission.inpatient.phoneNumber}</p>
                        <hr/>
                        <h4>Admission/Discharge Details</h4>
                        <hr/>
                        <p>Admission Date:${admission.admissionDate}</p>
                        <p>Discharge Date:${admission.discharge.dischargeDate}</p>
                        <p>Hiv Status: ${admission.hivStatus}</p>
                        <p>Nutrition: Status${admission.nutritionStatus}</p>
                        <p>Ward Name: ${admission.ward.wardName}</p>
                        <p>Guardian: ${admission.guardian}</p>
                        <p>Referral To:${admission.discharge.referralTo}</p>
                        <p>Referral From:${admission.referralFrom}</p>
                        <p>Discharge Remarks:${admission.discharge.remarks}</p>

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