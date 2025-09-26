<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<main id="add-visit" class="container py-4">
  <form action="saveVisit" method="post" id="patientForm" novalidate>

    <!-- ----------  HEADER / PROGRESS  ---------- -->
    <div class="card shadow">
      <div class="card-header bg-light">
        <div class="row text-center fw-bold small text-muted">
          <div class="col-4">
            <span class="badge bg-primary rounded-pill me-1">1</span> Patient
          </div>
          <div class="col-4">
            <span class="badge bg-primary rounded-pill me-1">2</span> Visit
          </div>
          <div class="col-4">
            <span class="badge bg-primary rounded-pill me-1">3</span> Payment
          </div>
        </div>
      </div>

      <!-- ----------  CARD BODY  ---------- -->
      <div class="card-body">

        <!-- =========================================================
             1.  PATIENT  SECTION
        ==========================================================-->
        <section class="mb-4">
          <h5 class="text-primary my-2">Patient</h5>

          <!-- Existing patient search -->
          <div class="form-floating my-2">
            <input list="patientsList" id="patientId" name="patientId"
                   class="form-control" placeholder=" "
                   onchange="toggleNewPatientFields(this.value)">
            <label for="patientId">Search existing patient</label>
            <datalist id="patientsList">
              <c:forEach var="p" items="${patients}">
                <option value="${p.name}">${p.name}</option>
              </c:forEach>
            </datalist>
            <div class="form-text">Leave blank to register a new patient.</div>
          </div>

          <!-- New patient block -->
          <div id="newPatientForm" class="row g-3">
            <div class="col-md-6 form-floating">
              <input type="text" class="form-control" id="firstName" name="firstName" placeholder=" " required>
              <label for="firstName">Full name *</label>
            </div>
            <div class="col-md-3 form-floating">
              <input type="number" class="form-control" name="age" placeholder=" " required>
              <label>Age *</label>
            </div>
            <div class="col-md-3 form-floating">
              <select class="form-select" name="gender" required>
                <option value="" hidden>Choose...</option>
                <option value="male">Male</option>
                <option value="female">Female</option>
              </select>
              <label>Gender *</label>
            </div>

            <div class="col-md-6 form-floating">
              <input type="text" class="form-control" name="contact" placeholder=" " required>
              <label>Contact phone *</label>
            </div>
            <div class="col-md-6 form-floating">
              <input type="text" class="form-control" name="residence" placeholder=" ">
              <label>Residence</label>
            </div>

            <div class="col-md-6 form-floating">
              <input type="text" class="form-control" name="occupation" placeholder=" ">
              <label>Occupation</label>
            </div>
            <div class="col-md-6 form-floating">
              <select class="form-select" name="maritalStatus">
                <option value="" hidden>Choose...</option>
                <option value="single">Single</option>
                <option value="married">Married</option>
                <option value="divorced">Divorced</option>
                <option value="widowed">Widowed</option>
              </select>
              <label>Marital status</label>
            </div>

            <div class="col-md-6 form-floating">
              <input type="text" class="form-control" name="emergencyName" placeholder=" ">
              <label>Emergency contact name</label>
            </div>
            <div class="col-md-6 form-floating">
              <input type="text" class="form-control" name="emergencyContact" placeholder=" ">
              <label>Emergency phone</label>
            </div>
          </div><!-- /newPatientForm -->
        </section>

        <hr>

        <!-- =========================================================
             2.  VISIT  SECTION
        ==========================================================-->
        <section class="mb-4">
          <h5 class="text-primary mb-3">Visit</h5>
          <div class="row g-3">
            <div class="col-md-6 form-floating">
              <select id="reasonForVisit" name="reasonForVisit" class="form-select" required
                      onchange="toggleReasonForm(this)">
                <option value="" hidden>-- Select reason --</option>
                <c:forEach var="p" items="${consultations}">
                  <option data-id="${p.id}" data-name="${p.name}" value="${p.name}">${p.name}</option>
                </c:forEach>
              </select>
              <label for="reasonForVisit">Reason for visit *</label>
            </div>
            <div class="col-md-6 form-floating">
              <input type="number" id="netAmount" name="netAmount" class="form-control" value="0" readonly>
              <label>Net amount</label>
            </div>
          </div>

          <!-- Dynamic reason blocks (unchanged IDs / classes) -->
          <div id="ConsultationForm" class="reason-form mt-3" style="display:none">
            <h6 class="text-primary">Consultation</h6>
            <div class="row g-2">
              <div class="col-md-6">
                <select class="form-select" name="visitServices[0].serviceTypeId"
                        onchange="updatePrice(this, 'consultationPrice')">
                  <option value="1" data-price="5000">General Consultation – 5 000</option>
                  <option value="2" data-price="8000">Specialist Consultation – 8 000</option>
                </select>
              </div>
              <div class="col-md-6">
                <input type="number" id="consultationPrice" name="visitServices[0].price"
                       class="form-control" value="0" readonly>
              </div>
            </div>
          </div>

          <div id="Consultation-PrénataleForm" class="reason-form mt-3" style="display:none">
          <h6 class="text-primary">Consultation Prenantal </h6>
			    <div class="row g-2">
			    <div class="col-md-6">
			    <select class="form-select" name="visitServices[0].serviceTypeId" onchange="updatePrice(this, 'consultation-PrénatalePrice')">
			      <option value="1" data-price="5000">General Consultation - 5000</option>
			      <option value="2" data-price="8000">Specialist Consultation - 8000</option>
			    </select>
			    
			    </div>
			     <div class="col-md-6">
			      <input type="number" id="consultation-PrénatalePrice" name="visitServices[0].price" class="form-control mt-2" readonly value="0">
			     
			     </div>
			    </div>
          </div>

          <div id="ExamenForm" class="reason-form mt-3" style="display:none">
            <h6 class="text-primary">Examen</h6>
            <button type="button" class="btn btn-sm btn-primary mb-2" onclick="addExamRow(currentReasonId)">Add exam</button>
            <div id="examenList"></div>
          </div>

          <div id="EchographieForm" class="reason-form mt-3" style="display:none">
            <h6 class="text-primary">Echographie</h6>
            <div class="row g-2">
              <div class="col-md-6">
                <select class="form-select" name="visitServices[0].serviceTypeId"
                        onchange="updatePrice(this, 'echoPrice')">
                  <option value="3" data-price="10000">Pelvic – 10 000</option>
                  <option value="4" data-price="12000">Abdominal – 12 000</option>
                </select>
              </div>
              <div class="col-md-6">
                <input type="number" id="echoPrice" name="visitServices[0].price"
                       class="form-control" value="0" readonly>
              </div>
            </div>
          </div>

          <div id="OrdonnanceForm" class="reason-form mt-3" style="display:none">
            <h6 class="text-primary">Pharmacy (sale)</h6>
            <button type="button" class="btn btn-sm btn-primary mb-2" onclick="addMedicineRow()">Add medicine</button>
            <div id="pharmacyList"></div>
          </div>

        <div id="VaccinationForm" class="reason-form" style="display:none;">
			     <h6 class="text-primary">Vaccination</h6>
			    <p>Vaccination form goes here</p>
			  </div>

          <div id="AutreForm" class="reason-form mt-3" style="display:none">
            <h6 class="text-primary">Other</h6>
			     <div class=" row g-2">
					    <div class="col-md-6">  <select class="form-select" name="visitServices[0].serviceTypeId" onchange="updatePrice(this, 'autrePrice')">
					      <option value="3" data-price="10000">Pelvic Echography - 10000</option>
					      <option value="4" data-price="12000">Abdominal Echography - 12000</option>
					    </select>
					    </div>
					     <div class="col-md-6">
					      <input type="number" id="autrePrice" name="visitServices[0].price" class="form-control mt-2" readonly value="0">
					     </div>
					   
			  </div>
			  </div>
        </section>

        <hr>

        <!-- =========================================================
             3.  PAYMENT  SECTION
        ==========================================================-->
        <section class="mb-4">
          <h5 class="text-primary mb-3">Payment & Doctor</h5>
          <div class="row g-3">
            <div class="col-md-4 form-floating">
              <input type="number" id="grandTotal" name="totalAmount" class="form-control" value="0" readonly>
              <label>Total (CDF)</label>
            </div>
            <div class="col-md-4 form-floating">
              <input type="number" id="discount" name="discount" class="form-control"
                     value="0" min="0" max="100" step="0.5">
              <label>Discount (%)</label>
            </div>
            <div class="col-md-4 form-floating">
              <input list="doctors" id="doctorId" name="doctorId" class="form-control" required>
              <label for="doctorId">Doctor *</label>
              <datalist id="doctors">
                <c:forEach var="doctor" items="${doctors}">
                  <option value="${doctor.id}">${doctor.name}</option>
                </c:forEach>
              </datalist>
            </div>
          </div>
        </section>

        <!-- =========================================================
             OPTIONAL APPOINTMENT
        ==========================================================-->
        <section class="mb-4">
          <div class="form-check">
            <input class="form-check-input" type="checkbox" id="appointmentCheck"
                   onclick="toggleAppointmentForm(this)">
            <label class="form-check-label" for="appointmentCheck">
              Also create an appointment
            </label>
          </div>
          <div id="appointmentForm" class="row g-3 mt-2" style="display:none">
            <div class="col-md-4 form-floating">
              <input type="text" class="form-control" name="reason" id="reason" placeholder=" " required>
              <label for="reason">Reason *</label>
            </div>
            <div class="col-md-4 form-floating">
              <input type="date" class="form-control" name="appointmentDate" id="appointmentDate" required>
              <label for="appointmentDate">Date *</label>
            </div>
            <div class="col-md-4 form-floating">
              <input type="time" class="form-control" name="appointmentTime" id="appointmentTime" required>
              <label for="appointmentTime">Time *</label>
            </div>
          </div>
        </section>

        <!-- ----------  SUBMIT  ---------- -->
        <div class="d-grid d-md-flex justify-content-md-end">
          <button type="submit" class="btn btn-primary btn-lg px-5">Save visit</button>
        </div>

      </div><!-- /card-body -->
    </div><!-- /card -->
  </form>
</main>

<script src="assets/js/hospital/visit.js"></script>