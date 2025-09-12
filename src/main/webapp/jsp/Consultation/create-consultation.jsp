
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
    <div class="card shadow-lg rounded-3">
        <div class="card-header bg-secondary text-white">
            <h4 class="mb-4">New Consultation</h4>
        </div>
        <div class="card-body">
            <form id="consultationForm" class="card ">
            
     <div class="my-3">
                    <label for="patient" class="form-label">Select Patient</label>
                    <select id="patientIdC" name="patientId" class="form-select" >
                        <option value="">-- Select Registered Patient --</option>
                        <c:forEach var="p" items="${patients}">
                            <option value="${p.id}">${p.name}</option>
                        </c:forEach>
                    </select>
                </div>
                
           <div class="row">
                
					    <div class=" my-3 col-md-6">
					        <label  class="form-label">If patient not registered, enter name:</label>
					        <input type="text" name="unregisteredPatientName" id="unregisteredPatientName" 
					               class="form-control" placeholder="Enter patient name"/>
					    </div>
			
			    <div class="my-3  col-md-6">
			        <label class="form-label">If patient not registered, Enter Phone Number</label>
			        <input type="tel" id="phoneNumber" class="form-control"  />
			    </div> 
   		 </div>
    
     <div class="row">
				    <div class="my-3 col-md-6">
				        <label class="form-label">Type of Consultation</label>
				        <select id="type" class="form-control" required>
				            <option value="Normal">Normal Consultation</option>
				            <option value="Special">Special Consultation</option>
				        </select>
				    </div>
		    
		    <div class="my-3 col-md-6">
		        <label class="form-label">Select Doctor</label>
		        <select id="doctorId" class="form-control" required>
		         <option value="">Select Doctor</option>
		            <c:forEach var="doctor" items="${doctors}">
		                <option value="${doctor.id}">${doctor.firstName} ${doctor.lastName}</option>
		            </c:forEach>
		        </select>
		    </div>
     </div>
	
	 <div class="row">
			 <div class="my-3 col-md-6">
			        <label class="form-label">Amount Paid</label>
			        <input type="number" id="amountPaid" class="form-control" required />
			    </div>
		    <div class="my-3 col-md-6">
		        <label class="form-label">Payment Type</label>
		        <select id="paymentType" class="form-control" required>
		            <option value="">Select Payment Type</option>
		            <option value="cash">Cash</option>
		            <option value="Mobile Money">Mobile Money</option>
		            <option value="Credit Card">Credit Card</option>
		        </select>
		    </div>
		</div>
		
    <div class="my-3">
        <label class="form-label">Notes</label>
        <textarea id="notes" class="form-control" rows="2" placeholder="Enter any required information"></textarea>
    </div>

   

   

    <div class="d-flex m-3 gap-2" style="justify-content: space-between;">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
        <button type="button" style="width:30%" onclick="saveConsultation()" class="btn btn-success">Save</button>
    </div>
</form>

        </div>
    </div>
</main><script src="assets/js/hospital/consultation.js"></script>



>