
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
                        <div class="tab-content pt-2">
                            <div class="tab-pane fade show active profile-overview" id="profile-overview" style="margin-left: 10%">
                                    <div class="container estimate" style="position: relative;bottom: -20px" >
                                        <div class="row">
                                            <div class="row">
                                                <div class="col-sm-6" style="text-align:left">
                                                    <h3 style="font-family: bold;"><strong>Estimate</strong></h3>
                                                </div>
                                                <div class="col-sm-1">
                                                </div>
                                                <div class="col-sm-5 customer-info"  style="text-align:left; font-weight: bold;">
                                                    <div>${job.customer.name}</div>
                                                    <div>${job.customer.telephone}</div>
                                                    <div>${job.customer.address}</div>
                                                </div>
                                            </div>

											</div> 

			                              	<div class="row">
                                                <table class="table-responsive ta" id="cover-table">
                                                    <thead id="estimate-header">
                                                        <tr>
                                                            <th scope="col">No.</th>
                                                            <th><span style="padding:10px">Quantity</span></th>
                                                            <th scope="col">Unit price(FCFA)</th>
                                                            <th scope="col">Total Price (FCFA)</th>
                                                            <th scope="col">Actions</th>                                                            
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                             <c:set var = "i"  value = "1"/> 
                                                             <c:forEach var="estimate" items="${estimateP}" varStatus="loop"> 
                                                                <tr> 
                                                                    <td><c:out value = "${i}"/></td> 
                                                                       <td style="font-family: bold;"><a> <fmt:formatNumber value="${estimate.quantity}" type="currency"   pattern = "#,###,###"/> </a></td>                                 
                                                                     <td style="font-family: bold;"><a> <fmt:formatNumber value="${estimate.unitPrice}" type="currency"   pattern = "#,###,###"/> </a></td>                                  
                                                                      <td style="font-family: bold;"><a> <fmt:formatNumber value="${estimate.totalPrice}" type="currency"   pattern = "#,###,###"/> </a></td>  
                                                                      <td>
                                                                        <button type="button" class="btn " onclick="getInvoiceQuantity(${estimate.id})" data-toggle="tooltip" data-placement="top" title="generate invoice"><i class="bi bi-download"></i></button>
<%--                                                                       	<button class="btn btn-secondary"style="width: 95px;" data-bs-toggle="modal" data-bs-target="#ExtralargeModalFile" onclick="confirmEstimate('job/estimate/confirm/${job.id}','job/estimate-pdf/');"><fmt:message key="generate"/></button> --%>

                                                                      </td>                                
                                                                </tr> 
                                                                <c:set var = "i"  value = "${i+1}"/>
                                                           </c:forEach> 
                                                        </tr>
                                                    </tbody>
                                                </table>
                                                <div class="" style="margin-top:50px;">
                           <!--                 <button class="btn btn-primary" onclick="confirmEstimate('/job/estimate/confirm/${job.id}');"><fmt:message key="confirm"/></button> -->

                                           		 <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#ExtralargeModalFile" onclick="loadPageModal('job/estimate-pdf-commission/${jobEstimate.reference}',);"><fmt:message key="confirm"/></button>
                              <!--                <button  class="btn btn-danger" onclick="closeModalView('ExtralargeModal');"><fmt:message key="cancel"/></button> -->

                                       		 </div>
                                            </div>
                                        </div>
                                        <hr><br>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
            </section>
        </main><!-- End #main -->
  
  
  