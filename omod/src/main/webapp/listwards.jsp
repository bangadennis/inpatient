<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp" %>
<%@ include file="template/localHeader.jsp"%>


<table class="table table-striped table-responsive table-hover">
    <thead>
    <tr>
        <th>#</th>
        <th>Ward Name</th>
        <th>Speciality</th>
        <th>Description</th>
        <th>Ward Capacity</th>
        <th>Available Capacity</th>
        <th>Delete</th>
        <th>Edit</th>
    </tr>
    </thead>
    <tbody>
    <c:set var="count" value="0" scope="page" />
    <c:forEach var="thisWard" items="${ward}" varStatus="status">
        <c:set var="count" value="${count + 1}" scope="page"/>
        <tr>
            <td>${count}</td>
            <td>${thisWard.wardName}</td>
            <td>${thisWard.speciality}</td>
            <td>${thisWard.description}</td>
            <td>${thisWard.capacity}</td>
            <td>${availableCapacity.get(thisWard.wardId)}</td>
            <td><a href="<c:url value='/module/inpatient/deleteWard.form?id=${thisWard.wardId}' />">
                <button class="btn btn-danger"><span class="glyphicon glyphicon-remove-sign">Delete
            </span> </button>
            </a> </td>
            <td><a href="<c:url value='/module/inpatient/editWard.form?id=${thisWard.wardId}' />">
                <button class="btn btn-success"><span class="glyphicon glyphicon-edit">Edit</span></button>
            </a> </td>

        </tr>
    </c:forEach>
    </tbody>
</table>

<%@ include file="/WEB-INF/template/footer.jsp"%>