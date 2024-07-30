/**
 * 
 */

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
	 if(commissionValue<1){commissionValue=1;}
	 let discountValue =document.getElementById("discount").value;
	  if(discountValue<1){
		   discountValue=1;
		  }
		 
	 loadMainModalForm(`job/estimate/commission/${id}/${commissionValue}/${discountValue}`);

	 
 }
 