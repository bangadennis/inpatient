<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp" %>
<%@ include file="template/localHeader.jsp"%>

<table class="table table-striped table-responsive table-hover">
    <thead>
    <tr>
        <th>First Name</th>
        <th>Middle Name</th>
        <th>Family Name</th>
        <th>Gender</th>
        <th>ADD</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="patient" items="${patientList}" varStatus="status">
        <tr>
            <td>${patient.givenName}</td>
            <td>${patient.middleName}</td>
            <td>${patient.familyName}</td>
            <td>${patient.gender}</td>

            <td><a href="<c:url value='/module/inpatient/inpatient.form?id=${patient.patientId}' />">
                <button class="btn btn-success"><span class="glyphicon glyphicon-edit">Edit</span></button>
            </a> </td>

        </tr>
    </c:forEach>
    </tbody>
</table>

<%@ include file="/WEB-INF/template/footer.jsp"%>