
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<head>
<link href="assets/css/profile.css" rel="stylesheet">
<link href="assets/css/billing/job.css" rel="stylesheet">
</head>
<main id="users-list" class="main">
    <section class="section profile" id="modal-details">
      <div class="row">
        <div class="col-xl-8" style="width: 100%">
          <div class="card">
            <div class="card-body pt-4">

              <ul class="nav nav-tabs" style="margin-left:10%; justify-content: center; background-color: #012970;">
                <li class="nav-item">
                  <button class="nav-link active" data-bs-toggle="tab" data-bs-target="#profile-overview" style="height:35px;background:#012970; color:white;"><h5>Job </h5></button>
                </li>
              </ul>
                <div class="tab-content pt-2">
                  <div class="tab-pane fade show active profile-overview" id="profile-overview" style="margin-left: 10%">    

      				<div class="container" style="position: relative;bottom: -20px;" >
       				<h4>Description</h4>
       				<hr>
					  <div class="row">
					 	 <div class="row">
					      <div class="col-sm-4">
					    	The Type of Job : <span id="job-type">${job.jobType.name}</span>
					      </div>
					      <div class="col-sm-4">
					    	 Title of Job : <span id="job-title">${job.title} </span> 
					      </div>
					      <div class="col-sm-4">
					    	 Name of Customer : <span id="job-customer"> ${job.customer.name} </span> 
					      </div>
					      </div>
					   	 <div class="row">
					       <div class="col-sm-4">
					       <c:if test="${job.jobType.category==2}">
					     	  Number of Pages for Cover : <span id="cover-pages">${job.coverVolume} </span>
					       </c:if>
					      </div>
					      <c:if test="${job.jobType.category==1||job.jobType.category==2 || job.jobType.category==0}">
					      <div class="col-sm-4">
					    	Number of Pages for Content :  <span id="content-pages"> ${job.contentVolume}</span> 
					      </div>
					      </c:if>
					      <div class="col-sm-4">
					    	 CTP Fees : <span id="ctp">${job.ctpFees}</span> 
					      </div>
					   </div>
					   
					   <div class="row">
					    <div class="col-sm-4">
					    	Paper Format : <span id="paper-format"> ${job.paperFormat}</span>
					    </div>
					    <div class="col-sm-4">
					    	Open :<span id="open-l">${job.openLength}</span> | <span id="open-w">${job.openWidth}</span></div> 
					    	<div class="col-sm-4">
					    	Fold :<span id="fold-l">${job.closeLength}</span> | <span id="fold-w">${job.closeWidth}</span>
					    </div>
					    </div>
					    <div class="row">
					    <div class="col-sm-4">
					    	Existing Plate: <span class="${job.existingPlate ? 'true' : 'false'}">${job.existingPlate ? 'yes' : 'no'}</span>
					    </div>
					    <div class="col-sm-4">
					    	Data Supply By Us : <span class="${job.dataSuppliedByCustomer ? 'true' : 'false'}"> ${job.dataSuppliedByCustomer ? 'yes' : 'no'}</span>
					    </div>
					    <div class="col-sm-4">
					    	Lay Out by Us :   <span class="${job.layOutByUs ? 'true' : 'false'}">${job.layOutByUs ? 'yes' : 'no'}</span>
					    </div>
					     
					    <div class="col-sm-4" id="">
					    	Type-Setting By Us : <span class="${job.typesettingByUs ? 'true' : 'false'}">${job.typesettingByUs ? 'yes' : 'no'}</span>
					   </div>
					   <div class="col-sm-4" id="">
	    				Created Date : <fmt:formatDate type = "both" value = "${job.creationDate}" />
					    	
					   </div>
					    </div>
					   </div>
					<!--            job decription ends     -->
					
					<c:if test="${job.jobType.category==2}">
							<h4 id="top2">Cover Paper Option</h4>
							<hr>
					   <div class="row">
					    <div class="row">
					    <div class="col-sm-4">
					    	Paper Type : <span id="cover-paper"> ${coverjobPapers.paperType.name}</span>
					    </div>
					    <div class="col-sm-4">
					    	 Paper Grammage (GSM) :${coverjobPapers.grammage}<span id="cover-grammage"> </span> 
					    </div>
					    <div class="col-sm-4">
					    	Volume : <span id="cover-volume"> ${coverjobPapers.volume} </span> 
					    </div>
					   </div>
					   </div>
					   
					   </c:if>
					   
					   <c:if test="${job.jobType.category==1||job.jobType.category==2 || job.jobType.category==0}">
						<h4 id="top2"> Content Paper Option</h4>
						<hr>
					    <div class="row">
					   <table class="ta" id="cover-table">
					  <thead>
					    <tr>
					      <th scope="col">Num</th>
					      <th scope="col">Print Type</th>
					      <th scope="col">Gramage (GSM)</th>
					      <th scope="col">Volume (Pages)</th>
					    </tr>
					  </thead>
					  <tbody>
			
						  <c:forEach var="jobPaper" items="${jobPapers}" varStatus="loop">
							    <c:set var="index" value="${loop.index}" />
							    <%    int index = (Integer) pageContext.getAttribute("index");  %>
							 <td>  <%= index + 1 %></td>
							   <td><a>${jobPaper.paperType.name}</a></td>
							   <td><a>${jobPaper.grammage}</a></td>
							   <td><a>${jobPaper.volume}</a></td>
							 </tr>
							   </c:forEach>
							    </tbody>
						</table>
					  	</div>
					  	</c:if>
					      <br>
		  
		 		 <c:if test="${job.jobType.category==2}">
					 <h4>Cover Printing Option</h4>

					 <hr>
						<div class="row">
					    <div class="row">
					     <div class="col-sm-3">
					    	 Machine  : <span id="cover-machine">${coverjobPapers.jobColorCombinations[0].printingMachine.name} </span>
					    </div>
					    <div class="col-sm-3">
					    	Print Type : <span id="cover-printtype">${coverjobPapers.jobColorCombinations[0].printType.name} </span> 
					    </div>
					    <div class="col-sm-3">
					    	Color Combination : <span id=cover-color-front></span> ${coverjobPapers.jobColorCombinations[0].frontColorNumber} / <span id=cover-color-back>${coverjobPapers.jobColorCombinations[0].backColorNumber}</span> 
					    </div>
					     <div class="col-sm-3">
					    	Signature : <span id=cover-signature>${coverjobPapers.jobColorCombinations[0].numberOfSignature}</span> 
					    </div>
					    </div>
					  </div>
					  
					  </c:if>
					  <c:if test="${job.jobType.category==1||job.jobType.category==2 || job.jobType.category==0}">
					   <h4>Content Printing Option</h4>

					 <hr>
					  <div class="row">
					<table class="ta" id="content-table">
					    <thead>
					        <tr>
                                <th scope="col">Num</th>
                                <th scope="col">Paper Type</th>
                                <th scope="col">Machine</th>
                                <th scope="col"> Print Type</th>
                                <th scope="col">Color Combination</th>
                                <th scope="col">Signature</th>
					        </tr>
					    </thead>
					    <tbody>
                            <c:forEach var="jobPaper" items="${jobPapers}" varStatus="loop">
                                <c:set var="index" value="${loop.index}" />
                                    <%    int index = (Integer) pageContext.getAttribute("index");  %>
                                    <tr>
                                        <td rowSpan="${fn:length(jobPaper.jobColorCombinations)+1}">  <%= index + 1 %></td>
                                    </tr>
                                    <c:forEach var="color" items="${jobPaper.jobColorCombinations}" varStatus="loop">
                                         <tr>
                                           <td>${jobPaper.paperType.name}</td>
                                           <td>${color.printingMachine.name}</td>
                                           <td>${color.printType.name}</td>
                                           <td> ${color.frontColorNumber} / ${color.backColorNumber}</td>
                                           <td>${color.numberOfSignature}</td>
                                         </tr>
                                    </c:forEach>
                            </c:forEach>
					    </tbody>
					</table>
				</div>
				</c:if>
			</div>
			
			<br>
			<h4>Finishing option</h4>
			<hr>
                <div class="row ">
                    <div class="col-sm-4">
                        <div> X Perforated : <span id="x-perforated"> </span>${job.getJobActivity().getXPerforated()}</div>
                        <div> X Numbered : <span id="x-numbered"></span> ${job.getJobActivity().getXNumbered()}</div>
                        <div> X Crossed : <span id="x-crossed"></span>${job.getJobActivity().getXCross()}</div>
                        <div> X Wired-stitched : <span id="x-wired"></span>${job.getJobActivity().getXWiredStiched()}</div>
                        <div> Creased : <span id="crease"></span>${job.getJobActivity().getXCreased()}</div>
                    </div>
                    <div class="col-sm-4">
                        <div> Lamination Sides : <span id="laminated-sides"></span> ${job.getJobActivity().getLamination()} </div>
<%--                         <div> Glueing Bound: <span id="glue-bound"></span>${job.getJobActivity().getGlueOption()}</div> --%>
                        <div> Binding Type : <span id="binding-type"></span> ${job.getJobActivity().getBindingType().getName()}</div>
                        <div> Sewn :<span class=" ${job.getJobActivity().isSewn() ? 'true' : 'false'}"> ${job.getJobActivity().isSewn() ? 'yes' : 'no'}</span>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div> Handgather :<span class=" ${job.getJobActivity().isHandgather() ? 'true' : 'false'}"> ${job.getJobActivity().isHandgather() ? 'yes' : 'no'}</span>
                        </div>
                        <div> Stitching :  <span class=" ${job.getJobActivity().isStitching() ? 'true' : 'false'}"> ${job.getJobActivity().isStitching() ? 'yes' : 'no'}</span>
                        </div>
                        <div> Trimmed :<span class=" ${job.getJobActivity().isTrimmed() ? 'true' : 'false'}"> ${job.getJobActivity().isTrimmed() ? 'yes' : 'no'}</span>
                        </div>
                        <div> Sellotaped :<span class=" ${job.getJobActivity().isSelloptaped() ? 'true' : 'false'}"> ${job.getJobActivity().isSelloptaped() ? 'yes' : 'no'}</span>
                        </div>
                    </div>
                </div>
                <br>
                <div class="row" style="position: relative;bottom: -19px;">
                  <div class ="col-lg-6">
                  	<h4>Estimates</h4>
                  </div>
                  <c:if test="${job.proofread=='true'&&job.status.name=='Confrimed'||job.status.name=='Approved'}">
                    <div class ="col-lg-6" id="proofreaded">
                    <span style="position: relative;justify-content: end;bottom: 2px;left: 332px; font-family: bold; font-size: 16px; color: green">Proof Readed</span>
                     </div>
                   </c:if>
                    <c:if test="${job.proofread=='false'&&job.status.name=='Registered'}">
                    <div class ="col-lg-6">
                  	   <button style="position: relative;justify-content: end;bottom: 2px;left: 332px;" onclick="proofreadByTheCustomer('${job.id}')"  data-toggle="tooltip" data-placement="top" title="Mark as Proofread" class="btn btn-outline-danger" data-bs-dismiss="modal">Proofread ?</button>
                     </div>
                   </c:if>
                 </div> 
                <hr>
                <div class="row" >
                        <c:forEach var="jobEstimate" items="${jobEstimates}" varStatus="loop">
                            <div class="col-sm-3">
                                <div class="card">

                                        <a class="nav-link" href="#" onclick="window.open('${pageContext.request.contextPath}/file/download?file=${jobEstimate.reference}.pdf&dir=folder.estimate')">
                                            <span>${jobEstimate.reference}</span>
                                            <i class="bi bi-download"> </i>
                                        </a>
                                </div>
                            </div>
                        </c:forEach>
                </div>
             <c:if test="${job.proofread=='true'}">
             <div class ="row py-3 "style="margin-top:50px" >
		     	<div class ="col-sm-6">
		     		 <button type="button" style ="width:125px;float:left" class="btn btn-outline-danger" onClick="loadPageModalForm('job/update-form/${job.id}');"><fmt:message key="edit"/></button>	
		    	</div>
		    	
	        	<div class ="col-sm-6">
		        	 <c:if test="${job.proofread=='true'&&job.status.name=='Registered'}">
		       		 	<button type="button"  style ="width:125px;float:right" onclick="confirmJob(${job.id})"  class="btn btn-outline-primary" id=""><fmt:message key="confirm"/></button>	
		       		 </c:if>
		       		 <c:if test="${job.proofread=='true'&&job.status.name=='Confrimed'}">
		       		 	<button type="button"  style ="width:125px;float:right" onclick="confirmApprove(${job.id})"  class="btn btn-outline-primary" id=""><fmt:message key="approve"/></button>	
		       		 </c:if>
	       		 </div>
	     	</div>
	     	</c:if>
	
                
			</div>
			

			</div>
			
            </div>
            </div>
          </div>
        </div>
    </section>
  </main><!-- End #main -->
  
  
  