<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<div class="row">
    <div class="jumbotron col-md-6 col-md-offset-3">
        <h5 class="text-center">Add Ward</h5>
        <div class="form-group col-md-offset-2 col-md-8">
            <br class="" method="post"  action="<c:url value='/module/inpatient/savePatient.form' />">

                <label><openmrs:message code="Admission"/></label>
                <br>
                <spring:bind path="admission.name">
                    <input type="text" class="form-control" name="name" value="${status.value}" size="35" required />
                    <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                </spring:bind>
                <br/>


                <label valign="top"><openmrs:message code="Date of Admission"/></label>
                <br>
                <spring:bind path="admission.date">
                    <input type="text" name="dateofadmission" class="form-control" value="${status.value}" size="35" required/>
                    <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                </spring:bind>
                <br/>
                <label valign="top"><openmrs:message code="Time of Admission"/></label>
                <br>
                <spring:bind path="admission.time">
                    <input type="text" name="timeofadmission" class="form-control" value="${status.value}" size="35" required/>
                    <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                </spring:bind>
                <br/>

                <label valign="top"><openmrs:message code="HIV Status"/></label>
                <br>
                <spring:bind path="admission.hivstatus">
                    <input type="number" name="hivstatus" class="form-control" value="${status.value}" size="35" required/>
                    <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                </spring:bind>
                </br>

                <label valign="top"><openmrs:message code="Guardian's name"/></label>
                <br>
                <spring:bind path="admission.guardian">
                    <input type="number" name="guardian" class="form-control" value="${status.value}" size="35" required/>
                    <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                </spring:bind>
                </br>
                <input type="submit" class="btn btn-md btn-success" value="<openmrs:message code="Save"/>" name="save">
            </form>
        </div>
    </div>

</div>
<%@ include file="/WEB-INF/template/footer.jsp"%>