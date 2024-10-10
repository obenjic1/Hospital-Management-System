/**
 * 
 */

function loadTables(url, idContainer) {
	 console.log()
     fetch(url)
         .then(response => response.text())
         .then(html => {
             document.getElementById(idContainer).innerHTML = html;
         })
         .catch(error => console.log(error));
 }

 function showCommisionInput(){
	 let checkbox = document.getElementById("applyCommision");
	 if(checkbox.checked) {
		document.getElementById("commisionDiv").style.display = "block";
	 }
	   else{
		   document.getElementById("commisionDiv").style.display = "none";
	   }
 }
 
 
 function applyCommission(id){
	 let commissionValue = document.getElementById("commision").value;
	 if(commissionValue<1){commissionValue=0;}
	 let discountValue =document.getElementById("discount").value;
	  if(discountValue<1){ discountValue=100;}
		 loadMainModalForm(`job/estimate/commission/${id}/${commissionValue}/${discountValue}`);
	 

 }
 
 function showTvaInput(){
	 let tvaCheckbox = document.getElementById("applyTva");
	 if(tvaCheckbox.checked) {
		document.getElementById("tvaDiv").style.display = "block";
	 }
	   else{
		   document.getElementById("tvaDiv").style.display = "none";
	   }
 }
   function showIrInput(){
	 let irTaxCheckbox = document.getElementById("applyIrTax");
	 if(irTaxCheckbox.checked) {
		document.getElementById("irDiv").style.display = "block";
	 }
	   else{
		   document.getElementById("irDiv").style.display = "none";
	   }

 }
 

 function proofreadByTheCustomer(id){
	 fetch(`job/proofread/${id}`, {
		method: 'POST',
	})
	.then( response => {	
			 loadPage("job/list-job");
		})
			.catch(function(error) {

			});
 }
 

 
 
  function confirmApprove(id) {
	$('#areyouSureYouWantToApprove').modal('show');
	$('#approveBtn').click(function() {
		approveJob(id);
	});
	}
  function showDiscountInput(){
	 let checkbox = document.getElementById("applyDiscount");
	 if(checkbox.checked) {
		document.getElementById("discountDiv").style.display = "block";
	 }
	   else{
		   document.getElementById("discountDiv").style.display = "none";
	   }
 }

 function showDiscountPercentageOrValueInput(){
 	var chosen = document.getElementById("discountOption").value;
 	var percentage_input = document.getElementById("discountValue");
 	var amount_input = document.getElementById("discountAmount");
 	if(chosen==1){
		 percentage_input.style.display="block"
		 amount_input.style.display="none";
	 }
 	else{
		 percentage_input.style.display="none"
		 amount_input.style.display="block";
		 percentage_input.style.display="none"

	 }
 }
 
function movementHistory(){
			let history_checkbox= document.getElementById("history-ckeckbox");
			let history_div =document.getElementById("history_div");
				if(history_checkbox.checked){
				history_div.style.display="block";
	} else {
		history_div.style.display="none";
	}
	}

	
	function saveJobType(id){
		
		let name = document.getElementById('name').value;
		let category = document.getElementById('category').value;
	//	let id = id;
		var formData = new FormData();
		
		formData.append('name', name),
		formData.append('id', id),
		formData.append('category', category),
		fetch('jobtype/add-jobType', {
			method: 'POST',
			body: formData,
		}).then( response => {	
   			 if (response.ok) {
       			Swal.fire("Succes/Success!", "Job saved successfully!", "success")
				return loadPage("jobtype/list-job-type");				
   			 } else if (response.status !== 200) {
				Swal.fire({  icon: "error", title: "Oops...", text: "Something went wrong!"});
				return loadPage("jobtype/list-job-type");
  			 }
		})
		 .then(function(data) {

		 })
			.catch(function(error) {

			});
	}
	
	function dashBoard(){
		loadPage('jobtracking/profile')
	}
	window.onload = dashBoard;
     