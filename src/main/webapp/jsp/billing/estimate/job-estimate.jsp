
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 


<link href="assets/css/profile.css" rel="stylesheet">
<link href="assets/css/billing/job.css" rel="stylesheet">

<main id="users-list" class="main">
	<section class="section profile" id="modal-details">
      	<div class="row"></div>
        	<div class="col-xl-8" style="width: 100%">
          		<div class="card">
            		<div class="card-body pt-4">
              			<ul class="nav nav-tabs nav-tabs-bordered">
                			<li class="nav-item">
                  				<button class="nav-link active" data-bs-toggle="tab" data-bs-target="#profile-overview" > </button>
                			</li>
              			</ul>
                		<div class="tab-content pt-2">
                  			<div class="tab-pane fade show active profile-overview" id="profile-overview" style="margin-left: 10%">    
                  				<h5 class="card-title">job Estimate</h5>
      							<div class="container" style="position: relative;bottom: -20px;" >
									<div class="row " style="  font-weight: bold;">
										<div class="col-sm-4"> <h3><strong>Estimate</strong></h3></div>
										<div class="col-sm-4"></div>
										<div class="col-sm-4  customer-info">
											<div>Name : ${job.customer.name}</div>
											<div>Tell : ${job.customer.telephone}</div>
											<div>Address :${job.customer.address}</div> 
											
										</div>
									</div>
									<div class="row">
										<div class="col-sm-4">
											Date : <span><fmt:formatDate type = "both" value = "${now}" /></span>
										</div>
									</div>
									<div class="row">
										<form>
										<input id="jobId" value="${job.id}" type="hidden">
										<div id="main-estimate-div" class="col-xl-8">
											<div class="col-sm-8" id="quantity-div" style="display:none">
												<label> Enter Quantity</label>
												<div style="  display: flex;width: 115%;padding-bottom:15px;;">
													<input estimate-quantity type=number class="input-box">
													<button type="button" id="deleteButton"  onclick="removeEstmateContentNode(this);"><i class="ri-delete-bin-3-line"></i> </button>
												</div>
											</div>
											<div class="col-sm-8" id="quantity-div" style="display:inline">
												<label> Enter Quantity</label>
													<div style="  display: block ruby;padding-bottom:15px;">
														<input estimate-quantity type=number class="input-box" style="  width: 71%;" >
														<button type="button"  id="duplicateButton"  onclick="addNextEstimateChild();" ><i class="ri-add-fill"></i></button>

													</div>
											</div>
										</div>
										<div class="col-sm-6 row" id="extra-fees">
												<div style="padding-bottom:15px">
													<label>Extra Fees (FCFA)</label>
												  <div>
														<input type=number id="extra-fee" value="0">
												  </div>
												</div>
												<div>
													<label>Description</label>
													<div>
													<textarea id="extra-fee-description" name="" rows="5" cols="50"></textarea>
													</div> 
												</div>
										</div>
										 <div class ="">
								        	<button   type="button" style="width:125px;float:right"  class="btn btn-primary" onclick="generateEstimate('/job/generate/${job.id}');"><fmt:message key="generate"/></button>			
								        </div>
								        </form>
									 </div>
               						</div>
              					</div>
      	     				</div>
            			</div>
          			</div>
        		</div>
      		</div>
    	</section>
  </main><!-- End #main -->
  
  
  