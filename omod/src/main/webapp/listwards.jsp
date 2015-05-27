<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp" %>
<%@ include file="template/localHeader.jsp"%>


<table class="table table-striped table-responsive table-hover">
    <thead>
    <tr>
        <th>Ward Name</th>
        <th>Speciality</th>
        <th>Description</th>
        <th>Capacity</th>
        <th>Delete</th>
        <th>Edit</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="thisWard" items="${ward}" varStatus="status">
        <tr>
            <td>${thisWard.wardName}</td>
            <td>${thisWard.speciality}</td>
            <td>${thisWard.description}</td>
            <td>${thisWard.capacity}</td>
            <td><a href="<c:url value='/module/inpatient/deleteWard.form?id=${thisWard.wardId}' />">
                <button class="btn btn-danger"><span class="glyphicon glyphicon-remove-sign">Delete
            </span> </button>
            </a> </td>
            <td><a href="<c:url value='/module/patientmodule/editWard.form?id=${thisWard.wardId}' />">
                <button class="btn btn-success"><span class="glyphicon glyphicon-edit">Edit</span></button>
            </a> </td>

        </tr>
    </c:forEach>
    </tbody>
</table>

<%@ include file="/WEB-INF/template/footer.jsp"%>