/**
 * 
 * 
 *  


 async function loadRevenue() {
            let date = document.getElementById("date").value;
            
            let response = await fetch(`/queenmary/reports/revenue/${date}`);
            
            let data = await response.json();
            
            document.getElementById("totalRevenue").innerText = data.totalRevenue;
        }

		*/
		
		
		
		
function loadRevenue(){
	var date = document.getElementById("seachDate").value;
loadPage(`reports/revenue/${date}`);

}

function filter(){
	var startDate = document.getElementById("startDate").value;
	var endDate = document.getElementById("endDate").value;


loadPage(`reports/sales/${startDate}/${endDate}`);

}