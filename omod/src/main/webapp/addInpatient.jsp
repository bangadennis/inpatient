<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<div class="row">
    <div class="jumbotron col-md-6 col-md-offset-3">
        <h5 class="text-center">Add Ward</h5>
        <div class="form-group col-md-offset-2 col-md-8">
            <form class="" method="post"  action="<c:url value='/module/inpatient/addInpatient.form' />">

                <br>
                <spring:bind path="inpatient.outPatientId">
                    <input type="hidden" class="form-control" name="patientId" value="${patientId}" size="35" required />
                    <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                </spring:bind>
                <br/>


                <label valign="top"><openmrs:message code="Inpatient Number"/></label>
                <br>
                <spring:bind path="inpatient.inpatientId">
                    <input type="text" name="inpatientId" class="form-control" value="${status.value}" size="35" required/>
                    <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                </spring:bind>
                <br/>
                <label valign="top"><openmrs:message code="Phone Number"/></label>
                <br>
                <spring:bind path="inpatient.phoneNumber">
                    <input type="text" name="phoneNumber" class="form-control" value="${status.value}" size="35" required/>
                    <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                </spring:bind>
                <br/>

                <%--the admission details start here--%>
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
                    <select class="form-control" name="ward_id">
                        <option></option>
                        <c:forEach var="ward" items="${wards}">
                            <option value="${ward.wardId}">${ward.wardName}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label>Referral From</label>
                    <input type="Text" class="form-control" name="referral_from"   />
                </div>

                <div class="form-group">
                    <input type="hidden" class="form-control" name="status" value="1" />
                </div>

                <input type="submit" class="btn btn-md btn-success" value="<openmrs:message code="Save"/>" name="save">

            </form>

        </div>
    </div>

</div>
<%@ include file="/WEB-INF/template/footer.jsp"%>