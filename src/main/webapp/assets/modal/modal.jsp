
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


	<!------------------Error------------------------------>
	<div class="modal fade" id="somthingwhenwrong" tabindex="-1">
	  <div class="modal-dialog modal-dialog-centered">
		<div class="alert alert-danger alert-dismissible fade show" role="alert">
		    <i class="bi bi-exclamation-octagon me-1"></i>
		    <p><fmt:message key="something.when.wrong.user.did.not.deleted"/></p>
		    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
		  </div>
		</div>
	 </div>	 
	 <!--------------Successl ------------->
	 <div class="modal fade" id="userDeleteSuccessfully" tabindex="-1">
	    <div class="modal-dialog modal-dialog-centered">	  
	      <div class="modal-content">		
	        <div class="modal-body">		  
	          <button type="button" class="btn-close" data-bs-dismiss="modal" style="position: relative; left: 50%; bottom: 12px;" onclick="loadPage('/user/list-users')" aria-label="Close"></button>
	          <img src="assets/img/success_icon.png" alt="">
	          <p><fmt:message key="group.created.successfully" /></p>
	        </div>
          </div>
        </div>
      </div>   
      <!-------------- Email already exist------------->
	  <div class="modal fade" id="emailAlreadyExist" tabindex="-1">
	    <div class="modal-dialog modal-dialog-centered">
	      <div class="modal-content">
		    <div class="modal-body">
		      <button type="button" style="position: relative; left: 50%; bottom: 12px;" class="btn-close" data-bs-dismiss="modal" aria-label="Close" onclick="loadPage('/user/list-users')"></button>
		      <img src="assets/img/error.png" alt="">
		      <p><fmt:message key="something.when.wrong.user.name.or.email.already.exist"/></p>
		    </div>
	      </div>
	    </div>
      </div>
						
 <script src="assets/js/billing/customer.js"></script>
  <script src="assets/js/users.js"></script>
  
  