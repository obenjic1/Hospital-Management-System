/**
 * 
 */
function addStaff(){
		const firstName = document.getElementById('firstName').value;
		const lastName = document.getElementById('lastName').value;
		const email = document.getElementById("email").value;
		const phone = document.getElementById('mobile').value;
		const address = document.getElementById('address').value;
		const salary = document.getElementById('salary').value;
		const speciality = document.getElementById('speciality').value;
		const gender = document.getElementById('gender').value;
		const status = document.getElementById('status').value;
		const department = document.getElementById('department').value;
	

		if(firstName==""){
			customAlert('Please, enter a First Name!');
			return;
		}
		  
		 if(phone==""){
			customAlert('Please enter a phone number!');
			return false;
		} 
		
		 if(salary==""){
		customAlert('Please enter a salary!');
		return;
		} 
		if(gender==""){
				customAlert('Please, Choose a Gender!');
				return;
			}
		
		var formData = new FormData();
			formData.append('firstName', firstName),
			formData.append('lastName', lastName),
			formData.append('email', email),
			formData.append('phone', phone),
			formData.append('salary', salary),
			formData.append('speciality', speciality),
			formData.append('gender', gender),
			formData.append('address', address),
			formData.append('status', status);
			formData.append('department', department);
			

			fetch('staff', {
				method: 'POST',
				body: formData,
			})
				.then( response => {	
	   			 if (response.ok) {
					Swal.fire("Succes/Success!", "Staff Successfully Registed !", "Staff Successfully Registered")
					let ExtralargeModal = document.getElementById('ExtralargeModal');
					let modal = bootstrap.Modal.getInstance(ExtralargeModal);
					modal.hide();
					return loadPage('staff');
	   			 } else if (!response.ok) {
						Swal.fire({icon: "error", title: "Oops...", text: "Something went wrong!"});
					

	  			 }
			})
			 .then(function(data) {

			 })
				.catch(function(error) {

				});
		
}


function updateStaff(id){
		const firstName = document.getElementById('firstName').value;
		const lastName = document.getElementById('lastName').value;
		const email = document.getElementById("email").value;
		const phone = document.getElementById('mobile').value;
		const address = document.getElementById('address').value;
		const salary = document.getElementById('salary').value;
		const speciality = document.getElementById('speciality').value;
		const gender = document.getElementById('gender').value;
		const status = document.getElementById('status').value;
		const department = document.getElementById('department').value;
	
		
		var formData = new FormData();
			formData.append('firstName', firstName),
			formData.append('lastName', lastName),
			formData.append('email', email),
			formData.append('phone', phone),
			formData.append('salary', salary),
			formData.append('speciality', speciality),
			formData.append('gender', gender),
			formData.append('address', address),
			formData.append('status', status);
			formData.append('department', department);
			

			fetch(`staff/edit/${id}`, {
				method: 'PUT',
				body: formData,
			})
				.then( response => {	
	   			 if (response.ok) {
	//       			sendMessage('Succes/Success', 1);
					Swal.fire("Succes/Success!", "Staff Successfully Updated !");
					document.getElementById("close-btn").click();
					let ExtralargeModal = document.getElementById('ExtralargeModal');
					let modal = bootstrap.Modal.getInstance(ExtralargeModal);
					modal.hide();
				//	document.getElementById("close-btn").click();				
					return loadPage('staff');
	   			 } else if (!response.ok) {
						Swal.fire({icon: "error", title: "Oops...", text: "Something went wrong!"});
	//				sendMessage('Failed / Echec : Email or Username already exist ',2);
					
					//	return loadPage('user/add-user');			

	  			 }
			})
			 .then(function(data) {

			 })
				.catch(function(error) {

				});
		
}


function confirmDisableStaff(id) {
  let deleteId = id;
		  Swal.fire({
			  title: "Are you sure?",
			  text: "You're about to desable this staff from work!",
			  icon: "warning",
			  showCancelButton: true,
			  confirmButtonColor: "#3085d6",
			  cancelButtonColor: "#d33",
			  confirmButtonText: "Yes, disable it!"
			}).then((result) => {
			  if (result.isConfirmed) {
				  disableStaff(deleteId);
			 
			  }else{
				 Swal.fire("Cancelled/Annulee!", "Operation cancelled", "info");
			  }
			});

}

function disableStaff(id){
		fetch(`staff/enable/${id}`, {
		method: 'POST',
	})
	.then( response => {	

   			 if (response.status === 200) {
			Swal.fire({title: "Disabled!", text: "Disabled successfully!", icon: "success" });
			loadPage('staff');
   			 } else if (response.status !== 200) {
				Swal.fire({icon: "error", title: "Oops...", text: "Something went wrong!"});
  			 }
		})
		 .then(function(data) {

		 })
			.catch(function(error) {

			});
}




	 
function selectedStaff(selectElement) {
    let salary = selectElement.options[selectElement.selectedIndex].dataset.salary;
   document.getElementById("basicSalary").value = salary;
    calculatePayroll();
}

		function calculatePayroll() {
		  let basic = +document.getElementById("basicSalary").value || 0;
		  let housing = +document.getElementById("housingAllowance").value || 0;
		  let transport = +document.getElementById("transportAllowance").value || 0;
		  let overtime = +document.getElementById("overtimePay").value || 0;
		  let otherAllowances = +document.getElementById("otherAllowances").value || 0;
		
		  let tax = +document.getElementById("tax").value || 0;
		  let pension = +document.getElementById("pension").value || 0;
		  let otherDeductions = +document.getElementById("otherDeductions").value || 0;
		
		  let gross = basic + housing + transport + overtime + otherAllowances;
		  let deductions = tax + pension + otherDeductions;
		  let net = gross - deductions;
		
		  document.getElementById("grossSalary").value = gross;
		  document.getElementById("netSalary").value = net;
		}
		
		// Attach event listeners to recalc on input
		document.querySelectorAll("#createPayrollForm input").forEach(el => {
		  el.addEventListener("input", calculatePayroll);
		});
		
	function submitPayroll() {
    event.preventDefault();
    
    // Retrieve values
    let staff = document.getElementById("staffId").value;
    let month = document.getElementById("month").value;
    let basic = +document.getElementById("basicSalary").value || 0;
    let housing = +document.getElementById("housingAllowance").value || 0;
    let transport = +document.getElementById("transportAllowance").value || 0;
    let overtime = +document.getElementById("overtimePay").value || 0;
    let otherAllowances = +document.getElementById("otherAllowances").value || 0;
    let tax = +document.getElementById("tax").value || 0;
    let pension = +document.getElementById("pension").value || 0;
    let otherDeductions = +document.getElementById("otherDeductions").value || 0;
    let basicSalary = document.getElementById("basicSalary").value;


    // Calculations
    let gross = basic + housing + transport + overtime + otherAllowances;
    let allowance = gross - basic;
    let deductions = tax + pension + otherDeductions;
    let netSalary = gross - deductions;

    // Prepare FormData
    var formData = new FormData();
    formData.append('staff', staff);
    formData.append('month', month);
    formData.append('allowances', allowance);
    formData.append('deductions', deductions);
    formData.append('netSalary', netSalary);
    formData.append('basicSalary', basicSalary);


    // Send data to server
    fetch('payroll/save', {
        method: 'POST',
        body: formData,
    }) .then(response => {
		if (response.ok) {
					Swal.fire("Succes/Success!", "Payroll Successfully Registed !", "Payroll Successfully Registered")
					let ExtralargeModal = document.getElementById('ExtralargeModal');
					let modal = bootstrap.Modal.getInstance(ExtralargeModal);
					modal.hide();
					return loadPage('payroll');
	   			 } else if (!response.ok) {
						Swal.fire({icon: "error", title: "Oops...", text: "Something went wrong!"});
					

	  			 }
		
	}).then(function(data) {

	
			 }).catch(function(error) {

				});}
		
		
				function createService() {
				event.preventDefault();

				let name = document.getElementById("name").value;
				let price = document.getElementById("price").value;
				let category = document.getElementById("category1").value;
				let description = document.getElementById("description").value;
				var formData = new FormData();
				formData.append('name', name);
				formData.append('price', price);
				formData.append('category', category);
				formData.append('description', description);
				console.log(formData);
				fetch('services', {
				    method: 'POST',
				    body: formData,
				}) .then(response => {
					if (response.ok) {
								Swal.fire("Succes/Success!", "Service Successfully Registed !", "Service Successfully Registered")
								let ExtralargeModal = document.getElementById('ExtralargeModal');
								let modal = bootstrap.Modal.getInstance(ExtralargeModal);
								modal.hide();
								return loadPage('services');
				   			 } else if (!response.ok) {
									Swal.fire({icon: "error", title: "Oops...", text: "Something went wrong!"});
				  			 }
				}).then(function(data) {
						 }).catch(function(error) {
});}
			
			function calculateTotal(){
						 var select = document.getElementById("serviceItems");
		    var total = 0;
		
		    for (var i = 0; i < select.options.length; i++) {
		        if (select.options[i].selected) {
		            total += parseFloat(select.options[i].getAttribute("data-price"));
		        }
		    }
		
		    document.getElementById("totalAmount").value = total.toFixed(2);
}
	
	
			
				function updateService(id) {
				event.preventDefault();

				let name = document.getElementById("uname").value;
				let price = document.getElementById("uprice").value;
				let category = document.getElementById("ucategory").value;
				let description = document.getElementById("udescription").value;
				var formData = new FormData();
				formData.append('name', name);
				formData.append('price', price);
				formData.append('category', category);
				formData.append('description', description);
				fetch(`services/update/${id}`, {
				    method: 'POST',
				    body: formData,
				}) .then(response => {
					if (response.ok) {
								Swal.fire("Succes/Success!", "Service Successfully Updated !", "Service Successfully Updated")
								let ExtralargeModal = document.getElementById('ExtralargeModal');
								let modal = bootstrap.Modal.getInstance(ExtralargeModal);
								modal.hide();
								return loadPage('services');
				   			 } else if (!response.ok) {
									Swal.fire({icon: "error", title: "Oops...", text: "Something went wrong!"});
				  			 }
				}).then(function(data) {
						 }).catch(function(error) {
});}
			
			function calculateTotal(){
						 var select = document.getElementById("serviceItems");
		    var total = 0;
		
		    for (var i = 0; i < select.options.length; i++) {
		        if (select.options[i].selected) {
		            total += parseFloat(select.options[i].getAttribute("data-price"));
		        }
		    }
		
		    document.getElementById("totalAmount").value = total.toFixed(2);
}
	
	
	
	
	
	
	
	
	
	function savePayment() {
    event.preventDefault();
    const patientId = document.getElementById("patient").value;
    const unregisteredPatientName =  document.getElementById("unregisteredPatientName").value;
    const paymentMethod = document.getElementById("paymentMethod").value;
    const serviceItemIds = Array.from(document.getElementById("serviceItems").selectedOptions)
                               .map(opt => opt.value);

   
    // Send request to backend
    fetch(`payments/create?patientId=${patientId}&unregisteredPatientName=${unregisteredPatientName}&serviceItemIds=${serviceItemIds.join(",")}&paymentMethod=${paymentMethod}`, {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
       // body: `patientId=${patientId}&serviceItemIds=${serviceItemIds.join(",")}&paymentMethod=${paymentMethod}&unregisteredPatientName=${unregisteredPatientName}`
    })
    .then(response => {
					if (response.ok) {
								Swal.fire("Succes/Success!", "Payement Successfully Registered !", "Service Successfully Registered")
								let ExtralargeModal = document.getElementById('ExtralargeModal');
								let modal = bootstrap.Modal.getInstance(ExtralargeModal);
								modal.hide();
								return loadPage('services');
				   			 } else if (!response.ok) {
									Swal.fire({icon: "error", title: "Oops...", text: "Something went wrong!"});
				  			 }
				}).then (data => {
      Swal.fire("Succes/Success!", "Patient Successfully Registed !", "Patient Successfully Registered")
								let ExtralargeModal = document.getElementById('ExtralargeModal');
								let modal = bootstrap.Modal.getInstance(ExtralargeModal);
								modal.hide();
								return loadPage('payments');
    })
    .catch(err => Swal.fire({icon: "error", title: "Oops...", text: "Something went wrong!"}));

		}
		
		
		
			function downloadReceipt(id){
				  const pdfUrl = `payments/receipt/${id}/pdf`;
    			   window.open(pdfUrl,'_blank');
			}

function confirmPayment(id) {
		  Swal.fire({
			  title: "Are you sure?",
			  text: "You want to Confirm this payent!",
			  icon: "warning",
			  showCancelButton: true,
			  confirmButtonColor: "#3085d6",
			  cancelButtonColor: "#d33",
			  confirmButtonText: "Yes, Confirm it!"
			}).then((result) => {
			  if (result.isConfirmed) {
				  pay(id);
			 
			  }else{
				 Swal.fire("Cancelled/Annulee!", "Operation cancelled", "info");
			  }
			});

}
		function pay(id){
			
	   fetch(`payments/pay/${id}`, {
        method: "POST",
    }) .then(response => {
					if (response.status==200) {
								Swal.fire("Succes/Success!", "Payment Successfully Registed !", "Payment Successfully Registered")
								let ExtralargeModal = document.getElementById('ExtralargeModal');
								let modal = bootstrap.Modal.getInstance(ExtralargeModal);
								modal.hide();
								return loadPage('payments');
				   			 } else if (!response.ok) {
									Swal.fire({icon: "error", title: "Oops...", text: "Something went wrong in this orienment !"});
				  			 }
				})
    .then(data => {
     
    })
    .catch(err => Swal.fire({icon: "error", title: "Oops...", text: "Something went wrong!"}));

			
		}
	
