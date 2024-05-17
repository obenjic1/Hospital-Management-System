
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

function updateTotalContentvolume(value){

	let volumeOfContent = document.querySelectorAll("[contentVolume]")[1].value;
		if(isNaN(parseInt(value))==false){
		volumeOfContent= parseInt(volumeOfContent)-parseInt(value);
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

function paperF(paperFormat){
		
	let PaperFormatArray = paperFormat.split(",");
	let length = PaperFormatArray[1]
	let width = PaperFormatArray[2]
	let halfWidth =Math.floor(parseInt(width)/2);
	document.getElementById("openWidth").value =width;
	document.getElementById("openLength").value =length;
	
	document.getElementById("closeWidth").value =halfWidth;
	document.getElementById("closeLength").value =length;

}

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

function signatureChange(SignatureValue,parent){
		let readOnlyInput = parent.querySelector("[inputSignReadonly]");
		let volumeOfContent = readOnlyInput.value;
		if(isNaN(parseInt(SignatureValue))==false){
		volumeOfContent=parseInt(volumeOfContent) -  parseInt(SignatureValue);
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
	
	let totalSignature = Math.ceil( volume / pagesPerSignature);
	
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



//${coverVolume}
//
// 	var jobType  = document.getElemntById("jobType").value;
//	var title = document.getElementById("title").value = "vujdhjqshhjhj";
//	var decription = document.getElemntById("description").value;
//	var customer = document.getElemntById("customer").value;
//	var contentVolume = document.getElemntById("contentVolume").value;
//	var paperFormat = document.getElemntById("paperFormat").value;
//	var opentwidth = document.getElemntById("opentwidth").value;
//	var opentLength = document.getElemntById("opentLength").value;
//	var closetwidth = document.getElemntById("closetwidth").value;
//	var closeLength = document.getElemntById("closeLength").value;
//	var volume = document.getElemntById("volume").value;
//	var bindingType = document.getElemntById("bindingTyp").value
//	var existingPlate = document.getElemntById("existingPlate").value;
//	var typesettingByUs = document.getElemntById("typesettingByUs").value;
//	var dataSuppliedByCustomer = document.getElyId("dataSuppliedByCustomer").value;
//	var layoutByUs = document.getElemntById("layoutByUs").value;
//	var coverPaperType = document.getElemntById("coverPaperType").value;
//	var coverGrammage = document.getElemntById("coverGrammage").value;
//	var coverVolume = document.getElemntById("coverVolume").value;
//	var contentPaperType = document.getElemntById("contentPaperType").value;
//	var contentGrammage = document.getElemntById("contentGrammage").value;
//	var totalCoverSignature = document.getElemntById("converSignature").value;
//	var printingMachineCover = document.getElemntById("printingMachineCover").value;
//	var coverPrintType = document.getElemntById("coverPrintType").value;
//	var coverFrontColorNumber = document.getElemntById("coverFrontColorNumber").value;
//	var backColorNumber = document.getElemntById("coverBackColorNumber").value;
//	var printingMachineContent = document.getElemntById("printingMachineContent").value;
//	var contentPrintType = document.getElemntById("contentPrintType").value;
//	var frontColorNumber = document.getElemntById("frontColorNumber").value;
//	var backColorNumber = document.getElemntById("backColorNumber").value;
//	var totalContentSignature = document.getElemntById("totalContentSignature").value;
//	var ctpFees = document.getElemntById("ctpFees").value;
//	var xPerforated = document.getElemntById("xPerforated").value;
//	var xNumbered = document.getElemntById("xNumbered").value;
//	var creased = document.getElemntById("creased").value;
//	var xWireStitched = document.getElemntById("xWire-stitched").value;
//	var xcross = document.getElemntById("xcross").value;
//	var glued = document.getElemntById("glued").value;
//	var trimmed = document.getElemntById("trimmed").value;
//	var head = document.getElemntById("head").value;
//	var sellotaped = document.getElemntById("sellotaped").value;
//	var glueBound = document.getElemntById("glueBound").value;
//	var sewn = document.getElemntById("sewn").value;
//	var leftSide = document.getElemntById("leftSide").value;
//	var extraFeesValue = document.getElemntById("value").value;
//	var extraFeesDescription = document.getElemntById("textArea").value;
//	
//	var formData = new FormData();
//		formData.append('jobType', jobType),
//		formData.append('title', title),
//		formData.append('decription', decription),
//		formData.append('customer', customer),
//		formData.append('coverVolume', coverVolume),
//		formData.append('contentVolume',contentVolume ),
//		formData.append('paperFormat',paperFormat ),
//		formData.append('opentwidth',opentwidth ),
//		formData.append('opentLength', opentLength),
//		formData.append('closetwidth',closetwidth ),
//		formData.append('closeLength',closeLength ),
//		formData.append('volume',volume ),
//		formData.append('bindingType', bindingType),
//		formData.append('existingPlate',existingPlate ),
//		formData.append('typesettingByUs',typesettingByUs ),
//		formData.append('dataSuppliedByCustomer',dataSuppliedByCustomer ),
//		formData.append('layoutByUs',layoutByUs ),
//		formData.append('coverPaperType',coverPaperType ),
//		formData.append('coverGrammage',coverGrammage ),
//		formData.append('coverVolume',coverVolume ),
//		formData.append('contentPaperType',contentPaperType ),
//		formData.append('contentGrammage',contentGrammage ),
//		formData.append('totalCoverSignature',totalCoverSignature ),
//		formData.append('printingMachineCover',printingMachineCover ),
//		formData.append('coverPrintType',coverPrintType ),
//		formData.append('coverFrontColorNumber',coverFrontColorNumber ),
//		formData.append('backColorNumber',backColorNumber ),
//		formData.append('totalContentSignature',totalContentSignature ),
//		formData.append('ctpFees',ctpFees ),
//		formData.append('xPerforated',xPerforated ),
//		formData.append('xNumbered',xNumbered ),
//		formData.append('creased', creased),
//		formData.append('xWireStitched', xWireStitched),
//		formData.append('xcross',xcross ),
//		formData.append('glued',glued ),
//		formData.append('trimmed',trimmed ),
//		formData.append('head',head ),
//		formData.append('sellotaped', sellotaped),
//		formData.append('glueBound', glueBound),
//		formData.append('sewn',sewn ),
//		formData.append('leftSide',leftSide ),
//		formData.append('extraFeesValue',extraFeesValue ),
//		formData.append('extraFeesDescription',extraFeesDescription )


//var defaultValue = "4"
//coverVolume.value = defaultValue;
//defaultValue.addEventListener.








