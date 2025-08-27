<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<main id="add-user">

		<section>
			<div class="card" >
				<div class="card-body">
					<h5 class="card-title text-center pb-0 fs-4">Create a payroll</h5>
					<p class="text-center small"><fmt:message key="enter.the.personal.details.to.create.the.user.account"/></p>

	 <form id="createPayrollForm">

          <!-- Staff Info -->
          <div class="my-3">
            <label for="staffId" class="form-label">Select Staff</label>
            <select class="form-select" onChange="selectedStaff(this)" id="staffId" name="staffId" required>
              <option value="" selected>Choose a Staff</option>
					<c:forEach items="${staffs}" var="staff">
						 <option  data-salary='${staff.salary}' value="${staff.id}">${staff.firstName} ${staff.lastName}</option>
				   </c:forEach>
            
            </select>
          </div>

          <!-- Payroll Period -->
          <div class="row my-3">
            <div class="col">
              <label for="month" class="form-label">Month</label>
              <select id="month" name="month" class="form-select" required>
                <option value="">-- Select Month --</option>
                <option>January</option><option>February</option><option>March</option>
                <option>April</option><option>May</option><option>June</option>
                <option>July</option><option>August</option><option>September</option>
                <option>October</option><option>November</option><option>December</option>
              </select>
            </div>
            <div class="col">
              <label for="year" class="form-label">Year</label>
              <div>
               <select id="year" class ="col">
              <option value="2025">2025</option>
              <option value="2026">2026</option>
              <option value="2027">2027</option>
              </select>
               </div>
            </div>
          </div>

          <!-- Salary & Allowances -->
          <h6 class="text-primary">Earnings</h6>
          <div class="row my-3">
            <div class="col">
              <label class="form-label">Basic Salary</label>
              <input type="number" class="form-control" id="basicSalary" name="basicSalary" required>
            </div>
            <div class="col">
              <label class="form-label">Housing Allowance</label>
              <input type="number" class="form-control" id="housingAllowance" name="housingAllowance" value="0">
            </div>
            <div class="col">
              <label class="form-label">Transport Allowance</label>
              <input type="number" class="form-control" id="transportAllowance" name="transportAllowance" value="0">
            </div>
          </div>

          <div class="row my-3">
            <div class="col">
              <label class="form-label">Overtime Pay</label>
              <input type="number" class="form-control" id="overtimePay" name="overtimePay" value="0">
            </div>
            <div class="col">
              <label class="form-label">Other Allowances</label>
              <input type="number" class="form-control" id="otherAllowances" name="otherAllowances" value="0">
            </div>
          </div>

          <!-- Deductions -->
          <h6 class="text-danger">Deductions</h6>
          <div class="row my-3">
            <div class="col">
              <label class="form-label">Tax</label>
              <input type="number" class="form-control" id="tax" name="tax" value="0">
            </div>
            <div class="col">
              <label class="form-label">Pension/Insurance</label>
              <input type="number" class="form-control" id="pension" name="pension" value="0">
            </div>
            <div class="col">
              <label class="form-label">Other Deductions</label>
              <input type="number" class="form-control" id="otherDeductions" name="otherDeductions" value="0">
            </div>
          </div>

          <!-- Totals -->
          <div class="row my-3">
            <div class="col">
              <label class="form-label fw-bold">Gross Salary</label>
              <input type="number" class="form-control" id="grossSalary" name="grossSalary" readonly>
            </div>
            <div class="col">
              <label class="form-label fw-bold">Net Salary</label>
              <input type="number" class="form-control" id="netSalary" name="netSalary" readonly>
            </div>
          </div>

          <div class="my-3">
            <label class="form-label">Remarks</label>
            <textarea class="form-control" id="remarks" name="remarks"></textarea>
          </div>


        <div class="modal-footer d-flex justify-content-between">
         <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
          <button type="button" class="btn btn-outline-primary" onclick="calculatePayroll()">Calculate Payroll</button>
          <div>
         
          <button type="button" class="btn btn-success"  onclick ="submitPayroll()">Save Payroll</button>
          </div>
	</div>
				
		</form>
	</div>
	
			</div>
		</section>
</main>
<!-- End #main -->
<script src="assets/js/users.js"></script>

