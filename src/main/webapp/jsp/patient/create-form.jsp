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
					<h5 class="card-title text-center pb-0 fs-4">Register a new Patient</h5>
					<p class="text-center small">Enter the personal detail to register a new Patient</p>

					<form class="row g-3 "  id="patintForm" novalidate style="margin-left: 5%;" >
					  <div class="col-md-6">
						<label for="name" class="form-label"> Name </label>
						  <div class="input-group has-validation">
							<span class="input-group-text"><i class="fas fa-user"></i></span>
							<input type="text" id="name" name="firstName" class="form-control"  placeholder="Please Enter Patient's  fullnames ..." required/>
						  </div>
						</div>
						<div class="col-md-6">
							<label for="Contacr" class="form-label">Patients Contact</label>
							<div class="input-group has-validation">
							  <span class="input-group-text"><i class="fas fa-envelope"></i></span>
							  <input type="text" id="contact" name="contact" class="form-control"  placeholder="Please Enter Patient's  Phone number .."/>
							  <span id = "emailMsg" style="color:red"> </span> <br><br>
							</div>
						</div>
						<div class="col-md-6">
							<label for="age" class="form-label">Age</label>
							<div class="input-group has-validation">
							  <span class="input-group-text"><i class="fas fa-user"></i></span>
							  <input type="number" id="age" name="age" class="form-control" placeholder="Please Enter Patient's age ..."/>
							</div>
						</div>
						
						<div class="col-md-6" >
						  <label for="groupe" id="" class="form-label">Gender</label>
						  <div class="input-group has-validation" >
							 <select id="gender" name="gender" class="form-select">
								 <option value="null" selected>Select gender </option>
								 <option value="male" >Male</option>
								 <option value="female" >Female</option>
								</select>
							</div>
						</div>
						<div class="col-md-6">
						  <label for="emmergenceName" class="form-label">Patient's Emmergency Contact Name</label>
						  <div class="input-group has-validation">
							<span class="input-group-text"> <i class="fas fa-map-marker-alt"></i></span> 
							<input type="text" id="emmergenceName" name="emmergenceName" class="form-control" required="required"  placeholder="Please Enter Patient's Emmergency contact Name ..."/>
						  </div>
						</div>
						<div class="col-md-6">
							<label for="emmergencyContact" class="form-label"> Patient's Emmergency Contact <fmt:message key="phone"/></label>
							<div class="input-group has-validation">
							  <span class="input-group-text"><i class="fas fa-phone"></i></span>
							  <input type="text" id="emmergencyContact" name="emmergencyContact" class="form-control"  placeholder="Please Enter Patient's Emmergency contact " required="required" />
						    </div>
					 </div>

						<div class="col-md-3" style="  width: 20%; left: 72%; position: relative;bottom: -10px; ">
							<input type="button" id="createBtn"  onclick="savePatient()"  style=" bottom: -42%;" class="btn btn-outline-primary w-100" value="Save" >
						</div>
					</form>
				</div>
			</div>
		</section>
</main>
<!-- End #main -->
<script src="assets/js/hospital/medicine.js"></script>


