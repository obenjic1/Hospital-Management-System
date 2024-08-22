<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

				   <label for="" class="form-label"><a> <fmt:message key="customer"/> </a></label>
				   <select id="customer"  class="form-select" >
				   <option>Choose...</option>
				    <option selected="${customerSelected.id}" value="${customerSelected.id}">${customerSelected.name}</option>
				    <option  data-bs-toggle="modal" data-bs-target="#ExtralargeModal" onclick="loadPageModalForm('customer/customerForm')"><fmt:message key="new.customer"/></option>
	                 <c:forEach items="${customers}" var="customer">
	                   <option value="${customer.id}">${customer.name}</option>
	                 </c:forEach>
	                </select>
