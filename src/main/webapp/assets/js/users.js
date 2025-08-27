
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

			/*
			
				** End Load Pages Sections 
			
			*/
			
	//			-------------------------
			
			/*
			
				** Start Add User Section
				
			*/


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

function addUser() {
	const staff = document.getElementById('staff').value;
	const password = document.getElementById("password").value;
	const confirmPassword = document.getElementById("confirmPassword").value;
	const username = document.getElementById('username').value;
	const groupe = document.getElementById('groupe').value;
	const imageFile = document.getElementById('imageFile').files[0];
//	var thumbnailInput = document.getElementById('imageFile');
//	var thumbnailFile = thumbnailInput.files.length > 0 ? thumbnailInput.files[0] : null;

	
	 if(username==""){
	customAlert('Please enter your Username!');
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
		formData.append('staff', staff),
		formData.append('username', username),
		formData.append('password', password),
		formData.append('confirmPassword', confirmPassword),
		formData.append('groupe', groupe);
		if (imageFile) {
		    formData.append('imageFile', imageFile);
		}

		fetch('user/add-user', {
			method: 'POST',
			body: formData,
		})
			.then( response => {	
   			 if (response.status === 200) {
//       			sendMessage('Succes/Success', 1);
				Swal.fire("Succes/Success!", "You clicked the button!", "success")
				let ExtralargeModal = document.getElementById('ExtralargeModal');
				let modal = bootstrap.Modal.getInstance(ExtralargeModal);
				modal.hide();
				return loadPage('user/list-users');
   			 } else if (response.status !== 200) {
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




			
function updateUserById(id) {
	
			const firstName = document.getElementById('firstName').value;
			const lastName = document.getElementById('lastName').value;
			const email = document.getElementById("email").value;
			const mobile = document.getElementById('mobile').value;
			const address = document.getElementById('address').value;
			const department = document.getElementById('department').value;
			const imageFile = document.getElementById('imageFile').files[0];
	
		
		var formData = new FormData();
		 formData.append('firstName',firstName);
		 formData.append('lastName',lastName);
		 formData.append('email',email);
		 formData.append('mobile',mobile);
		 formData.append('address',address);
		 formData.append('department',department);
		 if(imageFile){
			 formData.append('imageFile',imageFile);
		 }
		
console.log(formData);

		fetch(`user/update-user/${id}`, {
			method: 'POST',
			body: formData,

		})
			.then( response => {	
   			 if (response.status === 200) {
////       			sendMessage('Succes/Success', 1);
				Swal.fire("Succes/Success!", "You clicked the button!", "success")
				return loadPage('user/list-users');
   			 } else if (response.status !== 200) {
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
			

		
function confirmDisableUser(id) {
  let deleteId = id;
		  Swal.fire({
			  title: "Are you sure?",
			  text: "You're about to desable a user!",
			  icon: "warning",
			  showCancelButton: true,
			  confirmButtonColor: "#3085d6",
			  cancelButtonColor: "#d33",
			  confirmButtonText: "Yes, disable it!"
			}).then((result) => {
			  if (result.isConfirmed) {
				  disableUser(deleteId);
				  loadPage('user/list-users')
			    
			  }else{
				 Swal.fire("Cancelled/Annulee!", "Operation cancelled", "info");
			  }
			});

}

function disableUser(id){
		fetch(`user/enable/${id}`, {
		method: 'POST',
	})
	.then( response => {	

   			 if (response.status === 200) {
			Swal.fire({title: "Deleted!", text: "Disabled successfully!", icon: "success" });
   			 } else if (response.status !== 200) {
				Swal.fire({icon: "error", title: "Oops...", text: "Something went wrong!"});
  			 }
		})
		 .then(function(data) {

		 })
			.catch(function(error) {

			});
}

		/*
		
		  ** End  Disable Uer Section
		  
		*/

function resetPassword(){
	 const username = document.getElementById("pusername").value;
	const newpassword = document.getElementById("newpassword").value;
	const oldpassword = document.getElementById("rpassword").value;
	const confirmPassword = document.getElementById("rconfirmPassword").value;
	const id = document.getElementById('rid').value;
	const imageFile = document.getElementById('rimageFile').files[0];
	
	if(oldpassword==""){
		customAlert('Please enter your old Password!');
		return;
		} 
		
		if(newpassword==""){
		customAlert('Please enter your new Password!');
		return;
		} 
		
		 if (confirmPassword =="") {
			customAlert('Please enter the confirm password!');
			return;
		}

	 if(confirmPassword != newpassword){
		customAlert('The password fields should be the same');
		return;}
	var formData = new FormData();
			formData.append('id', id);
			formData.append('oldpassword', oldpassword);
			formData.append('newpassword', newpassword);
			
			if (imageFile) {
			    formData.append('imageFile', imageFile);
			}	
			fetch('user/update-password', {
					method: 'POST',
					body: formData,
				})
					.then( response => {	
					
		   			 if (response.ok) {
							console.log(response);
		//       			sendMessage('Succes/Success', 1);
						Swal.fire("Succes/Success!", "Password Successfully Changed!", "success")
						
						return loadPage(`user/viewUser/${username}`);
		   			 } else if (response.status !== 200) {
							Swal.fire({icon: "error", title: "Oops...", text: "Either your  current password didnt match or something went wrong try again"});
		//				sendMessage('Failed / Echec : Email or Username already exist ',2);
						
						//	return loadPage('user/add-user');			
		
		  			 }
				})
				 .then(function(data) {
		
				 })
					.catch(function(error) {
		
					});
	
}
