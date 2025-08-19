<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


		<div class="col-lg-6 col-md-6 d-flex flex-column align-items-center justify-content-center">
		  <div class="card mb-3" > 
		   <div class="card-body"> 
			 <div class="pt-3 pb-4"> 
			   <div class="d-flex justify-content-center py-4"> 
				 <h4 class="card-title text-center pb-0 fs-2"><fmt:message key="reset.password"/> </h4> 
			   </div>							 
			   <div class="col-12 fs-5 py-6"> 
			   <div class="col-12 text-center"> 
			    <h4 ><fmt:message key=" please.enter.your.email.and.we.will.send.you.a.link.to.change.your.password "/></h4> 
			   </div>
		      <div class="input-group has-validation"> 
			    <label for="email">Email:</label>
			    <span class="input-group-text" id="inputGroupPrepend">@</span> 
			    <input type="email" name="email" class="form-control" id="email" /> 
			    <div class="invalid-feedback"><fmt:message key="please.enter.your.email"/> </div> 
			    <button class="btn btn-primary w-100" type="submit"  data-toggle="tooltip" data-placement="top" title="click to login"><fmt:message key="submit"/></button>	
			  </div> 		
			</div> 
		  </div> 
	    </div>
	  </div> 
    </div>
							

