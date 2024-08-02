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
	 document.getElementById("discountDiv").style.display = "none";
	 let discount = document.getElementById("discountValue").value;
	 discountApply(`invoice/invoice-discount/${id}/${discount}`);
	
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
 
 
 
