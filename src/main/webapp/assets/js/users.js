
//<---------------fontion load different function pages--------------------->
//alert("hello world")
function loadPage(page) {
	fetch(page)
		.then(response => response.text())
		.then(html => {
			document.getElementById('main-content').innerHTML = html;
		})
		.catch(error => console.log(error));
}



function loadView(url, div) {
	fetch(url)
		.then(response => response.text())
		.then(html => {
			document.getElementById(div).innerHTML = html;
		})
		.catch(error => console.log(error));
}

function loadPageModal(page) {
	fetch(page)
		.then(response => response.text())
		.then(html => {
			document.getElementById('modC').innerHTML = html;
			document.getElementById('userUpdate').innerHTML = html;
			document.getElementById('addUser').innerHTML = html;
		})
		.catch(error => console.log(error));
}

document.getElementById('users-list').addEventListener('click', function() {
	loadPage('/user/list-users');
});

document.getElementById('update-user').addEventListener('click', function() {
	loadPage('user/get-user/{username}');
});


document.getElementById('add-user').addEventListener('click', function() {
	addUser();
});


document.getElementById('user-created-sucessffully').addEventListener('click', function() {
	loadPage('/success');
});


// <--------------Create a new user ------------------------->
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

		fetch('/user/add-user', {
			method: 'POST',
			body: formData,a
		})
			.then(function(response) {
				if (response.status === 200) {
					var modal = new bootstrap.Modal(document.getElementById('verticalycentered'));
					modal.show();
				} else if (response.status === 500) {
					var modal = new bootstrap.Modal(document.getElementById('emailAlreadyExist'));
					modal.show();
				}
			})
			.then(function(data) {

			})
			.catch(function(error) {
				var modal = new bootstrap.Modal(document.getElementById('somethingWhenWrong'));
				modal.show();
			});
}

var bouton = document.getElementById("create-btn");

bouton.removeAttribute("onclick");
bouton.addEventListener("click", function(event) {
  event.preventDefault();

  addUser();
});

// <------------ Delete an confirm delete section --------------------->
var deleteId;

function confirmDelete(id) {
	deleteId = id;
	$('#areyouSureYouWantToDetele').modal('show');

	$('#confirmDeleteBtn').click(function() {
		removeUser(deleteId);
	});
}
var modal = new bootstrap.Modal(document.getElementById('userDeleteSuccessfully'));
				modal.show();
// <------------ Delete User using soft delete --------------------->
function removeUser(id) {
	fetch(`user/remove-user/${id}`, {
		method: 'POST',
		headers: {
			'Content-type': 'application/json'
		},
	})
		.then(respose => {
			if (respose.ok) {
				var modal = new bootstrap.Modal(document.getElementById('somthingwhenwrong'));
				modal.show();
			} else {
				var modal = new bootstrap.Modal(document.getElementById('userDeleteSuccessfully'));
				modal.show();

			}
		})
		.catch(error => {
			console.error("internal server error :", error);
		})
}
document.addEventListener('DOMContentLoaded', function() {
	var deleteBtn = document.getElementById('delete-btn');
	deleteBtn.addEventListener('click', function() {

	});
});

//<------------------ Update user using DTO object -------------------->
function updateUserById(id) {
	var fields = ['firstName', 'lastName', 'username', 'email', 'mobile', 'address'];
	var userUpdatedData = {};

	fields.forEach(function(field) {
		var value = document.getElementById(field).value;
		userUpdatedData[field] = value;
	});

	var jsonUserUpdateData = JSON.stringify(userUpdatedData);

	fetch(`/user/update-user/${id}`, {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: jsonUserUpdateData,
	})
		.then(response => {
			if (response.ok) {
				var modal = new bootstrap.Modal(document.getElementById('userUdatedSuccessfully'));
				modal.show();

			} else {
				throw new Error(`Erreur lors de la mise à jour de l'utilisateur (status ${response.status}).`);
			}
		})
		.catch(error => {
			var modal = new bootstrap.Modal(document.getElementById('userNotDeleted'));
			modal.show();
			loadPage('/user/list-users');
		});
}

//<------------------ Get list users with pagination -------------------->
function refreshUserTable(pageNo) {
	$.ajax({
		url: 'user/page/' + pageNo,
		type: 'GET',
		success: function(data) {
			$('#users-list').html(data);
		},
		error: function() {
			alert('Une erreur s\'est produite lors du chargement de la page.');
		}
	});
	
	
//<------------------ function to reset password  -------------------->

}


//<------------------ function to reset password  -------------------->

