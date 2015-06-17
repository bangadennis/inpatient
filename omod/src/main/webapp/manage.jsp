<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<div class="panel panel-default">
    <div class="panel-heading">
        <i class="fa fa-spinner fa-spin"></i>&nbsp;Dashboard
    </div>
    <div class="panel-body">
        <div class="row">
            <div class="col-md-2">
               <a  style="text-decoration: none"href="<c:url value='/module/inpatient/findPatient.form'/>">
                <i class="fa fa-search fa-5x"></i><br>
                <span>Search Patient</span>
                </a>
            </div>

            <div class="col-md-2">
                <a style="text-decoration: none" href="<c:url value='/findPatient.htm'/>">
                    <i class="fa fa-user-plus fa-5x"></i><br>
                    <span>Add Patient</span>
                </a>
            </div>
            <div class="col-md-2">
                <a style="text-decoration: none" href="<c:url value='/module/inpatient/addward.form'/>">
                <i class="fa fa-plus-circle fa-5x"></i><br>
                <span>Add Ward</span>
                </a>
            </div>

            <div class="col-md-2">
                <a style="text-decoration: none" href="<c:url value='/module/inpatient/listwards.form'/>">
                <i class="fa fa-list-alt fa-5x"></i><br>
                <span>Ward</span>
                </a>
            </div>
            <div class="col-md-2">
                <a style="text-decoration: none" href="<c:url value='/module/inpatient/listadmission.form'/>">
                <i class="fa fa-th-list fa-5x"></i><br>
                <span>Admission</span>
                </a>
            </div>

            <div class="col-md-2">
                <a style="text-decoration: none" href="<c:url value='/module/inpatient/listdischarge.form'/>">
                <i class="fa fa-list-ol fa-5x"></i><br>
                <span>Discharge</span>
                </a>
            </div>
            <br/>
            <div class="col-md-2">
                <a style="text-decoration: none" href="<c:url value='/module/inpatient/listInpatient.form'/>">
                    <i class="fa fa-users fa-5x"></i><br>
                    <span>Inpatients List</span>
                </a>
            </div>

        </div>
    </div>
</div>


<%@ include file="/WEB-INF/template/footer.jsp"%>