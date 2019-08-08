<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<body class="bg-gradient-primary">

  <div class="container">

    <div class="card o-hidden border-0 shadow-lg my-5">
      <div class="card-body p-0">
        <!-- Nested Row within Card Body -->
        <div class="row">
          <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
          <div class="col-lg-7">
            <div class="p-5">
              <div class="text-center">
                <h1 class="h4 text-gray-900 mb-4"><spring:message code="store.createAccount"/></h1>
              </div>
				<form:form id="storeRegistrationFormCFN" action="store/register/resend/confirm" method="POST" modelAttribute="newStore"
					class="user" enctype="multipart/form-data">
					<form:errors path="*" cssClass="alert alert-danger" element="div"/>
					<div class="form-group row">
						<div class="col">
							<spring:message code="enterEmailAddress" var="enterEmailAddress"/>
							<form:input name="email" path="email" type="email" class="form-control form-control-user" placeholder="${enterEmailAddress}" required="required" />
						</div>
					</div>
					<hr>
					<div class="form-group row">
						<div class="col">
							<button type="submit" class="btn btn-primary btn-user btn-block"><spring:message code="register"/></button>
						</div>
					</div>
				</form:form>
              <hr>
              <div class="text-center">
                <a class="small" href="store"><spring:message code="store.haveAccount"/></a>
              </div>
			  <div class="text-center">
				<a class="small" href="store/register/resend/confirm?language=el">Ελληνικά</a> | <a class="small" href="store/register/resend/confirm?language=en">English</a>
			  </div>
            </div>
          </div>
        </div>
      </div>
    </div>

  </div>

</body>