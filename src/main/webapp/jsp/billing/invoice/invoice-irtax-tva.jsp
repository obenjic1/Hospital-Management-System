
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
                                <class="card-title">
                                    <div class="container estimate"  style="position: relative;bottom: -20px" >
                                        <div class="row">
                                            <div class="row">
                                                <div class="col-sm-6" style="text-align:left">
                                                    <h3 style="font-family: bold;"><strong>Invoice</strong></h3>
                                                </div>
                                                
                                                <div class="col-sm-5 customer-info"  style="text-align:left; font-weight: bold;">
                                                    <div style="font-family: bold;">${job.customer.name}</div>
                                                    <div>${job.customer.telephone}</div>
                                                    <div>${job.customer.address}</div>
                                                </div>
                                            </div>
                                            <div class="row table-responsive">
												<div style="font-family: bold; font-weight: bold;" class="col-sm-6"><fmt:formatDate type = "both" value = "${invoices.creationDate}" /></div>
                                                <table class="ta" id="cover-table">
                                                    <tbody>
                                                        <tr>
                                                            <td>Description</td>
                                                            <td>${job.title}</td>
                                                            <td></td>

                                                        </tr>

                                                    </tbody>
                                                </table>
                                            </div>
                                            
                                            <div class="row" " style="margin-bottom: -27px;">
				                       			 <div class="col-lg-5">
				                       			 	<div class="row" style="position: relative;">
							                          <div class ="col-lg-3 px3">
													    <div class="form-check" style="display: contents;">                     
										                    <label class="form-check-label" for="gridCheck1" style="position: relative;bottom: -6px; left: 35px;"><a>Apply VAT</a></label>
										                    <input class="form-check-input" type="checkbox" name="name" id="applyTva" style="position: relative;left: 35px; bottom: -7px"  onclick="showTvaInput()">
										                  </div>
													   </div> 
						                         		<div class ="col-lg-2 px3" id="tvaDiv" style="display: none;">
															<input id= "tvaValue" name="title" type= "number" placeholder="" style="width: 200px;height: 35px;margin-left: 15px;bottom: -2px;position: relative;" value=19.25>
											       </div>
													
													</div>
				                       			 </div>
				                       			 <div class="col-lg-5" >
				                       			 	 <div class="row" >
							                          <div class ="col-lg-3 px3">
													    <div class="form-check">                     
										                    <label class="form-check-label" for="gridCheck2" style="position: relative;bottom: -10px;left: 18px;"><a style="position: relative;bottom: 24px;">IR TAX </a></label>
										                    <input class="form-check-input" type="checkbox" name="name" id="applyIrTax"  style="position: relative;bottom: 14px;left: 18px;" onclick="showIrInput()" >
										                  </div>
													   </div> 
						                         		<div class ="col-lg-2 px3" style="width: 200px, relative; display: none;" id="irDiv">
														<input id= "irValue" name="title" type= "number"  style="width: 200px; height: 35px;" value="5.5">
											       </div>
													
													</div>
				                       			 </div>
				                       			 <div class="col-lg-2"  id="apply-btn-tax">
				                       			    <div class ="col px3" style="position: relative;" >
													  <input type= "button" class="btn btn-outline-danger" value ="Display Taxes " style="margin-left: 0px;width: 101px;position: relative;bottom: -2px;left: -65px;text-align:center " onclick="displayTax(${invoices.id})"" >
													  <input type= "button" class="btn btn-outline-primary" value ="Apply Taxes" style=" margin-left: -6px;position: relative;bottom: 35px;left: 46px; width: 107px; " onclick="applyTax(${invoices.id})" ">
													  
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
                                                        </tr>
                                                    </thead>
                                                    <tbody id="load-taxs-applyed">
                                                        <tr>
                                                             <c:set var = "i"  value = "1"/> 
                                                                <tr> 
                                                                    <td style="font-family: bold;"><c:out value = "${i}"/></td> 
                                                                       <td style="font-family: bold;"><a> <fmt:formatNumber value="${invoices.estimatePricing.quantity}" type="currency"   pattern = "#,###,###"/> </a></td>                                 
                                                                     <td style="font-family: bold;"><a> <fmt:formatNumber value="${invoices.estimatePricing.unitPrice}" type="currency"   pattern = "#,###,###"/> </a></td>                                  
                                                                      <td style="font-family: bold;"><a> <fmt:formatNumber value="${invoices.estimatePricing.totalPrice}" type="currency"   pattern = "#,###,###"/> </a></td>  
                                                                                                   
                                                                </tr> 
                                                                  <tr> 
                                                                 
                                                                       <td style="font-family: bold;">VAT <span>${invoices.vatPercentage}</span> % </a></td>                                 
                                                                     <td><a> </td>                                  
                                                                      <td><a> </td>  
                                                                      <td><a> <fmt:formatNumber value="${vatValue}" type="currency"   pattern = "#,###,###"/> </a></td>                               
                                                                </tr> 
                                                                 <tr> 
                                                                 
                                                                       <td style="font-family: bold;">Tax IR ${invoices.irTaxPercentage} % </a></td>                                 
                                                                     <td> </td>                                  
                                                                      <td> </td>  
                                                                      <td style="font-family: bold;"><a> <fmt:formatNumber value="${irTaxValue}" type="currency"   pattern = "#,###,###"/> </a></td>                               
                                                                </tr>
                                                                 <tr> 
                                                                 
                                                                       <td style="font-family: bold; font-weight: bold"><a>Net Payable </a></td>                                 
                                                                     <td><a> </a> </td>                                  
                                                                      <td><a> </a></td>  
                                                                      <td style="font-family: bold; font-weight: bold;"><a> <fmt:formatNumber value="${invoices.netPayable}" type="currency"   pattern = "#,###,###"/> </a></td>                               
                                                                </tr>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                         <span style="font-family: bold;left: 88%;position: relative; bottom: -1.5%">${invoices.referenceNumber}</span> 
                                        <hr><br>
                                        <div class="" style="margin-top:50px;">
                                           <button class="btn btn-primary"style="left: 87%;position: relative;width: 117px;" data-bs-toggle="modal" data-bs-target="#ExtralargeModalFile" onclick="confirmEstimate('job/estimate/confirm/${job.id}','job/estimate-pdf/');"><fmt:message key="print"/></button>

                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
            </section>
        </main><!-- End #main -->
  
  
  