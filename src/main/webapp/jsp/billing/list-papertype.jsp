<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<link rel="stylesheet" href="/DataTables/datatables.dataTables.css" />
<link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="assets/css/list-role.css" rel="stylesheet">
<link href="assets/css/list-users.css" rel="stylesheet">


<main id="list-customer" class="main">
	<div class="pagetitle">
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="/">Home</a></li>
				<li class="breadcrumb-item"><fmt:message key="paper.types"/></li>
				<li class="breadcrumb-item active"> <fmt:message key="list"/></li>
			</ol>
		</nav>
	</div>
	<section class="section">
		<div class="row">
			<div class="col-lg-12">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title"> <fmt:message key="paper.types"/></h5>
						<button data-bs-target="#ExtralargeModal" data-bs-toggle="modal" onclick="fillContentModal('/papertype/displayform')" type="button" class="btn btn-primary" style=" position: relative; left: 94%; width: 77px;">
						  <fmt:message key="add.group"/>
						</button>
						<!-- Table with stripped rows -->
						<table class="table datatable">
						  <thead style="background-color: #dddfe3;">
						    <tr>
						       <th scope="col"><fmt:message key="number"/></th>
						       <th scope="col"><fmt:message key="name"/></th>
							   <th scope="col"><fmt:message key="actions"/></th>
							</tr>
						  </thead>
						  <tbody>
						  <c:forEach var="paperType" items="${allPaperTypes}" varStatus="loop">
						    <tr class="${loop.index % 2 == 0 ? 'even-row' : 'odd-row'}">
							    <c:set var="index" value="${loop.index}" />
							    <%    int index = (Integer) pageContext.getAttribute("index");  %>
							 <td>  <%= index + 1 %></td>
							   <td>${paperType.name}</td>
							   <td>
							     <a>
								   <button data-bs-toggle="modal" data-bs-target="#ExtralargeModal" data-bs-toggle="modal" class="button-see" onclick="fillContentModal('papertype/paper/${paperType.id}')">
								     <i class="fas fa-eye"></i>
								   </button>
								   <button class="button-edite" data-bs-target="#ExtralargeModal" data-bs-toggle="modal" class="button-see" onclick="fillContentModal('papertype/toUpdate/${paperType.id}')">
								     <i class="fas fa-pencil-alt"></i>
								   </button>
								   <button class="button-delete" id="startDeleting" data-bs-toggle="modal" onclick="confirmDelete('${paperType.id}')" data-bs-target="#areyouSureYouWantToDetele">
								     <i class="fas fa-trash-alt"></i>
								   </button>
								 </a>
							   </td>
							 </tr>						
						  </c:forEach>
						</tbody>
					  </table>
					  <div class="modal fade" id="ExtralargeModal" tabindex="-1">
						  <div class="modal-dialog modal-xl">
							<div class="modal-content"id="addUser" >
							  <div class="modal-body">
								<ul class="nav nav-tabs nav-tabs-bordered"> </ul>
							  </div>
							  <div class="modal-footer" >
								<button type="button" class="btn btn-secondary" data-bs-dismiss="modal"><fmt:message key="close"/></button>
							  </div>
							</div>
						  </div>
						</div>	
					  <!-------------- Modal ------------->
					    <div class="modal fade" id="areyouSureYouWantToDetele" tabindex="-1">
						  <div class="modal-dialog modal-dialog-centered">
					        <div class="modal-content">
						    <div class="modal-body">
							  <p> <br><fmt:message key="are.you.sure.you.want.to.delete.this.user.this.action.will"/></p>
				 			  <button class="delete-denied" type="button" id="cancelButton" data-bs-dismiss="modal"><fmt:message key="cancel"/></button>
							  <button class="accept-delete" type="button" id="confirmDeleteBtn" data-bs-toggle="modal"><fmt:message key="delete"/></button>
						    </div>
						  </div>
					    </div>
					  </div>					  					 		
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


