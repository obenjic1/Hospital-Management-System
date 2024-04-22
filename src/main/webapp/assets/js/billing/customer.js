

// <--------------save customer using form data ------------------------->
function save(){
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
			
			})
			.then(function(data) {

			})
			.catch(function(error) {

			});
		
}


//function myFunction() {	
//	loadPage('/customer/displayCustomerForm');
//
//}












