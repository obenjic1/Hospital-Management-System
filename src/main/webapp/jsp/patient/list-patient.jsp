<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Patients</title>
        <link href="assets/css/list-users.css" rel="stylesheet">
    
</head>
<body class="container mt-4">
    <div class=" col d-flex justify-content-between align-items-center mb-2 ">  
      <h2 class="mb-2">Patients</h2>
	    <button onclick="loadPage('patients/new')" data-toggle="tooltip" data-placement="top" title="Register a new user" type="button" class="btn btn-outline-dark" >+ Add Patient </button>
    </div>
    <form  class="d-flex m-4" >
			<input type="text" name="q" class="form-control search-bar m-6" id="patientName"  style="width: 49%;" placeholder="Search patient..." />
                <button type="button"  onclick="event.preventDefault(); searchPatient()" class="btn btn-outline-primary ms-2">Search</button>
    </form>
    <table class="table table-bordered table-hover shadow-sm" style="text-align: center;">
        <thead class="table-dark">
            <tr>
                <th>ID</th><th>Name</th><th>Age</th><th>Gender</th><th>Contact</th><th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="p" items="${patients}" varStatus="loop">
                <tr>
                    <td>${loop.index + 1}</td>
                    <td>${p.name}</td>
                    <td>${p.age}</td>
                    <td>${p.gender}</td>
                    <td>${p.contact}</td>
                    <td style="text-aligh:center">
                    
                    <select>
                   		<option> Action</option>
                    	<option data-bs-toggle="modal"  data-bs-target="#ExtralargeModal" onclick="loadPageModalForm('appointments/new?patientId=${p.id}')"> Book Appointment</options>
                    	<option data-bs-toggle="modal"  data-bs-target="#ExtralargeModal"  onclick="loadPageModalForm('patients/view/${p.id}')" class="btn btn-sm btn-secondary">View</option>
                    	<option data-bs-toggle="modal"  data-bs-target="#ExtralargeModal"  onclick="loadPageModalForm('patients/history/${p.id}')" class="btn btn-sm btn-secondary">History</option>
                    </select>
<!--                         <a  data-bs-toggle="modal"  data-bs-target="#ExtralargeModal" class="btn btn-sm btn-secondary">Edit</a> -->
<%--                         <a    data-bs-toggle="modal" onclick="loadPageModalForm('patients/view/${p.id}')" data-bs-target="#ExtralargeModal" class="btn btn-sm btn-outline-primary"></a> --%>
<%--                          <button class="button-see" data-bs-toggle="modal" data-bs-target="#ExtralargeModal" onclick="loadPageModalForm('patients/history/${p.id}')"> --%>
<!-- 										       <i class="ri-eye-line"></i> -->
<!-- 						</button> -->
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
