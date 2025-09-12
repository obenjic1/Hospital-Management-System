<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- When loaded in a modal, this is just the body content -->
<main id="add-user">

		<section>
			<div class="card">
				<div class="card-body">
    <div id="formErrors" class="alert alert-danger d-none"></div>

    <form>

        <c:if test="${_csrf != null}">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </c:if>

        <input type="hidden" name="id" value="${appointment.id}"/>

        <!-- Patient selector or inline fields                     
        
        <c:choose>
            <c:when test="${appointment.patient != null}">
                <div class="my-3">
                    <label class="form-label">Patient</label>
                    <input type="hidden" name="patientId" value="${appointment.patient.id}">
                    <input class="form-control" value="${appointment.patient.name}" readonly>
                </div>
            </c:when>
            <c:otherwise>
                <c:choose>
                    <c:when test="${isWalkIn}">
                        <div class="my-3">
                            <label class="form-label">Patient Name</label>
                            <input type="text" class="form-control" name="walkInName" required>
                        </div>
                        <div class="my-3">
                            <label class="form-label">Phone</label>
                            <input type="text" class="form-control" name="walkInPhone">
                        </div>
                        <div class="my-3">
                            <label class="form-label">Gender</label>
                            <select class="form-select" name="walkInGender">
                                <option value="M">Male</option>
                                <option value="F">Female</option>
                                <option value="O">Other</option>
                            </select>
                        </div>
                    </c:when>
                    <c:otherwise>
                    -->
                        <div class="my-3">
                            <label class="form-label">Patient</label>
                            <select class="form-select" id="patientId" required>
                                    <option  selected value="${patient.id}">
                                        ${patient.name} 
                                    </option>
                            </select>
                        </div>
                          <!--
                    </c:otherwise>
                </c:choose>
            </c:otherwise>
        </c:choose>

        <!-- Doctor -->
        <div class="my-3">
            <label class="form-label">Doctor</label>
            <select class="form-select" id="doctorId" required>
                <option value="">-- choose doctor --</option>
                <c:forEach var="d" items="${doctors}">
                    <option value="${d.id}"
                        <c:if test="${appointment.doctor != null && appointment.doctor.id == d.id}">selected</c:if>>
                        ${d.firstName}   ${d.lastName}
                    </option>
                </c:forEach>
            </select>
        </div>

        <!-- Date/Time -->
        <div class="my-3">
            <label class="form-label">Appointment Date & Time</label>
            <input type="datetime-local" class="form-control" id="appointmentDate"
                   value="${appointment.appointmentDate != null ? appointment.appointmentDate : ''}" required>
        </div>

        <!-- Reason -->
        <div class="my-3">
            <label class="form-label">Reason</label>
            <textarea class="form-control" id="reason" rows="3">${appointment.reason}</textarea>
        </div>

        <!-- Status -->
        <div class="my-3">
            <label class="form-label">Status</label>
            <select class="form-select" id="status" required>
                <option value="SCHEDULED" ${appointment.status == 'SCHEDULED' ? 'selected' : ''}>Scheduled</option>
                <option value="COMPLETED" ${appointment.status == 'COMPLETED' ? 'selected' : ''}>Completed</option>
                <option value="CANCELLED" ${appointment.status == 'CANCELLED' ? 'selected' : ''}>Cancelled</option>
            </select>
        </div>

        <div class="d-flex justify-content-between">
            <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">Cancel</button>
            <button type="submit" class="btn btn-outline-primary" onclick="saveAppointment()">Save Appointment</button>
        </div>
    </form>
</div>
</div>
</section>
</main>

<script src="assets/js/hospital/consultation.js"></script>

