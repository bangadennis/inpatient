<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>
<%@ include file="template/localHeader.jsp"%>
<div class="row">
    <div class="jumbotron col-md-8 col-md-offset-2">
        <h2 class="text-center">Admission Form</h2>
        <div class="form-group col-md-offset-2 col-md-8">
            <form class="form-horizontal" method="post"  action="<c:url value='/module/inpatient/saveAdmission.form' />">

                <div class="form-group">
                    <input type="hidden" class="form-control" name="inpatient_id" value="${inpatientId}" required />
                </div>

                <div class="form-group">
                    <label>Admission Date</label>
                    <input type="date" class="form-control" name="admission_date"   required />
                </div>

                <div class="form-group">
                    <label>Hiv Status</label>
                    <input type="text" class="form-control" name="hiv_status"   required />
                </div>

                <div class="form-group">
                    <label>Nutrition Status</label>
                    <input type="number" class="form-control" name="nutrition_status"   required />
                </div>

                <div class="form-group">
                    <label>Guardian</label>
                    <input type="text" class="form-control" name="guardian"   required />
                </div>

                <div class="form-group">
                    <label>Ward ID</label>
                    <input type="integer" class="form-control" name="ward_id"   required />
                </div>

                <div class="form-group">
                    <label>Referral From</label>
                    <input type="Text" class="form-control" name="referral_from"   />
                </div>

                <div class="form-group">
                    <input type="hidden" class="form-control" name="status" value="1" />
                </div>


                <div class="form-group">
                    <button type="submit" class="btn btn-success">Submit</button>
                </div>

            </form>
        </div>

    </div>
<%@ include file="/WEB-INF/template/footer.jsp"%>