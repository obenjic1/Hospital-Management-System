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

    <section class="section">
      <div class="row">       
        <div class="col-lg-12">
          <div class="card">
            <div class="card-body" >
            <strong><h5 class="card-title" style="text-align:center"><fmt:message key="update.job"/> <i>${job.referenceNumber}</i></h5></strong>
              
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
				  <div class ="col-lg-3 px8" >					 
				   <label for="" class="form-label"><fmt:message key="customer"/></label>
				   <select id="customer"  class="form-select" >
				   	<option selected>${job.customer.name}</option>
	                 <c:forEach items="${customers}" var="customer">
	                   <option value="${customer.id}">${customer.name}</option>
	                 </c:forEach>
	                 
	                </select>
			      </div>
				  <div class ="col-lg-3 px8" style="position: relative; left: 10px;"> 
					<label for="" class="form-label"><fmt:message key="job.type"/></label> 
<!-- 					<select id="jobType" name="jobType" class="form-select" > -->
<%-- 					  <c:forEach items="${jobTypes}" var="jobType"> --%>
<%--                         <option value="${jobType.id}" >${job.jobType.name}</option> --%>
<%--                       </c:forEach> --%>
                     
<!--                     </select> -->
                    
                    <select onchange="jobTypeChoice(this.selectedOptions[0])"  id="jobType" name="jobType" class="form-select" >
					  <optgroup label="<fmt:message key="job.category.folded.two"/>" data-content="2">
					  <c:forEach items="${jobTypes}" var="jobType">
					  <c:if test="${jobType.category==2}">
                        <option style="marging-left: %;" value="${jobType.id}" >${jobType.name}</option>
                         </c:if>
                      </c:forEach>
                      </optgroup>
                       <optgroup label="<fmt:message key="job.category.folded.one"/>" data-content="1">
                     
                       <c:forEach items="${jobTypes}" var="jobType">
                        <c:if test="${jobType.category==1}">
                        <option value="${jobType.id}" >${jobType.name}</option>
                        </c:if>
                      </c:forEach>
                       </optgroup>
                       <optgroup   label="<fmt:message key="job.category.opened"/>" data-content="0">
                      
                       <c:forEach items="${jobTypes}" var="jobType">
                       <c:if test="${jobType.category==0}">
                        <option value="${jobType.id}" >${jobType.name}</option>
                         </c:if>
                      </c:forEach>
                     </optgroup>
                    </select>
				  </div>
				  <div class ="col-lg-3 px8" style="position: relative; left: 10px;">
				  <label for="title" class="form-label"><fmt:message key="title"/></label>
					<div>
					  <input id= "title" name="title" type= "text" value="${job.title}">
					</div>
                  </div>
				</div>						
			     <div class="row py-3">
				  <div class ="col-lg-3 px8">
				    <label for="coverVolume" class="form-label"><fmt:message key="volume.cover"/></label> 
				     <div>
					  <input id= "volumeOfCover" name="volumeOfCover" type="number" value=${job.coverVolume}>
				    </div>
				  </div>
				  <div class ="col-lg-3 px8" style="position: relative; left: 10px;"> 
				    <label for="volumeOfContent"   class="form-label" ><fmt:message key="volume.content"/></label>
				    <div>
					  <input id= "volumeOfContent" name="volumeOfContent" value=${job.contentVolume}  type="number" onchange="totalContentVolumeChange()">
				    </div>
				  </div>
				  <div class ="col-lg-3 px8"  style="position: relative; left: 10px;">
				    <label for="" class="form-label"><fmt:message key="ctp.fees"/></label> 
					<input type="number" id="ctpFees" value=${job.ctpFees}>
			      </div>
		       </div>		
			 <div class="row py-3">
			  <div class ="col-lg-3 px8" >
				   <label for="" class="form-label"> <fmt:message key="format"/></label>
              	   <select id="paperFormat" onchange="paperF(this.value)" name="name" class="form-select">
              	      <option selected>${job.paperFormat}</option>
              	      <option onclick="">Custom Format...</option>
					  <c:forEach items="${paperFormats}" var="paperFormat">
                        <option value="${paperFormat.id},${paperFormat.length},${paperFormat.width}">${paperFormat.name}</option>
                      </c:forEach>
                    </select>
			      </div>
			   <div class ="col-lg-3 px8" style="position: relative; left: 10px;">
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
			  <div class ="col-lg-3 px8" style="position: relative; left: 10px;"> 
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
                    <input class="form-check-input" type="checkbox" id="dataSuppliedByCustomer" ${job.dataSuppliedByCustomer ? 'checked' : ''}>
                  </div>
                  
			    </div>
			  </div>
			  <div class ="col-lg-3 px3" style="position: relative; left: 10px;">
			  <div class="form-check">
                    <label class="form-check-label" for="gridCheck1"><fmt:message key="layout.by.us"/></label>
                    <input class="form-check-input" type="checkbox" id="layoutByUs" ${job.layOutByUs ? 'checked' : ''}>
                  </div>
			  </div>
		    </div>	
		     <div class="row py-3">    
			<button  style=" width: 94px;" type="button"  class="btn btn-primary" onclick="tab1NextBtnAction()" id="next-btn" >Next</button>	
		   </div>  	
          </div>             
        </div>
        
 <!-- <----------------------------------- Tab2 ------------------------------->   
 
       <div class=" container tab-pane fade"  id="tab2" role="tabpanel" aria-labelledby="profile-tab">
         <div style="position: relative;bottom: -20px;" id="mainDiv" >	
         	
		   <div class="row py-4" id="coverInformations">
			  <div class ="col-lg-3 px8" >
			   <label for="" class="form-label"><a> <fmt:message key="cover.paper.type"/></a></label> 
			   <select coverPaperType name="name" class="form-select">
				 <c:forEach items="${paperTypes}" var="paperType">
                   <option value="${paperType.id}">${paperType.name}</option>
                 </c:forEach>
               </select>
			 </div>
			 <div class ="col-lg-3 px8" style="position: relative; left:10px;">
			   <label for="" class="form-label"><a><fmt:message key="grammage"/></a> </label>
			   <input style="postion-relative-left:2px;position: relative;left: 10px;" value="${coverJobPaper.grammage}" list="coverGrammage" id="coverGrammage" name="xx">
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
			     <input id="coverPaperUnitPrice" type="number" value="${coverJobPaper.unitPrice}" name="coverPaperUnitPrice">
              </div>
		    </div>	
		    
		   <div id="contentDiv">   		
			<div class="row py-3"  style="display:none">
			
			  <div class="col-lg-3 px-8" >
			    <label for="" class="form-label"><a> <fmt:message key="content.paper.type"/></a> </label>
				<select contentPaperType name="name" class="form-select">
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
			     <input paperUnitPrice type="number"  name="paperUnitPrice" value="">
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
			     <input contentGrammage type="text" list="contentGrammage" value =>
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
			     <input paperUnitPrice type="number"  name="paperUnitPrice" value="${contentJobPaper.unitPrice}">
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
          <div class="row py-3">
		    <div class ="col-lg-3 px8" >
			  <label for="" class="form-label"><fmt:message key="cover.printing.machine"/></label> 
			  <select id="coverPrintingMachine" name="name" class="form-select">
			    <c:forEach items="${printingMachines}" var="printingMachine">
                  <option value="${printingMachine.id}">${printingMachine.name}</option>
                </c:forEach>
              </select>
			</div>
			<div class ="col-lg-3 px8" style="position: relative; left: 10px;">
			  <label for="" class="form-label"><fmt:message key="cover.print.type"/></label>
			  <select id="coverPrintType" name="name" class="form-select">
			    <c:forEach items="${printTypes}" var="printType">
                  <option value="${printType.id}">${printType.name}</option>
                </c:forEach>
              </select>
            </div>
			<div class ="col-lg-3 px8" style="position: relative; left: 10px;">
			  <label for="" class="form-label"><fmt:message key="cover.color.combination"/></label>
				 <div class="row">
				   <div class="col-6 volume-cover-l">
                     <input id="coverFrontColorNumber" placeholder="<fmt:message key='front'/>" type="number" min="0" max="5" style="postion-relative-left:2px;position: relative;left: 4px;" value="${covercolourCombination.frontColorNumber}">
					                    
				     </div>
				     <div  class="col-6 volume-cover-w">
				       <input id="converBackColorNumber" placeholder="<fmt:message key='back'/>" type="number" min="0" max="5" value="${covercolourCombination.frontColorNumber}">
					   
                    </div>
				  </div>
              </div>
              <div class ="col-lg-3 px8" style="position: relative; left:10px;">
			  <label for="" class="form-label"> <fmt:message key="signature"/></label>
			  <div> <span class="coverSpace" id="coverSignature">1</span>
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
				  <label for="" class="form-label"><fmt:message key="printing.machine"/></label> 
				  <select contentPrintingMachine name="name" class="form-select" disabled="disabled">
				    <option selected>Choose...</option>
				    <c:forEach items="${printingMachines}" var="printingMachine">
		                <option value="${printingMachine.id},${printingMachine.plateLength},${printingMachine.plateWidth}">${printingMachine.name}</option>
		            </c:forEach>
		         </select>
			   </div>
			   <div class ="col-lg-3 px8" style="position: relative; left: 10px;">
				 <label for="" class="form-label"><fmt:message key="content.print.type"/> </label>
				 <select contentPrintType name="name" class="form-select">
				   <option selected>Choose...</option>
				   <c:forEach items="${printTypes}" var="printType">
                     <option value="${printType.id}">${printType.name}</option>
                   </c:forEach>
                 </select>
               </div>
				  <div class ="col-lg-3 px8" style="position: relative; left: 10px;">
					<label for="" class="form-label"><fmt:message key="content.color.combination"/></label>
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
				  <label for="" class="form-label"><fmt:message key="signature"/></label>
				  <div> 
				  <input type="number" step=".1" delContentSign style="width:70px;color:red; text-align:center" onclick="this.oldValue=this.value" onchange="signatureChange(this.value,this.parentNode.parentNode.parentNode.parentNode,oldValue)">
				  <span> <button  type="button" onclick="deleteContentsignature(this.parentNode.parentNode.parentNode.parentNode,this.parentNode.parentNode.parentNode.parentNode.parentNode)" style="background:red"><i class="ri-delete-bin-3-line"></i></i></button> </span>
				 </div> 
	            </div>
	           
				</div>

			  <div class="row py-3" >
			    <div class ="col-lg-3 px8" >
				  <label for="" class="form-label"><fmt:message key="printing.machine"/></label> 
				  <select  contentPrintingMachine name="name" class="form-select" onchange="signatureCalculation(this.value,this.parentNode.parentNode.parentNode)">
				    <option selected>Choose...</option>
				    <c:forEach items="${printingMachines}" var="printingMachine">
		                <option value="${printingMachine.id},${printingMachine.plateLength},${printingMachine.plateWidth}">${printingMachine.name}</option>
		            </c:forEach>
		         </select>
			   </div>
			   <div class ="col-lg-3 px8" style="position: relative; left: 10px;">
				 <label for="" class="form-label"><fmt:message key="content.print.type"/> </label>
				 <select contentPrintType name="name" class="form-select">
				   <option selected>Choose...</option>
				   <c:forEach items="${printTypes}" var="printType">
                     <option value="${printType.id}">${printType.name}</option>
                   </c:forEach>
                 </select>
               </div>
				  <div class ="col-lg-3 px8" style="position: relative; left: 10px;">
					<label for="" class="form-label"><fmt:message key="content.color.combination"/></label>
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
				  <label for="" class="form-label"><fmt:message key="signature"/></label>
				  <div> <input type="number" step=".1" id="" style="width:70px;color:red; text-align:center" inputSignReadonly>
				  <span> <button   type="button" id="duplicateButton" style="display: inline;" onclick="updateContentSignature(this.parentNode.parentNode.parentNode.parentNode.parentNode,1,this.parentNode.parentNode.parentNode.parentNode)"><i class="ri-add-fill"></i></button> </span>
				 </div> 
	            </div>
				</div>
			</div>	
			
			 <div  id="signatureDiv">
			  <div class="row py-3" style="display:none">
			 
			    <div class ="col-lg-3 px8">
				  <label for="" class="form-label"><fmt:message key="printing.machine"/></label> 
				  <select contentPrintingMachine name="name" class="form-select"  disabled="disabled" >
				   <option >Choose...</option>
				    <c:forEach items="${printingMachines}" var="printingMachine">
		                <option value="${printingMachine.id},${printingMachine.plateLength},${printingMachine.plateWidth}">${printingMachine.name}</option>
		            </c:forEach>
		         </select>
			   </div>
			   <div class ="col-lg-3 px8" style="position: relative; left: 10px;">
				 <label for="" class="form-label"><fmt:message key="content.print.type"/> </label>
				 <select contentPrintType name="name" updateContentSignature class="form-select">
				  <option >Choose...</option>
				   <c:forEach items="${printTypes}" var="printType">
                     <option value="${printType.id}">${printType.name}</option>
                   </c:forEach>
                 </select>
               </div>
				  <div class ="col-lg-3 px8" style="position: relative; left: 10px;">
					<label for="" class="form-label"><fmt:message key="content.color.combination"/></label>
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
				  <label for="" class="form-label"><fmt:message key="signature"/></label>
				  <div>
				  <input type="number" step=".1" delContentSign style="width:70px;color:red; text-align:center" onclick="this.oldValue=this.value" onchange="signatureChange(this.value,this.parentNode.parentNode.parentNode.parentNode, oldValue)">
				  <span> <button style="background:transparent;border-style: none;color: orange; font-size: 20px;" type="button" onclick="deleteContentsignature(this.parentNode.parentNode.parentNode.parentNode,this.parentNode.parentNode.parentNode.parentNode.parentNode)" style="background:red"><i class="ri-delete-bin-3-line"></i></i></button> </span>
				 </div> 
	            </div>
	          		  
				</div>

			  <div class="row py-3" id="test-me">
			   <div class ="col-lg-3 px8" >
				  <label for="" class="form-label"><fmt:message key="printing.machine"/></label> 
				  <select  contentPrintingMachine name="name" class="form-select"  onchange="signatureCalculation(this.value,this.parentNode.parentNode.parentNode)">
				   		<option >Choose...</option>
				    <c:forEach items="${printingMachines}" var="printingMachine">
		                <option value="${printingMachine.id},${printingMachine.plateLength},${printingMachine.plateWidth}">${printingMachine.name}</option>
		            </c:forEach>
		         </select>
			   </div>
			   <div class ="col-lg-3 px8" style="position: relative; left: 10px;">
				 <label for="" class="form-label"><fmt:message key="content.print.type"/> </label>
				 <select contentPrintType name="name" class="form-select">
				   <c:forEach items="${printTypes}" var="printType">
                     <option value="${printType.id}">${printType.name}</option>
                   </c:forEach>
                 </select>
               </div>
				  <div class ="col-lg-3 px8" style="position: relative; left: 10px;">
					<label for="" class="form-label"><fmt:message key="content.color.combination"/></label>
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
				  <label for="" class="form-label"><fmt:message key="signature"/></label>
				  <div> <input type="number" id="" step=".1" style="width:70px;color:red; text-align:center" readonly="readonly"  inputSignReadonly>
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
					<label for="" class="form-label"><fmt:message key="x.Perforated"/></label>
					<input type="number" id="xPerforated" value = "">
                  </div>
				  <div class ="col-lg-3 px8" style="position: relative; left: 10px;">
					<label for="" class="form-label"><fmt:message key="x.Numbered"/></label>
					<input type="number" id="xNumbered" value = "">
                  </div>
                    <div class ="col-lg-3 px8" style="position: relative; left:10px;">
			   <label for="" class="form-label"><fmt:message key="lamination"/></label> 
			      <select id="lamination" name="name" class="form-select">
			        <option selected  value="${jobActivity.lamination}">${jobActivity.lamination}</option>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
                  </select>
			    </div>
				</div>	
				   <div class="row py-4">
				  <div class ="col-lg-3 px8" >
				    <label for="" class="form-label"><fmt:message key="creased"/></label> 
					<input type="number" id="creased">
			      </div>
				 <div class ="col-lg-3 px8" style="position: relative; left:10px;">
					<label for="" class="form-label"><fmt:message key="x.Wire-stitched"/></label>
					<input type="number" id="xWire-stitched">
                  </div>
				  <div class ="col-lg-3 px8" style="position: relative; left: 10px;">
					<label for="" class="form-label"><fmt:message key="x.cross"/></label>
					<input type="number" id="xcross">
                  </div>
				</div>	
				 <div class="row py-4">
				<div class ="col-lg-3 px8" >
				  <label for="" class="form-label"><fmt:message key="glued.option"/></label> 
				  <select id="glueingOption" name="name" class="form-select">
<!-- 				    <option  selected>Choose...</option> -->
				    <option value="leftSide"><fmt:message key="left.side"/></option>
				   <option value="head"><fmt:message key="head"/></option>
				   <option value="glueBound"><fmt:message key="glue.bound"/></option>
		         </select>
			   </div>
			     <div class ="col-lg-3 px8" style="position: relative; left:10px;">
			   <label for="" class="form-label"><fmt:message key="binding.type"/></label> 
			      <select id="bindingType" name="name" class="form-select">
<!-- 			        <option selected>Choose...</option> -->
					<c:forEach items="${bindingTypes}" var="bindingTyp">
                      <option value="${bindingTyp.id}">${bindingTyp.name}</option>
                    </c:forEach>
                  </select>
			    </div>
			   </div>
				
                 <div class="row py-4">
				   <div class ="col-lg-2 px2" >
				    <div>
                     <div class="form-check">
                      <label class="form-check-label" for="trimmed"><fmt:message key="trimmed"/></label>
                       <input class="form-check-input" type="checkbox" id="trimmed">
                    </div>
				  </div>
			    </div>
				<div class ="col-lg-2 px2" style="position: relative; left: 10px;">
                     <div class="form-check">
                      <label class="form-check-label" for="sellotaped"><fmt:message key="sellotaped"/></label>
                       <input class="form-check-input" type="checkbox" id="sellotaped">
                    </div>
                  </div>
				  <div class ="col-lg-2 px2" style="position: relative; left: 10px;">
                     <div class="form-check">
                      <label class="form-check-label" for="sewn"><fmt:message key="sewn"/></label>
                       <input class="form-check-input" type="checkbox" id="sewn">
                    </div>
				  </div>
				  
				   <div class ="col-lg-2 px2">
                     <div class="form-check">
                      <label class="form-check-label" for="handgather"><fmt:message key="handgather"/></label>
                       <input class="form-check-input" type="checkbox" id="handgather">
                    </div>
                    </div>
                    <div class ="col-lg-2 px2">
                    <div class="form-check">
                      <label class="form-check-label" for="stitching"><fmt:message key="stitching"/></label>
                       <input class="form-check-input" type="checkbox" id="stitching">
                    </div>
				  </div>
                  </div>
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
       				<h4>Job Description</h4>
       				<hr>
					  <div class="row">
					 	 <div class="row">
					      <div class="col-sm-4">
					    	The Type of Job : <span id="job-type"> </span>
					      </div>
					      <div class="col-sm-4">
					    	 Title of Job : <span id="job-title"> </span> 
					      </div>
					      <div class="col-sm-4">
					    	 Name of Customer : <span id="job-customer">  </span> 
					      </div>
					    </div>
					   
					   	 <div class="row">
					    <div class="col-sm-4">
					    	Number of Pages for Cover : <span id="cover-pages"> </span>
					    </div>
					    <div class="col-sm-4">
					    	Number of Pages for Content :  <span id="content-pages"> </span> 
					    </div>
					    <div class="col-sm-4">
					    	 CTP Fees : <span id="ctp">  </span> 
					    </div>
					   </div>
					   
					     <div class="row">
					    <div class="col-sm-4">
					    	Paper Format : <span id="paper-format"> </span>
					    </div>
					    <div class="col-sm-4">
					    	Open :<span id="open-l"></span> | <span id="open-w"></span></div> 
					    	<div class="col-sm-4">
					    	Fold :<span id="fold-l"></span> | <span id="fold-w"></span>
					    </div>
					    </div>
					    <div class="row">
					    <div class="col-sm-4">
					    	Existing Plate : <span id="existing-plate"></span>
					    </div>
					    <div class="col-sm-4">
					    	Data Supply By Us : <span id="supply-data"></span>
					    </div>
					    <div class="col-sm-4">
					    	Lay Out by Us : <span id="data-layout"></span>
					    </div> 
					    <div class="col-sm-4" id="">
					    	Type Setting By Us : <span id="type-setting"></span> 
					    </div>
					    </div>
					   </div>
					   
					<!--            job decription ends     -->
					<br>
					<h4 id="top">Cover Paper Option</h4>
					<hr>
					   <div class="row">
					    <div class="row">
					    <div class="col-sm-4">
					    	Paper Type : <span id="cover-paper"> </span>
					    </div>
					    <div class="col-sm-4">
					    	 Paper Grammage (GSM) : <span id="cover-grammage"> </span> 
					    </div>
					    <div class="col-sm-4">
					    	Volume : <span id="cover-volume">  </span> 
					    </div>
					   </div>
	
					   </div>
					   
					   <br>
					<h4 id="top"> Content Paper Option</h4>
					<hr>

					    <div class="row">
					   <table class="ta" id="cover-table">
					  <thead>
					    <tr>
					      <th scope="col">Num</th>
					      <th scope="col">Print Type</th>
					      <th scope="col">Gramage (GSM)</th>
					      <th scope="col">Volume (Pages)</th>
					    </tr>
					  </thead>
					  <tbody>
<!-- 					    <tr> -->
<!-- 					      <th scope="row">1</th> -->
<!-- 					      <td>glows</td> -->
<!-- 					      <td>250</td> -->
<!-- 					      <td>2000</td> -->
<!-- 					    </tr> -->
					  </tbody>
					</table>
					   </div>
					      <br>
		  
					 <h4>Cover Printing Option</h4>
					 <hr>
						<div class="row">
					    <div class="row">
					     <div class="col-sm-3">
					    	 Machine   <span id="cover-machine"> </span>
					    </div>
					    <div class="col-sm-3">
					    	Print Type : <span id="cover-printtype"> </span> 
					    </div>
					    <div class="col-sm-3">
					    	Color Combination : <span id=cover-color-front></span> / <span id=cover-color-back></span> 
					    </div>
					     <div class="col-sm-3">
					    	Signature : <span id=cover-signature></span> 
					    </div>
					    </div>
					  </div>
					  
					  
					   <h4>Content Printing Option</h4>
					 <hr>
					  
					  <div class="row">
						<table class="ta" id="content-table">
					  <thead>
					    <tr>
					      <th scope="col">Num</th>
					      <th scope="col">Paper Type</th>
					      <th scope="col">Machine</th>
					      <th scope="col"> Print Type</th>
					      <th scope="col">Color Combination</th>
					      <th scope="col">Signature</th>
					    </tr>
					  </thead>
					  <tbody id="table-body">
					
					  </tbody>
					</table>
					   </div>
					  </div>
					  
						<br>
						 <h4>Finishing option</h4>
						 <hr>
						 <div class="row ">
					    <div class="col-sm-4">
					    	<div> X Perforated : <span id="x-perforated"></span> </div>
					    	<div> X Numbered : <span id="x-numbered"></span> </div>
					    	<div> X Crossed : <span id="x-crossed"></span></div>
					    	<div> X Wired-stitched : <span id="x-wired"></span> </div>
					    	<div> Creased : <span id="crease"></span> </div>
					    	
					    </div>
					   <div class="col-sm-4">
					    	<div> Lamination Sides : <span id="laminated-sides"></span> </div>
					    	<div> Glueing Bound: <span id="glue-bound"></span> </div>
					    	<div> Binding Type : <span id="binding-type"></span> </div>
					    	<div> Sewn : <span id="sown"></span> </div>
					    
					    </div>
					    
					    <div class="col-sm-4">
					    	<div> Handgather : <span id="hand-gather"></span> </div>
					    	<div> Stitching : <span id="stitch"></span> </div>
					    	<div> Trimmed : <span id="trim"></span> </div>
					    	<div> Sellotaped : <span id="sello-tape"></span> </div>
					    </div>
					 </div>
		           <div class ="row py-3 "style="margin-top:50px" >
				     <div class ="col-sm-6"> 
				      <button type="button" style="float:left" class="btn btn-primary" onclick="navigate(5,4);removeRows()"> <fmt:message key="previews"/></button>	
				     </div>
			         <div class ="col-sm-6">
			         <button type="button" style="width:125px;float:right"  class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#creation" id="next-btn1" onclick="navigate(4,5); submitUpdateForm(${job.id})"><fmt:message key="submit"/></button>			
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

   
  
  
  
  
  
  