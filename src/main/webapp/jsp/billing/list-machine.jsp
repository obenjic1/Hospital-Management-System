<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="assets/css/list-users.css" rel="stylesheet">
<link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">
<link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">
<link href="assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<link href="assets/css/list-users.css" rel="stylesheet">

<main id="list-machines" class="main">
	<div class="pagetitle">
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="/">Home</a></li>
				<li class="breadcrumb-item"> Machine</li>
				<li class="breadcrumb-item active"> List</li>
			</ol>
		</nav>
	</div>
	<section class="section">
		<div class="row">
			<div class="col-lg-12">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title"> Machines</h5>
						<button onclick="loadPage('machine/add')" type="button" class="btn btn-primary">
						  <fmt:message key="add.group"/>
						</button>
						
						<!-- Table with stripped rows -->
						<table class="table datatable">
						  <thead style="background-color: #dddfe3;">
						    <tr>
						     <th scope="col">Photo</th>
							  <th scope="col">Name</th>
						      <th scope="col">Abbreviation</th>
						       <th scope="col">Status</th>
							  <th scope="col">Plate Length</th>
							  <th scope="col">Plate Width</th>
							  <th scope="col">Actions</th>
							</tr>
						  </thead>
						  <tbody>
						  <c:forEach var="machine" items="${machines}" varStatus="loop">
						    <tr class="${loop.index % 2 == 0 ? 'even-row' : 'odd-row'}">
							   <th><img src="/download/${machine.thumbnail}" class="rounded-circle"></th>
							   <td><a>${machine.name}</a></td>
							   <td><a>${machine.abbreviation}</a></td>
							   <td><a class="${user.is_active ? 'Blocked' : 'Active' }">${user.deleted ? 'Blocked' : 'Active'}</a></td>
							     <td><a>${machine.plateLength}</a></td>
							     <td><a>${machine.plateWidth}</a></td>
							     
							  <td>
							     <a>
								   <button class="button-see" data-bs-toggle="modal" data-bs-target="#ExtralargeModal" onclick="loadPageModal('/machine/viewMachine/${machine.id}')">
								     <i class="fas fa-eye"></i>
								   </button>
								   <button class="button-edite" data-bs-toggle="modal" data-bs-target="#ExtralargeModal" onclick=" loadPageModal('/machine/update-form/${machine.id}')">
								     <i class="fas fa-pencil-alt"></i>
								   </button>
								   <button class="button-delete" onclick="removeMachine(${machine.id})" id="startDeleting1" data-bs-toggle="modal">
								     <i class="fas fa-trash-alt"></i>
								   </button>
								 </a>
							   </td>
							 </tr>
							 <!--------------confirmation to delete Machine ------------->
						     <div class="modal fade" id="areyouSureYouWantToDetele" tabindex="-1">
						       <div class="modal-dialog modal-dialog-centered">
							     <div class="modal-content">
								   <div class="modal-body">
									 <p> <br>Are you sure you want to delete this Machine, this Action cannot be changed </p>
									 <button class="delete-denied" type="button" id="cancelButton" data-bs-dismiss="modal"><fmt:message key="cancel"/></button>
									 <button class="accept-delete" type="button" id="confirmDeleteBtn" data-bs-toggle="modal"><fmt:message key="delete"/></button>
								  </div>
								</div>
							  </div>
							</div>
							
							<!--------------machine created successfully modal ------------->
							  <div class="modal fade" id="machineModal" tabindex="-1">
							    <div class="modal-dialog modal-dialog-centered">
							      <div class="modal-content">
								    <div class="modal-body">
									  <button type="button" style="position: relative; left: 50%; bottom: 12px;"  class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
									  <img src="assets/img/success_icon.png" alt="">
									  <p>Machine Deleted Successfully</p>
									</div>
								  </div>
							    </div>
							  </div>
						  </c:forEach>
						</tbody>
					</table>
						<!--------------MAchine updated successfully modal ------------->
					  <div class="modal fade" id="userUdatedSuccessfully" tabindex="-1">
					    <div class="modal-dialog modal-dialog-centered">
					 	  <div class="modal-content">
						    <div class="modal-body">
							  <button onclick="loadPage('/machine/list')" type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
							  <img src="assets/img/success_icon.png" alt="">
							  <p>Machine Added Successfully</p>
							</div>
						  </div>
					    </div>
					  </div>
						<!------------------Deleted error------------------------------>
						<div class="modal fade" id="confirmationModal" tabindex="-1">
						  <div class="modal-dialog modal-dialog-centered">
							<div class="alert alert-danger alert-dismissible fade show" role="alert">
							      <h2  class="modal title"id="confirmationTitle"></h2>
							    <i class="bi bi-exclamation-octagon me-1"></i>
 								<p id="confirmationMessage"></p>
 								 <button class="btn-close" id="confirmButton">Confirm</button>
     							 <button  class="btn-close" id="cancelButton">Cancel</button>
<!--  							    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button> -->
							  </div>
							</div>
						 </div>
						<!-- Pagination with icons -->
						<nav aria-label="Page navigation example">
						  <ul class="pagination nav-no-border">
							<li class="page-item"><input type="button" class="page-link" onclick="refreshUserTable(${currentPage - 1})" value="&laquo;" ${currentPage == 1 ? 'disabled' : ''}></li>
							<c:forEach var="i" begin="1" end="${totalPages}">
							  <li class="page-item ${i == currentPage ? 'active' : ''}"><input type="button" class="page-link" onclick="refreshUserTable(${i})" value="${i}"></li>
							</c:forEach>
							<li class="page-item"><input type="button" class="page-link" onclick="refreshUserTable(${currentPage + 1})" value="&raquo;" ${currentPage == totalPages ? 'disabled' : ''}></li>
						  </ul>
						</nav>
					</div>
				</div>
			</div>
		</div>
	</section>
</main>
	<script src="assets/js/billing/job-activity-options.js"></script> 
	<script src="assets/js/billing/machine.js"></script> 
	<script src="assets/js/main.js"></script>	
	<script src="assets/js/app.js"></script> 


