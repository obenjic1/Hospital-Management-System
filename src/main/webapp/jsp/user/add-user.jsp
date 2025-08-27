<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<main id="add-user">

		<section>
			<div class="card">
				<div class="card-body">
					<h5 class="card-title text-center pb-0 fs-4"><fmt:message key="create.an.ccount"/></h5>
					<p class="text-center small"><fmt:message key="enter.the.personal.details.to.create.the.user.account"/></p>

					<form class="row g-3 "  id="userForm" novalidate style=" margin-left: 5%;" >
					  <div class="col-md-6">
						<label for="firstName" class="form-label"> Select User </label>
						  <div class="input-group has-validation">
							<select id="staff" name="staff" class="form-select">
							 <option value="" selected>Choose a Staff</option>
							   <c:forEach items="${staffs}" var="staff">
								 <option value="${staff.id}">${staff.firstName} ${staff.lastName}</option>
							   </c:forEach>
								</select>
						  </div>
						</div>
						<div class="col-md-6">
							<label for="username" class="form-label"><fmt:message key="username"/></label>
							<div class="input-group has-validation">
							  <span class="input-group-text"><i class="fas fa-phone"></i></span>
							  <input type="text" id="username" name="username" class="form-control"  required="required" />
						    </div>
						</div>
						<div class="col-md-6">
						  <label for="Password" class="form-label"><fmt:message key="password"/></label>
						  <div class="input-group has-validation">
							<span class="input-group-text"><i class="fas fa-lock"></i></span>
							<input type="password" id="password" name="password" class="form-control" required="required" />
						  </div>
						</div>
						<div class="col-md-6">
						  <label for="ConfirmPassword" class="form-label"><fmt:message key="confirm.password"/></label>
						  <div class="input-group has-validation">
							<span class="input-group-text"><i class="fas fa-lock"></i></span>
							<input type="password" id="confirmPassword" name="confirmPassword" class="form-control" required="required" />
						  </div>
						</div>
						<div class="col-md-6" style="position: relative;left: -18px;">
						  <label for="imageFile" class="form-label"><fmt:message key="photo"/></label>
						  <div class="input-group has-validation" style="width: 93%; left: 7%;">
						    <input type="file" id="imageFile" name="imageFile" class="form-control" accept="image/*">
						  </div>
						</div>
						<div class="col-md-6" style="width: 234px;">
						  <label for="groupe" id="groupeLabel" class="form-label"><fmt:message key="list.groups"/></label>
						  <div class="input-group has-validation">
							 <select id="groupe" name="groupe" class="form-select">
							   <c:forEach items="${groups}" var="group">
								 <option value="${group.name}">${group.name}</option>
							   </c:forEach>
								</select>
							</div>
						</div>
						<div class="col-md-3"  style="position: relative;bottom: -37px;">
							<input type="button" id="createBtn"  onclick="addUser(); loadPage('user/list-users');"  style=" bottom: -42%;" class="btn btn-outline-primary w-100" value="Save" >
						</div>
					</form>
				</div>
			</div>
		</section>
</main>
<!-- End #main -->
<script src="assets/js/users.js"></script>

