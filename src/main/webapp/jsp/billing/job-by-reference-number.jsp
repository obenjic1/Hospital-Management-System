<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="assets/css/list-users.css" rel="stylesheet">
<link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">
<link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">
<link href="assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<link href="assets/css/list-users.css" rel="stylesheet">

<main id="list-job" class="main">
	<div class="pagetitle">
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="/">Home</a></li>
				<li class="breadcrumb-item"> job</li>
				<li class="breadcrumb-item active">Job By Reference</li>
			</ol>
		</nav>
	</div>
	<section class="section">
		<div class="row">
			<div class="col-lg-12">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title"><fmt:message key="job.management"/></h5>
						<!-- Table with stripped rows -->
						<div class="row" style="position: relative;bottom: 46px; left: 154px;">
					    <div id="startPeriod" class="col-sm-3" style="display: block;">
					         <label for="search_startDate" class="required">Start date</label>
					        <input type="date" id="search_startDate"  required="required" class="date_picker hasDatepicker" style="width: 255px;">
					    </div>
	    
					    <div id="endPeriod" class="col-sm-3" style="display: block;">
					        <label for="search_endDate" class="required">End date</label>
					        <input type="date" id="search_endDate"  required="required" class="date_picker hasDatepicker" style="width: 255px;">
					        
					    </div>
					    
					    <div id="endPeriod" class="col-sm-3" style="display: block;">
					      <button type="button" title="Search"onclick="findByDate()" style="background: none;"> <i class="bi bi-search" ></i> </button>
					        
					    </div>
					    
					</div> 
						<table id="myTable" class="table datatable">
						  <thead style="background-color: #dddfe3;">
						    <tr>
						      <th scope="col">Number</th>
						       <th scope="col">Job type</th>
						      <th scope="col">Title</th>
							  <th scope="col">Reference</th>
						      <th scope="col">JobStatus</th>
						      <th scope="col">Registration date</th>
						      <th scope="col">Customer</th>
						      <th scope="col">Actions</th>
							</tr>
						   </thead>
						<tbody>
						   <tr>
<!-- 							 <td>1</td> -->
							   <td><a>${result.title}</a></td>
							   <td><a>${result.jobType.name}</a></td>
							   <td><a>${result.referenceNumber}</a></td>
							   <td><a>${result.status}</a></td>
							   <td><a><fmt:formatDate type = "both" value = "${result.creationDate}" /></a></td>
							   <td><a>${result.customer.name}</a></td>
							 <td>
							  <select id="coverPaperType" name="name" class="form-select">
							     <option >Action</option>
							     <option data-bs-toggle="modal" data-bs-target="#ExtralargeModal" data-toggle="tooltip" data-placement="top" title="view job details" onclick="loadDynamicPageModal('job/viewJob/${result.id}');">View</option>
							     <option data-bs-toggle="modal" data-bs-target="#ExtralargeModal" data-toggle="tooltip" data-placement="top" title="edit job details" onClick="loadPageModalForm('job/update-form/${result.id}');">Edit</option>
							     <option data-toggle="tooltip" data-placement="top" title="archive a job" onclick="deleteJob(${result.id})">Delete</option>
							      <option data-bs-toggle="modal" data-bs-target="#ExtralargeModal" onclick="loadDynamicPageModal('job/estimate/${result.id}');">Generate Estimate</option>
							     <option data-bs-toggle="modal" data-bs-target="#ExtralargeModalFile" onclick="loadPageModal('job/generate-pdf/${result.id}');">Control Sheet</option>
				              </select>
							  </td>
							</tr>
						 </tbody>
					   </table>
					</div>
				</div>
			</div>
		</div>
	</section>
</main>



