<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<!-- Begin Page Content -->
<div class="container-fluid">

	<!-- Page Heading -->
	<h1 class="h3 mb-4 text-gray-800"><spring:message code="admin.menu.productcategories"/></h1>
	
		<div class="row">
		
			<div class="col-lg-12">
			
				<!-- Main DataTable Buttons -->
				<div class="card shadow mb-4">
					<div class="card-header py-3">
						<h6 class="m-0 font-weight-bold text-primary"><spring:message code="admin.menu.productcategories.help"/></h6>
					</div>
					<div class="card-body">
						<div class="row">
							<div class="col-12 col-xl-3 col-lg-4" id="mainTableButtonsCFN">
								<button id="addMainTableRowCFN" type="button" class="btn btn-success btn-circle">
									<i class="fas fa-plus"></i>
								</button>
								<button id="editMainTableRowCFN" type="button" class="btn btn-info btn-circle" disabled>
									<i class="fas fa-edit"></i>
								</button>
								<button id="deleteMainTableRowCFN" type="button" class="btn btn-danger btn-circle" disabled>
									<i class="fas fa-trash"></i>
								</button>
								<button id="addRemoveMainTableRowCFN" type="button" class="btn btn-warning btn-circle" disabled>
									<i class="fas fa-arrows-alt-h"></i>
								</button>
							</div>
							<div class="col-12 col-xl-9 col-lg-8">
								<div id="mainTableFormsCFN">
								
									<!-- Add New Product Category Form -->
									<form:form id="newCategoryFormCFN" action="administrator/dashboard/productcategories/add" method="POST" modelAttribute="newProductCategory"
										class="form-horizontal hidden" enctype="multipart/form-data">
										<form:errors path="*" cssClass="alert alert-danger" element="div"/>
										<fieldset>
											<div class="form-group row">
												<div class="col-sm-3">
													<label for="categoryParentCFN"><spring:message code="chooseParent"/></label>
													<form:select id="categoryParentCFN" name="parent" path="parent" class="form-control form-control-sm">
														<form:option value="0"><spring:message code="noneF"/></form:option>
														<c:forEach items="${productcategories}" var="productCat">
															<c:if test="${productCat.id == productCat.parent}">
																<form:option value="${productCat.id}">${productCat.title}</form:option>
															</c:if>
														</c:forEach>
													</form:select>
													<form:errors path="parent" cssClass="text-danger"/>
												</div>
												<div class="col-sm-3">
													<label for="categoryTitleCFN"><spring:message code="writeTitle"/></label>
													<form:input id="categoryTitleCFN" name="title" path="title" type="text" class="form-control form-control-sm" />
													<form:errors path="title" cssClass="text-danger"/>
												</div>
												<div class="col-sm-3">
													<label for="categoryImageCFN"><spring:message code="addImage"/></label>
													<form:input id="categoryImageCFN" name="image" path="image" type="file" class="form-control form-control-sm" />
													<form:errors path="image" cssClass="text-danger"/>
												</div>
												<div class="col-sm-3">
													<button type="submit" class="btn btn-primary"><spring:message code="add"/></button>
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
		
		</div>
		
		<div class="row">
		
			<div class="col-lg-6">
			
				<!-- Main DataTable Card -->
				<div class="card shadow mb-4">
					<div class="card-body">
						<div class="table-responsive">
							<table class="table table-bordered responsive no-wrap" id="mainTableCFN" width="100%" cellspacing="0">
								<thead>
									<tr>
										<th>Id</th>
										<th>Parent</th>
										<th class="titleHeaderCFN">Title</th>
										<th>Image</th>
									</tr>
								</thead>
								<tfoot>
									<tr>
										<th>Id</th>
										<th>Parent</th>
										<th>Title</th>
										<th>Image</th>
									</tr>
								</tfoot>
								<tbody>
									<c:forEach var="productCategory" items="${productcategories}">
										<tr>
											<td class="rowIdCFN">${productCategory.id}</td>
											<td>${productCategory.parent}</td>
											<td>${productCategory.title}</td>
											<td><img src="img/product/category/${productCategory.id}.jpg" /></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			
			</div>
			
			<div class="col-lg-6">
				<a href="#scrollToEditCardTop"></a>
				<!-- Edit Main DataTable's ManyToMany Relationships Card -->
				<div class="card border-left-warning shadow py-2">
					<div class="card-body">
						<div class="row no-gutters align-items-center">
							<div class="col mr-2">
								<div class="text-xs font-weight-bold text-warning text-uppercase mb-1"><spring:message code="admin.menu.productcategories.editCatProductsTitle"/></div>
								<div class="h5 mb-0 font-weight-bold text-gray-800"><spring:message code="admin.menu.productcategories.help2"/></div>
							</div>
							<div class="col-auto">
								<i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
							</div>
						</div>
						<div class="row no-gutters align-items-center">
							
						</div>
					</div>
				</div>
			
			</div>
		
		</div>

</div>
<!-- /.container-fluid -->