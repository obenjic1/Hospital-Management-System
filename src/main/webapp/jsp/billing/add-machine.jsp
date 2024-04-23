<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<main id="add-machine">
	
	<section>
			<div class="card">
				<div class="card-body">
					<h5 class="card-title text-center pb-0 fs-4">Add a Machine</h5>
					<p class="text-center small">Enter the details of the Machine</p>
					<form:form class="row g-3" id="machineForm" style=" margin-left: 5%;" method="POST" action="machine/add-machine">
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
						 <div class="col-md-3">
                  <label for="isActive" class="form-label">is machine Active</label>
                  <select id="isActive" class="form-select">
                    <option selected>select the machine status</option>
                    <option selected>true</option>
                    <option selected>false</option>
                  </select>
                </div>
						
						<div class="col-md-3" style="width: 15%; left: 32%; position: relative; alignment:center ">
							<button  id="submitButton" style="left: 42%; bottom: 2%" class="btn btn-primary w-100" onclick="addMachine()" >Save</button>
							
						</div>
						<!-------------machine added successfully modal ------------->
						<div class="modal fade" id="verticalycentered" tabindex="-1">
							<div class="modal-dialog modal-dialog-centered">
								<div class="modal-content">
									<div class="modal-body">
<!-- 										<button type="button" onclick="loadPage('/user/list-users')" -->
											class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
										<img src="assets/img/success_icon.png" alt="">
										<p>machine added successfully</p>
									</div>
								</div>
							</div>
						</div>
						<!-------------- Something when wrong Modal ------------->
						<div class="modal fade" id="somethingWhenWrong" tabindex="-1">
							<div class="modal-dialog modal-dialog-centered">
								<div class="modal-content">
									<div class="modal-body">
										<button type="button" class="btn-close"
											data-bs-dismiss="modal" aria-label="Close"></button>
										<img src="assets/img/success_icon.png" alt="">
										<p><fmt:message key="something.when.wrong.with.the.server"/></p>
									</div>
								</div>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</section>
	</main>
		<script src="assets/js/main.js"></script>
	<script src="assets/js/billing/machine.js"></script> 


