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
						<label for="firstName" class="form-label"> <fmt:message key="first.name"/> </label>
						  <div class="input-group has-validation">
							<span class="input-group-text"><i class="fas fa-user"></i></span>
							<input type="text" id="firstName" name="firstName" class="form-control"  required/>
							<div class="invalid-feedback"> Please enter your name.  </div>  
						  </div>
						</div>
						<div class="col-md-6">
							<label for="Email" class="form-label"><fmt:message key="email"/></label>
							<div class="input-group has-validation">
							  <span class="input-group-text"><i class="fas fa-envelope"></i></span>
							  <input type="email" id="email" name="email" class="form-control" required="required" />
							  <span id = "emailMsg" style="color:red"> </span> <br><br>
							</div>
						</div>
						<div class="col-md-6">
							<label for="lastName" class="form-label"><fmt:message key="last.name"/></label>
							<div class="input-group has-validation">
							  <span class="input-group-text"><i class="fas fa-user"></i></span>
							  <input type="text" id="lastName" name="lastName" class="form-control"/>
							</div>
						</div>
						<div class="col-md-6">
							<label for="mobile" class="form-label"><fmt:message key="phone"/></label>
							<div class="input-group has-validation">
							  <span class="input-group-text"><i class="fas fa-phone"></i></span>
							  <input type="text" id="mobile" name="mobile" class="form-control"  required="required" />
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
						  <label for="address" class="form-label"><fmt:message key="address"/></label>
						  <div class="input-group has-validation">
							<span class="input-group-text"> <i class="fas fa-map-marker-alt"></i></span> 
							<input type="text" id="address" name="address" class="form-control" required="required" />
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
						<div class="col-md-6" style="display: flex;left: -56px;position: relative;">
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
						<div class="col-md-6" style="width: 234px;left: 25px;position: relative;">
						  <label for="groupe" id="departement" class="form-label"><fmt:message key="list.departement"/></label>
						  <div class="input-group has-validation" >
							 <select id="department" name="department" class="form-select">
							   <c:forEach items="${departements}" var="departement">
								 <option value="${departement.id}">${departement.name}</option>
							   </c:forEach>
								</select>
							</div>
						</div>
						</div>
						<div class="col-md-3" style="  width: 20%; left: 72%; position: relative;bottom: -10px; ">
							<input type="button" id="createBtn"  onclick="addUser(); loadPage('user/list-users');"  style=" bottom: -42%;" class="btn btn-outline-primary w-100" value="Save" >
						</div>
					</form>
				</div>
			</div>
		</section>
</main>
<!-- End #main -->
<script src="assets/js/users.js"></script>

