<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>
<%@ include file="template/localHeader.jsp"%>

<script type="text/javascript">

    (function ($) {

        $(document).ready(function() {

            $('#admission_table').dataTable();

            $('#admissionDate').datetimepicker();


            $('#admissionModal').on('show.bs.modal', function(event) {
                var btn = $(event.relatedTarget);
                var id = btn.data('id');
                $("#inpatientId").val(id);
            });

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


            $('#dischargeModal').on('show.bs.modal', function(event) {
                var btn = $(event.relatedTarget);
                var id = btn.data('id');
                $("#dischargeId").val(id);
            });


            $('#dischargetime').datetimepicker();




        });

    }(jQuery));

</script>
<div class="row">
    <c:if test="${inpatient.patient.dead==true}">
        <div class="panel panel-danger">
            </c:if>
        <c:if test="${inpatient.patient.dead==false}">
            <div class="panel panel-info">
                </c:if>
        <div class="panel-heading">
            <c:if test="${inpatient.patient.dead}">
                   This Patient is Dead,Death Date-&nbsp;${inpatient.patient.deathDate}
            </c:if>
            <h3 class="panel-title">
                    <c:set var="gender" scope="session" value="${inpatient.patient.gender}"/>
                    <c:if test="${gender=='M'}">
                        <i class="fa fa-male"></i>
                    </c:if>
                     <c:if test="${gender=='F'}">
                         <i class="fa fa-female"></i>
                     </c:if>
                 ${inpatient.patient.givenName}&nbsp;${inpatient.patient.middleName}
                    &nbsp;${inpatient.patient.familyName}
            </h3>
            <h4 class="pull-right">Outpatient ID-${inpatient.patient.patientId}&nbsp;
                Inpatient ID-${inpatient.inpatientId}
            </h4>

            <h4 class="panel-title">Age-${inpatient.patient.age}(${inpatient.patient.birthdate})</h4>
            <c:if test="${admission !=null }">
            <h4 class="panel-title">
                Active Admission from-${admission.admissionDate}
            </h4>
            </c:if>
        </div>
        <div class="panel-body">
            <div role="tabpanel">
                <!-- Nav tabs -->
                <ul class="nav nav-tabs" role="tablist">
                    <li role="presentation" class="active"><a href="#overview" aria-controls="home" role="tab" data-toggle="tab">Overview</a></li>
                    <li role="presentation"><a href="#admission" aria-controls="admission" role="tab" data-toggle="tab">Admission</a></li>
                    <li role="presentation"><a href="#discharge" aria-controls="discharge" role="tab" data-toggle="tab">Past Admissions</a></li>
                    <li role="presentation"><a href="#orders" aria-controls="orders" role="tab" data-toggle="tab">Orders</a></li>
                </ul>

                <!-- Tab panes -->
                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane active" id="overview">
                        overview
                    </div>


                    <div role="tabpanel" class="tab-pane" id="admission">

                        <c:if test="${admission ==null }">
                            <button type="button" class="btn btn-success" data-toggle="modal"  data-id="${inpatient.outPatientId}" data-target="#admissionModal">
                                <i class="fa fa-plus-square"></i> Admit</button>
                        </c:if>

                        <c:if test="${admission !=null }">

                            <div role="tabpanel">

                                <!-- Nav tabs -->
                                <ul class="nav nav-pills">
                                    <li r class="active"><a href="#home"  data-toggle="pill">Add Encounters</a></li>
                                    <li><a href="#profile"  data-toggle="pill">List Encounters</a></li>
                                    <li><button type="button" class="btn btn-info" data-toggle="modal"  data-id="${admission.admissionId}" data-target="#dischargeModal">
                                        <i class="fa fa-check-square-o"></i>Discharge</button></li>
                                </ul>

                                <!-- Tab panes -->
                                <div class="tab-content">
                                    <div role="tabpanel" class="tab-pane active" id="home">
                                        <button class="btn btn-success">Add Encounter</button>
                                    </div>
                                    <div role="tabpanel" class="tab-pane" id="profile">View Encounters</div>
                                </div>

                            </div>

                        </c:if>

                    </div>

                    <div role="tabpanel" class="tab-pane" id="discharge">
                        <div class="col-md-10 col-offset-md-1">
                            <h3>Admissions and Discharges</h3>
                            <table class="table table-striped table-responsive table-hover" id="admission_table">
                            <thead>
                            <tr>
                                <th>#</th>
                                <td>Admission Date</td>
                                <td>Discharge Date</td>
                                <th>Ward Name</th>
                                <th>View</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:set var="count" value="0" scope="page" />
                            <c:forEach var="admission" items="${admissionList}" varStatus="status">
                                <tr>
                                    <c:set var="count" value="${count + 1}" scope="page"/>
                                    <td>${count}</td>
                                    <td>${admission.admissionDate}</td>
                                    <td>${admission.discharge.dischargeDate}</td>
                                    <td>${admission.ward.wardName}</td>
                                    <!-- Button trigger modal -->
                                    <td> <button type="button" class="btn btn-info" data-toggle="modal" data-target="#viewDischargeModal_${count}">
                                        <i class="fa fa-eye"></i>View
                                    </button></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        </div>

                    </div>
                    <div role="tabpanel" class="tab-pane" id="orders">
                        Orders
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="admissionModal" tabindex="-1" role="dialog" aria-labelledby="admissionModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="admissionModalLabel">Admit Patient</h4>
            </div>

            <div class="modal-body">
                <div class="row">
                    <div class="form-group col-md-offset-2 col-md-8">
                        <form class="form-horizontal" method="post"  action="<c:url value='/module/inpatient/saveAdmission.form' />">

                            <input id="inpatientId" type="hidden" name="inpatient_id"  required />

                            <%--<div class="form-group">--%>
                            <%--<label>Admission Date</label>--%>
                            <%--<input type="date" class="form-control" name="admission_date"   required />--%>
                            <%--</div>--%>
                            <div class="form-group">
                                <label>Admission Date</label>
                                <div class='input-group date' id='admissionDate'>
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

<!-- Modal for View -->
<c:set var="count" value="0" scope="page" />
<c:forEach var="admission" items="${admissionList}" varStatus="status">
    <c:set var="count" value="${count + 1}" scope="page"/>
    <div class="modal fade" id="viewDischargeModal_${count}" tabindex="-1" role="dialog" aria-labelledby="viewDischargeModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="viewDischargeModalLabel"> Admission/Discharge Details</h4>
                </div>
                <div class="modal-body">
                    <div class="text-center">
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


<div class="modal fade" id="dischargeModal" tabindex="-1" role="dialog" aria-labelledby="dischargeModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="dischargeModalLabel">Discharge Patient </h4>
            </div>

            <div class="modal-body">
                <div class="row">
                    <div class="form-group col-md-offset-2 col-md-8">
                        <form class="form-horizontal" method="post"  action="<c:url value='/module/inpatient/saveDischarge.form' />">

                            <input id="dischargeId" type="hidden" class="form-control" name="discharge_id"  required />

                            <div class="form-group">
                                <label>Discharge Date</label>
                                <div class='input-group date' id='dischargetime'>
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