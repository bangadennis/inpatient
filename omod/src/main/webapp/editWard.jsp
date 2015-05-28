<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<div class="row">
    <div class="jumbotron col-md-6 col-md-offset-3">
        <h5 class="text-center">Add Ward</h5>
        <div class="form-group col-md-offset-2 col-md-8">
            <form class="" method="post"  action="<c:url value='/module/inpatient/saveWardEdit.form' />">

                <input type="hidden"  name="id" value="${ward.wardId}"  />

                <label><openmrs:message code="Ward Name"/></label>
                <br>
                <spring:bind path="ward.wardName">
                    <input type="text" class="form-control" name="wardName" value="${ward.wardName}" size="35" required />
                    <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                </spring:bind>
                <br/>


                <label valign="top"><openmrs:message code="Speciality"/></label>
                <br>
                <spring:bind path="ward.speciality">
                    <input type="text" name="speciality" class="form-control" value="${ward.speciality}" size="35" required/>
                    <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                </spring:bind>
                <br/>
                <label valign="top"><openmrs:message code="Description"/></label>
                <br>
                <spring:bind path="ward.description">
                    <input type="text" name="description" class="form-control" value="${ward.description}" size="35" required/>
                    <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                </spring:bind>
                <br/>

                <label valign="top"><openmrs:message code="Capacity"/></label>
                <br>
                <spring:bind path="ward.capacity">
                    <input type="number" name="capacity" class="form-control" value="${ward.capacity}" size="35" required/>
                    <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                </spring:bind>
                <input type="submit" class="btn btn-md btn-success" value="<openmrs:message code="Save"/>" name="save">
            </form>
        </div>
    </div>

</div>
<%@ include file="/WEB-INF/template/footer.jsp"%>