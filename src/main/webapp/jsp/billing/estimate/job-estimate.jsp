
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 


<link href="assets/css/profile.css" rel="stylesheet">
<link href="assets/css/billing/job.css" rel="stylesheet">

<main id="job-estimate" class="main">
	<section class="section profile" id="modal-details">
      	<div class="row"></div>
        	<div class="col-xl-8" style="width: 100%">
          		<div class="card">
            		<div class="card-body pt-4">
              			<ul class="nav nav-tabs nav-tabs-bordered">
                			<li class="nav-item">
                  				<button class="nav-link active" data-bs-toggle="tab" data-bs-target="#profile-overview" >Job Estimate </button>
                			</li>

              			</ul>
                		<div id="tab-1" class="tab-content pt-2">
                  			<div class="tab-pane fade show active profile-overview" id="profile-overview" style="margin-left: 10%">    

      							<div class="container" style="position: relative;" >
									<div class="row " style="  font-weight: bold;">
										<div class="col-sm-4"> <h3><strong>Estimate</strong></h3></div>
										<div class="col-sm-3"></div>
										<div class="col-sm-5  customer-info">
											<div>${job.customer.name}</div>
											<div>${job.customer.telephone}</div>
											<div>${job.customer.address}</div>
											
										</div>
									</div><hr>
		
									<div class="row">
										<form>
                                            <input id="jobId" value="${job.id}" type="hidden">
                                            <div id="main-estimate-div" class="">
                                                <div class="" id="quantity-div" style="display:none">
                                                    <label> Enter Quantity</label>
                                                    <div style="  display: flex;width: 98%;padding-bottom:15px;">
                                                        <input estimate-quantity type=number class="input-box">
                                                        <button type="button" id="deleteButton"  onclick="removeEstmateContentNode(this);"><i class="ri-delete-bin-3-line"></i> </button>
                                                    </div>
                                                </div>
                                                <div class="" id="quantity-div" style="display:inline">
                                                    <label> Enter Quantity</label>
                                                        <div style="  display: block ruby;padding-bottom:15px;">
                                                            <input estimate-quantity type=number class="input-box" style=" width: 95%;" >
                                                            <button type="button"  id="duplicateButton"  onclick="addNextEstimateChild();" ><i class="ri-add-fill"></i></button>

                                                        </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                        <label>Advance Payment</label>
                                                      <div>
                                                            <input type=number id="advancePercentage" value="0" style=" width: 95%;" >
                                                      </div>

                                            </div>
                                            
                                            <div class="" id="extra-fees">
                                                    <div style="padding-bottom:15px">
                                                        <label>Extra Fees (FCFA)</label>
                                                      <div>
                                                            <input type=number id="extra-fee" value="0" style=" width: 95%;">
                                                      </div>
                                                    </div>
                                                    <div>
                                                        <label>Description</label>
                                                        <div>
                                                        <textarea id="extra-fee-description" name="" rows="5" value="" ></textarea>
                                                        </div>
                                                    </div>
                                            </div>
                                        </form>
                                        <hr><br>
                                        <div class="" style="margin-top:50px;">
                                            <button class="btn btn-primary" onclick="generateEstimate('job/generate/${job.id}', 'tab-1', 'tab-2');"><fmt:message key="generate"/></button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div id="tab-2" style="display:none;">
                       	
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</main><!-- End #main -->
<script src="assets/js/billing/job.js"></script> 

  
  
  