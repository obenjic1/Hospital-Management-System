<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
							pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>Patient Form</title>
    
  <link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">
    
    <h4>Appointments</h4>
<table class="table table-bordered">
    <thead>
        <tr>
            <th>Date</th>
            <th>Doctor</th>
            <th>Status</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="a" items="${appointments}">
            <tr>
                <td><fmt:formatDate value="${a.appointmentDate}" pattern="dd-MM-yyyy HH:mm" /></td>
                <td>${a.doctor.fullName}</td>
                <td>${a.status}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<h4>Consultations</h4>
<table class="table table-bordered">
    <thead>
        <tr>
            <th>Date</th>
            <th>Diagnosis</th>
            <th>Doctor</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="c" items="${consultations}">
            <tr>
                <td><fmt:formatDate value="${c.consultationDate}" pattern="dd-MM-yyyy HH:mm" /></td>
                <td>${c.diagnosis}</td>
                <td>${c.appointment.doctor.fullName}</td>
                <td>
                    <a class="btn btn-sm btn-info"
                       href="${pageContext.request.contextPath}/consultations/view/${c.id}">
                        View Consultation
                    </a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</body>
<script src="assets/js/hospital/staff.js"></script>

</html>
