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


function setDiscount() {
    const input  = document.getElementById('discount');
    const hidden = document.getElementById('actualDiscountValue');

    /* try datalist first */
    const opt = Array.from(document.getElementById('discountList').options)
                     .find(o => o.value === input.value);
    hidden.value = opt ? opt.value.replace('%','')   // "5%" -> "5"
                       : (parseFloat(input.value)||0); // free-type 5, 10.5 …
    recalcTotal();
}
	   
	  // ----- Doctor ID handling ----- 
	 function setDoctorId() {
    const input = document.getElementById('doctorId');
    const opt   = Array.from(document.getElementById('doctors').options)
                       .find(o => o.value === input.value);
    if (opt) {
        input.setAttribute('data-doctor-id', opt.getAttribute('data-id')); // store id
        // do NOT overwrite input.value – user still sees the name
    }
}

function setPatientId() {
    const input = document.getElementById('patientName');   // visible text field
    const opt   = Array.from(document.getElementById('patientsList').options)
                       .find(o => o.value === input.value.trim());
    if (opt) {
        document.getElementById('patientId').value = opt.getAttribute('data-id'); // store id
        // do NOT overwrite input.value – user still sees the name
    } else {
        document.getElementById('patientId').value = ''; // clear if not found
    }
}

// ----- Toggle Form Visibility -----
function toggleSubForm(type, show) {
  var form = document.getElementById(type + 'Form');
  if (!form) return;
  form.style.display = show ? 'block' : 'none';

  var checkBox = document.querySelector('input[data-name="' + type + '"]');
  var reasonId = checkBox ? checkBox.dataset.id : null;

  if (!show || !reasonId) {
    recalcTotal();
    return;
  }

  // Reset the price fields for other consultation types when switching
  resetPriceFields();

  // Reset other forms except Examen
  var select = form.querySelector('select[name$="serviceTypeId"]');
  if (select) {
    select.innerHTML = ''; // Reset the options for other forms (Consultation, Vaccination, etc.)
  }

  // Clear exam rows if it's not an exam consultation type
  if (type !== 'Examen') {
    var examenList = document.getElementById('examenList');
    if (examenList) {
      examenList.innerHTML = ''; // Clear all exam rows
    }
  }

  var url = 'admin/consultation-types/consultation-subtypes/' + reasonId;
  var xhr = new XMLHttpRequest();
  xhr.open('GET', url, true);
  xhr.setRequestHeader('Content-Type', 'application/json');
  xhr.onload = function () {
    if (xhr.status >= 200 && xhr.status < 300) {
      var data = JSON.parse(xhr.responseText);

      // Handle dynamic content loading based on the consultation type
      if (type === 'Examen') {
        loadExams(data); // Load all exams at once if this is an exam
      } else {
        // For other consultation types, load select options
        loadConsultationOptions(data, type); 
      }
    } else {
      console.error('Load sub-types failed:', xhr.statusText);
    }
    recalcTotal();
  };
  xhr.onerror = function () {
    console.error('Network error while loading sub-types');
    recalcTotal();
  };
  xhr.send();
}



// Function to load the select options for Consultation, Vaccination, etc.
function loadConsultationOptions(data, type) {
  const form = document.getElementById(type + 'Form');
  const select = form.querySelector('select[name$="serviceTypeId"]');
  
  // Reset previous options
  if (select) {
    // Ensure the first option is always visible, not disabled
    const prompt = document.createElement('option');
    prompt.value = '';
    prompt.textContent = '-- Select option --';
    prompt.selected = true;
    select.appendChild(prompt);
    
    // Add new options
    data.forEach(sub => {
      const opt = document.createElement('option');
      opt.value = sub.id;
      opt.textContent = `${sub.name} – ${sub.price}`;
      opt.dataset.price = sub.price;
      select.appendChild(opt);
    });
    select.disabled = false; // Enable the select
  }
}

// Function to load all exams at once and display them
function loadExams(data) {
  const examenList = document.getElementById('examenList');
  examenList.innerHTML = ''; // Clear any existing exams

  // Create a dropdown with all exam options
  const select = document.createElement('select');
  select.className = 'form-select';
  select.id = 'examSelect'; // ID for referencing this dropdown

  // Add default option
  const prompt = document.createElement('option');
  prompt.value = '';
  prompt.textContent = '-- Select exam --';
  prompt.selected = true;
  prompt.disabled = true;
  select.appendChild(prompt);

  // Add all exam options
  data.forEach(exam => {
    const option = document.createElement('option');
    option.value = exam.id;
    option.textContent = `${exam.name} – ${exam.price}`;
    option.dataset.price = exam.price;
    select.appendChild(option);
  });

  examenList.appendChild(select);
  
  // Enable the button to add the exam row
  const addButton = document.createElement('button');
  addButton.type = 'button';
  addButton.className = 'btn btn-primary mt-3';
  addButton.textContent = 'Add Exam';
  addButton.onclick = addExamRow; // Bind the function to the button
  examenList.appendChild(addButton);
}

function addExamRow() {
    const select = document.getElementById('examSelect');
    if (!select || !select.value) return;

    const opt      = select.options[select.selectedIndex];
    const examId   = opt.value;
    const examName = opt.textContent;
    const price    = parseFloat(opt.dataset.price);

    const list = document.getElementById('examenList');
    const row  = document.createElement('div');
    row.className = 'd-flex gap-2 mb-2 exam-row';               // keep class for loop
    row.dataset.id   = examId;
    row.dataset.name = examName;
    row.dataset.price= price;

    row.innerHTML = `
        <span class="form-control">${examName}</span>
        <input type="number" class="form-control price-field" readonly value="${price}">
        <button type="button" class="btn btn-danger btn-sm" onclick="this.parentNode.remove(); recalcTotal()">Remove</button>
    `;
    list.appendChild(row);
    recalcTotal();
}

// ----- Update Price Function -----
function updatePrice(select, priceInputId) {
    const price = parseFloat(select.options[select.selectedIndex].dataset.price || 0);
    document.getElementById(priceInputId).value = price.toFixed(2);
    recalcTotal();  // Recalculate the total whenever the price is updated
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

function toggleAppointmentForm(checkbox){ 
	 const formDiv = document.getElementById("appointmentForm"); formDiv.style.display = checkbox.checked ? "block ruby" : "none";

}

/* helper: reasonName → real price input id */
function priceId(reasonName) {
    const map = {
        'Consultation'             : 'consultationPrice',
        'Consultation Prénatale'   : 'consultation-PrénatalePrice',
        'Echographie'              : 'echoPrice',
        'Vaccination'              : 'vaccPrice',
        'Autre'                    : 'autrePrice'
    };
    return map[reasonName];
}

/* ----------  build FormData  ---------- */
function buildVisitFormData() {
    const fd = new FormData();

    /* 1.  PATIENT  ------------------------------------------ */
   setPatientId();   // sets hidden field #patientId
fd.append('patientId', document.getElementById('patientId').value.trim());

fd.append('firstName',  document.querySelector('[name="firstName"]').value.trim());
fd.append('age',        document.querySelector('[name="age"]').value.trim());
fd.append('gender',     document.querySelector('[name="gender"]').value.trim());
fd.append('contact',    document.querySelector('[name="contact"]').value.trim());
fd.append('residence',  document.querySelector('[name="residence"]').value.trim());
fd.append('occupation', document.querySelector('[name="occupation"]').value.trim());
fd.append('maritalStatus', document.querySelector('[name="maritalStatus"]').value.trim());
fd.append('emergencyName',    document.querySelector('[name="emergencyName"]').value.trim());
fd.append('emergencyContact', document.querySelector('[name="emergencyContact"]').value.trim());
    /* 2.  VISIT DATE / TIME  -------------------------------- */
    fd.append('visitDate', new Date().toISOString().split('T')[0]);
    
    const now = new Date();
    const isoTime = now.toTimeString().slice(0, 5);  // "HH:mm"
    fd.append('visitTime', isoTime);

    /* 3.  SERVICES (checkbox driven)  ----------------------- */
    const checked = document.querySelectorAll('input[type="checkbox"][data-id]:checked');
     console.log('checked boxes', checked.length, checked); 
    let idx = 0;

checked.forEach(chk => {
    const reasonName = chk.value;    // Consultation, Examen, Vaccination …
    const reasonId   = chk.dataset.id;
    fd.append('reasonId', reasonId || '');
    fd.append('reasonName', reasonName);

    // ---- SINGLE ROW TYPES (Consultation, Prenatale, Echo, Vaccination, Autre) ----
    if (['Consultation','Consultation Prénatale','Echographie','Vaccination','Autre'].includes(reasonName)) {
        const form   = document.getElementById(reasonName + 'Form');
        if (form) {
            const select = form.querySelector('select[name$="serviceTypeId"]');
            const priceInput = form.querySelector('.price-field');

            if (select && select.value) {
                fd.append(`services[${idx}].id`, select.value);                     // subtype id
                fd.append(`services[${idx}].name`, select.selectedOptions[0].text); // subtype name
                fd.append(`services[${idx}].price`, priceInput ? priceInput.value : 0);
                idx++;
            }
        }
    }

    // ---- EXAMENS (multiple rows) ----
    if (reasonName === 'Examen') {
        document.querySelectorAll('#examenList .exam-row').forEach(row => {
            const id   = row.dataset.id;
            const name = row.dataset.name;
            const price= row.dataset.price;
            if (!id || !price) return;

            fd.append(`services[${idx}].id`,   id);
            fd.append(`services[${idx}].name`, name);
            fd.append(`services[${idx}].price`, price);
            idx++;
        });
    }


    });

    /* 4.  PAYMENT  ------------------------------------------ */
    fd.append('totalAmount', document.getElementById('grandTotal').value);
    fd.append('netAmount',   document.getElementById('netAmount').value);

    setDiscount();                                              // your function
    fd.append('discount', document.getElementById('actualDiscountValue').value);

    setDoctorId();                                              // your function
    fd.append('doctorId', document.getElementById('doctorId').getAttribute('data-doctor-id') || '');

    /* 5.  OPTIONAL APPOINTMENT  ----------------------------- */
    const appChk = document.getElementById('appointmentCheck').checked;
    fd.append('createAppointment', appChk);
    if (appChk) {
        fd.append('appointmentReason', document.getElementById('reason').value.trim());
        fd.append('appointmentDate',   document.getElementById('appointmentDate').value);
        fd.append('appointmentTime',   document.getElementById('appointmentTime').value);
    }

    return fd;
}


// ----- Save Visit -----
 function saveVisit() {
	  event.preventDefault();
	   const fd = buildVisitFormData();
	   fetch('visit/save', { method: 'POST', body: fd })
	   .then (function(response) { if (response.ok)
	    { Swal.fire("Success!/Success!", "Visit Registered successfully!", "success"); 
	    loadPage('patients'); }else{ Swal.fire({icon: "error", title: "Oops...", text: "Something went wrong!"}); } })
	     .then(html => { }) .catch(err => { alert(err.message); }); }
let currentStatus = ''; // '' = All

function setStatus(st) {
    currentStatus = st; // '' is valid → shows All
    document.querySelectorAll('.btn-group button').forEach(b => b.classList.remove('active'));
    document.querySelector(`.btn-group button[value="${st}"]`).classList.add('active');
    applyFilter();
}

function applyFilter() {
    const name = document.getElementById('nameFilter').value.trim();
    const from = document.getElementById('fromFilter').value;
    const to   = document.getElementById('toFilter').value;
    const params = new URLSearchParams();
    if (name)   params.append('name', name);
    if (from)   params.append('from', from);
    if (to)     params.append('to', to);
    if (currentStatus) params.append('status', currentStatus);

    loadPage('factures?' + params.toString());
}
      /* ---------- delete confirmation ---------- */ 
      
      function confirmDelete(id){
		   if(confirm('Delete invoice #' + id + ' ?\nThis action cannot be undone.')){ 
		  location.href = '${pageContext.request.contextPath}/factures/delete/' + id; } 
		  } 
		  
		  /* ---------- ENTER key in name field ---------- */ 
  document.getElementById('nameFilter').addEventListener('keyup', e => { 
	  if (e.key === 'Enter') applyFilter(); }); const balance = parseFloat('${facture.balance}');
	   const amountInput = document.getElementById('amount'); 
	   const remainingSpan = document.getElementById('remainingSpan'); 
	   amountInput.addEventListener('input', function () 
	   { const paid = parseFloat(this.value) || 0; let left = balance - paid; if (left < 0)
	    {
			 // block over-payment 
			 this.value = balance.toFixed(2); left = 0; }
	 remainingSpan.textContent = new Intl.NumberFormat('fr-FR', { style: 'currency', currency: 'XAF' }).format(left);
	  });


function printView(id){
	 const element = document.getElementById('factureContent');
        const opt = {
            margin:       10,
            filename:    id,
            image:        { type: 'jpeg', quality: 0.98 },
            html2canvas:  { scale: 2 },
            jsPDF:        { unit: 'mm', format: 'a4', orientation: 'portrait' }
        };
        html2pdf().set(opt).from(element).save();
}



function searchStats(){
	    const date = document.getElementById('date').value;
	     const params = new URLSearchParams();
       if (date)   params.append('date', date);
	   loadPage('factures/stats/daily-sales?' + params.toString());

}
function searchDoctor(){
	  const params = new URLSearchParams();
     
	  const startDate = document.getElementById('startDateD').value;
	  const endDate = document.getElementById('endDateD').value;
	   if (startDate)   params.append('startDate', startDate);
       if (endDate)   params.append('endDate', endDate);
       loadPage('visit/statistics/doctor-consultations/?' + params.toString());

}

function resetPriceFields(type) {
  // Reset the price fields only for the consultation type that is unchecked
  if (type !== 'Examen') {
    document.querySelectorAll('.price-field').forEach((field) => {
      const serviceTypeSelect = field.closest('.reason-form')?.querySelector('select[name*="serviceTypeId"]');
      if (serviceTypeSelect && serviceTypeSelect.value) {
        field.value = '0'; // Reset price to 0
      }
    });
  }
  recalcTotal();  // Recalculate total after resetting prices
}



