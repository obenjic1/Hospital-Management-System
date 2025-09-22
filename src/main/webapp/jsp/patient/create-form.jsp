<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
    uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<main id="add-user">
    <section>
        <div class="card">
            <div class="card-body">
                <h5 class="card-title text-center pb-0 fs-4">Register a new Patient</h5>
                <p class="text-center small">Enter the personal details to register a new Patient</p>

                <form class="row g-3" id="patientForm" novalidate style="margin-left: 5%;">
                    <div class="col-md-6">
                        <label for="name" class="form-label">Name</label>
                        <div class="input-group has-validation">
                            <span class="input-group-text"><i class="fas fa-user"></i></span>
                            <input type="text" id="name" name="firstName" class="form-control" placeholder="Please Enter Patient's fullnames ..." required/>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <label for="contact" class="form-label">Patient's Contact</label>
                        <div class="input-group has-validation">
                            <span class="input-group-text"><i class="fas fa-phone"></i></span>
                            <input type="text" id="contact" name="contact" class="form-control" placeholder="Please Enter Patient's Phone number ..."/>
                            <span id="emailMsg" style="color:red"></span> <br><br>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <label for="age" class="form-label">Age</label>
                        <div class="input-group has-validation">
                            <span class="input-group-text"><i class="fas fa-user"></i></span>
                            <input type="number" id="age" name="age" class="form-control" placeholder="Please Enter Patient's age ..."/>
                        </div>
                    </div>
                     <!-- New Fields -->
                    <div class="col-md-6">
                        <label for="occupation" class="form-label">Occupation</label>
                        <div class="input-group has-validation">
                            <span class="input-group-text"><i class="fas fa-briefcase"></i></span>
                            <input type="text" id="occupation" name="occupation" class="form-control" placeholder="Please Enter Patient's Occupation"/>
                        </div>
                    </div>

                    <div class="col-md-6">
                        <label for="maritalStatus" class="form-label">Marital Status</label>
                        <div class="input-group has-validation">
                            <span class="input-group-text"><i class="fas fa-heart"></i></span>
                            <select id="maritalStatus" name="maritalStatus" class="form-select">
                                <option value="single">Single</option>
                                <option value="married">Married</option>
                                <option value="divorced">Divorced</option>
                                <option value="widowed">Widowed</option>
                            </select>
                        </div>
                    </div>

                    <div class="col-md-6">
                        <label for="residence" class="form-label">Residence</label>
                        <div class="input-group has-validation">
                            <span class="input-group-text"><i class="fas fa-home"></i></span>
                            <input type="text" id="residence" name="residence" class="form-control" placeholder="Please Enter Patient's Residence"/>
                        </div>
                    </div>

                    <div class="col-md-6">
                        <label for="gender" class="form-label">Gender</label>
                        <div class="input-group has-validation">
                            <select id="gender" name="gender" class="form-select">
                                <option value="null" selected>Select gender</option>
                                <option value="male">Male</option>
                                <option value="female">Female</option>
                            </select>
                        </div>
                    </div>
	<div class="row">
                    <div class="col-md-6">
                        <label for="emergencyName" class="form-label">Patient's Emergency Contact Name</label>
                        <div class="input-group has-validation">
                            <span class="input-group-text"><i class="fas fa-map-marker-alt"></i></span>
                            <input type="text" id="emmergenceName" name="emergencyName" class="form-control" required="required" placeholder="Please Enter Patient's Emergency contact Name ..."/>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <label for="emergencyContact" class="form-label">Patient's Emergency Contact</label>
                        <div class="input-group has-validation">
                            <span class="input-group-text"><i class="fas fa-phone"></i></span>
                            <input type="text" id="emmergencyContact" name="emergencyContact" class="form-control" placeholder="Please Enter Patient's Emergency contact" required="required"/>
                        </div>
                    </div>	 
                     </div>
           
                   
					<div class="row">
                    <div class="col-md-3" style="width: 20%; left: 72%; position: relative;bottom: -10px;">
                        <input type="button" id="createBtn" onclick="savePatient()" style="bottom: -42%;" class="btn btn-outline-primary w-100" value="Save">
                    </div>
                    </div>
                </form>
            </div>
        </div>
    </section>
</main>

<script src="assets/js/hospital/medicine.js"></script>
