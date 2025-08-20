<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login</title>

<link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
<link href="assets/css/style.css" rel="stylesheet">
<link href="assets/css/newLogin.css" rel="stylesheet">



</head>
<body>

<div class="login-wrapper">
    <div class="login-card">
        <img src="assets/img/queenmary-logo.png" alt="Hospital Logo" class="login-logo">

        <h4 class="text-center mb-4"><fmt:message key="login"/></h4>

        <c:url value="/login" var="loginUrl" />
        <form action="${loginUrl}" method="post" class="needs-validation" novalidate>
            
            <c:if test="${param.error != null}">
                <div class="alert alert-danger p-2 text-center mb-3">
                    <fmt:message key="invalid.username.or.password"/>
                </div>
            </c:if>

            <div class="mb-3">
                <label class="form-label"><fmt:message key="username"/></label>
                <input type="text" name="username" class="form-control" required>
                <div class="invalid-feedback">
                    <fmt:message key="please.enter.your.username"/>
                </div>
            </div>

            <div class="mb-3">
                <label class="form-label"><fmt:message key="password"/></label>
                <input type="password" name="password" class="form-control" required>
                <div class="invalid-feedback">
                    <fmt:message key="please.enter.your.password"/>
                </div>
            </div>

            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

            <button type="submit" class="btn btn-primary w-100">
                <fmt:message key="login"/>
            </button>

            <a href="#" onclick="loadView('/password/forgotten', 'formContent');" class="reset-link">
                <fmt:message key="reset.password"/>
            </a>
        </form>
    </div>
</div>

<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
