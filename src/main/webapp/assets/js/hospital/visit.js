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
  document.querySelectorAll('.reason-form').forEach(div => div.style.display = 'none');
  
  // Get the reasonId from the selected option
  currentReasonId = selectElement.options[selectElement.selectedIndex].getAttribute('data-id'); 
  const reason = selectElement.options[selectElement.selectedIndex].getAttribute('data-name'); 
  
  // Check if reasonId is valid
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
  const subtypeSelect = document.getElementById(reason + 'Form').getElementsByTagName('select')[0];
  subtypeSelect.innerHTML = '<option value="">-- Select Consultation Subtype --</option>';
  subtypeSelect.disabled = true;

  if (!reason) return;
  fetch(`admin/consultation-types/consultation-subtypes/${reasonId}`)
    .then(response => response.json())
    .then(data => {
      console.log(data);
      data.forEach(sub => {
        const option = document.createElement("option");
        option.value = sub.id;
        option.textContent = `${sub.name} - ${sub.price}`;
        option.dataset.price = sub.price;
        subtypeSelect.appendChild(option);
      });
      subtypeSelect.disabled = false;  // Enable the select box after loading options
    })
    .catch(error => {
      console.error("Error fetching subtypes:", error);
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

function toggleAppointmentForm(checkbox) {
    const formDiv = document.getElementById("appointmentForm");
    formDiv.style.display = checkbox.checked ? "block ruby" : "none";
}
