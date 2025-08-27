<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Patients</title>
        <link href="assets/css/list-users.css" rel="stylesheet">
    
</head>
<body class="container mt-4">
    <div class=" col d-flex justify-content-between align-items-center mb-2 ">  
      <h2 class="mb-2">Hospital Staffs</h2>
	    <button onclick="loadPage('patients/new')" data-toggle="tooltip" data-placement="top" title="Register a new user" type="button" class="btn btn-outline-dark" >+ Add Patient </button>
    </div>
    <form  class="d-flex m-4" >
			<input type="text" name="q" class="form-control search-bar m-6" id="searchBoxer"  style="width: 49%;" placeholder="Search patient..." />
                <select name="category" id="category" class="form-select ms-2" style="width:180px;">
                    <option value="All" ${selectedCategory == 'All' ? 'selected' : ''}>All Categories</option>
                    <c:forEach var="cat" items="${categories}">
                        <option value="${cat.name}">${cat.name}</option>
                    </c:forEach>
                    <option  onclick="loadMainModalForm('store/add-category')" data-bs-toggle="modal" data-bs-target="#MainModal" class="btn btn-gradient" style="margin-right:121px">Add New Category</option>
                </select>
                <button type="button"  onclick="event.preventDefault(); searchPharmacyMedicine()" class="btn btn-outline-primary ms-2">Search</button>
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
                        <a  data-bs-toggle="modal"  data-bs-target="#ExtralargeModal" class="btn btn-sm btn-secondary">Edit</a>
                        <a    data-bs-toggle="modal"  data-bs-target="#ExtralargeModal" class="btn btn-sm btn-outline-primary">View</a>
                         <button class="button-see" data-bs-toggle="modal" data-bs-target="#ExtralargeModal" onclick="loadPageModalForm('patients/history/${p.id}')">
										       <i class="ri-eye-line"></i>
						</button>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
