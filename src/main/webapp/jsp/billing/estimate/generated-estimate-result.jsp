
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.time.LocalDate"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<link href="assets/css/profile.css" rel="stylesheet">
<link href="assets/css/billing/job.css" rel="stylesheet">

<main id="users-list" class="main">
    <section class="section profile" id="modal-details">
      <div class="row">
        <div class="col-xl-8" style="width: 100%">
          <div class="card">
            <div class="card-body pt-4">
              <ul class="nav nav-tabs nav-tabs-bordered">
                <li class="nav-item">
                  <button class="nav-link active" data-bs-toggle="tab" data-bs-target="#profile-overview" > <fmt:message key="overview"/></button>
                </li>
              </ul>
                <div class="tab-content pt-2">
                  <div class="tab-pane fade show active profile-overview" id="profile-overview" style="margin-left: 10%">    
                  <class="card-title"><h5></h5>
      				<div class="container estimate" style="position: relative;bottom: -20px" >
					  <div class="row">
					 	 <div class="row">
					 	 	<div class="col-sm-6" style="text-align:left">
					 	 		<h3><strong>Estimate</strong></h3>
					 	 	</div>
					 	 	<div class="col-sm-3">
					 	 	</div>
					 	 	<div class="col-sm-3" style="text-align:left">
					 	 		<div>${job.customer.name}</div>
								<div>${job.customer.telephone}</div>
								<div>${job.customer.address}</div> 
					 		</div>
					 	 </div>
					 	 <div class="row">
					 	 		Date :<fmt:formatDate value="${now}" type="both" dateStyle="long" timeStyle="long" />
					 	 </div>
					 	<div class="row table-responsive">
					 	
					 		<table class="ta" id="cover-table">
					  			<tbody>
									<tr>
								   		<td>Description</td>
								   		<td>${job.title}</td>
								   		<td></td>
								   		
							 		</tr>
							 		<tr>
								   		<td>Format</td>
								   		<td> 
								   			Open  :<span>${job.openLength}</span> x <span>${job.openWidth}</span>mm<br>
								   		 	Folded : <span>${job.closeLength}</span> x <span>${job.closeWidth}</span>mm 
								   		</td>
								   		<td></td>
							 		</tr>
							 		<tr>
								   		<td>Volume</td>
								   		<td> Cover : <span>${job.coverVolume}</span>  <span>Pages</span><br>
								   			 Content : <span>${job.contentVolume}</span> <span>Pages</span> 
								   		</td>
								   		<td></td>
							 		</tr>
							 		<tr>
								   		<td>TypeSetting &<br> Reproduction</td>
								   		<td>
								   			<span>
								   				<c:forEach var="typeSettingActivity" items="${typeSettingActivities}" varStatus="loop">
												   ${typeSettingActivity} <br>
												</c:forEach>
								   			</span>
								   		</td>
								   		<td></td>
							 		</tr>
							 		<tr>
								   		<td>Printing</td>
								   		<td>
								   			<span>
								   				${coverJobPaper.contentType.name } : 
								   				<c:forEach var="jobColorCombination" items="${coverJobPaper.jobColorCombinations}" varStatus="loop">
								   					<span> 
												   		${jobColorCombination.frontColorNumber} / ${jobColorCombination.backColorNumber}  ${jobColorCombination.printType.name} 
												   </span>
												   <br>
												</c:forEach>

								   			</span>
								   			
								   			<span>
								   			<c:set var="contentType" value="${contentJobPapers[0].contentType.name}"></c:set>
								   				<span>
								   					<c:if test="${ contentJobPapers[0].contentType.name==contentType}">
								   						${contentJobPapers[0].contentType.name } : <br>
								   						<c:set var="contentType" value="contenu "></c:set>
								   					</c:if>
								   				</span>
								   				
								   					<c:forEach var="jobPaper" items="${contentJobPapers}" varStatus="loop">

											   					<ul>
															   		<c:forEach var="jobColorCombination" items="${jobPaper.jobColorCombinations}" varStatus="loop">
													   					<li>
													   					<span> 
																	   		${jobColorCombination.frontColorNumber} / ${jobColorCombination.backColorNumber}  ${jobColorCombination.printType.name} 
																	   </span>
																	   </li>
																	</c:forEach> 
																	</ul>
													</c:forEach>
								   				
								   				
								   			</span>
								   		</td>
								   		<td></td>
							 		</tr>
							 		
							 		<tr>
								   		<td>Finishing</td>
								   		<td><span>${ finishingActivities}</span></td>
								   		<td></td>
							 		</tr>
							 		
							 		<tr>
								   		<td>Paper </td>
									   	<td>
								   		 	Cover :<span>${coverJobPaper.paperType.name}</span>
								   		 		<span style="float:right">${coverJobPaper.grammage} GSM</span>
								   		 
								   		 	<br>
								   		 		<span>
								   					<c:set var="contentType" value="${contentJobPapers[0].contentType.name}"></c:set>
							   						<span>
							   							<c:if test="${ contentJobPapers[0].contentType.name==contentType}">
							   								${contentJobPapers[0].contentType.name } : 
							   							<c:set var="contentType" value="contenu "></c:set>
							   							</c:if>
							   						</span>
						   							<ul>
							   							<c:forEach var="jobPaper" items="${contentJobPapers}" varStatus="loop">
							   								<li>
											   					<span style="display:left">								   					
											   						${jobPaper.paperType.name}
																</span>
															 	<span style="float:right"> 
																 	${jobPaper.grammage} GSM <br>
															 	</span>
															 	<br>
														 	</li>
														</c:forEach>
													</ul>
								   				</span>
											</td>
										<td>
										
										</td>
							 		</tr>
							    </tbody>
						</table>
					 </div>
					 <div class="row">
					 	<table class="table-responsive ta" id="cover-table">
					      <thead id="estimate-header"> 
					      	<tr>
						      <th scope="col">Quantity</th>
						      <th><span style="padding:10px"></span></th>
						      <th scope="col">Unit price(FCFA)</th>
						      <th scope="col">Total Price (FCFA)</th>
					   		 </tr>
					  	 </thead>
					 		<tbody>
					 		 <tr>
						     	<c:forEach var="estimate" items="${estimates}" varStatus="loop">
								  <tr>
								  <td></td>
								   <td><a>${estimate.quantity}</a></td>
								   <td><a>${estimate.unitPrice}</a></td>
								   <td><a>${estimate.totalPrice}</a></td>
								   </tr>
							  	</c:forEach>
							 </tr>
						   </tbody>
						 </table>
					  	</div>
					 </div>
					 <div>
						<button   type="button" style="width:125px;float:right"  class="btn btn-primary" onclick="printEstimate();"><fmt:message key="print"/></button>			
					 </div>
               </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </main><!-- End #main -->
  
  
  