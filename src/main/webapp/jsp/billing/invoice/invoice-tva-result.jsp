
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
    
            										
           <tr>
                <c:set var = "i"  value = "1"/> 
                   <tr> 
                       <td style="font-family: bold;"><c:out value = "${i}"/></td> 
                          <td style="font-family: bold;"><a> <fmt:formatNumber value="${invoices.estimatePricing.quantity}" type="currency"   pattern = "#,###,###"/> </a></td>                                 
                        <td style="font-family: bold;"><a> <fmt:formatNumber value="${invoices.estimatePricing.unitPrice}" type="currency"   pattern = "#,###,###"/> </a></td>                                  
                         <td style="font-family: bold;"><a> <fmt:formatNumber value="${invoices.estimatePricing.totalPrice}" type="currency"   pattern = "#,###,###"/> </a></td>  
                                                      
                   </tr> 
                     <tr> 
                    
                          <td style="font-family: bold;">VAT <span>${invoices.vatPercentage}</span> % </td>                                 
                        <td> </td>                                  
                         <td> </td>  
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
                                                   
  
  