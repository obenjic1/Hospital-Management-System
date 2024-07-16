<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="assets/css/list-role.css" rel="stylesheet">


<main id="role-pagination" class="main">
	<div class="pagetitle">
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="/">Home</a></li>
				<li class="breadcrumb-item">Roles</li>
				<li class="breadcrumb-item active"> <fmt:message key="list"/></li>
			</ol>
		</nav>
	</div>
	<section class="section">
		<div class="row">
			<div class="col-lg-12">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">List Roles</h5>
						<div class="datatable-wrapper no-footer">
							<div class="datatable-container">							
							  <table id="myTable" class="table datatable">
							    <thead style="background-color: #dddfe3;">
								  <tr>
									<th scope="col"> <fmt:message key="number"/></th>
								    <th scope="col"><fmt:message key="names"/></th>
									<th scope="col"><fmt:message key="description"/></th>
									<th scope="col"><fmt:message key="actions"/></th>
								  </tr>
								</thead>
								<tbody>
								  <c:forEach items="${roles}" var="role" varStatus="loop">
								    <tr class="${loop.index % 2 == 0 ? 'even-row' : 'odd-row'}">
									  <th scope="row">${role.id}</th>
									  <td><a>${role.name}</a></td>
									  <td><a data-toggle="tooltip" title="${role.description}">${role.description}</a></td>
									  <td>
										<button class="button-see" data-bs-toggle="modal" data-bs-target="#ExtralargeModal" onclick="loadPageModalForm('role/view-role-details/${role.name}')">
										  <i class="ri-eye-line"></i>
										</button>
									  </td>
									</tr>
								   
								  </c:forEach>
							    </tbody>
							  </table>
							</div>
						 </div>
					   </div>
				     </div>
			       </div>
		         </div>
	           </section>
             </main>
<script src="/DataTables/datatables.js"></script>
<script src="assets/js/role.js"> </script>

	<script>
	$(document).ready( function () {
		$('#myTable').DataTable();
		});
	</script>

