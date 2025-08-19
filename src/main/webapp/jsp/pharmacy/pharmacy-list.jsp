<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
							pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

  <link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">


<head>
    <meta charset="UTF-8">
    <title>PharmaCare Dashboard</title>

    <!-- Bootstrap 5.3 CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">

    <style>
        body {
            background-color: #f8f9fa;
        }
        .card {
      		  margin:0 auto;
            border-radius: 15px;
            transition: transform 0.2s ease-in-out;
        }
        .card:hover {
            transform: translateY(-3px);
        }
        .btn-gradient {
            background: linear-gradient(to right, #667eea, #764ba2);
            color: white;
        }
        .tag {
            font-size: 0.75rem;
            background: #f1f3f5;
            padding: 3px 8px;
            border-radius: 12px;
        }
        .low-stock {
            color: red;
        }
        .cart-box {
            border-radius: 15px;
            background: linear-gradient(to right, green, black);
            padding: 15px;
            color: white;
        }
        .cart-item {
            background: white;
            color: black;
            border-radius: 10px;
            padding: 10px;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>

<div class="container-fluid p-4">

    <!-- Header -->
    <div class="d-flex align-items-center justify-content-between mb-4">
        <h2 class="fw-bold">PharmaCare Dashboard</h2>
    </div>
     <div class="mb-3">
            <span class="me-3"><i class="bi bi-capsule"></i> ${stats.totalMedicines}  Medicines</span>
            <c:if test="${stats.expiringSoon>0}">
                    <span class="badge bg-danger"><i class="bi bi-exclamation-circle"></i> ${stats.expiringSoon} Expiring Soon</span>
            </c:if>
            <span class="badge bg-danger"><i class="bi bi-exclamation-circle"></i> ${stats.lowStock} Low Stock</span>
            
        </div>
   <div class="row d-flex">
   	<div class="col-lg-8"> 
   	<div class="row mb-4  d-flex">
       <form  class="d-flex" >
			<input type="text" name="q" class="form-control search-bar m-6" id="searchBoxer" placeholder="Search medicines..." />
                <select name="category" id="category" class="form-select ms-2" style="width:180px;">
                    <option value="All" ${selectedCategory == 'All' ? 'selected' : ''}>All Categories</option>
                    <c:forEach var="cat" items="${categories}">
                        <option value="${cat.name}">${cat.name}</option>
                    </c:forEach>
                    <option  onclick="loadMainModalForm('store/add-category')" data-bs-toggle="modal" data-bs-target="#MainModal" class="btn btn-gradient" style="margin-right:121px">Add New Category</option>
                </select>
                <button type="button"  onclick="event.preventDefault(); searchPharmacyMedicine()" class="btn btn-outline-primary ms-2">Search</button>
            </form>
            
              <div class="row mt-3" >
               <c:forEach var="m" items="${medicines}">
	         
	                 <div class="col-md-6 col-xl-4">
	                    <div class="card p-3">
	                        <h5 class="fw-bold">${m.name}</h5>
	                        <span class="tag mb-2">${m.category.name}</span>
	                        <p class="text-muted">${m.description}</p>
	                        <div class="mb-2">
	                        <c:choose>
	                        	<c:when test="${m.pharmacyQuantity < 10}">
	                        		    <span class="badge bg-danger">Pharmacy: ${m.pharmacyQuantity}</span>
	                        	
	                        	</c:when>
	                        	<c:otherwise>
	                        			 <span class="badge bg-success">Pharmacy: ${m.pharmacyQuantity}</span>
	                        			
	                        	</c:otherwise>
	                        
	                        
	                        </c:choose>
	                            <span class="badge bg-primary">Store:  ${m.storeQuantity}</span>
	                        </div>
	                        <h5 class="text-success">CFA ${m.price}</h5>
	                        <div style="display:flex;">
	                        <button class="btn btn-gradient w-100 add-to-cart "  style="margin:5px"  onclick="addToCart('${m.id}', '${m.name}', '${m.price}')">+ Add</button>
						                        <button class="btn btn-outline-danger w-100" id ="request-${m.id}" style="margin:5px" onclick="toogleRequestForm(${m.id})">Request</button>
	                       </div>
	                       <div id="medDiv-${m.id}"style="text-align: end;display:none">
	                       <form   id="request" onsubmit="return false;">
                                        <input type="hidden" name="medicineId" id="med-${m.id}"  value="${m.id}" />
                                        <input type="number" name="quantity" id="qty-${m.id}" min="1" placeholder="qty" min="1" style="width:80px;" class="form-control d-inline-block" required />
                                        <button class="btn btn-sm btn-outline-success" onclick="TransferToPharmacy(${m.id},${m.storeQuantity})">Confirm</button>
                           </form>
                           </div>
	                    </div>
	                </div>
	                
	                
	              </c:forEach>  
	                
	                
              </div>
        
    </div>
   		
   		</div>
		<div class="col-lg-3">
		<div class="cart-box">
                <h5><i class="bi bi-cart" id="cartTotalItems"></i> </h5>
                 <div><input type="text" name="customerName" placeholder="Customer Name" required class="form-control mb-2"/></div>
                
                <div id="cartBody">

                </div>

                <div class="mt-3">
                    <h5 id="cartTotal"></h5>
                    <button class="btn btn-light w-100"onclick="checkout()" >Checkout</button>
                </div>
            </div>
        </div>
		
		
		</div>   
   
   </div>


</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<!-- End #main -->
<script src="assets/js/billing/customer.js"></script> 
<script src="assets/js/store/medicine.js"></script> 
</body>

</html>


