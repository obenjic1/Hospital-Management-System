
function loadPage(page) {
	fetch(page)
		.then(response => response.text())
		.then(html => {
			document.getElementById('main-content').innerHTML = html;
		})
		.catch(error => console.log(error));
}

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
	.then(function(response){
		
	})
	.catch(function(response){
		console.log('Erreur ahahaha')
	})
}

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
		if(response.ok){
			return response.json();
		} else if (response.status === 400){
            throw new Error('Erreur : Données utilisateur invalides');
            
        } else {
            throw new Error('Erreur inattendue');
        }
   })
   .then(data => {
      loadPage('papertype/list');
    })
	.catch(error => {
		
    });
}

























