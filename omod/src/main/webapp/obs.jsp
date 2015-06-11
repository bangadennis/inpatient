<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>
<%@ include file="template/localHeaderObs.jsp" %>

<openmrs:htmlInclude file="/scripts/calendar/calendar.js" />
<openmrs:htmlInclude file="/scripts/timepicker/timepicker.js" />

<script type="text/javascript">
    $j(document).ready( function() {
        // set up the autocomplete for the drug field
        new AutoComplete("valueDrugDisplay", new CreateCallback().drugCallback(), {
            select: function(event, ui) {
                jquerySelectEscaped("valueDrug").val(ui.item.object.drugId);
            },
            placeholder:'<openmrs:message code="Obs.drug.search.placeholder" javaScriptEscape="true"/>'
        });

        //Clear hidden value on losing focus with no valid entry
        $j("#valueDrugDisplay").autocomplete().blur(function(event, ui) {
            if (!event.target.value) {
                jquerySelectEscaped('valueDrug').val('');
            }
        });

    });

    // on concept select:
    function onQuestionSelect(concept) {
        $j("#conceptDescription").show();
        $j("#conceptDescription").html(concept.description);
        updateObsValues(concept);
    }

    // on answer select:
    function onAnswerSelect(concept) {
        $j("#codedDescription").show();
        $j("#codedDescription").html(concept.description);
    }

    function showProposeConceptForm() {
        var qs = "?";
        var encounterId = "${obs.encounter.encounterId}" || $j("#encounterId").val();
        if (encounterId != "")
            qs += "&encounterId=" + encounterId;
        var obsConceptId = "${obs.concept.conceptId}" || $j("#conceptId").val();
        if (obsConceptId != "")
            qs += "&obsConceptId=" + obsConceptId;
        document.location = "${pageContext.request.contextPath}/admin/concepts/proposeConcept.form" + qs;
    }

    function updateObsValues(tmpConcept) {
        var values = ['valueBooleanRow', 'valueCodedRow', 'valueDatetimeRow', 'valueDateRow', 'valueTimeRow', 'valueModifierRow', 'valueTextRow', 'valueNumericRow', 'valueInvalidRow', 'valueComplex', 'valueDrugRow'];
        $j.each(values, function(x, val) { $j("#" + val).hide() });

        if (tmpConcept != null) {
            var datatype = tmpConcept.hl7Abbreviation;
            if (typeof datatype != 'string')
                datatype = tmpConcept.datatype.hl7Abbreviation;

            //always clear value drug on selection of a question
            $j('#valueDrug').val("");
            $j('#valueDrugDisplay').val("");

            if (datatype == 'BIT') {
                $j('#valueBooleanRow').show();
            }
            else if (datatype == 'NM' || datatype == 'SN') {
                $j('#valueNumericRow').show();
                DWRConceptService.getConceptNumericUnits(tmpConcept.conceptId, fillNumericUnits);
            }
            else if (datatype == 'CWE') {
                $j('#valueCodedRow').show();
                $j('#valueDrugRow').show();

                // clear any old values:
                $j("#valueCoded").val("");
                $j("#valueCoded_selection").val("");
                $j("#codedDescription").html("");

                // set up the autocomplete for the answers
                var conceptId = $j("#conceptId").val();
                new AutoComplete("valueCoded_selection", new CreateCallback({showAnswersFor: conceptId}).conceptAnswersCallback(), {'minLength':'0'});
                $j("#valueCoded_selection").autocomplete().focus(function(event, ui) {
                    if (event.target.value == "")
                        $j("#valueCoded_selection").trigger('keydown.autocomplete');
                }); // trigger the drop down on focus

                // something in the autocomplete is setting the focus to the conceptId box after
                // this method is done.  get around this and focus on our answer box by putting
                // a very small delay on the call using setTimeout
                setTimeout("$j('#valueCoded_selection').focus();", 0);
            }
            else if (datatype == 'ST') {
                $j('#valueTextRow').show();
            }
            else if (datatype == 'DT' ) {
                $j('#valueDateRow').show();
            }
            else if ( datatype == 'TS' ) {
                $j('#valueDatetimeRow').show();
            }
            else if ( datatype == 'TM') {
                $j('#valueTimeRow').show();
            }
            // TODO move datatype 'TM' to own time box.  How to have them select?
            else if (datatype == 'ED') {
                $j('#valueComplex').show();
            }
            else {
                $j('#valueInvalidRow').show();
                DWRConceptService.getQuestionsForAnswer(tmpConcept.conceptId, fillValueInvalidPossible(tmpConcept));
            }
        }
    }

    function fillNumericUnits(units) {
        $j('#numericUnits').html(units);
    }

    function validateNumericRange(value) {
        if (!isNaN(value) && value != '') {
            var conceptId = $j("#conceptId").val();
            var numericErrorMessage = function(validValue) {
                var errorTag = document.getElementById('numericRangeError');
                errorTag.className = "error";
                if (validValue == false)
                    errorTag.innerHTML = '<openmrs:message code="ConceptNumeric.invalid.msg"/>';
                else
                    errorTag.innerHTML = errorTag.className = "";
            }
            DWRConceptService.isValidNumericValue(value, conceptId, numericErrorMessage);
        }
    }

    function removeHiddenRows() {
        var rows = document.getElementsByTagName("TR");
        var i = 0;
        while (i < rows.length) {
            if (rows[i].style.display == "none")
                rows[i].parentNode.removeChild(rows[i]);
            else
                i = i + 1;
        }
    }

    var fillValueInvalidPossible = function(invalidConcept) {
        return function(questions) {
            var div = document.getElementById('valueInvalidPossibleConcepts');
            div.innerHTML = "";
            var txt = document.createTextNode('<openmrs:message code="Obs.valueInvalid.didYouMean"/> ');
            for (var i=0; i<questions.length && i < 10; i++) {
                if (i == 0)
                    div.appendChild(txt);
                var concept = questions[i];
                var link = document.createElement("a");
                link.href = "#selectAsQuestion";
                link.onclick = selectNewQuestion(concept, invalidConcept);
                link.title = concept.description;
                link.innerHTML = concept.name;
                if (i == (questions.length - 1) || i == 9)
                    link.innerHTML += "?";
                else
                    link.innerHTML += ", ";
                div.appendChild(link);
            }
        }
    }

    var selectNewQuestion = function (question, answer) {
        return function() {
            var msg = new Object();
            msg.objs = [question];
            dojo.event.topic.publish(conceptSearch.eventNames.select, msg);
            msg.objs = [answer];
            dojo.event.topic.publish(codedSearch.eventNames.select, msg);
            return false;
        };
    }

</script>

<style>
    th {
        text-align: left;
    }
    *>#numericRangeError, *>.obsValue {
        visibility: visible;
    }
    #numericRangeError {
        font-weight: bold;
        padding: 2px 4px 2px 4px;
    }
    .numericRangeErrorNormal {
        background-color: green;
        color: white;
    }
    .numericRangeErrorCritical {
        background-color: yellow;
        color: black;
    }
    .numericRangeErrorAbsolute {
        background-color: orange;
        color: white;
    }
    .numericRangeErrorInvalid {
        background-color: red;
        color: white;
    }
    .obsValue {
        display: none;
    }
</style>


<div class="panel panel-default">
    <div class="panel-heading">
        <h2><openmrs:message code="Obs.title"/></h2>
    </div>
    <div class="panel-body">
    <spring:hasBindErrors name="obs">
        <openmrs_tag:errorNotify errors="${errors}" />
    </spring:hasBindErrors>

<div class="col-md-offset-3 col-md-6">
<form method="post"  onSubmit="removeHiddenRows()" enctype="multipart/form-data" action="<c:url value='/module/inpatient/saveObs.form' />" >
    <input type="hidden" name="id" value="${encounterId}" />
    <fieldset>
        <spring:nestedPath path="obs">

            <table id="obsTable">

                <tr>
                    <th><openmrs:message code="Obs.datetime"/></th>
                    <td>
                        <spring:bind path="obsDatetime">
                            <input type="text" name="${status.expression}" size="10"
                                   value="${status.value}" onfocus="showCalendar(this)" id="${status.expression}" />
                            (<openmrs:message code="general.format"/>: <openmrs:datePattern />)
                            <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                        </spring:bind>
                    </td>
                </tr>

                <tr>
                    <th><openmrs:message code="Obs.concept"/></th>
                    <td>
                        <spring:bind path="obs.concept">
                            <openmrs_tag:conceptField formFieldName="concept" formFieldId="conceptId" excludeDatatypes="N/A" initialValue="${status.editor.value.conceptId}" onSelectFunction="onQuestionSelect" />
                            <div class="description" id="conceptDescription"></div>
                            <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                        </spring:bind>
                    </td>
                </tr>
                <c:if test="${1 == 2}">
                    <tr>
                        <th><openmrs:message code="Obs.accessionNumber"/></th>
                        <td>
                            <spring:bind path="accessionNumber">
                                <input type="text" name="${status.expression}" id="accessionNumber" value="${status.value}" size="10" <c:if test="${obs.obsId != null}">disabled</c:if> />
                                <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                            </spring:bind>
                        </td>
                    </tr>
                    <tr>
                        <th><openmrs:message code="Obs.valueGroupId"/></th>
                        <spring:bind path="valueGroupId">
                            <td>
                                <input type="text" name="${status.expression}" id="valueGroupId" value="${status.value}" size="10" <c:if test="${obs.obsId != null}">disabled</c:if> />
                                <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                            </td>
                        </spring:bind>
                    </tr>
                </c:if>
                <tr id="valueBooleanRow" class="obsValue">
                    <th><openmrs:message code="Obs.booleanAnswer"/></th>
                    <spring:bind path="valueBoolean">
                        <td>
                            <select name="${status.expression}" id="valueBooleanSelect">
                                <option value="" <c:if test="${status.value == null || status.value == ''}">selected</c:if>></option>
                                <option value="true" <c:if test="${status.value == 'true'}">selected</c:if>><openmrs:message code="general.true"/></option>
                                <option value="false" <c:if test="${status.value == 'false'}">selected</c:if>><openmrs:message code="general.false"/></option>
                            </select>
                            <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                        </td>
                    </spring:bind>
                </tr>
                <tr id="valueCodedRow" class="obsValue">
                    <th valign="top"><openmrs:message code="Obs.codedAnswer"/></th>
                    <td>
                        <spring:bind path="valueCoded">
                            <openmrs_tag:conceptField formFieldName="valueCoded" formFieldId="valueCoded" initialValue="${status.editor.value.conceptId}" showAnswers="${obs.concept.conceptId}" onSelectFunction="onAnswerSelect"/>
                            <div class="description" id="codedDescription"></div>
                            <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                        </spring:bind>
                    </td>
                </tr>
                <tr id="valueDrugRow" class="obsValue">
                    <th valign="top"><openmrs:message code="Obs.answer.drug"/></th>
                    <td>
                        <spring:bind path="valueDrug">
                            <input type="text" id="valueDrugDisplay" size="45"
                                   <c:if test="${not empty status.editor.value}">value="${status.editor.value.displayName}"</c:if> />
                            <input type="hidden" id="valueDrug" name="valueDrug" value="${status.value}" />
                            <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                        </spring:bind>
                    </td>
                </tr>
                <tr id="valueDateRow" class="obsValue">
                    <th><openmrs:message code="Obs.dateAnswer"/></th>
                    <td>
                        <spring:bind path="valueDate">
                            <input type="text" name="${status.expression}" size="10"
                                   value="${status.value}" onClick="showCalendar(this)" />
                            (<openmrs:message code="general.format"/>: <openmrs:datePattern />)
                            <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                        </spring:bind>
                    </td>
                </tr>
                <tr id="valueDatetimeRow" class="obsValue">
                    <th><openmrs:message code="Obs.datetimeAnswer"/></th>
                    <td>
                        <spring:bind path="valueDatetime">
                            <input type="text" name="${status.expression}" size="15"
                                   value="${status.value}" onClick="showDateTimePicker(this)" />
                            (<openmrs:message code="general.format"/>: <openmrs:datePattern /> <openmrs:timePattern format="jquery"/>)
                            <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                        </spring:bind>
                    </td>
                </tr>
                <tr id="valueTimeRow" class="obsValue">
                    <th><openmrs:message code="Obs.timeAnswer"/></th>
                    <td>
                        <spring:bind path="valueTime">
                            <input type="text" name="${status.expression}" size="10"
                                   value="${status.value}" onfocus="showTimePicker(this)" />
                            (<openmrs:message code="general.format"/>: <openmrs:timePattern format="jquery"/>)
                            <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                        </spring:bind>
                    </td>
                </tr>
                <tr id="valueNumericRow" class="obsValue">
                    <th><openmrs:message code="Obs.numericAnswer"/></th>
                    <spring:bind path="valueNumeric">
                        <td>
                            <input type="text" name="${status.expression}" value="${status.value}" size="10" onKeyUp="validateNumericRange(this.value)"/>
                            <span id="numericUnits"></span>
                            <span id="numericRangeError"></span>
                            <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                        </td>
                    </spring:bind>
                </tr>
                <tr id="valueModifierRow" class="obsValue">
                    <th><openmrs:message code="Obs.valueModifier"/></th>
                    <spring:bind path="valueModifier">
                        <td>
                            <input type="text" name="${status.expression}" id="valueModifierInput" value="${status.value}" size="3" maxlength="2"/>
                            <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                        </td>
                    </spring:bind>
                </tr>
                <tr id="valueTextRow" class="obsValue">
                    <th><openmrs:message code="Obs.textAnswer"/></th>
                    <spring:bind path="valueText">
                        <td>
                            <textarea name="${status.expression}" rows="9" cols="80">${status.value}</textarea>
                            <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                        </td>
                    </spring:bind>
                </tr>
                <tr id="valueComplex" class="obsValue">
                    <th><openmrs:message code="Obs.complexAnswer"/></th>
                    <spring:bind path="valueComplex">
                        <td>
                                ${status.value}<br/>
                            <a href="${hyperlinkView}" target="_blank"><openmrs:message code="Obs.viewCurrentComplexValue"/></a><br/>
                                ${htmlView}<br/><br/>
                            <openmrs:message code="Obs.valueComplex.uploadNew"/>
                            <input type="file" name="complexDataFile" />
                            <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                        </td>
                    </spring:bind>
                </tr>
                <tr id="valueInvalidRow" class="obsValue">
                    <th> &nbsp; </th>
                    <td>
                        <div class="error"><openmrs:message code="Obs.valueInvalid.description"/></div>
                        <div id="valueInvalidPossibleConcepts"></div>
                    </td>
                </tr>

                <openmrs:extensionPoint pointId="org.openmrs.admin.observations.belowValueRow" type="html" parameters="obsId=${obs.obsId}"></openmrs:extensionPoint>

                <tr>
                    <th><openmrs:message code="Obs.comment"/></th>
                    <spring:bind path="comment">
                        <td>
                            <textarea name="${status.expression}" rows="2" cols="45">${status.value}</textarea>
                            <c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
                        </td>
                    </spring:bind>
                </tr>
            </table>
        </spring:nestedPath>

        <input type="hidden" name="phrase" value="<request:parameter name="phrase" />"/>
        <br /><br />

        <input type="submit" class="btn btn-default" name="saveObs" value='<openmrs:message code="Obs.save"/>' <c:if test="${obs.voided}">disabled</c:if> >

        &nbsp;
        <input type="button" class="btn btn-default" value='<openmrs:message code="general.cancel"/>' onclick="history.go(-1);">
    </fieldset>
    </form>
    </div>

</div>
</div>
<div>

</div>
<c:if test="${obsList!=null}">
<div class="panel panel-default">
    <div class="panel-heading"> <h4>Observations</h4></div>
    <div class="panel-body">
        <table class="table table-striped">
            <thead>
            <th>Observation Item</th>
            <th>Observation Value</th>
            <th>Datetime</th>
            </thead>

            <tbody>
            <c:forEach items="${obsList}" var="obsValue">
            <tr>
                <td>${obsValue.concept.name}</td>
                <td>${obsValue.valueNumeric}</td>
                <td>${obsValue.obsDatetime}</td>
            </tr>
            </c:forEach>
            </tbody>

        </table>

    </div>
</div>
</c:if>

<%@ include file="/WEB-INF/template/footer.jsp" %>