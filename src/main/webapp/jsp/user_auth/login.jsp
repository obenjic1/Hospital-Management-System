
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	
<!DOCTYPE html>
<html lang="en">
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	  <meta content="width=device-width, initial-scale=1.0" name="viewport">
	
	  <title>Login</title>
	  <meta content="" name="description">
	  <meta content="" name="keywords">
	
	  <!-- Favicons -->
	  <link href="assets/img/presprint.jpg" rel="icon">
	  <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">
	
	  <!-- Google Fonts -->
	  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
	  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">
	 
	  <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	  <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
	  <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
	  <link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">
	
	  <!-- Template Main CSS File -->
	  <link href="assets/css/style.css" rel="stylesheet">
	  <link href="assets/css/login.css" rel="stylesheet">
	</head>
	
	<body>
	  <main>
	    <div class="container">
	      <section class="section register min-vh-100 d-flex flex-column align-items-center justify-content-center py-4">
	        <div class="container">
	          <div class="row justify-content-center">
	            <div class="col-lg-4 col-md-6 d-flex flex-column align-items-center justify-content-center">
	              <div class="card mb-3">
	                <div class="card-body">
	                  <div class="pt-4 pb-2">
	                    <h5 class="card-title text-center pb-0 fs-4">Login to Your Account</h5>
	                    <p class="text-center small">Enter your username & password to login</p>
	                  </div>
					  <c:url value="/login" var="loginUrl"/> 
					 <form class="row g-3 needs-validation" action="<c:url value='/login' />" method="post">
					    <c:if test="${param.error != null}">
					        <p class="error">Invalid username or password.</p>
					    </c:if>
					    <c:if test="${param.logout != null}">
					        <p>You have been logged out.</p>
					    </c:if>
					    <div class="col-12">
					        <label for="username" class="form-label">Username</label>
					        <div class="input-group has-validation">
					            <span class="input-group-text" id="inputGroupPrepend">@</span>
					            <input type="text" name="username" class="form-control" id="username" required>
					            <div class="invalid-feedback">Please enter your username.</div>
					        </div>
					    </div>
					    <div class="col-12">
					        <label for="password" class="form-label">Password</label>
					        <div class="input-group has-validation">
					            <span class="input-group-text"><i class="fas fa-lock"></i></span>
					            <input type="password" name="password" class="form-control" id="password" required>
					            <div class="invalid-feedback">Please enter your password!</div>
					        </div>
					    </div>
					    <div class="col-12">
					        <div class="form-check">
					            <input class="form-check-input" type="checkbox" name="remember" value="true" id="rememberMe">
					            <label class="form-check-label" for="rememberMe">Remember me</label>
					        </div>
					    </div>
					    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					    <div class="col-12">
					        <button class="btn btn-primary w-100" type="submit">Login</button>
					    </div>
					</form>
	                </div>
	              </div>
	            </div>
	          </div> 
	        </div>
	      </section>
	    </div>
	  </main><!-- End #main -->
		
  <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  	
	</body>

</html>