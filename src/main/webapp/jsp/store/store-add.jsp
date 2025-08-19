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
					<h5 class="card-title text-center pb-0 fs-4">Add Medicine</h5>
					<p class="text-center small">Add a New Medicine By Filling This Form</p>

					<form class="row g-3 needs-validation" style="margin-left:5%" onsubmit="event.preventDefault(); saveMedicine()">
					  <div class="row">
						 <label class="form-label">Medicine Name :</label>
                        <input type="text" id="medicineName" class="form-control" required>
						</div>
						<div class="row mt-2">
						 <label class="form-label">Description :</label>
						 <textarea id="description" class="form-control" placeholder="Enter a description ......"></textarea>
<!--                         <input type="text" id="name" class="form-control" > -->
						</div>
						<div class="row mt-2">
						 <label class="form-label">Category :</label>
	                        <select id="medCategory" class="form-select" required>
	                        	 <option value="">Choose a category ...</option>
	                        	  <c:forEach var="cat" items="${categories}">
                      				  <option value="${cat.id}">${cat.name}</option>
                  				  </c:forEach>
	                            
	                        </select>
							
						</div>					
						<div class="row mt-2">
							
							<div class="mb-3 col-md-6">
                            <label class="form-label">Quantity :</label>
                            <input type="number" id="quantity" class="form-control" required>
                        </div>
                        <div class="mb-3 col-md-6">
                            <label class="form-label">Price (CfA)</label>
                            <input type="number" step="0.01" id="price" class="form-control" required>
                        </div>
							
						</div>
						 <div class="row mt-2">
                        <label class="form-label">Expiring Date</label>
                        <input type="date" id="expiryDate" class="form-control" >
                    </div>

                    <div class="row mt-2">
                        <label class="form-label">Threshold (Low Quantity) :</label>
                        <input type="number" id="threshold" class="form-control">
                    </div>
						
					<div class="d-flex mt-3 gap-2" style="justify-content: space-between;">
					    <button type="button" id="add-close"class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
					    <button type="submit" class="btn btn-gradient">Add Medicine</button>
					    
					</div>
			
					</form>					
				</div>
			</div>
		</section>
</main>
<!-- End #main -->
<script src="assets/js/billing/customer.js"></script> 
<script src="assets/js/store/medicine.js"></script> 

