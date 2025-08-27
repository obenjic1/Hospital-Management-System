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
				  <h5 class="card-title text-center pb-0 fs-4 update-user-title" style="color: #012970;"><fmt:message key="update.user"/> : ${userFinded.username}</h5>
				  <p style="font-family: bold;" class="text-center small"><fmt:message key="enter.the.user.informations"/></p>
					<form style="width: 55%;margin-left:30px">
                      <div class="row mb-3">
                          <label for="firstName" class="col-md-4 col-lg-3 col-form-label"><fmt:message key="first.name"/></label>
                          <div class="col-md-8 col-lg-9">
                              <input style="font-family: bold;" name="firstName" type="text" class="form-control" id="firstName" value="${userFinded.staff.firstName}">
                          </div>
                      </div>
                      <div class="row mb-3">
                          <label style="font-family: bold;" for="lastName" class="col-md-4 col-lg-3 col-form-label"><fmt:message key="last.name"/></label>
                          <div class="col-md-8 col-lg-9">
                              <input name="lastName" type="text" class="form-control" id="lastName" value="${userFinded.staff.lastName}">
                          </div>
                      </div>
                       	 <div class="row mb-3">
                          <label style="font-family: bold;" for="email" class="col-md-4 col-lg-3 col-form-label"><fmt:message key="email"/></label>
                          <div class="col-md-8 col-lg-9">
                          <input name="email" type="email" class="form-control" id="email" value="${userFinded.staff.email}">
                      	</div>
                      </div>
                       <div class="row mb-3">
                          <label style="font-family: bold;" for="mobile" class="col-md-4 col-lg-3 col-form-label"><fmt:message key="phone"/></label>
                          <div class="col-md-8 col-lg-9">
                              <input name="mobile" type="text" class="form-control" id="mobile" value="${userFinded.staff.phone}">
                          </div>
                      </div>

                      
                       <div class="row mb-3">
                           <label style="font-family: bold;" for="address" class="col-md-4 col-lg-3 col-form-label"><fmt:message key="address"/></label>
                           <div class="col-md-8 col-lg-9">
                               <input name="address" type="text" class="form-control" id="address" value="${userFinded.staff.address}">
                           </div>
                       </div>
                       <div class="row mb-3">
						  <label style="font-family: bold;" for="address" class="col-md-4 col-lg-3 col-form-label"><fmt:message key="list.departement"/></label>
						  <div class="col-md-8 col-lg-9">
							 <select id="department" name="department" class="form-select" style="position: relative;">
							   <c:forEach items="${departements}" var="departement">
							   <c:if test="${departement.id==userFinded.staff.department.id}">
								 <option selected value="${departement.id}">${departement.name}</option>
								 </c:if>
								 <c:if test="${departement.id!=userFinded.staff.department.id}">
								  <option value="${departement.id}">${departement.name}</option>
								 </c:if>
							   </c:forEach>
								</select>
							</div>
						</div>
                       <div class="row mb-3">
                           <label style="font-family: bold;" for="ImageFile" class="col-md-4 col-lg-3 col-form-label"><fmt:message key="photo"/></label>
                           <div class="col-md-8 col-lg-9">
                               <input name="imageFile" type="file" class="form-control" id="imageFile" accept="image/*">
                           </div>
                       </div> 
                      <div class="text-center">
                      	<input type="button" onclick="updateUserById('${userFinded.staff.id}'); loadPage('user/list-users')" class="btn btn-primary"  data-bs-dismiss="modal" value="Save Changes" style="left: 96%;bottom: -18%;"/>
                      </div>
   
                  </form>
				</div>
			</div>
		</section>
	</div>
</main>
<!-- End #main -->
<script src="assets/js/users.js"></script>
