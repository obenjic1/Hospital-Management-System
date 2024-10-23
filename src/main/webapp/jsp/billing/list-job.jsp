<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="DataTables/datatables.css" />

<link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">
<link href="assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<link href="assets/css/list-users.css" rel="stylesheet">

<main id="list-job" class="main">
	<div class="pagetitle">
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="/">Home</a></li>
				<li class="breadcrumb-item"><fmt:message key="job"/></li>
				<li class="breadcrumb-item active"><fmt:message key="list"/> </li>
			</ol>
		</nav>
	</div>
	<section class="section">
		<div class="row">
			<div class="">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title"><fmt:message key="job.management"/></h5>

						<div class="row" style="position: relative; left:  42.5%;margin:13px">
							<div class="col-sm-4"><button onclick="loadPage('job/displayform')"  data-toggle="tooltip" data-placement="top" title=" new job"  style="width: 100px;position: relative; margin-bottom:10px;left: 130.5%" type="button" class="btn btn-outline-primary"><fmt:message key="new.job"/></button>
							</div>
							<div class="col-sm-4"><button onclick="loadPage('job/displayform-draft')"  data-toggle="tooltip" data-placement="top" title="draft job"  style="width: 100px;position: relative; left:50.5%" type="button" class="btn btn-outline-danger"><fmt:message key="draft.job"/></button>
							</div>
						</div>
						
						<!-- Table with stripped rows -->
						<div class="row" style="position: relative;bottom: 46px; left: 154px;">
					    <div id="startPeriod" class="col-sm-3" style="display: block;">
					         <label for="search_startDate" class="required"><fmt:message key="start.date"/></label>
					        <input type="date" id="search_startDate"  required="required" class="date_picker hasDatepicker" style="width: 255px;">
					    </div>
	    
					    <div id="endPeriod" class="col-sm-3" style="display: block;">
					        <label for="search_endDate" class="required"><fmt:message key="end.date"/></label>
					        <input type="date" id="search_endDate"  required="required" class="date_picker hasDatepicker" style="width: 255px;">
					        
					    </div>
					    
					    <div id="endPeriod" class="col-sm-3" style="display: block;">
					      <button type="button" title="Search"onclick="findByDate()" style="background: none;"> <i class="bi bi-search" ></i> </button>
					        
					    </div>
					    <div > 
						    <label style="left: 71%;position: relative; font-family: bold;color: #012970;"><fmt:message key="total.jobs"/> </label>
						    <span style="left: 75%;position: relative; color: red; font-family: bold;">${totalElement}</span>
					    </div> 
					   
					</div> 
			
						<table id="myTable" class="table datatable">
						  <thead style="background-color: #dddfe3;">
						     <tr>
						      <th scope="col"><fmt:message key="number"/></th>
						      <th scope="col"><fmt:message key="reference"/></th>
						       <th scope="col"><fmt:message key="job.type"/></th>
						      <th scope="col"><fmt:message key="title"/></th>
						      <th scope="col"><fmt:message key="job.status"/></th>
						      <th scope="col"><fmt:message key="registration.date"/></th>
						      <th scope="col"><fmt:message key="customer"/></th>
						      <th scope="col"><fmt:message key="actions"/></th>
							</tr>
						   </thead>
						<tbody>
						  <c:forEach var="job" items="${jobs}" varStatus="loop">
							
						    
							    <c:set var="index" value="${loop.index}" />
							    <%    int index = (Integer) pageContext.getAttribute("index");  %>
						   	   <td>  <%= index + 1 %></td>
							   <td><a>${job.referenceNumber}</a></td>
							   <td><a>${job.jobType.name}</a></td>
							   <td><a>${job.title}</a></td>
							   <td>	 
							   					  <c:if test="${job.status.name=='Registered'}"><a>${job.status.name}</a></c:if>
							   					  <c:if test="${job.status.name=='Draft'}"><a>${job.status.name}</a></c:if>
							   					  <c:if test="${job.status.name=='Confrimed'}"><a class="badge bg-warning">${job.status.name}</a></c:if>
							   					  <c:if test="${job.status.name=='Approved'}"> <a class=" badge bg-success" >${job.status.name}</a></c:if>
							   					  <c:if test="${job.status.name=='Abort'}"><a class=" badge bg-danger" >${job.status.name}</a></c:if>
							   					 
							   					 </td>
							   <td><a><fmt:formatDate type = "both" value = "${job.creationDate}" /></a></td>
							   <td><a>${job.customer.name}</a></td>
							 <td>
								  <select id="jobListActions" name="name" class="form-select">
								     <option >Actions</option>
								     <option data-bs-toggle="modal" data-bs-target="#ExtralargeModal" data-toggle="tooltip" data-placement="top" title="view job details" onclick="loadPageModalForm('job/viewJob/${job.id}');"><fmt:message key="view"/> </option>
  									<c:if test="${job.status.name=='Draft'}">
								      <option data-bs-toggle="modal" data-bs-target="#ExtralargeModal" data-toggle="tooltip" data-placement="top" title="edit Draft Job" onClick="loadPageModalForm('job/update-draft/${job.id}');"><fmt:message key="edit.draft"/></option>
<%-- 								      <option data-bs-toggle="modal" data-bs-target="#ExtralargeModal" data-toggle="tooltip" data-placement="top" title="edit job details" onClick="loadPageModalForm('job/display-draft-form/${job.id}');">Test Edit Job</option> --%>
								      <option data-bs-toggle="modal" data-bs-target="#ExtralargeModal" data-toggle="tooltip" data-placement="top" title="edit job details" onClick="loadPageModalForm('job/get-complete-draft-form/${job.id}');"><fmt:message key="edi.to.copletedJob"/></option>
								      <option data-bs-toggle="modal" data-bs-target="#ExtralargeModal" data-toggle="tooltip" data-placement="top" title="view job details" onclick="loadPageModalForm('job/move/${job.id}');"><fmt:message key="move.job"/> </option>
<%-- 								      <option data-toggle="tooltip" data-placement="top" title="archive a job" onclick="confirmAbort(${job.id})">Abort</option> --%>
								    </c:if>
								    
								    <c:if test="${job.status.name=='Registered'}">
								      <option data-bs-toggle="modal" data-bs-target="#ExtralargeModal" data-toggle="tooltip" data-placement="top" title="edit job details" onClick="loadPageModalForm('job/update-form/${job.id}');"><fmt:message key="edit"/></option>
								      <option data-bs-toggle="modal" data-bs-target="#ExtralargeModal" data-toggle="tooltip" data-placement="top" title="view job details" onclick="loadPageModalForm('job/confimJob/${job.id}');"><fmt:message key="confirm"/></option>
								      <option data-bs-toggle="modal" data-bs-target="#ExtralargeModal" data-toggle="tooltip" data-placement="top" title="view job details" onclick="loadPageModalForm('job/move/${job.id}');"><fmt:message key="move.job"/></option>
								      <option data-bs-toggle="modal" data-bs-target="#ExtralargeModalFile" onclick="loadPageModalControlSheet('job/generate-pdf/${job.id}');"><fmt:message key="control.sheet"/></option>
								     <c:if test="${job.jobEstimates.size()>0}">
 									    <option data-bs-toggle="modal" onclick="loadPage('job/get-estimate/${job.id}');"><fmt:message key="view.estimate"/></option>
								      </c:if> 
								    </c:if> 
								    
								    
								     <c:if test="${job.status.name=='Confrimed'}">
									     <sec:authorize access="hasRole('ROLE_APPROVE_JOB')">
									       <option data-bs-toggle="modal" data-bs-target="#ExtralargeModal" data-toggle="tooltip" data-placement="top" title="view job details" onclick="loadPageModalForm('job/confimJob/${job.id}');"><fmt:message key="validate"/></option>
									     </sec:authorize>
								     	<option data-bs-toggle="modal" data-bs-target="#ExtralargeModal" data-toggle="tooltip" data-placement="top" title="edit job details" onClick="loadPageModalForm('job/update-form/${job.id}');"><fmt:message key="edit"/></option>
								     	<option data-bs-toggle="modal" data-bs-target="#ExtralargeModal" data-toggle="tooltip" data-placement="top" title="view job details" onclick="loadPageModalForm('job/move/${job.id}');"><fmt:message key="move.job"/></option>
 									   <c:if test="${job.jobEstimates.size()>0}">
 									      <option data-bs-toggle="modal" onclick="loadPage('job/get-estimate/${job.id}');"><fmt:message key="view.estimate"/></option>
								        </c:if> 
 									    <option data-bs-toggle="modal" data-bs-target="#ExtralargeModalFile" onclick="loadPageModalControlSheet('job/generate-pdf/${job.id}');"><fmt:message key="control.sheet"/></option>
									    <option data-bs-toggle="modal" data-bs-target="#ExtralargeModal" onclick="loadDynamicPageModal('job/estimate/${job.id}');"><fmt:message key="generate.estimate"/></option>	
								      </c:if>
								   	 <c:if test="${job.status.name=='Approved'}">
								      <option data-bs-toggle="modal" data-bs-target="#ExtralargeModal" data-toggle="tooltip" data-placement="top" title="view job details" onclick="loadPageModalForm('job/move/${job.id}');"><fmt:message key="move.job"/></option>
<%-- 								       <c:if test="${job.controlSheetGenerated=='true'}"> --%>
								       <c:if test="${job.invoiced==0}">
									 		 <option data-bs-toggle="modal" data-bs-target="#ExtralargeModal" onclick="loadDynamicPageModal('job/estimate/${job.id}');"><fmt:message key="generate.estimate"/></option>	
<%-- 								  </c:if>		 --%>
									    </c:if>							  
 									  <c:if test="${job.jobEstimates.size()>0}">
 									    <option data-bs-toggle="modal" onclick="loadPage('job/get-estimate/${job.id}');"><fmt:message key="view.estimate"/></option>
								      </c:if> 
 									  
									</c:if>    
								  
									<c:if test="${job.status.name !='Abort' && job.status.name !='Approved' && job.invoiced==0}">
								      <option data-toggle="tooltip" data-placement="top" title="archive a job" onclick="confirmAbort(${job.id})"><fmt:message key="abort.button"/></option>
								    </c:if>  
									
									<option data-bs-toggle="modal" data-bs-target="#ExtralargeModal" onclick="loadDynamicPageModal('job/reprint/${job.id}');"><fmt:message key="reprint.job"/></option>
								     <option data-bs-toggle="modal" data-bs-target="#ExtralargeModal" onclick="loadDynamicPageModal('job/history/${job.id}');"><fmt:message key="history"/></option>

					              </select>
							  </td>
							</tr>
						   </c:forEach>
						 </tbody>
					   </table>
<!-- 					Pagination with icons -->
<!-- 						<nav aria-label="Page navigation example"> -->
<!-- 						  <ul class="pagination nav-no-border"> -->
<%-- 							<li class="page-item"><input type="button" class="page-link" onclick="refreshUserTable(${currentPage - 1})" value="&laquo;" ${currentPage == 1 ? 'disabled' : ''}></li> --%>
<%-- 							<c:forEach var="i" begin="1" end="${totalPages}"> --%>
<%-- 							  <li class="page-item ${i == currentPage ? 'active' : ''}"><input type="button" class="page-link" onclick="refreshUserTable(${i})" value="${i}"></li> --%>
<%-- 							</c:forEach> --%>
<%-- 							<li class="page-item"><input type="button" class="page-link" onclick="refreshUserTable(${currentPage + 1})" value="&raquo;" ${currentPage == totalPages ? 'disabled' : ''}></li> --%>
<!-- 						  </ul> -->
<!-- 						</nav> -->
					</div>
				</div>
			</div>
		</div>
		</section>
	</main>
	<script src="DataTables/datatables.js"></script>
	<script src="assets/js/main.js"></script>	
	<script src="assets/js/job.js"></script> 
	<script type="text/javascript">
	const picker = datepicker('.date_picker', {
		onSelect: (instance, date)=>{
			
		}
	})
   </script>


