
// <--------------save customer using form data ------------------------->
function save() {
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
		
		fetch('/customer/save', {
			method: 'POST',
			body: formData ,
		})
		.then(function(response) {
   			 if (response.status === 'Success') {
       			 return loadPage('/customer/list');
   			 } else if (response.status === 'Error') {
       			 console.log(response.errorMessage);
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
	
	fetch(`/customer/updatecustomer/${id}`, {
		method: 'post',
		headers: {
			'Content-Type': 'application/json'
		},
      	 body: jsonUpdatedData,
	})
	.then(function(response) {
		
	})
	.catch(function(error){
		console.log('error')
	})
}


