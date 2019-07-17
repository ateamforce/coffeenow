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
						<button id="addMainTableRowCFN" type="button" href="#" class="btn btn-success btn-circle">
							<i class="fas fa-plus"></i>
						</button>
						<button id="editMainTableRowCFN" type="button" href="#" class="btn btn-warning btn-circle" disabled>
							<i class="fas fa-edit"></i>
						</button>
						<button id="deleteMainTableRowCFN" type="button" href="#" class="btn btn-danger btn-circle" disabled>
							<i class="fas fa-trash"></i>
						</button>
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
							<table class="table table-bordered responsive no-wrap dataTableCFN" id="mainTableCFN" width="100%" cellspacing="0">
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
											<td><img src="/img/product/category/${productCategory.image}" /></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			
			</div>
			
			<div class="col-lg-6">
			
				<!-- Edit Main DataTable's ManyToMany Relationships Card -->
				<div class="card shadow mb-4">
					<div class="card-body">
						
					</div>
				</div>
			
			</div>
		
		</div>

</div>
<!-- /.container-fluid -->