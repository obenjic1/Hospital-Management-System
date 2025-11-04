<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<main id="add-user" class="container-fluid py-4">
  <div class="row justify-content-center">
    <div class="col-12 ">

      <!-- =====  CARD  ===== -->
      <div class="card shadow-sm border-0">
        <div class="card-header bg-transparent text-center">
          <h5 class="mb-0 text-primary">Update a Registered Patient</h5>
          <p class="text-muted small mb-0">Enter the personal details to Update   <h3> ${patient.name}</h3> </p>
        </div>

        <div class="card-body">
          <form id="patientForm" class="row g-3 needs-validation" novalidate>

            <!-- Row 1 -->
            <div class="col-md-6">
              <label for="name" class="form-label fw-semibold">Full Name <span class="text-danger">*</span></label>
              <div class="input-group">
                <span class="input-group-text"><i class="bi bi-person"></i></span>
                <input type="text" id="nameE" name="firstName" class="form-control" value="${patient.name}" placeholder="Patient's full name" required>
              </div>
            </div>

            <div class="col-md-6">
              <label for="contact" class="form-label fw-semibold">Contact <span class="text-danger">*</span></label>
              <div class="input-group">
                <span class="input-group-text"><i class="bi bi-telephone"></i></span>
                <input type="tel" id="contactE" name="contact" class="form-control" value="${patient.contact}" placeholder="Phone number" required>
              </div>
              <span id="emailMsg" class="small text-danger"></span>
            </div>

            <!-- Row 2 -->
            <div class="col-md-6">
              <label for="age" class="form-label fw-semibold">Age</label>
              <div class="input-group">
                <span class="input-group-text"><i class="bi bi-calendar-event"></i></span>
                <input type="number" id="ageE" name="age" class="form-control" placeholder="Age" value="${patient.age}" min="0" max="120">
              </div>
            </div>

            <div class="col-md-6">
              <label for="occupation" class="form-label fw-semibold">Occupation</label>
              <div class="input-group">
                <span class="input-group-text"><i class="bi bi-briefcase"></i></span>
                <input type="text" id="occupationE" name="occupation" class="form-control" value="${patient.occupation}" placeholder="Occupation">
              </div>
            </div>

            <!-- Row 3 -->
            <div class="col-md-6">
              <label for="maritalStatus" class="form-label fw-semibold">Marital Status</label>
              <div class="input-group">
                <span class="input-group-text"><i class="bi bi-heart"></i></span>
                <select id="maritalStatusE" name="maritalStatus" class="form-select">
                  <option value="${patient.maritalStatus }" >${patient.maritalStatus}</option>
                  <option value="single">Single</option>
                  <option value="married">Married</option>
                  <option value="divorced">Divorced</option>
                  <option value="widowed">Widowed</option>
                </select>
              </div>
            </div>

            <div class="col-md-6">
              <label for="residence" class="form-label fw-semibold">Residence</label>
              <div class="input-group">
                <span class="input-group-text"><i class="bi bi-geo-alt"></i></span>
                <input type="text" id="residenceE" name="residence" class="form-control"  value="${patient.residence}" placeholder="Residence">
              </div>
            </div>

            <!-- Row 4 -->
            <div class="col-md-6">
              <label for="gender" class="form-label fw-semibold">Gender</label>
              <div class="input-group">
                <span class="input-group-text"><i class="bi bi-gender-ambiguous"></i></span>
                <select id="genderE" name="gender" class="form-select" required>
                  <option value="${patient.gender }" >${patient.gender}</option>
                  <option value="male">Male</option>
                  <option value="female">Female</option>
                </select>
              </div>
            </div>

            <!-- Row 5 – Emergency -->
            <div class="col-md-6">
              <label for="emmergenceName" class="form-label fw-semibold">Emergency Contact Name <span class="text-danger">*</span></label>
              <div class="input-group">
                <span class="input-group-text"><i class="bi bi-person-plus"></i></span>
                <input type="text" id="emmergenceNameE" name="emergencyName" class="form-control"  value="${patient.emmergenceName}" placeholder="Emergency contact name" required>
              </div>
            </div>

            <div class="col-md-6">
              <label for="emmergencyContact" class="form-label fw-semibold">Emergency Contact Phone <span class="text-danger">*</span></label>
              <div class="input-group">
                <span class="input-group-text"><i class="bi bi-telephone"></i></span>
                <input type="tel" id="emmergencyContactE" name="emergencyContact"  value="${patient.emmergencyContact}" class="form-control" placeholder="Emergency phone" required>
              </div>
            </div>

            <!-- Submit -->
            <div class="col-12 text-end">
              <button type="button" id="createBtn" onclick="updatePatient(${patient.id})" class="btn btn-outline-primary px-4">
                <i class="bi bi-check-circle me-2"></i>Update
              </button>
            </div>
          </form>
        </div><!-- /card-body -->
      </div><!-- /card -->
    </div><!-- /col -->
  </div><!-- /row -->
</main>

<script src="assets/js/hospital/medicine.js"></script>