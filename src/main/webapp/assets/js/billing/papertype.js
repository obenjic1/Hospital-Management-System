
//<---------------- Save paper type------------------------>
function save(){
	var name = document.getElementById('name').value;
	
	var data = {
		name: name,
	};
	
	var jsonData = JSON.stringify(data);
	fetch('papertype/save', {
		method: 'POST',
		headers: {
           'Content-type': 'application/json'
         },
        body: jsonData
	})
	.then(response => {
		if (response.status === 200) {
					Swal.fire("Success!/Success!", "Saved successfully!", "success");
  			 } else if (response.status !== 200) {
				   Swal.fire({icon: "error", title: "Oops...", text: "Something went wrong!"});
  			 }
		
	})
	.catch(function(response){
		console.log('Erreur ahahaha')
	})
}

			/*
			
				** End Save Paper Type Section
				
			*/
			
//			------------------------------------

			/*
			
				** Start Update Paper Type Function 
				
			*/

//<---------------- Update paper type ------------------------>
function update(id){
	var name = document.getElementById('name').value;
	
	var data = {
		name: name
	};
	var jsonUpdateData = JSON.stringify(data)
	
	fetch(`papertype/update/${id}`, {
		method: 'post',
		headers: {
			'Content-type': 'application/json'
		},
		body: jsonUpdateData
	})
	.then(response => {
	if (response.status === 200) {
			Swal.fire("Success!/Success!", "Updated successfully!", "success");
		 } else if (response.status !== 200) {
			Swal.fire({icon: "error", title: "Oops...", text: "Something went wrong!"});
		 }
   })
   .then(data => {
      loadPage('papertype/list');
    })
	.catch(error => {
		
    });
}

























