 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@page import="java.time.LocalDate"%>

 <div class="row">
 <table  class="table-responsive ta" id="cover-table3">
    <thead id="estimate-header">
         <tr>
            <th scope="col"> <fmt:message key="number"/> .</th>
            <th><span style="padding:10px"> <fmt:message key="quantity"/></span></th>
            <th scope="col"> <fmt:message key="unit.price"/> (FCFA)</th>
            <th scope="col"> <fmt:message key="total.price"/> (FCFA)</th>
<%--                                                              <sec:authorize  access="hasRole('ROLE_GENERATE_INVOICE')"> --%>
            <th scope="col"><fmt:message key="actions"/></th>    
<%--                                                              </sec:authorize>                                                             --%>
        </tr>
    </thead>
	 <tbody >
                                                      <tr> 
		  <c:set var = "i"  value = "1"/> 
		  <c:forEach var="estimate" items="${estimates}" varStatus="loop"> 
		     <tr> 
		         <td><c:out value = "${i}"/></td> 
		            <td style="font-family: bold;"><a> <fmt:formatNumber value="${estimate.quantity}" type="currency"   pattern = "#,###,###"/> </a></td>                                 
		          <td style="font-family: bold;"><a> <fmt:formatNumber value="${estimate.unitPrice}" type="currency"   pattern = "#,###,###"/> </a></td>                                  
		           <td style="font-family: bold;"><a> <fmt:formatNumber value="${estimate.totalPrice}" type="currency"   pattern = "#,###,###"/> </a></td>  
		          
		<%--   <sec:authorize  access="hasRole('ROLE_GENERATE_INVOICE')"> --%>
		           <td>
		            <c:if test="${estimate.jobEstimate.discountValue!=0 || estimate.jobEstimate.commission!=0}">
		           <c:if test="${estimate.invoiced}"> 
		            <button type="button" class="btn " onclick="loadMainModalForm('invoice/job-invoice/from-pricing/${estimate.id}')" data-toggle="tooltip" data-placement="top" title="View Invoices">
		              <i class="ri-eye-line" style="color: #0d6efd"></i>
		             </button>
		           </c:if>
		           
		             <c:if test="${!estimate.invoiced}"> 
		               <button type="button" class="btn " onclick="getInfo('${jobEstimate.id}','${estimate.quantity}')" data-toggle="tooltip" data-placement="top" title="Generate Invoice">
		                 <i class="ir ri-draft-line" style="color: green"></i>
		               </button>
		             </c:if>
		             </c:if>
		    </td>
		<%--                                                     		  </sec:authorize>                                 --%>
		          </tr> 
		          <c:set var = "i"  value = "${i+1}"/>
		     </c:forEach> 
		  </tr><%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  --%>
<!-- <link href="assets/css/profile.css" rel="stylesheet"> -->
<!-- <link href="assets/css/billing/job.css" rel="stylesheet"> -->
<!--  <div class="row">  -->
    	</tbody>
    </table>
		<div class="" style="margin-top:50px;">	
		<button class="btn btn-primary" style="left: 87%;position: relative;width: 117px;" data-bs-toggle="modal" data-bs-target="#ExtralargeModalFile" onclick="loadPageModal('job/estimate-pdf-commission/${jobEstimate.reference}')"><fmt:message key="print"/> </button>
	</div>
	</div> 
 


