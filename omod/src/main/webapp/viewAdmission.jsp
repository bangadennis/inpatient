<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>
<%@ include file="template/localHeader.jsp"%>
<div class="row">
    <div class="jumbotron col-md-8 col-md-offset-2">
        <h2 class="text-center">Admission Detail</h2>
        <div class="form-group col-md-offset-2 col-md-8">
            <div class="form-horizontal">

                <div class="form-group">
                    <input type="hidden" class="form-control" name="inpatient_id" value="${admission.admissionId}" required />
                </div>

                <div class="form-group">
                    <label>Admission Date</label>
                    <input type="date" class="form-control" name="admission_date"  value="${admission.admissionDate}"   required />
                </div>

                <div class="form-group">
                    <label>Hiv Status</label>
                    <input type="text" class="form-control" name="hiv_status" value="${admission.hivStatus}"  required />
                </div>

                <div class="form-group">
                    <label>Nutrition Status</label>
                    <input type="number" class="form-control" name="nutrition_status" value="${admission.nutritionStatus}"  required />
                </div>

                <div class="form-group">
                    <label>Guardian</label>
                    <input type="text" class="form-control" name="guardian" value="${admission.guardian}"  required />
                </div>

                <div class="form-group">
                    <label>Ward Name</label>
                    <input type="text" class="form-control" name="guardian" value="${admission.ward.wardName}"  required />
                </div>

                <div class="form-group">
                    <label>Referral From</label>
                    <input type="Text" class="form-control" name="referral_from" value="${admission.referralFrom}"  />
                </div>

                <div class="form-group">
                    <input type="hidden" class="form-control" name="status" value="1" />
                </div>


                <div>
                    <a href="<c:url value='/module/inpatient/listadmission.form' />">
                    <button  class="btn btn-warning">
                        <i class="fa fa-arrow-left"></i>Back</button></a>
                </div>

            </div>
        </div>

    </div>
<%@ include file="/WEB-INF/template/footer.jsp"%>