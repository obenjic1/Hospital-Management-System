<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- =====  HEAD  ===== -->
<head>
  <meta charset="UTF-8">
  <title>PharmaCare – Sell Drugs</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">


  <!-- =====  CUSTOM CSS  ===== -->
  <style>
    :root{
      --clr-primary:#6f42c1;
      --clr-danger:#e55353;
      --clr-success:#1cc88a;
    }
    body{background-color:#f8f9fa;}
    .card{border-radius:1rem;transition:.2s;}
    .card:hover{transform:translateY(-3px);}
    .btn-gradient{
      background:linear-gradient(135deg, var(--clr-primary), #9b59b6);
      color:#fff;
    }
    .btn-gradient:hover{color:#fff;filter:brightness(1.05);}
    .cart-box{
      background:linear-gradient(135deg, var(--clr-success), #17a673);
      border-radius:1rem;
      color:#fff;
      padding:1.5rem;
    }
    .cart-item{background:#fff;color:#333;border-radius:.75rem;padding:.75rem;margin-bottom:.75rem;}
    .badge{font-size:.75rem;}
    .low-stock{color:var(--clr-danger);}
    .table th{border-top:none;}
  </style>
</head>

<body>
<!-- =====  TOP BAR  ===== -->
<nav class="navbar navbar-light bg-white border-bottom px-3 sticky-top">
  <div class="container-fluid">
    <span class="navbar-brand mb-0 h1 d-flex align-items-center">
      <i class="bi bi-capsule text-primary me-2"></i>PharmaCare
    </span>
    <div class="d-flex align-items-center small text-muted">
      <i class="bi bi-cash-stack me-1"></i>Today:
      <span class="badge bg-primary ms-1">${totalSales} CFA</span>
    </div>
  </div>
</nav>

<!-- =====  MAIN LAYOUT  ===== -->
<div class="container-fluid p-3">
  <div class="row g-4">

    <!-- =====  LEFT: MEDICINES  ===== -->
    <div class="col-lg-9">
      <!-- filters -->
      <div class="card border-0 shadow-sm my-2">
        <div class="card-body">
          <form class="row g-2 align-items-center" onsubmit="searchPharmacyMedicine();return false;">
            <div class="col">
              <div class="input-group">
                <span class="input-group-text"><i class="bi bi-search"></i></span>
                <input type="text" id="searchBoxer" name="q" class="form-control" placeholder="Search medicines...">
              </div>
            </div>
            <div class="col-auto">
              <select id="category" name="category" class="form-select">
                <option value="All">All Categories</option>
                <c:forEach var="cat" items="${categories}">
                  <option value="${cat.name}">${cat.name}</option>
                </c:forEach>
              </select>
            </div>
            <div class="col-auto">
              <button type="submit" class="btn btn-outline-primary">Search</button>
            </div>
          </form>

          <!-- quick stats -->
          <div class="d-flex flex-wrap gap-2 mt-2 small">
            <span class="badge bg-secondary"><i class="bi bi-capsule"></i> ${stats.totalMedicines} Medicines</span>
            <c:if test="${stats.expiringSoon>0}">
              <span class="badge bg-danger"><i class="bi bi-exclamation-circle"></i> ${stats.expiringSoon} Expiring Soon</span>
            </c:if>
            <span class="badge bg-warning text-dark"><i class="bi bi-box-seam"></i> ${stats.lowStock} Low Stock</span>
          </div>
        </div>
      </div>

      <!-- medicines grid -->
      <div class="row g-3" id="medicinesGrid">
        <c:forEach var="m" items="${medicines}">
          <div class="col-sm-6 col-xl-3">
            <div class="card h-100 shadow-sm">
              <div class="card-body d-flex flex-column">
                <h6 class="fw-bold mb-1">${m.name}</h6>
                <span class="badge bg-light text-dark mb-2">${m.category.name}</span>
                <p class="small text-muted mb-2">${m.description}</p>

                <!-- stock badges -->
                <div class="mb-2">
                  <c:choose>
                    <c:when test="${m.pharmacyQuantity<10}">
                      <span class="badge bg-danger">Pharmacy: ${m.pharmacyQuantity}</span>
                    </c:when>
                    <c:otherwise>
                      <span class="badge bg-success">Pharmacy: ${m.pharmacyQuantity}</span>
                    </c:otherwise>
                  </c:choose>
                  <span class="badge bg-primary">Store: ${m.storeQuantity}</span>
                </div>

                <!-- prices -->
                <div class="d-flex justify-content-between small mb-2">
                  <div>
                    <span class="text-muted">Unit:</span>
                    <span class="text-success fw-bold">CFA ${m.unitPrice}</span>
                  </div>
                  <div>
                    <span class="text-muted">Packet:</span>
                    <span class="text-success fw-bold">CFA ${m.packetPrice}</span>
                  </div>
                </div>

                <!-- purchase type -->
                <div class="my-2">
                  <label class="form-label small">Purchase Type</label>
                  <select id="priceType-${m.id}" class="form-select form-select-sm"
                          onchange="updatePrice(${m.id},'${m.unitPrice}','${m.packetPrice}')">
                    <option value="packet">Packet</option>
                    <option value="unit">Unit</option>
                  </select>
                </div>

                <!-- actions -->
                <div class="d-flex gap-2 mt-auto">
                  <button class="btn btn-gradient btn-sm flex-fill"
                          onclick="addToCart('${m.id}','${m.name}','${m.packetPrice}','${m.unitPrice}')">
                    <i class="bi bi-cart-plus me-1"></i>Add
                  </button>
                  <button class="btn btn-outline-danger btn-sm flex-fill"
                          onclick="toogleRequestForm(${m.id})">
                    <i class="bi bi-box-arrow-in-down me-1"></i>Request
                  </button>
                </div>

                <!-- hidden request form -->
                <div id="medDiv-${m.id}" class="mt-3" style="display:none">
                  <form class="row g-2" onsubmit="return false;">
                    <input type="hidden" id="med-${m.id}" value="${m.id}">
                    <div class="col-6">
                      <input type="number" id="qty-${m.id}" min="1" class="form-control form-control-sm" placeholder="Qty" required>
                    </div>
                    <div class="col-6 d-grid">
                      <button class="btn btn-sm btn-success" onclick="TransferToPharmacy(${m.id},${m.storeQuantity})">Confirm</button>
                    </div>
                  </form>
                </div>
              </div>
            </div>
          </div>
        </c:forEach>
      </div><!-- /row -->
    </div><!-- /col-lg-9 -->

    <!-- =====  RIGHT: CART  ===== -->
    <div class="col-lg-3">
      <div class="cart-box sticky-lg-top">
        <h6 class="mb-3"><i class="bi bi-cart3 me-2"></i>Sale Cart <span class="float-end fw-bold" id="cartTotalItems">0</span></h6>

        <div class="my-2">
          <input type="text" id="customerName" class="form-control form-control-sm" placeholder="Customer name">
        </div>

        <div id="cartBody" class="mb-3" style="max-height:50vh;overflow-y:auto"></div>

        <div class="my-2">
          <label class="form-label small">Payment Method</label>
          <select id="paymentMethod" class="form-select form-select-sm">
            <option value="Cash">Cash</option>
            <option value="Mobile Money">Mobile Money</option>
          </select>
        </div>

        <div class="d-flex justify-content-between align-items-center my-2">
          <span>Total:</span>
          <h5 class="mb-0" id="cartTotal">0 CFA</h5>
        </div>
        <button class="btn btn-light btn-sm w-100" onclick="checkout()">
          <i class="bi bi-check2-circle me-1"></i>Checkout
        </button>
      </div>
    </div><!-- /col-lg-3 -->
  </div><!-- /row -->
</div><!-- /container -->

<!-- =====  SCRIPTS  ===== -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="assets/js/store/medicine.js"></script>
<script src="assets/js/billing/customer.js"></script>
<script src="assets/js/statistics/revenue.js"></script>
</body>
</html>