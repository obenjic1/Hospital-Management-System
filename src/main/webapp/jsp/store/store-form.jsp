<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
							pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

  <link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
 <style>
       
        .btn-gradient {
            background: linear-gradient(to right, #4e73df, #7b42f6);
            color: white;
            border: none;
        }
        .btn-gradient:hover {
            opacity: 0.9;
        }
    </style>
<main id="add-user">

		<section>
			<div class="card">
				<div class="card-body">
					<h5 class="card-title text-center pb-0 fs-4">Edit Medicine</h5>
					<p class="text-center small">Edit this Medication  be Carefull cause all actions are tracked  </p>

					<form class="row g-3 needs-validation" style="margin-left:5%" onsubmit="event.preventDefault(); updateMedicine('${medicine.id}')">
					  <div class="row">
						 <label class="form-label">Medicine Name</label>
                        <input type="text" id="nameE" value ="${medicine.name}"class="form-control" required>
						</div>
						<div class="row mt-2">
						 <label class="form-label">Category</label>
	                        <select id="medCategoryE" class="form-select" required>
	                        	
	                        	  <c:forEach var="cat" items="${categories}">
							        <option value="${cat.id}" <c:if test="${cat == medicine.category}">selected</c:if>>${cat.name}</option>
                  				  </c:forEach>
	                            
	                        </select>
						</div>					
						<div class="row mt-2">
							
							<div class="mb-3 col-md-6">
                            <label class="form-label">Quantity</label>
                            <input type="number" id="quantityE"   value ="${medicine.quantity}" class="form-control" required>
                        </div>
                        <div class="mb-3 col-md-6">
                            <label class="form-label">Price (CFA)</label>
                            <input type="number"  value ="${medicine.price}"step="0.01" id="priceE" class="form-control" required>
                        </div>
							
						</div>
						 <div class="row mt-2">
                        <label class="form-label">Expiry Date</label>
                        <input type="date" id="expiryDateE" class="form-control" value ="${medicine.expirationDate}" >
                    </div>

                    <div class="row mt-2">
                        <label class="form-label">Threshold (Low Quantity)</label>
                        <input type="number" id="thresholdE"  value ="${medicine.threshold}" class="form-control">
                    </div>
						
					<div class="d-flex mt-3 gap-2" style="justify-content: space-between;">
					    <button type="button" id="add-close"class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
					    <button type="submit" class="btn btn-gradient">Update</button>
					    
					</div>
			
					</form>					
				</div>
			</div>
		</section>
</main>
<!-- End #main -->
<script src="assets/js/billing/customer.js"></script> 
<script src="assets/js/store/medicine.js"></script> 

