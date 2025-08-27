<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
							pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>Patient Form</title>
    
  <link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">
    <h2>Patient Form</h2>
    <form   style="background-color: white; margin: auto auto">
        <div class="my-3">
            <label>Name</label>
            <input type="text" class="form-control" id="name" name="name" value="${patient.name}" required/>
        </div>
        <div class="my-3">
            <label>Age</label>
            <input type="number" class="form-control" name="age" value="${patient.age}"  id="age"/>
        </div>
        <div class="my-3">
            <label>Gender</label>
            <select  id="gender"class="form-control" name="gender">
            	<option value="">choose a gender .....</option>
                <option ${patient.gender == 'Male' ? 'selected' : ''}>Male</option>
                <option ${patient.gender == 'Female' ? 'selected' : ''}>Female</option>
            </select>
        </div>
        <div class="my-3">
            <label>Contact</label>
            <input type="text" class="form-control" id="contact" name="contact" value="${patient.contact}" required/>
        </div>
      <div class="d-flex justify-content-between">
       		 <a  class="btn btn-secondary"  data-bs-dismiss="modal" >Cancel</a>
            <button type="button" onclick="savePatient()" class="btn btn-success">Save</button>
      </div>      
    </form>
</body>
<script src="assets/js/hospital/staff.js"></script>

</html>
