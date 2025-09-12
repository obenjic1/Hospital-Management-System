<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <title>Consultation Details</title>
  <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet"></head>
</head>
<body class="bg-light">
<div class="container mt-4">
    <div class="card shadow p-4">
        <h3 class="mb-3 text-center">Consultation Details</h3>

        <p><strong>Patient:</strong> ${consultation.appointment.patient.name}</p>
        <p><strong>Doctor:</strong> ${consultation.appointment.doctor.name}</p>
        <p><strong>Date:</strong> ${consultation.appointment.appointmentDate}</p>

        <hr>
        <p><strong>Diagnosis:</strong></p>
        <p>${consultation.diagnosis}</p>

        <p><strong>Prescription:</strong></p>
        <p>${consultation.prescription}</p>

        <p><strong>Notes:</strong></p>
        <p>${consultation.notes}</p>

        <a href="${pageContext.request.contextPath}/consultations" class="btn btn-primary mt-3">Back</a>
    </div>
</div>
</body>
</html>
