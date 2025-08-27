<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">


<script src="DataTables/datatables.js"></script>
<link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<main class="main" id="pagination-list">
	<div class="pagetitle">
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="/">Home</a></li>
				<li class="breadcrumb-item"><fmt:message key="list.groups"/></li>
				<li class="breadcrumb-item active"> <fmt:message key="list"/></li>
			</ol>
		</nav>
	</div>
	<section class="section">
		<div class="row">
			<div class="col-lg-12">

				<div class="card">
					<div class="card-body">
						<h5 class="card-title">List groups</h5>
						<button type="button" onclick="loadPageModalForm('group/add-group')" data-bs-toggle="modal" data-bs-target="#ExtralargeModal" class="btn btn-primary" style="left: 90%;width: 142px;">Add Group</button>

						<!-- Table with stripped rows -->
						<table id="myTable" class="table datatable">
						  <thead style="background-color: #dddfe3;">
						    <tr>
							  <th scope="col"><fmt:message key="number"/></th>
							  <th scope="col"><fmt:message key="names"/></th>
							  <th scope="col"><fmt:message key="status"/></th>
							  <th scope="col"><fmt:message key="description"/> </th>
							  <th scope="col"><fmt:message key="actions"></fmt:message> </th>
							</tr>
						  </thead>
						  <tbody>
						    <c:forEach items="${groups}" var="group" varStatus="loop">
								  <tr>
								<td>${loop.index + 1}</td>
							    <td><a>${group.name}</a></td>
							    <td><a class="${group.enabled ? 'Active' : 'Blocked' }">${group.enabled ? 'Active': 'Inactive'}</a></td>
							    <td><a data-bs-toggle="tooltip" data-bs-placement="top" title="${group.description}"> ${group.description}</a></td>
							    <td>
								  <button class="button-see" data-bs-toggle="modal" data-bs-target="#ExtralargeModal" onclick="loadPageModalForm('group/group-details/${group.name}')">
								    <i class="ri-eye-line"></i>											
								  </button>
							      <button class="button-icon" data-bs-toggle="modal" data-bs-target="#ExtralargeModal" onclick="loadPageModalForm('group/update-group/${group.name}')">
							        <i class="ri-pencil-line"></i>
								  </button>
								   <button class="button-delete"  data-toggle="tooltip" data-placement="top" title="Deactivate/Reactivate Group" onclick="confirmDisableGroupe('${group.id}')">
								      ${group.enabled ? '<i class="bi-toggle2-on"></i>' :'<i class="bi-toggle2-off"></i>'}
								   </button>
							    </td>
							  </tr>
							</c:forEach>
							</tbody>
						   </table>
<!--  						  <nav aria-label="Page navigation example"> -->
<!--  					        <ul class="pagination nav-no-border"> -->
<%--  						      <li class="page-item"><input type="button" class="page-link" onclick="refreshGroupTable(${currentPage - 1})" value="&laquo;" ${currentPage == 1 ? 'disabled' : ''}></li>  --%>
<%--  							  <c:forEach var="i" begin="1" end="${totalPages}">  --%>
<%--  							   <li class="page-item ${i == currentPage ? 'active' : ''}">  --%>
<%-- 							     <input type="button" class="page-link" onclick="refreshGroupTable(${i})" value="${i}">  --%>
<!-- 							   </li> -->
<%--  							 </c:forEach>  --%>
<%--  							 <li class="page-item"><input type="button" class="page-link" onclick="refreshGroupTable(${currentPage + 1})" value="&raquo;" ${currentPage == totalPages ? 'disabled' : ''}></li>  --%>
<!--  						   </ul>  -->
<!--  						 </nav> -->
					</div>
				</div>
			</div>
		</div>
	</section>
</main>
<!-- End #main -->

<script> 
	$(document).ready( function () {
	$('#myTable').DataTable();
} );
	
	
</script>
<link href="assets/css/list-group.css" rel="stylesheet">
