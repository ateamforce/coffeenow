<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<body class="bg-gradient-light">

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
				<form:form id="storeRegistrationFormCFN" action="store/register" method="POST" modelAttribute="newStore"
					class="user" enctype="multipart/form-data">
					<form:errors path="*" cssClass="alert alert-danger" element="div"/>
					<div class="form-group row">
						<div class="col">
							<spring:message code="enterEmailAddress" var="enterEmailAddress"/>
							<form:input name="email" path="email" type="email" class="form-control form-control-user" placeholder="${enterEmailAddress}" required="required" />
						</div>
					</div>
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
					<hr>
					<div class="form-group row">
						<div class="col-sm-6 mb-3 mb-sm-0">
							<spring:message code="enterContactName" var="enterContactName"/>
							<form:input name="contactname" path="contactname" type="text" class="form-control form-control-user" placeholder="${enterContactName}" required="required" />
						</div>
					</div>
					<hr>
					<div class="form-group row">
						<div class="col-sm-6 mb-3 mb-sm-0">
							<spring:message code="enterStoreName" var="enterStoreName"/>
							<form:input name="storename" path="storename" type="text" class="form-control form-control-user" placeholder="${enterStoreName}" required="required" />
						</div>
						<div class="col-sm-6">
							<spring:message code="enterVat" var="enterVat"/>
							<form:input name="vat" path="vat" type="text" class="form-control form-control-user" placeholder="${enterVat}" required="required" />
						</div>
					</div>
					<div class="form-group row">
						<div class="col-sm-6 mb-3 mb-sm-0">
							<spring:message code="enterStorePhone" var="enterStorePhone"/>
							<form:input name="phone" path="phone" type="tel" class="form-control form-control-user" placeholder="${enterStorePhone}" required="required" />
						</div>
						<div class="col-sm-6">
							<spring:message code="enterStoreAddress" var="enterStoreAddress"/>
							<form:input name="address" path="address" type="text" class="form-control form-control-user" placeholder="${enterStoreAddress}" required="required" />
						</div>
					</div>
					<div class="form-group row">
						<div class="col-sm-6 mb-3 mb-sm-0">
							
							<form:select name="state" path="state" class="form-control form-control-user" required="required">
								<form:option value="0"><spring:message code="enterStoreState"/></form:option>
								
								<form:option value="athinwn"><spring:message code="state_athinwn"/></form:option>
								<form:option value="agioOros"><spring:message code="state_agioOros"/></form:option>
								<form:option value="artas"><spring:message code="state_artas"/></form:option>
								<form:option value="axaias"><spring:message code="state_axaias"/></form:option>
								<form:option value="aitoloakarnanias"><spring:message code="state_aitoloakarnanias"/></form:option>
								<form:option value="arkadias"><spring:message code="state_arkadias"/></form:option>
								<form:option value="argolidas"><spring:message code="state_argolidas"/></form:option>
								<form:option value="anatolikisAttikis"><spring:message code="state_anatolikisAttikis"/></form:option>
								
								<form:option value="voiotias"><spring:message code="state_voiotias"/></form:option>
								
								<form:option value="grevenwn"><spring:message code="state_grevenwn"/></form:option>
								
								<form:option value="ditikisAttikis"><spring:message code="state_ditikisAttikis"/></form:option>
								<form:option value="dramas"><spring:message code="state_dramas"/></form:option>
								<form:option value="dwdekanissou"><spring:message code="state_dwdekanissou"/></form:option>
								
								<form:option value="euboias"><spring:message code="state_euboias"/></form:option>
								<form:option value="evrou"><spring:message code="state_evrou"/></form:option>
								<form:option value="evritanias"><spring:message code="state_evritanias"/></form:option>
								
								<form:option value="irakleiou"><spring:message code="state_irakleiou"/></form:option>
								<form:option value="ileias"><spring:message code="state_ileias"/></form:option>
								<form:option value="imathias"><spring:message code="state_imathias"/></form:option>
								
								<form:option value="zakynthou"><spring:message code="state_zakynthou"/></form:option>
								
								<form:option value="thesprotias"><spring:message code="state_thesprotias"/></form:option>
								<form:option value="thessalonikis"><spring:message code="state_thessalonikis"/></form:option>
								
								<form:option value="ioanninwn"><spring:message code="state_ioanninwn"/></form:option>
								
								<form:option value="kavalas"><spring:message code="state_kavalas"/></form:option>
								<form:option value="karditsas"><spring:message code="state_karditsas"/></form:option>
								<form:option value="kukladwn"><spring:message code="state_kukladwn"/></form:option>
								<form:option value="corinthias"><spring:message code="state_corinthias"/></form:option>
								<form:option value="kefallinias"><spring:message code="state_kefallinias"/></form:option>
								<form:option value="kerkyras"><spring:message code="state_kerkyras"/></form:option>
								<form:option value="kastorias"><spring:message code="state_kastorias"/></form:option>
								<form:option value="kilkis"><spring:message code="state_kilkis"/></form:option>
								<form:option value="kozanis"><spring:message code="state_kozanis"/></form:option>
								
								<form:option value="lasithiou"><spring:message code="state_lasithiou"/></form:option>
								<form:option value="lesvou"><spring:message code="state_lesvou"/></form:option>
								<form:option value="lakonias"><spring:message code="state_lakonias"/></form:option>
								<form:option value="leukadas"><spring:message code="state_leukadas"/></form:option>
								<form:option value="larisas"><spring:message code="state_larisas"/></form:option>
								
								<form:option value="messinias"><spring:message code="state_messinias"/></form:option>
								<form:option value="magnisias"><spring:message code="state_magnisias"/></form:option>
								
								<form:option value="xanthis"><spring:message code="state_xanthis"/></form:option>
								
								<form:option value="prevezas"><spring:message code="state_prevezas"/></form:option>
								<form:option value="peiraiws"><spring:message code="state_peiraiws"/></form:option>
								<form:option value="pellas"><spring:message code="state_pellas"/></form:option>
								<form:option value="pierias"><spring:message code="state_pierias"/></form:option>
								
								<form:option value="rethumnis"><spring:message code="state_rethumnis"/></form:option>
								<form:option value="rodopis"><spring:message code="state_rodopis"/></form:option>
								
								<form:option value="samou"><spring:message code="state_samou"/></form:option>
								<form:option value="serrwn"><spring:message code="state_serrwn"/></form:option>
								
								<form:option value="trikalwn"><spring:message code="state_trikalwn"/></form:option>
								
								<form:option value="fokidas"><spring:message code="state_fokidas"/></form:option>
								<form:option value="fthiotidas"><spring:message code="state_fthiotidas"/></form:option>
								<form:option value="florinas"><spring:message code="state_florinas"/></form:option>
								
								<form:option value="ckalkidikis"><spring:message code="state_ckalkidikis"/></form:option>
								<form:option value="chiou"><spring:message code="state_chiou"/></form:option>
								<form:option value="chaniwn"><spring:message code="state_chaniwn"/></form:option>
								
							</form:select>
							
						</div>
						<div class="col-sm-6">
							<spring:message code="enterStoreZip" var="enterStoreZip"/>
							<form:input name="zip" path="zip" type="text" class="form-control form-control-user" placeholder="${enterStoreZip}" required="required" />
						</div>
					</div>
					<div class="form-group row">
						<div class="col">
							<button type="submit" class="btn btn-primary btn-user btn-block"><spring:message code="register"/></button>
						</div>
					</div>
				</form:form>
              <hr>
              <div class="text-center">
                <a class="small" href="#"><spring:message code="login.forgotpassword"/></a>
              </div>
              <div class="text-center">
                <a class="small" href="store"><spring:message code="store.haveAccount"/></a>
              </div>
			  <div class="text-center">
				<a class="small" href="store/register?language=el">Ελληνικά</a> | <a class="small" href="store/register?language=en">English</a>
			  </div>
            </div>
          </div>
        </div>
      </div>
    </div>

  </div>

</body>