<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<body class="bg-gradient-light">

  <div class="container">

    <!-- Outer Row -->
    <div class="row justify-content-center">

      <div class="col-xl-10 col-lg-12 col-md-9">

        <div class="card o-hidden border-0 shadow-lg my-5">
          <div class="card-body p-0">
            <!-- Nested Row within Card Body -->
            <div class="row">
              <div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
              <div class="col-lg-6">
                <div class="p-5">
                  <div class="text-center">
                    <h1 class="h4 text-gray-900 mb-4"><spring:message code="admin.login.welcome"/></h1>
                  </div>
				  <spring:url value="store/dashboard/check" var="loginUrl"/>
                  <form action=${loginUrl} method="post" class="user">
					<c:choose>
						<c:when test="${param.error != null}">
							<div class="alert alert-danger">
								<spring:message code="admin.login.invalid"/>
							</div>
						</c:when>
						<c:when test="${param.logout != null}">
							<div class="alert alert-success">
								<spring:message code="admin.login.loggedout"/>
							</div>
						</c:when>
						<c:when test="${param.accessDenied != null}">
							<div class="alert alert-danger">
								<spring:message code="admin.login.denied"/>
							</div>
						</c:when>
						<c:when test="${param.sessionExpired != null}">
							<div class="alert alert-danger">
								<spring:message code="admin.login.sessionexpired"/>
							</div>
						</c:when>
					</c:choose>
                    <div class="form-group">
					  <spring:message code="enterEmailAddress" var="enterEmailAddress"/>
                      <input type="email" class="form-control form-control-user" id="userId" aria-describedby="emailHelp" placeholder="${enterEmailAddress}" name="username" required>
                    </div>
                    <div class="form-group">
					  <spring:message code="passwordPlaceholder" var="passwordPlaceholder"/>
                      <input type="password" class="form-control form-control-user" id="password" placeholder="${passwordPlaceholder}" name="password" required>
                    </div>
                    <div class="form-group">
                      <div class="custom-control custom-checkbox small">
                        <input type="checkbox" class="custom-control-input" id="rememberMe">
                        <label class="custom-control-label" for="rememberMe"><spring:message code="login.rememberme"/></label>
                      </div>
                    </div>
					<button type="submit" class="btn btn-primary btn-user btn-block"><spring:message code="login"/></button>
                  </form>
                  <hr>
                  <div class="text-center">
                    <a class="small" href="#"><spring:message code="login.forgotpassword"/></a>
                  </div>
				  <div class="text-center">
                    <a class="small" href="store?language=el">Ελληνικά</a> | <a class="small" href="store?language=en">English</a>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

      </div>

    </div>

  </div>

</body>