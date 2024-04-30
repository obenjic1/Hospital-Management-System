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
					<h5 class="card-title text-center pb-0 fs-4">Add an Activity Option</h5>
					<p class="text-center small">Enter the Detail of the Activity</p>
					<form:form class="row g-3 text-center pb-0 fs-4" id="activityForm" style=" margin-left:10%;" method="POST"  modelAttribute="JobActivityOption">
					  <div class="col-md-8">
						<label for="Name" class="form-label"></label>
						  <div class="input-group has-validation">
							<span class="input-group-text"><i class="fas fa-user"></i></span>
							<input type="text" id="name" name="Name" class="form-control" placeholder="Name" required />
						  </div>
						 <div id="emptyNameAlert" class="alert alert-danger alert-dismissible fade show" role="alert" style="display: none;"></div>
					  </div>
					<div class="col-md-4">
						   <label for="logo" class="form-label"></label>
						      <div class="input-group has-validation" style="width: 60%; left: 7%;">
						        <input type="button" id="addActivity-btn" onclick="addActivity()" style=" bottom: -42%;" class="btn btn-primary w-100" value="Save" />
						   </div>
						</div>
				   </form:form>
						   <!-------------Activity Option  added successfully modal ------------->
								<div class="modal fade" id="activityAdded" tabindex="-1">
									<div class="modal-dialog modal-dialog-centered">
										<div class="modal-content">
											<div class="modal-body">
												<button type="button" onclick="loadPage('activityOption/list-activityOptions')"
													class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
												<img src="assets/img/success_icon.png" alt="">
												<p>an Activity has been added successfully</p>
											</div>
										</div>
									</div>
								</div>
						</div>
					</div>
		</section>
	</main>
	<script src="assets/js/main.js"></script>
	<script src="assets/js/billing/job-activity-options.js"></script> 



