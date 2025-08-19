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
					<h5 class="card-title text-center pb-0 fs-4">Add Category</h5>
					<p class="text-center small">Add a New Medicine By Filling This Form</p>

					<form class="row g-3 needs-validation" style="margin-left:5%" onsubmit="event.preventDefault(); addCategory()">
					  <div class="row">
						 <label class="form-label">Category Name</label>
                        <input type="text" name="name" class="" required id="categoryName">
						</div>
						

                    <div class="row mt-2">
                        <label class="form-label">Description</label>
                        <textarea  name="threshold" class="" id="categoryDesc"></textarea>
                    </div>
						
					<div class="d-flex mt-3 gap-2" style="justify-content: space-between;">
					    <button type="button" id="close-btn" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
					    <button type="submit" class="btn btn-gradient">Add Category</button>
					    
					</div>
			
					</form>					
				</div>
			</div>
		</section>
</main>
<!-- End #main -->
<script src="assets/js/store/medicine.js"></script> 
