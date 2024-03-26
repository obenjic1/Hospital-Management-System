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

<main id="users-list" class="main">
	<div class="pagetitle">
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="/">Home</a></li>
				<li class="breadcrumb-item">User</li>
				<li class="breadcrumb-item active">List</li>
			</ol>
		</nav>
	</div>
	<section class="section">
		<div class="row">
			<div class="col-lg-12">

				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Users list</h5>
						<button onclick="loadPage('/user/add-user')" type="button"
							class="btn btn-primary">Add User</button>

						<!-- Table with stripped rows -->
						<table class="table datatable">
							<thead>
								<tr>
									<th scope="col">Photo</th>
									<th scope="col">Name(s)</th>
									<th scope="col">Email</th>
									<th scope="col">Address</th>
									<th scope="col">Group</th>
									<th scope="col">Mobile</th>
									<th scope="col">Date</th>
									<th scope="col">Actions</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="user" items="${users}" varStatus="loop">
									<tr class="${loop.index % 2 == 0 ? 'even-row' : 'odd-row'}">
										<th><img src="/download/${user.imagePath}"
											class="rounded-circle"></th>
										<td>${user.username}</td>
										<td>${user.email}</td>
										<td>${user.address}</td>
										<td>${user.groupe.name}</td>
										<td>${user.mobile}</td>
										<td>${user.createdAt}</td>
										<td>
											<button class="button-see"
												onclick="loadPage('user/viewUser/${user.username}')">
												<i class="fas fa-eye"></i>
											</button>
											<button class="button-edite"
												onclick=" loadPage('user/get-user/${user.username}')">
												<i class="fas fa-pencil-alt"></i>
											</button>
											<button class="button-delete"
												onclick="confirmDelete('${user.id}')" id="startDeleting"
												data-bs-toggle="modal">
												<i class="fas fa-trash-alt"></i>
											</button>
										</td>
									</tr>
									<!--------------Are you sure you want t delete this user? modal ------------->
									<div class="modal fade" id="areyouSureYouWantToDetele"
										tabindex="-1">
										<div class="modal-dialog modal-dialog-centered">
											<div class="modal-content">
												<div class="modal-body">
													<p>
														<br>Are you sure you want to delete this user ? This
														action will delete all the user informations. You won't be
														able to revert this.
													</p>
													<button class="delete-denied" type="button"
														id="cancelButton" data-bs-dismiss="modal">Cancel</button>
													<button class="accept-delete" type="button"
														id="confirmDeleteBtn" data-bs-toggle="modal">Delete</button>
												</div>
											</div>
										</div>
									</div>
								</c:forEach>
							</tbody>
						</table>
						<!--------------User created successfully modal ------------->
						<div class="modal fade" id="verticalycentered" tabindex="-1">
							<div class="modal-dialog modal-dialog-centered">
								<div class="modal-content">
									<div class="modal-body">
										<button type="button"
											style="position: relative; left: 50%; bottom: 12px;"
											 class="btn-close"
											data-bs-dismiss="modal" aria-label="Close"></button>
										<img src="assets/img/success_icon.png" alt="">
										<p>User created successfully</p>
									</div>
								</div>
							</div>
						</div>
						<!--------------Email already exist modal ------------->
						<div class="modal fade" id="emailAlreadyExist" tabindex="-1">
							<div class="modal-dialog modal-dialog-centered">
								<div class="modal-content">
									<div class="modal-body">
										<button type="button"
											style="position: relative; left: 50%; bottom: 12px;"
											class="btn-close" data-bs-dismiss="modal" aria-label="Close"
											onclick="loadPage('/user/list-users')"></button>
										<img src="assets/img/error.png" alt="">
										<p>Something when wrong : User name or Email already exist
										</p>
									</div>
								</div>
							</div>
						</div>
						<!--------------User updated successfully modal ------------->
						<div class="modal fade" id="userUdatedSuccessfully" tabindex="-1">
							<div class="modal-dialog modal-dialog-centered">
								<div class="modal-content">
									<div class="modal-body">
										<button onclick="loadPage('/user/list-users')" type="button"
											class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
										<img src="assets/img/success_icon.png" alt="">
										<p>User updated successfully</p>
									</div>
								</div>
							</div>
						</div>
						<!--------------User deleted successfully modal ------------->
						<div class="modal fade" id="userDeleteSuccessfully" tabindex="-1">
							<div class="modal-dialog modal-dialog-centered">
								<div class="modal-content">
									<div class="modal-body">
										<button type="button" class="btn-close"
											data-bs-dismiss="modal"
											style="position: relative; left: 50%; bottom: 12px;"
											onclick="loadPage('/user/list-users')" aria-label="Close"></button>
										<img src="assets/img/success_icon.png" alt="">
										<p>User deleted successfully</p>
									</div>
								</div>
							</div>
						</div>
						<!------------------Deleted error------------------------------>
						<div class="modal fade" id="somthingwhenwrong" tabindex="-1">
							<div class="modal-dialog modal-dialog-centered">
								<div class="alert alert-danger alert-dismissible fade show"
									role="alert">
									<i class="bi bi-exclamation-octagon me-1"></i>
									<p>Something when wrong User did not deleted ! Please try
										again</p>
									<button type="button" class="btn-close" data-bs-dismiss="alert"
										aria-label="Close"></button>
								</div>
							</div>
						</div>
						<!-- End Table with stripped rows -->
						<!-- Pagination with icons -->
						<nav aria-label="Page navigation example">
							<ul class="pagination nav-no-border">
								<li class="page-item"><input type="button"
									class="page-link"
									onclick="refreshUserTable(${currentPage - 1})" value="&laquo;"
									${currentPage == 1 ? 'disabled' : ''}></li>
								<c:forEach var="i" begin="1" end="${totalPages}">
									<li class="page-item ${i == currentPage ? 'active' : ''}"><input type="button"
										class="page-link" onclick="refreshUserTable(${i})"
										value="${i}"></li>
								</c:forEach>
								<li class="page-item"><input type="button"
									class="page-link"
									onclick="refreshUserTable(${currentPage + 1})" value="&raquo;"
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

<script src="assets/js/users.js"></script>
<script src="assets/js/main.js"></script>


