<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Payments</title>
    <link href="assets/css/list-users.css" rel="stylesheet">
    
</head>
<body class="container mt-4">
<div class="d-flex justify-content-between  my-3">
  <h4>Staff Payroll</h4>
  <button class="btn btn-outline-primary"   onclick="loadPageModalForm('payments/new')" data-toggle="tooltip" data-placement="top" title="create new user" type="button" data-bs-toggle="modal" data-bs-target="#ExtralargeModal">
    <i class="bi bi-plus-circle"></i> New Payroll
  </button>
  
  
</div>
<form  class="d-flex m-4" >
			<input type="text" name="q" class="form-control search-bar m-6" id="searchBoxer"  style="width: 49%;" placeholder="Search payment..." />
                <select name="category" id="category" class="form-select ms-2" style="width:180px;">
                    <option value="All" ${selectedCategory == 'All' ? 'selected' : ''}>All Categories</option>
                    <c:forEach var="cat" items="${categories}">
                        <option value="${cat.name}">${cat.name}</option>
                    </c:forEach>
                    <option  onclick="loadMainModalForm('store/add-category')" data-bs-toggle="modal" data-bs-target="#MainModal" class="btn btn-gradient" style="margin-right:121px">Add New Category</option>
                </select>
                <button type="button"  onclick="event.preventDefault(); searchPharmacyMedicine()" class="btn btn-outline-primary ms-2">Search</button>
            </form>
   <table class="table table-bordered table-hover shadow-sm " style="text-align: center;">
    <thead class="table-dark">
        <tr>
            <th>No</th>
            <th>Patient</th>
            <th>Services</th>
            <th>Amount</th>
             <th>Status</th>
            <th>Date</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="p" items="${payments}" varStatus="loop">
                <tr>
                    <td>${loop.index + 1}</td>
                <td><c:choose>
			        <c:when test="${not empty p.patient}">
			            ${p.patient.name}
			        </c:when>
			        <c:otherwise>
			            ${p.unregisteredPatientName}
			        </c:otherwise>
			    </c:choose></td>
                <td>
                    <c:forEach var="s" items="${p.serviceItems}" varStatus="status">
                        ${s.name}<c:if test="${!status.last}">, </c:if>
                    </c:forEach>
                </td>
                <td>${p.amount}</td>
                <td><c:choose>
			        <c:when test="${p.status == 'PENDING'}">
			           <span style="color:red"> ${p.status}</span> 
			        </c:when>
			        <c:otherwise>
			           <span style="color:green"> ${p.status}</span> 
			        </c:otherwise>
			    </c:choose></td>
                <td>${p.paymentDate}</td>
                <td>
                
                <c:choose>
			        <c:when test="${p.status == 'PENDING'}">
			            <button class="btn btn-sm btn-success px-4" onclick="confirmPayment(${p.id})">Pay</button>
			        </c:when>
			        <c:otherwise>
			          <button onclick="downloadReceipt(${p.id})" 
                       class="btn btn-sm btn-outline-primary">Receipt</button>
			        </c:otherwise>
                    
                 </c:choose>
                 
                 <button class="button-see" data-bs-toggle="modal" data-bs-target="#ExtralargeModal" onclick="loadPageModalForm('payments/history/${p.id}')">
										       <i class="ri-eye-line"></i>
				</button>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</body>
</html>
