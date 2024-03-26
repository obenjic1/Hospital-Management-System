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
<link href="DataTables/dataTables.dataTables.css"  rel="stylesheet"  />
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
<!-- 			  <img src="assets/img/presprintc.jpg" alt="">  -->
			  <span class="d-none d-lg-block">Billing System</span>
			</a>
		  <i class="bi bi-list toggle-sidebar-btn"></i>
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
				<li class="nav-item d-block d-lg-none">
				    <a class="nav-link nav-icon search-bar-toggle " href="#">
				    <i class="bi bi-search"></i>
				  </a>
				</li>
				
				<!-- End Search Icon-->
				<!------------------ Select language ------------------>
				<li class="choose-language">
				  <a href="${pageContext.request.contextPath}/?lang=fr"> <fmt:message key="lang.fr" /> / </a> 
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
				<!-- End Profile Nav -->
			</ul>
		</nav>
		<!-- End Icons Navigation -->
	</header>
	<!-- End Header -->

	<!-- ======= Sidebar ======= -->
			<aside id="sidebar" class="sidebar">
		  <ul class="sidebar-nav" id="sidebar-nav">
		    <sec:authorize access="hasRole('ROLE_LIST_USERS')">
		      <li class="pp-module">
		        <i class="bi bi-person-lines-fill"></i>
		        <span><fmt:message key="administration.managemant" /></span>
		      </li>
		      <!-- End Printing Press Nav -->		  
		    </sec:authorize>
		    <li class="pp-module">
		      <i class="bx bxl-firebase"></i>
		      <span><fmt:message key="jobsheet.management" /></span>
		      <ul class="nav-content collapse">
		        <li class="nav-item">
		          <a class="nav-link collapsed" href="list-groupes.jsp">
		            <i class="ri-file-list-3-line"></i>
		            <span><fmt:message key="new.controlsheets" /></span>
		          </a>
		        </li>
		        <li class="nav-item">
		          <a class="nav-link collapsed" href="list-groupes.jsp">
		            <i class="bi bi-card-list"></i>
		            <span><fmt:message key="list.controlsheets" /></span>
		          </a>
		        </li>
		        <li class="nav-item">
		          <a class="nav-link collapsed" href="#">
		            <i class="bi bi-receipt-cutoff"></i>
		            <span><fmt:message key="list.ofproforma" /></span>
		          </a>
		        </li>
		        <li class="nav-item">
		          <a class="nav-link collapsed" onclick="getListOfUser()" href="#">
		            <i class="ri-bookmark-2-line"></i>
		            <span><fmt:message key="list.jobsheets" /></span>
		          </a>
		        </li>
		        <li class="nav-item">
		          <a class="nav-link collapsed" onclick="getListOfUser()" href="#">
		            <i class="ri-bit-coin-line"></i>
		            <span><fmt:message key="list.ofbill" /></span>
		          </a>
		        </li>
		        <li class="nav-item">
		          <a class="nav-link collapsed" onclick="getListOfUser()" href="#">
		            <i class="bi bi-lightbulb"></i>
		            <span><fmt:message key="production.management" /></span>
		          </a>
		        </li>
		      </ul>
		    </li>
		  </ul>
		  <!-- End Printing Press Nav -->
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

		<div class="copyright"> &copy; Copyright  <strong><span>Billing system</span></strong>. All Rights Reserved

		
		<div class="credits"> Designed by 
		  <a href="https://bootstrapmade.com/">Presprint Plc</a>
		</div>
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

</body>

</html>