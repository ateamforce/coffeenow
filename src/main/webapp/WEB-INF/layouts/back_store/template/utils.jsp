<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
	<i class="fas fa-angle-up"></i>
</a>

<!-- Main Modal-->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="logoutModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">

		</div>
	</div>
</div>

<c:if test="${not empty mainMessage}">
	<!-- MAIN MESSAGE PANEL (ALWAYS HAS TO BE IN UTILS, OR WHEREVER THE CLOSING body TAG IS, IF NO UTILS IS AVAILABLE) -->
	<div id="mainMessagePanelCFN" class="alert alert-success">${mainMessage}</div>
</c:if>