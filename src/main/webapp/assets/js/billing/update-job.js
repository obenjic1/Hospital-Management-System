	/*
	* Update Job function
	*/

function submitUpdateForm(id){
	 let opt=document.getElementById("jobType").selectedOptions[0];	
	 let dataContentValue = opt.parentElement.getAttribute('data-content');
 let job = {};
 job.customerId = document.getElementById("customer").value;
 job.jobTypeId = document.getElementById("jobType").value;
 job.title = document.getElementById("title").value;
 
 if(dataContentValue==2){
	  job.coverVolume = document.getElementById("volumeOfCover").value;
 }
    if(dataContentValue==0||dataContentValue==2){
	   job.contentVolume = document.getElementById("volumeOfContent").value;
 } 
 job.ctpFees = document.getElementById("ctpFees").value;
 job.openWidth = document.getElementById("openWidth").value;
 job.openLength = document.getElementById("openLength").value;
 if(dataContentValue==1 || dataContentValue==2){
	  job.closeWidth = document.getElementById("closeWidth").value;
 	  job.closeLength = document.getElementById("closeLength").value;
 }else{
	  job.closeWidth = document.getElementById("openWidth").value;
 	  job.closeLength = document.getElementById("openLength").value;
	 
 }

 job.existingPlate = document.getElementById("existingPlate").checked; 
 job.dataSuppliedByCustomer = document.getElementById("dataSuppliedByCustomer").checked; 
 job.layOutByUs = document.getElementById("layoutByUs").checked;
 job.typesettingByUs = document.getElementById("typesettingByUs").checked;
 
	let jobActivity = { };
	 jobActivity.xPerforated = document.getElementById("xPerforated").value;
	 jobActivity.xNumbered = document.getElementById("xNumbered").value;
	 jobActivity.lamination = document.getElementById("lamination").value
	 jobActivity.xCreased = document.getElementById("creased").value;
	 jobActivity.xWiredStiched = document.getElementById("xWire-stitched").value;
	 jobActivity.xCross = document.getElementById("xcross").value;
	// jobActivity.glueOption = document.getElementById("glueingOption").value;
	 jobActivity.bindingType = document.getElementById("bindingType").value;
	 jobActivity.handgather = document.getElementById("handgather").checked;
	 jobActivity.stitching = document.getElementById("stitching").checked;
	 jobActivity.trimmed = document.getElementById("trimmed").checked;  
	 jobActivity.selloptaped = document.getElementById("sellotaped").checked;
	 jobActivity.sewn = document.getElementById("sewn").checked;
	// jobActivity.handFoldCov = document.getElementById("handFoldCov").value;
  job.jobActivities = jobActivity;
 	
 	// Adding coverJobPaper and color combination  
 	
	 let jobPapers = [ ];
	 if(dataContentValue==2){
		 let coverJobPaper = { };
	  coverJobPaper.grammage = document.getElementById("coverGrammage").value;
	  coverJobPaper.volume = document.getElementById("coverVolume").value;
	  coverJobPaper.paperTypeId = document.getElementById("coverPaperType-to-update").value;
	  coverJobPaper.contentTypeId = 1;
	  coverJobPaper.paperUnitPrice = document.getElementById("coverPaperUnitPrice").value;
	  
	  let coverColorCombinations = [];
	  let colorCombination = {};
	   colorCombination.frontColorNumber = document.getElementById("coverFrontColorNumber").value;
	   colorCombination.backColorNumber = document.getElementById("converBackColorNumber").value;
	   colorCombination.printTypeId = document.getElementById("coverPrintType").value;
	   colorCombination.printingMachineId = document.getElementById("coverPrintingMachine").value;
	   colorCombination.signatureNumber = document.getElementById("coverSignature").value;
       coverColorCombinations.push(colorCombination);
       coverJobPaper.jobColorCombinations = coverColorCombinations;
       jobPapers.push(coverJobPaper);
 	}
	 
    //End of Adding CoverJobPaper and color combination   
    // Start adding contentJobPaper and color combination
    
    if(dataContentValue==1||dataContentValue==2 || dataContentValue==0|| dataContentValue==3){
		
     let contentPaperTypes = document.getElementById("contentDiv").children;
     let mainContentSignature = document.getElementById("mainContentSignature").children;
     
  
     for(let i = 1;  i < contentPaperTypes.length; i ++){

		 let contentJobPaper = { };
		 let currentRow = contentPaperTypes[i];
		 let paperType=currentRow.querySelector("[contentPaperType]").value;
		 let grammage = currentRow.querySelector("[contentGrammage]").value;
		 let volume = currentRow.querySelector("[contentVolume]").value;
		 let paperUnitPrice = currentRow.querySelector("[paperUnitPrice]").value;
		 contentJobPaper.grammage = grammage;
		 contentJobPaper.volume = volume;
		// contentJobPaper.paperTypeId = paperType;
		 contentJobPaper.paperUnitPrice=paperUnitPrice;
		 
		 let jobColorCombinations = [];
		 let colorConbination = {}; 
		 contentJobPaper.contentTypeId = 2;
		 let currentContentSignature = mainContentSignature[i];
		 colorConbination.signatureNumber = currentContentSignature.querySelector("[inputSignReadonly]").value; 
		 colorConbination.printingMachineId = currentContentSignature.querySelectorAll("[contentPrintingMachine]")[1].value;
		 colorConbination.printTypeId  = currentContentSignature.querySelectorAll("[contentPrintType]")[1].value;
		 colorConbination.frontColorNumber = currentContentSignature.querySelectorAll("[contentFrontColorNumber]")[1].value;
		 colorConbination.backColorNumber = currentContentSignature.querySelectorAll("[contentBackColorNumber]")[1].value;
		 jobColorCombinations.push(colorConbination);
		 
		 let mainContentSignatureChildren = currentContentSignature.children;
		 let k  = 2; if(i > 1) k = 3;
		 for(let j = k; j < mainContentSignatureChildren.length; j ++){
		 let colorConbination = {}; 
		 let currentContentSignature = mainContentSignatureChildren[j];
		 colorConbination.signatureNumber = currentContentSignature.querySelector("[delContentSign]").value;
		 colorConbination.printingMachineId = currentContentSignature.querySelector("[contentPrintingMachine]").value;
		 colorConbination.printTypeId  = currentContentSignature.querySelector("[contentPrintType]").value;
		 colorConbination.frontColorNumber = currentContentSignature.querySelector("[contentFrontColorNumber]").value;
		 colorConbination.backColorNumber = currentContentSignature.querySelector("[contentBackColorNumber]").value;
		 jobColorCombinations.push(colorConbination);
		 }
		 contentJobPaper.jobColorCombinations = jobColorCombinations;
		 jobPapers.push(contentJobPaper);
	 }
	 }
	  
	 job.jobPapers = jobPapers;
	 fetch(`job/update-job/${id}`, {
			method: 'POST',
			body: JSON.stringify(job) ,
			 headers: {
           "Content-Type": "application/json",
           },
		})
		.then( response => {	
   			 if (response.status === 200) {
       			Swal.fire("Succes/Success!", "Job Updated / Job mis a jour", "success")
   			 } else if (response.status) {
				Swal.fire({  icon: "error", title: "Oops...", text: "Something went wrong!"});
  			 }
		})
		 .then(function(data) {

		 })
			.catch(function(error) {

			});
	}
	
	
			/*
			
				** End Submit Update Job Function Section
				
			*/
			
	//			------------------------------------

			/* 
			
				** Start Update Job Summary Section
				
			*/

	
 function summaryUpdate(){

					//  JOB DESCRIPTION SECTION

		   let opt=document.getElementById("jobType").selectedOptions[0];	
		   let dataContentValue = opt.parentElement.getAttribute('data-content');
		   
			document.getElementById("job-type-updated").innerHTML= document.getElementById("jobType").selectedOptions[0].innerHTML;
			document.getElementById("job-title-updated").innerHTML= document.getElementById("title").value;
			document.getElementById("job-customer-updated").innerHTML= document.getElementById("customer").selectedOptions[0].innerHTML;
			
			
			document.getElementById("ctp").innerHTML= document.getElementById("ctpFees").value;
			document.getElementById("paper-format").innerHTML= document.getElementById("paperFormat").selectedOptions[0].innerHTML;
			document.getElementById("open-l").innerHTML= document.getElementById("openLength").value;
			document.getElementById("open-w").innerHTML= document.getElementById("openWidth").value;
			if(dataContentValue==2 || dataContentValue==1){
				document.getElementById("fold-l").innerHTML=document.getElementById("closeLength").value;
			    document.getElementById("fold-w").innerHTML= document.getElementById("closeWidth").value;
			}else {
				document.getElementById("fold-l").innerHTML=document.getElementById("openLength").value;
			document.getElementById("fold-w").innerHTML= document.getElementById("openWidth").value;
			}
			
			
			let yes = "Yes";
			let no ="No";
			
			if(document.getElementById("dataSuppliedByCustomer").checked)
			document.getElementById("supply-data").innerHTML=yes;
			else document.getElementById("supply-data").innerHTML=no;
			
			if(document.getElementById("existingPlate").checked)
			document.getElementById("existing-plate").innerHTML=yes;
			else document.getElementById("existing-plate").innerHTML=no;
			
			if(document.getElementById("dataSuppliedByCustomer").checked)
			document.getElementById("data-layout").innerHTML=yes;
			else document.getElementById("data-layout").innerHTML=no;
			
			if(document.getElementById("typesettingByUs").checked)
			document.getElementById("type-setting").innerHTML=yes;
			else document.getElementById("type-setting").innerHTML=no;
			
			//finishing options 
			
			document.getElementById("x-perforated").innerHTML=document.getElementById("xPerforated").value;
			document.getElementById("x-numbered").innerHTML=document.getElementById("xNumbered").value;
			document.getElementById("x-wired").innerHTML=document.getElementById("xWire-stitched").value;
			document.getElementById("x-crossed").innerHTML=document.getElementById("xcross").value;
			document.getElementById("crease").innerHTML=document.getElementById("creased").value;
			if(document.getElementById("lamination").selectedOptions[0].value==0){
				document.getElementById("laminated-sides").innerHTML=0;
			}else{
				document.getElementById("laminated-sides").innerHTML=document.getElementById("lamination").selectedOptions[0].innerHTML;
			}

			if(document.getElementById("bindingType").selectedOptions[0].value==0){
				document.getElementById("binding-type").innerHTML="No";
			}else{
				document.getElementById("binding-type").innerHTML=document.getElementById("bindingType").selectedOptions[0].innerHTML;
			}
		
			document.getElementById("stitch").innerHTML = document.getElementById("stitching").value;
			if(document.getElementById("handgather").checked)
			document.getElementById("hand-gather").innerHTML=yes;
			else document.getElementById("hand-gather").innerHTML=no;
//			
//			if(document.getElementById("stitching").checked)
//			document.getElementById("stitch").innerHTML=yes;
//			else document.getElementById("stitch").innerHTML=no;
			
			if(document.getElementById("trimmed").checked)
			document.getElementById("trim").innerHTML=yes;
			else document.getElementById("trim").innerHTML=no;
			
			if(document.getElementById("sellotaped").checked)
			document.getElementById("sello-tape").innerHTML=yes;
			else document.getElementById("sello-tape").innerHTML=no;
			
			if(document.getElementById("sewn").checked)
			document.getElementById("sown").innerHTML=yes;
			else document.getElementById("sown").innerHTML=no;
			
			if(dataContentValue==2)
				// job type is a cover or cover and content cover-paper
			{
			document.getElementById("cover-pages").innerHTML= document.getElementById("volumeOfCover").value;
			document.getElementById("content-pages").innerHTML= document.getElementById("volumeOfContent").value;
			
			document.getElementById("cover-paper-to-update").innerHTML=document.getElementById("coverPaperType-to-update").selectedOptions[0].innerHTML;

			//selectedOptions[0].innerHTML;;
			//document.getElementById("coverPaperType").selectedOptions[0].innerHTML;
			document.getElementById("cover-volume").innerHTML= document.getElementById("coverVolume").value;
			document.getElementById("cover-grammage").innerHTML= document.getElementById("coverGrammage").value;
						
			document.getElementById("cover-machine").innerHTML= document.getElementById("coverPrintingMachine").selectedOptions[0].innerHTML;
			document.getElementById("cover-printtype").innerHTML= document.getElementById("coverPrintType").selectedOptions[0].innerHTML;
			document.getElementById("cover-color-front").innerHTML= document.getElementById("coverFrontColorNumber").value;
			document.getElementById("cover-color-back").innerHTML= document.getElementById("converBackColorNumber").value;
			document.getElementById("cover-signature").innerHTML= document.getElementById("coverSignature").innerHTML;
				
			document.getElementById('cover-pages-info').style.display = "";
			document.getElementById('cover-papers-options-info').style.display = "";
			document.getElementById('cover-printing-options-info').style.display = "";
			
				document.getElementById('content-pages-info').style.display = "";
				document.getElementById('content-papers-options-info').style.display = "";
				document.getElementById('content-printing-options-info').style.display = "";	
		
			}
			
			
			
		if(dataContentValue==2 || dataContentValue==1 || dataContentValue==0|| dataContentValue==3)
		{
			/** job type is either have content and cov|| dataContentValue==1er or only content
		 */
		let contentPaperTypes = document.getElementById("contentDiv").children;
		let mainContentSignature = document.getElementById("mainContentSignature").children;
		let signatureDive = document.getElementById("signatureDiv").children;
		
		let coverTable = document.getElementById("cover-table");
		document.getElementById("content-pages").innerHTML= document.getElementById("volumeOfContent").value;


	for (let i = 1; i < contentPaperTypes.length; i++) {
			
			
		let currentRow = contentPaperTypes[i];
		let contentSignatureRow = mainContentSignature[i];
		let Row = coverTable.insertRow(-1);
		let c1 = Row.insertCell(0);
		let c2 = Row.insertCell(1);
		let c3 = Row.insertCell(2);
		let c4 = Row.insertCell(3);
		c1.innerHTML = i;
		c2.innerHTML = currentRow.querySelector("[contentPaperType]").selectedOptions[0].innerHTML;
		c3.innerHTML = currentRow.querySelector("[contentGrammage]").value;
		c4.innerHTML = currentRow.querySelector("[contentVolume]").value;

}

					// PAPER OPTIONS SECTION ( cover )		

					// PAPER OPTIONS SECTION ( content )

		let contentTable = document.getElementById("update-content-table");
	for (let i=1 ; i<contentPaperTypes.length;i++){
		let currentContentSignature = mainContentSignature[i];
		let lengthOfSignature = currentContentSignature.querySelectorAll("[contentPrintingMachine]").length;
		let currentRow = contentPaperTypes[i];
		let firstRow = contentTable.insertRow(-1);

		let fc1 = firstRow.insertCell(0);
		fc1.rowSpan=lengthOfSignature;
		fc1.innerHTML= i;
		
		for (let j=1 ; j<lengthOfSignature;j++){
			let firstRow = contentTable.insertRow(-1);
			let fc3 = firstRow.insertCell(0);
			let fc4 = firstRow.insertCell(1);
			//let fc5 = firstRow.insertCell(2);
			let fc6 = firstRow.insertCell(2);
			let fc7 = firstRow.insertCell(3);

//		console.log(" check here" + currentContentSignature.querySelectorAll("[contentPrintingMachine]").length);
		fc3.innerHTML=currentRow.querySelector("[contentPaperType]").selectedOptions[0].innerHTML;

		fc4.innerHTML = currentContentSignature.querySelectorAll("[contentPrintingMachine]")[j].selectedOptions[0].innerHTML;
		//fc5.innerHTML =currentContentSignature.querySelectorAll("[contentPrintType]")[j].selectedOptions[0].innerHTML;
		fc6.innerHTML = currentContentSignature.querySelectorAll("[contentFrontColorNumber]")[j].value +" / "+ currentContentSignature.querySelectorAll("[contentBackColorNumber]")[j].value;
		let allSignature = currentContentSignature.querySelectorAll("[inputSignReadonly]");
			for(let a=0; a< allSignature.length; a++){
				fc7.innerHTML = allSignature[a].value;
			}
		
		}
		
		}
		
		document.getElementById('content-pages-info').style.display = "";
		document.getElementById('content-papers-options-info').style.display = "";
	    document.getElementById('content-printing-options-info').style.display="";
	    document.getElementById('cover-pages-info').style.display = "";
		document.getElementById('cover-papers-options-info').style.display = "";
		document.getElementById('cover-printing-options-info').style.display = "";
		if(dataContentValue==1 || dataContentValue==0){//do not display cover divs infos
				document.getElementById('cover-pages-info').style.display = "none";
				document.getElementById('cover-papers-options-info').style.display = "none";
				document.getElementById('cover-printing-options-info').style.display = "none";
					
		}
		}				// PAPER OPTIONS SECTION
	
     }
     
     
	