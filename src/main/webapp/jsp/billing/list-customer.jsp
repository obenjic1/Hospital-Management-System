<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<meta content="width=device-width, initial-scale=1.0" name="viewport">

<link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">
<link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">
<link href="assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<link href="assets/css/list-users.css" rel="stylesheet">

<main id="list-customer" class="main">
	<div class="pagetitle">
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="/">Home</a></li>
				<li class="breadcrumb-item"><fmt:message key="customer"/></li>
				<li class="breadcrumb-item active"> <fmt:message key="list"/></li>
			</ol>
		</nav>
	</div>
	<section class="section">
		<div class="row">
			<div class="col-lg-12">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title"> <fmt:message key="customers"/></h5>
						<button data-bs-toggle="modal" data-bs-target="#ExtralargeModal" onclick="loadPageModalForm('customer/displayCustomerForm')" type="button" class="btn btn-primary" style=" position: relative; left: 94%; width: 77px;">
						  <fmt:message key="add.group"/>
<!-- 						   data-bs-toggle="modal" data-bs-target="#saveCustomerModal1" -->
						</button>
						<!-- Table with stripped rows -->
						<table class="table datatable">
						  <thead style="background-color: #dddfe3;">
						    <tr>
							  <th scope="col"><fmt:message key="photo"/></th>
						      <th scope="col"><fmt:message key="name"/></th>
							  <th scope="col"><fmt:message key="phone"/></th>
							   <th scope="col"><fmt:message key="address"/></th>
							   <th scope="col"><fmt:message key="actions"/></th>
							</tr>
						  </thead>
						  <tbody>
						  <c:forEach var="customers" items="${allCustomers}" varStatus="loop">
						    <tr class="${loop.index % 2 == 0 ? 'even-row' : 'odd-row'}">
							   <th><img src="customer/image/${customer.thumbnail}" class="rounded-circle"></th>
							   <td><a>${customers.name}</a></td>
							   <td><a>${customers.telephone}</a></td>
							   <td><a>${customers.address}</a></td> 
							   <td>
							     <a>
								   <button class="button-see" data-bs-toggle="modal" data-bs-target="#ExtralargeModal" onclick="loadPageModalForm('customer/find/${customers.email}')">
								     <i class="fas fa-eye"></i>
								   </button>
								   <button class="button-edite" data-bs-toggle="modal" data-bs-target="#ExtralargeModal" onclick="loadPageModalForm('customer/update/${customers.email}')">
								     <i class="fas fa-pencil-alt"></i>
								   </button>
								   <button class="button-delete" data-bs-toggle="modal" onclick="confirmDelete('${customers.id}')" data-bs-target="#areyouSureYouWantToDetele" id="startDeleting">
								     <i class="fas fa-trash-alt"></i>
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
<!-- End #main -->

<script src="assets/js/users.js"></script>
<script src="assets/js/billing/customer.js"></script>


