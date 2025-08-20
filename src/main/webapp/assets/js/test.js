


/**
 * 
 */
//var model=document.getElementById("modal1")
function resetPassword(){
var modal = new bootstrap.Modal(document.getElementById('modal1'));
				modal.show();
}




function resetEmail(email){
	//var resetBtn = document.getElementById("resetB");
	fetch(`http://localhost:8000/user/resetPassword/$(email)`, {
  method: "POST",
  body: JSON.stringify({
    email: resetEmail,
   
  }),
  headers: {
    "Content-type": "application/json; charset=UTF-8"
  }
})
 .then((response) => response.json())
  .then((json) => console.log(json));;

	var resetEmail = document.getElementById("email").value;
	alert("email has been sent to " + resetEmail);
	
}
