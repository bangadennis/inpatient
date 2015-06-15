<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<div class="row">
    <div class="jumbotron col-md-6 col-md-offset-3">
        <h5 class="text-center">Add Ward</h5>
        <div class="form-group col-md-offset-2 col-md-8">
            <form class="" method="post"  action="<c:url value='/module/inpatient/saveWard.form' />">

                <label><openmrs:message code="Ward Name"/></label>
                <br>
                <spring:bind path="ward.wardName">
                    <input type="text" class="form-control" name="wardName" value="${status.value}" size="35" required />
                    <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                </spring:bind>
                <br/>


                <label valign="top"><openmrs:message code="Speciality/Description"/></label>
                <br>
                <spring:bind path="ward.speciality">
                    <input type="text" name="speciality" class="form-control" value="${status.value}" size="35" required/>
                    <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                </spring:bind>
                <br/>
                <label valign="top"><openmrs:message code="Category"/></label>
                <br>
                <spring:bind path="ward.category">
                    <select name="category" class="form-control" >
                        <option value="Minor">Minor</option>
                        <option value="Male">Male</option>
                        <option value="Female">Female</option>
                        <option value="Special">Special</option>
                    </select>
                    <%--<input type="text" name="description" class="form-control" value="${status.value}" size="35" required/>--%>
                    <%--<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>--%>
                </spring:bind>
                <br/>

                <label valign="top"><openmrs:message code="Capacity"/></label>
                <br>
                <spring:bind path="ward.capacity">
                    <input type="number" name="capacity" class="form-control" value="${status.value}" size="35" required/>
                    <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                </spring:bind>
                <br>
                <input type="submit" class="btn btn-md btn-success" value="<openmrs:message code="Save"/>" name="save">
            </form>
        </div>
    </div>

</div>
<%@ include file="/WEB-INF/template/footer.jsp"%>