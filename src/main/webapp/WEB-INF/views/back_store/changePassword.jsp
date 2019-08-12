<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<c:if test="${not empty id}">
<c:if test="${not empty token}">
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
                <h1 class="h4 text-gray-900 mb-4"><spring:message code="message.resetPassword"/></h1>
              </div>
				<form:form id="storeChangePasswordFormCFN" action="store/reset/password/change?id=${id}&token=${token}" method="POST" modelAttribute="passwordDto"
					class="user">
					<form:errors path="*" cssClass="alert alert-danger" element="div"/>
					<div class="form-group row">
						<div class="col-sm-6 mb-3 mb-sm-0">
							<spring:message code="passwordPlaceholder" var="passwordPlaceholder"/>
							<form:input name="password" path="password" type="password" class="form-control form-control-user" placeholder="${passwordPlaceholder}" required="required" />
						</div>
						<div class="col-sm-6">
							<spring:message code="passwordRepeatPlaceholder" var="passwordRepeatPlaceholder"/>
							<form:input name="passwordRepeat" path="passwordRepeat" type="password" class="form-control form-control-user" placeholder="${passwordRepeatPlaceholder}" required="required" />
						</div>
					</div>
					<div class="form-group row">
						<div class="col">
							<button type="submit" class="btn btn-primary btn-user btn-block"><spring:message code="submit"/></button>
						</div>
					</div>
				</form:form>
              <hr>
			  <div class="text-center">
				<a class="small" href="store/reset/password/change?language=el">Ελληνικά</a> | <a class="small" href="store/reset/password/change?language=en">English</a>
			  </div>
            </div>
          </div>
        </div>
      </div>
    </div>

  </div>

</body>
</c:if>
</c:if>
