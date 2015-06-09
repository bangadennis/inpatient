<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp" %>
<%@ include file="template/localHeader.jsp"%>
<script type="text/javascript">

    (function ($) {

        $(document).ready(function() {

            $('#ward_table').dataTable();

            $('#editModal').on('show.bs.modal', function(event) {
                var btn = $(event.relatedTarget);
                var id = btn.data('id');
                $("#wardTag").val(id);
            });

            $("#wardtime").datetimepicker();
        });

    }(jQuery));

</script>

<table class="table table-striped table-responsive table-hover" id="ward_table">
    <thead>
    <tr>
        <th>#</th>
        <th>Ward Name</th>
        <th>Speciality</th>
        <th>Description</th>
        <th>Ward Capacity</th>
        <%--<th>Available Capacity</th>--%>
        <%--<th>Delete</th>--%>
        <th>Edit</th>
    </tr>
    </thead>
    <tbody>
    <c:set var="count" value="0" scope="page" />
    <c:forEach var="thisWard" items="${wards}" varStatus="status">
        <c:set var="count" value="${count + 1}" scope="page"/>
        <tr>
            <td>${count}</td>
            <td><a href="<c:url value='/module/inpatient/wardPatient.form?id=${thisWard.wardId}' />">
            ${thisWard.wardName} </a></td>
            <td>${thisWard.speciality}</td>
            <td>${thisWard.description}</td>
            <td>${thisWard.capacity}</td>
            <%--<td> ${availableCapacity.get(thisWard.wardId)} </td>--%>
            <%--<td>--%>
                <%--<a href="<c:url value='/module/inpatient/deleteWard.form?id=${thisWard.wardId}' />">--%>
                <%--<button class="btn btn-danger"><span class="glyphicon glyphicon-remove-sign">Delete</span> --%>
                <%--</button>--%>
            <%--</a>--%>
            <%--</td>--%>
            <td>
                <button class="btn btn-success" data-toggle="modal" data-target="#editWardModal_${count}">
                    <span class="glyphicon glyphicon-edit">Edit</span></button>
            </td>

        </tr>
    </c:forEach>
    </tbody>
</table>


<!-- Modal for Edit Ward -->
<c:set var="count" value="0" scope="page" />
<c:forEach var="ward" items="${wards}" varStatus="status">
    <c:set var="count" value="${count + 1}" scope="page"/>
    <div class="modal fade" id="editWardModal_${count}" tabindex="-1" role="dialog" aria-labelledby="editWardModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="editWardModalLabel">Edit Ward</h4>
                </div>

                <div class="modal-body">
                    <div class="row">
                        <div class="form-group col-md-offset-2 col-md-8">
                            <form class="" method="post"  action="<c:url value='/module/inpatient/saveWardEditModal.form' />">

                                <input type="hidden"  name="id" value="${ward.wardId}"  />

                                <div class="form-group">
                                    <label>Ward Name</label>
                                    <input type="text" class="form-control" name="wardName" value="${ward.wardName}"  required />
                                </div>

                                <div class="form-group">
                                    <label>Speciality</label>
                                    <input type="text" name="speciality" class="form-control" value="${ward.speciality}"  required/>
                                </div>
                                <div class="form-group">
                                    <label>Description</label>
                                    <input type="text" name="description" class="form-control" value="${ward.description}" required/>
                                </div>

                                <div class="form-group">
                                    <label>Capacity</label>
                                    <input type="number" name="capacity" class="form-control" value="${ward.capacity}" required/>
                                </div>

                                <div class="form-group">
                                    <input type="submit" class="btn btn-success" value="Save" />

                                </div>

                            </form>

                        </div>

                    </div>

                </div>

            </div>
        </div>
    </div>
</c:forEach>


<%@ include file="/WEB-INF/template/footer.jsp"%>