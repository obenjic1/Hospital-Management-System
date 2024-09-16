<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

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
					 <div class="row" style="position: relative;bottom: 46px; left: 154px;">
					    <div id="startPeriod" class="col-sm-3" style="display: block;">
					         <label for="search_startDate" class="required">Start date</label>
					        <input type="date" id="search_startDate"  required="required" class="date_picker hasDatepicker" style="width: 255px;">
					    </div>
	    
					    <div id="endPeriod" class="col-sm-3" style="display: block;">
					        <label for="search_endDate" class="required">End date</label>
					        <input type="date" id="search_endDate"  required="required" class="date_picker hasDatepicker" style="width: 255px;">
					        
					    </div>
					    
					    <div id="endPeriod" class="col-sm-3" >
					      <button type="button" title="Search"  onclick="findBycreationDate()"> <i class="bi bi-search" ></i> </button>
					        
					 </div>
					 <div > 
						    <label style="left: 71%;position: relative; font-family: bold;color: #012970;">Total Invoices </label>
						    <span style="left: 75%;position: relative; color: red; font-family: bold;">${totalElement}</span>
					    </div>
						</div> 
						<table id="myTable1" class="table datatable">
						  <thead style="background-color: #dddfe3;">
						    <tr>
						       <th scope="col"><fmt:message key="number"/></th>
						       <th scope="col"><fmt:message key="reference.number"/></th>
						       <th scope="col"><fmt:message key="job.title"/></th>
						       <th scope="col"><fmt:message key="customer"/></th>
						        <th scope="col"><fmt:message key="created.date"/></th>
						        <th scope="col"><fmt:message key="net.payable"/></th>
							   <th scope="col"><fmt:message key="actions"/></th>
							</tr>
						  </thead>
						  <tbody id ="table-content">
						
							  <c:forEach var="result" items="${results}" varStatus="loop">
							    <tr class="${loop.index % 2 == 0 ? 'even-row' : 'odd-row'}">
								    <c:set var="index" value="${loop.index}" />
								    <%    int index = (Integer) pageContext.getAttribute("index");  %>
								 <td>  <%= index + 1 %></td>
								 <td>${result.referenceNumber}</td>
								 <td>${result.estimatePricing.jobEstimate.job.title}</td>
								 <td>${result.estimatePricing.jobEstimate.job.customer.name}</td>
								   <td><fmt:formatDate type = "both" value = "${result.creationDate}" /></td>
								    <td> <fmt:formatNumber value="${result.netPayable}" type="currency"   pattern = "#,###,###"/></td>
								   <td>
								     <a>
								   <select id="coverPaperType" name="name" class="form-select">
								     <option >Action</option>
								      <option data-bs-toggle="modal" data-bs-target="#ExtralargeModal" data-toggle="tooltip" data-placement="top" title="view job details" onclick="loadPageModalForm('invoice/job-invoice/${result.id}');">View</option>
								      <sec:authorize  access="hasRole('ROLE_APPLY_DISCOUNT')"> 
								       <option data-bs-toggle="modal" data-bs-target="#ExtralargeModal" data-toggle="tooltip" data-placement="top" title="edit job details" onClick="loadPageModalForm('invoice/invoice-discount/${result.id}');">Apply Discount</option>
 									 </sec:authorize> 
								   	 <sec:authorize  access="hasRole('ROLE_APPLY_TAX')"> 
								        <option data-bs-toggle="modal" data-bs-target="#ExtralargeModal" data-toggle="tooltip" data-placement="top" title="view job details" onclick="loadPageModalForm('invoice/job-tax-form/${result.id}');">Apply Taxes</option>
					              	 </sec:authorize> 

					               </select>								 
									 </a>
								   </td>
							  </c:forEach>
						  <tr style="font-family: bold; ">
						  <td  style="color: #012970; ">Total Payable : </td>
						   <td> </td>
						    <td> </td>
						   	<td> </td>
						   	<td> </td>
						   	<td> </td>
						   <td style="color:red;"><fmt:formatNumber value="${netPayable}" type="currency"   pattern = "#,###,###"/> XAF</td>
						 </tr>
						</tbody>
					  </table>
					 
					  </div>					  					 		
					</div>
				</div>
			</div>
		</div>
	</section>
</main>

