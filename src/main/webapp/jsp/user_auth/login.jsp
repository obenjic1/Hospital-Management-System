
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>Login</title>
<meta content="" name="description">
<meta content="" name="keywords">

<!-- Favicons -->
<link href="assets/img/queenmary-logo.png" rel="icon">
<link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

<!-- Google Fonts -->
<!-- <link rel="stylesheet" -->
<!-- 	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"> -->
<!-- <link -->
<!-- 	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" -->
<!-- 	rel="stylesheet"> -->

<link href="assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="assets/vendor/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet">
<link href="assets/vendor/boxicons/css/boxicons.min.css"
	rel="stylesheet">
<link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">

<!-- Template Main CSS File -->
<link href="assets/css/style.css" rel="stylesheet">
<link href="assets/css/login.css" rel="stylesheet">
<script type="text/javascript"
	src="assets/js/install-sw.js"></script>
</head>

<body onload="registerServiceWorker();">
	<main>
		<div class="container">
						 	
			<section
				class="section register min-vh-100 d-flex flex-column align-items-center justify-content-center py-2">	
				<div class="container">

					<div class="row justify-content-center" id="formContent">
						<div class="col-lg-4 col-md-6 d-flex flex-column align-items-center justify-content-center">
							   <img src="assets/img/queenmary-logo.png" width="400px"   alt="" class="top-logo login-logo">
							<div class="card mb-3  login-card" >
								<div class="card-body">
									<div class="pt-3 pb-4">
										<div class="d-flex justify-content-center py-4">
				               
			                 <h4 class="card-title text-center pb-0 fs-2"><fmt:message key="login"/></h4>
				              </div>
									</div>
									<c:url value="/login" var="loginUrl" />
									<form class="row g-3 needs-validation" action="<c:url value='/login'/>" method="post">										
										<c:if test="${param.error != null}">
											<p class="error"><fmt:message key="invalid.username.or.password"/></p>
 										</c:if>										
										<c:if test="${param.logout != null}">
 										<div class="alert alert-danger alert-dismissible fade show" role="alert">
               							  <p><fmt:message key="you.have.been.logged.out"/></p> 
                 						  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button> 
               							</div>											 
										</c:if>										
										<div class="col-12 fs-5">
										  <label for="username " class="form-label"><fmt:message key="username" /></label>
										  <div class="input-group has-validation">
											<span class="input-group-text" id="inputGroupPrepend">@</span>
											<input type="text" name="username" class="form-control" id="username" required>
											<div class="invalid-feedback"><fmt:message key="please.enter.your.username"/> </div>
											</div>
										 </div>
										
										<div class="col-12 fs-5">
											<label for="password" class="form-label"><fmt:message key="password"/></label>
											<div class="input-group has-validation">
												<span class="input-group-text"><i class="fas fa-lock"></i></span>
												<input type="password" name="password" class="form-control" id="password" required>
												<div class="invalid-feedback"> <fmt:message key="please.enter.your.password"/> </div>
											</div>
										</div>
																				
<%-- 									<label id="reset"  data-toggle="tooltip" data-placement="right" title="click to reset your password" onclick="resetPassword()"><fmt:message key="reset.password"/></label>	 --%>
										<a id="reset" data-toggle="tooltip" data-placement="right" title="click to reset your password" onclick="loadView('/password/forgotten', 'formContent');"><br><fmt:message key="reset.password"/></a>
										
										
										<input type="hidden" name="${_csrf.parameterName}"value="${_csrf.token}" />
										  <div class="col-12">
<!-- 									    <button class="btn btn-primary w-100" type="submit">Login</button> -->
											<button class="btn btn-primary w-100" type="submit"  data-toggle="tooltip" data-placement="top"  style="position: inherit;" title="click to login">
											<fmt:message key="login"/>
										  </button>															
										</div>
									</form>
									
								</div>
							</div>
						</div>						
					</div>
				</div>
				
			</section>
		</div>
	</main>
	<!-- End #main -->
	<script src="assets/js/test.js"></script>
	<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="assets/js/users.js"></script>

</body>

</html>