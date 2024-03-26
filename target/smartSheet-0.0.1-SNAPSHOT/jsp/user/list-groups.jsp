<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">
<link href="assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<main class="main" id="pagination-list">
	<div class="pagetitle">
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="/">Home</a></li>
				<li class="breadcrumb-item">Groups</li>
				<li class="breadcrumb-item active">List</li>
			</ol>
		</nav>
	</div>

	<section class="section">
		<div class="row">
			<div class="col-lg-12">

				<div class="card">
					<div class="card-body">
						<h5 class="card-title">List groups</h5>
						<button type="button" onclick="loadPage('/group/add-group')"
							class="btn btn-primary">Add Group</button>

						<!-- Table with stripped rows -->
						<table class="table datatable">
							<thead>
								<tr>
									<th scope="col">N°</th>
									<th scope="col">Name(s)</th>
									<th scope="col">Description</th>
									<th scope="col">Actions</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${groups}" var="group" varStatus="loop">
									<tr class="${loop.index % 2 == 0 ? 'even-row' : 'odd-row'}">
										<th scope="row">${group.id}</th>
										<td>${group.name}</td>
										<td>${group.description}</td>
										<td>
											<button class="button-see"
												onclick="loadPage('group/group-details/${group.name}')">
												<i class="fas fa-eye"></i>
											</button>
											<button class="button-icon"
												onclick="loadPage('group/update-group/${group.name}')">
												<i class="fas fa-pencil-alt"></i>
											</button>
											<button class="button-delete ${group.enabled ? '' : 'disabled-button'}"
												onclick="confirmDisableGroupe('${group.id}')"
												data-bs-toggle="modal">
												<i class="fas fa-ellipsis-h"></i>
											</button>
										</td>
									</tr>
									<!-------------- Group created successfully modal ------------->
									<div class="modal fade" id="groupCreatedSuccessfuly"
										tabindex="-1">
										<div class="modal-dialog modal-dialog-centered">
											<div class="modal-content">
												<input type="button"
													onclick="loadPage('/group/list-groups')" class="btn-close"
													data-bs-dismiss="modal" aria-label="Close">
												<div class="modal-body">
													<img src="assets/img/success_icon.png" alt="">
													<p>Group created successfully</p>
												</div>
											</div>
										</div>
									</div>
									<div class="modal fade" id="groupNameAlreadyExist"
										tabindex="-1">
										<div class="modal-dialog modal-dialog-centered">
											<div class="modal-content">
												<input type="button"
													onclick="loadPage('/group/list-groups')" class="btn-close"
													data-bs-dismiss="modal" aria-label="Close">
												<div class="modal-body">
													<img src="assets/img/error.png" alt="">
													<p>Something when wrong : Group name already exist</p>
												</div>
											</div>
										</div>
									</div>
									<!-------------- Are you sure you want t delete this group? modal ------------->
									<div class="modal fade" id="areyouSureYouWantToDisable"
										tabindex="-1">
										<div class="modal-dialog modal-dialog-centered">
											<div class="modal-content">
												<div class="modal-body">
													<p>
														<br>Are you sure you want to disable the group ? This
														action will block the group's functionality until you
														re-enable it.
													</p>
													<button class="delete-denied" type="button"
														id="cancelButton" data-bs-dismiss="modal">Cancel</button>
													<button class="accept-delete" type="button"
														id="confirmDisabledBtn" data-bs-toggle="modal">Disable</button>
												</div>
											</div>
										</div>
									</div>

								</c:forEach>
							</tbody>
						</table>
						<!-------------- Group disabled successfully modal ------------->
						<div class="modal fade" id="groupdisabledSuccessfully"
							tabindex="-1">
							<div class="modal-dialog modal-dialog-centered">
								<div class="modal-content">
									<button onclick="loadPage('/group/list-groups')" type="button"
										class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
									<div class="modal-body">
										<img src="assets/img/success_icon.png" alt="">
										<p>The group has been disabled successfully...</p>
									</div>
								</div>
							</div>
						</div>
						<!-- End Table with stripped rows -->
						<!-- Pagination with icons -->
						<nav aria-label="Page navigation example">
							<ul class="pagination nav-no-border">
								<li class="page-item"><input type="button"
									class="page-link"
									onclick="refreshGroupTable(${currentPage - 1})" value="&laquo;"
									${currentPage == 1 ? 'disabled' : ''}></li>
								<c:forEach var="i" begin="1" end="${totalPages}">
									<li class="page-item ${i == currentPage ? 'active' : ''}"><input type="button"
										class="page-link" onclick="refreshGroupTable(${i})"
										value="${i}"></li>
								</c:forEach>
								<li class="page-item"><input type="button"
									class="page-link"
									onclick="refreshGroupTable(${currentPage + 1})" value="&raquo;"
									${currentPage == totalPages ? 'disabled' : ''}></li>
							</ul>
						</nav>
					</div>
				</div>
			</div>
		</div>
	</section>
</main>
<!-- End #main -->

<link href="assets/css/list-group.css" rel="stylesheet">
