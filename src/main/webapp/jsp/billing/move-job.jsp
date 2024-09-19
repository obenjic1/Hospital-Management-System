<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<head>
  <link href="assets/css/billing/job.css" rel="stylesheet">
</head>
<main id="add-machine">
<!-- 	action="machine/add-machine -->
	<section>
			<div class="card">
				<div class="card-body">
					<h5 class="card-title text-center pb-0 fs-4"><fmt:message key="move.job"/></h5>
					<p class="text-center small"><fmt:message key="move.this.job.to.a.different.department"/></p>
					
					<div class ="row " style=" padding-left: 8%;padding-right: 3%;">
							<div class="col-md-5">
								<div class ="row py-3">
								  <div> <fmt:message key="from"/>   </div>
								    <div><span><strong>${movement.department.name}</strong></span> </div>
								</div>
								<div class ="row">
								 <label for="" class="form-label"> <fmt:message key="description"/> :</label>
								<div><span>${movement.description} </span> </div>
								</div>
								<div class ="row py-3">
								  <div> <fmt:message key="date"/> : </div>
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
							  <div class="row py-1" style="margin-bottom: -63px;">  
									<button  style=" width: 94px;margin-left: -24%;margin-top: 18px;" type="button"  class="btn btn-primary"data-bs-dismiss="modal" onclick="moveJob('${job.id}')" id="next-btn" > <fmt:message key="send"/> </button>	
							  </div> 
						</div>
					
					</div>
					<div>
<%-- 									<button  style=" width: 94px;" type="button"  class="btn btn-primary"data-bs-dismiss="modal" onclick="moveJob('${job.id}')" id="next-btn" >history </button> --%>
									<input class="form-check-input" type="checkbox" id="history-ckeckbox" onchange="movementHistory()">
									<label class="form-ckheck-label" for="Movement">History</label>
							  </div> 
					<hr>
					<div class="row " id="history_div" style="display:none"> 
							  
						
							<table class="ta" id="cover-table" style="text-align:">
									<thead style="text-align:">
										<tr>
											<th scope="col"><fmt:message key="date"/> </th>
											<th scope="col"></th>
											<th scope="col"></th>
											<th scope="col"><fmt:message key="user"/> </th>
											<th scope="col"></th>
											<th scope="col"><fmt:message key="department"/></th>
											<th scope="col"></th>
											<th scope="col"> <fmt:message key="operation"/></th>
										</tr>
									</thead>
									<tbody>
										 <c:forEach var="movement" items="${movements}" varStatus="loop">
											 <tr>
												  	<td><a><fmt:formatDate type = "both" value = "${movement.creationDate}"/></a></td>
												  	<td></td>
												  	<td></td>
												    <td>${movement.user.username}</td>
												    <td></td>
												    <td>${movement.department.name}</td>
												  	<td></td>
												    <td>${movement.description}</td>
											  </tr>
										 </c:forEach>
									</tbody>
					       </table>
							  
							 
					</div>
					</div>
				</div>
		</section>
		<script src="assets/js/main.js"></script>
		<script src="assets/js/billing/machine.js"></script> 
	</main>
		


