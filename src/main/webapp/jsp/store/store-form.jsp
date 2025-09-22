<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<style>
    .btn-gradient {
        background: linear-gradient(to right, #4e73df, #7b42f6);
        color: white;
        border: none;
    }
    .btn-gradient:hover {
        opacity: 0.9;
    }
</style>

<main id="edit-medicine">
    <section>
        <div class="card">
            <div class="card-body">
                <h5 class="card-title text-center pb-0 fs-4">Edit Medicine</h5>
                <p class="text-center small">
                    Edit this Medication carefully, all actions are tracked
                </p>

                <form class="row g-3 needs-validation" style="margin-left:5%" 
                      onsubmit="event.preventDefault(); updateMedicine('${medicine.id}')">

                    <!-- Medicine Name & Code -->
                    <div class="row">
                        <div class="col-md-8">
                            <label class="form-label">Medicine Name :</label>
                            <input type="text" id="nameE" value="${medicine.name}" 
                                   class="form-control" required>
                        </div>
                        <div class="col-md-4">
                            <label class="form-label">Code :</label>
                            <input type="text" id="codeE" value="${medicine.code}" 
                                   class="form-control" required>
                        </div>
                    </div>

                    <!-- Description -->
                    <div class="row mt-2">
                        <label class="form-label">Prescription :</label>
                        <textarea id="descriptionE" class="form-control" 
                                  placeholder="Enter a description ......">${medicine.description}</textarea>
                    </div>

                    <!-- Category -->
                    <div class="row mt-2">
                        <label class="form-label">Category :</label>
                        <select id="medCategoryE" class="form-select" required>
                            <option value="">Choose a category ...</option>
                            <c:forEach var="cat" items="${categories}">
                                <option value="${cat.id}" 
                                    <c:if test="${cat.id == medicine.category.id}">selected</c:if>>
                                    ${cat.name}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- Quantity & Purchase Price -->
                    <div class="row mt-2">
                        <div class="mb-2 col-md-6">
                            <label class="form-label">Total Quantity (Packets) :</label>
                            <input type="number" id="quantityE" value="${medicine.quantity}" 
                                   class="form-control" required>
                        </div>
                        <div class="mb-2 col-md-6">
                            <label class="form-label">Purchase Price (CFA) :</label>
                            <input type="number" step="0.01" id="purchasePriceE" 
                                   value="${medicine.purchasePrice}" class="form-control" required>
                        </div>
                    </div>

                    <!-- Packet Price & Unit Price -->
                    <div class="row mt-2">
                        <div class="mb-2 col-md-6">
                            <label class="form-label">Packet Price (CFA) :</label>
                            <input type="number" step="0.01" id="packetPriceE" 
                                   value="${medicine.packetPrice}" class="form-control" required>
                        </div>
                        <div class="mb-2 col-md-6">
                            <label class="form-label">Unit Price (CFA) :</label>
                            <input type="number" step="0.01" id="unitPriceE" 
                                   value="${medicine.unitPrice}" class="form-control" required>
                        </div>
                    </div>

                    <!-- Units per Packet & Threshold -->
                    <div class="row mt-2">
                        <div class="mb-2 col-md-6">
                            <label class="form-label">Units Per Packet :</label>
                            <input type="number" id="unitsPerPacketE" min="1" 
                                   value="${medicine.unitsPerPacket}" class="form-control" required>
                        </div>
                        <div class="mb-2 col-md-6">
                            <label class="form-label">Threshold (Low Packet) :</label>
                            <input type="number" id="thresholdE" 
                                   value="${medicine.threshold}" class="form-control">
                        </div>
                    </div>

                    <!-- Expiry Date -->
                    <div class="row mt-2">
                        <label class="form-label">Expiry Date :</label>
                        <input type="date" id="expiryDateE" value="${medicine.expirationDate}" 
                               class="form-control">
                    </div>

                    <!-- Action buttons -->
                    <div class="d-flex mt-3 gap-2 justify-content-between">
                        <button type="button" id="add-close" class="btn btn-secondary" 
                                data-bs-dismiss="modal">Cancel</button>
                        <button type="submit" class="btn btn-gradient">Update</button>
                    </div>
                </form>
            </div>
        </div>
    </section>
</main>

<script src="assets/js/billing/customer.js"></script> 
<script src="assets/js/store/medicine.js"></script> 
