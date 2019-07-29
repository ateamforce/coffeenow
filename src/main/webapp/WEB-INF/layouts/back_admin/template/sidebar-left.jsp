<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<!-- Sidebar -->
<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

    <!-- Sidebar - Brand -->
    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="administrator/dashboard">
        <img src="img/common/logo-medium-white-min.png" />
    </a>

    <!-- Divider -->
    <hr class="sidebar-divider my-0">

    <!-- Nav Item - Dashboard -->
    <li class="nav-item ${dashboardIsActive}">
        <a class="nav-link" href="administrator/dashboard">
            <i class="fas fa-fw fa-tachometer-alt"></i>
            <span><spring:message code="admin.dashboard"/></span></a>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider">
	
	<!-- Stores -->
    <li class="nav-item ${storesIsActive}">
        <a class="nav-link" href="administrator/dashboard/stores">
            <i class="fas fa-fw fa-store"></i>
            <span><spring:message code="admin.menu.stores"/></span></a>
    </li>
	
	<!-- Divider -->
    <hr class="sidebar-divider">
	
    <!-- Product Categories -->
    <li class="nav-item ${productcategoriesIsActive}">
        <a class="nav-link" href="administrator/dashboard/productcategories">
            <i class="fas fa-fw fa-folder-open"></i>
            <span><spring:message code="admin.menu.productcategories"/></span></a>
    </li>

    <!-- Products -->
    <li class="nav-item ${productsIsActive}">
        <a class="nav-link" href="administrator/dashboard/products">
            <i class="fas fa-fw fa-mug-hot"></i>
            <span><spring:message code="admin.menu.products"/></span></a>
    </li>

    <!-- Extras Categories -->
    <li class="nav-item ${extracategoriesIsActive}">
        <a class="nav-link" href="administrator/dashboard/extracategories">
            <i class="fas fa-fw fa-folder-open"></i>
            <span><spring:message code="admin.menu.extrascategories"/></span></a>
    </li>
	
	<!-- Extras -->
    <li class="nav-item ${extrasIsActive}">
        <a class="nav-link" href="administrator/dashboard/extras">
            <i class="fas fa-fw fa-fill-drip"></i>
            <span><spring:message code="admin.menu.extras"/></span></a>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider d-none d-md-block">

    <!-- Sidebar Toggler (Sidebar) -->
    <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
    </div>

</ul>
<!-- End of Sidebar -->