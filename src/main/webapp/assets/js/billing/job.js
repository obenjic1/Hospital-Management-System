				
				/*
				
					** Start Navigate Section 
					
				*/

function tab1NextBtnAction(){
	let volumeOfCover = document.getElementById("volumeOfCover").value;
	let volumeOfContent = document.getElementById("volumeOfContent").value;
	
	document.getElementById("coverVolume").value=volumeOfCover;
	let contentVolumes = document.querySelectorAll("[contentVolume]");  
	if(contentVolumes[1].value == 0)
	contentVolumes[1].value = volumeOfContent;

 	 navigate(1,2);
 	
	}

function navigate(activeTab,tabToActivate){
	
	$('.nav-tabs button[data-bs-target= "#tab'+tabToActivate+'"]').tab('show');
	$('.nav-tabs button[data-bs-target= "#tab'+tabToActivate+'"]').css("color", "blue") ;
	tabToActivate = tabToActivate-tabToActivate + activeTab;
	$('.nav-tabs button[data-bs-target= "#tab'+tabToActivate+'"]').css("color", "black");
}

				/*
				
					** End  Navigate Section
					
				*/
				
	//     -------------------------------------------
	
				/*
				
					** Start Content Node Section 
					
				*/
				
function totalContentVolumeChange(){
	document.getElementById("coverVolume").value=volumeOfCover;
	let contentVolumes = document.querySelectorAll("[contentVolume]");  
	contentVolumes.forEach(contentVolume =>{
		contentVolume.value = "";
		});
}

function addContentPaperChild(){
	let contentDiv = document.getElementById("contentDiv");
	let ContentFirstChild = contentDiv.children[0];
	let	cloneContentFirstChild = ContentFirstChild.cloneNode(true);
		cloneContentFirstChild.style.display="block";
		cloneContentFirstChild.style.display="";
		contentDiv.appendChild(cloneContentFirstChild);	
		
	let contentDivTab2 =  document.getElementById("mainContentSignature");
	let contentDivTab2FirstChild = contentDivTab2.children[0];
	let	clonecontentDivTab2FirstChild= contentDivTab2FirstChild.cloneNode(true);
		clonecontentDivTab2FirstChild.style.display="block";
		clonecontentDivTab2FirstChild.style.display="";
		contentDivTab2.appendChild(clonecontentDivTab2FirstChild);	

	}

function updateTotalContentvolume(value,oldValue){

	let volumeOfContent = document.querySelectorAll("[contentVolume]")[1].value;
		if(isNaN(parseInt(value))==false){
		volumeOfContent = parseInt(volumeOfContent)-parseInt(value);	
	}
		
		if(isNaN(parseInt(oldValue))==false){
		volumeOfContent = parseInt(volumeOfContent)+parseInt(oldValue);	
	}
		document.querySelectorAll("[contentVolume]")[1].value = volumeOfContent;

}


function removeContentNode(deleteBtn,input){
	let parentNode = deleteBtn.parentNode.parentNode.parentNode;
	let removeindex =Array.from(parentNode.children).indexOf(deleteBtn.parentNode.parentNode);
	let value =input.value;
	let volumeOfContent = document.querySelectorAll("[contentVolume]")[1].value;
	if(isNaN(parseInt(value))==false){
		volumeOfContent=parseInt(volumeOfContent) +  parseInt(value);
	}
	document.querySelectorAll("[contentVolume]")[1].value = volumeOfContent;
	deleteBtn.parentNode.parentNode.remove();
	
	let nodeRemove = document.getElementById("mainContentSignature");
		nodeRemove.children[removeindex].remove();
	
}

				/*
				
					** Remove Content Node Section
					
				*/
				
	//        ---------------------------------------
	
				/*
				
					** Start Paper Format Section
					
				*/

function paperF(paperFormat){
	 let opt=document.getElementById("jobType").selectedOptions[0];	
	 let dataContentValue = opt.parentElement.getAttribute('data-content');
	let PaperFormatArray = paperFormat.split(",");
	let length = PaperFormatArray[1];
	let width = PaperFormatArray[2];
	let halfWidth =Math.floor(parseInt(width)/2);
	document.getElementById("openWidth").value =width;
	document.getElementById("openLength").value =length;
	if(dataContentValue==3){
		document.getElementById("closeWidth").value =width;
		document.getElementById("closeLength").value =length;
	}else{
		document.getElementById("closeWidth").value =halfWidth;
		document.getElementById("closeLength").value =length;
	}
	
}

				/*
				
					**n Choose Paper Format Section 
					
				*/
				
	//				------------------------------
				
				/* 
				
					** Start Calcul Signatur Number Section 
					
				*/
				
function updateContentSignature(parent,index,node){
	let child = parent.children[index];
	let cloneChild =child.cloneNode(true);
	cloneChild.style.display="block";
	cloneChild.style.display="";
	let machineParam = node.querySelector("[contentPrintingMachine]").value; 
	let childSelectInput = cloneChild.querySelector("[contentPrintingMachine]");
	childSelectInput.value = machineParam;
	parent.appendChild(cloneChild);
	
}

function signatureChange(SignatureValue,parent,oldValue){
		let readOnlyInput = parent.querySelector("[inputSignReadonly]");
		let volumeOfContent = readOnlyInput.value;
		if(isNaN(parseFloat(SignatureValue))==false){
		volumeOfContent=parseFloat(volumeOfContent) -  parseFloat(SignatureValue);
	}
	if(isNaN(parseFloat(oldValue))==false){
		volumeOfContent=parseFloat(volumeOfContent) +  parseFloat(oldValue);
	}
	readOnlyInput.value = volumeOfContent;
	
	
}


function deleteContentsignature(noteToDel,parent){
	let inputValue = parent.querySelectorAll("[delContentSign]")[1].value;
	let readonlyValue =parent.querySelector("[inputSignReadonly]").value;
	
	if(isNaN(parseInt(inputValue))==false){
		readonlyValue=parseInt(readonlyValue) + parseInt(inputValue);
	}
	parent.querySelector("[inputSignReadonly]").value = readonlyValue;
	noteToDel.remove();
}


function signatureCalculation(machineParams,node){
	
	let allSignatureNodes=document.getElementById("mainContentSignature");
	let nodeIndex =Array.from(allSignatureNodes.children).indexOf(node);
	let contentDivTab2 = document.getElementById("contentDiv");
	let volumeRow = contentDivTab2.children[nodeIndex];
	
	let inputVolume = volumeRow.querySelector("[contentVolume]");
	let inputSignature = node.querySelector("[inputSignReadonly]");
	
	let volume = inputVolume.value;
	
	let machineParamArray = machineParams.split(",");
	let machinePlateLength = parseInt(machineParamArray[1]);
	let machinePlateWidth = parseInt(machineParamArray[2]);
	
	let closeWidth =parseInt(document.getElementById("closeWidth").value);
	let closeLength =parseInt(document.getElementById("closeLength").value);
	
	let logP = Math.log((machinePlateLength * machinePlateWidth)/ (closeLength*closeWidth));
		logP = logP/Math.log(2);
		logP= Math.floor(logP)+1;
	let pagesPerSignature = Math.pow(2,logP);
	let floatingSignature = ( volume / pagesPerSignature);
	let totalSignature = Math.floor(floatingSignature)<floatingSignature&&floatingSignature<Math.floor(floatingSignature+0.5)&&(floatingSignature+0.5 != Math.floor(floatingSignature+0.5)) ?Math.ceil(floatingSignature):Math.floor(floatingSignature)<floatingSignature?Math.floor(floatingSignature)+0.5:Math.floor(floatingSignature); 
	inputSignature.value = totalSignature;
	
	let machines = node.querySelectorAll("[contentPrintingMachine]");
	let i = 0;
	for(i=2;i<machines.length;i++){
		machines[i].value = machineParams;
	};
	
	let signatures = node.querySelectorAll("[delContentSign]");
	for(i=1;i<signatures.length;i++){
		signatures[i].value=0;
	};

}


     		// Calcul of Cover Signqture
 function coverSignatureCalculation(machineParams,node){
	
	let coverSignature=document.getElementById("cover-signature-div");
	let coverDivTab2 = document.getElementById("coverInformations");
	
	let inputCoverVolume = document.getElementById("coverVolume");
	let inputCoverSignature = document.getElementById("coverSignature");
	
	let volume = inputCoverVolume.value;
	
	let coverMachineParamArray = machineParams.split(",");
	let machinePlateLength = parseInt(coverMachineParamArray[1]);
	let machinePlateWidth = parseInt(coverMachineParamArray[2]);
	
	let closeWidth =parseInt(document.getElementById("closeWidth").value);
	let closeLength =parseInt(document.getElementById("closeLength").value);
	
	let logP = Math.log((machinePlateLength*machinePlateWidth)/(closeLength*closeWidth));
		logP = logP/Math.log(2);
		logP= Math.floor(logP)+1;
	let pagesPerSignature = Math.pow(2,logP);
	let floatingCoverSignature = (volume/pagesPerSignature);
	let totalCoverSignature = Math.floor(floatingCoverSignature)<floatingCoverSignature&&floatingCoverSignature<Math.floor(floatingCoverSignature+0.5)&&(floatingCoverSignature+0.5 != Math.floor(floatingCoverSignature+0.5)) ?Math.ceil(floatingCoverSignature):Math.floor(floatingCoverSignature)<floatingCoverSignature?Math.floor(floatingCoverSignature)+0.5:Math.floor(floatingCoverSignature); 
	inputCoverSignature.value = totalCoverSignature;
	
}
				/*
				
					** End Signature Calculation Section 
					
				*/
				
//		------------------------------------------		

				/*
				
					** Start Rand Up Signature Section 
					
				*/


function randUpCoverSignature(){
	let valueToRandUp = document.getElementById("coverSignature").value;
	let contentSignatureToRandUp = document.getElementById("coverSignature");
	contentSignatureToRandUp.value = Math.ceil(valueToRandUp);
}

function randUpContentSignature(){
	let contentValueToRandUp = document.getElementById("signature-content-to-randup").value;
	let contentRandedUpValue = document.getElementById("signature-content-to-randup");
	contentRandedUpValue.value = Math.ceil(contentValueToRandUp);
	alert(contentRandedUpValue);

}


				/*
				
					** End Rand Up Signature Section
					
				*/
				
	//				--------------------------------------
				
				/*
				
					** Start Submit Job Section
					
				*/

function submitForm(){
	 let opt=document.getElementById("jobType").selectedOptions[0];	
	 let dataContentValue = opt.parentElement.getAttribute('data-content');
 let job = { };
 job.customerId = document.getElementById("customer").value;
 job.jobTypeId = document.getElementById("jobType").value;
 job.title = document.getElementById("title").value;
 
 if(dataContentValue==2){
	  job.coverVolume = document.getElementById("volumeOfCover").value;
 }
    if(dataContentValue==0||dataContentValue==2||dataContentValue==3){
	   job.contentVolume = document.getElementById("volumeOfContent").value;
 } 
 job.ctpFees = document.getElementById("ctpFees").value;
 job.openWidth = document.getElementById("openWidth").value;
 job.openLength = document.getElementById("openLength").value;
 if(dataContentValue==1 || dataContentValue==2||dataContentValue==3){
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
	 jobActivity.stitching = document.getElementById("stitching").value;
	 jobActivity.trimmed = document.getElementById("trimmed").checked;  
	 jobActivity.selloptaped = document.getElementById("sellotaped").checked;
	 jobActivity.sewn = document.getElementById("sewn").checked;
	 jobActivity.handFoldCov = document.getElementById("handFoldCov").value;
  job.jobActivities = jobActivity;
 	
 	// Adding coverJobPaper and color combination  
 	
	 let jobPapers = [ ];
	 if(dataContentValue==2||dataContentValue==3){
		 let coverJobPaper = { };
	  coverJobPaper.grammage = document.getElementById("coverGrammage").value;
	  coverJobPaper.volume = document.getElementById("coverVolume").value;
	  coverJobPaper.paperTypeId = document.getElementById("coverPaperType").value;
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
    
    if(dataContentValue==1||dataContentValue==2 || dataContentValue==0||dataContentValue==3){
		
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
		 contentJobPaper.paperTypeId = paperType;
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
	 
	 fetch('job/save', {
			method: 'POST',
			body: JSON.stringify(job) ,
			 headers: {
           "Content-Type": "application/json",
           },
		})
		.then( response => {	
   			 if (response.status === 200) {
       			Swal.fire("Succes/Success!", "Job saved successfully!", "success")
			return loadPage("job/list-job");				
   			 } else if (response.status !== 200) {
				Swal.fire({  icon: "error", title: "Oops...", text: "Something went wrong!"});
  			 }
		})
		 .then(function(data) {

		 })
			.catch(function(error) {

			});
	}
     
     			/*
     			
     				** End Submit Job Section 
     				
     			*/
     			
	//     		--------------------------------------

			     /*
			     
			    	 ** Start Summary Section
			    	 
			     */
				
    function summary(){
		
					//  JOB DESCRIPTION SECTION
			//alert(document.getElementById("coverPaperType").selectedOptions[0].innerHTML);
			console.log(document.getElementById("coverPaperType").selectedOptions[0].innerHTML);

		   let opt=document.getElementById("jobType").selectedOptions[0];	
		   let dataContentValue = opt.parentElement.getAttribute('data-content');
		   
			document.getElementById("job-type").innerHTML= document.getElementById("jobType").selectedOptions[0].innerHTML;
			document.getElementById("job-title").innerHTML= document.getElementById("title").value;
			document.getElementById("job-customer").innerHTML= document.getElementById("customer").selectedOptions[0].innerHTML;
			
			
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
			
			if(dataContentValue==2||dataContentValue==3)
				// job type is a cover or cover and content cover-paper
			{
			document.getElementById("cover-pages").innerHTML= document.getElementById("volumeOfCover").value;
			document.getElementById("content-pages").innerHTML= document.getElementById("volumeOfContent").value;

			document.getElementById("cover-paper").innerHTML=document.getElementById("coverPaperType").selectedOptions[0].innerHTML;
			

			//selectedOptions[0].innerHTML;;
		//	document.getElementById("coverPaperType").selectedOptions[0].innerHTML;
			document.getElementById("cover-volume").innerHTML= document.getElementById("coverVolume").value;
			document.getElementById("cover-grammage").innerHTML= document.getElementById("coverGrammage").value;
						
			document.getElementById("cover-machine").innerHTML= document.getElementById("coverPrintingMachine").selectedOptions[0].innerHTML;
			document.getElementById("cover-printtype").innerHTML= document.getElementById("coverPrintType").selectedOptions[0].innerHTML;
			document.getElementById("cover-color-front").innerHTML= document.getElementById("coverFrontColorNumber").value;
			document.getElementById("cover-color-back").innerHTML= document.getElementById("converBackColorNumber").value;
			document.getElementById("cover-signature").innerHTML= document.getElementById("coverSignature").value;
				
			document.getElementById('cover-pages-info').style.display = "";
			document.getElementById('cover-papers-options-info').style.display = "";
			document.getElementById('cover-printing-options-info').style.display = "";
			
				document.getElementById('content-pages-info').style.display = "";
				document.getElementById('content-papers-options-info').style.display = "";
				document.getElementById('content-printing-options-info').style.display = "";	
		
			}
		if(dataContentValue==2 || dataContentValue==1 || dataContentValue==0 || dataContentValue==3)
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

		let contentTable = document.getElementById("content-table");
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
			let fc5 = firstRow.insertCell(2);
			let fc6 = firstRow.insertCell(3);
			let fc7 = firstRow.insertCell(4);

//		console.log(" check here" + currentContentSignature.querySelectorAll("[contentPrintingMachine]").length);
		fc3.innerHTML=currentRow.querySelector("[contentPaperType]").selectedOptions[0].innerHTML;

		fc4.innerHTML =currentContentSignature.querySelectorAll("[contentPrintingMachine]")[j].selectedOptions[0].innerHTML;
		fc5.innerHTML =currentContentSignature.querySelectorAll("[contentPrintType]")[j].selectedOptions[0].innerHTML;
		fc6.innerHTML =currentContentSignature.querySelectorAll("[contentFrontColorNumber]")[j].value +" / "+ currentContentSignature.querySelectorAll("[contentBackColorNumber]")[j].value;
		fc7.innerHTML =currentContentSignature.querySelectorAll("[allSignatures]")[j].value;
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
			
//			if(document.getElementById("glueingOption").selectedOptions[0].value==0){
//				document.getElementById("glue-bound").innerHTML="No";
//			}else{
//				document.getElementById("glue-bound").innerHTML=document.getElementById("glueingOption").selectedOptions[0].innerHTML;
//			}
			if(document.getElementById("bindingType").selectedOptions[0].value==0){
				document.getElementById("binding-type").innerHTML="No";
			}else{
				document.getElementById("binding-type").innerHTML=document.getElementById("bindingType").selectedOptions[0].innerHTML;
			}
			document.getElementById("stitch").innerHTML = document.getElementById("stitching").value;
			
			if(document.getElementById("handgather").checked)
			document.getElementById("hand-gather").innerHTML=yes;
			else document.getElementById("hand-gather").innerHTML=no;
			
			
//			if(document.getElementById("stitching").checked)
//			document.getElementById("stitch").innerHTML=;
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
			
			
			
     }
   
   				/*
   				
   					** End Summary Section 
   					
   				*/
   				
	//   	---------------------------------------
	
   				/*
   				
   					** Start Add and Remove Child Section
   					
   				*/
   
		function removeRows() {
		let contentPaperTypes = document.getElementById("contentDiv").children;
		let contentPaperTable = document.getElementById("cover-table");
		let contentPrintingTable = document.getElementById("content-table");
		let allContentPaperTableRows = contentPaperTable.querySelectorAll("tr");
		let allContentPrintingTable = contentPrintingTable.querySelectorAll("tr");
//		let tbody = document.getElementById("table-body");
	           		
	           		removeRowspan()
		for(let i=1;i<contentPaperTypes.length;i++){
				    removeRowspan();
	           		allContentPaperTableRows[i].remove();
//	           		allContentPrintingTable[i].remove;
// 					removeContentRow()
	           		}

				}
				
				
				
	function removeRowspan() {
    let contentTable = document.getElementById("content-table");
	let allContentPrintingTable = contentTable.querySelectorAll("tr");
    // Find the cell with the rowspan
    let rowspanCell = contentTable.querySelector("td[rowspan]");
    if (rowspanCell) {
      // Get the parent row
      let parentRow = rowspanCell.parentNode;

      // Get the rowspan value
      let rowspanValue = parseInt(rowspanCell.getAttribute("rowspan"));

      // Remove the rows with the rowspan
      for (let i = 1; i < rowspanValue; i++) {
        contentTable.deleteRow(parentRow.rowIndex + 1);
      }

      // Remove the rowspan from the cell
      rowspanCell.removeAttribute("rowspan");
    }
    for(let i=1;i<allContentPrintingTable.length;i++){
	           		allContentPrintingTable[i].remove();
	 }
	  }


	
function removeEstmateContentNode(deleteBtn){
	let parentNode = deleteBtn.parentNode;
	deleteBtn.parentNode.parentNode.remove();
	
}
function addNextEstimateChild(){
	let contentDiv = document.getElementById("main-estimate-div");
	let ContentFirstChild = contentDiv.children[0];
	let	cloneContentFirstChild = ContentFirstChild.cloneNode(true);
		cloneContentFirstChild.style.display="block";
		cloneContentFirstChild.style.display="";
		contentDiv.appendChild(cloneContentFirstChild);	


}
				/*
				
					** End Add and Remove Child Section 
					
				*/
				
	//		----------------------------------------
				/*
				
					** Start Generate Estimate Section
					
				*/
		
function generateEstimate(url, currentDiv, nextDiv){
	let quantities = document.querySelectorAll("[estimate-quantity]");
	let estimateQuantities = "";

	for(let i=1; i<quantities.length; i++){
		estimateQuantities+=quantities[i].value + "@";
	}
		
	let extraFee = document.getElementById("extra-fee").value;
	let description = document.getElementById("extra-fee-description").value;
	let advancePercentage = document.getElementById("advancePercentage").value;

	url+= "?quantities=" + estimateQuantities + "&extraFee=" +extraFee + "&extraFeeDescription=" + description +"&advancePercentage="+advancePercentage;
	loadDynamicPageContent(url, nextDiv);
	document.getElementById(nextDiv).style.display="block";
	document.getElementById(currentDiv).style.display="none";
	
	
}

				/*
				
					** End Generate Estimate Section
					
				*/
				
	//			-----------------------------------
	
				/*
				
					** Start Apply AdVance Percentage Section
					
				*/
	
	function advancePercentage(){
			let advancePercentageCheckbox= document.getElementById("advancePercentageC");
			let advancePercentageInput =document.getElementById("advancePercentage");
				if(advancePercentageCheckbox.checked){
				advancePercentageInput.style.display="block";
	} else {
		advancePercentageInput.style.display="none";
	}
 }
	
				/*
				
					**End Apply AdVance Percentage Section
					
				*/
				
	//				-------------------------------------
				
				/*
				
					**Start Confirm Estimate Section 
					
				*/
				
function confirmEstimate(urlConfirm, urlPrintEstimate){
	let quantities = document.querySelectorAll("[estimate-quantity]");
	let estimateQuantities = "";
	let advancePercentage = document.getElementById("advancePercentage").value;
	
	for(let i=1; i<quantities.length; i++){
		estimateQuantities+=quantities[i].value + "@";
	}

	let extraFee = document.getElementById("extra-fee").value;
	let description = document.getElementById("extra-fee-description").value;

	urlConfirm+= "?quantities=" + estimateQuantities + "&extraFee=" +extraFee + "&extraFeeDescription=" + description+"&advancePercentage="+advancePercentage;
	fetch(urlConfirm, {
    		method: 'POST',
    		headers: {
    			'Content-type': 'application/json'
    		},
    	}).then(response => response.text())
    	   .then(html=> {

            if(html.indexOf("k0###")== -1){
				loadPageModal(urlPrintEstimate+html);
				
			}else{
				alert("something when wrong");
			}

        })
        .catch(error => {
            console.log("internal server error :", error);
        });
}

			/*
			
				** End Confirm Estimate Section
				
			*/

	//			----------------------------------------
			
			/*
			
				** Start Confirm Job Section
				
			*/

function confirmEstimateCommission(ref){
	
	loadPage(`job/search-by/${reference}`);		
}
				/**
				 
					 ** End Confirm Job Section 
				 
				 */
				
	//		-----------------------------------

				/*
				
					** Start Behavior of For in function of Job Type Choosen
					
				*/

function jobTypeChoice(opt){
	
	let dataContentValue = opt.parentElement.getAttribute('data-content');
	
	if(dataContentValue==0){
		document.getElementById('volumeofContent').style.display = "";
		document.getElementById('volumeofCover').style.display = "none";
		document.getElementById('coverInformations').style.display = "none";
		document.getElementById('contentDiv').style.display = "";
		document.getElementById('cover-signature-div').style.display = "none";
		document.getElementById('mainContentSignature').style.display = "";  
		document.getElementById('closeDimensionDiv').style.display = "none";
		document.getElementById('volumeOfContent').value=1;
		document.getElementById('volumeOfContent').readOnly = false;
	}
	else if(dataContentValue==1){
		document.getElementById('volumeofCover').style.display = "none";
		document.getElementById('volumeofContent').style.display = "";
		document.getElementById('coverInformations').style.display = "none";
		document.getElementById('contentDiv').style.display = "";
		document.getElementById('cover-signature-div').style.display = "none";
		document.getElementById('mainContentSignature').style.display = "";
		document.getElementById('closeDimensionDiv').style.display = "";
		document.getElementById('volumeOfContent').value="";
		document.getElementById('volumeOfContent').readOnly = false;
	}
	else if(dataContentValue==3){
		document.getElementById('closeDimensionDiv').style.display = "none";
		document.getElementById('volumeofContent').style.display = "";
		document.getElementById('volumeofCover').style.display = "";
		document.getElementById('coverInformations').style.display = "";
		document.getElementById('contentDiv').style.display = "";
		document.getElementById('cover-signature-div').style.display = "";
		document.getElementById('mainContentSignature').style.display = "";
		document.getElementById('volumeOfContent').value="";
		document.getElementById('volumeOfContent').readOnly = false;
	}
	else {
		document.getElementById('volumeofContent').style.display = "";
		document.getElementById('volumeofCover').style.display = "";
		document.getElementById('coverInformations').style.display = "";
		document.getElementById('contentDiv').style.display = "";
		document.getElementById('cover-signature-div').style.display = "";
		document.getElementById('mainContentSignature').style.display = "";
		document.getElementById('closeDimensionDiv').style.display = "";
		document.getElementById('volumeOfContent').value="";
		document.getElementById('volumeOfContent').readOnly = false;
	}

}
	
			/*
			
				** End Choose Job Type Section
				
			*/
			
	//		--------------------------------
			
			/*
			
				** Start Pagination Section
				
			*/

	function refreshJobPage(pageNo) {
    return new Promise((resolve, reject) => {
        $.ajax({
            url: 'job/page/' + pageNo,
            type: 'GET',
            success: function(data) {
                $('#job-pagination').html(data);
                resolve(); // Resolve the promise on success
            },
            error: function() {
                alert('Une erreur s\'est produite lors du chargement de la page.');
                reject(); // Reject the promise on error
            }
        });
    });
}

				/**
				
					** End Pagination Section
					
				*/
				
	//				---------------------------------
				
				/** 
				
					** Start Search Job Section 
				
				*/

function searchByReference() {
	let reference = document.getElementById("search").value;
	let test = reference[0];
	if(test=='J'||test=='j'){
		loadPage(`job/search-by/${reference}`);
	}
	
	else if (test=='E'||test=='e'){
		loadPage(`job/search-estimate-by/${reference}`);
	}
	
		
	}

function findByDate() {
	let startDateValue = document.getElementById("search_startDate").value;
	let endDateValue = document.getElementById("search_endDate").value;
	loadPage(`job/find-by/creationdate/${startDateValue}/${endDateValue}`);
		
	}	
	
				/** 
				
					** End Search Job Section
					
				*/
				
				
	/**
	 * Working with Draft
	 * 
	 */
	function tab1NextBtn(){
 	 navigate(1,2);
 	
	}
	
	
	function submitFormDraft(){
	 let opt=document.getElementById("jobType").selectedOptions[0];	
	 let dataContentValue = opt.parentElement.getAttribute('data-content');
 let job = { };
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
 
	 fetch('job/save-draft', {
			method: 'POST',
			body: JSON.stringify(job) ,
			 headers: {
           "Content-Type": "application/json",
           },
		})
		.then( response => {	

   			 if (response.status === 200) {
					Swal.fire("Succes/Success!", "You clicked the button!", "success")
				return loadPage("job/list-job");				
   			 } else if (response.status !== 200) {
					Swal.fire({icon: "error", title: "Oops...", text: "Something went wrong!"});
				return loadPage("job/list-job");	
//				sendMessage('Failed / Echec', 2);
  			 }
		})
		 .then(function(data) {

		 })
			.catch(function(error) {

			});
	}
	
function updateDraft(id){
  let opt=document.getElementById("jobType").selectedOptions[0];	
	 let dataContentValue = opt.parentElement.getAttribute('data-content');
 let job = { };
// job.customerId = document.getElementById("customer").value;
 job.jobTypeId = document.getElementById("jobType").value;
 job.title = document.getElementById("title").value;
 
// if(dataContentValue==2){
	  job.coverVolume = document.getElementById("volumeOfCover").value;
 //}
  //  if(dataContentValue==0||dataContentValue==2){
	   job.contentVolume = document.getElementById("volumeOfContent").value;
// } 
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
 
 
	 fetch(`job/update-draft/${id}`, {
			method: 'POST',
			body: JSON.stringify(job) ,
			 headers: {
           "Content-Type": "application/json",
           },
                 	// body: jsonUpdatedData,
			
		})
		.then( response => {	

   			 if (response.status === 200) {
				Swal.fire("Succes/Success!", "You clicked the button!", "success")				
   			 } else if (response.status !== 200) {
					Swal.fire({icon: "error", title: "Oops...", text: "Something went wrong!"});
  			 }
		})
		 .then(function(data) {

		 })
			.catch(function(error) {

			});
			}



	/*
	*  Summary To update Draft
	*/

function summaryDraftUpdate(){

	let opt=document.getElementById("jobType").selectedOptions[0];	
		   let dataContentValue = opt.parentElement.getAttribute('data-content');
		   
			document.getElementById("job-type").innerHTML= document.getElementById("jobType").selectedOptions[0].innerHTML;
			document.getElementById("job-title").innerHTML= document.getElementById("title").value;
			document.getElementById("job-customer").innerHTML= document.getElementById("customer").selectedOptions[0].innerHTML;
			
			
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
			
			if(dataContentValue==2)
				// job type is a cover or cover and content
			{
			document.getElementById("cover-pages").innerHTML= document.getElementById("volumeOfCover").value;
			document.getElementById("content-pages").innerHTML= document.getElementById("volumeOfContent").value;

			document.getElementById("cover-paper").innerHTML= document.getElementById("coverPaperType").value;
//			   selectedOptions[0].innerHTML;
			document.getElementById("cover-volume").innerHTML= document.getElementById("coverVolume").value;
			document.getElementById("cover-grammage").innerHTML= document.getElementById("coverGrammage").value;
						
			document.getElementById("cover-machine").innerHTML= document.getElementById("coverPrintingMachine").selectedOptions[0].innerHTML;
			document.getElementById("cover-printtype").innerHTML= document.getElementById("coverPrintType").selectedOptions[0].innerHTML;
			document.getElementById("cover-color-front").innerHTML= document.getElementById("coverFrontColorNumber").value;
			document.getElementById("cover-color-back").innerHTML= document.getElementById("converBackColorNumber").value;
			document.getElementById("cover-signature").innerHTML= document.getElementById("coverSignature").value;
				
			document.getElementById('cover-pages-info').style.display = "";
			document.getElementById('cover-papers-options-info').style.display = "";
			document.getElementById('cover-printing-options-info').style.display = "";
			
				document.getElementById('content-pages-info').style.display = "";
				document.getElementById('content-papers-options-info').style.display = "";
				document.getElementById('content-printing-options-info').style.display = "";	
		
			}
			document.getElementById("cover-pages").innerHTML= document.getElementById("volumeOfCover").value;
			document.getElementById("content-pages").innerHTML= document.getElementById("volumeOfContent").value;
		if(dataContentValue==2 || dataContentValue==1 || dataContentValue==0)
		{
			/** job type is either have content and cov|| dataContentValue==1er or only content
		 */
		let contentPaperTypes = document.getElementById("contentDiv").children;
		let mainContentSignature = document.getElementById("mainContentSignature").children;
		let signatureDive = document.getElementById("signatureDiv").children;
		let coverTable = document.getElementById("cover-table");
		document.getElementById("content-pages").innerHTML= document.getElementById("volumeOfContent").value;

}
}


//	----------------------------------------------
		/*
			** Abort Job Sectio
		*/
     

     function confirmAbort(id) {
		  Swal.fire({
		    title: 'Are you sure to abort?',
		    text: "You won't be able to revert this!",
		    icon: 'warning',
		    showCancelButton: true,
		    confirmButtonColor: '#3085d6',
		    cancelButtonColor: '#d33',
		    confirmButtonText: 'Yes, abort it!',
		  }).then((result) => {
		    if (result.isConfirmed) {
		      abortJob(id);
		      Swal.fire("Success!/Success!", "Your job has been aborted!", "success");
		    } else {
		      Swal.fire("Cancelled!/Annulee!", "Operation cancelled", "info");
		    }
		  });
		}


     function abortJob(id){
		  fetch(`job/abortJob/${id}`, {
			method: 'POST',
		headers: {
			'Content-type': 'application/json'
		},
	})
		.then( response => {	

   			 if (response.ok) {
       			Swal.fire({  icon: "error", title: "Oops...", text: "Something went wrong!"});
				return loadPage("job/list-job"); 
  			 } else if (response.status !== 200) {
				return loadPage("job/list-job");

  			 }
		})
		 .then(function(data) {

		 })
			.catch(function(error) {

			});
	 }
		 
		 /*
		   **  End Abort Job Section
		 */
//	 ------------------------------------------
		 /*
		  ** Start Confirm Job Section
		 */
	 
		  function confirmJob(id) {
			Swal.fire({
			  title: 'Are you sure to Confirm?',
		      text: "You won't be able to revert this!",
		      icon: 'warning',
		      showCancelButton: true,
		      confirmButtonColor: '#3085d6',
		      cancelButtonColor: '#d33',
		      confirmButtonText: 'Yes, Confirm it!',
			}).then((result) => {
				if(result.isConfirmed){
				    confirm(id);
					Swal.fire("Success!/Success!", "Your job has been confirmed!", "success");
				}else{
					Swal.fire("Cancelled/Annulee!", "Operation cancelled", "info");
				}
			})  
		}

	  function confirm(id){
		  fetch(`job/confirm/${id}`, {
			method: 'POST',
		headers: {
			'Content-type': 'application/json'
		},
	})
		.then( response => {	

   			  if (response.ok) {
				return loadPage("job/list-job"); 
  			 } else if (response.status !== 200) {
	//			Swal.fire({  icon: "error", title: "Oops...", text: "Something went wrong!"});
				return loadPage("job/list-job");
  			 }
		})
		 .then(function(data) {

		 })
			.catch(function(error) {

			});
	 }

		/*
		 ** End Confirm Job
		*/
		
//		-------------------------------------------
		
  		/*
  		  ** Start Approv Job
  		*/
  		
  function approveJob(id){
	  swal({
		  title: 'Are you sure?',
		  text: "You're going to approve the job!",
		  type: 'warning',
		  showCancelButton: true,
		  confirmButtonColor: '#3085d6',
		  cancelButtonColor: '#d33',
		  confirmButtonText: 'Yes, delete it!'
		}).then((result) => {
			if(result.isConfirmed){
				confirmApproveJob(id);
				Swal.fire("Success!/Success!", "Your job has been approved!", "success");
			}else{
				Swal.fire("Cancelled/Annulee!", "Operation cancelled", "info");
			}
		  
		})
  }
  		
 function confirmApproveJob(id){
	 fetch(`job/approve/${id}`, {
		 method: 'POST',
		headers: {
			'Content-type': 'application/json'
		},
	})
		.then( response => {	

   			 if (response.ok) {
       		//	Swal.fire("Succes/Success!", "Job Approved / Job Approve", "success");
				return loadPage("job/list-job"); 
  			 } else if (response.status !== 200) {
		     // Swal.fire({  icon: "error", title: "Oops...", text: "Something went wrong!"});
				return loadPage("job/list-job");
  			 }
		})
		 .then(function(data) {

		 })
			.catch(function(error) {

			});
	 }
	 
	 /*
	   * End Approof Job Section
	*/
	
 //  ----------------------------------------------
	
	/*
	 **  Start Move Job section
	*/
		 
	  function moveJob(id){
		   var department = document.getElementById('department').value;
	 var description = document.getElementById('description').value;
	 
	var job = {
		
		description: description,
		department :department,
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
       			Swal.fire("Succes/Success!", "Job Moved / Job Transfere", "success");
			return loadPage("job/list-job");				
   			 } else if (response.status !== 200) {
				Swal.fire({  icon: "error", title: "Oops...", text: "Something went wrong!"});
  			 }
		})
		 .then(function(data) {

		 })
			.catch(function(error) {

			});

	  }
	 
	