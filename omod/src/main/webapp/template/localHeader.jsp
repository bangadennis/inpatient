<spring:htmlEscape defaultHtmlEscape="true" />
<h2>
	<spring:message code="inpatient.title" />
</h2>

<ul id="menu">
	<li class="first"><a
		href="${pageContext.request.contextPath}/admin">
		<%--<spring:message--%>
				<%--code="admin.title.short" /></a>--%>
			<i class="fa fa-lock fa-5x"></i>

	</li>

	<li
		<c:if test='<%= request.getRequestURI().contains("/manage") %>'>class="active"</c:if> >
		<a
		href="${pageContext.request.contextPath}/module/inpatient/manage.form">
			<%--<spring:message code="inpatient.manage" />--%>
			<i class="fa fa-users fa-5x"></i>
		</a>
	</li>
	
	<!-- Add further links here -->
</ul>

