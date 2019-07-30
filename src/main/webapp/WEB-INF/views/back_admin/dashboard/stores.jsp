<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!-- Begin Page Content -->
<div class="container-fluid positionRel">

	<!-- Page Heading -->
	<h1 class="h3 mb-4 text-gray-800"><spring:message code="admin.menu.stores"/></h1>
		
		<div class="row">
		
			<div class="col-lg-12">
			
				<!-- Main DataTable Card -->
				<div class="card shadow mb-4">
					<div class="card-body">
						<div class="table-responsive">
							<table data-cfn-delUrl="administrator/dashboard/stores/delete/" class="table table-bordered responsive no-wrap" id="mainCategoriesTableCFN" width="100%" cellspacing="0">
								<thead>
									<tr>
										<th class="idHeaderCFN">Id</th>
										<th class="nameHeaderCFN"><spring:message code="admin.menu.store.name"/></th>
										<th class="detailsHeaderCFN"><spring:message code="admin.menu.store.details"/></th>
										<th class="statusHeaderCFN"><spring:message code="admin.menu.store.status"/></th>
										<th class="optionsHeaderCFN"><spring:message code="options"/></th>
									</tr>
								</thead>
								<tfoot>
									<tr>
										<th>Id</th>
										<th><spring:message code="admin.menu.store.name"/></th>
										<th><spring:message code="admin.menu.store.details"/></th>
										<th><spring:message code="admin.menu.store.status"/></th>
										<th><spring:message code="options"/></th>
									</tr>
								</tfoot>
								<tbody>
									<c:forEach var="store" items="${stores}">
										<tr>
											<td class="rowIdCFN">${store.id}</td>
											<td>${store.storename}</td>
											<td>
												<p>${store.address}, ${store.state}, ${store.zip}</p>
												<p>${store.phone}, ${store.email}, ${store.contactname}</p>
												<p>${store.vat}</p>
											</td>
											<td>
												<form method="POST" action="administrator/dashboard/stores">
												
													<c:set var="isDisabledSelected2" value="btn-secondary" scope="page" />
													<c:set var="isEnabledSelected2" value="btn-secondary" scope="page" />
													<c:set var="isWaitSelected2" value="btn-secondary" scope="page" />
													<c:choose>
														<c:when test="${store.enabled == 0}">
															<c:set var="isDisabledSelected" value="checked" scope="page" />
															<c:set var="isDisabledSelected2" value="btn-danger" scope="page" />
														</c:when>   
														<c:when test="${store.enabled == 1}">
															<c:set var="isEnabledSelected" value="checked" scope="page" />
															<c:set var="isEnabledSelected2" value="btn-success" scope="page" />
														</c:when> 
														<c:otherwise>
															<c:set var="isWaitSelected" value="checked" scope="page" />
															<c:set var="isWaitSelected2" value="btn-warning" scope="page" />
														</c:otherwise>
													</c:choose>
													
													<fieldset>
													
														<input class="accountStatusToggleCFN statusDisabledCFN" type="radio" name="enabled" value="0" ${isDisabledSelected}>
														<c:set var="statusDisabled"><spring:message code="admin.menu.store.statusDisabled"/></c:set>
														<button onclick="selectThis(this, 'statusDisabledCFN')" type="button" class="btn ${isDisabledSelected2} btn-circle btn-sm" title="${statusDisabled}">
															<span class="icon text-white">
																<i class="fas fa-times"></i>
															</span>
														</button>
														
														<input class="accountStatusToggleCFN statusEnabledCFN" type="radio" name="enabled" value="1" ${isEnabledSelected}>
														<c:set var="statusEnabled"><spring:message code="admin.menu.store.statusEnabled"/></c:set>
														<button onclick="selectThis(this, 'statusEnabledCFN')" type="button" class="btn ${isEnabledSelected2} btn-circle btn-sm" title="${statusEnabled}">
															<span class="icon text-white">
																<i class="fas fa-check"></i>
															</span>
														</button>
														
														<input class="accountStatusToggleCFN statusWaitCFN" type="radio" name="enabled" value="2" ${isWaitSelected}>
														<c:set var="statusWait"><spring:message code="admin.menu.store.statusWait"/></c:set>
														<button onclick="selectThis(this, 'statusWaitCFN')" type="button" class="btn ${isWaitSelected2} btn-circle btn-sm" title="${statusWait}">
															<span class="icon text-white">
																<i class="fas fa-hourglass-half"></i>
															</span>
														</button>
														
														<input type="hidden" name="storeId" value="${store.id}">
														
													</fieldset>
													
												</form>
											</td>
											<td>
												<button onclick="deleteRow(${store.id})" type="button" class="btn btn-danger btn-circle btn-sm">
													<span class="icon text-white">
														<i class="fas fa-trash"></i>
													</span>
												</button>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			
			</div>
		
		</div>
		
</div>
<!-- /.container-fluid -->