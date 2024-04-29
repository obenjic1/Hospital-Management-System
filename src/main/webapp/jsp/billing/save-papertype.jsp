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
					<h5 class="card-title text-center pb-0 fs-4"><fmt:message key="save.paper.type"/></h5>
					<p class="text-center small"><fmt:message key="enter.paper.details"/></p>

					<form class="row g-3 needs-validation" style=" margin-left: 5%;" onsubmit="event.preventDefault(); save()">
					  <div class="col-md-6">
						<label for="Name" class="form-label"></label>
						  <div class="input-group has-validation">
							<input type="text" id="name" name="Name" class="form-control" placeholder="Name" style=" left: 144px;">
						  </div>
						  <div id="emptyNameAlert" class="alert alert-danger alert-dismissible fade show" role="alert" style="display: none;"></div>
						</div>
						<div class="col-md-3" style="  width: 13%; left: 21%; position: relative; ">
							<input type="button" id="create-btn" onclick="save()" style="left: 165px;bottom: -37%;position: relative;" class="btn btn-primary w-100" value="Save" />
						</div>					
					</form>
				</div>
			</div>
		</section>
</main>
<!-- End #main -->
<script src="assets/js/billing/papertype.js"></script>
