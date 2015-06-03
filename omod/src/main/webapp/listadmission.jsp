<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp" %>
<%@ include file="template/localHeader.jsp"%>

<table class="table table-striped table-responsive table-hover">
    <thead>
    <tr>
        <th>#</th>
        <th>Given Name</th>
        <th>Family Name</th>
        <th>Ward Name</th>
        <th>View</th>
        <th>Discharge</th>
    </tr>
    </thead>
    <tbody>
    <c:set var="count" value="0" scope="page" />
    <c:forEach var="admission" items="${admissionList}" varStatus="status">
        <tr>
            <c:set var="count" value="${count + 1}" scope="page"/>
            <td>${count}</td>
            <td>${admission.inpatient.patient.givenName}</td>
            <td>${admission.inpatient.patient.familyName}</td>
            <td>${admission.ward.wardName}</td>
            <td><a href="<c:url value='/module/inpatient/viewAdmission.form?id=${admission.admissionId}' />">
                <button class="btn btn-info"><i class="fa fa-eye"></i>View</button>
            </a> </td>
            <td><a href="<c:url value='/module/inpatient/discharge.form?id=${admission.admissionId}' />">
                <button class="btn btn-success"> <i class="fa fa-check-square-o"></i> Discharge</button>
            </a> </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<%@ include file="/WEB-INF/template/footer.jsp"%>