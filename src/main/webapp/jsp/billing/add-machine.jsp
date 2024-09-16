<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<main id="add-machine">
<!-- 	action="machine/add-machine -->
	<section>
			<div class="card">
				<div class="card-body">
					<h5 class="card-title text-center pb-0 fs-4"> <fmt:message key="add.achine"/></h5>
					<p class="text-center small"><fmt:message key="machine.details"/></p>
					<form:form class="row g-3" id="machineForm" style=" margin-left: 5%;" method="POST"  modelAttribute="PrintingMachine" >
					  <div class="col-md-4">
						<label for="Name" class="form-label"></label>
						  <div class="input-group has-validation">
							<span class="input-group-text"><i class="fas fa-user"></i></span>
							<input type="text" id="name" name="Name" class="form-control" placeholder="Name" required />
						  </div>
						  <div id="emptyNameAlert" class="alert alert-danger alert-dismissible fade show" role="alert" style="display: none;"></div>
						</div>
						<div class="col-md-4">
							<label for="abbreviation" class="form-label"></label>
							<div class="input-group has-validation">
							  <span class="input-group-text"><i class="fas fa-envelope"></i></span>
							  <input type="text" id="abbreviation" name="abbreviation" class="form-control" placeholder="Abbreviation" required />
							</div>
						</div>
								<div class="col-md-3">
		                  <label for="isActive" class="form-label"></label>
		                  <select id="isActive" class="form-select">
		                    <option selected> <fmt:message key="select.the.machine.status"/> </option>
		                    <option value=1> <fmt:message key="true"/></option>
		                    <option value=0> <fmt:message key="false"/></option>
		                  </select>
		                </div>
						<div class="col-md-4">
							<label for="Plate Length" class="form-label"></label>
							<div class="input-group has-validation">
							  <span class="input-group-text"><i class="fas fa-user"></i></span>
							  <input type="number" id="plateLength" name="plateLength" class="form-control" placeholder="Plate Length" required />
							</div>
						  <div id="emptyplateLengthAlert" class="alert alert-danger alert-dismissible fade show" role="alert" style="display: none;"></div>
						</div>
						<div class="col-md-4">
							<label for="Plate Width" class="form-label"></label>
							<div class="input-group has-validation">
							  <span class="input-group-text"><i class="fas fa-phone"></i></span>
							  <input type="number" id="plateWidth" name="plateWidth" class="form-control" placeholder="Plate Width" required />
						    </div>
						  <div id="emptyplateWidthAlert" class="alert alert-danger alert-dismissible fade show" role="alert" style="display: none;"></div>
						</div>
						
						<div class="col-md-4">
						  <label for="logo" class="form-label"></label>
						  <div class="input-group has-validation" style="width: 93%; left: 7%;">
						    <input type="file" id="thumbnail" name="thumbnail" class="form-control" accept="image/*">
						  </div>
						</div>
						 
						
				<div class="col-md-3" style="  width: 13%; left: 81%; position: relative; ">
				  <input type="button" id="create-btn" onclick="addMachine()" style=" bottom: -42%;" class="btn btn-primary w-100" value="Save" />
				</div>
				</form:form>

					</div>
				</div>
		</section>
		<script src="assets/js/main.js"></script>
		<script src="assets/js/billing/machine.js"></script> 
	</main>
		


