<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
							pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>Patient Detail</title>
    
  <link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<main id="add-user">

	<section>
	<div class="card">
	<div class="card-body">
    <h2>Patient Detail</h2>
    <br>
    <form   style="background-color: white; margin: auto auto">
    <div class="row">
        <div class="my-3 col-md-6">
            <label>Name : </label>
            <span>${patient.name}</span>
        </div>
        <div class="my-3 col-md-6 ">
            <label>Age : </label>
            <span>${patient.age}</span>
        </div>
        
      </div>
    <div class="row">
        <div class="my-3 col-md-6">
            <label>Gender : </label>
             <span>${patient.gender}</span>

        </div>
        <div class="my-3 col-md-6">
            <label>Contact : </label>
           <span>${patient.contact}</span>
        </div>
     </div>
         <div class="my-3 row">
            <label>Emmergency Contact information </label>
            <div class="row">
            <div class="col-md-6"> 
             <label>Name :  </label>
             <span>${patient.emmergenceName}</span> </div>
            
             <div class="col-md-6">
               <label>Contact :  :</label>
                <span>${patient.emmergencyContact}</span></div>
            </div>
           
        </div>
<!--       <div class="d-flex justify-content-between"> -->
<!--        		 <a  class="btn btn-secondary"  data-bs-dismiss="modal" >Cancel</a> -->
<!--             <button type="button" onclick="savePatient()" class="btn btn-success">Save</button> -->
<!--       </div>       -->
    </form>
    
    
      <h4>Previous Appointments</h4>
          <br>
      
<table class="table table-bordered">
    <thead>
        <tr>
            <th>Date</th>
            <th>Doctor</th>
            <th>Status</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="a" items="${appointments}">
            <tr>
                <td>${a.appointmentDate}</td>
                <td>${a.doctor.firstName} ${a.doctor.lastName}</td>
                <td>${a.status}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<!-- <h4>Consultations History</h4> -->
<!--     <br> -->

<!-- <table class="table table-bordered"> -->
<!--     <thead> -->
<!--         <tr> -->
<!--             <th>Date</th> -->
<!--             <th>Diagnosis</th> -->
<!--             <th>Doctor</th> -->
<!--             <th>Actions</th> -->
<!--         </tr> -->
<!--     </thead> -->
<!--     <tbody> -->
<%--         <c:forEach var="c" items="${consultations}"> --%>
<!--             <tr> -->
<%--                 <td><fmt:formatDate value="${c.consultationDate}" pattern="dd-MM-yyyy HH:mm" /></td> --%>
<%--                 <td>${c.diagnosis}</td> --%>
<%--                 <td>${c.appointment.doctor.fullName}</td> --%>
<!--                 <td> -->
<!--                     <a class="btn btn-sm btn-info" -->
<%--                        href="${pageContext.request.contextPath}/consultations/view/${c.id}"> --%>
<!--                         View Consultation -->
<!--                     </a> -->
<!--                 </td> -->
<!--             </tr> -->
<%--         </c:forEach> --%>
<!--     </tbody> -->
<!-- </table> -->
<!-- <div class="d-flex justify-content-end mb-3"> -->
<%--     <a href="${pageContext.request.contextPath}/patients/${patient.id}/history/pdf" --%>
<!--        class="btn btn-outline-primary" target="_blank"> -->
<!--         <i class="bi bi-file-earmark-pdf"></i> Download PDF -->
<!--     </a> -->
<!-- </div> -->
	</div>
			</div>
		</section>
</main>
<script src="assets/js/hospital/staff.js"></script>

</html>
