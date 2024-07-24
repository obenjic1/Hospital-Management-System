// Fonction pour charger une page via une requête fetch
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
			document.getElementById('modC').innerHTML = html;
			document.getElementById('updateGroup').innerHTML = html;
		})
		.catch(error => console.log(error));
}

//<---------------- Function to create a group ------------------------>
function addGroupe() {
    var name = document.getElementById('name').value;
    var description = document.getElementById('description').value;
    var ids = Array.from(document.querySelectorAll('input[type="checkbox"]:checked'))
        .map(function(checkbox) {
            return checkbox.value;
        });

    var data = {
        name: name,
        description: description,
        ids: ids
    };

    var stringData = JSON.stringify(data);

    fetch('group/add-group', {
            method: "POST",
            headers: {
                'Content-type': 'application/json'
            },
            body: stringData
        })
        .then( response => {	

   			 if (response.status === 200) {
       			sendMessage('Succes/Success', 1);
			return loadPage("group/list-groups");				
   			 } else if (response.status !== 200) {
				sendMessage('Failed : email or user name already exist ', 2);
  			 }
		})
		 .then(function(data) {

		 })
			.catch(function(error) {

			});
}


//<-------------------- Update Group --------------------->
function updateGroupe(name) {
   var updatedName = document.getElementById('name').value;
   var description = document.getElementById('description').value;
   var ids = Array.from(document.querySelectorAll('input[type="checkbox"]:checked'))
      .map(function(checkbox) {
         return checkbox.value;
      });

   var updatedGroupValue = {
      name: updatedName,
      description: description,
      ids: ids
   };
   var jsonGroupUpdatedValue = JSON.stringify(updatedGroupValue);
   
   fetch(`group/update-group/${name}`, {
      method: "POST",
      headers: {
         'Content-type': 'application/json'
      },
      body: jsonGroupUpdatedValue
   })
      .then( response => {	

   			 if (response.status === 200) {
       			sendMessage('Succes/Success', 1);
			return loadPage("group/list-groups");				
   			 } else if (response.status !== 200) {
				sendMessage('Failed / Echec', 2);
  			 }
		})
		 .then(function(data) {

		 })
			.catch(function(error) {

			});
}

// <------------ Delete an confirm delete section --------------------->

function confirmDisableGroupe(id) {
  let deleteId = id;
	$('#areyouSureYouWantToDetele').modal('show');
	$('#confirmDeleteBtn').click(function() {
		disableGroup(deleteId);
	});
}

function disableGroup(id){
	fetch(`group/disable-group/${id}`, {
		method: 'POST',
	})
	.then( response => {	

   			 if (response.status === 200) {
       			sendMessage('Succes/Success', 1);
			return loadPage("group/list-groups");				
   			 } else if (response.status !== 200) {
				sendMessage('Failed / Echec', 2);
  			 }
		})
		 .then(function(data) {

		 })
			.catch(function(error) {

			});
}

function refreshGroupTable(pageNo) {
    $.ajax({
        url: 'group/page/' + pageNo,
        type: 'GET',
        success: function(data) {
            $('#pagination-list').html(data);
        },
        error: function() {
            alert('Une erreur s\'est produite lors du chargement de la page.');
        }
    });
}


$(document).ready( function () {
    $('#groupDataTable').DataTable({});
} );