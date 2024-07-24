<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<main id="update-customer">
	<div class="container">
		<section>
			<div class="card" style="width: 102%; left: -1%;">
				<div class="card-body">
				  <h5 class="card-title text-center pb-0 fs-4"><fmt:message key="update.paperType"/></h5>
				  <p class="text-center small"><fmt:message key="enter.the.paperType.informations"/></p>
					<form style="width: 55%;margin-left:30px">
					  <div class="row mb-3" style="bottom: -7px;position: relative;">
					    <label for="name" class="col-md-4 col-lg-3 col-form-label"><fmt:message key="name"/></label>
						 <div class="col-md-8 col-lg-9">
						   <input type="text" id="name" name="name" class="form-control" value="${update.name}" style="left: -102px;"/>
						 </div>
					   </div>
						<div class="col-md-6">
						  <input onclick="update('${update.id}')" type="button" class="btn btn-primary" value="Save Changes" style="position: relative;left: 277%;bottom: -34%;"/>
						</div>
					</form>
				</div>
			</div>
		</section>
	</div>
</main>
<!-- End #main -->
