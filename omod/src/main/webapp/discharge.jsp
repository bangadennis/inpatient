<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>
<%@ include file="template/localHeader.jsp"%>
<div class="row">
    <div class="jumbotron col-md-8 col-md-offset-2">
        <h2 class="text-center">Discharge Form</h2>
        <div class="form-group col-md-offset-2 col-md-8">
            <form class="form-horizontal" method="post"  action="<c:url value='/module/inpatient/saveDischarge.form' />">

                <div class="form-group">
                    <input type="hidden" class="form-control" name="discharge_id" value="${admissionId}" required />
                </div>

                <div class="form-group">
                    <label>Discharge Date</label>
                    <input type="date" class="form-control" name="discharge_date"   required />
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
                    <input type="text" class="form-control" name="outcome"   required />
                </div>

                <div class="form-group">
                    <label>Cause of Death</label>
                    <input type="Text" class="form-control" name="causeofdeath"   />
                </div>

                <div class="form-group">
                    <label>Referral To</label>
                    <input type="Text" class="form-control" name="referral_to"   />
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
<%@ include file="/WEB-INF/template/footer.jsp"%>