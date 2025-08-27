
function loadPageModal(page) {
	fetch(page)
		.then(response => response.text())
		.then(html => {
			document.getElementById('modC').innerHTML = html;
			document.getElementById('updateGroup').innerHTML = html;
		})
		.catch(error => console.log(error));
}

	//               ---------------------
			/*
			
				** Start Add group Section 
				
			*/

function addGroupe() {
    var name = document.getElementById('name').value
//  
//	$('#areyouSureYouWantToDetele').modal('show');
//	$('#confirmDeleteBtn').click(function() {
//		
//	});alue;
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
					Swal.fire("Succes/Success!", "Group Created Succesfully!", "success")
					
       			//sendMessage('Succes/Success', 1);
       			let modal = bootstrap.Modal.getInstance(ExtralargeModal);
			    modal.hide();
				loadPage("group/list-groups");				
   			 } else if (response.status !== 200) {
					Swal.fire({icon: "error", title: "Oops...", text: "Something went wrong!"});
	//			sendMessage('Failed : email or user name already exist ', 2);
  			 }
		})
		 .then(function(data) {

		 })
			.catch(function(error) {

			});
}

			/*
			
				** End Add Groupe Section
				 
			*/
			
	//			------------------------------------

			/*
			
				** Start Update group Section
				
			*/

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
					Swal.fire("Succes/Success!", "Group Udpated Succesfully!", "success")
				let modal = bootstrap.Modal.getInstance(ExtralargeModal);
			    modal.hide();
				loadPage("group/list-groups");	
       		//	sendMessage('Succes/Success', 1);
		//	return ;				
   			 } else if (response.status !== 200) {
				 Swal.fire({icon: "error", title: "Oops...", text: "Something went wrong!"});
		//		sendMessage('Failed / Echec', 2);
  			 }
		})
		 .then(function(data) {

		 })
			.catch(function(error) {

			});
}

			/*
			
				** End Update Group Section
				
			*/
			
	//			--------------------------------

			/*
			
				** Start Disable Group Section
				
			*/

function confirmDisableGroupe(id) {
	let deleteId = id;
		Swal.fire({
			  title: "Are you sure?",
			  text: "You won't be able to revert this!",
			  icon: "warning",
			  showCancelButton: true,
			  confirmButtonColor: "#3085d6",
			  cancelButtonColor: "#d33",
			  confirmButtonText: "Yes, delete it!"
			}).then((result) => {
			  if (result.isConfirmed) {
				  disableGroup(deleteId);
			  }else{
				 Swal.fire("Cancelled!/Annulee!", "Operation cancelled", "info"); 
			  }
			});
	
}

function disableGroup(id){
	fetch(`group/disable-group/${id}`, {
		method: 'POST',
	})
	.then( response => {	

   			 if (response.status === 200) {
				Swal.fire({title: "Deleted!",text: "Group has been disable Successdully.", icon: "success" });
//       			sendMessage('Succes/Success', 1);
//			return loadPage("group/list-groups");				
   			 } else if (response.status !== 200) {
				Swal.fire({  icon: "error", title: "Oops...", text: "Something went wrong!"});
  			 }
		})
		 .then(function(data) {

		 })
			.catch(function(error) {

			});
}

			
			/*
			
				** End Disable Groupe Section 
				
			*/
			
	//			-------------------------------
			
			/*
			
				** Paginqtion Section
				
			*/

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