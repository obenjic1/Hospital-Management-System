<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<meta content="width=device-width, initial-scale=1.0" name="viewport">
<link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">
<link href="assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<link href="https://fonts.gstatic.com" rel="preconnect">
<link href="assets/css/add-user.css" rel="stylesheet">
<link href="assets/css/update-user.css" rel="stylesheet">

<main id="update-user">
	<div class="container">
		<div class="pagetitle">
			<nav>
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="/">Home</a></li>
					<li class="breadcrumb-item">Update</li>
					<li class="breadcrumb-item active">User</li>
				</ol>
			</nav>
		</div>

		<section>
			<div class="card">
				<div class="card-body">
					<h5 class="card-title text-center pb-0 fs-4">Update User</h5>
					<p class="text-center small">Enter the nez user's informations</p>

					<form id="myForm" class="row g-3 needs-validation">
						<div class="col-md-6">
							<label for="firstName" class="form-label"></label>
							<div class="input-group has-validation">
								<span class="input-group-text"><i class="fas fa-user"></i></span>
								<input type="text" id="firstName" name="firstName"
									class="form-control" value="${userFinded.firstName}" />
							</div>
						</div>
						<div class="col-md-6">
							<label for="Email" class="form-label"></label>
							<div class="input-group has-validation">
								<span class="input-group-text"><i class="fas fa-envelope"></i></span>
								<input type="email" id="email" name="email" class="form-control"
									value="${userFinded.email}" />
							</div>
						</div>
						<div class="col-md-6">
							<label for="lastName" class="form-label"></label>
							<div class="input-group has-validation">
								<span class="input-group-text"><i class="fas fa-user"></i></span>
								<input type="text" id="lastName" name="lastName"
									class="form-control" value="${userFinded.lastName}" />
							</div>
						</div>
						<div class="col-md-6">
							<label for="mobile" class="form-label"></label>
							<div class="input-group has-validation">
								<span class="input-group-text"><i class="fas fa-phone"></i></span>
								<input type="text" id="mobile" name="mobile"
									class="form-control" value="${userFinded.mobile}" />
							</div>
						</div>
						<div class="col-md-6">
							<label for="username" class="form-label"></label>
							<div class="input-group has-validation">
								<span class="input-group-text" id="inputGroupPrepend">@</span> <input
									type="text" id="username" name="username" class="form-control"
									value="${userFinded.username}" />
							</div>
						</div>
						<div class="col-md-6">
							<label for="ImageFile" class="form-label"></label>
							<div class="input-group has-validation">
								<input type="file" id="imageFile" name="imageFile"
									class="form-control" accept="image/*"
									value="${userFinded.imagePath}">
							</div>
						</div>
						<div class="col-md-6">
							<label for="address" class="form-label"></label>
							<div class="input-group has-validation">
								<span class="input-group-text"> <i
									class="fas fa-map-marker-alt"></i></span> <input type="text"
									id="address" name="address" class="form-control"
									value="${userFinded.address}" />
							</div>
						</div>

						<div class="col-md-6">
							<label for="groupe" id="groupeLabel" class="form-label"></label>
							<div class="input-group has-validation">
								<span class="input-group-text"> <i class="fas fa-users"></i>
								</span> <select id="groupe" name="groupe" class="form-select">
									<c:forEach items="${groups}" var="group">
										<option value="${group.name}">${group.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="col-md-6">
							<input data-bs-toggle="modal" type="button"
								onclick="loadPage('/user/list-users') ;updateUserById('${userFinded.id}')"
								class="btn btn-primary" value="Save Changes" />
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
						<!-------------- Something when wrong Modal ------------->
						<div class="modal fade" id="somethingWhenWrong" tabindex="-1">
							<div class="modal-dialog modal-dialog-centered">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title">Error</h5>
										<button type="button" class="btn-close"
											data-bs-dismiss="modal" aria-label="Close"></button>
									</div>
									<div class="modal-body">
										<img src="assets/img/success_icon.png" alt="">
										<p>Something when wrong with the server</p>
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</section>
	</div>
</main>
<!-- End #main -->
<script src="assets/js/users.js"></script>
