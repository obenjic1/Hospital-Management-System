<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Patient Details</title>

    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">

    <style>
        body {
            background-color: #f1f3f6;
            font-family: 'Segoe UI', sans-serif;
        }

        .profile-container {
            background: #fff;
            border-radius: 8px;
            padding: 40px;
            box-shadow: 0 0 12px rgba(0, 0, 0, 0.05);
            margin-top: 40px;
        }

        .section-title {
            font-size: 1.3rem;
            font-weight: 600;
            margin-bottom: 20px;
            color: #1f2d3d;
            border-bottom: 2px solid #dee2e6;
            padding-bottom: 5px;
        }

        .info-group {
            margin-bottom: 15px;
        }

        .info-label {
            font-weight: 600;
            color: #5f6f81;
            display: block;
        }

        .info-value {
            font-size: 1rem;
            color: #212529;
        }

        .table th {
            background-color: #f8f9fa;
            color: #333;
        }

        @media (max-width: 768px) {
            .profile-container {
                padding: 20px;
            }
        }
    </style>
</head>

<body>

<main class="container">
    <div class="profile-container">

        <!-- Patient Header -->
        <h2 class="text-center mb-4">Patient Details</h2>

        <!-- Personal Info -->
        <div class="section-title">Personal Information</div>
        <div class="row">
            <div class="col-md-6 info-group">
                <span class="info-label">Full Name:</span>
                <span class="info-value">${patient.name}</span>
            </div>
            <div class="col-md-6 info-group">
                <span class="info-label">Age:</span>
                <span class="info-value">${patient.age}</span>
            </div>
            <div class="col-md-6 info-group">
                <span class="info-label">Gender:</span>
                <span class="info-value">${patient.gender}</span>
            </div>
            <div class="col-md-6 info-group">
                <span class="info-label">Contact:</span>
                <span class="info-value">${patient.contact}</span>
            </div>
            <div class="col-md-6 info-group">
                <span class="info-label">Occupation:</span>
                <span class="info-value">${patient.occupation}</span>
            </div>
            <div class="col-md-6 info-group">
                <span class="info-label">Marital Status:</span>
                <span class="info-value">${patient.maritalStatus}</span>
            </div>
            <div class="col-md-6 info-group">
                <span class="info-label">Residence:</span>
                <span class="info-value">${patient.residence}</span>
            </div>
        </div>

        <!-- Emergency Contact -->
        <div class="section-title mt-4">Emergency Contact</div>
        <div class="row">
            <div class="col-md-6 info-group">
                <span class="info-label">Contact Name:</span>
                <span class="info-value">${patient.emmergenceName}</span>
            </div>
            <div class="col-md-6 info-group">
                <span class="info-label">Contact Number:</span>
                <span class="info-value">${patient.emmergencyContact}</span>
            </div>
        </div>

        <!-- Previous Appointments -->
        <div class="section-title mt-4">Previous Appointments</div>
        <div class="table-responsive">
            <table class="table table-bordered align-middle">
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
                            <td><fmt:formatDate value="${a.appointmentDate}" pattern="dd-MM-yyyy" /></td>
                            <td>${a.doctor.firstName} ${a.doctor.lastName}</td>
                            <td>${a.status}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <!-- Optional: Export Button -->
        <!--
        <div class="text-end mt-3">
            <a href="${pageContext.request.contextPath}/patients/${patient.id}/history/pdf"
               class="btn btn-outline-primary" target="_blank">
                <i class="bi bi-file-earmark-pdf"></i> Download PDF
            </a>
        </div>
        -->

    </div>
</main>

<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
