
// function to Add  a new Machine

function addMachine() {
	const name = document.getElementById('name').value;
	const abbreviation = document.getElementById('abbreviation').value;
	const plateLength = document.getElementById("plateLength").value;
	const plateWidth = document.getElementById("plateWidth").value;
	const isActive = document.getElementById('isActive').value;
	const thumbnail = document.getElementById('thumbnail').files[0];
	
	var formData = new FormData();
		formData.append('name', name),
		formData.append('abbreviation', abbreviation),
		formData.append('plateLength', plateLength),
		formData.append('plateWidth', plateWidth),
		formData.append('isActive', isActive),
		formData.append('imageFile', thumbnail),

		fetch('machine/add-machine', {
			method: 'POST',
			body: formData,
		})
			.then(function(response) {
				if (response.status === 200) {
					var modal = new bootstrap.Modal(document.getElementById('userUdatedSuccessfully'));
					modal.show();
				} 
			})
			.then(function(data) {
			
			})
			.catch(function(error) {
				
			});console.log(isActive);
			console.log(formData);
				loadPage('machine/list');
}

function deleteMachine(id) {
	deleteId = id;
	$('#areyouSureYouWantToDetele').modal('show');

	$('#confirmDeleteBtn').click(function() {
		removeM(deleteId);
	});
}
var modal = new bootstrap.Modal(document.getElementById('userDeleteSuccessfully'));
				modal.show();
				
				
var bouton = document.getElementById("create-btn");

bouton.removeAttribute("onclick");
bouton.addEventListener("click", function(event) {
  event.preventDefault();
  addMachine();
});
				
// <------------ Delete User using soft delete --------------------->

function removeMachine(id) {
	fetch(`machine/switchCase/${id}`, {
		method: 'POST',
		headers: {
			'Content-type': 'application/json'
		},
	})
		.then( response => {	

   			 if (response.ok) {
       			sendMessage('Succes/Success', 1);
				return loadPage('machine/list'); 
  			 } else if (response.status !== 200) {
				sendMessage('Failed / Echec', 2);
  			 }
		})
		 .then(function(data) {

		 })
			.catch(function(error) {
			});
}

function updateMachine(id) {
	const name = document.getElementById('name').value;
	const abbreviation = document.getElementById('abbreviation').value;
	const plateLength = document.getElementById("plateLength").value;
	const plateWidth = document.getElementById("plateWidth").value;
//	const isActive = document.getElementById('isActive').value;
	
	var formData = {
		name:name,
		abbreviation:abbreviation,
		plateLength:plateLength,
		plateWidth:plateWidth
//		isActive:isActive
	};
		var jsonUpdatedData = JSON.stringify(formData);

		fetch(`machine/update/${id}`, {
		method: 'post',
		headers: {
			'Content-Type': 'application/json'
		},
      	 body: jsonUpdatedData,
	})
			.then( response => {	

   			 if (response.status === 200) {
       			sendMessage('Succes/Success', 1);
				return loadPage('machine/list'); 
  			 } else if (response.status !== 200) {
				sendMessage('Failed / Echec', 2);
  			 }
		})
		 .then(function(data) {

		 })
			.catch(function(error) {
			});
}

function showConfirmationModal(action, message, icon, callback) {
  const modal = document.getElementById('confirmation-modal');
  const modalMessage = document.getElementById('modal-message');
  const modalIcon = document.getElementById('modal-icon');
  const confirmButton = document.getElementById('confirm-button');
  const cancelButton = document.getElementById('cancel-button');

  modalMessage.textContent = message;
  modalIcon.src = icon;

  confirmButton.addEventListener('click', () => {
    modal.style.display = 'none';
    callback();
  });

  cancelButton.addEventListener('click', () => {
    modal.style.display = 'none';
  });

  modal.style.display = 'flex';
}


const deleteButton = document.getElementById('startDeleting1');
deleteButton.addEventListener('click', function() {
showConfirmationModal('delete', 'Are you sure you want to delete?', () => {
 removeMachine(id);
});});
	