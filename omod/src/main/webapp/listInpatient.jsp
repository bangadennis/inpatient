<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp" %>
<%@ include file="template/localHeader.jsp"%>

<table class="table table-striped table-responsive table-hover">
    <thead>
    <tr>
        <th>#</th>
        <th>Inpatient ID</th>
        <th>Phone Number</th>
        <th>Given Name</th>
        <th>Delete</th>
        <th>Edit</th>
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
            <td><a href="<c:url value='/module/inpatient/deleteInpatient.form?id=${inpatient.outPatientId}' />">
                <button class="btn btn-danger"><span class="glyphicon glyphicon-remove-sign">Delete
            </span> </button>
            </a> </td>
            <td><a href="<c:url value='/module/inpatient/admission.form?id=${inpatient.inpatientId}' />">
                <button class="btn btn-success"><span class="glyphicon glyphicon-edit">Admit</span></button>
            </a> </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<%@ include file="/WEB-INF/template/footer.jsp"%>