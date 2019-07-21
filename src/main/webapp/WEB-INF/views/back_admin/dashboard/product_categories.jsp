<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!-- Begin Page Content -->
<div class="container-fluid">

	<!-- Page Heading -->
	<h1 class="h3 mb-4 text-gray-800"><spring:message code="admin.menu.productcategories"/></h1>
	
		<div class="row">
		
			<div class="col-lg-12">
			
				<!-- Main DataTable Buttons -->
				<div class="card shadow mb-4">
					<div class="card-body" id="newOrUpdateItemCardCFN">
						<div class="row">
							<div class="col-12 labels-left_CFN">
								<!-- Add New Product Category Form -->
								<form:form id="newOrUpdateItemFormCFN" action="administrator/dashboard/productcategories" method="POST" modelAttribute="productCategory"
									class="form-horizontal" enctype="multipart/form-data">
									<form:errors path="*" cssClass="alert alert-danger" element="div"/>
									<fieldset>
										<div class="form-group row">
											<div class="col-sm-3">
												<label for="itemParentCFN"><spring:message code="chooseParent"/></label>
												<form:select id="itemParentCFN" name="parent" path="parent" class="form-control form-control-sm">
													<form:option value="0"><spring:message code="noneF"/></form:option>
													<c:forEach items="${productcategories}" var="productCat">
														<c:if test="${productCat.id == productCat.parent}">
															<form:option value="${productCat.id}">${productCat.title}</form:option>
														</c:if>
													</c:forEach>
												</form:select>
											</div>
											<div class="col-sm-3">
												<label for="itemTitleCFN"><spring:message code="writeTitle"/></label>
												<form:input id="itemTitleCFN" name="title" path="title" type="text" class="form-control form-control-sm" />
											</div>
											<div class="col-sm-3">
												<label for="itemImageCFN"><spring:message code="addImage"/></label>
												<form:input id="itemImageCFN" name="image" path="image" type="file" class="form-control form-control-sm" />
											</div>
											<form:input id="itemIdCFN" name="id" path="id" type="hidden" class="form-control form-control-sm" value="" />
											<div class="col-sm-3">
												<button type="submit" class="btn btn-primary btn-icon-split btn-sm">
													<span class="icon text-white">
														<i class="fas fa-plus"></i>
													</span>
													<span class="text"><spring:message code="add"/></span>
												</button>
												<div class="my-2"></div>
												<button id="deleteMainTableRowCFN" type="button" class="btn btn-danger btn-icon-split btn-sm hidden" disabled>
													<span class="icon text-white">
														<i class="fas fa-trash"></i>
													</span>
													<span class="text"><spring:message code="delete"/></span>
												</button>
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
							<table class="table table-bordered responsive no-wrap" id="mainCategoriesTableCFN" width="100%" cellspacing="0">
								<thead>
									<tr>
										<th>Id</th>
										<th class="hidden">Parent</th>
										<th class="titleHeaderCFN">Title</th>
										<th><spring:message code="extras"/></th>
										<th><spring:message code="products"/></th>
										<th class="imageHeaderCFN">Image</th>
									</tr>
								</thead>
								<tfoot>
									<tr>
										<th>Id</th>
										<th class="hidden">Parent</th>
										<th>Title</th>
										<th><spring:message code="extras"/></th>
										<th><spring:message code="products"/></th>
										<th>Image</th>
									</tr>
								</tfoot>
								<tbody>
									<c:forEach var="productCategory" items="${productcategories}">
										<tr>
											<td class="rowIdCFN">${productCategory.id}</td>
											<td class="parentIdCFN hidden">${productCategory.parent}</td>
											<td>${productCategory.title}</td>
											<td>${fn:length(productCategory.extrascategoriesList)}</td>
											<td>${fn:length(productCategory.productsList)}</td>
											<td>
												<c:if test="${productCategory.hasimage == true}">
													<img src="img/product/category/${productCategory.id}.jpg" />
												</c:if>
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