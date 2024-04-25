
function loadPageModal(page) {
	fetch(page)
		.then(response => response.text())
		.then(html => {
			document.getElementById('modC').innerHTML = html;
			document.getElementById('userUpdate').innerHTML = html;
			document.getElementById('addUser').innerHTML = html;
			document.getElementById('saveCustomer').innerHTML = html;
		})
		.catch(error => console.log(error));
}
