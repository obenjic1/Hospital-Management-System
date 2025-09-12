/**
 * 
 */

function saveAppointment(){
	var  patientId = document.getElementById('patientId').value;
	var doctor_id = document.getElementById("doctorId").value;
	var reason = document.getElementById("reason").value;
    var appointmentDate = document.getElementById("appointmentDate").value;
    var status = document.getElementById("status").value;
    var formData  = new FormData();
   formData.append('patientId', patientId),
   formData.append('doctor_id', doctor_id),
   formData.append('reason', reason),
   formData.append('appointmentDate', appointmentDate),
   formData.append('status', status),


		fetch('appointments', {
				method: 'POST',
				body: formData,
			})
				.then( response => {	
	   			 if (response.ok) {
					Swal.fire("Succes/Success!", "Appointment Successfully Registed !", "Appointment Successfully Registered")
					let ExtralargeModal = document.getElementById('ExtralargeModal');
					let modal = bootstrap.Modal.getInstance(ExtralargeModal);
					modal.hide();
					return loadPage('appointments');
	   			 } else if (!response.ok) {
						Swal.fire({icon: "error", title: "Oops...", text: "Something went wrong!"});
	  			 }
			})
			 .then(function(data) {

			 })
				.catch(function(error) {

				});
		
}

function saveConsultation() {
    var patientId = document.getElementById("patientIdC").value;
    var unregisteredPatientName = document.getElementById("unregisteredPatientName").value;
    var phoneNumber = document.getElementById("phoneNumber").value;
    var type = document.getElementById("type").value;
    var doctorId = document.getElementById("doctorId").value;
    var amountPaid = document.getElementById("amountPaid").value;
    var paymentType = document.getElementById("paymentType").value;
    var notes = document.getElementById("notes").value;

    var formData = new FormData();
    formData.append('patientId', patientId);
    formData.append('unregisteredPatientName', unregisteredPatientName);
    formData.append('phoneNumber', phoneNumber);
    formData.append('type', type);
    formData.append('doctorId', doctorId);
    formData.append('amountPaid', amountPaid);
    formData.append('paymentType', paymentType);
    formData.append('notes', notes);

    fetch('consultations/save', {
        method: 'POST',
        body: formData,
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Failed to save consultation.");
        }
        return response.json(); // expecting JSON with consultation ID
    })
    .then(data => {
        Swal.fire("Success", "Consultation saved successfully!", "success");

        // Hide modal
        let ExtralargeModal = document.getElementById('ExtralargeModal');
        let modal = bootstrap.Modal.getInstance(ExtralargeModal);
        modal.hide();

        // Reload consultation list
        loadPage('consultations');

        // === Download the receipt ===
        let consultationId = data.id;
        if (consultationId) {
            // Trigger file download
            const receiptUrl = `consultations/${consultationId}/receipt`;
            window.open(receiptUrl, '_blank'); // open in new tab or trigger download
        }

    })
    .catch(error => {
        console.error("Error:", error);
        Swal.fire({ icon: "error", title: "Oops...", text: "Something went wrong while saving!" });
    });
}


//function saveConsultation(){
//	
//	var patientId = document.getElementById("patientIdC").value;
//	var unregisteredPatientName = document.getElementById("unregisteredPatientName").value;
//	var phoneNumber = document.getElementById("phoneNumber").value;
//	var type 	 = document.getElementById("type").value;
//	var doctorId = document.getElementById("doctorId").value;
//	var amountPaid = document.getElementById("amountPaid").value;
//	var paymentType = document.getElementById("paymentType").value;
//	var notes = document.getElementById("notes").value;
//
//
//	var formData = new FormData();
//		formData.append('patientId', patientId),
//		formData.append('unregisteredPatientName', unregisteredPatientName),
//		formData.append('phoneNumber', phoneNumber),
//		formData.append('type', type),
//		formData.append('doctorId', doctorId),
//		formData.append('amountPaid', amountPaid),
//		formData.append('paymentType', paymentType),
//		formData.append('notes', notes),
//
//
//
//		fetch('consultations/save', {
//			method: 'POST',
//			body: formData,
//		})
//			.then(function(response) {
//				if (response.ok) {
//					Swal.fire("Success!/Success!", "Consultation save  successfully!", "Consultation save  successfully!");
//					let ExtralargeModal = document.getElementById('ExtralargeModal');
//					let modal = bootstrap.Modal.getInstance(ExtralargeModal);
//					modal.hide();
//					loadPage('consultations');
//				}else{
//					 Swal.fire({icon: "error", title: "Oops...", text: "Something went wrong!"});
//				} 
//			})
//			.then(function(data) {
//			
//			})
//			.catch(function(error) {
//				
//			});
//				
//	
//}
	