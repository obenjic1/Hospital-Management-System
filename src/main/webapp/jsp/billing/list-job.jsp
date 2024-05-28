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

<main id="list-job" class="main">
	<div class="pagetitle">
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="/">Home</a></li>
				<li class="breadcrumb-item"> job</li>
				<li class="breadcrumb-item active"> List</li>
			</ol>
		</nav>
	</div>
	<section class="section">
		<div class="row">
			<div class="col-lg-12">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Job</h5>
						<button onclick="loadPage('job/displayform')" style="width: 120px;" type="button" class="btn btn-primary">
						  <fmt:message key="add.group"/>
						</button>
						
						<!-- Table with stripped rows -->
						<table class="table datatable">
						  <thead style="background-color: #dddfe3;">
						    <tr>
						      <th scope="col">Number</th>
						       <th scope="col">Job type</th>
						      <th scope="col">Title</th>
							  <th scope="col">Reference</th>
						      <th scope="col">JobStatus</th>
						      <th scope="col">Creation date</th>
						      <th scope="col">Customer</th>
						      <th scope="col">Actions</th>
						      <th scope="col"></th>
						      
							</tr>
						  </thead>
						  <tbody>
			
						  <c:forEach var="job" items="${jobs}" varStatus="loop">
						   <tr class="${loop.index % 2 == 0 ? 'even-row' : 'odd-row'}">
							    <c:set var="index" value="${loop.index}" />
							    <%    int index = (Integer) pageContext.getAttribute("index");  %>
							 <td>  <%= index + 1 %></td>
							   <td><a>${job.title}</a></td>
							   <td><a>${job.jobType.name}</a></td>
							   <td><a>${job.referenceNumber}</a></td>
							   <td><a>${job.status}</a></td>
							   <td><a>${job.creationDate}</a></td>
							   <td><a>${job.customer.name}</a></td>
							     
							  <td>
							  <select id="coverPaperType" name="name" class="form-select">
							     <option value="">Action</option>
							     <option onClick="loadPageModal('/job/viewJob/${job.id}');" >View</option>
							     <option>Delete</option>
							     <option>Update</option>
							     <option>Genrate</option>
				               </select>
							   </td>
							   
							   <td>
							     <a>
								   <button class="button-see" data-bs-toggle="modal" data-bs-target="#ExtralargeModal" onclick="loadPageModal('/job/viewJob/${job.id}')">
								     <i class="fas fa-eye"></i>
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
	<script src="assets/js/main.js"></script>	
	<script src="assets/js/job.js"></script> 


