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

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>Billing System</title>
<meta content="" name="description">
<meta content="" name="keywords">

<!-- Favicons -->
<link href="assets/img/presprint.jpg" rel="icon">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>


<!-- Google Fonts -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">

<!-- Vendor CSS Files -->
<link href="/DataTables/dataTables.dataTables.css"  rel="stylesheet"  />
<link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
<link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
<link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">

<!-- Template Main CSS File -->
<link href="assets/css/style.css" rel="stylesheet">
<link href="assets/css/smartsheet.css" rel="stylesheet">
<link href="assets/css/profile.css" rel="stylesheet">


</head>

<body>

	<!-- ======= Header ======= -->
	<header id="header" class="header fixed-top d-flex align-items-center">

		<div class="d-flex align-items-center justify-content-between">
		   <a href="index" class="logo d-flex align-items-center">
			 <img src="assets/img/logo.png" alt="">
			 <span class="d-none d-lg-block">Billing System</span>
		   </a>
		   <i class="bi bi-list toggle-sidebar-btn"></i>
		</div>
		<!-- End Logo -->

		<div class="search-bar">
			<form class="search-form d-flex align-items-center" method="POST" action="#">
			  <input type="text" name="query" placeholder="Search" title="Enter search keyword">
			  <button type="submit" title="Search"> <i class="bi bi-search"></i> </button>
			</form>
		</div>
		<!-- End Search Bar -->
		<nav class="header-nav ms-auto">
			<ul class="d-flex align-items-center">
				<li class="nav-item d-block d-lg-none">
				  <a class="nav-link nav-icon search-bar-toggle " href="#">
				    <i class="bi bi-search"></i>
				  </a>
				</li>
				
				<!-- End Search Icon-->
				<!------------------ Select language ------------------>
				<li class="choose-language">
				  <a style="margin-right: 10px;" href="${pageContext.request.contextPath}/?lang=fr"> <fmt:message key="lang.fr" />  </a> 
				  <a href="${pageContext.request.contextPath}/?lang=en"> <fmt:message key="lang.en" /> </a>
				</li>

				<li class="nav-item dropdown pe-3">
				  <sec:authentication property="name" var="username" /> 
				  <c:set var="user" value="${userAuthenticated}" /> 
				  <a class="nav-link nav-profile d-flex align-items-center pe-0" href="#" data-bs-toggle="dropdown"> 
				    <img src="<c:url value='/download-profile-image/${imagePath}'/>" class="profile-image" /> 
					<span class="d-none d-md-block dropdown-toggle ps-2">${username}</span>
				  </a> <!-- End Profile Iamge Icon -->
				  
					<ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow profile">
						<li class="dropdown-header"> 
						  <h6>${username}</h6> 
						</li>
						<li> 
						  <hr class="dropdown-divider"> 
						</li>
						<li>
						  <a class="dropdown-item d-flex align-items-center" onclick="loadPage('user/viewUser/${username}')" href="#"> 
						    <i class="bi bi-person"></i>
						    <span>MyProfile</span>
						  </a>
						</li>
						<li> 
						  <hr class="dropdown-divider"> 
						</li>
						<li>
						  <a class="dropdown-item d-flex align-items-center" href="/logout">
						    <i class="bi bi-box-arrow-right"></i>
						    <span><fmt:message key="user.sign-out" /></span>
						  </a>
						</li>
					</ul> <!-- End Profile Dropdown Items --></li>
			  </ul>
		  </nav>
		<!-- End Icons Navigation -->
	  </header>
	<!-- End Header -->

	<!-- ======= Sidebar ======= -->
			<aside id="sidebar" class="sidebar" style="background: #dddfe3;">
		     <ul class="sidebar-nav" id="sidebar-nav">
			  <li class="pp-module">
			    <i class="bi bi-person-lines-fill"> </i> 
			     <span>
			      <span><fmt:message key="administration.managemant" /></span>
			    </span>
			  </li>
			<!-- End Printing Press Nav -->
			<li class="nav-item">
			<sec:authorize access="hasRole('ROLE_ADD_USER')">					
			  <ul id="user-management-nav" >
				<sec:authorize access="hasRole('ROLE_LIST_USERS')">
			      <li class="nav-item">
					<a class="nav-link collapsed" onclick="loadPage('/user/list-users')" href="#"> <i class="bi bi-person">
					  </i> <span><fmt:message key="list.users" /></span>
					</a>
				  </li>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_LIST_GROUPS')">
				  <li class="nav-item">
				    <a class="nav-link collapsed" onclick="loadPage('/group/list-groups')" href="#">
				    <i class="bi bi-person">
				  </i> <span><fmt:message key="list.groups" /></span></a></li>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_LIST_ROLES')">
				  <li class="nav-item"><a class="nav-link collapsed" onclick="loadPage('/role/list-roles')" href="#">
				    <i class="bi bi-card-list"></i> 
				    <span><fmt:message key="list.roles" /></span> </a>
				  </li>
				</sec:authorize>
			  </ul>
			</sec:authorize> 								
				<!-- End of user management Nav --> <!-- start of job sheet management Nav -->			
				<li class="nav-item">
				  <li class="pp-module">
				    <i class="ri-database-2-fill"> </i> 
				  <span>
				    <span><fmt:message key="production.management" /></span>
				  </span>
				</li>
				<ul id="jobsheet-management-nav">
					<li class="nav-item">
                      <a class="nav-link collapsed" href="list-groupes.jsp">
						<i class="ri-file-list-3-line"></i>
						<span><fmt:message key="new.controlsheets" /></span>
					  </a>
					<li class="nav-item">
					  <a class="nav-link collapsed" onclick="getListOfUser()" href="#">
					    <i class="ri-steam-fill"></i>
					    <span><fmt:message key="list.jobsheets" /></span>
					  </a>
					</li>
				  <sec:authorize access="hasRole('ROLE_SAVE_CUSTOMER')">
					<li class="nav-item">
					  <a class="nav-link collapsed" onclick="loadPage('/customer/list')" href="#">
					    <i class="ri-team-line"></i>
					    <span><fmt:message key="customer.management" /></span>
					  </a>
					</li>
				  </sec:authorize> 

					<li class="nav-item">
					  <a class="nav-link collapsed" onclick="getListOfUser()" href="#">
					    <i class="ri-keyboard-fill"></i>
					    <span><fmt:message key="prepress" /> </span>
					  </a>
					</li>	
					<li class="nav-item">
					  <a class="nav-link collapsed" onclick="getListOfUser()" href="#">
					    <i class="ri-keyboard-box-line"></i>
					    <span><fmt:message key="printing" /> </span>
					  </a>
					</li>
					<li class="nav-item">
					  <a class="nav-link collapsed" onclick="getListOfUser()" href="#">
					    <i class="ri-rainbow-line"></i>
					    <span><fmt:message key="finishing" /> </span>
					  </a>
					</li>
					<li class="nav-item">
					  <a class="nav-link collapsed" onclick="getListOfUser()" href="#">
					    <i class="ri-file-copy-2-line"></i>
					    <span><fmt:message key="paper" /> </span>
					  </a>
					</li>
				</ul> 
				<!-- End of job sheet management Nav --> <!-- start of bill management Nav -->	
			  <sec:authorize access="hasRole('ROLE_VIEW_SETINGS')">
				<li class="nav-item">
				  <li class="pp-module">
				    <i class="ri-tools-fill"> </i> 
				  <span>
				    <span><fmt:message key="settings" /></span>
				  </span>
				</li>
			  </sec:authorize> 
			  <sec:authorize access="hasRole('ROLE_VIEW_SETINGS')">
				<ul id="configuration-management-nav">
					<li class="nav-item">
                      <a class="nav-link collapsed" onclick="loadPage('machine/getListPage')" href="#">
						<i class="ri-steam-line"></i>
						<span><fmt:message key="machines" /></span>
					  </a>
										
					<li class="nav-item">
					  <a class="nav-link collapsed" onclick="loadPage('/papertype/list')" href="#">
					    <i class="bi bi-receipt-cutoff"></i>
					    <span><fmt:message key="paper.types"/></span>
					  </a>
					</li>
					
					<li class="nav-item">
					  <a class="nav-link collapsed" onclick="loadPage('activityOption/list-activityOptions')" href="#">
					    <i class="ri-bit-coin-line"></i>
					    <span>Job activities options</span>
					  <a class="nav-link collapsed" onclick="getListOfUser()" href="#">
					    <i class="ri-compasses-line"></i>
					    <span><fmt:message key="job.activities.options" /> </span>
					  </a>
					</li>
					<li class="nav-item">
					  <a class="nav-link collapsed" onclick="getListOfUser()" href="#">
					    <i class="ri-file-damage-line"></i>
					    <span><fmt:message key="paper.grammage" /> </span>
					  </a>
					</li>
					<li class="nav-item">
					  <a class="nav-link collapsed" onclick="getListOfUser()" href="#">
					    <i class="ri-file-edit-line"></i>
					    <span><fmt:message key="paper.format" /> </span>
					  </a>
					</li>
					<li class="nav-item">
					  <a class="nav-link collapsed" onclick="getListOfUser()" href="#">
					    <i class="ri-hammer-line"></i>
					    <span><fmt:message key="job.type" /> </span>
					  </a>
					</li>
					<li class="nav-item">
					  <a class="nav-link collapsed" onclick="getListOfUser()" href="#">
					    <i class="ri-key-2-line"></i>
					    <span><fmt:message key="job.tracking" /> </span>
					  </a>
					</li>
					<li class="nav-item">
					  <a class="nav-link collapsed" onclick="getListOfUser()" href="#">
					    <i class="ri-more-fill"></i>
					    <span><fmt:message key="job.status" /> </span>
					  </a>
					</li>
					<li class="nav-item">
					  <a class="nav-link collapsed" onclick="getListOfUser()" href="#">
					    <i class="ri-pantone-fill"></i>
					    <span><fmt:message key="binding.type" /> </span>
					  </a>
					</li>
					<li class="nav-item">
					  <a class="nav-link collapsed" onclick="getListOfUser()" href="#">
					    <i class="ri-slack-fill"></i>
					    <span><fmt:message key="job.activity" /> </span>
					  </a>
					</li>
					<li class="nav-item">
					  <a class="nav-link collapsed" onclick="getListOfUser()" href="#">
					    <i class="ri-signal-tower-line"></i>
					    <span><fmt:message key="job.operation.options" /> </span>
					  </a>
					</li>
					<li class="nav-item">
					  <a class="nav-link collapsed" onclick="getListOfUser()" href="#">
					    <i class="ri-stack-line"></i>
					    <span><fmt:message key="job.paper" /> </span>
					  </a>
					</li>
					<li class="nav-item">
					  <a class="nav-link collapsed" onclick="getListOfUser()" href="#">
					    <i class="ri-stack-fill"></i>
					    <span><fmt:message key="content.type" /> </span>
					  </a>
					</li>
					<li class="nav-item">
					  <a class="nav-link collapsed" onclick="getListOfUser()" href="#">
					    <i class="ri-test-tube-fill"></i>
					    <span><fmt:message key="color.combinaition" /> </span>
					  </a>
					</li>						
					<li class="nav-item">
					  <a class="nav-link collapsed" onclick="getListOfUser()" href="#">
					    <i class="ri-todo-line"></i>
					    <span><fmt:message key="print.type" /> </span>
					  </a>
					</li>
					
				</ul> 
			  </sec:authorize> 
				
				<!-- End of job sheet management Nav --> <!-- start of bill management Nav -->
			<li class="nav-item">
				  <li class="pp-module">
				    <i class="ri-line-chart-line"> </i> 
				  <span>
				    <span>Statistics</span>
				  </span>
				</li>
				<ul id="configuration-management-nav">
					
					<li class="nav-item">
					  <a class="nav-link collapsed" onclick="getListOfUser()" href="#">
					    <i class="ri-line-chart-line"></i>
					    <span>Statistics</span>
					  </a>
					</li>
										
				</ul> 			
		</ul>
	</aside>
	<!-- End Sidebar-->

	<main id="main" class="main">
	  <div class="pagetitle">
		<h1><spring:message code="greeting" text="default" /> </h1>
	  </div>
	  <section class="section dashboard">
		<div class="row">
		<!-- Left side columns -->
		  <div id="main-content" class="row"></div>
		</div>
	  </section>
	</main>
	<a href="#" class="back-to-top d-flex align-items-center justify-content-center">
	  <i class="bi bi-arrow-up-short"></i>
	</a>

	<footer id="footer" class="fixed-bottom text-center">

		<div class="copyright"> &copy; Copyright 2024 <strong><span>Presprint Plc</span></strong>. All Rights Reserved</div>


<!-- 		<div class="credits"> -->
<!-- 			Designed by <a href="https://bootstrapmade.com/">Presprint Plc 20</a> -->
<!-- 		</div> -->
	</footer>
	<!-- End Footer -->

	<!-- Vendor JS Files -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://cdn.datatables.net/1.10.23/js/jquery.dataTables.min.js"></script>
	<script src="assets/js/role.js"></script>

	<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="assets/vendor/tinymce/tinymce.min.js"></script>

	<!-- Template Main JS File -->
	<script src="/DataTables/dataTables.js"></script>
	<script src="assets/js/role.js"> </script>
	<script src="assets/js/main.js"></script>
	<script src="assets/js/app.js"></script>
	<script src="assets/js/users.js"></script>
	<script src="assets/js/groups.js"></script>
    <script src="assets/js/modal.js"></script>
	<script src="assets/js/billing/list-customer.js"></script>
	<script src="assets/js/billing/customer.js"></script>
	<script src="assets/js/billing/machine.js"></script> 
	<script src="assets/js/modal.js"></script>
	<script src="assets/js/billing/activityOptions"></script> 
	<script src="assets/js/billing/papertype.js"></script>

</body>

</html>