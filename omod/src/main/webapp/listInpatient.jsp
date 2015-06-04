<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp" %>
<%@ include file="template/localHeader.jsp"%>
<script type="text/javascript">

    (function ($) {

        $(document).ready(function() {

            $('#inpatient_table').dataTable();

            $('#datetimepicker2').datetimepicker();
        });

    }(jQuery));

</script>

<table class="table table-striped table-responsive table-hover" id="inpatient_table">
    <thead>
    <tr>
        <th>#</th>
        <th>Inpatient ID</th>
        <th>Phone Number</th>
        <th>Given Name</th>
        <th>Family Name</th>
        <th>Delete</th>
        <th>Admit</th>
    </tr>
    </thead>
    <tbody>
    <c:set var="count" value="0" scope="page" />
    <c:forEach var="inpatient" items="${inpatientList}" varStatus="status">
        <c:set var="count" value="${count+1}" scope="page" />
        <tr>
            <td>${count}</td>
            <td>${inpatient.inpatientId}</td>
            <td>${inpatient.phoneNumber}</td>
            <td>${inpatient.patient.givenName}</td>
            <td>${inpatient.patient.familyName}</td>
            <td><a href="<c:url value='/module/inpatient/deleteInpatient.form?id=${inpatient.outPatientId}' />">
                <button class="btn btn-danger"><span class="glyphicon glyphicon-remove-sign">Delete
            </span> </button>
            </a> </td>
            <td>
                <button type="button" class="btn btn-success" data-toggle="modal" data-target="#admissionModal_${count}">
                    <i class="fa fa-plus-square"></i> Admit</button>
                <%--<a href="<c:url value='/module/inpatient/admission.form?id=${inpatient.inpatientId}' />">--%>
                <%--<button class="btn btn-success"><span class="glyphicon glyphicon-edit">Admit</span></button>--%>
            <%--</a> --%>

            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>



<!-- Modal for Discharge -->
<c:set var="count" value="0" scope="page" />
<c:forEach var="inpatient" items="${inpatientList}" varStatus="status">
    <c:set var="count" value="${count + 1}" scope="page"/>
    <div class="modal fade" id="admissionModal_${count}" tabindex="-1" role="dialog" aria-labelledby="admissionModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="admissionModalLabel">Admit Patient -Inpatient ID:${inpatient.inpatientId}</h4>
                </div>

                <div class="modal-body">
                    <div class="row">
                        <div class="form-group col-md-offset-2 col-md-8">
                            <form class="form-horizontal" method="post"  action="<c:url value='/module/inpatient/saveAdmission.form' />">

                                <div class="form-group">
                                    <input type="hidden" class="form-control" name="inpatient_id" value="${inpatient.inpatientId}" required />
                                </div>

                                <%--<div class="form-group">--%>
                                    <%--<label>Admission Date</label>--%>
                                    <%--<input type="date" class="form-control" name="admission_date"   required />--%>
                                <%--</div>--%>
                                <div class="form-group">
                                    <label>Admission Date</label>
                                    <div class='input-group date' id='datetimepicker2'>
                                        <input type='text' class="form-control" name="admission_date" />
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-calendar"></span>
                                        </span>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label>Hiv Intervention</label>
                                    <select class="form-control" name="status">
                                        <option value="0">None</option>
                                        <option value="1">Counselled</option>
                                        <option value="2">Tested</option>
                                        <option value="1,2">Counselled and Tested</option>
                                    </select>
                                </div>

                                <div class="form-group">
                                    <label>Hiv Status</label>
                                    <select class="form-control" name="hiv_status">
                                        <option value="1">Known Positive</option>
                                        <option value="2">Positive this visit</option>
                                        <option value="3">Negative</option>
                                        <option value="4">Unknown</option>
                                    </select>
                                </div>

                                <div class="form-group">
                                    <label>Nutrition Status</label>
                                    <select class="form-control" name="nutrition_status">
                                        <option value="0">None</option>
                                        <option value="1">Nutrition Education</option>
                                        <option value="2">Nutrition Supplements</option>
                                    </select>
                                </div>

                                <div class="form-group">
                                    <label>Referral From</label>
                                    <select class="form-control" name="referral_from">
                                        <option value="0">None</option>
                                        <option value="1">Other Health Facility</option>
                                        <option value="2">Community Unit</option>
                                    </select>
                                </div>

                                <div class="form-group">
                                    <label>Guardian</label>
                                    <input type="text" class="form-control" name="guardian"   required />
                                </div>

                                <div class="form-group">
                                    <label>Ward ID</label>
                                    <select class="form-control" name="ward_id">
                                        <option></option>
                                        <c:forEach var="ward" items="${wards}">
                                            <option value="${ward.wardId}">${ward.wardName}</option>
                                        </c:forEach>
                                    </select>
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