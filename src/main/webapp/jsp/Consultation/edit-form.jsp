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

<!-- =====  ADD FORMS  ===== -->
<div class="row g-4 mb-4">
  <!-- Add Subtype -->
  <div class="col">
    <div class="card-add p-3 h-100">
      <h5 class="section-title">Edit ${subType.name}  123</h5>
      <form>
        <div class="my-2">
          <label class="form-label">Subtype Name <span class="text-danger">*</span></label>
          <input type="text" id="subNameE" name="name" class="form-control" value="${subType.name}"  required>
        </div>
        <div class="my-2">
          <label class="form-label">Price (CFA) <span class="text-danger">*</span></label>
          <input type="number" id="priceE" name="price" class="form-control"   value="${subType.price}" step="0.01" min="0" required>
        </div>
        <div class="my-2">
          <label class="form-label">Parent Type <span class="text-danger">*</span></label>
          <select id="typeIdE" name="typeId" class="form-select" required>
            <option value="${subType.consultationType.id}" selected >${subType.consultationType.name}</option>
<%--             <c:forEach var="type" items="${types}"> --%>
<%--               <option value="${type.id}">${type.name}</option> --%>
<%--             </c:forEach> --%>
          </select>
        </div>
        <button  style="float:right" type="button" onclick="editConsultationSubtype(${subType.id});return false;" class="btn btn-success my-2">
          <i  class="bi bi-pencil me-1"></i>Update Subtype
        </button>
      </form>
    </div>
  </div>
</div>


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