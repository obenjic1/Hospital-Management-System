/**
 * 
 */
 function loadTable(page) {
	fetch(page)
		.then(response => response.text())
		.then(html => {
			document.getElementById("table-content").innerHTML = html;
		})
		.catch(error => console.log(error));
}

 function getInvoiceQuantity(id){
		loadMainModalForm(`job/generate/invoice/${id}`);
 }
 
  function findBycreationDate(){
	  let startDate = document.getElementById("search_startDate").value;
	  let endDate = document.getElementById("search_endDate").value;
	// let table =  document.getElementById("table-content");
	 
		loadTable(`invoice/find-by/${startDate}/${endDate}`);
 }
  function getInvoiceQuantityFromPricing(id){
		loadMainModalForm(`job/generate/invoice/${id}`);
 }
 
 
 
 function loadtaxsApply(url) {
	 console.log()
     fetch(url)
         .then(response => response.text())
         .then(html => {
             document.getElementById("load-taxs-applyed").innerHTML = html;
         })
         .catch(error => console.log(error));
 }
 /**
  *  Apply TAXES FORM
  */
 
 function applyTax(id){
	 let tvaCheckbox = document.getElementById("applyTva");
	 let tvaValue = document.getElementById("tvaValue");
	  let irTaxValue =document.getElementById("irValue");
	  let irTaxCheckbox = document.getElementById("applyIrTax");
	  let tvaVal = 0;
	  let irTaxVal = 0;
	 if(tvaCheckbox.checked) {
		tvaVal = tvaValue.value;
	 }
	 else {
		tvaVal = 0;
	 } 
	 if(irTaxCheckbox.checked) {
		irTaxVal = irTaxValue.value;
	 }else{
		 irTaxVal = 0;
	 }

	loadtaxsApply(`invoice/invoice-taxs/${id}/${irTaxVal}/${tvaVal}`);
 }
 
 
 /**
  *  Apply DISCOUNT FORM
  */
  function showDiscountImput(){
	 let tvaCheckbox = document.getElementById("applyDiscount");
	 if(tvaCheckbox.checked) {
		document.getElementById("discountDiv").style.display = "block";
	 }
	   else{
		   document.getElementById("discountDiv").style.display = "none";
	   }
 }
 
  function discountApply(url) {
	 console.log()
     fetch(url)
         .then(response => response.text())
         .then(html => {
             document.getElementById("load-discount-applyed").innerHTML = html;
         })
         .catch(error => console.log(error));
 }
 
  function applyDiscount(id){
	  let chosen = document.getElementById("discountOption").value;
	 		
	 		let discountMessage =  document.getElementById("discountMessage");
	  let discount =0;
	  if(chosen ==1){
		  	 discount  = document.getElementById("discountValue").value;
		  		if(discount>100){
				 discountMessage.style.display = "block";
				
				 return;
			  }
			  else{	  
					discountApply(`invoice/invoice-discount/${id}/${discount}`);
					 document.getElementById("discountDiv").style.display = "none";
		}
		
	  }
	else{
			discount = document.getElementById("discountAmount").value;
			discountApply(`invoice/invoice-discount-amount/${id}/${discount}`);
			document.getElementById("discountDiv").style.display = "none";
	}

 }
 displayTax
 
  function displayTax(id){
	 let tvaCheckbox = document.getElementById("applyTva");
	 let tvaValue = document.getElementById("tvaValue");
	  let irTaxValue =document.getElementById("irValue");
	  let irTaxCheckbox = document.getElementById("applyIrTax");
	  let tvaVal = 0;
	  let irTaxVal = 0;
	 if(tvaCheckbox.checked) {
		tvaVal = tvaValue.value;
	 }
	 else {
		tvaVal = 0;
	 } 
	 if(irTaxCheckbox.checked) {
		irTaxVal = irTaxValue.value;
	 }else{
		 irTaxVal = 0;
	 }

	loadtaxsApply(`invoice/display-taxs/${id}/${irTaxVal}/${tvaVal}`);
 }
 
 function getInvoiceFromPricing(id){
	 fetch(`invoice/generate/invoice/${id}`, {
			method: 'GET',
			 headers: {
           "Content-Type": "application/json",
           },
		})
		.then( response => {	
   			 if (response.status===200) {
       			Swal.fire("Succes/Success!", "Genreted successfully!/Genere avec succes!", "success")
			return loadPage('invoice/list');				
   			 } else if (response.status !== 200) {
				Swal.fire({  icon: "error", title: "Oops...", text: "Something went wrong!"});
				return loadPage('invoice/list');
  			 }
		})
		 .then(function(data) {

		 })
			.catch(function(error) {

			});
 }
 
function getCommissionInvoice(id,qty){
 loadMainModalForm(`invoice/commission-invoice/${id}/${qty}`);
}

function getDiscountInvoiceFromPricing(id,qty){
	 fetch(`invoice/generateDiscount/invoice/${id}/${qty}`, {
			method: 'GET',
			 headers: {
           "Content-Type": "application/json",
           },
		})
		.then( response => {	
   			 if (response.status===200) {
       			Swal.fire("Succes/Success!", "Genreted successfully!/Genere avec succes!", "success")
			return loadPage('invoice/list');				
   			 } else if (response.status !== 200) {
				Swal.fire({  icon: "error", title: "Oops...", text: "Something went wrong!"});
				return loadPage('invoice/list');
  			 }
		})
			.catch(function(error) {

			});
 }


