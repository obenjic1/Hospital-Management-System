
 function sendMessage(msg){
	 var successMessage = document.getElementById('successMesssage');
	successMessage.innerText = msg;
	 
 }
 
// <--------------save customer using form data ------------------------->
function savecutomer() {
	
	var name = document.getElementById('name').value;
	var email = document.getElementById('email').value;
	var telephone = document.getElementById('telephone').value;
	var address = document.getElementById('address').value;
	var thumbnail = document.getElementById('thumbnail').files[0];

	var formData = new FormData();
		formData.append('name', name);
		formData.append('email', email);
		formData.append('telephone', telephone);
		formData.append('address', address);
		formData.append('thumbnail', thumbnail);
			
		fetch('customer/save', {
			method: 'POST',
			body: formData ,
		})
		.then( response => {	

   			 if (response.status === 200) {
       			sendMessage('Succes/Success', 1);
								
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
function update(id) {
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
	
	fetch(`customer/updatecustomer/${id}`, {
		method: 'post',
		headers: {
			'Content-Type': 'application/json'
		},
      	 body: jsonUpdatedData,
	})
	.then( response => {	

   	  if (response.status === 200) {
       sendMessage('Succes/Success', 1);
								
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
       			sendMessage('Succes/Success', 1);
								
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
	deleteId = id;
	$('#confirmDeleteBtn').click(function() {
		deleteCust(deleteId);
	});
	
}




