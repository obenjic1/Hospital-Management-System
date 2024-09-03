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
					<h5 class="card-title text-center pb-0 fs-4">Move Job</h5>
					<p class="text-center small">Move this Job to a Different Department</p>
					
					<div class ="row " style=" padding-left: 8%;padding-right: 3%;">
							<div class="col-md-5">
								<div class ="row py-3">
								  <div> <fmt:message key="from"/>   </div>
								    <div><span><strong>${movement.department.name}</strong></span> </div>
								</div>
								<div class ="row">
								 <label for="" class="form-label"> <fmt:message key="description"/></label>
								<div><span>${movement.description} </span> </div>
								</div>
								<div class ="row py-3">
								  <div> <fmt:message key="date"/>   </div>
								    <div><span><a><strong><fmt:formatDate type = "both" value = "${movement.creationDate}" /></strong></a>
								    </span> </div>
								</div>
							</div>
							<div class="col-md-5"> 
							<div class ="row py-3">
							  <label for="" class="form-label"> <fmt:message key="to"/> </label>
							 <div>
								 <select id="department"  name="name" class="form-select">
		              	     		 <option selected>Choose...</option>
								  <c:forEach items="${departments}" var="department">
			                        <option value="${department.id}">${department.name}</option>
			                      </c:forEach>
		                    </select>
	                    	</div>
	                     </div>
							
							<div class ="row py-3">
							 <label for="" class="form-label"> <fmt:message key="description"/> </label>
                            	 <div>
                                 	<textarea id="description" name="" rows="3" value="" style=" width: 95%;"></textarea>
                                 </div>
                                                   
							</div>
							  <div class="row py-1">  
									<button  style=" width: 94px;margin-left: 71%;" type="button"  class="btn btn-primary"data-bs-dismiss="modal" onclick="moveJob('${job.id}')" id="next-btn" >Send </button>	
							  </div> 
						</div>
					
					</div>
						<!-------------machine added successfully modal ------------->
						<div class="modal fade" id="addMachine" tabindex="-1">
							<div class="modal-dialog modal-dialog-centered">
								<div class="modal-content">
									<div class="modal-body">
										<button type="button" onclick="loadPage('/machine/list')"
											class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
										<img src="assets/img/success_icon.png" alt="">
										<p>an Activity has been added successfully</p>
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
											<button type="button" onclick="loadPage('/machine/list')"></button>
										<p><fmt:message key="something.when.wrong.with.the.server"/></p>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
		</section>
		<script src="assets/js/main.js"></script>
		<script src="assets/js/billing/machine.js"></script> 
	</main>
		


