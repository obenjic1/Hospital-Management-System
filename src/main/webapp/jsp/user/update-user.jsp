<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<main id="update-user">
	<div class="container">
		<section>
			<div class="card" style="width: 102%; left: -1%;">
				<div class="card-body">
				  <h5 class="card-title text-center pb-0 fs-4"><fmt:message key="update.user"/></h5>
				  <p class="text-center small"><fmt:message key="enter.the.user.informations"/></p>
					<form style="width: 55%;margin-left:30px">
                      <div class="row mb-3">
                          <label for="firstName" class="col-md-4 col-lg-3 col-form-label"><fmt:message key="first.name"/></label>
                          <div class="col-md-8 col-lg-9">
                              <input name="firstName" type="text" class="form-control" id="firstName" value="${userFinded.firstName}">
                          </div>
                      </div>
                      <div class="row mb-3">
                          <label for="lastName" class="col-md-4 col-lg-3 col-form-label"><fmt:message key="last.name"/></label>
                          <div class="col-md-8 col-lg-9">
                              <input name="lastName" type="text" class="form-control" id="lastName" value="${userFinded.lastName}">
                          </div>
                      </div>
                      <div class="row mb-3">
                          <label for="username" class="col-md-4 col-lg-3 col-form-label"><fmt:message key="username"/></label>
                          <div class="col-md-8 col-lg-9">
                              <input name="username" type="text" class="form-control" id="username" value="${userFinded.username}" readonly="readonly">
                          </div>
                      </div>
                       <div class="row mb-3">
                          <label for="mobile" class="col-md-4 col-lg-3 col-form-label"><fmt:message key="phone"/></label>
                          <div class="col-md-8 col-lg-9">
                              <input name="mobile" type="text" class="form-control" id="mobile" value="${userFinded.mobile}">
                          </div>
                      </div>
                      <div class="row mb-3">
                          <label for="email" class="col-md-4 col-lg-3 col-form-label"><fmt:message key="email"/></label>
                          <div class="col-md-8 col-lg-9">
                              <input name="email" type="email" class="form-control" id="email" value="${userFinded.email}">
                          </div>
                      </div>
                      
                       <div class="row mb-3">
                           <label for="address" class="col-md-4 col-lg-3 col-form-label"><fmt:message key="address"/></label>
                           <div class="col-md-8 col-lg-9">
                               <input name="address" type="text" class="form-control" id="address" value="${userFinded.address}">
                           </div>
                       </div>
                       <div class="row mb-3">
                           <label for="ImageFile" class="col-md-4 col-lg-3 col-form-label"><fmt:message key="photo"/></label>
                           <div class="col-md-8 col-lg-9">
                               <input name="imageFile" type="file" class="form-control" id="imageFile" accept="image/*" value="${userFinded.imagePath}">
                           </div>
                       </div>  <div class="row mb-3">
                          <label for="firstName" class="col-md-4 col-lg-3 col-form-label"><fmt:message key="first.name"/></label>
                          <div class="col-md-8 col-lg-9">
                              <input name="firstName" type="text" class="form-control" id="firstName" value="${userFinded.firstName}">
                          </div>
                      </div>
                      <div class="row mb-3">
                          <label for="lastName" class="col-md-4 col-lg-3 col-form-label"><fmt:message key="last.name"/></label>
                          <div class="col-md-8 col-lg-9">
                              <input name="lastName" type="text" class="form-control" id="lastName" value="${userFinded.lastName}">
                          </div>
                      </div>
                      <div class="row mb-3">
                          <label for="username" class="col-md-4 col-lg-3 col-form-label"><fmt:message key="username"/></label>
                          <div class="col-md-8 col-lg-9">
                              <input name="username" type="text" class="form-control" id="username" value="${userFinded.username}" readonly="readonly">
                          </div>
                      </div>
                       <div class="row mb-3">
                          <label for="mobile" class="col-md-4 col-lg-3 col-form-label"><fmt:message key="phone"/></label>
                          <div class="col-md-8 col-lg-9">
                              <input name="mobile" type="text" class="form-control" id="mobile" value="${userFinded.mobile}">
                          </div>
                      </div>
                      <div class="row mb-3">
                          <label for="email" class="col-md-4 col-lg-3 col-form-label"><fmt:message key="email"/></label>
                          <div class="col-md-8 col-lg-9">
                              <input name="email" type="email" class="form-control" id="email" value="${userFinded.email}">
                          </div>
                      </div>
                      
                       <div class="row mb-3">
                           <label for="address" class="col-md-4 col-lg-3 col-form-label"><fmt:message key="address"/></label>
                           <div class="col-md-8 col-lg-9">
                               <input name="address" type="text" class="form-control" id="address" value="${userFinded.address}">
                           </div>
                       </div>
                       <div class="row mb-3">
                           <label for="ImageFile" class="col-md-4 col-lg-3 col-form-label"><fmt:message key="photo"/></label>
                           <div class="col-md-8 col-lg-9">
                               <input name="imageFile" type="file" class="form-control" id="imageFile" accept="image/*" value="${userFinded.imagePath}">
                           </div>
                       </div>
                      <div class="text-center">
                      	<input data-bs-toggle="modal" data-bs-target="#creation" type="button" onclick="updateUserById('${userFinded.id}')" class="btn btn-primary" value="Save Changes" style="left: 96%;bottom: -18%;"/>
                      </div>
   
                  </form>
                      <div class="text-center">
                      	<input data-bs-toggle="modal" data-bs-target="#creation" type="button" onclick="updateUserById('${userFinded.id}')" class="btn btn-primary" value="Save Changes" style="left: 96%;bottom: -18%;"/>
                      </div>
   
                  </form>
				</div>
			</div>
		</section>
	</div>
</main>
<!-- End #main -->
<script src="assets/js/users.js"></script>
