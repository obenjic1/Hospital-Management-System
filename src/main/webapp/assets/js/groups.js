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

//<---------------- Event listener to load group list ------------------>
document.getElementById('list-groups').addEventListener('click', function() {
    loadPage('/group/list-groups');
});

//<------------------ Event listener to create a group ----------------------->
document.getElementById('create-group').addEventListener('click', function() {
    addGroupe();
});

//<------------------ view group details ----------------------->
document.getElementById('group-details').addEventListener('click', function() {
    loadPage('/group/group-details/{name}');
});


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

    fetch('/group/add-group', {
            method: "POST",
            headers: {
                'Content-type': 'application/json'
            },
            body: stringData
        })
        .then(function(response) {
            if (response.ok) {
				loadPage('/group/list-groups')
                var modal = new bootstrap.Modal(document.getElementById('groupCreatedSuccessfuly'));
                modal.show();
            } else {
                var modal = new bootstrap.Modal(document.getElementById('groupNameAlreadyExist'));
                modal.show();
            }
        })
        .catch(function(error) {
            console.error('Something went wrong with the request:', error);
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
   
   fetch(`/group/update-group/${name}`, {
      method: "POST",
      headers: {
         'Content-type': 'application/json'
      },
      body: jsonGroupUpdatedValue
   })
      .then(function(response) {
         if (response.ok) {
			 loadPage('/group/list-groups')
            var modal = new bootstrap.Modal(document.getElementById('groupUdatedSuccessfully'));
            modal.show();
         } else {
			loadPage('/group/list-groups')
            var modal = new bootstrap.Modal(document.getElementById('somethingWhenWrong'));
            modal.show();
         }
      })
      .catch(function(error) {
         console.error('Something went wrong with the request:', error);
      });
}

//<-------------------- List group with paginations --------------------->
function refreshGroupTable(pageNo) {
    $.ajax({
        url: '/group/page/' + pageNo,
        type: 'GET',
        success: function(data) {
            $('#pagination-list').html(data);
        },
        error: function() {
            alert('Une erreur s\'est produite lors du chargement de la page.');
        }
    });
}

// <------------ Delete an confirm delete section --------------------->
var disableId;

function confirmDisableGroupe(id) {
	disableId = id;	
	$('#areyouSureYouWantToDisable').modal('show');
	$('#confirmDisabledBtn').click(function() {	
		disableGroup(disableId);
	});
}

function disableGroup(id){
	fetch(`/group/disable-group/${id}`, {
		method: 'POST',
	})
	.then(response => {
    if (response.ok) {
      var modal = new bootstrap.Modal(document.getElementById('groupdisabledSuccessfully'));
      modal.show();
    } else {
	  var modal = new bootstrap.Modal(document.getElementById('groupdisabledSuccessfully'));
      modal.show();
    }
  })
  .catch(error => {
    console.error(error);
  });
}

$(document).ready( function () {
    $('#groupDataTable').DataTable({});
} );