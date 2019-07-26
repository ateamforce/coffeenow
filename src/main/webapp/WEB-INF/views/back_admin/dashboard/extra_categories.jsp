<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!-- Begin Page Content -->
<div class="container-fluid">

    <!-- Page Heading -->
    <h1 class="h3 mb-4 text-gray-800"><spring:message code="admin.menu.extrascategories"/></h1>

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
                            <!-- Add New Product Category Form -->
                            <form:form id="newOrUpdateItemFormCFN" action="administrator/dashboard/extracategories" method="POST" modelAttribute="extraCategory"
                                       class="form-horizontal" enctype="multipart/form-data">
                                <form:errors path="*" cssClass="alert alert-danger" element="div"/>
                                <fieldset>
                                    <div class="form-group row">
                                        <div class="col-lg-12">
                                            <div class="form-group row">
                                                <div class="col-lg-12">
                                                    <label class="labels-left_CFN" for="itemParentCFN"><spring:message code="chooseParent"/></label>
                                                    <form:select id="itemParentCFN" name="parent" path="parent" class="form-control form-control-sm newOrUpdateItemFormCFN">
                                                        <form:option value="0"><spring:message code="noneI"/></form:option>
                                                        <c:forEach items="${extracategories}" var="extraCat">
                                                            <c:if test="${extraCat.id == extraCat.parent}">
                                                                <form:option value="${extraCat.id}">${extraCat.title}</form:option>
                                                            </c:if>
                                                        </c:forEach>
                                                    </form:select>
                                                </div>
                                                <div class="col-lg-12">
                                                    <label class="labels-left_CFN" for="itemTitleCFN"><spring:message code="writeTitle"/></label>
                                                    <form:input id="itemTitleCFN" name="title" path="title" type="text" class="form-control form-control-sm newOrUpdateItemFormCFN" />
                                                </div>
                                                <div class="col-lg-12">
                                                    <label class="labels-left_CFN" for="itemImageCFN"><spring:message code="addImage"/></label>
                                                    <form:input id="itemImageCFN" name="image" path="image" type="file" class="form-control form-control-sm newOrUpdateItemFormCFN" />
                                                </div>
                                                <form:input id="itemIdCFN" name="id" path="id" type="hidden" class="form-control form-control-sm" value="" />
                                            </div>
                                            <div class="form-group row margin-top-2">
                                                <div class="col-lg-6 col-sm-12 text-left">
                                                    <label class="labels-left_CFN" for="itemProductsCategoriesCFN"><spring:message code="admin.menu.productcategories"/></label>
                                                    <form:select id="itemProductsCategoriesCFN" multiple="true" path="productcategoriesList" items="${productcategories}" itemLabel="title" itemValue="id" name="productcategoriesList" class="multipleSelectCFN_JS"></form:select>
                                                    </div>
                                                    <div class="col-lg-6 col-sm-12 text-left">
                                                        <label class="labels-left_CFN" for="itemExtrasCFN"><spring:message code="extras"/></label>
                                                    <form:select id="itemExtrasCFN" multiple="true" path="extrasList" items="${extras}" itemLabel="title" itemValue="id" name="extrasList" class="multipleSelectCFN_JS"></form:select>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-lg-12">
                                                <div class="row">
                                                    <div class="col-lg-12">
                                                        <button id="newOrUpdateMainTableRowButtonCFN" type="submit" class="btn btn-success btn-icon-split btn-sm newOrUpdateItemFormCFN">
                                                            <span class="icon text-white">
                                                                <i class="fas fa-plus"></i>
                                                            </span>
                                                            <span class="text"><spring:message code="insert"/></span>
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
                        <table data-cnf-delUrl="administrator/dashboard/extracategories/delete/" data-cnf-getOneUrl="administrator/dashboard/extracategories/" class="table table-bordered responsive no-wrap" id="mainCategoriesTableCFN" width="100%" cellspacing="0">
                            <thead>
                                <tr>
                                    <th class="idHeaderCFN">Id</th>
                                    <th class="hidden">Parent</th>
                                    <th class="titleHeaderCFN">Title</th>
                                    <th><spring:message code="admin.menu.productcategories"/></th>
                                    <th><spring:message code="extras"/></th>
                                    <th class="imageHeaderCFN">Image</th>
                                    <th class="optionsHeaderCFN"><spring:message code="options"/></th>
                                </tr>
                            </thead>
                            <tfoot>
                                <tr>
                                    <th>Id</th>
                                    <th class="hidden">Parent</th>
                                    <th>Title</th>
                                    <th><spring:message code="admin.menu.productcategories"/></th>
                                    <th><spring:message code="extras"/></th>
                                    <th>Image</th>
                                    <th><spring:message code="options"/></th>
                                </tr>
                            </tfoot>
                            <tbody>
                                <c:forEach var="extraCategory" items="${extracategories}">
                                    <tr>
                                        <td class="rowIdCFN">${extraCategory.id}</td>
                                        <td class="parentIdCFN hidden">${extraCategory.parent}</td>
                                        <td>${extraCategory.title}</td>
                                        <td>${fn:length(extraCategory.productcategoriesList)}</td>
                                        <td>${fn:length(extraCategory.extrasList)}</td>
                                        <td class="rowImageCFN">
                                            <c:if test="${extraCategory.hasimage == true}">
                                                <img src="img/extra/category/${extraCategory.id}.jpg" />
                                            </c:if>
                                        </td>
                                        <td>
                                            <button onclick="load(${extraCategory.id})" type="button" class="btn btn-warning btn-circle btn-sm updateRowCFN">
                                                <span class="icon text-white">
                                                    <i class="fas fa-edit"></i>
                                                </span>
                                            </button>
                                            <button onclick="deleteRow(${extraCategory.id})" type="button" class="btn btn-danger btn-circle btn-sm">
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