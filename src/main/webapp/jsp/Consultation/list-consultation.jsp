<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <title>Consultations</title>
     <link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-4">
    <h2 class="mb-4 text-center">Consultations</h2>
    
  <form  class="mb-4">
		    <div class="row">
		        <div class="col-md-4">
		            <label>Start Date</label>
		            <input type="date" id="startDateC" value="${startDate}" class="form-control">
		        </div>
		        <div class="col-md-4">
		            <label>End Date</label>
		            <input type="date" id="endDateC" value="${endDate}" class="form-control">
		        </div>
		        <div class="col-md-4 align-self-end">
		            <button type="button" onclick="searchConsultations()" class="btn btn-outline-primary w-100">Filter</button>
		        </div>
		    </div>
		</form>
		<div class="row mt-2 g-2">
		 <form  class="d-flex m-2 col-6 " >
			<input type="text" name="q" class="form-control search-bar m-6" id="patientName"  style="width: 100%;" placeholder="Search patient..." />
                <button type="button"  onclick="event.preventDefault(); searchPatientName()" class="btn btn-outline-primary ms-2">Search</button>
            </form>
            
             <div class=" col-5 text-align-left" style="text-align: end;" >
            <button class="btn btn-outline-primary"  data-bs-toggle="modal"  data-bs-target="#ExtralargeModal" onclick="loadPageModalForm('consultations/new/consultation')">
                <i class="bi bi-plus"></i> New Consultation
            </button>
            <a class="btn btn-success"  data-bs-toggle="modal"  data-bs-target="#ExtralargeModal" onClick="loadPageModalForm('${pageContext.request.contextPath}/consultations/walkin')">
                Walk-in Consultation
            </a>
        </div>
        </div>
    <table class="table table-striped table-hover shadow-sm">
        <thead class="table-dark">
        <tr>
            <th>#</th>
             <th>Reference Num</th>
            <th>Patient Name</th>
            <th>Doctor Name</th>
             <th>Consultation Type</th>
             <th>Amount</th>
            <th>Date</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="c" items="${consultations}" varStatus="loop">
             <tr>
             <td>${loop.index + 1}</td>
              <td>${c.referenceNumber}</td>
                <td><c:choose>
			        <c:when test="${not empty c.patient}">
			            ${c.patient.name}
			        </c:when>
			        <c:otherwise>
			            ${c.unreegisteredPatientName}
			        </c:otherwise>
			    </c:choose></td>
                <td>${c.doctor.firstName}</td>
                <td>${c.type}</td>
                 <td>${c.amountPaid}</td>
                <td>${c.consultationDate}</td>
                <td>
                    <button class="btn btn-sm btn-outline-primary">
                        View
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
