// ----- Patient Search Function -----
function toggleNewPatientFields(value) {
    fetch(`patients/findByName?name=${encodeURIComponent(value)}`, {
        method: 'GET',
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Failed to fetch consultation.");
        }
        return response.json(); 
    })
    .then(data => {
        if (data) {
            // Auto-fill the form fields
            document.querySelector('input[name="firstName"]').value = data.name || ''; // Name
            document.querySelector('input[name="age"]').value = data.age || ''; // Age
            const genderSelect = document.querySelector('select[name="gender"]');
            genderSelect.value = data.gender || ''; // Gender
            const maritalStatusSelect = document.querySelector('select[name="maritalStatus"]');
            maritalStatusSelect.value = data.maritalStatus || ''; // Marital Status
            document.querySelector('input[name="contact"]').value = data.contact || ''; // Contact
            document.querySelector('input[name="residence"]').value = data.residence || ''; // Residence
            document.querySelector('input[name="occupation"]').value = data.occupation || ''; // Occupation
            document.querySelector('input[name="emergencyName"]').value = data.emmergenceName || ''; // Emergency Contact Name
            document.querySelector('input[name="emergencyContact"]').value = data.emmergencyContact || ''; // Emergency Contact Phone
        }
    })
    .catch(error => {
        console.error(error);
        alert("Error fetching patient.");
    });
}

let currentReasonId = null;

function toggleReasonForm(selectElement) {
    document.querySelectorAll('.price-field').forEach(inp => inp.value = '0');
    document.getElementById('grandTotal').value = '0';
    document.getElementById('netAmount').value   = '0';
    document.getElementById('discount').value    = '0';
    document.querySelectorAll('.reason-form').forEach(div => div.style.display = 'none');
    currentReasonId = selectElement.options[selectElement.selectedIndex].getAttribute('data-id'); 
    const reason = selectElement.options[selectElement.selectedIndex].getAttribute('data-name'); 

    if (!currentReasonId) {
        console.error("Invalid reasonId:", currentReasonId);
        return; // Exit if reasonId is invalid
    }

    if (reason) {
        const reasonDiv = document.getElementById(reason + 'Form');
        if (reason === 'Examen') {
            document.getElementById('ExamenForm').style.display = 'block'; 
            addExamRow(currentReasonId); 
        } else {
            loadSubtypes(reason, currentReasonId);
            if (reasonDiv) reasonDiv.style.display = 'block';  
        }
    }

    recalcTotal();
}

// ----- Subtypes Fetch and Load -----
function loadSubtypes(reason, reasonId) {
    const formDiv = document.getElementById(reason + 'Form');
    const subtypeSelect = formDiv ? formDiv.querySelector('select.consultation-subtype') : null;
    if (!subtypeSelect) return;

    subtypeSelect.innerHTML = '<option value="">-- Select Consultation Subtype --</option>';
    subtypeSelect.disabled = true;

    fetch(`admin/consultation-types/consultation-subtypes/${reasonId}`)
        .then(resp => resp.json())
        .then(data => {
            if (data && data.length > 0) {
                data.forEach(sub => {
                    let opt = document.createElement("option");
                    opt.value = sub.id;
                    opt.text = sub.name;
                    subtypeSelect.appendChild(opt);
                });
                subtypeSelect.disabled = false;
            }
        });
}


// ----- Update Price -----
function updatePrice(select, priceInputId) {
    const price = parseFloat(select.options[select.selectedIndex].dataset.price || 0);
    document.getElementById(priceInputId).value = price.toFixed(2);
    recalcTotal();  // Recalculate the total whenever the price is updated
}

// ----- Examen Handling -----
let examIndex = 0;

function addExamRow(reasonId) {
    const list = document.getElementById('examenList');
    const idx = examIndex++;

    // Create a new row for the exam selection
    const row = document.createElement('div');
    row.className = 'd-flex gap-2 mb-2';
    row.innerHTML = `
        <select class="form-select" name="visitServices[${idx}].serviceTypeId" onchange="updateExamPrice(this, this.nextElementSibling)">
            <option value="">-- Select Exam --</option>
            <!-- Exam options will be dynamically filled based on reasonId -->
        </select>
        <input type="number" name="visitServices[${idx}].price" class="form-control price-field" readonly value="0">
        <button type="button" class="btn btn-danger btn-sm" onclick="this.parentNode.remove(); recalcTotal()">Remove</button>
    `;
    
    list.appendChild(row);

    // Fetch the available subtypes for the selected reason and populate the select options
    fetch(`admin/consultation-types/consultation-subtypes/${reasonId}`)
    .then(response => response.json())
    .then(data => {
        const select = row.querySelector('select');
        data.forEach(sub => {
            const option = document.createElement('option');
            option.value = sub.id;
            option.textContent = `${sub.name} - ${sub.price}`;
            option.dataset.price = sub.price;
            select.appendChild(option);
        });
    })
    .catch(error => {
        console.error("Error fetching exam options:", error);
    });
}

function updateExamPrice(select, priceInput) {
    const price = parseFloat(select.options[select.selectedIndex].dataset.price || 0);
    priceInput.value = price.toFixed(2);
    recalcTotal();  // Recalculate total when exam price is updated
}

// ----- Pharmacy Handling -----
let medIndex = 0;

function addMedicineRow() {
    const list = document.getElementById('pharmacyList');
    const idx = medIndex++;

    // Create a new row for medicine selection
    const row = document.createElement('div');
    row.className = 'd-flex gap-2 mb-2';
    row.innerHTML = `
        <select class="form-select" name="visitServices[${idx}].serviceTypeId" onchange="updateMedicinePrice(this, this.nextElementSibling, this.nextElementSibling.nextElementSibling)">
            <option value="10" data-price="1000">Paracetamol - 1000</option>
            <option value="11" data-price="2000">Amoxicillin - 2000</option>
        </select>
        <input type="number" class="form-control" value="1" min="1" style="width:80px;" onchange="updateMedicinePrice(this.previousElementSibling, this, this.nextElementSibling)">
        <input type="number" name="visitServices[${idx}].price" class="form-control price-field" readonly value="0">
        <button type="button" class="btn btn-danger btn-sm" onclick="this.parentNode.remove(); recalcTotal()">Remove</button>
    `;
    list.appendChild(row);
}

function updateMedicinePrice(select, qtyInput, priceInput) {
    const unit = parseFloat(select.options[select.selectedIndex].dataset.price || 0);
    const qty = parseInt(qtyInput.value || 1);
    priceInput.value = (unit * qty).toFixed(2);
    recalcTotal();  // Recalculate total when medicine price is updated
}

// ----- Recalculate Total -----
function recalcTotal() {
    let total = 0;

    // Add up all the prices from dynamically added rows (exams, pharmacy, consultations, etc.)
    document.querySelectorAll('.price-field').forEach(pe => {
        const v = parseFloat(pe.value || 0);
        if (!isNaN(v)) total += v;
    });

    const otherAmount = document.querySelector('input[name="otherAmount"]');
    if (otherAmount) {
        const ov = parseFloat(otherAmount.value || 0);
        if (!isNaN(ov)) total += ov;
    }

    // Update the grand total
    document.getElementById('grandTotal').value = total.toFixed(2);

    const discount = parseFloat(document.getElementById('discount').value || 0);
    const net = total - (total * (discount / 100));
    document.getElementById('netAmount').value = net.toFixed(2);
}
function setPatientId() {
  const input = document.getElementById('patientName');  // This is the patient name input
  const patientIdInput = document.getElementById('patientId');  // Hidden field to store patient ID
  
  // Find the selected option by comparing the value (patient name)
  const selectedOption = Array.from(document.getElementById('patientsList').options)
    .find(option => option.value === input.value);
  
  if (selectedOption) {
    // Set the patient ID in the hidden input field
    patientIdInput.value = selectedOption.dataset.id;  // Use dataset.id (patient ID) from the option
  } else {
    // If no match, clear the hidden input
    patientIdInput.value = "";
  }
}

function toggleAppointmentForm(checkbox) {
    const formDiv = document.getElementById("appointmentForm");
    formDiv.style.display = checkbox.checked ? "block" : "none";
}

function setDiscount() {
    const input = document.getElementById('discount');
    const actualDiscountValue = document.getElementById('actualDiscountValue');
  
    // Find the selected option and assign its data-value to the hidden input
    const selectedOption = Array.from(document.getElementById('discountList').options)
    .find(option => option.value === input.value);
  
    if (selectedOption) {
        actualDiscountValue.value = selectedOption.dataset.value;
    }
    recalcTotal();  // Recalculate total when medicine price is updated
}

// ----- Doctor ID handling -----
function setDoctorId() {
    const input = document.getElementById('doctorId');
  
    // Find the selected option based on the name
    const selectedOption = Array.from(document.getElementById('doctors').options)
        .find(option => option.value === input.value);

    if (selectedOption) {
        // Get the doctorId from the data-id attribute
        const doctorId = selectedOption.getAttribute('data-id');
        
        // Set the doctorId as the hidden value
        input.value = doctorId;  // This will now hold the doctorId (numeric value) instead of the name
    }
}

// ----- Form Data Builder -----
function buildVisitFormData() {
    const fd = new FormData();

    /* ---------- 1.  PATIENT  ---------- */
    const patientIdInput = document.getElementById('patientId').value.trim();

   
        // If patientId is provided (existing patient)
        fd.append('patientId', patientIdInput);
  
        // If no patient is selected, collect the new patient details
        fd.append('firstName', document.querySelector('input[name="firstName"]').value.trim());
        fd.append('age', document.querySelector('input[name="age"]').value.trim());
        fd.append('gender', document.querySelector('select[name="gender"]').value.trim());
        fd.append('contact', document.querySelector('input[name="contact"]').value.trim());
        fd.append('residence', document.querySelector('input[name="residence"]').value.trim());
        fd.append('occupation', document.querySelector('input[name="occupation"]').value.trim());
        fd.append('maritalStatus', document.querySelector('select[name="maritalStatus"]').value.trim());
        fd.append('emergencyName', document.querySelector('input[name="emergencyName"]').value.trim());
        fd.append('emergencyContact', document.querySelector('input[name="emergencyContact"]').value.trim());
   

    /* ---------- 2.  VISIT HEADER  ---------- */
    const reasonSel = document.getElementById('reasonForVisit');
    const selectedReason = reasonSel.options[reasonSel.selectedIndex];
    fd.append('reasonForVisit', selectedReason.value);  // Name of the reason
    fd.append('reasonId', selectedReason.getAttribute('data-id'));  // ID of the reason

   /* ---------- 3.  SERVICES (dynamic rows)  ---------- */
document.querySelectorAll('.price-field').forEach((inp, idx) => {
    const row   = inp.closest('.reason-form, #examenList, #pharmacyList'); 
    const type  = row ? row.querySelector('select[name*="serviceTypeId"]') : null;
    const price = parseFloat(inp.value) || 0;

    if (!type || !type.value || price <= 0) return;

    fd.append(`services[${idx}].serviceTypeId`, type.value);
    fd.append(`services[${idx}].price`,         price);
});

    
let svcIdx = 0;
document.querySelectorAll('select[name*="subserviceDTO"]').forEach(subSel => {
    if (!subSel.value) return;          // nothing chosen – skip

    fd.append(`services[${svcIdx}].subserviceDTO.id`,   subSel.value);
    fd.append(`services[${svcIdx}].subserviceDTO.name`, subSel.options[subSel.selectedIndex].text);
    svcIdx++;
});
    /* ---------- 4. PAYMENT  ---------- */
    fd.append('totalAmount', document.getElementById('grandTotal').value);
    fd.append('discount', document.getElementById('discount').value);  
    fd.append('netAmount', document.getElementById('netAmount').value); 

  
    const doctorId = document.getElementById('doctorId').value.trim();
    if (doctorId) {
        fd.append('doctorId', doctorId);  // Ensure the doctorId is correctly appended as a number
    }

    /* ---------- 5. OPTIONAL APPOINTMENT  ---------- */
    const chk = document.getElementById('appointmentCheck');
    if (chk && chk.checked) {
        fd.append('createAppointment', 'true');
        fd.append('appointmentReason', document.getElementById('reason').value.trim());
        fd.append('appointmentDate', document.getElementById('appointmentDate').value);
        fd.append('appointmentTime', document.getElementById('appointmentTime').value);
    } else {
        fd.append('createAppointment', 'false');
    }

    return fd;
}

// ----- Save Visit -----
function saveVisit() {
    event.preventDefault();
    const fd = buildVisitFormData();

    fetch('visit', {
        method: 'POST',
        body: fd
    }).then (function(response) {
				if (response.ok) {
					Swal.fire("Success!/Success!", "Visit Registered successfully!", "success");
					document.getElementById("close-btn").click();
					loadPage('visit');
					
				}else{
					 Swal.fire({icon: "error", title: "Oops...", text: "Something went wrong!"});
				} 
			})
    .then(html => {
    })
    .catch(err => {
        alert(err.message);
    });
}


  /* ---------- build query string and reload table ---------- */
  function applyFilter(){
    const name  = document.getElementById('nameFilter').value.trim();
    const from  = document.getElementById('fromFilter').value;
    const to    = document.getElementById('toFilter').value;
    const status= window.currentStatus || '';

    const params = new URLSearchParams();
    if(name)  params.append('name',name);
    if(from)  params.append('from',from);
    if(to)    params.append('to',to);
    if(status)params.append('status',status);

    const url = 'factures?' + params.toString();
    loadPage(url);

//    fetch(url)
//      .then(r => r.text())
//      .then(html => {
//        const newBody = new DOMParser()
//                          .parseFromString(html,'text/html')
//                          .querySelector('#tableBody').innerHTML;
//        document.querySelector('#tableBody').innerHTML = newBody;
//        /* update record count */
//        const newCount= (newBody.match(/<tr>/g) || []).length - (newBody.includes('No invoices') ? 1 : 0);
//        document.querySelector('#countBadge').textContent = newCount + ' record(s)';
//      })
//      .catch(err => location.href = url);   // fall back to full reload
  }

  /* ---------- quick status toggle ---------- */
  let currentStatus = '';
  function setStatus(st){
    currentStatus = st;
    applyFilter();
  }

  /* ---------- delete confirmation ---------- */
  function confirmDelete(id){
    if(confirm('Delete invoice #' + id + ' ?\nThis action cannot be undone.')){
      location.href = '${pageContext.request.contextPath}/factures/delete/' + id;
    }
  }

  /* ---------- ENTER key in name field ---------- */
  document.getElementById('nameFilter').addEventListener('keyup', e => {
    if (e.key === 'Enter') applyFilter();
  });
