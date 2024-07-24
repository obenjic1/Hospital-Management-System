<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<script src="DataTables/datatables.js"></script>
<link rel="stylesheet" href="DataTables/dataTables.dataTables.css" />
<link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="assets/css/list-role.css" rel="stylesheet">
<link href="assets/css/list-users.css" rel="stylesheet">


<main id="list-customer" class="main">
	<div class="pagetitle">
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="/">Home</a></li>
				<li class="breadcrumb-item"><fmt:message key="invoice"/></li>
				<li class="breadcrumb-item active"> <fmt:message key="list"/></li>
			</ol>
		</nav>
	</div>
	<section class="section">
		<div class="row">
			<div class="col-lg-12">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title"> <fmt:message key="invoice"/></h5>
						<!-- Table with stripped rows -->
							<div class="search-bar" style="bottom: 37px;position: relative;">
								<form class="search-form d-flex align-items-center" method="GET" action="#" style="justify-content: end;">
								  <input type="text" name="referenceNumber" placeholder="Search" title="Enter search keyword" id="search">
								  <button type="button" title="Search" onclick="searchJobByReference()"> <i class="bi bi-search" ></i> </button>
								</form>
							</div>
						<table id="myTable1" class="table datatable">
						  <thead style="background-color: #dddfe3;">
						    <tr>
						       <th scope="col"><fmt:message key="number"/></th>
						       <th scope="col"><fmt:message key="job.title"/></th>
						       <th scope="col"><fmt:message key="customer"/></th>
						       <th scope="col"><fmt:message key="reference.number"/></th>
						        <th scope="col"><fmt:message key="created.date"/></th>
							   <th scope="col"><fmt:message key="actions"/></th>
							</tr>
						  </thead>
						  <tbody>
						  <c:forEach var="result" items="${results}" varStatus="loop">
						    <tr class="${loop.index % 2 == 0 ? 'even-row' : 'odd-row'}">
							    <c:set var="index" value="${loop.index}" />
							    <%    int index = (Integer) pageContext.getAttribute("index");  %>
							 <td>  <%= index + 1 %></td>
							 <td>${result.estimatePricingid.jobEstimate.job.title}</td>
							 <td>${result.estimatePricingid.jobEstimate.job.customer.name}</td>
							   <td>${result.referenceNumber}</td>
							   <td><fmt:formatDate type = "both" value = "${result.creationDate}" /></td>
							   <td>
							     <a>
								   <button data-bs-toggle="modal" data-bs-target="#ExtralargeModal" data-bs-toggle="modal" class="button-see" onclick="loadPageModalForm('papertype/paper/${paperType.id}')">
								    <i class="ri-eye-line"></i>
								   </button>
								   <button class="button-edite" data-bs-target="#ExtralargeModal" data-bs-toggle="modal" class="button-see" onclick="loadPageModalForm('papertype/toUpdate/${paperType.id}')">
								     <i class="ri-download-2-fill"></i>
								   </button>
								 
								 </a>
							   </td>
							 </tr>						
						  </c:forEach>
						</tbody>
					  </table>
					  </div>					  					 		
					</div>
				</div>
			</div>
		</div>
	</section>
</main>

