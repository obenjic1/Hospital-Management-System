<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.23/css/jquery.dataTables.min.css">

<link href="assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">
<link href="assets/css/list-role.css" rel="stylesheet">


<main id="table-body" class="main">
	<div class="pagetitle">
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="/">Home</a></li>
				<li class="breadcrumb-item">Roles</li>
				<li class="breadcrumb-item active">List</li>
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
								<table class="datatable-table table datatable striped-rows">
									<thead>
										<tr>
											<th scope="col">N°</th>
											<th scope="col">Name(s)</th>
											<th scope="col">Description</th>
											<th scope="col">Actions</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${roles}" var="role" varStatus="loop">
											<tr class="${loop.index % 2 == 0 ? 'even-row' : 'odd-row'}">
												<th scope="row">${role.id}</th>
												<td>${role.name}</td>
												<td>${role.description}</td>
												<td>
													<button class="button-see"
														onclick="loadPage('role/view-role-details/${role.name}')">
														<i class="fas fa-eye"></i>
													</button>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<!-- Pagination with icons -->
								<nav aria-label="Page navigation example">
									<ul class="pagination nav-no-border">
										<li class="page-item"><input type="button"
											class="page-link" onclick="refreshTable(${currentPage - 1})"
											value="&laquo;" ${currentPage == 1 ? 'disabled' : ''}>
										</li>
										<c:forEach var="i" begin="1" end="${totalPages}">
											<li class="page-item ${i == currentPage ? 'active' : ''}">
												<input type="button" class="page-link"
												onclick="refreshTable(${i})" value="${i}">
											</li>
										</c:forEach>
										<li class="page-item"><input type="button"
											class="page-link" onclick="refreshTable(${currentPage + 1})"
											value="&raquo;"
											${currentPage == totalPages ? 'disabled' : ''}></li>
									</ul>
								</nav>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

</main>
<script src="assets/js/role.js"></script>

