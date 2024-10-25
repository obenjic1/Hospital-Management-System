
function loadPageModal(page) {
	fetch(page)
		.then(response => response.text())
		.then(html => {
			
			fetch('file/download?'+ html).then(resp=> resp.blob()).then(blob=>{
			let file = window.URL.createObjectURL(blob);
			document.getElementById('controlSheetViewer').src=file;
			listJob();
			});
		})
		.catch(error => console.log(error));
}

function loadPageModalForm(page) {
	fetch(page)
		.then(response => response.text())
		.then(html => {
			document.getElementById('addForm').innerHTML = html;
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
		document.getElementById("messageImage").src = "billing/assets/img/success_icon.png"; 
	} else if (n == 2){
		document.getElementById("messageImage").src = "billing/assets/img/delete.jpg"; 
	} else {
		document.getElementById("messageImage").src = "billing/assets/img/delete.jpg"; 
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

 function loadDynamicPageModal(page) {
 	fetch(page)
 		.then(response => response.text())
 		.then(html => {
 			document.getElementById('addForm').innerHTML = html;
 		})
 		.catch(error => console.log(error));
 }

function closeModalView(modalId) {
    var modal = document.getElementById(modalId);
    modal.style.display="none";
 } 
 
 
 
  function loadMainModalForm(page) {
 	fetch(page)
 		.then(response => response.text())
 		.then(html => {
 			document.getElementById('getPage').innerHTML = html;
 		})
 		.catch(error => console.log(error));
 }
 
 
 
 function loadThisApplyDiscountCommissionDiv(url){
	      fetch(url)
         .then(response => response.text())
         .then(html => {
             document.getElementById("discount-table").innerHTML = html;
         })
         .catch(error => console.log(error));
 }
 
 
 function loadPageModalControlSheet(page) {
	fetch(page)
		.then(response => response.text())
		.then(html => {
			
			fetch('file/download?'+ html).then(resp=> resp.blob()).then(blob=>{
			let file = window.URL.createObjectURL(blob);
			document.getElementById('controlSheetViewer').src=file;
			listJob();
			});
		})
		.catch(error => console.log(error));
}
 
 
 
  function loadDynamicPageContentEstimate(url, idContainer) {
     fetch(url)
         .then(response => response.text())
         .then(html => {
             document.getElementById(idContainer).innerHTML = html;
        	listJob();
         })
         .catch(error => console.log(error));
 }
 
 
 
 
 
 