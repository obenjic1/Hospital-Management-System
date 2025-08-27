<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="en">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>Queen Mary</title>
<meta content="" name="description">
<meta content="" name="keywords">

<!-- Favicons -->
<link rel="stylesheet" href="assets/sweetalert/sweetalert2.min.css"/>

<link rel="stylesheet" href="DataTables/datatables.css" />
<script src="DataTables/datatables.js"></script>
<link href="assets/img/presprint.jpg" rel="icon">
<link href="assets/img/queenmary-logo.png" rel="icon">

<script src="assets/vendor/jquery-3.5.1.min.js"></script>


<!-- Vendor CSS Files -->
<link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
<link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
<link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">

<!-- Template Main CSS File -->
<link href="assets/css/style.css" rel="stylesheet">
<link href="assets/css/smartsheet.css" rel="stylesheet">
<link href="assets/css/profile.css" rel="stylesheet">
<link href="assets/css/user.css" rel="stylesheet">
<link href="assets/css/update-user.css" rel="stylesheet">

  <link href="assets/vendor/bootstrapValidator.min.css" rel="stylesheet">


</head>

<body>

	<!-- ======= Header ======= -->
	<header id="header" class="header fixed-top d-flex align-items-center">

		<div class="d-flex align-items-center justify-content-between">
		   <a class="logo d-flex align-items-center">
			 <img src="assets/img/queenmary-logo.png" alt="">
			 <span class="d-none d-lg-block">Queen Mary</span>
		   </a>
		   <i class="bi bi-list toggle-sidebar-btn"></i>
		</div>
		<!-- End Logo -->

		<div class="search-bar">
			<form class="search-form d-flex align-items-center" method="GET" action="#">
			  <input type="text" name="referenceNumber" placeholder="Search" title="Enter search keyword" id="search">
			  <button type="button" title="Search" onclick="searchByReference()"> <i class="bi bi-search" ></i> </button>
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
				  <a style="margin-right: 10px;" href="?lang=fr"> <fmt:message key="lang.fr" />  </a> 
				  <a href="?lang=en"> <fmt:message key="lang.en" /> </a>
				</li>

				<li class="nav-item dropdown pe-3">
				  <sec:authentication property="name" var="username" /> 
				  <a class="nav-link nav-profile d-flex align-items-center pe-0" href="#" data-bs-toggle="dropdown">
				   	<c:if test="${not empty user.imagePath}">
                      <img src="${pageContext.request.contextPath}/file/download?file=${user.imagePath}&dir=folder.user.images" class="rounded-circle">
                    </c:if>
                    <c:if test="${empty user.imagePath}">
                    <img src="assets/img/default.png" class="rounded-circle">
                  </c:if>
<%-- 				    <img src="<c:url value='download-profile-image/${imagePath}'/>" class="profile-image" /> --%>
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
						    <span>My Profile</span>
						  </a>
						</li>
						<li> 
						  <hr class="dropdown-divider"> 
						</li>
						<li>
						  <a class="dropdown-item d-flex align-items-center" href="logout">
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
 		     <sec:authorize access="hasRole('ROLE_ADMIN')"> 
			  <li class="pp-module">
			    <i class="bi bi-person-lines-fill"> </i> 
			     <span>
			      <span><fmt:message key="administration.managemant" /></span>
			    </span><script src="DataTables/datatables.js"></script>
			  </li>
			  </sec:authorize> 
			<!-- End Printing Press Nav -->
			<li class="nav-item">
			<sec:authorize access="hasRole('ROLE_CREATE_USER')">					 
			  <ul id="user-management-nav" >
				<sec:authorize access="hasRole('ROLE_ADMIN')"> 
			      <li class="nav-item">
					<a class="nav-link collapsed" onclick="loadPage('user/list-users')" href="#"> <i class="bi bi-person">
					  </i> <span><fmt:message key="list.users" /></span>
					</a>
				  </li>
				</sec:authorize> 
 				<sec:authorize access="hasRole('ROLE_ADD_GROUP')"> 
				  <li class="nav-item">
				    <a class="nav-link collapsed" onclick="loadPage('group/list-groups');refreshGroupTable(1)" href="#">
				    <i class="bi bi-person">
				  </i> <span><fmt:message key="list.groups" /></span></a></li>
				</sec:authorize> 
				<sec:authorize access="hasRole('ROLE_ADMIN')"> 
				  <li class="nav-item"><a class="nav-link collapsed" onclick="loadPage('role/list-roles');refreshRolePage(1)" href="#">
				    <i class="bi bi-card-list"></i> 
				    <span><fmt:message key="list.roles" /></span> </a>
				  </li>
				</sec:authorize> 
			  </ul>
			  
			  <li class="nav-item">
				  <li class="pp-module">
				    <i class="ri-tools-fill"> </i> 
				  <span>
				    <span><fmt:message key="settings" /></span>
				  </span>
				</li>
			  </sec:authorize>  
			  <sec:authorize access="hasRole('ROLE_STAFF_MANAGEMENT')">
				<ul id="configuration-management-nav">
					<li class="nav-item">
                      <a class="nav-link collapsed" onclick="loadPage('staff')" href="#">
						<i class="ri-steam-line"></i>
						<span>Staff Management</span>
					  </a>
					  </li>

					<li class="nav-item">
                      <a class="nav-link collapsed" onclick="loadPage('payroll')" href="#">
						<i class="ri-steam-line"></i>
						<span>Staff Payment</span>
					  </a>
					  </li>
				</ul> 
			  
			  </sec:authorize>
			<!-- End Printing Press Nav -->
		

			  
<%-- 			</sec:authorize> --%>
			 <!-- start of job sheet management Nav -->	
			 
			 <li class="nav-item">
				  <li class="pp-module">
				    <i class="ri-database-2-fill"> </i> 
				  <span>
				    <span><fmt:message key="store.management" /></span>
				  </span>
				</li>
				  <sec:authorize access="hasRole('ROLE_VIEW_STORE')">
				<ul id="store-nav">
					
					<li class="nav-item">
					  <a class="nav-link collapsed" onclick="loadPage('store')" href="#">
					    <i class="ri-steam-fill"></i>
					    <span>Store</span>
					  </a>
					</li>
				</sec:authorize>
 <sec:authorize access="hasRole('ROLE_STAFF_PAYROLL')">
					<li class="nav-item">
					  <a class="nav-link collapsed" onclick="loadPage('invoice/list')" href="#">
					    <i class=" ri-money-dollar-circle-line"></i>
					    <span><fmt:message key="invoice.management" /></span>
					  </a>
					</li>
</sec:authorize>
<!-- 					<li class="nav-item"> -->
<!-- 					  <a class="nav-link collapsed" onclick="loadPage('store')" href="#"> -->
<!-- 					    <i class=" ri-money-dollar-circle-line"></i> -->
<!-- 					     <span>Store</span> -->
<!-- 					  </a> -->
<!-- 					</li> -->
<!-- 					<li class="nav-item"> -->
<!--                       <a class="nav-link collapsed" onclick="loadPage('reports')"" href="#"> -->
<!-- 						<i class="ri-file-list-3-line"></i> -->
<!-- 						<span>Reports</span> -->
<!-- 					  </a> -->
<!-- 					  </li> -->
					<li class="nav-item">
<%-- 				  <sec:authorize access="hasRole('ROLE_SAVE_CUSTOMER')"> --%>
<!-- 					<li class="nav-item"> -->
<!-- 					  <a class="nav-link collapsed" onclick="loadPage('customer/list')" href="#"> -->
<!-- 					    <i class="ri-team-line"></i> -->
<%-- 					    <span><fmt:message key="customer.management" /></span> --%>
<!-- 					  </a> -->
<!-- 					</li> -->
<%-- 				  </sec:authorize>  --%>
				</ul> 
 	 <sec:authorize  access="hasRole('ROLE_PHAMARCY')">  
				<li class="nav-item">
				  <li class="pp-module">
				    <i class="ri-database-2-fill"> </i> 
				  <span>
				    <span><fmt:message key="production.management" /></span>
				  </span>
				</li>
				<ul id="jobsheet-management-nav">
					<li class="nav-item">
                      <a class="nav-link collapsed" onclick="loadPage('pharmacy')" href="#">
						 <i class=" ri-money-dollar-circle-line"></i>
						<span>Pharmacy</span>
					  </a>
					<li class="nav-item">
					</ul> 
	</sec:authorize>
					 <li class="pp-module">
			    <i class="bi bi-person-lines-fill"> </i> 
			     <span>
			      <span>Patient Management</span>
			    </span><script src="DataTables/datatables.js"></script>
			  </li>
						<li class="nav-item">
<%-- 			<sec:authorize access="hasRole('ROLE_ADD_USER')">					 --%>
			  <ul id="user-management-nav" >
				<sec:authorize access="hasRole('ROLE_REGISTER_PATIENT')">
			      <li class="nav-item">
					<a class="nav-link collapsed" onclick="loadPage('patients/new')" href="#"> <i class="bi bi-person">
					  </i> <span>Register Patient</span>
					</a>
				  </li>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_VIEW_PATIENT')">
				  <li class="nav-item">
				    <a class="nav-link collapsed" onclick="loadPage('patients')" href="#">
				    <i class="bi bi-person">
				  </i> <span>List Patients</span></a></li>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_VIEW_SERVICE')">
				  <li class="nav-item"><a class="nav-link collapsed" onclick="loadPage('services')" href="#">
				    <i class="bi bi-card-list"></i> 
				    <span>Services</span> </a>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_VIEW_PAYMENT')">
				 <li class="nav-item"><a class="nav-link collapsed" onclick="loadPage('payments')" href="#">
				    <i class="bi bi-card-list"></i> 
				    <span>Payment</span> </a>
					
<!-- 					<li class="nav-item"> -->
<!-- 					  <a class="nav-link collapsed" onclick="loadPage('payments')" href="#"> -->
<!-- 					    <i class="ri-team-line"></i> -->
<!-- 					    <span>Payment</span> -->
<!-- 					  </a> -->
<!-- 					<li class="nav-item"> -->
<!--                       <a class="nav-link collapsed" onclick="loadPage('reports')"" href="#"> -->
<!-- 						<i class="ri-file-list-3-line"></i> -->
<!-- 						<span>Reports</span> -->
<!-- 					  </a> -->
<!-- 					</li> -->
					<li class="nav-item">
					</sec:authorize>
			  </ul>
					
<!-- 					<li class="nav-item"> -->
<!-- 					  <a class="nav-link collapsed" onclick="loadPage('pharmacy')" href="#"> -->
<!-- 					    <i class=" ri-money-dollar-circle-line"></i> -->
<%-- 					    <span><fmt:message key="invoice.management" /></span> --%>
<!-- 					  </a> -->
<!-- 					</li> -->
<!-- 					<li class="nav-item"> -->
<!--                       <a class="nav-link collapsed" onclick="loadPage('reports')"" href="#"> -->
<!-- 						<i class="ri-file-list-3-line"></i> -->
<!-- 						<span>Reports</span> -->
<!-- 					  </a> -->
<!-- 					<li class="nav-item"> -->
					
<%-- 				  <sec:authorize access="hasRole('ROLE_SAVE_CUSTOMER')"> --%>
<!-- 					<li class="nav-item"> -->
<!-- 					  <a class="nav-link collapsed" onclick="loadPage('customer/list')" href="#"> -->
<!-- 					    <i class="ri-team-line"></i> -->
<%-- 					    <span><fmt:message key="customer.management" /></span> --%>
<!-- 					  </a> -->
<!-- 					</li> -->
					
<%-- 				  </sec:authorize>  --%>
				
<%-- 			 </sec:authorize>  --%>
				<!-- End of job sheet management Nav --> 
				
<%-- 			 </sec:authorize>  --%>
				<!-- End of job sheet management Nav --> 

<%-- 			 <sec:authorize access="hasRole('ROLE_ADMIN')"> --%>
				
<%-- 			  </sec:authorize>  	 --%>
 <sec:authorize access="hasRole('ROLE_ADMIN')">							
			<li class="nav-item">
				  <li class="pp-module">
				    <i class="ri-line-chart-line"> </i> 
				  <span>
				    <span>Statistics</span>
				  </span>
				</li>
				<ul id="configuration-management-nav">
					
					<li class="nav-item">
					  <a class="nav-link collapsed" onclick="loadPage('reports/dashboard') "href="#">
					    <i class="ri-line-chart-line"></i>
					    <span>Statistics</span>
					  </a>
					</li>
					<li class="nav-item">
					  <a class="nav-link collapsed" onclick="loadPage('reports/monthly') "href="#">
					    <i class="ri-line-chart-line"></i>
					    <span>Revenue</span>
					  </a>
					</li>
										
				</ul> 	
				 </sec:authorize> 		
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
			
		  <div id="main-content" class="row">

		  <jsp:include page="user/view-user-dashboard.jsp"></jsp:include>
<%-- 	  <jsp:include page="billing/list-job.jsp"></jsp:include> --%>
		  
		   </div>
		  		  
		  <!-- modal content -->
		   <div>
		     <div class="modal fade" id="creation" tabindex="-1">
	           <div class="modal-dialog modal-dialog-centered">
	             <div class="modal-content">
	               <input type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" style=" left: 93%;  bottom: -7px; ">
	               <div class="modal-body">
	                 <img id="messageImage" src="" alt="">	 
	                 <div id="successMesssage"> </div>
	               </div>
	             </div>
	           </div>
	         </div>	 
	           
			 <div class="modal fade" id="ExtralargeModal" tabindex="-1">
			   <div class="modal-dialog modal-xl">
				 <div class="modal-content" id="addForm">

				        <div class="modal-body"  >
				        </div>
				        <div class="modal-footer" >
				            <button type="button" class="btn btn-secondary"  id="closeaddForm" data-bs-dismiss="modal"><fmt:message key="close"/></button>
				        </div>
			        </div>
			  </div>
			</div>	
			 <div class="modal fade" id="MainModal" tabindex="-1">
			   <div class="modal-dialog modal-xl">
				 <div class="modal-content" id="getPage" style="height: 400px; width: 50%; margin:0 auto">

				        <div class="modal-body"  >
				        </div>
				        <div class="modal-footer" >
				            <button type="button"  id="close-modal" class="btn btn-secondary" data-bs-dismiss="modal"><fmt:message key="close"/></button>
				        </div>
			        </div>
			  </div>
			</div>	
			
			 <div class="modal fade" id="ExtralargeModalFile" tabindex="-1">
			   <div class="modal-dialog modal-xl">
				 <div class="modal-content"  style="height: 800px; width: 100%" >
				 	
				  <iframe src="" height="100%" id="controlSheetViewer" framedorder="0" style="height:100%; width: 100%;display: block; "></iframe>
				
			    </div>
			  </div>
			</div>
			
			<!-------------- Modal ------------->
			<div class="modal fade" id="areyouSureYouWantToDetele" tabindex="-1">
			  <div class="modal-dialog modal-dialog-centered">
			    <div class="modal-content">
			      <div class="modal-body">
	                <p> <br><fmt:message key="desable.anable"/></p>
			        <button class="delete-denied" type="button" id="cancelButton" data-bs-dismiss="modal"><fmt:message key="cancel"/></button>
		          <button class="accept-delete" type="button" id="confirmDeleteBtn" data-bs-toggle="modal" data-bs-target="#creation"><fmt:message key="delete"/></button>
			    </div>
		      </div>
		   </div>
	     </div>	  
	     	<!-------------- Modal ------------->
			<div class="modal fade" id="areyouSureYouWantToAbort" tabindex="-1">
			  <div class="modal-dialog modal-dialog-centered">
			    <div class="modal-content">
			      <div class="modal-body" id ="main-body">
	                <p id="content-message"> 
	                <br><fmt:message key="abort"/>
	                </p>
			        <button class="delete-denied" type="button" id="cancelButton" data-bs-dismiss="modal"><fmt:message key="cancel"/></button>
		          <button class="accept-delete" type="button" id="confirmAbortBtn" data-bs-toggle="modal" data-bs-target="#creation"><fmt:message key="abort.button"/></button>
			    </div>
		      </div>
		   </div>
	     </div>	 
	     
	     <!-------------- Modal ------------->
			<div class="modal fade" id="areyouSureYouWantToConfirm" tabindex="-1">
			  <div class="modal-dialog modal-dialog-centered">
			    <div class="modal-content">
			      <div class="modal-body">
	                <p> <br><fmt:message key="confirm.message"/></p>
			        <button class="delete-denied" type="button" id="cancelButton" data-bs-dismiss="modal"><fmt:message key="cancel"/></button>
		          <button class="accept-delete" type="button" id="approveConfirmBtn" data-bs-toggle="modal" data-bs-target="#creation"><fmt:message key="confirm"/></button>
			    </div>
		      </div>
		   </div>
	     </div>	 
	     	<div class="modal fade" id="areyouSureYouWantToApprove" tabindex="-1">
			  <div class="modal-dialog modal-dialog-centered">
			    <div class="modal-content">
			      <div class="modal-body">
	                <p> <br><fmt:message key="approve.message"/></p>
			        <button class="delete-denied" type="button" id="cancelButton" data-bs-dismiss="modal"><fmt:message key="cancel"/></button>
		          <button class="accept-delete" type="button" id="approveBtn" data-bs-toggle="modal" data-bs-target="#creation"><fmt:message key="aprove"/></button>
			    </div>
		      </div>
		   </div>
	     </div>	
		 </div>
		</div>
	  </section>
	</main>
	<a href="#" class="back-to-top d-flex align-items-center justify-content-center">
	  <i class="bi bi-arrow-up-short"></i>
	</a>

	<footer id="footer" class="fixed-bottom text-center">

		<div class="copyright"> &copy; Copyright 2025  Queen Mary<strong><span></span></strong>. All Rights Reserved</div>
		<div><small>the Best health Care Service  695 697 830 / 679 625 303</small></div>



	</footer>
	<!-- End Footer -->


<script >
$(document).ready(function() {
    refreshRolePage(1);
    refreshGroupTable(1);
});
</script>
    <script src="assets/js/hospital/medicine.js"></script>
    <script src="assets/js/hospital/staff.js"></script>
	<script src="assets/js/billing/job.js"></script>
	<script src="assets/js/statistics/revenue.js"></script> 
	
	<script src="assets/sweetalert/sweetalert2.all.min.js"></script>
	<!-- Template Main JS File -->
    <script src="assets/js/users.js"></script>
	<script src="assets/js/role.js"> </script>
	<script src="assets/js/groups.js"> </script>
	<script src="assets/js/main.js"></script>
	<script src="assets/js/billing/invoice.js"></script>
	<script src="assets/modal/modal.js"></script>	
	
	<script src="assets/js/billing/update-job.js"></script> 
	<script src="assets/js/billing/machine.js"></script> 
	<script src="assets/js/billing/customer.js"></script>
	<script src="assets/js/billing/papertype.js"></script>
	<script src="assets/js/billing/app.js"></script>

	<script src="DataTables/datatables.js"></script>
	<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="assets/vendor/tinymce/tinymce.min.js"></script>
	
	<script src="assets/vendor/bootstrap/bootstrapValidator.min.js"></script>
</body>

</html>