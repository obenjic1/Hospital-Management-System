<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
							pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

  <link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<main id="add-user">

		<section>
			<div class="card">
				<div class="card-body">
					<h5 class="card-title text-center pb-0 fs-4"><fmt:message key="save.customer"/></h5>
					<p class="text-center small"><fmt:message key="enter.customer.details"/></p>

					<form class="row g-3 needs-validation" style=" margin-left: 5%;" onsubmit="event.preventDefault(); save()">
					  <div class="col-md-6">
						<label for="Name" class="form-label"></label>
						  <div class="input-group has-validation">
							<span class="input-group-text"><i class="fas fa-user"></i></span>
							<input type="text" id="name" name="Name" class="form-control" placeholder="Name" required="required" />
						  </div>
						  <div id="emptyNameAlert" class="alert alert-danger alert-dismissible fade show" role="alert" style="display: none;"></div>
						</div>
						<div class="col-md-6">
							<label for="Email" class="form-label"></label>
							<div class="input-group has-validation">
							  <span class="input-group-text"><i class="fas fa-envelope"></i></span>
							  <input type="email" id="email" name="email" class="form-control" placeholder="Email"/>
							</div>
							<div id="emptyEmailAlert" class="alert alert-danger alert-dismissible fade show" role="alert" style="display: none;"></div>
						</div>					
						<div class="col-md-6">
							<label for="mobile" class="form-label"></label>
							<div class="input-group has-validation">
							  <span class="input-group-text"><i class="fas fa-phone"></i></span>
							  <input type="text" id="telephone" name="telephone" class="form-control" placeholder="Telephone"/>
						    </div>
						  <div id="emptyNameAlert" class="alert alert-danger alert-dismissible fade show" role="alert" style="display: none;"></div>
						</div>
						<div class="col-md-6">
						  <label for="address" class="form-label"></label>
						  <div class="input-group has-validation">
							<span class="input-group-text"> <i class="fas fa-map-marker-alt"></i></span> 
							<input type="text" id="address" name="address" class="form-control" placeholder="Address" />
						  </div>
						  <div id="emptyAddressAlert" class="alert alert-danger alert-dismissible fade show" role="alert" style="display: none;"></div>
						</div>
						
						<div class="col-md-6">
						  <label for="Thumbnail" class="form-label"></label>
						  <div class="input-group has-validation" style="width: 93%; left: 7%;">
						    <input type="file" id="thumbnail" name="thumbnail" class="form-control" accept="image/*">
						  </div>
						</div>
						<div class="col-md-3" style="  width: 22%; left: 21%; bottom: -30px; position: relative; ">
						  <input type="button" id="create-btn" onclick="saveCustomerFromJobForm(); loadPage('customer/list');" style=" bottom: -42%;" class="btn btn-primary w-100" value="Save" />
						</div>							
					</form>					
				</div>
			</div>
		</section>
</main>
<!-- End #main -->
<script src="assets/js/billing/customer.js"></script> 
