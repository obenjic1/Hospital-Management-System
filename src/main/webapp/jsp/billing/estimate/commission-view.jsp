
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
                                            
                                             <div class="row" style="margin-bottom: -66px;bottom: 26px;position: relative;margin-top: 81px;">
				                       			 <div class="col-lg-5">
				                       			 	<div class="row" style="position: relative;">
							                          <div class ="col-lg-3 px3">
													    <div class="form-check">                     
										                    <label class="form-check-label" for="gridCheck1"><a>Commission </a></label>
										                    <input class="form-check-input" style="/*! margin-bottom: 25px; */position: relative;bottom: 21px;" type="checkbox" name="name" id="applyCommision"  onclick="showCommisionInput()">
										                  </div>
													   </div> 
						                         		<div class ="col-lg-2 px3" style="position: relative;bottom: -13px; display: none;" id="commisionDiv">
															<input id= "commision" name="title" type= "number" placeholder="XAF" style="width: 200px;height: 35px;margin-left: 20px;">
											       </div>
													
													</div>
				                       			 </div>
				                       			 <div class="col-lg-5" style="position: relative; bottom: 10px">
				                       			 	 <div class="row" style="position: relative;">
							                          <div class ="col-lg-3 px3">
													    <div class="form-check">                     
										                    <label class="form-check-label" for="gridCheck1"><a>Commission as Discount </a></label>
										                    <input class="form-check-input" style="position: relative;bottom: 32px;" type="checkbox" name="name" id="applyDiscount"  onclick="showDiscountInput()">
										                  </div>
													   </div> 
						                         		<div class ="col-lg-2 px3" style="position: relative;bottom: -13px; display: none;" id="discountDiv">
														<input id= "discount" name="title" type= "number" placeholder="XAF" style=" margin-bottom: 25px; width: 200px;height: 35px;margin-left: 20px;margin-top: 16px;">
											       </div>
													
													</div>
				                       			 </div>
				                       			 <div class="col-lg-2">
				                       			 			<div class ="col px3" style="position: relative;bottom: -13px;">
													 			 <input type= "button" class="btn btn-primary btn-sm" value ="Apply" style="margin-left: 49px;" onclick="applyCommission(${estimate.id})">
					                 					 	</div>	
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
                                                             <c:forEach var="estimate" items="${estimates}" varStatus="loop"> 
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
  
  
  