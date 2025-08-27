<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
							pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<script src="DataTables/datatables.js"></script>

<link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- <link href="assets/css/list-role.css" rel="stylesheet"> -->
<link rel="stylesheet" href="DataTables/datatables.css">
    <link href="assets/css/list-users.css" rel="stylesheet">




    <title>Medicine Inventory Store</title>
<!--     <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"> -->

    <style>
        body { background-color: #f4f7fc; font-family: Arial, sans-serif; }
        .stat-card { border-radius: 12px; color: #fff; padding: 10px; display: flex; align-items: center;
                     justify-content: space-between; box-shadow: 0 4px 10px rgba(0,0,0,0.08);}
        .medicine-list { background: #fff; padding: 01px; border-radius: 12px; box-shadow: 0 4px 10px rgba(0,0,0,0.04);text-aligh:center}
        .btn-gradient { background: linear-gradient(45deg,#6c63ff,#42a5f5); border: none; color: white; }
        .head{ color: #fff}
 


        
    </style>
</head>
<body>
<div id="pagination-list" class="container mt-1">
    <h3 class="fw-bold">Medicine Inventory Store</h3>
    <p class="text-muted">Manage your medicine inventory and transfers efficiently</p>

    <div class="row g-3 mt-4">
        <div class="col-md-3">
            <div class="stat-card" style="background: linear-gradient(45deg,#3b82f6,#2563eb);">
                <div>
                    <h6 class="mb-1 head">Total Medicines</h6>
                    <h3>${stats.totalMedicines}</h3>
                </div>
                <div class="stat-icon fs-3">&#9776;</div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="stat-card" style="background: linear-gradient(45deg,#22c55e,#16a34a);">
                <div>
                    <h6 class="mb-1 head">Total Quantity</h6>
                    <h3>${stats.totalQuantity}</h3>
                </div>
                <div class="stat-icon fs-3">&#8962;</div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="stat-card" style="background: linear-gradient(45deg,#a855f7,#7c3aed);">
                <div>
                    <h6 class="mb-1 head">Total Value</h6>
                    <h3><fmt:formatNumber value="${stats.totalValue}" type="currency" currencySymbol="CFA"/></h3>
                </div>
                <div class="stat-icon fs-3">&#36;</div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="stat-card" style="background: linear-gradient(45deg,#ef4444,#dc2626);">
                <div>
                    <h6 class="mb-1 head">Expiring Soon</h6>
                    <h3>${stats.expiringSoon}</h3>
                </div>
                <div class="stat-icon fs-3">&#9888;</div>
            </div>
        </div>
    </div>

    <!-- Search / filter / add       -->
    <div class="row mt-2 g-2" >
        <div class="col-md-8">
            <form  class="d-flex" >
			<input type="text" name="q" class="form-control search-bar m-6" id="searchBoxer" placeholder="Search medicines..." />
                <select name="category" id="category" class="form-select ms-2" style="width:180px;">
                    <option value="All" ${selectedCategory == 'All' ? 'selected' : ''}>All Categories</option>
                    <c:forEach var="cat" items="${categories}">
                        <option value="${cat.name}">${cat.name}</option>
                    </c:forEach>
                    <option  onclick="loadMainModalForm('store/add-category')" data-bs-toggle="modal" data-bs-target="#MainModal" class="btn btn-gradient" style="margin-right:121px">Add New Category</option>
                </select>
                <button type="button"  onclick="event.preventDefault(); searchMedicine()" class="btn btn-outline-primary ms-2">Search</button>
            </form>
        </div>

        <div class="col-md-4 text-end">
        
      
        
        <button type="button" onclick="loadMainModalForm('store/add')" data-bs-toggle="modal" data-bs-target="#MainModal" class="btn btn-gradient" style="margin-right:121px"> <i class="bi bi-plus-circle"></i> Add Medicine</button>
        </div>
        	
    </div>

    <!-- Medicine list -->
    <div class="medicine-list mt-4" style="text-align: center;">
        <c:choose>
            <c:when test="${empty medicines}">
                <div class="text-center py-5">
                    <div style="font-size:36px;color:#bfc9d9">&#9776;</div>
                    <p class="fw-bold mt-3">No medicines found</p>
                    <p class="text-muted">Add your first medicine to get started</p>
                </div>
            </c:when>
            <c:otherwise>
                <table class="table table-striped   table-hover"  id="medicineTable">
                    <thead class="table-dark">
                        <tr>
                        	 <th>No</th>
                            <th>Name</th>
                            <th>Category</th>
                            <th>Quantity</th>
                             <th>Store Quantity</th>
                              <th>P Quantity</th>
                            <th>Price (CFA)</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="m" items="${medicines}" varStatus="loop">
		                <tr>
		                    <td>${loop.index + 1}</td>
                                <td>${m.name}</td>
                                <td><c:out value="${m.category.name}" default="-" /></td>
                                <td>${m.quantity}</td>
                                 <td>${m.storeQuantity}</td>
                                <td>${m.pharmacyQuantity}</td>
                                <td> ${m.price}</td>
                                <td >
                                    <form  style="display:inline-block;" onsubmit="return false;">
                                     <input type="hidden" name="medicineId" id="med-${m.id}"  value="${m.id}" />
                                       <input type="number" name="quantity" id="qtyi-${m.id}" min="1" placeholder="qty" style="width:60px;" class="form-control d-inline-block" required />
                                        <button class="btn btn-sm btn-success" onclick="Transfer(${m.id},${m.storeQuantity})">Transfer</button> 
                                     </form>
                                         <button class="btn btn-sm btn-secondary" data-bs-toggle="modal" data-bs-target="#MainModal" onclick="loadMainModalForm('store/edit/${m.id}')" style="width:60px; margin-left:10px">Edit</button>
                                     <form id="addQuantityForm-${m.id}" style="display:inline-block" onsubmit="return false;">
										    <input type="hidden" id="med-${m.id}" value="${m.id}" />
										    <input type="number" id="qty-${m.id}" min="1" placeholder="qty" 
										           style="width:60px;" class="form-control d-inline-block" required />
										    <button type="button" onclick="addQuantity('${m.id}')" 
										            class="btn btn-gradient" style="width:60px; margin-left:10px"> Add
										    </button>
										    <button class="button-see" data-bs-toggle="modal" data-bs-target="#ExtralargeModal" onclick="loadPageModalForm('store/history/${m.id}')">
										       <i class="ri-eye-line"></i>
											</button>
										</form>

                                   
							 </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
        
    </div>
</div>
    <script src="assets/js/hospital/medicine.js"></script>
    <script src="assets/vendor/dataTables.js"></script>
        <script src="/DataTables/datatables.js"></script>
        <!-- jQuery -->
<script src="assets/vendor/jquery-3.5.1.min.js"></script>

<!-- DataTables CSS -->

<!-- DataTables JS -->
<script src="DataTables/datatables.js"></script>
        
    
<script src="assets/js/role.js"> </script>

<!-- Include bootstrap JS -->
</body>
</html>
