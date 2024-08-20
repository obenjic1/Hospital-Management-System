
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
                                    <div class="container estimate" style="position: relative;bottom: -20px" >
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
<%--                                                                    <c:if test="${invoices.vatPercentage} >0"> --%>
                                                                  
                                                                  	<td style="font-family: bold;">VAT <span>${invoices.vatPercentage}</span> % </td>                                 
                                                                     <td> </td>                                  
                                                                      <td> </td>  
                                                                      <td><a> <fmt:formatNumber value="${vatValue}" type="currency"   pattern = "#,###,###"/> </a></td> 
<%--                                                                   </c:if> --%>
                                                                                                     
                                                                </tr> 
                                                                 <tr> 
<%--                                                               <c:if test="${invoices.irTaxPercentage}>0.01"> --%>
                                                                 	 <td style="font-family: bold;">Tax IR ${invoices.irTaxPercentage} % </td>                                 
                                                                     <td> </td>                                  
                                                                      <td> </td>  
                                                                      <td style="font-family: bold;"><a> <fmt:formatNumber value="${irTaxValue}" type="currency"   pattern = "#,###,###"/> </a></td> 
<%--                                                                 </c:if> --%>                    
                                                                </tr>
                                                                   <tr> 
                   
												                         <td style="font-family: bold;"><span>${invoices.discountPercentage} </span>%  Discount</a></td>                                 
												                       <td><a> </td>                                  
												                        <td><a> </td>  
												                        <td><a> <fmt:formatNumber value="${discount}" type="currency"   pattern = "#,###,###"/> </a></td>                               
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
                                           <button class="btn btn-primary"style="left: 87%;position: relative;width: 117px;" data-bs-toggle="modal" data-bs-target="#ExtralargeModalFile" onclick="loadPageModal('invoice/invoice-pdf/${invoices.referenceNumber}');"><fmt:message key="print"/></button>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
            </section>
        </main><!-- End #main -->
  
  
  