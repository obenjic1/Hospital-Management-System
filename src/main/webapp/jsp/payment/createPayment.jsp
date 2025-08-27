
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<main id="add-user">
    <div class="card shadow-lg rounded-3">
        <div class="card-header bg-primary text-white">
            <h4 class="mb-4">New Payment</h4>
        </div>
        <div class="card-body">
            <form id="paymentForm">
                <!-- Patient Select -->
                <div class="my-3">
                    <label for="patient" class="form-label">Select Patient</label>
                    <select id="patient" name="patientId" class="form-select" >
                        <option value="">-- Select Registered Patient --</option>
                        <c:forEach var="p" items="${patients}">
                            <option value="${p.id}">${p.name}</option>
                        </c:forEach>
                    </select>
                </div>
		    <div class="form-group my-3">
		        <label>If patient not registered, enter name:</label>
		        <input type="text" name="unregisteredPatientName" id="unregisteredPatientName" 
		               class="form-control" placeholder="Enter patient name"/>
		    </div>
                <!-- Service Items Multi Select -->
                <div class="my-3">
                    <label for="serviceItems" class="form-label">Select Services</label>
                    <select id="serviceItems" name="serviceItemIds" class="form-select" onchange="calculateTotal()" multiple required>
                        <c:forEach var="s" items="${services}">
                            <option value="${s.id}" data-price="${s.price}">
                                ${s.name} - ${s.price} FCFA
                            </option>
                        </c:forEach>
                    </select>
                    <div class="form-text">Hold CTRL (Windows) or CMD (Mac) to select multiple</div>
                </div>

                <!-- Total Preview -->
                <div class="my-3">
                    <label class="form-label">Total Amount</label>
                    <input type="text" id="totalAmount" class="form-control" readonly>
                </div>

                <!-- Payment Method -->
                <div class="my-3">
                    <label class="form-label">Payment Method</label>
                    <select name="paymentMethod" id="paymentMethod" class="form-select" >
                        <option value="">-- Select Method --</option>
                         <option value="CASH">Pending</option>
                        <option value="CASH">Cash</option>
                        <option value="MOBILE_MONEY">Mobile Money</option>
                        <option value="CARD">Card</option>
                    </select>
                </div>

                <button type="button" onclick="savePayment()" class="btn btn-success w-100">Record Payment</button>
            </form>
        </div>
    </div>
</main><script src="assets/js/hospital/staff.js"></script>


