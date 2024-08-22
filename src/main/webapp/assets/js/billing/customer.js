
 function sendMessage(msg){
	 var successMessage = document.getElementById('successMesssage');
	successMessage.innerText = msg;
	 
 }
 
  function loadCustomerInputFromJobForm(page) {
	fetch(page)
		.then(response => response.text())
		.then(html => {
			document.getElementById("loadInputForCustomerNewlyCreated").innerHTML = html;
		})
		.catch(error => console.log(error));
}

 
// <--------------save customer using form data ------------------------->
function savecutomer() {
	
	var name = document.getElementById('name').value;
	var email = document.getElementById('email').value;
	var telephone = document.getElementById('telephone').value;
	var address = document.getElementById('address').value;
	var thumbnailInput = document.getElementById('thumbnail');
	var thumbnailFile = thumbnailInput.files.length > 0 ? thumbnailInput.files[0] : null;


	var formData = new FormData();
		formData.append('name', name);
		formData.append('email', email);
		formData.append('telephone', telephone);
		formData.append('address', address);
		if (thumbnailFile) {
		    formData.append('thumbnail', thumbnailFile);
		}
//		formData.append('thumbnail', thumbnail);
			
		fetch('customer/save', {
			method: 'POST',
			body: formData ,
		})
		.then( response => {	

   			 if (response.status === 200) {
       			sendMessage('Succes/Success', 1);
				return loadPage("customer/list");
   			 } else if (response.status !== 200) {
				sendMessage('Failed / Echec', 2);
  			 }
		})
		 .then(function(data) {

		 })
			.catch(function(error) {

			});
		
}

// <--------------Update customer ------------------------->

function updatecustomer(id) {
	var name = document.getElementById('name').value;
	var email = document.getElementById('email').value;
	var telephone = document.getElementById('telephone').value;
	var address = document.getElementById('address').value;
	
	var updateData = {
		name: name,
		email: email,
		telephone: telephone,
		address: address
	};	
	var jsonUpdatedData = JSON.stringify(updateData);
	
	fetch(`customer/updateCustomer/${id}`, {
		method: 'post',
		headers: {
			'Content-Type': 'application/json'
		},
      	 body: jsonUpdatedData,
	})
	.then( response => {	


   	  if (response.ok) {
		sendMessage('Succes/Success', 1);
		return loadPage('customer/list');		

								
    } else if (response.status !== 200) {
	  sendMessage('Failed / Echec', 2);
  	 }
		})
		 .then(function(data) {

		 })
			.catch(function(error) {
	});
}


// <-------------- Delete customer ------------------------->
function deleteCust(id){
	fetch(`customer/delete/${id}`, {
		method: 'POST',
		headers: {
			'Content-type': 'application/json'
		},
	})
	.then( response => {	

   			 if (response.status === 200) {
				
				return loadPage('customer/list');
       			
								
   			 } else if (response.status !== 200) {
				sendMessage('Failed / Echec', 2);
  			 }
		})
		 .then(function(data) {

		 })
			.catch(function(error) {

			});
}
// <-------------- Confirm delete customer ------------------------->
function confirmDelete(id) {
	let deleteId = id;
	$('#areyouSureYouWantToDetele').modal('show');
	$('#confirmDeleteBtn').click(function() {
		deleteCust(deleteId);
	});
	
}


/**
 *  Create customer since job form
 */
function saveCustomerFromJobForm(){
	var name = document.getElementById('name').value;
	var email = document.getElementById('email').value;
	var telephone = document.getElementById('telephone').value;
	var address = document.getElementById('address').value;
	var thumbnailInput = document.getElementById('thumbnail');
	var thumbnailFile = thumbnailInput.files.length > 0 ? thumbnailInput.files[0] : null;


	var formData = new FormData();
		formData.append('name', name);
		formData.append('email', email);
		formData.append('telephone', telephone);
		formData.append('address', address);
		if (thumbnailFile) {
		    formData.append('thumbnail', thumbnailFile);
		}
			
		fetch('customer/job/new-customer', {
			method: 'POST',
			body: formData ,
		})
		.then( response => {	

   			 if (response.status === 200) {
       			sendMessage('Succes/Success', 1);
       			return loadCustomerInputFromJobForm("customer/get/new-customer");
   			 } else if (response.status !== 200) {
				sendMessage('Failed / Echec', 2);
  			 }
		})
		 .then(function(data) {

		 })
			.catch(function(error) {

			});
}



