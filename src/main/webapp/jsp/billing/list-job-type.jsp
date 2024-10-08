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


<main id="list-jobType" class="main">
	<div class="pagetitle">
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="/">Home</a></li>
				<li class="breadcrumb-item"><fmt:message key="job.type"/></li>
				<li class="breadcrumb-item active"> <fmt:message key="list"/></li>
			</ol>
		</nav>
	</div>
	<section class="section">
		<div class="row">
			<div class="col-lg-12">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title"> <fmt:message key="job.type"/></h5>
						<button data-bs-target="#ExtralargeModal" data-bs-toggle="modal" onclick="loadPageModalForm('jobtype/displayform')" type="button" class="btn btn-primary" style=" position: relative; left: 94%; width: 77px;">
						  <fmt:message key="add.group"/>
						</button>
						<!-- Table with stripped rows -->
						<table class="table datatable">
						  <thead style="background-color: #dddfe3;">
						    <tr>
						       <th scope="col"><fmt:message key="number"/></th>
						       <th scope="col"><fmt:message key="name"/></th>
						       <th scope="col"><fmt:message key="category"/></th>
						       <th scope="col"><fmt:message key="actions"/></th>
							</tr>
						  </thead>
						  <tbody>
						   <c:forEach var="jobType" items="${jobTypes}" varStatus="loop">
						    <tr class="${loop.index % 2 == 0 ? 'even-row' : 'odd-row'}">
						   <c:set var="index" value="${loop.index}" />
							    <%    int index = (Integer) pageContext.getAttribute("index");  %>
							 <td>  <%= index + 1 %></td>
							   <td>${jobType.name}</td>
							   <td>${jobType.category}</td>
							   <td>
							     <a>
							     <button class="button-see" data-bs-toggle="modal" data-bs-target="#ExtralargeModal" data-toggle="tooltip" data-placement="top" title="view jobType details" onclick="loadPageModalForm('jobtype/viewJobtype/${jobType.id}')">
								     <i class="ri-eye-line"></i>
								   </button>
								    <button class="button-edite" data-bs-toggle="modal" data-bs-target="#ExtralargeModal" data-toggle="tooltip" data-placement="top" title="edit jobType" onclick=" loadPageModalForm('jobtype/update/${jobType.id}')">
								    <i class="ri-pencil-line"></i>
								   </button>
								 </a>
							   </td>
							 </tr>						
						  </c:forEach>
						</tbody>
					  </table>										  					 		
					 <nav aria-label="Page navigation example">
					   <ul class="pagination nav-no-border">
						 <li class="page-item">
						   <input type="button" class="page-link" onclick="refreshTable(${currentPage - 1})" value="&laquo;" ${currentPage == 1 ? 'disabled' : ''}> 
						 </li>
						 <c:forEach var="i" begin="1" end="${totalPages}">
						   <li class="page-item ${i == currentPage ? 'active' : ''}">
							 <input type="button" class="page-link" onclick="refreshTable(${i})" value="${i}">
						   </li>
						   </c:forEach>
						   <li class="page-item">
						     <input type="button" class="page-link" onclick="refreshTable(${currentPage + 1})" value="&raquo;" ${currentPage == totalPages ? 'disabled' : ''}>
						   </li>
					    </ul>
					</nav>
					</div>
				</div>
			</div>
		</div>
	</section>
</main>
<!-- End #main -->
<script src="/DataTables/datatables.js"></script>
<script src="assets/js/users.js"></script>
