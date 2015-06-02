<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp" %>
<%@ include file="template/localHeader.jsp"%>

<table class="table table-striped table-responsive table-hover">
    <thead>
    <tr>
        <th>Adm ID</th>
        <th>Given Name</th>
        <th>Family Name</th>
        <th>Ward ID</th>
        <th>Delete</th>
        <th>Edit</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="admission" items="${admissionList}" varStatus="status">
        <tr>
            <td>${admission.admissionId}</td>
            <td>${admission.inpatient.patient.givenName}</td>
            <td>${admission.inpatient.patient.familyName}</td>
            <td>${admission.wardId}</td>
            <td><a href="<c:url value='/module/inpatient/deleteWard.form?id=${admission.admissionId}' />">
                <button class="btn btn-danger"><span class="glyphicon glyphicon-remove-sign">Delete
            </span> </button>
            </a> </td>
            <td><a href="<c:url value='/module/inpatient/discharge.form?id=${admission.admissionId}' />">
                <button class="btn btn-success"><span class="glyphicon glyphicon-edit">Discharge</span></button>
            </a> </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<%@ include file="/WEB-INF/template/footer.jsp"%>