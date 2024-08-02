<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

						 
							 <c:forEach var="result" items="${invoices}" varStatus="loop">
						    <tr class="${loop.index % 2 == 0 ? 'even-row' : 'odd-row'}">
							    <c:set var="index" value="${loop.index}" />
							    <%    int index = (Integer) pageContext.getAttribute("index");  %>
							 <td>  <%= index + 1 %></td>
							 <div id ="table-content">
							 <td>${result.estimatePricingid.jobEstimate.job.title}</td>
							  <td>${result.referenceNumber}</td>
							 <td>${result.estimatePricingid.jobEstimate.job.customer.name}</td>
							   <td><fmt:formatDate type = "both" value = "${result.creationDate}" /></td>
							    <td>${result.netPayable}</td>
							   <td>
							     <a>
								   <button data-bs-toggle="modal" data-bs-target="#ExtralargeModal" data-bs-toggle="modal" class="button-see" onclick="loadPageModalForm('invoice/job-invoice/${result.id}')">
								    <i class="ri-eye-line"></i>
								   </button>
								   <button class="button-edite" data-bs-target="#ExtralargeModal" data-bs-toggle="modal" class="button-see" onclick="loadPageModalForm('papertype/toUpdate/${paperType.id}')">
								     <i class="ri-download-2-fill"></i>
								   </button>
								 
								 </a>
							   </td>
							   </div>
						  </c:forEach>
						   <tr style="font-family: bold; ">
						  <td  style="color: #012970; ">Total Payable : </td>
						   <td> </td>
						    <td> </td>
						   	<td> </td>
						   	<td> </td>
						   <td style="color: red;"> <fmt:formatNumber value="${netPayable}" type="currency"   pattern = "#,###,###"/>XCFA</td>
						 </tr>