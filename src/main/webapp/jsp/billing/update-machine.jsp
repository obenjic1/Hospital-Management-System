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
				  <h5 class="card-title text-center pb-0 fs-4">Update Machine</h5>
				  <p class="text-center small">Update Machine Information</p>
					<form id="myForm" class="row g-3 needs-validation" style=" margin-left: 20%;">
						<label for="name" class="col-md-4 col-lg-3 col-form-label">Name</label>
	                      <div class="col-md-8 col-lg-9">
	                        <input name="name" type="text" class="form-control" id="name" value="${findMachine.name}">
	                      </div>
	                      <label for="abbreviation" class="col-md-4 col-lg-3 col-form-label">abbreviation</label>
	                      <div class="col-md-8 col-lg-9">
	                        <input name="abbreviation" type="text" class="form-control" id="abbreviation" value="${findMachine.abbreviation}">
	                      </div>
	                      <label for="plateLength" class="col-md-4 col-lg-3 col-form-label">Plate Length</label>
	                      <div class="col-md-8 col-lg-9">
	                        <input name="plateLength" type="number" class="form-control" id="plateLength" value="${findMachine.plateLength}">
	                      </div>
	                       <label for="Plate Width" class="col-md-4 col-lg-3 col-form-label">Plate Width</label>
	                      <div class="col-md-8 col-lg-9">
	                        <input name="plateWidth" type="number" class="form-control" id="plateWidth" value="${findMachine.plateWidth}">
	                      </div>
	                       <label for="thumbnail" class="col-md-4 col-lg-3 col-form-label">Logo</label>
	                      <div class="col-md-8 col-lg-9">
	                        <input name="thumbnail" type="file" class="form-control" id="thumbnail" value="${findMachine.thumbnail}" accept="image/*">
	                      </div>
	                      <div class="text-center">
<%-- 	                      <input type="button" data-bs-toggle="modal" data-bs-target="#creation" onclick="updatecustomer('${update.id}')" class="btn btn-primary" value="Save Changes" style="left: 164%;bottom: -18%;"/> --%>
                      	<input type="button" data-bs-toggle="modal" data-bs-target="#creation" id="submitButton" style="left: 42%; bottom: 2%" value="Save Changes" class="btn btn-primary w-30" onclick="updateMachine('${findMachine.id}')" > 
                     </div>
					</form>				
									
				</div>
			</div>
		</section>
	</div>
</main>
<!-- End #main -->
<script src="assets/js/billing/machine.js"></script> 
<script src="assets/js/users.js"></script>
