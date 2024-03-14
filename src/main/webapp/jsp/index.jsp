<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="en">
<head>

<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.23/css/jquery.dataTables.min.css">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>Billing System</title>
<meta content="" name="description">
<meta content="" name="keywords">

<!-- Favicons -->
<link href="assets/img/presprint.jpg" rel="icon">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>


<!-- Google Fonts -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">

<!-- Vendor CSS Files -->
<link href="assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="assets/vendor/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet">
<link href="assets/vendor/boxicons/css/boxicons.min.css"
	rel="stylesheet">
<link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
<link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">

<!-- Template Main CSS File -->
<link href="assets/css/style.css" rel="stylesheet">
<link href="assets/css/smartsheet.css" rel="stylesheet">
</head>

<body>

	<!-- ======= Header ======= -->
	<header id="header" class="header fixed-top d-flex align-items-center">

		<div class="d-flex align-items-center justify-content-between">
			<a href="index" class="logo d-flex align-items-center"> <img
				src="assets/img/presprint.jpg" alt=""> <span
				class="d-none d-lg-block">Billing System</span>
			</a> <i class="bi bi-list toggle-sidebar-btn"></i>
		</div>
		<!-- End Logo -->

		<div class="search-bar">
			<form class="search-form d-flex align-items-center" method="POST"
				action="#">
				<input type="text" name="query" placeholder="Search"
					title="Enter search keyword">
				<button type="submit" title="Search">
					<i class="bi bi-search"></i>
				</button>
			</form>
		</div>
		<!-- End Search Bar -->
		<nav class="header-nav ms-auto">
			<ul class="d-flex align-items-center">
				<li class="nav-item d-block d-lg-none"><a
					class="nav-link nav-icon search-bar-toggle " href="#"> <i
						class="bi bi-search"></i>
				</a></li>
				<!-- End Search Icon-->
				
				<!------------------ Select language ------------------>
				<li class="choose-language">
			      <a href="${pageContext.request.contextPath}/?lang=fr"><fmt:message key="lang.fr" /></a>
			      <a href="${pageContext.request.contextPath}/?lang=en"><fmt:message key="lang.en" /></a>
			    </li> 
	     
				<li class="nav-item dropdown pe-3"><sec:authentication
						property="name" var="username" /> <c:set var="user"
						value="${userAuthenticated}" /> <a
					class="nav-link nav-profile d-flex align-items-center pe-0"
					href="#" data-bs-toggle="dropdown"> <img
						src="<c:url value='/download-profile-image/${imagePath}'/>"
						class="profile-image" /> <span
						class="d-none d-md-block dropdown-toggle ps-2">${username}</span>
				</a> <!-- End Profile Iamge Icon -->
					<ul
						class="dropdown-menu dropdown-menu-end dropdown-menu-arrow profile">
						<li class="dropdown-header">
							<h6>${username}</h6>
						</li>
						<li>
							<hr class="dropdown-divider">
						</li>
						<li><a class="dropdown-item d-flex align-items-center"
							onclick="loadPage('user/viewUser/${username}')" href="#"> <i
								class="bi bi-person"></i> <span>MyProfile</span>
						</a></li>
						<li>
							<hr class="dropdown-divider">
						</li>
						<li><a class="dropdown-item d-flex align-items-center"
							href="/logout"> <i class="bi bi-box-arrow-right"></i> <span><fmt:message
										key="user.sign-out" /></span>
						</a></li>
					</ul> <!-- End Profile Dropdown Items --></li>
				<!-- End Profile Nav -->
			</ul>
		</nav>
		<!-- End Icons Navigation -->
	</header>
	<!-- End Header -->
	
	<!-- ======= Sidebar ======= -->
	<aside id="sidebar" class="sidebar">
		<ul class="sidebar-nav" id="sidebar-nav">
			<li class="pp-module"><i class="bi bi-grid"> </i> <span>
					<fmt:message key="printing.press" />
			</span></li>
			<!-- End Printing Press Nav -->
			<li class="nav-item"><sec:authorize
					access="hasRole('ROLE_ADD_USER')">
					<a class="nav-link collapsed" data-bs-target="#user-management-nav"
						data-bs-toggle="collapse" href="#"> <i class="fas fa-user"></i>
						<h6>
							<fmt:message key="users.management" />
						</h6> <i class="bi bi-chevron-down ms-auto"></i>
					</a>
					<ul id="user-management-nav" class="nav-content collapse "
						data-bs-parent="#sidebar-nav">
						<sec:authorize access="hasRole('ROLE_LIST_USERS')">
							<li class="nav-item"><a class="nav-link collapsed"
								onclick="loadPage('/user/list-users')" href="#"> <i
									class="bi bi-person"></i> <span><fmt:message
											key="list.users" /></span>
							</a></li>
						</sec:authorize>
						<sec:authorize access="hasRole('ROLE_LIST_GROUPS')">
							<li class="nav-item"><a class="nav-link collapsed"
								onclick="loadPage('/group/list-groups')" href="#"><i
									class="bi bi-person"></i> <span><fmt:message
											key="list.groups" /></span></a></li>
						</sec:authorize>

						<sec:authorize access="hasRole('ROLE_LIST_ROLES')">
							<li class="nav-item"><a class="nav-link collapsed"
								onclick="loadPage('/role/list-roles')" href="#"><i
									class="bi bi-card-list"></i> <span><fmt:message
											key="list.roles" /></span> </a></li>
						</sec:authorize>

					</ul>
				</sec:authorize> <!-- End of user management Nav --> <!-- start of job sheet management Nav -->
			<li class="nav-item"><a class="nav-link collapsed"
				data-bs-target="#jobsheet-management-nav" data-bs-toggle="collapse"
				href="#"> <i class="fas fa-users-cog"></i>
					<h6>
						<fmt:message key="jobsheet.management" />
					</h6> <i class="bi bi-chevron-down ms-auto"></i>
			</a>
				<ul id="jobsheet-management-nav" class="nav-content collapse "
					data-bs-parent="#sidebar-nav">
					<li class="nav-item"><a class="nav-link collapsed"
						href="list-groupes.jsp"><i class="bi bi-card-list"></i><span><fmt:message
									key="list.controlsheets" /></span></a></li>
					<li class="nav-item"><a class="nav-link collapsed" href="#"><i
							class="bi bi-person"></i> <span><fmt:message
									key="search.controlsheet" /></span></a></li>
					<!-- End search control sheet Nav -->
					<li class="nav-item"><a class="nav-link collapsed"
						onclick="getListOfUser()" href="#"> <i class="bi bi-person"></i>
							<span><fmt:message key="list.jobsheets" /></span>
					</a></li>
					<li class="nav-item"><a class="nav-link collapsed" href="#">
							<i class="bi bi-person"></i> <span><fmt:message
									key="search.jobsheet" /></span>
					</a></li>

				</ul> <!-- End of job sheet management Nav --> <!-- start of bill management Nav -->
			<li class="nav-item"><a class="nav-link collapsed"
				data-bs-target="#bill-management-nav" data-bs-toggle="collapse"
				href="#"> <i class="fas fa-receipt"></i>
					<h6>
						<fmt:message key="bill.banagement" />
					</h6> <i class="bi bi-chevron-down ms-auto"></i>
			</a>
				<ul id="bill-management-nav" class="nav-content collapse "
					data-bs-parent="#sidebar-nav">
					<%-- 				    <c:if test="${user.groupe.role.name.contains('ROLE_CASHIER')"></c:if>					 --%>
					<li class="nav-item"><a class="nav-link collapsed"
						onclick="getListOfUser()" href="#"> <i class="bi bi-person"></i>
							<span><fmt:message key="list.ofbill" /> List of bill</span>
					</a></li>
					<li class="nav-item"><a class="nav-link collapsed"
						href="user-pages-register"> <i class="fas fa-user"></i><span><fmt:message
									key="search.bill" /></span>
					</a></li>
					<li class="nav-item"><a class="nav-link collapsed" href="#">
							<i class="bi bi-person"></i> <span><fmt:message
									key="list.ofproforma" /></span>
					</a></li>
				</ul> <!-- End of bill management Nav --> <!-- Start of production management Nav -->
			<li class="nav-item"><a class="nav-link collapsed"
				data-bs-target="#production-management-nav"
				data-bs-toggle="collapse" href="#"> <i class="fas fa-cogs"></i>
					<h6>
						<fmt:message key="production.management" />
					</h6> <i class="bi bi-chevron-down ms-auto"></i>
			</a>
				<ul id="production-management-nav" class="nav-content collapse "
					data-bs-parent="#sidebar-nav">

				</ul> <!-- End of production management Nav -->
			<li class="pp-module"><i class="bi bi-grid"> </i> <span>
					<fmt:message key="fabric.Printing" />
			</span></li>
			<li class="pp-module"><i class="bi bi-grid"> </i> <span><fmt:message
						key="screen.Printing" /></span></li>
		</ul>
	</aside>
	<!-- End Sidebar-->

	<main id="main" class="main">
		<div class="pagetitle">
			<h1>
				<spring:message code="greeting" text="default" />
			</h1>
		</div>
		<section class="section dashboard">
			<div class="row">
				<!-- Left side columns -->
				<div id="main-content" class="row"></div>
			</div>
		</section>
	</main>
	<a href="#"
		class="back-to-top d-flex align-items-center justify-content-center"><i
		class="bi bi-arrow-up-short"></i></a>

	<footer id="footer" class="footer">
		<div class="copyright">
			&copy; Copyright <strong><span>Billing system</span></strong>. All Rights
			Reserved
		</div>
		<div class="credits">
			Designed by <a href="https://bootstrapmade.com/">Presprint Plc</a>
		</div>
	</footer>
	<!-- End Footer -->

	<!-- Vendor JS Files -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script
		src="https://cdn.datatables.net/1.10.23/js/jquery.dataTables.min.js"></script>
	<script src="assets/js/role.js"></script>

	<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="assets/vendor/tinymce/tinymce.min.js"></script>

	<!-- Template Main JS File -->
	<script src="assets/js/main.js"></script>
	<script src="assets/js/app.js"></script>
	<script src="assets/js/users.js"></script>
	<script src="assets/js/groups.js"></script>



</body>

</html>