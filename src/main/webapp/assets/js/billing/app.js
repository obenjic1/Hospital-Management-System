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
 
  function showDiscountInput(){
	 let checkbox = document.getElementById("applyDiscount");
	 if(checkbox.checked) {
		document.getElementById("discountDiv").style.display = "block";
	 }
	   else{
		   document.getElementById("discountDiv").style.display = "none";
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
 
 function moveJob(id){
	 var department = document.getElementById('department').value;
	 var description = document.getElementById('description').value;
	var job = {
		department :department,
		description: description,
		}
		
		fetch(`job/move-job/${id}`, {
			method: 'POST',
			body: JSON.stringify(job) ,
			 headers: {
           "Content-Type": "application/json",
           },
		})
		.then( response => {	

   			 if (response.status === 200) {
       			sendMessage('Succes/Success', 1);
			return loadPage("job/list-job");				
   			 } else if (response.status !== 200) {
				sendMessage('Failed / Echec', 2);
  			 }
		})
		 .then(function(data) {

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
 

 
