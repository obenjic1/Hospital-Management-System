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
	//       			sendMessage('Succes/Success', 1);
					Swal.fire("Succes/Success!", "Staff Successfully Registed !", "Staff Successfully Registered")
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