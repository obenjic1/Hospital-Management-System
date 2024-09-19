
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<head>
  <link href="assets/css/billing/job.css" rel="stylesheet">
</head>


    <div class="pagetitle">
      <nav>
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a href="index.html">Home</a></li>
          <li class="breadcrumb-item"><fmt:message key="jobs"/></li>
          <li class="breadcrumb-item active"><fmt:message key="new"/></li>
        </ol>
      </nav>
    </div><!-- End Page Title -->

    <section class="section">
      <div class="row">       
        <div class="col-lg-12">
          <div class="card">
            <div class="card-body" >
              <h5 class="card-title"> <fmt:message key="update.draft"/></h5>

              <!-- Default Tabs -->
              <ul style="background-color: #fbfbfb;"  class="nav nav-tabs d-flex" id="myTabjustified" role="tablist">
                <li class="nav-item flex-fill" role="presentation">
                  <button style="color: blue;" disabled="disabled" class="nav-link w-100 active" id="home-tab" data-bs-toggle="tab" data-bs-target="#tab1" type="button" role="tab" aria-controls="home" aria-selected="true">JOB DESCRIPTION</button>
                </li>
                <li class="nav-item flex-fill" role="presentation">
                  <button disabled="disabled" class="nav-link w-100"  data-bs-toggle="tab" data-bs-target="#tab2" type="button" role="tab" aria-controls="profile" aria-selected="false">PAPER OPTIONS</button>
                </li>
                <li class="nav-item flex-fill" role="presentation">
                  <button disabled="disabled" class="nav-link w-100" id="contact-tab" data-bs-toggle="tab" data-bs-target="#tab3" type="button" role="tab" aria-controls="contact" aria-selected="false">PRINTING OPTIONS</button>
                </li>
                <li class="nav-item flex-fill" role="presentation">
                  <button disabled="disabled" class="nav-link w-100" id="contact-tabm" data-bs-toggle="tab" data-bs-target="#tab4" type="button" role="tab" aria-controls="justified" aria-selected="false">FINISHING OPTIONS</button>
                </li>
                 <li class="nav-item flex-fill" role="presentation">
                  <button disabled="disabled" class="nav-link w-100" id="contact-taob" data-bs-toggle="tab" data-bs-target="#tab5" type="button" role="tab" aria-controls="samary" aria-selected="false">SUMMARY</button>
                </li>
              </ul>
           <form action="" method="post" id="myForm" style=" padding-left: 5%;">             
              <div class="tab-content pt-2" id="myTabjustifiedContent">
                <div class=" container tab-pane fade show active" id="tab1" role="tabpanel" aria-labelledby="home-tab">  
                              
<!-- <--------------------------------- TAB 1----------------------------------------------------------> 
                                            <div style="position: relative;bottom: -20px;" >		
			     <div class="row py-4">
				  <div class ="col-lg-4 px8" >					 
				   <label for="" class="form-label"><fmt:message key="customer"/></label>
				   <select id="customer"  class="form-select" readonly>
				   	<option selected>${job.customer.name}</option>
	                </select>
			      </div>
				  <div class ="col-lg-4 px8" style="position: relative; left: 10px;"> 
					<label for="" class="form-label"><fmt:message key="job.type"/></label> 
					<select id="jobType" name="jobType" class="form-select" >
					
                        <option value="${jobType.id}">${job.jobType.name}</option>
                     
                    </select>
				  </div>
				
				  <div class ="col-lg-3 px8" style="position: relative; left: 10px;">
				  <label for="title" class="form-label"><fmt:message key="title"/> </label>
				  
					<div>
					  <input id= "title"  type="text" name="title" type= "text" value="${job.title}">
					</div>
                  </div>
				</div>						
			     <div class="row py-3">
				  <div class ="col-lg-4 px8">
				    <label for="volumeofCover" class="form-label"><fmt:message key="volume.cover"/></label> 
				     <div>
					  <input id= "volumeOfCover" name="volumeOfCover" type="number" value=${job.coverVolume} >
				    </div>
				  </div>
				  <div class ="col-lg-4 px8" style="position: relative; left: 10px;"> 
				    <label for="volumeofContent"   class="form-label" ><fmt:message key="volume.content"/></label>
				    <div>
					  <input id= "volumeOfContent" name="volumeOfContent" value=${job.contentVolume}  type="number" onchange="totalContentVolumeChange()">
				    </div>
				  </div>
				  <div class ="col-lg-4 px8"  style="position: relative; left: 10px;">
				    <label for="" class="form-label"><fmt:message key="ctp.fees"/></label> 
					<input type="number" id="ctpFees" value=${job.ctpFees}>
			      </div>
		       </div>		
			 <div class="row py-3">
			  <div class ="col-lg-4 px8" >
				   <label for="" class="form-label"> <fmt:message key="format"/></label>
              	   <select id="paperFormat" onchange="paperF(this.value)" name="name" class="form-select">
              	      <option selected>${job.paperFormat}</option>
              	      <option onclick="">Custom Format...</option>
					  <c:forEach items="${paperFormats}" var="paperFormat">
                        <option value="${paperFormat.id}">${paperFormat.name}</option>
                      </c:forEach>
                    </select>
			      </div>
			   <div class ="col-lg-4 px8" style="position: relative; left: 10px;">
			     <label for="" class="form-label"> <fmt:message key="open.format"/></label> 
			       <div class="row">
				     <div class="col-6 volume-cover-l">
                       <input id="openWidth" type="number" value=${job.openWidth} style="postion-relative-left:2px;position: relative;left: 4px;"  placeholder= "<fmt:message key='open.width'/> ">
				      </div>
				      <div  class="col-6 volume-cover-w">
				      <input id="openLength" type="number"  value=${job.openLength} placeholder="<fmt:message key='open.legnth'/>">
					  
                   </div>
				 </div>
			  </div>
			  <div class ="col-lg-4 px8" style="position: relative; left: 10px;"> 
			   <label for="" class="form-label"><fmt:message key="close.format"/></label>
			     <div class="row">
				   <div class="col-6 volume-cover-l">
                     <input type="number" id="closeWidth" value=${job.closeWidth} style="postion-relative-left:2px;position: relative;left: 4px;"  placeholder= "<fmt:message key="open.width"/> ">
					                 
				     </div>
				     <div  class="col-6 volume-cover-w">
				       <input id="closeLength" type="number"  value=${job.closeLength} placeholder="<fmt:message key="open.legnth"/>">
					 
                    </div>
				  </div>
			   </div>
			
		      </div>	
		      <div class="row py-3">
			    <div class ="col-lg-3 px3">
                  <div class="form-check">
                    <label class="form-check-label" for="existingPlate"><fmt:message key="existing.plate"/></label>
                      <input class="form-check-input" type="checkbox" name="existingPlate" id="existingPlate" ${job.existingPlate ? 'checked':''}>
                  </div>
			    </div>
			    <div class ="col-lg-3 px3">
			      <div class="form-check">                     
                    <label class="form-check-label" for="gridCheck1"><fmt:message key="type.setting.by.us"/></label>
                    <input class="form-check-input" type="checkbox" name="name" id="typesettingByUs" ${job.typesettingByUs ? 'checked':''}>
                  </div>
                </div>
			  <div class ="col-lg-3 px3" style="position: relative; left: 10px;"> 
			     <div>
			      <div class="form-check">                      
                    <label class="form-check-label" for="gridCheck1"><fmt:message key="data.supplied.by.customer"/></label>
                    <input class="form-check-input" type="checkbox" id="dataSuppliedByCustomer" ${job.dataSuppliedByCustomer ? 'checked':''}>
                  </div>
                  
			    </div>
			  </div>
			  <div class ="col-lg-3 px3" style="position: relative; left: 10px;">
			  <div class="form-check">
                    <label class="form-check-label" for="gridCheck1"><fmt:message key="layout.by.us"/></label>
                    <input class="form-check-input" type="checkbox" name ="layOutByUs" id="layoutByUs" ${job.layOutByUs ? 'checked' : ''}>
                  </div>
			  </div>
		    </div>	
		     <div class="row py-3">    
			<button  style=" width: 94px;display:none" type="button"  class="btn btn-primary" onclick="tab1NextBtn(),summary()" id="next-btn-draft">Next</button>	
			<button  style=" position:relative;width: 94px;left:90%" type="button"  class="btn btn-primary" onclick="tab1NextBtn(),summaryDraftUpdate()" id="next-btn-draft" >Next</button>	
			
		   </div>  	
          </div>    
        </div>
        
 <!-- <----------------------------------- Tab2 ------------------------------->   
 
       <div class=" container tab-pane fade"  id="tab2" role="tabpanel" aria-labelledby="profile-tab">
         <div style="position: relative;bottom: -20px;" id="mainDiv" >	
         	
		   <div class="row py-4" id="coverInformations">
			 <div class ="col-lg-3 px8" >
			   <label for="" class="form-label"><a> <fmt:message key="cover.paper.type"/></a></label> 
			   <select id="coverPaperType" name="name" class="form-select">
			     <option selected>Choose...</option>
				 <c:forEach items="${paperTypes}" var="paperType">
                   <option value="${paperType.id}">${paperType.name}</option>
                 </c:forEach>
               </select>
			 </div>

			 <div class ="col-lg-3 px8" style="position: relative; left:10px;">
			   <label for="" class="form-label"><a><fmt:message key="grammage"/></a> </label>
			   <input style="postion-relative-left:2px;position: relative;left: 10px;" list="coverGrammage" id="coverGrammage" name="xx">
					   <datalist id="coverGrammage">
	                    <c:forEach items="${paperGrammages}" var="paperGrammage">
                    <option value="${paperGrammage.value}"></option>
                    </c:forEach>
	                   </datalist>  
              </div>
			  <div class ="col-lg-3 px8" style="position: relative; left: 10px;">
			    <label for="" class="form-label"><a><fmt:message key="cover.volume"/></a></label>
			     <input id="coverVolume" type="number"  name="fname" readonly="readonly">
              </div>
              
              <div class ="col-lg-3 px8" style="position: relative;">
			    <label for="" class="form-label"><a><fmt:message key="cover.paper.price"/></a></label>
			     <input id="coverPaperUnitPrice" type="number"  name="coverPaperUnitPrice">
              </div>
		    </div>	
		    
		   <div id="contentDiv">   		
			<div class="row py-3"  style="display:none">
			
			  <div class="col-lg-3 px-8" >
			    <label for="" class="form-label"><a> <fmt:message key="content.paper.type"/></a> </label>
				<select contentPaperType name="name" class="form-select">
				  <option >Choose...</option>
			  <c:forEach items="${paperTypes}" var="paperType">
                    <option value="${paperType.id}">${paperType.name}</option>
                  </c:forEach>
                </select>
			  </div>
			  <div class="col-lg-3 px-8" style="position: relative; left: 10px;">
			    <label for="" class="form-label"><a><fmt:message key="grammage"/></a> </label>
			     <input contentGrammage type="text"  list="contentGrammage" >
				  <datalist  id="contentGrammage">
	               <c:forEach items="${paperGrammages}" var="paperGrammage">
                    <option value="${paperGrammage.value}"></option>
                    </c:forEach>
	            </datalist>   
			  </div>
			 
			  <div class="col-lg-3 px-8 coverDup" style="position: relative; left: 10px;float:left">
			    <label for="" class="form-label"><a> <fmt:message key="content.volume"/> </a></label> 
				<input type="number" contentVolume  name="contentVolume" oldValue="" onclick="this.oldValue=this.value" onchange="updateTotalContentvolume(this.value,this.oldValue)"  >
			  </div>
			  
			   <div class ="col-lg-3 px8" style="position: relative;">
			    <label for="" class="form-label"><a><fmt:message key="content.paper.price"/></a> </label>
			     <input paperUnitPrice type="number"  name="paperUnitPrice">
			     <button type="button" id="deleteButton"  onclick="removeContentNode(this,this.previousElementSibling)"><i class="ri-delete-bin-3-line"></i> </button>
              </div>
			  
		   </div>
		   
		   <div class="row py-3"  >
			  <div class="col-lg-3 px-8">
			    <label for="" class="form-label"><a><fmt:message key="content.paper.type"/> </a> </label>
				<select contentPaperType name="name" class="form-select">
				  <option >Choose...</option>
			  <c:forEach items="${paperTypes}" var="paperType">
                    <option value="${paperType.id}">${paperType.name}</option>
                  </c:forEach>
                </select>
			  </div>
			  
			  <div class="col-lg-3 px-8" style="position: relative; left: 10px;">
			    <label for="" class="form-label"><a> <fmt:message key="grammage"/></a></label>
			     <input contentGrammage type="text" list="contentGrammage">
				  <datalist  id="contentGrammage">
	               <c:forEach items="${paperGrammages}" var="paperGrammage">
                    <option value="${paperGrammage.value}"></option>
                    </c:forEach>
	            </datalist>   
			  </div>
			  <div class="col-lg-3 px-8 " style="position: relative; left: 10px">
			    <label for="" class="form-label"><a><fmt:message key="content.volume"/></a> </label> 
				<input type="number" contentVolume name="contentVolume" readonly="readonly">
			  </div>

				<div class ="col-lg-3 px8" style="position: relative;">
			    <label for="" class="form-label"><a><fmt:message key="content.paper.price"/></a></label>
			     <input paperUnitPrice type="number"  name="paperUnitPrice">
              </div>
			   <div class="col-lg-3 px-8 " style="position: relative; left: 10px">
			       <label for="" class="form-label" style=""><a><fmt:message key="add"/></a></label> 
			       <span>
			       	<button type="button"  id="duplicateButton"  onclick="addContentPaperChild()" ><i class="ri-add-fill"></i>
			       	</button>

			       </span>
			  </div>
		   </div>
		   
		    </div>
		  
	     </div>	
	     <div class ="row py-3 "style="margin-top:50px" >
		     <div class ="col-sm-6"> <button type="button" style ="width:125px;float:left" class="btn btn-primary" onclick="navigate(2,1);"><fmt:message key="previews"/></button>	
		    </div>
	        <div class ="col-sm-6">
	        <button type="button"  style ="width:125px;float:right" onclick="navigate(2,3)"  class="btn btn-primary" id=""><fmt:message key="next"/></button>	
	       </div>

	     </div>
				
       </div>         
<!------------------------------- TAB 3 BIGINS --------------->
       <div class="tab-pane fade" id="tab3" role="tabpanel" aria-labelledby="contact-tab">
        <div class="container" >	
          <div class="row py-3" id="cover-signature-div">
		    <div class ="col-lg-3 px8" >
			  <label for="" class="form-label"><a><fmt:message key="cover.printing.machine"/></a></label> 
			  <select id="coverPrintingMachine" name="name" class="form-select" onchange="coverSignatureCalculation(this.value , this.parentNode)">
			    <option selected>Choose...</option>
			    <c:forEach items="${printingMachines}" var="printingMachine">
                  <option value="${printingMachine.id},${printingMachine.plateLength},${printingMachine.plateWidth}">${printingMachine.name}</option>
                </c:forEach>
              </select>
			</div>
			<div class ="col-lg-3 px8" style="position: relative; left: 10px;">
			  <label for="" class="form-label"><a><fmt:message key="cover.print.type"/></a></label>
			  <select id="coverPrintType" name="name" class="form-select">
			    <option selected>Choose...</option>
			    <c:forEach items="${printTypes}" var="printType">
                  <option value="${printType.id}">${printType.name}</option>
                </c:forEach>
              </select>
            </div>
			<div class ="col-lg-3 px8" style="position: relative; left: 10px;">
			  <label for="" class="form-label"><a><fmt:message key="cover.color.combination"/></a></label>
				 <div class="row">
				   <div class="col-6 volume-cover-l">
                     <input id="coverFrontColorNumber" placeholder="<fmt:message key='front'/>" type="number" min="0" max="5" style="postion-relative-left:2px;position: relative;left: 4px;">
					                    
				     </div>
				     <div  class="col-6 volume-cover-w">
				       <input id="converBackColorNumber" placeholder="<fmt:message key='back'/>" type="number" min="0" max="5">
					   
                    </div>
				  </div>
              </div>
              <div class ="col-lg-3 px8" style="position: relative; left:10px;">
			  <label for="" class="form-label"> <a><fmt:message key="signature"/></a></label>
			  <div style="width: 73px;"> <input class="coverSpace" type="number" step=".1" id="coverSignature" readonly="readonly">
			 </div> 
            </div>
			</div>	
			
		<!-- 			main content signature div -->
		
		
	
		
		<div id="mainContentSignature">
		
<!-- 		main duplicated div -->
			 <div style="display:none" id="eachSignatureDiv"> 
			 <hr style="margin-top: 10px; ">	
			  <div class="row py-3" style="display:none">
			    <div class ="col-lg-3 px8" >
				  <label for="" class="form-label"><a><fmt:message key="printing.machine"/></a></label> 
				  <select contentPrintingMachine name="name" class="form-select" disabled="disabled">
				    <option selected>Choose...</option>
				    <c:forEach items="${printingMachines}" var="printingMachine">
		                <option value="${printingMachine.id},${printingMachine.plateLength},${printingMachine.plateWidth}">${printingMachine.name}</option>
		            </c:forEach>
		         </select>
			   </div>
			   <div class ="col-lg-3 px8" style="position: relative; left: 10px;">
				 <label for="" class="form-label"><a><fmt:message key="content.print.type"/></a> </label>
				 <select contentPrintType name="name" class="form-select">
				   <option selected>Choose...</option>
				   <c:forEach items="${printTypes}" var="printType">
                     <option value="${printType.id}">${printType.name}</option>
                   </c:forEach>
                 </select>
               </div>
				  <div class ="col-lg-3 px8" style="position: relative; left: 10px;">
					<label for="" class="form-label"><a><fmt:message key="content.color.combination"/></a></label>
					 <div class="row">
				   <div class="col-6 content-cover-l">
                     <input contentFrontColorNumber placeholder="<fmt:message key='front'/>" type="number" min="0" max="5" style="postion-relative-left:2px;position: relative;left: 4px;">
					                  
				     </div>
				     <div class="col-6 volume-cover-w">
				       <input type="number" min="0" max="5" placeholder="<fmt:message key='back'/>" contentBackColorNumber>
					     
                    </div>
				  </div>
                  </div>
                  <div class ="col-lg-3 px8" style="position: relative; left:10px;">
				  <label for="" class="form-label"><a><fmt:message key="signature"/></a></label>
				  <div> 
				  <input type="number" step=".1" delContentSign allSignatures style="width:70px;color:red; text-align:center" onclick="this.oldValue=this.value" onchange="signatureChange(this.value,this.parentNode.parentNode.parentNode.parentNode,oldValue)">
				  <span> <button  type="button" onclick="deleteContentsignature(this.parentNode.parentNode.parentNode.parentNode,this.parentNode.parentNode.parentNode.parentNode.parentNode)" style="background:red"><i class="ri-delete-bin-3-line"></i></i></button> </span>
				 </div> 
	            </div>
	           
				</div>

			  <div class="row py-3" >
			    <div class ="col-lg-3 px8" >
				  <label for="" class="form-label"><a><fmt:message key="printing.machine"/></a></label> 
				  <select  contentPrintingMachine name="name" class="form-select" onchange="signatureCalculation(this.value,this.parentNode.parentNode.parentNode)">
				    <option selected>Choose...</option>
				    <c:forEach items="${printingMachines}" var="printingMachine">
		                <option value="${printingMachine.id},${printingMachine.plateLength},${printingMachine.plateWidth}">${printingMachine.name}</option>
		            </c:forEach>
		         </select>
			   </div>
			   <div class ="col-lg-3 px8" style="position: relative; left: 10px;">
				 <label for="" class="form-label"><a><fmt:message key="content.print.type"/> </a></label>
				 <select contentPrintType name="name" class="form-select">
				   <option selected>Choose...</option>
				   <c:forEach items="${printTypes}" var="printType">
                     <option value="${printType.id}">${printType.name}</option>
                   </c:forEach>
                 </select>
               </div>
				  <div class ="col-lg-3 px8" style="position: relative; left: 10px;">
					<label for="" class="form-label"><a><fmt:message key="content.color.combination"/></a></label>
					 <div class="row">
				   <div class="col-6 volume-cover-l">
                     <input contentFrontColorNumber type="number" min="0" placeholder="<fmt:message key='front'/>" max="5" style="postion-relative-left:2px;position: relative;left: 4px;">
					                  
				     </div>
				     <div class="col-6 volume-cover-w">
				       <input type="number" min="0" max="5" placeholder="<fmt:message key='back'/>" contentBackColorNumber>
					     
                    </div>
				  </div>
                  </div>
                  <div class ="col-lg-3 px8" style="position: relative; left:10px;">
				  <label for="" class="form-label"><a><fmt:message key="signature"/></a></label>
				  <div> <input type="number" step=".1" id="" style="width:70px;color:red; text-align:center" allSignatures inputSignReadonly>
				  <span> <button   type="button" id="duplicateButton" style="display: inline;" onclick="updateContentSignature(this.parentNode.parentNode.parentNode.parentNode.parentNode,1,this.parentNode.parentNode.parentNode.parentNode)"><i class="ri-add-fill"></i></button> </span>
				 </div> 
	            </div>
				</div>
			</div>	
			
			 <div  id="signatureDiv">
			  <div class="row py-3" style="display:none">
			 
			    <div class ="col-lg-3 px8">
				  <label for="" class="form-label"><a><fmt:message key="printing.machine"/></a></label> 
				  <select contentPrintingMachine name="name" class="form-select"  disabled="disabled" >
				    <option selected>Choose...</option>
				    <c:forEach items="${printingMachines}" var="printingMachine">
		                <option value="${printingMachine.id},${printingMachine.plateLength},${printingMachine.plateWidth}">${printingMachine.name}</option>
		            </c:forEach>
		         </select>
			   </div>
			   <div class ="col-lg-3 px8" style="position: relative; left: 10px;">
				 <label for="" class="form-label"><a><fmt:message key="content.print.type"/> </a></label>
				 <select contentPrintType name="name" updateContentSignature class="form-select">
				   <option selected>Choose...</option>
				   <c:forEach items="${printTypes}" var="printType">
                     <option value="${printType.id}">${printType.name}</option>
                   </c:forEach>
                 </select>
               </div>
				  <div class ="col-lg-3 px8" style="position: relative; left: 10px;">
					<label for="" class="form-label"><a><fmt:message key="content.color.combination"/></a></label>
				<div class="row">
				   <div class="col-6 volume-cover-l">
                     <input contentFrontColorNumber type="number" min="0" placeholder="<fmt:message key='front'/>" max="5" style="postion-relative-left:2px;position: relative;left: 4px;">                 
				     </div>
				     <div class="col-6 volume-cover-w">
				       <input type="number" min="0" max="5"  contentBackColorNumber placeholder="<fmt:message key='back'/>">  
                    </div>
				  </div>
                  </div>
                  <div class ="col-lg-3 px8" style="position: relative; left:10px;">
				  <label for="" class="form-label"><a><fmt:message key="signature"/></a></label>
				  <div>
				  <input type="number" step=".1" delContentSign allSignatures style="width:70px;color:red; text-align:center" onclick="this.oldValue=this.value" onchange="signatureChange(this.value,this.parentNode.parentNode.parentNode.parentNode, oldValue)">
				  <span> <button style="background:transparent;border-style: none;color: orange; font-size: 20px;" type="button" onclick="deleteContentsignature(this.parentNode.parentNode.parentNode.parentNode,this.parentNode.parentNode.parentNode.parentNode.parentNode)" style="background:red"><i class="ri-delete-bin-3-line"></i></i></button> </span>
				 </div> 
	            </div>
	          		  
				</div>

			  <div class="row py-3" id="test-me">
			   <div class ="col-lg-3 px8" >
				  <label for="" class="form-label"><a><fmt:message key="printing.machine"/></a></label> 
				  <select  contentPrintingMachine name="name" class="form-select"  onchange="signatureCalculation(this.value,this.parentNode.parentNode.parentNode)">
				    <option selected>Choose...</option>
				    <c:forEach items="${printingMachines}" var="printingMachine">
		                <option value="${printingMachine.id},${printingMachine.plateLength},${printingMachine.plateWidth}">${printingMachine.name}</option>
		            </c:forEach>
		         </select>
			   </div>
			   <div class ="col-lg-3 px8" style="position: relative; left: 10px;">
				 <label for="" class="form-label"><a><fmt:message key="content.print.type"/></a> </label>
				 <select contentPrintType name="name" class="form-select">
				   <option selected>Choose...</option>
				   <c:forEach items="${printTypes}" var="printType">
                     <option value="${printType.id}">${printType.name}</option>
                   </c:forEach>
                 </select>
               </div>
				  <div class ="col-lg-3 px8" style="position: relative; left: 10px;">
					<label for="" class="form-label"><a><fmt:message key="content.color.combination"/></a></label>
					 <div class="row">
				   <div class="col-6 volume-cover-l">
                     <input contentFrontColorNumber type="number" min="0" max="5" placeholder="<fmt:message key='front'/>" style="postion-relative-left:2px;position: relative;left: 4px;" >
					                  
				     </div>
				     <div class="col-6 volume-cover-w">
				       <input type="number" min="0" max="5" placeholder="<fmt:message key='back'/>"  contentBackColorNumber>
					     
                    </div>
				  </div>
                  </div>
                  <div class ="col-lg-3 px8" style="position: relative; left:10px;">
				  <label for="" class="form-label"><a><fmt:message key="signature"/></a></label>
				  <div> <input type="number" id="" step=".1" style="width:70px;color:red; text-align:center" readonly="readonly" allSignatures inputSignReadonly>
				  <span><button  type="button" style="display: inline;" id="duplicateButton" onclick="updateContentSignature(this.parentNode.parentNode.parentNode.parentNode.parentNode,0,this.parentNode.parentNode.parentNode.parentNode)" ><i class="ri-add-fill"></i></button> </span>
				 </div> 
	            </div>
				</div>
		
				</div>
					
<!-- 							duplicated div ends 	-->
				</div> 
	
			<div class ="row py-3 "style="margin-top:50px" >
		     <div class ="col-sm-6"> 
		      <button type="button" style="width:125px;float:left" class="btn btn-primary" onclick="navigate(3,2);"><fmt:message key="previews"/></button>	
		    </div>
	        <div class ="col-sm-6">
	        	
	         <button style="width:125px;float:right"  type="button" class="btn btn-primary" id="next-btn1" onclick="navigate(3,4);"><fmt:message key="next"/></button>	
	        		
	       </div>

	     </div>
			
			     	<!-- 			main content div -->
               </div>
             </div>  	<!-- 			main div ends -->
          
<!-- <--------------------TAB 4 BEGINS HERE----------------------------------------------------------------------->  
             <div class="tab-pane fade" id="tab4" role="tabpanel" aria-labelledby="contact-tab">
                <div class="container" style="position: relative;bottom: -20px;" >
                <div class="row py-4">
				 
				 <div class ="col-lg-3 px8">
					<label for="" class="form-label"><a><fmt:message key="x.Perforated"/></a></label>
					<input type="number" id="xPerforated" value="0">
                  </div>
				  <div class ="col-lg-3 px8" style="position: relative; left: 10px;">
					<label for="" class="form-label"><a><fmt:message key="x.numbered"/></a></label>
					<input type="number" id="xNumbered" value="0">
                  </div>
                    <div class ="col-lg-3 px8" style="position: relative; left:10px;">
			   <label for="" class="form-label"><a><fmt:message key="lamination"/></a></label> 
			      <select id="lamination" name="name" class="form-select">
			        <option value="0" selected>0</option>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
                  </select>
			    </div>
				</div>	
				   <div class="row py-4">
				  <div class ="col-lg-3 px8" >
				    <label for="" class="form-label"><a><fmt:message key="creased"/></a></label> 
					<input type="number" id="creased" value="0">
			      </div>
				 <div class ="col-lg-3 px8" style="position: relative; left:10px;">
					<label for="" class="form-label"><a><fmt:message key="x.Wire-stitched"/></a></label>
					<input type="number" id="xWire-stitched" value="0">
                  </div>
				  <div class ="col-lg-3 px8" style="position: relative; left: 10px;">
					<label for="" class="form-label"><a><fmt:message key="x.cross"/></a></label>
					<input type="number" id="xcross" value="0">
                  </div>
				</div>	
				 <div class="row py-4">
			     <div class ="col-lg-3 px8" style="position: relative; left:10px;">
			   <label for="" class="form-label"><a><fmt:message key="binding.type"/></a></label> 
			      <select id="bindingType" name="name" class="form-select">
			        <option value="0" selected>Choose...</option>
					<c:forEach items="${bindingTypes}" var="bindingTyp">
                      <option value="${bindingTyp.id}">${bindingTyp.name}</option>
                    </c:forEach>
                  </select>
			    </div>
			    
			    <div class ="col-lg-3 px8" style="position: relative; left:10px;">
				  <label for="" class="form-label"><a><fmt:message key="handFoldCov"/></a></label>
				  <input type="number" id="handFoldCov" value="0">
                </div>
			 
			   </div>
    
                  <div class="row py-3">
				   <div class ="col-lg-2 px3" >
				    <div>
                     <div class="form-check">
                      <label class="form-check-label" for="trimmed"><a><fmt:message key="trimmed"/></a></label>
                       <input class="form-check-input" type="checkbox" id="trimmed">
                    </div>
				  </div>
			    </div>
				<div class ="col-lg-2 px3" style="position: relative; left: 10px;">
				  <div>
                     <div class="form-check">
                      <label class="form-check-label" for="sellotaped"><a><fmt:message key="sellotaped"/></a></label>
                       <input class="form-check-input" type="checkbox" id="sellotaped">
                    </div>
				  </div>
                  </div>
				  <div class ="col-lg-2 px3" style="position: relative; left: 10px;">
                     <div class="form-check">
                      <label class="form-check-label" for="sewn"><a><fmt:message key="sewn"/></a></label>
                       <input class="form-check-input" type="checkbox" id="sewn">
                    </div>
				  </div>
				   <div class ="col-lg-2 px3" >
				    <div>
                     <div class="form-check">
                      <label class="form-check-label" for="handgather"><a><fmt:message key="handgather"/></a></label>
                       <input class="form-check-input" type="checkbox" id="handgather">
                    </div>
				  </div>
			    </div>
			    <div class ="col-lg-2 px3" style="position: relative; left: 10px;">
				  <div>
                      <div class="form-check">
                      <label class="form-check-label" for="stitching"><a><fmt:message key="stitching"/></a></label>
                       <input class="form-check-input" type="checkbox" id="stitching">
                    </div>
				  </div>
                  </div>
                  </div>
     
<!--                    <div class="row py-3"> -->
<!-- 				   <div class ="col-lg-3 px3" > -->
<!-- 				    <div> -->
<!--                      <div class="form-check"> -->
<%--                       <label class="form-check-label" for="handgather"><a><fmt:message key="handgather"/></a></label> --%>
<!--                        <input class="form-check-input" type="checkbox" id="handgather"> -->
<!--                     </div> -->
<!-- 				  </div> -->
<!-- 			    </div> -->
				
<!--                   </div> -->
                  
				</div>	
		           <div class ="row py-3 "style="margin-top:50px" >
				     <div class ="col-sm-6"> 
				      <button type="button" style="float:left" class="btn btn-primary" onclick="navigate(4,3);"> <fmt:message key="previews"/></button>	
				     </div>
			         <div class ="col-sm-6">
			         <button type="button" style="width:125px;float:right"  class="btn btn-primary" id="next-btn1" onclick="navigate(4,5),summaryUpdate()"><fmt:message key="next"/></button>			
			        </div>
			        
			        
<!-- 			         <div class ="col-sm-6">  -->
<%-- 				      <button type="button" style="float:left" class="btn btn-primary" onclick="navigate(4,3);"> <fmt:message key="previews"/></button>	 --%>
<!-- 				     </div> -->
			        </div>
               	</div>
               	
               	<!-- <--------------------TAB 5 BEGINS HERE----------------------------------------------------------------------->  
             <div class="tab-pane fade" id="tab5" role="tabpanel" aria-labelledby="contact-tab">
                <div class="container" style="position: relative;bottom: -20px;" >
       				<h4><fmt:message key="job.description"/></h4>
       				<hr>
					  <div class="row">
					 	 <div class="row">
					      <div class="col-sm-4"><fmt:message key="the.type.of.job"/>  : <span id="job-type"> </span>
					      </div>
					      <div class="col-sm-4"><fmt:message key="title.of.job"/> : <span id="job-title"> </span> 
					      </div>
					      <div class="col-sm-4"><fmt:message key="name.of.customer"/>  : <span id="job-customer">  </span> 
					      </div>
					    </div>
					   
					   	 <div class="row">
					    <div class="col-sm-4" id="cover-pages-info"><fmt:message key="number.of.pages.for.cover"/> : <span id="cover-pages"> </span>
					    </div>
					    <div class="col-sm-4"  id="content-pages-info"><fmt:message key="number.of.pages.for.content"/> :  <span id="content-pages"> </span> 
					    </div>
					    <div class="col-sm-4"><fmt:message key="ctp.fees"/> : <span id="ctp">  </span>
					    </div>
					   </div>
					   
					     <div class="row">
					    <div class="col-sm-4"><fmt:message key="paper.format"/> : <span id="paper-format"> </span>
					    </div>
					    <div class="col-sm-4"><fmt:message key="open"/> :<span id="open-l"></span> | <span id="open-w"></span></div> 
					    	<div class="col-sm-4"><fmt:message key="fold"/>  :<span id="fold-l"></span> | <span id="fold-w"></span>
					    </div>
					    </div>
					    <div class="row">
					    <div class="col-sm-4"><fmt:message key="existing.plate"/> : <span id="existing-plate"></span>
					    </div>
					    <div class="col-sm-4"><fmt:message key="data.suply.by.us"/>  : <span id="supply-data"></span>
					    </div>
					    <div class="col-sm-4"><fmt:message key="lay.out.by.us"/>  : <span id="data-layout"></span>
					    </div> 
					    <div class="col-sm-4" id=""><fmt:message key="type.setting.by.us"/> : <span id="type-setting"></span> 
					    </div>
					    </div>
					   </div>
					   
					<!--            job decription ends     -->
					<div id="cover-papers-options-info">
					<br>
					<h4 id="top"><fmt:message key="cover.paper.option"/></h4>
					<hr>
					   <div class="row">
					    <div class="row">
					    <div class="col-sm-4"><fmt:message key="paper.types"/> : <span id="cover-paper"> </span>
					    </div>
					    <div class="col-sm-4"><fmt:message key="paper.grammage"/> (GSM) : <span id="cover-grammage"> 1</span> 
					    </div>
					    <div class="col-sm-4"><fmt:message key="volume"/> : <span id="cover-volume">  </span> 
					    </div>
					   </div>
					   </div>
					  </div> 
					  
					<div id="content-papers-options-info">
					<br>
					<h4 id="top"><fmt:message key="content.paper.option"/></h4>
					<hr>
					    <div class="row">
					   <table class="ta" id="cover-table">
					  <thead>
					    <tr>
					      <th scope="col"><fmt:message key="number"/> </th>
					      <th scope="col"><fmt:message key="print.type"/> </th>
					      <th scope="col"> <fmt:message key="grammage"/> (GSM)</th>
					      <th scope="col"> <fmt:message key="volume"/> (Pages)</th>
					    </tr>
					  </thead>
					  <tbody>
					  </tbody>
					</table>
					   </div>
					 </div>
					 <div id="cover-printing-options-info">  
					 <br>
					 <h4> <fmt:message key="cover.printing.option"/></h4>
					 <hr>
						<div class="row">
					    <div class="row">
					     <div class="col-sm-3"><fmt:message key="machine"/> :  <span id="cover-machine"> </span>
					    </div>
					    <div class="col-sm-3"><fmt:message key="print.type"/> : <span id="cover-printtype"> </span> 
					    </div>
					    <div class="col-sm-3"><fmt:message key="color.combination"/> : <span id=cover-color-front></span> / <span id=cover-color-back></span> 
					    </div>
					     <div class="col-sm-3"><fmt:message key="signature"/> : <span id=cover-signature></span> 
					    </div>
					    </div>
					  </div>
					  </div>
					  <div id="content-printing-options-info">
					   <h4><fmt:message key="content.printing.option"/></h4>
					 <hr>
					  
					  <div class="row">
						<table class="ta" id="content-table">
					  <thead>
					    <tr>
					      <th scope="col"><fmt:message key="number"/></th>
					      <th scope="col"> <fmt:message key="print.type"/> </th>
					      <th scope="col"><fmt:message key="machine"/></th>
					      <th scope="col"> </th>
					      <th scope="col"><fmt:message key="color.combination"/> </th>
					      <th scope="col"><fmt:message key="signature"/></th>
					    </tr>
					  </thead>
					  <tbody id="table-body">
					
					  </tbody>
					</table>
					   </div>
					  </div>
					  
						<br>
						 <h4><fmt:message key="finishing.option"/> </h4>
						 <hr>
						 <div class="row ">
					    <div class="col-sm-4">
					    	<div> <fmt:message key="x.perforated" /> : <span id="x-perforated"></span> </div>
					    	<div> <fmt:message key="x.numbered" />: <span id="x-numbered"></span> </div>
					    	<div> <fmt:message key="x.crossed"/> : <span id="x-crossed"></span></div>
					    	<div><fmt:message key="x.Wired.stitched"/> : <span id="x-wired"></span> </div>
					    	<div> <fmt:message key="creased"/> : <span id="crease"></span> </div>
					    </div>
					    <div class="col-sm-4">
					    	<div> <fmt:message key="lamination"/>: <span id="laminated-sides"></span> </div>
<!-- 					    	<div> Glueing Bound: <span id="glue-bound"></span> </div> -->
					    	<div> <fmt:message key="binding.type"/> : <span id="binding-type"></span> </div>
					    	<div> <fmt:message key="sewn" /> : <span id="sown"></span> </div>
					    </div>
					    
					    <div class="col-sm-4">
					    	<div><fmt:message key="handgather"/> : <span id="hand-gather"></span> </div>
					    	<div> <fmt:message key="stitching"/> : <span id="stitch"></span> </div>
					    	<div> <fmt:message key="trimmed"/> : <span id="trim"></span> </div>
					    	<div> <fmt:message key="sellotaped"/> : <span id="sello-tape"></span> </div>
					    </div>
					 </div>
		           <div class ="row py-3 "style="margin-top:50px" >
				     <div class ="col-sm-6"> 
				      <button type="button" style="float:left" class="btn btn-primary" onclick="navigate(5,4);removeRows()"> <fmt:message key="previews"/></button>	
				     </div>
			         <div class ="col-sm-6">
			         <button   type="button" style="width:125px;float:right"  class="btn btn-primary" id="next-btn1" onclick="navigate(4,5); submitForm(); loadPage('job/list-job')"><fmt:message key="submit"/></button>			
			        </div>
			        </div>
               	</div>
               	<!-- <--------------------tab 5 ends ----------------------------------------------------------------------->  
               	</div>
               </form>  
            </div>
         </div>
      </div>
      
      <!-- End Default Tabs -->
      </div>
    </section>  
<script src="assets/js/billing/job.js"></script> 

   
  
  
  
  
  
  