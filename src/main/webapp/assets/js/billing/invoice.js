/**
 * 
 */

 function getInvoiceQuantity(id){
	 fetch(`job/generate/invoice/${id}`, {
		 method: 'GET',
		 headers: {
			'Content-type': 'application/json'
		},
	})
		loadMainModalForm(`job/generate/invoice/${id}`);
//		.then(respose => {
//			if (respose.ok) {
//			
//			} else {
//
//			}
//		})
//		.catch(error => {
//			console.error("internal server error :", error);
//		})
 }