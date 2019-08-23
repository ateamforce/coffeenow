<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<!-- Begin Page Content -->
<div class="container-fluid positionRel">

	<!-- Page Heading -->
	<h1 class="h3 mb-4 text-gray-800"><spring:message code="profile"/></h1>
		
		<div class="row">
		
			<div class="col-xl-4 col-lg-4 col-md-6 col-sm-9 col-xs-12">
			
				<!-- Main Profile Login Card -->
				<div class="card shadow mb-4">
					<div class="card-body">
					  <spring:url value="store/dashboard/profile/auth" var="loginUrl" />
					  <form action=${loginUrl} method="post" class="user">
						<c:choose>
						  <c:when test="${param.error != null}">
							<div class="alert alert-danger">
							  <spring:message code="admin.login.invalid" />
							</div>
						  </c:when>
						  <c:when test="${param.logout != null}">
							<div class="alert alert-success">
							  <spring:message code="admin.login.loggedout" />
							</div>
						  </c:when>
						  <c:when test="${param.accessDenied != null}">
							<div class="alert alert-danger">
							  <spring:message code="admin.login.denied" />
							</div>
						  </c:when>
						  <c:when test="${param.sessionExpired != null}">
							<div class="alert alert-danger">
							  <spring:message code="admin.login.sessionexpired" />
							</div>
						  </c:when>
						</c:choose>
						<div class="form-group">
						  <spring:message code="enterEmailAddress" var="enterEmailAddress" />
						  <input type="email" class="form-control form-control-user" id="userId"
							aria-describedby="emailHelp" placeholder="${enterEmailAddress}" name="username" required>
						</div>
						<div class="form-group">
						  <spring:message code="passwordPlaceholder" var="passwordPlaceholder" />
						  <input type="password" class="form-control form-control-user" id="password"
							placeholder="${passwordPlaceholder}" name="password" required>
						</div>
						<button type="submit" class="btn btn-primary btn-user btn-block">
						  <spring:message code="login" /></button>
					  </form>
					</div>
				</div>
			
			</div>
		
		</div>
		
</div>
<!-- /.container-fluid -->