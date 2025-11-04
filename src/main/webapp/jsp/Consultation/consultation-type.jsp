<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Manage Consultation Types</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <!-- =====  LOCAL BOOTSTRAP 5  ===== -->
  <link href="assets/css/bootstrap.min.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">

  <!-- =====  CUSTOM THEME  ===== -->
  <style>
    :root{
      --clr-primary:#6f42c1;
      --clr-success:#00c851;
      --clr-danger:#ff4444;
      --radius:.75rem;
    }
    body{background:#f4f7fe;font-family:"Segoe UI",Arial,sans-serif;}
    .section-title{
      font-size:1.1rem;font-weight:600;color:var(--clr-primary);
      border-bottom:2px solid var(--clr-primary);padding-bottom:.5rem;margin-bottom:1rem;
    }
    .card-add{
      background:#fff;
      border-radius:var(--radius);
      box-shadow:0 .125rem .25rem rgba(0,0,0,.08);
    }
    .card-type{
      background:#fff;
      border-left:4px solid var(--clr-primary);
      border-radius:var(--radius);
      box-shadow:0 .125rem .25rem rgba(0,0,0,.08);
    }
    .badge-price{background:var(--clr-success);color:#fff;font-size:.75rem;}
    .empty-type{border-left-color:var(--clr-danger);}
  </style>
</head>

<body class="container-fluid p-4">
<!-- =====  TOP BAR  ===== -->
<nav class="navbar navbar-light bg-white border-bottom px-3 sticky-top">
  <div class="container-fluid">
    <span class="navbar-brand mb-0 h1 d-flex align-items-center">
      <i class="bi bi-heart-pulse text-primary me-2"></i>Manage Consultation Types
    </span>
    <div class="d-flex align-items-center small text-muted">
      <i class="bi bi-clock me-1"></i><span id="digital-clock"></span>
    </div>
  </div>
</nav>

<!-- =====  FLASH  ===== -->
<c:if test="${not empty successMessage}">
  <div class="alert alert-success alert-dismissible fade show mt-3" role="alert">
    ${successMessage}
    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
  </div>
</c:if>

<!-- =====  ADD FORMS  ===== -->
<div class="row g-4 mb-4">
  <!-- Add Type -->
  <div class="col-lg-6">
    <div class="card-add p-3 h-100">
      <h5 class="section-title">Add New Consultation Type</h5>
      <form onsubmit="saveConsultationType();return false;">
        <div class="my-2">
          <label class="form-label">Type Name <span class="text-danger">*</span></label>
          <input type="text" id="name" name="name" class="form-control" placeholder="e.g. General Consultation" required>
        </div>
        <button  style="float:right" type="submit" class="btn btn-secondary">
          <i class="bi bi-plus-circle me-1"></i>Add Type
        </button>
      </form>
    </div>
  </div>

  <!-- Add Subtype -->
  <div class="col-lg-6">
    <div class="card-add p-3 h-100">
      <h5 class="section-title">Add New Subtype</h5>
      <form onsubmit="saveConsultationSubtype();return false;">
        <div class="my-2">
          <label class="form-label">Subtype Name <span class="text-danger">*</span></label>
          <input type="text" id="subName" name="name" class="form-control" placeholder="e.g. Follow-up" required>
        </div>
        <div class="my-2">
          <label class="form-label">Price (CFA) <span class="text-danger">*</span></label>
          <input type="number" id="price" name="price" class="form-control" step="0.01" min="0" required>
        </div>
        <div class="my-2">
          <label class="form-label">Parent Type <span class="text-danger">*</span></label>
          <select id="typeId" name="typeId" class="form-select" required>
            <option value="" hidden>-- Select Type --</option>
            <c:forEach var="type" items="${types}">
              <option value="${type.id}">${type.name}</option>
            </c:forEach>
          </select>
        </div>
        <button  style="float:right" type="submit" class="btn btn-success my-2">
          <i class="bi bi-check2-circle me-1"></i>Add Subtype
        </button>
      </form>
    </div>
  </div>
</div>

<!-- =====  LIST  ===== -->
<div class="table-card p-3">
  <h5 class="section-title">Existing Consultation Types & Subtypes</h5>

  <c:choose>
    <c:when test="${empty types}">
      <div class="text-center py-5">
        <div class="text-muted fs-1"><i class="bi bi-inbox"></i></div>
        <p class="fw-bold mt-3">No types created yet</p>
        <p class="text-muted">Use the form above to add the first type</p>
      </div>
    </c:when>
    <c:otherwise>
      <div class="row g-3">
        <c:forEach var="type" items="${types}">
          <div class="col-lg-6">
            <div class="card-type p-3 h-100 ${empty type.subtypes ? 'empty-type' : ''}">
              <div class="d-flex justify-content-between align-items-center mb-2">
                <h6 class="mb-0 fw-bold">${type.name}</h6>
                <span class="badge bg-primary">${type.subtypes.size()} subtypes</span>
              </div>

              <c:choose>
                <c:when test="${not empty type.subtypes}">
                  <div class="table-responsive">
                    <table class="table table-sm table-borderless align-middle mb-0">
                      <thead>
                        <tr>
                          <th>Subtype</th>
                          <th class="text-end">Price</th>
                        </tr>
                      </thead>
                      <tbody>
                        <c:forEach var="sub" items="${type.subtypes}">
                          <tr>
                            <td>${sub.name}</td>
                            <td class="text-end">
                              <span class="badge badge-price">CFA ${sub.price}</span>
                            </td>
                             <td class="text-end">
							                            <!-- ===  TYPE LEVEL BUTTONS  === -->
							<div class="mt-2 d-flex gap-1">
							  <button class="btn btn-outline-primary btn-sm"
							         data-bs-toggle="modal" data-toggle="tooltip" data-placement="top" title="Edit Consultation Details" data-bs-target="#ExtralargeModal" onclick="loadPageModalForm('admin/consultation-types/edit/${sub.id}')"  style="width:60px; margin-left:10px">
							    <i class="bi bi-pencil"></i> Edit
							  </button>
							  <button class="btn btn-outline-danger btn-sm"
							          onclick="deleteItem(${type.id})">
							    <i class="bi bi-trash"></i> Delete
							  </button>
							</div>
                          </tr>
                        </c:forEach>
                       
                        </tr>
                      </tbody>
                    </table>
                  </div>
                </c:when>
                <c:otherwise>
                  <div class="text-muted small">No subtypes added for this type.</div>
                </c:otherwise>
              </c:choose>
            </div>
          </div>
        </c:forEach>
      </div>
    </c:otherwise>
  </c:choose>
</div><!-- /table-card -->

<!-- =====  SCRIPTS  ===== -->
<script src="assets/js/hospital/consultation.js"></script>
<script>
  /* =====  LIVE CLOCK  ===== */
  function updateClock(){
    const now=new Date();
    document.getElementById('digital-clock').textContent=now.toLocaleTimeString('en-GB');
  }
  setInterval(updateClock,1000);
  updateClock();
</script>
</body>
</html>