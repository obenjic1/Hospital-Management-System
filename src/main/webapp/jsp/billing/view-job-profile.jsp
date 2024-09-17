
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
                  <button class="nav-link active" data-bs-toggle="tab" data-bs-target="#profile-overview" style="height:35px;background:#012970; color:white;"><h5><fmt:message key="jobsheet.management"/> </h5></button>
                </li>
              </ul>
                <div class="tab-content pt-2">
                  <div class="tab-pane fade show active profile-overview" id="profile-overview" style="margin-left: 10%">    

      				<div class="container" style="position: relative;bottom: -20px;" >
       				<h4><fmt:message key="job.description"/> </h4>
       				<hr>
					  <div class="row">
					 	 <div class="row">
					      <div class="col-sm-4">
					    	<fmt:message key="the.type.of.job"/> : <span id="job-type">${job.jobType.name}</span>
					      </div>
					      <div class="col-sm-4">
					    	<fmt:message key="title.of.job"/> : <span id="job-title">${job.title} </span> 
					      </div>
					      <div class="col-sm-4">
					    	<fmt:message key="name.of.customer"/> : <span id="job-customer"> ${job.customer.name} </span> 
					      </div>
					      </div>
					   	 <div class="row">
					       <div class="col-sm-4">
					       <c:if test="${job.jobType.category==2 || job.jobType.category==3}">
					     	 <fmt:message key="number.of.pages.for.cover"/> : <span id="cover-pages">${job.coverVolume} </span>
					       </c:if>
					      </div>
					      <c:if test="${job.jobType.category==1||job.jobType.category==2 || job.jobType.category==0 || job.jobType.category==3}">
					      <div class="col-sm-4">
					    	<fmt:message key="pages.for.content"/> :  <span id="content-pages"> ${job.contentVolume}</span> 
					      </div>
					      </c:if>
					      <div class="col-sm-4">
					    	<fmt:message key="ctp.fees"/> : <span id="ctp">${job.ctpFees}</span> 
					      </div>
					   </div>
					   
					   <div class="row">
					    <div class="col-sm-4">
					     	<fmt:message key="paper.format"/> : <span id="paper-format"> ${job.paperFormat}</span>
					    </div>
					    <div class="col-sm-4">
					      <fmt:message key="open"/> :<span id="open-l">${job.openLength}</span> | <span id="open-w">${job.openWidth}</span>
					    </div> 
					    <div class="col-sm-4">
					      <fmt:message key="fold"/> : <span id="fold-l">${job.closeLength}</span> | <span id="fold-w">${job.closeWidth}</span>
					    </div>
					    </div>
					    <div class="row">
					    <div class="col-sm-4">
					       <fmt:message key="existing.plate"/> : <span class="${job.existingPlate ? 'true' : 'false'}">${job.existingPlate ? 'yes' : 'no'}</span>
					    </div>
					    <div class="col-sm-4">
					      <fmt:message key="data.supply.by.us"/> : <span class="${job.dataSuppliedByCustomer ? 'true' : 'false'}"> ${job.dataSuppliedByCustomer ? 'yes' : 'no'}</span>
					    </div>
					    <div class="col-sm-4">
					    	<fmt:message key="lay.out.by.us"/>  :   <span class="${job.layOutByUs ? 'true' : 'false'}">${job.layOutByUs ? 'yes' : 'no'}</span>
					    </div>
					     
					    <div class="col-sm-4" id="">
					       <fmt:message key="types.setting.by.us"/>  : <span class="${job.typesettingByUs ? 'true' : 'false'}">${job.typesettingByUs ? 'yes' : 'no'}</span>
					   </div>
					   <div class="col-sm-4" id="">
	    				<fmt:message key="created.date"/>  : <fmt:formatDate type = "both" value = "${job.creationDate}" />
					    	
					   </div>
					    </div>
					   </div>
					<!--            job decription ends     -->
					
					<c:if test="${job.jobType.category==2 || job.jobType.category==3}">
							<h4 id="top2"><fmt:message key="cover.paper.option"/></h4>
						<hr>
					   <div class="row">
					    <div class="row">
					    <div class="col-sm-4">
					      <fmt:message key="paper.type"/> : <span id="cover-paper"> ${coverjobPapers.paperType.name}</span>
					    </div>
					    <div class="col-sm-4">
					    	<fmt:message key="paper.grammage"/> (GSM) :${coverjobPapers.grammage}<span id="cover-grammage"> </span> 
					    </div>
					    <div class="col-sm-4">
					       <fmt:message key="volume"/> : <span id="cover-volume"> ${coverjobPapers.volume} </span> 
					    </div>
					   </div>
					   </div>
					   
					   </c:if>
					   
					   <c:if test="${job.jobType.category==1||job.jobType.category==2 || job.jobType.category==0 || job.jobType.category==3}">
						<h4 id="top2"><fmt:message key="content.paper.option"/></h4>
						<hr>
					    <div class="row">
					   <table class="ta" id="cover-table">
					  <thead>
					    <tr>
					      <th scope="col"><fmt:message key="num"/></th>
					      <th scope="col"><fmt:message key="print.type"/></th>
					      <th scope="col"><fmt:message key="gramage"/>  (GSM)</th>
					      <th scope="col"><fmt:message key="volume"/> (Pages)</th>
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
		  
		 		 <c:if test="${job.jobType.category==2 || job.jobType.category==3}">
					 <h4><fmt:message key="cover.printing.option"/> </h4>

					 <hr>
						<div class="row">
					    <div class="row">
					     <div class="col-sm-3">
					    	<fmt:message key="machine"/> : <span id="cover-machine">${coverjobPapers.jobColorCombinations[0].printingMachine.name} </span>
					    </div>
					    <div class="col-sm-3">
					       <fmt:message key="print.type"/> : <span id="cover-printtype">${coverjobPapers.jobColorCombinations[0].printType.name} </span> 
					    </div>
					    <div class="col-sm-3">
					    	<fmt:message key="color.combination"/> : <span id=cover-color-front></span> ${coverjobPapers.jobColorCombinations[0].frontColorNumber} / <span id=cover-color-back>${coverjobPapers.jobColorCombinations[0].backColorNumber}</span> 
					    </div>
					     <div class="col-sm-3">
					    	<fmt:message key="signature"/> : <span id=cover-signature>${coverjobPapers.jobColorCombinations[0].numberOfSignature}</span> 
					    </div>
					    </div>
					  </div>
					  
					  </c:if>
					  <c:if test="${job.jobType.category==1||job.jobType.category==2 || job.jobType.category==0 || job.jobType.category==3}">
					   <h4><fmt:message key="content.printing.option"/></h4>

					 <hr>
					  <div class="row">
					<table class="ta" id="content-table">
					    <thead>
					        <tr>
                                <th scope="col"><fmt:message key="num"/></th>
                                <th scope="col"><fmt:message key="paper.type"/></th>
                                <th scope="col"><fmt:message key="Machine"/></th>
                                <th scope="col"><fmt:message key="print.type"/></th>
                                <th scope="col"><fmt:message key="color.combination"/></th>
                                <th scope="col"><fmt:message key="signature"/></th>
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
			<h4><fmt:message key="finishing.option"/></h4>
			<hr>
                <div class="row ">
                    <div class="col-sm-4">
                        <div><fmt:message key="x.perforated"/> : <span id="x-perforated"> </span>${job.getJobActivity().getXPerforated()}</div>
                        <div><fmt:message key="x.umbered "/> : <span id="x-numbered"></span> ${job.getJobActivity().getXNumbered()}</div>
                        <div><fmt:message key="x.crossed"/>  : <span id="x-crossed"></span>${job.getJobActivity().getXCross()}</div>
                        <div><fmt:message key="x.wired.stitched"/>  : <span id="x-wired"></span>${job.getJobActivity().getXWiredStiched()}</div>
                        <div><fmt:message key="creased"/> : <span id="crease"></span>${job.getJobActivity().getXCreased()}</div>
                    </div>
                    <div class="col-sm-4">
                        <div><fmt:message key="lamination.sides"/> : <span id="laminated-sides"></span> ${job.getJobActivity().getLamination()} </div>
<%--                         <div> Glueing Bound: <span id="glue-bound"></span>${job.getJobActivity().getGlueOption()}</div> --%>
                        <div><fmt:message key="binding.type"/> : <span id="binding-type"></span> ${job.getJobActivity().getBindingType().getName()}</div>
                        <div><fmt:message key="sewn"/> :<span class=" ${job.getJobActivity().isSewn() ? 'true' : 'false'}"> ${job.getJobActivity().isSewn() ? 'yes' : 'no'}</span>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div><fmt:message key="handgather"/> :<span class=" ${job.getJobActivity().isHandgather() ? 'true' : 'false'}"> ${job.getJobActivity().isHandgather() ? 'yes' : 'no'}</span>
                        </div>
                        <div><fmt:message key="stitching"/> :  <span class=" ${job.getJobActivity().isStitching() ? 'true' : 'false'}"> ${job.getJobActivity().isStitching() ? 'yes' : 'no'}</span>
                        </div>
                        <div><fmt:message key="trimmed"/> :<span class=" ${job.getJobActivity().isTrimmed() ? 'true' : 'false'}"> ${job.getJobActivity().isTrimmed() ? 'yes' : 'no'}</span>
                        </div>
                        <div> <fmt:message key="sellotaped"/> :<span class=" ${job.getJobActivity().isSelloptaped() ? 'true' : 'false'}"> ${job.getJobActivity().isSelloptaped() ? 'yes' : 'no'}</span>
                        </div>
                    </div>
                </div>
                <br>
                <div class="row" style="position: relative;bottom: -19px;">
                  <div class ="col-lg-6">
                  	<h4><fmt:message key="estimates"/></h4>
                  </div>
                  <c:if test="${job.proofread=='true'}">
                    <div class ="col-lg-6" id="proofreaded">
                    <span style="position: relative;justify-content: end;bottom: 2px;left: 332px; font-family: bold; font-size: 16px; color: green"> <fmt:message key="proof.readed"/></span>
                     </div>
                   </c:if>
                    <c:if test="${job.proofread=='false'&&job.status.name=='Registered'}">
                    <div class ="col-lg-6">
                  	   <button style="position: relative;justify-content: end;bottom: 2px;left: 332px;" onclick="proofreadByTheCustomer('${job.id}')"  data-toggle="tooltip" data-placement="top" title="Mark as Proofread" class="btn btn-outline-danger" data-bs-dismiss="modal"><fmt:message key="proofread"/> ?</button>
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
	        	<div class ="col-sm-12">
	       		   <c:if test="${job.proofread=='true'&&job.status.name=='Confrimed'}">
	       		 	<span   style ="width:125px;float:right;font-family: bold; font-size: 16px;"  class="" id=""><fmt:message key="job.confirmed"/></span>	
	       		 	 </c:if>
	       		 	 <c:if test="${job.proofread=='true'&&job.status.name=='Approved'}">
	       		 	<span   style ="width:155px;float:right;font-family: bold; font-size: 16px;" class="outline-primary" id=""><fmt:message key="job.approved"/></span>	
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
  
  
  