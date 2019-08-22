<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<!-- Begin Page Content -->
<div class="container-fluid">

	<!-- 404 Error Text -->
	<div class="text-center">
		<div class="error mx-auto" data-text="404">404</div>
		<p class="lead text-gray-800 mb-5"><spring:message code="error_404"/></p>
		<p class="text-gray-500 mb-0"><spring:message code="error_404_msg"/></p>
		<a href="administrator/dashboard">&larr; <spring:message code="backToDashboard"/></a>
	</div>

</div>
<!-- /.container-fluid -->