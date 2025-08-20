<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<main id="add-user">

		<section>
			<div class="card">
				<div class="card-body">
					<h5 class="card-title text-center pb-0 fs-4">Edit a  Staff Record</h5>
					<p class="text-center small">Enter the personal detail to Edit a  Staff Information </p>

					<form class="row g-3 "  id="staffForm" novalidate style="margin-left: 5%;" >
					  <div class="col-md-6">
						<label for="firstName" class="form-label"> <fmt:message key="first.name"/> </label>
						  <div class="input-group has-validation">
							<span class="input-group-text"><i class="fas fa-user"></i></span>
							<input type="text" id="firstName" name="firstName" class="form-control" value="${staff.firstName}"  required/>
							<div class="invalid-feedback"> Please enter your name.  </div>  
						  </div>
						</div>
						<div class="col-md-6">
							<label for="lastName" class="form-label"><fmt:message key="last.name"/></label>
							<div class="input-group has-validation">
							  <span class="input-group-text"><i class="fas fa-user"></i></span>
							  <input type="text" id="lastName" value="${staff.lastName}" name="lastName" class="form-control"/>
							</div>
						</div>
						<div class="col-md-6">
							<label for="Email" class="form-label"><fmt:message key="email"/></label>
							<div class="input-group has-validation">
							  <span class="input-group-text"><i class="fas fa-envelope"></i></span>
							  <input type="email" id="email" name="email" value="${staff.email}" class="form-control" />
							  <span id = "emailMsg" style="color:red"> </span> <br><br>
							</div>
						</div>
						
						<div class="col-md-6">
							<label for="mobile" class="form-label"><fmt:message key="phone"/></label>
							<div class="input-group has-validation">
							  <span class="input-group-text"><i class="fas fa-phone"></i></span>
							  <input type="text" id="mobile" value="${staff.phone}" name="mobile" class="form-control"  required="required" />
						    </div>
						</div>
						
<!-- 						<div class="col-md-6"> -->
<%-- 							<label for="username" class="form-label"><fmt:message key="username"/></label> --%>
<!-- 							<div class="input-group has-validation"> -->
<!-- 							  <span class="input-group-text"><i class="fas fa-phone"></i></span> -->
<!-- 							  <input type="text" id="username" name="username" class="form-control"  required="required" /> -->
<!-- 						    </div> -->
<!-- 						</div> -->
						<div class="col-md-6" >
						  <label for="groupe" id="" class="form-label">Gender</label>
						  <div class="input-group has-validation" >
							 <select id="gender" name="gender" class="form-select">
								 <option value="${staff.gender}" selected>${staff.gender}</option>
								 <option value="Male" >Male</option>
								 <option value="Female" >Female</option>
								</select>
							</div>
						</div>
						<div class="col-md-6">
						  <label for="address" class="form-label"><fmt:message key="address"/></label>
						  <div class="input-group has-validation">
							<span class="input-group-text"> <i class="fas fa-map-marker-alt"></i></span> 
							<input type="text" id="address" name="address" value="${staff.address}" class="form-control" required="required" />
						  </div>
						</div>
						<div class="col-md-6" >
						  <label for="groupe" id="departement" class="form-label"><fmt:message key="list.departement"/></label>
						  <div class="input-group has-validation" >
							 <select id="department" name="department" class="form-select">
							   <c:forEach items="${departments}" var="departement">
								 <option value="${departement.id}" <c:if test="${departement == staff.department}">selected</c:if>>${departement.name}</option>
							   </c:forEach>
								</select>
							</div>
						</div>
						<div class="col-md-6">
						  <label for="address" class="form-label">Speciality</label>
						  <div class="input-group has-validation">
							<span class="input-group-text"> <i class="fas fa-map-marker-alt"></i></span> 
							<input type="text" id="speciality"  value="${staff.speciality}" name="speciality" class="form-control"  />
						  </div>
						</div>
						<div class="col-md-6">
						  <label for="address" class="form-label">Salary</label>
						  <div class="input-group has-validation">
							<span class="input-group-text"> <i class="fas fa-map-marker-alt"></i></span> 
							<input type="number" id="salary"  value="${staff.salary}" name="salary" class="form-control"  />
						  </div>
						</div>
						<div class="col-md-6" >
						  <label for="groupe" id="" class="form-label">Status</label>
						  <div class="input-group has-validation" >
							 <select id="status" name="status" class="form-select">
								 <option value="true" >Active</option>
								 <option value="false" >Inactive</option>
								</select>
							</div>
						</div>
<!-- 						<div class="col-md-6"> -->
<%-- 						  <label for="Password" class="form-label"><fmt:message key="password"/></label> --%>
<!-- 						  <div class="input-group has-validation"> -->
<!-- 							<span class="input-group-text"><i class="fas fa-lock"></i></span> -->
<!-- 							<input type="password" id="password" name="password" class="form-control" required="required" /> -->
<!-- 						  </div> -->
<!-- 						</div> -->
<!-- 						<div class="col-md-6"> -->
<%-- 						  <label for="ConfirmPassword" class="form-label"><fmt:message key="confirm.password"/></label> --%>
<!-- 						  <div class="input-group has-validation"> -->
<!-- 							<span class="input-group-text"><i class="fas fa-lock"></i></span> -->
<!-- 							<input type="password" id="confirmPassword" name="confirmPassword" class="form-control" required="required" /> -->
<!-- 						  </div> -->
<!-- 						</div> -->
<!-- 						<div class="col-md-6" style="position: relative;left: -18px;"> -->
<%-- 						  <label for="imageFile" class="form-label"><fmt:message key="photo"/></label> --%>
<!-- 						  <div class="input-group has-validation" style="width: 93%; left: 7%;"> -->
<!-- 						    <input type="file" id="imageFile" name="imageFile" class="form-control" accept="image/*"> -->
<!-- 						  </div> -->
<!-- 						</div> -->
<!-- 						<div class="col-md-6" style="display: flex;left: -56px;position: relative;"> -->
<!-- 						<div class="col-md-6" style="width: 234px;"> -->
<%-- 						  <label for="groupe" id="groupeLabel" class="form-label"><fmt:message key="list.groups"/></label> --%>
<!-- 						  <div class="input-group has-validation"> -->
<!-- 							 <select id="groupe" name="groupe" class="form-select"> -->
<%-- 							   <c:forEach items="${groups}" var="group"> --%>
<%-- 								 <option value="${group.name}">${group.name}</option> --%>
<%-- 							   </c:forEach> --%>
<!-- 								</select> -->
<!-- 							</div> -->
<!-- 						</div> -->
						
<!-- 						</div> -->
						<div class="col-md-3" style="  width: 20%; left: 72%; position: relative;bottom: -10px; ">
							<input type="button" id="createBtn"  onclick="updateStaff(${staff.id})"  style=" bottom: -42%;" class="btn btn-outline-primary w-100" value="Save" >
						</div>
					</form>
				</div>
			</div>
		</section>
</main>
<!-- End #main -->
<script src="assets/js/hospital/staff.js"></script>


html>