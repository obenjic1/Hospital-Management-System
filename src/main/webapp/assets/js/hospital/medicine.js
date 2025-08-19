
function addCategory(){
	var catName = document.getElementById("categoryName").value;
	var catDescription = document.getElementById("categoryDesc").value;
	var urlConfirm=  "/store/add-category?name=" + catName + "&description=" +catDescription;
	
	fetch(urlConfirm, {
			method: 'POST',
		})
			.then(function(response) {
				if (response.ok) {
					Swal.fire("Success!/Success!", "Added successfully!", "success");
					document.getElementById("close-btn").click();
					loadPage('store');
					
				}else{
					 Swal.fire({icon: "error", title: "Oops...", text: "Something went wrong!"});
				} 
			})
			.then(function(data) {
			
			})
			.catch(function(error) {
				
			});
				loadPage('store');
}

function saveMedicine(){
	var name = document.getElementById("medicineName").value;
	var category = document.getElementById("medCategory").value;
	var quantity = document.getElementById("quantity").value;
	var price 	 = document.getElementById("price").value;
	var threshold = document.getElementById("threshold").value;
	var expiringDate = document.getElementById("expiryDate").value;
	var description = document.getElementById("description").value;

	var formData = new FormData();
		formData.append('name', name),
		formData.append('category', category),
		formData.append('quantity', quantity),
		formData.append('price', price),
		formData.append('threshold', threshold),
		formData.append('expiringDate', expiringDate),
		formData.append('description', description),


		fetch('store/add', {
			method: 'POST',
			body: formData,
		})
			.then(function(response) {
				if (response.ok) {
					Swal.fire("Success!/Success!", "Added successfully!", "success");
					document.getElementById("add-close").click();
					loadPage('store');
				}else{
					 Swal.fire({icon: "error", title: "Oops...", text: "Something went wrong!"});
				} 
			})
			.then(function(data) {
			
			})
			.catch(function(error) {
				
			});
				loadPage('store');
	
}
	

function searchMedicine(){
	var q = document.getElementById("searchBoxer").value;
	var category = document.getElementById("category").value;
	const url = "store/?category="+category +"&q="+q;
	
	loadPage(url);

}


function updateMedicine(id){
	var name = document.getElementById("nameE").value;
	var category = document.getElementById("medCategoryE").value;
	var quantity = document.getElementById("quantityE").value;
	var price 	 = document.getElementById("priceE").value;
	var threshold = document.getElementById("thresholdE").value;
	var expirationDate = document.getElementById("expiryDateE").value;

	var formData = new FormData();
		formData.append('name', name),
		formData.append('category', category),
		formData.append('quantity', quantity),
		formData.append('price', price),
		formData.append('threshold', threshold),
		formData.append('expirationDate', expirationDate),


		fetch(`store/edit/${id}`, {
			method: 'POST',
			body: formData,
		})
			.then(function(response) {
				if (response.ok) {
					Swal.fire("Success!/Success!", "Updated successfully!", "success");
					document.getElementById("add-close").click();
					loadPage('store');
				}else{
					 Swal.fire({icon: "error", title: "Oops...", text: "Something went wrong!"});
				} 
			})
		
			.catch(function(error) {
				
			});
				loadPage('store');
	
}
	
function addQuantity(medicineId){

    const quantity = document.getElementById(`qty-${medicineId}`).value;	

	var urlConfirm="/store/add-quantity?medicineId="+ medicineId +"&quantity="+quantity ;
	
	fetch(urlConfirm, {
		method: 'POST',
	})
		.then(function(response) {
			console.log(response);
			if (response.ok) {
				document.getElementById(`qty-${medicineId}`).value= "";	
				Swal.fire("Success!/Success!", "Medicine successfully!", "success");
				loadPage('store');

			}else{
				 Swal.fire({icon: "error", title: "Oops...", text: response.message});
			} 
		})
		.then(function(data) {
		
		})
		.catch(function(error) {
			 
		});


}

//action="${pageContext.request.contextPath}/store/transfer" method="post";


function Transfer(medicineId,quantity){
	event.preventDefault();
	
    const quant = document.getElementById(`qtyi-${medicineId}`).value;	
    	if(quant==null){
		Swal.fire({icon: "error", title: "Oops...", text: "you have to fill a quantity"});
		return;
	}
	if(quant> quantity){
		Swal.fire({icon: "error", title: "Oops...", text: "can't transfer more that available quantity"});
		return;
	}

	var urlConfirm="/store/transfer?medicineId="+ medicineId +"&quantity="+quant ;
	
	
	fetch(urlConfirm, {
		method: 'POST',
	})
		.then(function(response) {
			console.log(response.bodydy);
			if (response.ok) {
				document.getElementById(`qty-${medicineId}`).value= "";	
				Swal.fire("Success!/Success!", "Medicine successfully!", "success");
				loadPage('store');

			}else{
				 Swal.fire({icon: "error", title: "Oops...", text: "choose a number"});
			} 
		})
		.then(function(data) {
		
		})
		.catch(function(error) {
			 
		});


}


function searchPharmacyMedicine(){
	
	var q = document.getElementById("searchBoxer").value;
	var category = document.getElementById("category").value;
	const url = "pharmacy/?category="+category +"&q="+q;
	
	loadPage(url);
}



function toogleRequestForm(id){
		  const div = document.getElementById(`medDiv-${id}`);
		  const buttonText = document.getElementById(`request-${id}`);
		  
		  
		  

            if (div.style.display === 'none' || div.style.display === '') {
                div.style.display = 'block';
                buttonText.textContent = "Cancel"
            } else {
                div.style.display = 'none';
                buttonText.textContent = "Request"

            }


	
}

function TransferToPharmacy(medicineId,quantity){
	event.preventDefault();
	
    const quant = document.getElementById(`qty-${medicineId}`).value;	
    	if(quant==null){
		Swal.fire({icon: "error", title: "Oops...", text: "you have to fill a quantity"});
		return;
	}
	if(quant> quantity){
		Swal.fire({icon: "error", title: "Oops...", text: "can't transfer more that available quantity"});
		return;
	}

	var urlConfirm="/pharmacy/transfer?medicineId="+ medicineId +"&quantity="+quant ;
	
	
	fetch(urlConfirm, {
		method: 'POST',
	})
		.then(function(response) {
			console.log(response.bodydy);
			if (response.ok) {
				document.getElementById(`qty-${medicineId}`).value= "";	
				Swal.fire("Success!/Success!", "Medicine successfully!", "success");
				loadPage('pharmacy');

			}else{
				 Swal.fire({icon: "error", title: "Oops...", text: "choose a number"});
			} 
		})
		.then(function(data) {
		
		})
		.catch(function(error) {
			 
		});


}


  let cart = [];


  function addToCart(id, name, price) {
    let existing = cart.find(item => item.id === id);
    if (existing) {
      existing.qty += 1;
    } else {
      cart.push({ id, name, price: parseFloat(price), qty: 1 });
    }
    renderCart();
  }





 // Render cart
  function renderCart() {
    const cartBody = document.getElementById("cartBody");
    cartBody.innerHTML = "";

    let total = 0;
    cart.forEach((item, index) => {
      let itemTotal = item.price * item.qty;
      total += itemTotal;

      cartBody.innerHTML += `
      
      
       <div class="cart-item">
            <div class=" d-flex justify-content-between align-items-center">
                    <div  class="mx-3">
                        <strong>${item.name} </strong><br>
                        <small>CFA ${item.price.toFixed(2)} each</small>
                    </div>
                    <div class="d-flex justify-content-between align-items-center">
                        <button class="btn btn-sm btn-outline-secondary" onclick="decreaseQty(${index})">-</button>
                         <input type="number" style="width:60px;margin:4px:text-align:center" min="1" value="${item.qty}" 
                   onchange="updateQty(${index}, this.value)">
                        <button class="btn btn-sm btn-outline-secondary" onclick="increaseQty(${index})>+</button>
                    </div>
                    <hr>
                  </div>
              <div>  Item Total Price :  ${itemTotal.toFixed(2)} </div>
                    
       </div>
                                 


      `;
    });

    document.getElementById("cartTotal").innerHTML = ` Grand Total :${total.toFixed(2)}`
    document.getElementById("cartTotalItems").innerHTML = ` Cart (${cart.length} items)`

  }
//                        <button class="btn btn-sm btn-danger ms-2" onclick="removeItem(${index})><i class="bi bi-trash"> X</i></button>


  function removeItem(index) {
    cart.splice(index, 1);
    renderCart();
  }


function updateQty(index, newQty) {
    cart[index].qty = parseInt(newQty);
    renderCart();
  }

 function increaseQty(index) {
    cart[index].qty += 1;
    renderCart();
  }

  function decreaseQty(index) {
    if (cart[index].qty > 1) {
      cart[index].qty -= 1;
    } else {
      removeItem(index);
    }
    renderCart();
  }





function checkout() {
    if (cart.length === 0) {
				 Swal.fire({icon: "error", title: "Oops...", text: "Carte is Empty"});
      return;
    }

    fetch("/sales/checkout", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(cart)
    })
		.then(function(response) {
			console.log(respose);
			
			if (response.status=="ok") {
			 Swal.fire({
			        icon: "success",
			        title: "Sale Successful!",
			        text: "Receipt Number: " + data.receiptNumber,
			        confirmButtonText: "View Receipt"
			    })

			}else{
				 Swal.fire({icon: "error", title: "Oops...", text: error});
			} 
		})
		.then(function(data) {
			console.log(data);
					cart = [];
		       		renderCart();
			        previewPdf(data.saleId);
			        loadPage("pharmacy");
		
		})
		.catch(function(error) {
			 
		});
			 
		}
    

 function previewPdf(saleId) {
        const pdfUrl = `/sales/receipt/${saleId}/pdf`;
        document.getElementById("controlSheetViewer").src = pdfUrl;

        // Show Bootstrap modal
        var myModal = new bootstrap.Modal(document.getElementById('ExtralargeModalFile'));
        myModal.show();
    }










//
// .then(res => res.json())
//			.then(function(response) {

















