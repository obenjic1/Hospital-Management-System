
// function to Add  a new Machine

function addActivity() {
	const name = document.getElementById('name').value;
	var formData = new FormData();
		formData.append('name', name),
		
		fetch('/activity-option/add-activity', {
			method: 'POST',
			body: formData,
		})
			.then(function(response) {
				if (response.status === 200) {
					var modal = new bootstrap.Modal(document.getElementById('activityAdded'));
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

//var bouton = document.getElementById("");
//
//bouton.removeAttribute("onclick");
//bouton.addEventListener("click", function(event) {
//  event.preventDefault();

//  function to update job activity

function updateActivity() {
	const name = document.getElementById('name').value;
	var formData = new FormData();
		formData.append('name', name),
		
		fetch('/activity-option//update/{id}', {
			method: 'POST',
			body: formData,
		})
			.then(function(response) {
				if (response.status === 200) {
					var modal = new bootstrap.Modal(document.getElementById('activityAdded'));
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



// function to delete an activity
function removeActivity(id) {
	
	fetch(`activity-option/delete-activity/${id}`, {
		method: 'POST',
		headers: {
			'Content-type': 'application/json'
		},
	})
		.then(respose => {
			if (respose.ok) {
			var modal = new bootstrap.Modal(document.getElementById('somthingwhenwrong'));
			modal.show();
			loadPage('activity-option/list');
			
			} else {
				var modal = new bootstrap.Modal(document.getElementById('activityDeleteSuccessfully'));
				modal.show();
				loadPage('activity-option/list');
			}
		})
		.catch(error => {
			console.error("internal server error :", error);
		})
}
