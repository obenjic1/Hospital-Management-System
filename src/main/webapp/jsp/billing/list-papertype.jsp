<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<script src="DataTables/datatables.js"></script>
<link rel="stylesheet" href="DataTables/dataTables.dataTables.css" />
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
						<button data-bs-target="#ExtralargeModal" data-bs-toggle="modal" onclick="loadPageModalForm('papertype/displayform')" type="button" class="btn btn-primary" style=" position: relative; left: 94%; width: 77px;">
						  <fmt:message key="add.group"/>
						</button>
						<!-- Table with stripped rows -->
						<table id="myTable1" class="table datatable">
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
								   <button data-bs-toggle="modal" data-bs-target="#ExtralargeModal" data-bs-toggle="modal" class="button-see" onclick="loadPageModalForm('papertype/paper/${paperType.id}')">
								    <i class="ri-eye-line"></i>
								   </button>
								   <button class="button-edite" data-bs-target="#ExtralargeModal" data-bs-toggle="modal" class="button-see" onclick="loadPageModalForm('papertype/toUpdate/${paperType.id}')">
								     <i class="ri-pencil-line"></i>
								   </button>
								   <button class="button-delete" id="startDeleting" data-bs-toggle="modal" onclick="confirmDelete('${paperType.id}')" data-bs-target="#areyouSureYouWantToDetele">
								    <i class="ri-delete-bin-3-line"></i>
								   </button>
								 </a>
							   </td>
							 </tr>						
						  </c:forEach>
						</tbody>
					  </table>
					  </div>					  					 		
<!-- 					 <nav aria-label="Page navigation example"> -->
<!-- 					   <ul class="pagination nav-no-border"> -->
<!-- 						 <li class="page-item"> -->
<%-- 						   <input type="button" class="page-link" onclick="refreshTable(${currentPage - 1})" value="&laquo;" ${currentPage == 1 ? 'disabled' : ''}>  --%>
<!-- 						 </li> -->
<%-- 						 <c:forEach var="i" begin="1" end="${totalPages}"> --%>
<%-- 						   <li class="page-item ${i == currentPage ? 'active' : ''}"> --%>
<%-- 							 <input type="button" class="page-link" onclick="refreshTable(${i})" value="${i}"> --%>
<!-- 						   </li> -->
<%-- 						   </c:forEach> --%>
<!-- 						   <li class="page-item"> -->
<%-- 						     <input type="button" class="page-link" onclick="refreshTable(${currentPage + 1})" value="&raquo;" ${currentPage == totalPages ? 'disabled' : ''}> --%>
<!-- 						   </li> -->
<!-- 					    </ul> -->
<!-- 					</nav> -->
					</div>
				</div>
			</div>
		</div>
	</section>
</main>
<!-- End #main -->
<script src="assets/js/users.js"></script>

<script>
$(document).ready( function () {
$('#myTable1').DataTable();
} );
</script>



