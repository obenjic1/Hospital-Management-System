<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<main id="update-customer">
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
						   <input type="text" id="name" name="name" class="form-control" value="${update.name}" />
						 </div>
					   </div>
						<div class="col-md-6">
						  <label for="Email" class="form-label"></label>
						  <div class="input-group has-validation">
							<span class="input-group-text"><i class="fas fa-envelope"></i></span>
							<input type="email" id="email" name="email" class="form-control" value="${update.email}" />
						  </div>
						</div>					
						<div class="col-md-6">
						  <label for="mobile" class="form-label"></label>
						  <div class="input-group has-validation">
						    <span class="input-group-text"><i class="fas fa-phone"></i></span>
							<input type="text" id="telephone" name="telephone" class="form-control" value="${update.telephone}" />
						  </div>
						</div>
						<div class="col-md-6">
						  <label for="address" class="form-label"></label>
						  <div class="input-group has-validation">
						    <span class="input-group-text"> <i class="fas fa-map-marker-alt"></i></span> 
						    <input type="text" id="address" name="address" class="form-control" value="${update.address}" />
						  </div>
						</div>

						<div class="col-md-6">
						  <button onclick="updateCustomer('${update.id}')" type="button" class="btn btn-primary" value="Save Changes" style="left: 164%;bottom: -18%;"/>Save Changes</button>
						</div>
					</form>
				</div>
			</div>
		</section>
	</div>
</main>
<!-- End #main -->
<script src="assets/js/billing/customer.js"></script>
<script src="assets/js/users.js"></script>
