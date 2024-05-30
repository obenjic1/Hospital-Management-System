<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<main id="update-user">
	<div class="container">
		<section>
			<div class="card" style="width: 102%; left: -1%;">
				<div class="card-body">
				  <h5 class="card-title text-center pb-0 fs-4"><fmt:message key="update.user"/></h5>
				  <p class="text-center small"><fmt:message key="enter.the.user.informations"/></p>
					<form id="myForm" class="row g-3 needs-validation" style=" margin-left: 5%;">
					  <div class="col-md-6">
					    <label for="firstName" class="form-label"></label>
						  <div class="input-group has-validation">
						   <span class="input-group-text"><i class="fas fa-user"></i></span>
						   <input type="text" id="firstName" name="firstName" class="form-control" value="${userFinded.firstName}" />
						 </div>
					   </div>
						<div class="col-md-6">
						  <label for="Email" class="form-label"></label>
						  <div class="input-group has-validation">
							<span class="input-group-text"><i class="fas fa-envelope"></i></span>
							<input type="email" id="email" name="email" class="form-control" value="${userFinded.email}" />
						  </div>
						</div>
						<div class="col-md-6">
						  <label for="lastName" class="form-label"></label>
						  <div class="input-group has-validation">
							<span class="input-group-text"><i class="fas fa-user"></i></span>
							<input type="text" id="lastName" name="lastName" class="form-control" value="${userFinded.lastName}" />
						  </div>
						</div>
						<div class="col-md-6">
						  <label for="mobile" class="form-label"></label>
						  <div class="input-group has-validation">
						    <span class="input-group-text"><i class="fas fa-phone"></i></span>
							<input type="text" id="mobile" name="mobile" class="form-control" value="${userFinded.mobile}" />
						  </div>
						</div>
						<div class="col-md-6">
						  <label for="username" class="form-label"></label>
							<div class="input-group has-validation">
							<span class="input-group-text" id="inputGroupPrepend">@</span> <input type="text" id="username" name="username" class="form-control" value="${userFinded.username}" />
						  </div>
						</div>
						<div class="col-md-6">
						  <label for="ImageFile" class="form-label"></label>
						  <div class="input-group has-validation" style="width: 93%;">
						    <input type="file" id="imageFile" name="imageFile" style=" left: -8%; width: 93%;" class="form-control" accept="image/*" value="${userFinded.imagePath}">
						  </div>
						</div>
						<div class="col-md-6">
						  <label for="address" class="form-label"></label>
						  <div class="input-group has-validation">
						    <span class="input-group-text"> <i class="fas fa-map-marker-alt"></i></span> <input type="text" id="address" name="address" class="form-control" value="${userFinded.address}" />
						  </div>
						</div>
						<div class="col-md-6">
						  <label for="groupe" id="groupeLabel" class="form-label"></label>
						  <div class="input-group has-validation" style=" width: 92%; left: -7%;">
						    <span class="input-group-text"> <i class="fas fa-users"></i> </span> 
						    <select id="groupe" name="groupe" class="form-select">
							  <c:forEach items="${groups}" var="group"> <option value="${group.name}">${group.name}</option> </c:forEach>
							</select>
						  </div>
						</div>
						<div class="col-md-6">
						  <input data-bs-toggle="modal" data-bs-target="#creation" type="button" onclick="loadPage('/user/list-users') ;updateUserById('${userFinded.id}')" class="btn btn-primary" value="Save Changes" style="left: 164%;bottom: -18%;"/>
						</div>
					</form>
				</div>
			</div>
		</section>
	</div>
</main>
<!-- End #main -->
<script src="assets/js/users.js"></script>
