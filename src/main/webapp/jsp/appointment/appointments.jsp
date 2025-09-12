<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Appointments</title>



<!-- Vendor CSS Files -->
<link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">

    <style>
        .badge-status { font-weight: 600; }
    </style>
</head>
<body class="bg-light">
<div class="container py-4">
    <div class="d-flex justify-content-between align-items-center my-3">
        <h3 class="mb-0">Appointments</h3>
		  
        <div class="d-flex gap-2">
            <!-- New Appointment (modal) -->
<%--             <button class="btn btn-outline-primary"  data-bs-toggle="modal"  data-bs-target="#ExtralargeModal" onclick="loadPageModalForm('${pageContext.request.contextPath}/appointments/new')"> --%>
<!--                 <i class="bi bi-plus"></i> New Appointment -->
<!--             </button> -->
            <!-- Walk-in Consultation shortcut (optional) -->
<%--             <a class="btn btn-success"  data-bs-toggle="modal"  data-bs-target="#ExtralargeModal" onClick="loadPageModalForm('${pageContext.request.contextPath}/consultations/walkin')"> --%>
<!--                 Walk-in Consultation -->
<!--             </a> -->
        </div>
    </div>
    
     <form  class="">
		    <div class="row">
		        <div class="col-md-4">
		            <label>Start Date</label>
		            <input type="date" id="startDateA" value="${startDate}" class="form-control">
		        </div>
		        <div class="col-md-4">
		            <label>End Date</label>
		            <input type="date" id="endDateA" value="${endDate}" class="form-control">
		        </div>
		        <div class="col-md-4 align-self-end">
		            <button type="button" onclick="searchAppointment()" class="btn btn-outline-primary w-100">Filter</button>
		        </div>
		    </div>
		</form>

    <div class="card shadow-sm">
        <div class="card-body">
            <table id="appointmentsTable" class="table table-striped table-hover">
                <thead class="table-dark">
                <tr>
                    <th>#</th>
                    <th>Patient</th>
                    <th>Doctor</th>
                    <th>Date/Time</th>
                    <th>Status</th>
                    <th>Reason</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="a" items="${appointments}">
                    <tr>
                        <td>${a.id}</td>
                        <td>
                            <c:choose>
                                <c:when test="${a.patient != null}">${a.patient.name}</c:when>
                                <c:otherwise><span class="text-muted">Walk-in</span></c:otherwise>
                            </c:choose>
                        </td>
                        <td><c:out value="${a.doctor != null ? a.doctor.firstName : '-'}"/></td>
                        <td>${a.appointmentDate}</td>
                        <td>
                            <c:choose>
                                <c:when test="${a.status == 'SCHEDULED'}">
                                    <span class="badge bg-primary badge-status">Scheduled</span>
                                </c:when>
                                <c:when test="${a.status == 'COMPLETED'}">
                                    <span class="badge bg-success badge-status">Completed</span>
                                </c:when>
                                <c:when test="${a.status == 'CANCELLED'}">
                                    <span class="badge bg-danger badge-status">Cancelled</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="badge bg-secondary badge-status">${a.status}</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td><c:out value="${a.reason}" /></td>
                        <td>
                            <div class="dropdown">
                                <button class="btn btn-sm btn-outline-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown">
                                    Actions
                                </button>
                                <ul class="dropdown-menu">
                                    <li>
                                        <a class="dropdown-item"
                                           onclick="openAppointmentModal('${pageContext.request.contextPath}/appointments/edit/${a.id}')">
                                            Edit
                                        </a>
                                    </li>
                                    <li>
                                        <a class="dropdown-item"
                                        data-bs-toggle="modal"  data-bs-target="#ExtralargeModal" onclick="loadPageModalForm('consultations/new/${a.id}')" style="pointer:cusor">
                                            Start Consultation
                                        </a>
                                    </li>
                                    <li><hr class="dropdown-divider"></li>
                                    <li>
                                        <form action="${pageContext.request.contextPath}/appointments/cancel/${a.id}" method="post" class="px-3">
                                            <c:if test="${_csrf != null}">
                                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                            </c:if>
                                            <button type="submit" class="btn btn-link text-danger p-0">Cancel</button>
                                        </form>
                                    </li>
                                </ul>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>     

</body>
</html>
