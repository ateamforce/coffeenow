<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!-- Begin Page Content -->
<div class="container-fluid">

	<!-- Page Heading -->
	<h1 class="h3 mb-4 text-gray-800">
		<spring:message code="admin.menu.extras" />
	</h1>
	<!-- Set toggling class for tools sidebar, depending on errors or not -->
	<c:set var="toolsSidebarClass" value="" scope="page" />
	<c:if test="${mainFormHasErrors == true}">
		<c:set var="toolsSidebarClass" value="expandedCFN" scope="page" />
	</c:if>

	<div class="row  ${toolsSidebarClass}" id="newOrUpdateItemCardCFN">
		<div class="switch"><i class="fas fa-cogs"></i></div>
		<div class="col col-lg-12">

			<!-- Main DataTable Buttons -->
			<div class="card mb-4">
				<div class="card-body">
					<div class="row">
						<div class="col-12">
							<!-- Add New Extra Form -->
							<form:form id="newOrUpdateItemFormCFN" action="administrator/dashboard/extras"
								method="POST" modelAttribute="newExtra" class="form-horizontal"
								enctype="multipart/form-data">
								<form:errors path="*" cssClass="alert alert-danger" element="div" />
								<fieldset>
									<div class="form-group row">
										<div class="col-lg-12">
											<div class="form-group row">
												<div class="col-lg-12">
													<label class="labels-left_CFN" for="itemTitleCFN">
														<spring:message code="writeTitle" /></label>
													<form:input id="itemTitleCFN" name="title" path="title" type="text"
														class="form-control form-control-sm newOrUpdateItemFormCFN" />
												</div>
												<div class="col-lg-12">
													<label class="labels-left_CFN" for="itemImageCFN">
														<spring:message code="addImage" /></label>
													<form:input id="itemImageCFN" name="image" path="image" type="file"
														class="form-control form-control-sm newOrUpdateItemFormCFN" />
												</div>
												<form:input id="itemIdCFN" name="id" path="id" type="hidden"
													class="form-control form-control-sm" value="" />
											</div>
											<div class="form-group row margin-top-2">
												<div class="col-lg-6 col-sm-12 text-left">
													<label class="labels-left_CFN" for="itemExtrasCategoriesCFN">
														<spring:message code="admin.menu.extrascategories" /></label>
													<form:select id="itemExtrasCategoriesCFN" multiple="true"
														path="extracategoriesList" items="${extracategories}"
														itemLabel="title" itemValue="id" name="extracategoriesList"
														class="multipleSelectCFN_JS"></form:select>
												</div>
											</div>
										</div>
										<div class="col-lg-12">
											<div class="row">
												<div class="col-lg-12">
													<button id="newOrUpdateMainTableRowButtonCFN" type="submit"
														class="btn btn-success btn-icon-split btn-sm newOrUpdateItemFormCFN">
														<span class="icon text-white">
															<i class="fas fa-plus"></i>
														</span>
														<span class="text">
															<spring:message code="insert" /></span>
													</button>
												</div>
											</div>
										</div>
									</div>
								</fieldset>
							</form:form>
						</div>
					</div>
				</div>
			</div>

		</div>

	</div>

	<div class="row">

		<div class="col-lg-12">

			<!-- Main DataTable Card -->
			<div class="card shadow mb-4">
				<div class="card-body">
					<div class="table-responsive">
						<table data-cfn-delUrl="administrator/dashboard/extras/delete/"
							data-cfn-getOneUrl="administrator/dashboard/extras/"
							class="table table-bordered responsive no-wrap" id="mainCategoriesTableCFN" width="100%"
							cellspacing="0">
							<thead>
								<tr>
									<th class="idHeaderCFN">Id</th>
									<th class="titleHeaderCNF"><spring:message code="thisTitle" /></th>
									<th><spring:message code="admin.menu.extrascategories" /></th>
									<th class="imageHeaderCFN"><spring:message code="image" /></th>
									<th class="optionsHeaderCFN"><spring:message code="options" /></th>
								</tr>
							</thead>
							<tfoot>
								<tr>
									<th>Id</th>
									<th><spring:message code="thisTitle" /></th>
									<th><spring:message code="admin.menu.extrascategories" /></th>
									<th><spring:message code="image" /></th>
									<th><spring:message code="options" /></th>
								</tr>
							</tfoot>
							<tbody>
								<c:forEach var="extra" items="${extras}">
									<tr>
										<td class="rowIdCFN">${extra.id}</td>
										<td class="rowTitleCNF">${extra.title}</td>
										<td>${fn:length(extra.extracategoriesList)}</td>
										<td class="rowImageCFN">
											<c:if test="${extra.hasimage == true}">
												<img src="img/extra/${extra.id}.jpg" />
											</c:if>
										</td>
										<td>
											<button onclick="load(${extra.id})" type="button"
												class="btn btn-warning btn-circle btn-sm updateRowCFN">
												<span class="icon text-white">
													<i class="fas fa-edit"></i>
												</span>
											</button>
											<button onclick="deleteRow(${extra.id})" type="button"
												class="btn btn-danger btn-circle btn-sm">
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