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
						<button data-bs-target="#ExtralargeModal" data-bs-toggle="modal" data-toggle="tooltip" data-placement="top" title="add new machine" onclick="loadPageModalForm('machine/add')" type="button" class="btn btn-primary" style=" position: relative; left: 94%; width: 77px;">
						  <fmt:message key="add.group"/>
						</button>
						<div style="position: relative;bottom: 30px;"> 
						    <label style="left: 78%;position: relative; font-family: bold;color: #012970;">Total Machines </label>
						    <span style="left: 80%;position: relative; color: red; font-family: bold;">${totalElement}</span>
					    </div>
					    
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
							   <th><img src="/download/${machine.thumbnail}" class="rounded-circle data-toggle="tooltip" data-placement="top" title="machine logo""></th>
							   <td><a>${machine.name}</a></td>
							   <td><a>${machine.abbreviation}</a></td>
							   <td><a class="${machine.active ? 'Active' : 'Blocked' }">${machine.active ? 'Active' : 'Inactive'}</a></td>
							     <td><a>${machine.plateLength}</a></td>
							     <td><a>${machine.plateWidth}</a></td>
							     
							  <td>
							     <a>
								   <button class="button-see" data-bs-toggle="modal" data-bs-target="#ExtralargeModal" data-toggle="tooltip" data-placement="top" title="view machine details" onclick="loadPageModalForm('machine/viewMachine/${machine.id}')">
								     <i class="ri-eye-line"></i>
								   </button>
								   <button class="button-edite" data-bs-toggle="modal" data-bs-target="#ExtralargeModal" data-toggle="tooltip" data-placement="top" title="edit machine" onclick=" loadPageModalForm('machine/update-form/${machine.id}')">
								    <i class="ri-pencil-line"></i>
								   </button>
								   <button class="button-delete" data-toggle="tooltip" data-placement="top" title="activate or disactivate machine" onclick="disableMachine(${machine.id})" id="startDeleting1" data-bs-toggle="modal">
								     ${machine.active ? '<i class="bi-toggle2-on"></i>' : '<i class="bi-toggle2-off"></i>' }
								   </button>
								 </a>
							   </td>
							 </tr>
							 
						  </c:forEach>
						</tbody>
					</table>
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


