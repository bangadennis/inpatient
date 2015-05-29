<spring:htmlEscape defaultHtmlEscape="true" />

<ul id="menu">
	<li class="first"><a href="${pageContext.request.contextPath}/admin">
		<spring:message
				code="admin.title.short" /></a>
			<%--<i class="fa fa-cog fa-5x"></i>--%>

	</li>

	<li <c:if test='<%= request.getRequestURI().contains("/manage") %>'>class="active"</c:if> >
		<a href="${pageContext.request.contextPath}/module/inpatient/manage.form">
			<spring:message code="inpatient.manage" />
			<%--<i class="fa fa-users fa-5x"></i>--%>
		</a>
	</li>

	<li <c:if test='<%= request.getRequestURI().contains("/addward") %>'>class="active"</c:if> >
		<a href="${pageContext.request.contextPath}/module/inpatient/addward.form">
			<spring:message code="Add Ward" />
			<%--<i class="fa fa-plus-square fa-5x"></i>--%>
		</a>
	</li>


	<li
			<c:if test='<%= request.getRequestURI().contains("/listwards") %>'>class="active"</c:if> >
		<a href="${pageContext.request.contextPath}/module/inpatient/listwards.form">
			<spring:message code="List Wards" />
			<%--<i class="fa fa-list fa-5x"></i>--%>
		</a>
	</li>

	<li
			<c:if test='<%= request.getRequestURI().contains("/listPatient") %>'>class="active"</c:if> >
		<a href="${pageContext.request.contextPath}/module/inpatient/listPatient.form">
			<spring:message code="Add patient" />
			<%--<i class="fa fa-list fa-5x"></i>--%>
		</a>
	</li>
	
	<!-- Add further links here -->

</ul>
<h2>
	<spring:message code="inpatient.title" />
</h2>

