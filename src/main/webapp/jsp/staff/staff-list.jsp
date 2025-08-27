<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
							pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>
<head>
    <title>Staff Management</title>
    <link href="assets/css/list-users.css" rel="stylesheet">
    
</head>
<body class="bg-light">

<div class="container mt-5">
	  

    <div class=" col-lg-6 d-flex justify-content-between align-items-center mb-2 ">  
      <h2 class="mb-2">Hospital Staffs</h2>
	    <button onclick="loadPageModalForm('staff/new')" data-toggle="tooltip" data-placement="top" title="create new user" type="button" data-bs-toggle="modal" data-bs-target="#ExtralargeModal" class="btn btn-primary" >+ Add Staff </button>
    </div>
    
    <form  class="d-flex m-4" >
			<input type="text" name="q" class="form-control search-bar m-6" id="searchBoxer"  style="width: 49%;" placeholder="Search Staff..." />
                <select name="category" id="category" class="form-select ms-2" style="width:180px;">
                    <option value="All" ${selectedCategory == 'All' ? 'selected' : ''}>All Categories</option>
                    <c:forEach var="cat" items="${categories}">
                        <option value="${cat.name}">${cat.name}</option>
                    </c:forEach>
                    <option  onclick="loadMainModalForm('store/add-category')" data-bs-toggle="modal" data-bs-target="#MainModal" class="btn btn-gradient" style="margin-right:121px">Add New Category</option>
                </select>
                <button type="button"  onclick="event.preventDefault(); searchPharmacyMedicine()" class="btn btn-outline-dark ms-2">Search</button>
            </form>
<!-- href="/staff/new"  -->
    <table class="table table-bordered table-hover shadow-sm">
        <thead class="table-dark">
        <tr>
            <th>No</th>
            <th>Name</th>
            <th>Department</th>
            <th>Speciality</th>
            <th>Salary</th>
            
            <th>Phone</th>
            <th>Email</th>
            <th>Status</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="s" items="${staffList}" varStatus="loop">
               <tr>
                  <td>${loop.index + 1}</td>
				
                <td>${s.firstName} ${s.lastName}</td>
                <td>${s.department.name}</td>
                <td>${s.speciality}</td>
                <td>${s.salary}</td>
                
                <td>${s.phone}</td>
                <td>${s.email}</td>
                     <td><a class="${s.active ? 'Blocked' : 'Active' }">${s.active ? 'Inactive' : 'Active'}</a></td>

                <td>
                    <button class="btn btn-sm " data-bs-toggle="modal" data-toggle="tooltip" data-placement="top" title="Edit Staff Details" data-bs-target="#MainModal" onclick="loadMainModalForm('staff/edit/${s.id}')" style="width:60px; margin-left:10px">  <i class="ri-pencil-line"></i></button>
                     <button class="button-delete" data-bs-toggle="modal"  data-toggle="tooltip" data-placement="top" title="Deactivate/Reactivate Staff" onclick="confirmDisableStaff(${s.id})">
								      ${s.active ? '<i class="bi-toggle2-off"></i>' : '<i class="bi-toggle2-on"></i>'}
								   </button>
                    <button class="btn" data-bs-toggle="modal" data-bs-target="#MainModal" onclick="loadMainModalForm('staff/edit/${s.id}')" style="width:60px; margin-left:10px"><i class="ri-delete-bin-3-line btn-danger"></i></button>
                   
                
<%--                     <a href="/staff/edit/${s.id}" class="btn btn-sm btn-warning">Edit</a> --%>
<%--                     <a href="/staff/delete/${s.id}" class="btn btn-sm btn-danger" --%>
<!--                        onclick="return confirm('Are you sure?')">Delete</a> -->
<!--                 </td> -->
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
