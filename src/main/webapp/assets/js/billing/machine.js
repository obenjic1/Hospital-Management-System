
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

		fetch('/machine/add-machine', {
			method: 'POST',
			body: formData,
		})
			.then(function(response) {
				if (response.status === 200) {
					var modal = new bootstrap.Modal(document.getElementById('verticalycentered'));
					modal.show();
				} 
			})
			.then(function(data) {

			})
			.catch(function(error) {
				var modal = new bootstrap.Modal(document.getElementById('somethingWhenWrong'));
				modal.show();
				
					
			});console.log(isActive);
				loadPage('machine/getListPage');
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
	

	fetch(`machine/delete-machine/${id}`, {
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
				
				
				var modal = new bootstrap.Modal(document.getElementById('machineModal'));
				modal.show();
				
				loadPage('machine/getListPage')
			}
		})
		.catch(error => {
			console.error("internal server error :", error);
		})
		
}

function updateMachine(id) {
	const name = document.getElementById('name').value;
	const abbreviation = document.getElementById('abbreviation').value;
	const plateLength = document.getElementById("plateLength").value;
	const plateWidth = document.getElementById("plateWidth").value;
	const isActive = document.getElementById('isActive').value;
	
	var formData = new FormData();
		formData.append('name', name),
		formData.append('abbreviation', abbreviation),
		formData.append('plateLength', plateLength),
		formData.append('plateWidth', plateWidth),
		formData.append('isActive', isActive),
		
		fetch(`machine/update/${id}`, {
			method: 'POST',
			body: formData,
		
		})
			.then(function(response) {
				if (response.status === 200) {
					var modal = new bootstrap.Modal(document.getElementById('verticalycentered'));
					modal.show();
				} 
			})
			.catch(function(error) {
				console.log('Oups')
			});
			loadPage('machine/getListPage');
}
