<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<div class="jumbotron">
	<div class="header">
		<ul class="nav nav-pills pull-right">
			<li><a href='<spring:url value="/"/>'>Home</a></li>
			<li><a href='<spring:url value="/administrator/"/>' class="active">Admin</a></li>
			<li><a href='<spring:url value="/store/"/>'>Store</a></li>
		</ul>
	</div>
</div>