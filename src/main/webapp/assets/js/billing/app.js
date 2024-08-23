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
 
<<<<<<< HEAD
 
=======
 function proofreadByTheCustomer(id){
	 fetch(`job/proofread/${id}`, {
		method: 'POST',
	})
	.then( response => {	
			 loadPage("job/list-job");
		})
			.catch(function(error) {
>>>>>>> f683e9535ebcb4ce0eb53cd2bb264c3785f5d1f7

			});
 }
 
