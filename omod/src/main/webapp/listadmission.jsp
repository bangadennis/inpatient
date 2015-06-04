<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp" %>
<%@ include file="template/localHeader.jsp"%>


<script type="text/javascript">

    (function ($) {

        $(document).ready(function() {

            $('#admission_table').dataTable();

            $('#outcome_opt').change(function(){
                var selection = $(this).val();
                //alert(selection);
                switch(selection)
                {
                    case "D":
                        $('#causeofdeath_opt').show();
                        break;

                    default:
                        $('#causeofdeath_opt').hide();
                        break;
                }
            });


            $('#datetimepicker1').datetimepicker();


        });

    }(jQuery));

</script>


<table class="table table-striped table-responsive table-hover" id="admission_table">
    <thead>
    <tr>
        <th>#</th>
        <th>Inpatient ID</th>
        <td>Admission Date</td>
        <th>Given Name</th>
        <th>Family Name</th>
        <th>Ward Name</th>
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
            <td>${admission.admissionDate}</td>
            <td>${admission.inpatient.patient.givenName}</td>
            <td>${admission.inpatient.patient.familyName}</td>
            <td>${admission.ward.wardName}</td>

            <!-- Button trigger modal -->
           <td> <button type="button" class="btn btn-info" data-toggle="modal" data-target="#viewModal_${count}">
               <i class="fa fa-eye"></i>View
            </button></td>

            <td>
                <button type="button" class="btn btn-success" data-toggle="modal" data-target="#dischargeModal_${count}">
                    <i class="fa fa-check-square-o"></i> Discharge</button>

                <%--<a href="<c:url value='/module/inpatient/discharge.form?id=${admission.admissionId}' />">--%>
                <%--<button class="btn btn-success"> <i class="fa fa-check-square-o"></i> Discharge</button>--%>
                <%--</a> --%>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>



<!-- Modal for View -->
<c:set var="count" value="0" scope="page" />
<c:forEach var="admission" items="${admissionList}" varStatus="status">
    <c:set var="count" value="${count + 1}" scope="page"/>
<div class="modal fade" id="viewModal_${count}" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="viewModalLabel">Patient Admission</h4>
            </div>
            <div class="modal-body">
                <div class="text-center">
                    <h4>Patient Details</h4>
                    <p>Inpatient ID:${admission.inpatient.inpatientId}</p>
                    <p>Given Name:${admission.inpatient.patient.givenName}</p>
                    <p>Middle Name:${admission.inpatient.patient.middleName}</p>
                    <p>Family Name:${admission.inpatient.patient.familyName}</p>
                    <p>Phone Number:${admission.inpatient.phoneNumber}</p>
                    <h4>Admission Details</h4>
                    <p>Admission Date:${admission.admissionDate}</p>
                    <p>Hiv Status: ${admission.hivStatus}</p>
                    <p>Nutrition: Status${admission.nutritionStatus}</p>
                    <p>Ward Name: ${admission.ward.wardName}</p>
                    <p>Guardian: ${admission.guardian}</p>
                    <p>Referral From:${admission.referralFrom}</p>
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


<!-- Modal for Discharge -->
<c:set var="count" value="0" scope="page" />
<c:forEach var="admission" items="${admissionList}" varStatus="status">
    <c:set var="count" value="${count + 1}" scope="page"/>
    <div class="modal fade" id="dischargeModal_${count}" tabindex="-1" role="dialog" aria-labelledby="dischargeModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="dischargeModalLabel">Discharge Patient -Inpatient ID:${admission.inpatient.inpatientId}</h4>
                </div>

                <div class="modal-body">
                    <div class="row">
                        <div class="form-group col-md-offset-2 col-md-8">
                            <form class="form-horizontal" method="post"  action="<c:url value='/module/inpatient/saveDischarge.form' />">
                                <div class="form-group">
                                    <input type="hidden" class="form-control" name="discharge_id" value="${admission.admissionId}" required />
                                </div>

                                <%--<div class="form-group">--%>
                                    <%--<label>Discharge Date</label>--%>
                                    <%--<input type="date" class="form-control" name="discharge_date"   required />--%>
                                <%--</div>--%>
                                <div class="form-group">
                                    <label>Discharge Date</label>
                                    <div class='input-group date' id='datetimepicker1'>
                                        <input type='text' class="form-control" name="discharge_date"/>
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-calendar"></span>
                                        </span>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label>Diagnosis</label>
                                    <input type="text" class="form-control" name="diagnosis"   required />
                                </div>

                                <div class="form-group">
                                    <label>Treatment</label>
                                    <input type="text" class="form-control" name="treatment"   required />
                                </div>

                                <div class="form-group">
                                    <label>Outcome</label>
                                    <select name="outcome" class="form-control" id="outcome_opt" required>
                                        <option></option>
                                        <option value="A">Alive</option>
                                        <option value="D">Dead</option>
                                    </select>
                                </div>

                                <div class="form-group" id="causeofdeath_opt" style="display:none;">
                                    <label>Cause of Death</label>
                                    <input type="Text" class="form-control" name="causeofdeath"   />
                                </div>

                                <div class="form-group">
                                    <label>Referral To</label>
                                    <select class="form-control" name="referral_to">
                                        <option value="0">None</option>
                                        <option value="3">Other Health Facility</option>
                                        <option value="4">Community Unit</option>
                                    </select>
                                </div>

                                <div class="form-group">
                                    <label>Remarks</label>
                                    <input type="Text" class="form-control" name="remarks"   />
                                </div>

                                <div class="form-group">
                                    <button type="submit" class="btn btn-success">Submit</button>
                                </div>
                            </form>
                        </div>

                    </div>

                </div>
                <%--<div class="modal-footer">--%>
                    <%--<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>--%>
                    <%--&lt;%&ndash;<button type="submit" class="btn btn-primary">Save</button>&ndash;%&gt;--%>
                <%--</div>--%>
            </div>
        </div>
    </div>
</c:forEach>



<%@ include file="/WEB-INF/template/footer.jsp"%>