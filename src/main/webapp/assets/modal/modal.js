
function loadPageModal(page) {
	fetch(page)
		.then(response => response.text())
		.then(html => {
			
			console.log('localhost:8000/file/download?'+ html);
			fetch('http://localhost:8000/file/download?'+ html).then(resp=> resp.blob()).then(blob=>{
			let file = window.URL.createObjectURL(blob);
			document.getElementById('controlSheetViewer').src=file;
			});
		})
		.catch(error => console.log(error));
}

function showModal(){
	
	var modal = document.getElementById('ExtralargeModal');
	modal.style.display="block";
}

function closeModal() {
    var modal = document.getElementById("myModal");
    modal.style.display="none";
 }

 function fillContentModal(url){
	 
	 showModal();
	 loadPageModal(url);
	 
 }
 

 function sendMessage(msg, n){
	 var successMessage = document.getElementById('successMesssage');
	successMessage.innerText = msg;
	if (n == 1) {
		document.getElementById("messageImage").src = "assets/img/success_icon.png"; 
	} else if (n == 2){
		document.getElementById("messageImage").src = "assets/img/delete.jpg"; 
	} else {
		document.getElementById("messageImage").src = " "; 
	}
	
	function loadPdfToTheModal(modal){
		let display = document.getElementById('ExtralargeModal');
	}
	
	 
 }

 function loadDynamicPageContent(url, idContainer) {
     fetch(url)
         .then(response => response.text())
         .then(html => {
             document.getElementById(idContainer).innerHTML = html;
         })
         .catch(error => console.log(error));
 }