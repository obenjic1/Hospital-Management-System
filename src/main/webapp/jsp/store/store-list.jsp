<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Medicine Inventory Store</title>
    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/vendor/DataTables/datatables.css" rel="stylesheet">
    <style>
  :root{
      --clr-primary:#6f42c1;
      --clr-success:#00c851;
      --clr-danger:#ff4444;
      --radius:.75rem;
    }
    body{background:#f4f7fe;font-family:"Segoe UI",Arial,sans-serif;color:#2e2e2e;}
    .stat-card{
      background:#fff;
      border-radius:var(--radius);
      padding:1.5rem;
      display:flex;align-items:center;justify-content:space-between;
      box-shadow:0 .25rem .75rem rgba(0,0,0,.08);
      transition:.2s;
    }
    .stat-card:hover{transform:translateY(-2px);}
    .stat-icon{
      width:60px;height:60px;border-radius:50%;
      display:grid;place-items:center;font-size:1.5rem;color:#fff;
    }
    .bg-gradient-total{ background:linear-gradient(135deg,#667eea,#764ba2); }
    .bg-gradient-qty   { background:linear-gradient(135deg,#11998e,#38ef7d); }
    .bg-gradient-value { background:linear-gradient(135deg,#f7971e,#ffd200); }
    .bg-gradient-exp   { background:linear-gradient(135deg,#eb3349,#f45c43); }
        .stat-card { border-radius: 12px; color: black; padding: 10px; display: flex; align-items: center;
                     justify-content: space-between; box-shadow: 0 4px 10px rgba(0,0,0,0.08);}
        .medicine-list { background: #fff; padding: 10px; border-radius: 12px; box-shadow: 0 4px 10px rgba(0,0,0,0.04);}
        .btn-gradient { background: linear-gradient(45deg,#6c63ff,#42a5f5); border: none; color: white; }
        .btn-gradient:hover { opacity: 0.9; }
        .action-btns form, .action-btns button { display:inline-block; margin-right:5px; margin-top:3px; }
        table.dataTable th, table.dataTable td { vertical-align: middle; text-align: center; }
    </style>
</head>
<body>

<div class="container mt-1">
    <h3 class="fw-bold">Medicine Inventory Store</h3>
    <p class="text-muted">Manage your medicine inventory and transfers efficiently</p>

    
      <!-- =====  STATS  ===== -->
  <div class="row g-4 mb-4">
    <div class="col-sm-6 col-lg-3">
      <div class="stat-card">
        <div>
          <div class="fs-6 text-muted">Total Medicines</div>
          <div class="fs-2 fw-bold">${stats.totalMedicines}</div>
        </div>
        <div class="stat-icon bg-gradient-total"><i class="bi bi-capsule"></i></div>
      </div>
    </div>
    <div class="col-sm-6 col-lg-3">
      <div class="stat-card">
        <div>
          <div class="fs-6 text-muted">Total Quantity</div>
          <div class="fs-2 fw-bold">${stats.totalQuantity}</div>
        </div>
        <div class="stat-icon bg-gradient-qty"><i class="bi bi-layers"></i></div>
      </div>
    </div>
    <div class="col-sm-6 col-lg-3">
      <div class="stat-card">
        <div>
          <div class="fs-6 text-muted">Total Value</div>
          <div class="fs-2 fw-bold"><fmt:formatNumber value="${stats.totalValue}" type="currency" currencySymbol="CFA"/></div>
        </div>
        <div class="stat-icon bg-gradient-value"><i class="bi bi-currency-dollar"></i></div>
      </div>
    </div>
    <div class="col-sm-6 col-lg-3">
      <div class="stat-card">
        <div>
          <div class="fs-6 text-muted">Expiring Soon</div>
          <div class="fs-2 fw-bold text-danger">${stats.expiringSoon}</div>
        </div>
        <div class="stat-icon bg-gradient-exp"><i class="bi bi-exclamation-triangle"></i></div>
      </div>
    </div>
  </div>
    </div>

    <!-- Search / Category Filter / Add -->
    <div class="row mt-3 mb-2 g-2">
        <div class="col-md-8 d-flex">
            <input type="text" id="searchBoxer" class="form-control me-2" placeholder="Search medicines..." value="${q}">
            <select id="category" class="form-select" style="width:180px;">
                <option value="All" ${selectedCategory == 'All' ? 'selected' : ''}>All Categories</option>
                <c:forEach var="cat" items="${categories}">
                    <option value="${cat.name}" ${selectedCategory == cat.name ? 'selected' : ''}>${cat.name}</option>
                </c:forEach>
            </select>
            <button type="button" id="searchBtn" onclick="searchMedicine()" class="btn btn-outline-primary ms-2">Search</button>
        </div>
        <div class="col-md-4 text-end">
            <button type="button" onclick="loadMainModalForm('store/add')" data-bs-toggle="modal" data-bs-target="#MainModal"
                    class="btn btn-gradient">Add Medicine</button>
        </div>
    </div>

    <!-- Medicine List -->
    <div class="medicine-list mt-2">
        <c:choose>
            <c:when test="${empty medicines}">
                <div class="text-center py-5">
                    <div style="font-size:36px;color:#bfc9d9">&#9776;</div>
                    <p class="fw-bold mt-3">No medicines found</p>
                    <p class="text-muted">Add your first medicine to get started</p>
                </div>
            </c:when>
            <c:otherwise>
                <table class="table table-striped table-hover align-middle" id="medicineTable">
                    <thead class="table-dark">
                        <tr>
                            <th>No</th>
                            <th>Code</th>
                            <th>Name</th>
                            <th>Unit Price (CFA)</th>
                            <th>Packet Price (CFA)</th>
                            <th>Purchase Price (CFA)</th>
                            <th>Units / Packet</th>
                            <th>Total Qty</th>
                            <th>Store Qty</th>
                             <th>Pharmarcy Qty</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="m" items="${medicines}" varStatus="loop">
                            <tr>
                                <td>${loop.index + 1}</td>
                                <td>${m.code}</td>
                                <td class="fw-bold">${m.name}</td>
                                <td><fmt:formatNumber value="${m.unitPrice}" type="currency" currencySymbol="CFA"/></td>
                                <td><fmt:formatNumber value="${m.packetPrice}" type="currency" currencySymbol="CFA"/></td>
                                <td><fmt:formatNumber value="${m.purchasePrice}" type="currency" currencySymbol="CFA"/></td>
                                <td>${m.unitsPerPacket}</td>
                                <td>${m.quantity}</td>
                                <td>${m.storeQuantity}</td>
                                <td>${m.pharmacyQuantity}</td>
                                <td class="action-btns">
                                    <form onsubmit="return false;">
                                        <input type="hidden" value="${m.id}" />
                                        <input type="number" id="qtyi-${m.id}" min="1" placeholder="qty" class="form-control d-inline-block" style="width:60px;" required />
                                        <button class="btn btn-sm btn-success" onclick="Transfer(${m.id}, ${m.storeQuantity})">Transfer</button>
                                    </form>
                                    <button class="btn btn-sm btn-secondary" onclick="loadMainModalForm('store/edit/${m.id}')" data-bs-toggle="modal" data-bs-target="#MainModal">Edit</button>
                                    <form onsubmit="return false;">
                                        <input type="hidden" value="${m.id}" />
                                        <input type="number" id="qty-add-${m.id}" min="1" placeholder="qty" class="form-control d-inline-block" style="width:60px;" required />
                                        <button type="button" onclick="addQuantity('${m.id}')" class="btn btn-sm btn-outline-primary">Add</button>
                                    </form>
                                    <button class="btn btn-sm btn-outline-info" data-bs-toggle="modal" data-bs-target="#ExtralargeModal" onclick="loadPageModalForm('store/history/${m.id}')">
                                        <i class="ri-eye-line"></i>
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
    </div>
</div>



<script>



</script>
<!-- Scripts -->
<script src="DataTables/datatables.js"></script>

<script src="assets/vendor/jquery-3.5.1.min.js"></script>
<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="assets/js/hospital/medicine.js"></script>
</body>
</html>
