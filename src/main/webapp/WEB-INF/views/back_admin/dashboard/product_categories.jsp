<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<!-- Begin Page Content -->
<div class="container-fluid">

	<!-- Page Heading -->
	<h1 class="h3 mb-4 text-gray-800"><spring:message code="admin.menu.productcategories"/></h1>
	<p class="mb-4"><spring:message code="admin.menu.productcategories.desc"/></p>
	
          <!-- DataTales Example -->
          <div class="card shadow mb-4">
            <div class="card-header py-3">
              <h6 class="m-0 font-weight-bold text-primary"><spring:message code="admin.menu.productcategories.help"/></h6>
            </div>
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  <thead>
                    <tr>
					  <th>Id</th>
                      <th>Parent</th>
                      <th>Title</th>
                      <th>Image</th>
					  <th><spring:message code="options"/></th>
                    </tr>
                  </thead>
                  <tfoot>
                    <tr>
                      <th>Id</th>
                      <th>Parent</th>
                      <th>Title</th>
					  <th>Image</th>
                      <th><spring:message code="options"/></th>
                    </tr>
                  </tfoot>
                  <tbody>
					<c:forEach var="productCategory" items="${productcategories}">
						<tr>
							<td class="rowIdCFN">${productCategory.id}</td>
							<td>${productCategory.parent}</td>
							<td>${productCategory.title}</td>
							<td>${productCategory.image}</td>
							<td class="optionsCFN"></td>
						</tr>
					</c:forEach>
                  </tbody>
                </table>
              </div>
            </div>
          </div>

</div>
<!-- /.container-fluid -->