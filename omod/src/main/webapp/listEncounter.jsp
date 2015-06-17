<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp" %>
<%@ include file="template/localHeader.jsp"%>
<script type="text/javascript">

    (function ($) {

        $(document).ready(function() {

            $('#inpatient_table').dataTable({
                "sDom": '<"top"fl>rt<"bottom"p><"clear">'
            });

            $(".clickable-row").click(function() {
                window.document.location = $(this).data("href");
            });

        });

    }(jQuery));

</script>

<div class="">
    <div class="row">
        <div class="well well-sm bg-info">
            <h4>InpatientID:&nbsp;${admission.inpatient.inpatientId}&nbsp;Name:${admission.inpatient.patient.givenName}
                &nbsp;Admission Date:${admission.admissionDate}</h4>
        </div>

        <div class="">

            <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                <c:set var="count" value="0" scope="page" />
                <c:forEach var="encounter" items="${encounterList}" varStatus="status">
                    <c:set var="count" value="${count + 1}" scope="page"/>

                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="heading_${count}">
                            <h4 class="panel-title">
                                <a role="button" data-toggle="collapse" data-parent="#accordion" href="#encounter_${count}" aria-expanded="true" aria-controls="collapseOne">
                                    Encounter-#${count} &nbsp;&nbsp;${encounter.encounterDatetime}&nbsp;&nbsp;${encounter.encounterType.name}&nbsp;&nbsp;${encounter.location.name}
                                </a>
                            </h4>
                        </div>

                        <div id="encounter_${count}" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading_${count}">
                            <div class="panel-body">
                                <h4>Observation</h4>
                                <table class="table table-striped table-hover">
                                    <thead>
                                    <th>#</th>
                                    <th>Name</th>
                                    <th>Date</th>
                                    <th>Value</th>
                                    </thead>
                                    <c:set value="0" var="count_var" scope="page"/>
                                    <c:set var="obsList" value="${obsMap[encounter.encounterId]}" scope="page" />
                                    <c:forEach var="obs" items="${obsList}" varStatus="status">
                                        <c:set value="${count_var+1}" var="count_var" scope="page"/>
                                        <tr>
                                            <td>${count_var}</td>
                                            <td>${obs.concept.name}</td>
                                            <td>${obs.obsDatetime}</td>
                                            <td>${obs.valueNumeric}</td>

                                        </tr>
                                    </c:forEach>
                                </table>

                            </div>
                        </div>

                    </div>
                </c:forEach>
            </div>
        </div>

    </div>
</div>



<%@ include file="/WEB-INF/template/footer.jsp"%>