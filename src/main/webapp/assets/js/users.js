
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
// <-------------- Save user ------------------------->
function addUser() {
	const firstName = document.getElementById('firstName').value;
	const lastName = document.getElementById('lastName').value;
	const email = document.getElementById("email").value;
	const password = document.getElementById("password").value;
	const mobile = document.getElementById('mobile').value;
	const address = document.getElementById('address').value;
	const username = document.getElementById('username').value;
	const groupe = document.getElementById('groupe').value;
	const imageFile = document.getElementById('imageFile').files[0];

	if (!validateField(firstName, 'emptyNameAlert')) {
		return false;
	}

	if (!validateField(lastName, 'emptyLastNameAlert')) {
		return false;
	}

	if (!validateField(email, 'emptyEmailAlert')) {
		return false;
	}

	if (!validateField(address, 'emptyAddressAlert')) {
		return false;
	}

	if (!validateField(username, 'emptyUserameAlert')) {
		return false;
	}

	if (!validateField(mobile, 'emptyMobileAlert')) {
		return false;
	}

	if (!validateField(password, 'emptyPasswordAlert')) {
		return false;
	}

	var formData = new FormData();
		formData.append('firstName', firstName),
		formData.append('lastName', lastName),
		formData.append('email', email),
		formData.append('mobile', mobile),
		formData.append('username', username),
		formData.append('password', password),
		formData.append('address', address),
		formData.append('groupe', groupe),
		formData.append('imageFile', imageFile),

		fetch('user/add-user', {
			method: 'POST',
			body: formData,
		})
			.then( response => {	

   			 if (response.status === 200) {
       			sendMessage('Succes/Success', 1);
			return loadPage("user/add-user");				
   			 } else if (response.status !== 200) {
				sendMessage('Failed / Echec', 2);
  			 }
		})
		 .then(function(data) {

		 })
			.catch(function(error) {

			});
}

let bouton = document.getElementById("create-btn");

bouton.removeAttribute("onclick");
bouton.addEventListener("click", function(event) {
  event.preventDefault();

  addUser();
});

var modal = new bootstrap.Modal(document.getElementById('userDeleteSuccessfully'));
				modal.show();

//<------------------ Update user using DTO object -------------------->
function updateUserById(id) {
	
	const firstName = document.getElementById('firstName').value;
	const lastName = document.getElementById('lastName').value;
	const email = document.getElementById("email").value;
	const mobile = document.getElementById('mobile').value;
	const address = document.getElementById('address').value;
	const username = document.getElementById('username').value;
//	const groupe = document.getElementById('groupe').value;
	const imageFile = document.getElementById('imageFile').files[0];

var formData = {
				
				firstName:firstName,
				lastName:lastName,
				email:email,
				mobile:mobile,
				username:username,
				address:address,
//				groupe:groupe,
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




//document.addEventListener('DOMContentLoaded', function() {
//	var deleteBtn = document.getElementById('delete-btn');
//	deleteBtn.addEventListener('click', function() {
//
//	});
//});



//<------------------ Get list users with pagination -------------------->

