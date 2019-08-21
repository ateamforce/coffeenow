<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<c:if test="${cookie.containsKey('storeSidebarToggled')}">
	<c:set var="toggled" value="toggled" />
</c:if>

<!-- Sidebar -->
<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion ${toggled}" id="accordionSidebar">

    <!-- Sidebar - Brand -->
    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="store/dashboard">
        <img src="img/common/boilerplate/logo-medium-white-min.png" />
    </a>

    <!-- Divider -->
    <hr class="sidebar-divider my-0">

    <!-- Nav Item - Dashboard -->
    <li class="nav-item ${dashboardIsActive}">
        <a class="nav-link" href="store/dashboard">
            <i class="fas fa-fw fa-tachometer-alt"></i>
            <span><spring:message code="admin.dashboard"/></span>
		</a>
    </li>
	
	<!-- Divider -->
    <hr class="sidebar-divider">
	
	<!-- Orders -->
    <li class="nav-item ${ordersIsActive}">
        <a class="nav-link" href="store/dashboard/orders">
            <i class="fas fa-fw fa-book-reader"></i>
            <span><spring:message code="admin.menu.orders"/></span>
		</a>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider">
	
	<!-- Heading -->
	<div class="sidebar-heading">
		<spring:message code="settings"/>
	</div>
	
	<!-- Profile -->
    <li class="nav-item ${profileIsActive}">
        <a class="nav-link" href="store/dashboard/profile">
            <i class="fas fa-fw fa-store"></i>
            <span><spring:message code="profile"/></span>
		</a>
    </li>
	
    <!-- Products -->
    <li class="nav-item ${productsIsActive}">
        <a class="nav-link" href="store/dashboard/profile/products">
            <i class="fas fa-fw fa-mug-hot"></i>
            <span><spring:message code="admin.menu.products"/></span>
		</a>
    </li>

	<!-- Extras -->
    <li class="nav-item ${extrasIsActive}">
        <a class="nav-link" href="store/dashboard/profile/extras">
            <i class="fas fa-fw fa-fill-drip"></i>
            <span><spring:message code="admin.menu.extras"/></span>
		</a>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider d-none d-md-block">

    <!-- Sidebar Toggler (Sidebar) -->
    <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
    </div>

</ul>
<!-- End of Sidebar -->