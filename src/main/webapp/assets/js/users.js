
//<---------------fontion load different function pages--------------------->

function loadPage(page) {
	fetch(page)
		.then(response => response.text())
		.then(html => {
			document.getElementById('main-content').innerHTML = html;
		})
		.catch(error => console.log(error));
}

function loadPageModal(page) {
	fetch(page)
		.then(response => response.text())
		.then(html => {
			document.getElementById('main-content').innerHTML = html;
		})
		.catch(error => console.log(error));
}


// <-------------- Fieald validation  ------------------------->
function validateField(field, errorMessage) {
	if (field.trim() === '') {
		var alertElement = document.getElementById(errorMessage);
		alertElement.textContent = 'Sorry, this field cannot be empty!';
		alertElement.style.display = 'block';

		setTimeout(function() {
			alertElement.style.display = 'none';
		}, 5000);

		return false;
	}

	return true;
}


function customAlert(message){
	Swal.fire({
			  title: "<small>File error</small>!",
			  text: message,
			  html: false
			});
}

function validateAddUserform(){
	
	
}
// <-------------- Save user ------------------------->
function addUser() {
	const firstName = document.getElementById('firstName').value;
	const lastName = document.getElementById('lastName').value;
	const email = document.getElementById("email").value;
	const password = document.getElementById("password").value;
	const confirmPassword = document.getElementById("confirmPassword").value;
	const mobile = document.getElementById('mobile').value;
	const address = document.getElementById('address').value;
	const username = document.getElementById('username').value;
	const groupe = document.getElementById('groupe').value;
	//const imageFile = document.getElementById('imageFile').files[0];
	var thumbnailInput = document.getElementById('imageFile');
	var thumbnailFile = thumbnailInput.files.length > 0 ? thumbnailInput.files[0] : null;

	if(firstName==""){
		customAlert('Please, enter your First Name!');
		return;
	}
	 if(email==""){
		customAlert('Please enter your Email!');
		return;
	}  
	 if(mobile==""){
		customAlert('Please enter your phone number!');
		return false;
	} 
	
	 if(username==""){
	customAlert('Please enter your Username!');
	return;
	} 
	
	 if(address==""){
		customAlert('Please enter your Address!');
		return;
	} 
	

	if(password==""){
		customAlert('Please enter your Password!');
		return;
		} else if (confirmPassword =="") {
			
			customAlert('Please enter the confirm password!');
		}



	 if(confirmPassword != password){
		customAlert('The password fields should be the same');
		return;
	} 
	var formData = new FormData();
		formData.append('firstName', firstName),
		formData.append('lastName', lastName),
		formData.append('email', email),
		formData.append('mobile', mobile),
		formData.append('username', username),
		formData.append('password', password),
		formData.append('confirmPassword', confirmPassword),
		formData.append('address', address),
		formData.append('groupe', groupe),
		if (thumbnailFile) {
		    formData.append('imageFile', thumbnailFile);
		}

		fetch('user/add-user', {
			method: 'POST',
			body: formData,
		})
			.then( response => {	
   			 if (response.status === 200) {
//       			sendMessage('Succes/Success', 1);
				Swal.fire("Succes/Success!", "You clicked the button!", "success")
				return loadPage('user/list-users');
   			 } else if (response.status !== 200) {
				sendMessage('Failed / Echec : Email or Username already exist ',2);
				//	return loadPage('user/add-user');			

  			 }
		})
		 .then(function(data) {

		 })
			.catch(function(error) {

			});
	
}

//<------------------ Update user using DTO object -------------------->
function updateUserById(id) {
	
	const firstName = document.getElementById('firstName').value;
	const lastName = document.getElementById('lastName').value;
	const email = document.getElementById("email").value;
	const mobile = document.getElementById('mobile').value;
	const address = document.getElementById('address').value;
	const username = document.getElementById('username').value;
	const groupe = document.getElementById('groupe').value;
	const imageFile = document.getElementById('imageFile').files[0];

	var formData = {
				
				firstName:firstName,
				lastName:lastName,
				email:email,
				mobile:mobile,
				username:username,
				address:address,
				groupe:groupe,
				imageFile:imageFile
}
			
	var jsonUserUpdateData = JSON.stringify(formData);

	fetch(`user/update-user/${id}`, {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: jsonUserUpdateData,
	})
		.then(response => {
			if (response.ok) {
				sendMessage('Succes/Success', 1);
				return loadPage('user/list-users');
  			 } else if (response.status !== 200) {
				sendMessage('Failed / Echec', 2);
  			 }
		})
		 .then(function(data) {

		 })
			.catch(function(error) {
			});
}


//<------------------ Disactivate and activate a user -------------------->

function confirmDisableUser(id) {
  let deleteId = id;
	$('#areyouSureYouWantToDetele').modal('show');
	$('#confirmDeleteBtn').click(function() {
		disableUser(deleteId);
	});
}

function disableUser(id){
		fetch(`user/enable/${id}`, {
		method: 'POST',
	})
	.then( response => {	

   			 if (response.status === 200) {
       			sendMessage('Succes/Success', 1);
			return loadPage("user/list-users");				
   			 } else if (response.status !== 200) {
				sendMessage('Failed / Echec', 2);
  			 }
		})
		 .then(function(data) {

		 })
			.catch(function(error) {

			});
}

//<------------------ Get list users with pagination -------------------->

