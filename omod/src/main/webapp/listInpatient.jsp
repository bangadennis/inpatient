<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp" %>
<%@ include file="template/localHeader.jsp"%>

<table class="table table-striped table-responsive table-hover">
    <thead>
    <tr>
        <th>Inpatient ID</th>
        <th>Phone Number</th>
        <th>Given Name</th>
        <th>Delete</th>
        <th>Edit</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="inpatient" items="${inpatientList}" varStatus="status">
        <tr>
            <td>${inpatient.inpatientId}</td>
            <td>${inpatient.phoneNumber}</td>
            <td>${inpatient.patient.givenName}</td>
            <td><a href="<c:url value='/module/inpatient/deleteWard.form?id=${inpatient.inpatientId}' />">
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