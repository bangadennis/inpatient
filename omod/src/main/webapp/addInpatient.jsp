<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<div class="row">
    <div class="jumbotron col-md-6 col-md-offset-3">
        <h5 class="text-center">Add Ward</h5>
        <div class="form-group col-md-offset-2 col-md-8">
            <form class="" method="post"  action="<c:url value='/module/inpatient/addInpatient.form' />">

                <%--<label><openmrs:message code="Patient ID"/></label>--%>
                <%--<br>--%>
                <%--<spring:bind path="inpatient.outpatient_id">--%>
                    <%--<input type="text" class="form-control" name="patientId" value="${status.value}" size="35" required />--%>
                    <%--<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>--%>
                <%--</spring:bind>--%>
                <%--<br/>--%>


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
                <input type="submit" class="btn btn-md btn-success" value="<openmrs:message code="Save"/>" name="save">
            </form>
        </div>
    </div>

</div>
<%@ include file="/WEB-INF/template/footer.jsp"%>