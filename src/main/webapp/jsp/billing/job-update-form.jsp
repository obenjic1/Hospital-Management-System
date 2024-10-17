
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
               <strong><h5 class="card-title" style="text-align:center; font-family: bold; color: #012970;"><fmt:message key="update.job"/> : ${job.referenceNumber}</h5></strong>

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
				  <div id="loadInputForCustomerNewlyCreated" class ="col-lg-3 px8">					 
				   <label for="" class="form-label"><a> <fmt:message key="customer"/> </a></label>
				   <select id="customer" name="customer"  class="form-select" >
				   	<option value="${job.customer.id}" selected>${job.customer.name}</option>
	                 <c:forEach items="${customers}" var="customer">
	                   <option value="${customer.id}">${customer.name}</option>
	                 </c:forEach>
	                </select>
			      </div>
				  <div class ="col-lg-3 px8" style="position: relative; left: 10px;"> 
					<label for="" class="form-label"><a> <fmt:message key="job.type"/></a></label> 
						<select onchange="jobTypeChoice(this.selectedOptions[0])"  id="jobType" name="jobType" class="form-select" >
					  <option>Choose...</option>
					  
					  <optgroup label="<fmt:message key="job.category.folded.two"/>" data-content="2">
					  <c:forEach items="${jobTypes}" var="jobType">
					  <c:if test="${jobType.category==2}">
					  <c:if test="${jobType.id==job.jobType.id}">
					  	 <option style="marging-left: %;" selected value="${jobType.id}" >${jobType.name}</option>
					  </c:if>
					    <c:if test="${jobType.id!=job.jobType.id}">
					  	 <option style="marging-left: %;"  value="${jobType.id}" >${jobType.name}</option>
					  </c:if>
                         </c:if>
                      </c:forEach>
                      </optgroup>
                      
                       <optgroup label="<fmt:message key="job.category.folded.one"/>" data-content="1">
                        <c:forEach items="${jobTypes}" var="jobType">
					  <c:if test="${jobType.category==1}">
					  <c:if test="${jobType.id==job.jobType.id}">
					  	 <option selected value="${jobType.id}" >${jobType.name}</option>
					  </c:if>
					    <c:if test="${jobType.id!=job.jobType.id}">
					  	 <option  value="${jobType.id}" >${jobType.name}</option>
					  </c:if>
                         </c:if>
                      </c:forEach>
                       </optgroup>
                       
                       <optgroup   label="<fmt:message key="job.category.opened"/>" data-content="0">
                        <c:forEach items="${jobTypes}" var="jobType">
					  <c:if test="${jobType.category==0}">
					  <c:if test="${jobType.id==job.jobType.id}">
					  	 <option selected value="${jobType.id}" >${jobType.name}</option>
					  </c:if>
					    <c:if test="${jobType.id!=job.jobType.id}">
					  	 <option  value="${jobType.id}" >${jobType.name}</option>
					  </c:if>
                         </c:if>
                      </c:forEach>
                     </optgroup>
                     
                      <optgroup   label="<fmt:message key="job.category.opened.with.cover"/>" data-content="3">
                      <c:forEach items="${jobTypes}" var="jobType">
					  <c:if test="${jobType.category==3}">
					  <c:if test="${jobType.id==job.jobType.id}">
					  	 <option style="marging-left: %;" selected value="${jobType.id}" >${jobType.name}</option>
					  </c:if>
					    <c:if test="${jobType.id!=job.jobType.id}">
					  	 <option style="marging-left: %;"  value="${jobType.id}" >${jobType.name}</option>
					  </c:if>
                         </c:if>
                      </c:forEach>
                     </optgroup>
                     
                    </select>
				  </div>
				  <div class ="col-lg-3 px8" style="position: relative; left: 10px;">
				  <label for="title" class="form-label"><a><fmt:message key="title"/></a> </label>
					<div>
					  <input id= "title" name="title" type= "text" value="${job.title}">
					</div>
                  </div>
				</div>	
			<div class="row py-3">
			  <div class ="col-lg-3 px8" >
				   <label for="" class="form-label"> <a><fmt:message key="format"/></a> </label>
              	   <select id="paperFormat" onchange="paperF(this.value)" class="form-select">
              	      <option onclick="">Custom Format...</option>
              	      <option value="${paperFormat.id},${paperFormat.length},${paperFormat.width}" selected>${job.paperFormat}</option>
					  <c:forEach items="${paperFormats}" var="paperFormat">
                        <option value="${paperFormat.id},${paperFormat.length},${paperFormat.width}">${paperFormat.name}</option>
                      </c:forEach>
                    </select>
			   </div>
			   <div class ="col-lg-3 px8" style="position: relative; left: 10px;">
			     <label for="" class="form-label"> <a><fmt:message key="open.format"/></a> </label> 
			       <div class="row">
				     <div class="col-6 volume-cover-l">
                       <input id="openWidth" name="openwidth" value="${job.openWidth}" style="postion-relative-left:2px;position: relative;left: 4px;"  placeholder= "<fmt:message key='open.width'/> ">
				      </div>
				      <div  class="col-6 volume-cover-w">
				      <input id="openLength"  name="openLegnth" value="${job.openLength}" placeholder="<fmt:message key='open.legnth'/>">
					  
                   </div>
				 </div>
			  </div>
			  <div class ="col-lg-3 px8" id="closeDimensionDiv" style="position: relative; left: 10px;"> 
			   <label for="" class="form-label"> <a><fmt:message key="close.format"/></a> </label>
			     <div class="row">
				   <div class="col-6 volume-cover-l">
                     <input name="closeWidth id="closeWidth" value="${job.closeWidth}" style="postion-relative-left:2px;position: relative;left: 4px;"  placeholder= "<fmt:message key="open.width"/> ">
				     </div>
				     <div  class="col-6 volume-cover-w">
				       <input id="closeLength"  name="closeLength" value="${job.closeLength}"  placeholder="<fmt:message key="open.legnth"/>">
                    </div>
				  </div>
			   </div>
		      </div>
									
			     <div class="row py-3">
				  <div id="volumeofCover" class ="col-lg-3 px8">
				    <label for="coverVolume" class="form-label"> <a><fmt:message key="volume.cover"/></a></label> 
				     <div>
					  <input id= "volumeOfCover" name="volumeOfCover" type="number" value="${job.coverVolume}">
				    </div>
				  </div>
				  <div id="volumeofContent" class ="col-lg-3 px8" style="position: relative; left: 10px;"> 
				    <label for="volumeOfContent"   class="form-label"> <a><fmt:message key="volume.content"/></a></label>
				    <div>
					  <input id= "volumeOfContent" name="volumeOfContent" type="number" onchange="totalContentVolumeChange()" value="${job.contentVolume}">
				    </div>
				  </div>
				  <div class ="col-lg-3 px8"  style="position: relative; left: 10px;">
				    <label for="" class="form-label"><a> <fmt:message key="ctp.fees" /></a></label> 
					<input type="number"  name="ctpFees" id="ctpFees" value="${job.ctpFees}">
			      </div>
		       </div>		
	
		      <div class="row py-3">
			    <div class ="col-lg-3 px3">
                  <div class="form-check">
                    <label class="form-check-label" for="existingPlate"><a><fmt:message key="existing.plate"/></a> </label>
                      <input class="form-check-input" type="checkbox" name="existingPlate" id="existingPlate" ${job.existingPlate ? 'checked':''}>
                  </div>
			    </div>
			    <div class ="col-lg-3 px3">
			    <div class="form-check">                     
                    <label class="form-check-label" for="gridCheck1"> <a><fmt:message key="type.setting.by.us"/></a></label>
                    <input class="form-check-input" type="checkbox" name="name" id="typesettingByUs" ${job.typesettingByUs ? 'checked':''}>
                  </div>
			    </div>
			  <div class ="col-lg-3 px3" style="position: relative; left: 10px;"> 
			     <div>
			      <div class="form-check">                      
                    <label class="form-check-label" for="gridCheck1"><a><fmt:message key="data.supplied.by.customer"/></a> </label>
                    <input class="form-check-input" type="checkbox" id="dataSuppliedByCustomer" ${job.dataSuppliedByCustomer ? 'checked' : ''}>
                  </div>
                  
			    </div>
			  </div>
			  <div class ="col-lg-3 px3" style="position: relative; left: 10px;">
			  <div class="form-check">
                    <label class="form-check-label" for="gridCheck1"><a><fmt:message key="layout.by.us"/></a> </label>
                    <input class="form-check-input" type="checkbox" id="layoutByUs" ${job.layOutByUs ? 'checked' : ''}>
                  </div>
			  </div>
		    </div>	
		     <div class="row py-3">    
			<button  style=" width: 94px;" type="button"  class="all-button-style" onclick="validateTab1(); tab1NextBtnAction();" id="next-btn" >Next</button>	
		   </div>  	
          </div>             
        </div>
        
        
        
        
        
        
        
 <!-- <----------------------------------- Tab2 ------------------------------->   
 
      <div class=" container tab-pane fade"  id="tab2" role="tabpanel" aria-labelledby="profile-tab">
         <div style="position: relative;bottom: -20px;" id="mainDiv" >	
         	
		   <div class="row py-4" id="coverInformations">
			  <div class ="col-lg-2 px8" >
			   <label for="" class="form-label"><a> <fmt:message key="cover.paper.type"/></a></label> 
			   <select id="coverPaperType" name="paperType" class="form-select">
				 <c:forEach items="${paperTypes}" var="paperTypeToUpdate">
				 	<c:if test="${coverJobPaper.paperType.id==paperTypeToUpdate.id}">
				 	<option selected value="${paperTypeToUpdate.id}">${paperTypeToUpdate.name}</option>
				 	</c:if>
				 	<c:if test="${coverPaperType.id!=paperTypeToUpdate.id}">
				 		<option  value="${paperTypeToUpdate.id}">${paperTypeToUpdate.name}</option>
				 	</c:if>
                 </c:forEach>
               </select>
			 </div>
			 <div class ="col-lg-2 px8" style="position: relative; left:10px;">
			   <label for="" class="form-label"><a><fmt:message key="grammage"/></a> </label>
			   <input   name="paperGrammage" style="postion-relative-left:2px;position: relative;left: 10px;" value="${coverJobPaper.grammage}" list="coverGrammage" id="coverGrammage" name="xx">
			   <datalist id="coverGrammage">
                   <c:forEach items="${paperGrammages}" var="paperGrammage"> 
                   <option value="${paperGrammage.value}"></option> 
                  </c:forEach> 
                </datalist>  
              </div>
			  <div class ="col-lg-2 px8" style="position: relative; left: 10px;">
			    <label for="" class="form-label"><a><fmt:message key="cover.volume"/></a></label>
			     <input id="coverVolume" type="text"  name="volume" readonly="readonly">
              </div>
              <div class ="col-lg-2 px8" style="position: relative; left: 10px;">
			  <label for="" class="form-label"><a><fmt:message key="paper.size"/> (mm)</a></label>
				 <div class="row">
				   <div class="col-6 paper-size-width">
                     <input name="paperSizeWidth" id="paperSizeWidth" placeholder="<fmt:message key='width'/>" value="${coverJobPaper.paperSizeWidth}" style="postion-relative-left:2px;position: relative;left: 4px;">
                   </div>
				   <div  class="col-6 paper-size-length"  style="position: relative;left: -25px;">
				      <input name="paperSizeLength" id="paperSizeLength" value="${coverJobPaper.paperSizeLength}" placeholder="<fmt:message key='length'/>">
                    </div>
				  </div>
              </div>
              <div class ="col-lg-2 px8" style="position: relative;">
			    <label for="" class="form-label"><a><fmt:message key="cover.paper.price"/></a></label>
			     <input id="coverPaperUnitPrice" type="number" value="${coverJobPaper.unitPrice}" name="paperPrice">
              </div>
		    </div>	
		    
		   <div id="contentDiv">   		
			<div class="row py-3"  style="display:none">
			  <div class="col-lg-2 px-8" >
			    <label for="" class="form-label"><a> <fmt:message key="content.paper.type"/></a> </label>
				<select contentPaperType name="paperType" class="form-select">
				<option >Choose...</option>
			  	<c:forEach items="${paperTypes}" var="paperType">
                    <option value="${paperType.id}">${paperType.name}</option>
                  </c:forEach>
                </select>
			  </div>
			  <div class="col-lg-2 px-8" style="position: relative; left: 10px;">
			    <label for="" class="form-label"><a><fmt:message key="grammage"/></a> </label>
			     <input contentGrammage  list="contentGrammage" name="paperGrammage">
				  <datalist  id="contentGrammage">
	               <c:forEach items="${paperGrammages}" var="paperGrammage">
                    <option value="${paperGrammage.value}"></option>
                    </c:forEach>
	            </datalist>   
			  </div>
			 
			  <div class="col-lg-2 px-8 coverDup" style="position: relative; left: 10px;float:left">
			    <label for="" class="form-label"><a> <fmt:message key="content.volume"/> </a></label> 
				<input type="text" contentVolume name="volume" oldValue="" onclick="this.oldValue=this.value" onchange="updateTotalContentvolume(this.value,this.oldValue)"  >
			  </div>
			       <div class ="col-lg-2 px8" style="position: relative; left: 10px;">
			  <label for="" class="form-label"><a><fmt:message key="paper.size"/> (mm)</a></label>
				 <div class="row">
				   <div class="col-6 paper-size-width">
                     <input name="paperSizeWidth" paperSizeWidth placeholder="<fmt:message key='width'/>" value="620" type="number" style="postion-relative-left:2px;position: relative;left: 4px;">
                   </div>
				   <div  class="col-6 paper-size-length"  style="position: relative;left: -25px;">
				      <input name="paperSizeLength" paperSizeLength value="950" placeholder="<fmt:message key='length'/>" type="number">
                    </div>
				  </div>
              </div>
			   <div class ="col-lg-3 px8" style="position: relative;">
			    <label for="" class="form-label"><a><fmt:message key="content.paper.price"/></a> </label>
			     <input paperUnitPrice type="number"  name="paperPrice" value="">
			     <button type="button" id="deleteButton"  onclick="removeContentNode(this,this.parentNode.previousElementSibling)"><i class="ri-delete-bin-3-line"></i> </button>
              </div>
			  
		   </div>
		   <c:set var="counter" value="1"/>
		   <c:forEach items="${contentJobPaper}" var="contentJPaper">
		   <c:if test="${counter==1}">
		   <div class="row py-3"  >
			  <div class="col-lg-2 px-8">
			    <label for="" class="form-label"><a><fmt:message key="content.paper.type"/> </a> </label>
				<select contentPaperType name="paperType" class="form-select">
					 <option >Choose...</option>
			  <c:forEach items="${paperTypes}" var="paperType">
			  <c:if test="${contentJPaper.paperType.id==paperType.id}">
			  	<option selected value="${paperType.id}">${paperType.name}</option>
			  </c:if>
			  <c:if test="${contentJPaper.id!=paperType.id}">
			  	<option  value="${paperType.id}">${paperType.name}</option>
			  </c:if>
                  </c:forEach>
                </select>
			  </div>
			  
			  <div class="col-lg-2 px-8" style="position: relative; left: 10px;">
			    <label for="" class="form-label"><a> <fmt:message key="grammage"/></a></label>
			     <input contentGrammage  list="contentGrammage" value="${contentJPaper.grammage}" name="paperGrammage">
				  <datalist  id="contentGrammage">
	               <c:forEach items="${paperGrammages}" var="paperGrammage">
	               <c:if test="${contentJPaper.grammage==paperGrammage.value}"> 
                    <option selected value="${paperGrammage.value}"></option> 
                    </c:if>
                    <c:if test="${contentJPaper.grammage!=paperGrammage.value}"> 
                    <option  value="${paperGrammage.value}"></option> 
                    </c:if>
                    </c:forEach>
	            </datalist>   
			  </div>
			  <div class="col-lg-2 px-8 " style="position: relative; left: 10px">
			    <label for="" class="form-label"><a><fmt:message key="content.volume"/></a> </label> 
				<input  contentVolume name="volume" value="${contentJPaper.volume}" readonly="readonly">
			  </div>

 			<div class ="col-lg-2 px8" style="position: relative; left: 10px;">
			  <label for="" class="form-label"><a><fmt:message key="paper.size"/> (mm)</a></label>
				 <div class="row">
				   <div class="col-6 paper-size-width">
                     <input name="paperSizeWidth" paperSizeWidth placeholder="<fmt:message key='width'/>" value="${contentJPaper.paperSizeWidth}" type="number" style="postion-relative-left:2px;position: relative;left: 4px;">
                   </div>
				   <div  class="col-6 paper-size-length"  style="position: relative;left: -25px;">
				      <input name="paperSizeLength" paperSizeLength value="${contentJPaper.paperSizeLength}" placeholder="<fmt:message key='length'/>" type="number">
                    </div>
				  </div>
              </div>
             
              <div class ="col-lg-2 px8" style="position: relative;"> 			    
 				<label for="" class="form-label"><a><fmt:message key="content.paper.price"/></a></label> 
 			     <input paperUnitPrice type="number"  name="paperPrice" value="${contentJPaper.unitPrice}"> 
              </div>
			   <div class="col-lg-2 px-8 " style="position: relative; left: 10px">
			       <label for="" class="form-label" style=""><a><fmt:message key="add"/></a></label> 
			       <span>
			       	<button type="button"  id="duplicateButton"  onclick="addContentPaperChild()" ><i class="ri-add-fill"></i>
			       	</button>

			       </span>
			  </div>
			 
<%-- 			  <c:if test="${counter!=1}"> --%>
<!-- 			   <div class ="col-lg-3 px8" style="position: relative;"> -->
<%-- 			    <label for="" class="form-label"><a><fmt:message key="content.paper.price"/></a> </label> --%>
<%-- 			     <input paperUnitPrice type="number"  name="paperUnitPrice" value="${contentJPaper.unitPrice}"> --%>
<!-- 			     <button type="button" id="deleteButton"  onclick="removeContentNode(this,this.previousElementSibling)"><i class="ri-delete-bin-3-line"></i> </button> -->
<!--               </div> -->
<%-- 			  </c:if> --%>
			 
		   </div>
		    </c:if>
		     <c:if test="${counter!=1}">
		     <div class="row py-3">
			  <div class="col-lg-2 px-8" >
			    <label for="" class="form-label"><a> <fmt:message key="content.paper.type"/></a> </label>
				<select contentPaperType name="paperType" class="form-select">
				<option >Choose...</option>
			  	 <c:forEach items="${paperTypes}" var="paperType">
					  <c:if test="${contentJPaper.paperType.id==paperType.id}">
					  	<option selected value="${paperType.id}">${paperType.name}</option>
					  </c:if>
					  <c:if test="${contentJPaper.id!=paperType.id}">
					  	<option  value="${paperType.id}">${paperType.name}</option>
					  </c:if>
                  </c:forEach>
                </select>
			  </div>
			  <div class="col-lg-2 px-8" style="position: relative; left: 10px;">
			    <label for="" class="form-label"><a><fmt:message key="grammage"/></a> </label>
			    <input contentGrammage  list="contentGrammage" value="${contentJPaper.grammage}" name="paperGrammage">
				  <datalist  id="contentGrammage">
	               <c:forEach items="${paperGrammages}" var="paperGrammage">
	               <c:if test="${contentJPaper.grammage==paperGrammage.value}"> 
                    <option selected value="${paperGrammage.value}"></option> 
                    </c:if>
                    <c:if test="${contentJPaper.grammage!=paperGrammage.value}"> 
                    <option  value="${paperGrammage.value}"></option> 
                    </c:if>
                    </c:forEach>
	            </datalist>  
			  </div>
			 
			  <div class="col-lg-2 px-8 coverDup" style="position: relative; left: 10px;float:left">
			    <label for="" class="form-label"><a> <fmt:message key="content.volume"/> </a></label> 
				<input name="volume" type="text" value="${contentJPaper.volume}" contentVolume name="contentVolume" oldValue="" onclick="this.oldValue=this.value" onchange="updateTotalContentvolume(this.value,this.oldValue)"  >
			  </div>
			  	<div class ="col-lg-2 px8" style="position: relative; left: 10px;">
			  <label for="" class="form-label"><a><fmt:message key="paper.size"/> (mm)</a></label>
				 <div class="row">
				   <div class="col-6 paper-size-width">
                     <input name="paperSizeWidth" paperSizeWidth placeholder="<fmt:message key='width'/>" value="${contentJPaper.paperSizeWidth}" type="number" style="postion-relative-left:2px;position: relative;left: 4px;">
                   </div>
				   <div  class="col-6 paper-size-length"  style="position: relative;left: -25px;">
				      <input name="paperSizeLength" paperSizeLength value="${contentJPaper.paperSizeLength}" placeholder="<fmt:message key='length'/>" type="number">
                    </div>
				  </div>
              </div>
             
			   <div class ="col-lg-2 px8" style="position: relative;">
			    <label for="" class="form-label"><a><fmt:message key="content.paper.price"/></a> </label>
			     <input paperUnitPrice type="number"  name="paperPrice" value="${contentJPaper.unitPrice}">
			     <button type="button" id="deleteButton"  onclick="removeContentNode(this,this.parentNode.previousElementSibling)"><i class="ri-delete-bin-3-line"></i> </button>
              </div>
			  
		   </div>
		      </c:if>
		      <c:set var="counter" value="2"/>
		      
		   </c:forEach>
		    </div>
		  
	     </div>	
	     <div class ="row py-3 "style="margin-top:50px" >
		     <div class ="col-sm-6"> <button type="button" style ="width:125px;float:left" class="all-button-style" onclick="navigate(2,1);"><fmt:message key="previews"/></button>	
		    </div>
	        <div class ="col-sm-6">
	        <button type="button"  style ="width:125px;float:right" onclick="validateTab2();navigate(2,3)"  class="all-button-style" id=""><fmt:message key="next"/></button>	
	       </div>
	     </div>
       </div>     
       
       
       
       
       
       
       
       
       
       
       
           
<!------------------------------- TAB 3 BIGINS --------------->
       <div class="tab-pane fade" id="tab3" role="tabpanel" aria-labelledby="contact-tab">
        <div class="container" >	
          <div class="row py-3">
		   <div class ="col-lg-3 px8" >
				  <label for="" class="form-label"><a><fmt:message key="cover.printing.machine"/></a></label> 
				  <select id="coverPrintingMachine" name="printingMachine" class="form-select" onchange="coverSignatureCalculation2(this.value , this.parentNode)">
				    <c:forEach items="${printingMachines}" var="printingMachine">
				    <c:if test="${covercolourCombination.printingMachine.id==printingMachine.id}">
				    	 <option selected value="${printingMachine.id},${printingMachine.plateLength},${printingMachine.plateWidth}">${printingMachine.name}</option>
				    </c:if>
				    <c:if test="${covercolourCombination.printingMachine.id!=printingMachine.id}">
				    	 <option  value="${printingMachine.id},${printingMachine.plateLength},${printingMachine.plateWidth}">${printingMachine.name}</option>
				    </c:if>
	                 
	                </c:forEach>
	              </select>
				</div>
			<div class ="col-lg-3 px8" style="position: relative; left: 10px;">
			  <label for="" class="form-label"><fmt:message key="cover.print.type"/></label>
			  <select id="coverPrintType" name="printType" class="form-select">
			    <c:forEach items="${printTypes}" var="printType">
			    <c:if test="${covercolourCombination.printType.id==printType.id}">
			    	 <option selected value="${printType.id}">${printType.name}</option>
			    </c:if>
			     <c:if test="${covercolourCombination.printType.id!=printType.id}">
			    	 <option  value="${printType.id}">${printType.name}</option>
			    </c:if>
                 
                </c:forEach>
              </select>
            </div>
			<div class ="col-lg-3 px8" style="position: relative; left: 10px;">
			  <label for="" class="form-label"><fmt:message key="cover.color.combination"/></label>
				 <div class="row">
				   <div class="col-6 volume-cover-l">
                     <input name="frontColor" id="coverFrontColorNumber" placeholder="<fmt:message key='front'/>" type="number" min="0" max="5" style="postion-relative-left:2px;position: relative;left: 4px;" value="${covercolourCombination.frontColorNumber}">
					                    
				     </div>
				     <div  class="col-6 volume-cover-w">
				       <input name="backColor" id="converBackColorNumber" placeholder="<fmt:message key='back'/>" type="number" min="0" max="5" value="${covercolourCombination.frontColorNumber}">
					   
                    </div>
				  </div>
              </div>
             <div class ="col-lg-3 px8" style="position: relative; left:10px;">
			  	<label for="" class="form-label"> <a><fmt:message key="signature"/></a></label>
			  	<div style=" display: flex;">
			  <div style="width: 73px;"> 
			  	<input name="signature" class="coverSpace" type="number" step=".1" id="coverSignature" value="${covercolourCombination.numberOfSignature}" readonly="readonly">
			 </div> 
<!-- 				 <div> -->
<!-- 				 <span onclick="randUpCoverSignature()"><i style="color: green; font-size: 22px" class="bi bi-arrow-up"></i></span> -->
<!-- 				</div> -->
            </div>
			</div>
			</div>	
			
		<!-- 			main content signature div -->
		
		
	
		
		<div id="mainContentSignature">
		
		<!-- 			start each signature bloc            -->
		
			<div style="display:none" id="eachSignatureDiv"> 
			 <hr style="margin-top: 10px; ">	
			  <div class="row py-3" style="display:none">
			    <div class ="col-lg-3 px8" >
				  <label for="" class="form-label"><a><fmt:message key="printing.machine"/></a></label> 
				  <select contentPrintingMachine name="printingMachine" class="form-select" disabled="disabled">
				    <option selected>Choose...</option>
				    <c:forEach items="${printingMachines}" var="printingMachine">
		                <option value="${printingMachine.id},${printingMachine.plateLength},${printingMachine.plateWidth}">${printingMachine.name}</option>
		            </c:forEach>
		         </select>
			   </div>
			   <div class ="col-lg-3 px8" style="position: relative; left: 10px;">
				 <label for="" class="form-label"><a><fmt:message key="content.print.type"/></a> </label>
				 <select contentPrintType name="printType" class="form-select">
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
                     <input name="frontColor" contentFrontColorNumber placeholder="<fmt:message key='front'/>" type="number" min="0" max="5" style="postion-relative-left:2px;position: relative;left: 4px;">
					                  
				     </div>
				     <div class="col-6 volume-cover-w">
				       <input name="backColor" type="number" min="0" max="5" placeholder="<fmt:message key='back'/>" contentBackColorNumber>
					     
                    </div>
				  </div>
                  </div>
                  <div class ="col-lg-3 px8" style="position: relative; left:10px;">
				  <label for="" class="form-label"><a><fmt:message key="signature"/></a></label>
				  <div> 
				  <input name="signature" type="number" step=".1" delContentSign allSignatures style="width:70px;color:red; text-align:center" onclick="this.oldValue=this.value" onchange="signatureChange(this.value,this.parentNode.parentNode.parentNode.parentNode,oldValue)">
				  <span> <button  type="button" onclick="deleteContentsignature(this.parentNode.parentNode.parentNode.parentNode,this.parentNode.parentNode.parentNode.parentNode.parentNode)" style="background:red"><i class="ri-delete-bin-3-line"></i></i></button> </span>
				 </div>
				 
	            </div>
	           
				</div>

			  <div class="row py-3" >
			    <div class ="col-lg-3 px8" >
				  <label for="" class="form-label"><a><fmt:message key="printing.machine"/></a></label> 
				  <select  contentPrintingMachine name="printingMachine"" class="form-select" onchange="signatureCalculation(this.value,this.parentNode.parentNode.parentNode)">
				    <option selected>Choose...</option>
				    <c:forEach items="${printingMachines}" var="printingMachine">
		                <option value="${printingMachine.id},${printingMachine.plateLength},${printingMachine.plateWidth}">${printingMachine.name}</option>
		            </c:forEach>
		         </select>
			   </div>
			   <div class ="col-lg-3 px8" style="position: relative; left: 10px;">
				 <label for="" class="form-label"><a><fmt:message key="content.print.type"/> </a></label>
				 <select contentPrintType name="printType" class="form-select">
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
                     <input name="frontColor" contentFrontColorNumber type="number" min="0" placeholder="<fmt:message key='front'/>" max="5" style="postion-relative-left:2px;position: relative;left: 4px;">
					                  
				     </div>
				     <div class="col-6 volume-cover-w">
				       <input name="backColor" type="number" min="0" max="5" placeholder="<fmt:message key='back'/>" contentBackColorNumber>
					     
                    </div>
				  </div>
                  </div>
                  <div class ="col-lg-3 px8" style="position: relative; left:10px;">
				  <label for="" class="form-label"><a><fmt:message key="signature"/></a></label>
				  <div> <input name="signature" type="number" step=".1" id="" style="width:70px;color:red; text-align:center" allSignatures inputSignReadonly readonly="readonly">
				  <span> <button   type="button" id="duplicateButton" style="display: inline;" onclick="updateContentSignature(this.parentNode.parentNode.parentNode.parentNode.parentNode,1,this.parentNode.parentNode.parentNode.parentNode)"><i class="ri-add-fill"></i></button> </span>
				 </div> 
	            </div>
				</div>
			</div>
			
		<!-- 			End each signature bloc            -->	
		
		
			
			<c:set var="paperTypeCounter" value="1"/>
			<c:forEach items="${contentJobPaper}" var="contentJPaper">
				<c:set var="colorCounter" value="1"/>
				
				<c:forEach items="${contentJPaper.jobColorCombinations}" var="jobColorCombinaition">
				<c:if test="${paperTypeCounter==1 and colorCounter==1 }">
				
				<!-- 	Start SignatureDiv bloc            	-->
				
				 <div  id="signatureDiv">
			    <div class="row py-3" style="display:none">
			    <div class ="col-lg-3 px8">
				  <label for="" class="form-label"><a><fmt:message key="printing.machine"/></a></label> 
				  <select contentPrintingMachine name="printingMachine" class="form-select"  disabled="disabled" >
				    <option selected>Choose...</option>
				    <c:forEach items="${printingMachines}" var="printingMachine">
		                <option value="${printingMachine.id},${printingMachine.plateLength},${printingMachine.plateWidth}">${printingMachine.name}</option>
		            </c:forEach>
		         </select>
			   </div>
			   <div class ="col-lg-3 px8" style="position: relative; left: 10px;">
				 <label for="" class="form-label"><a><fmt:message key="content.print.type"/> </a></label>
				 <select contentPrintType name="printType" updateContentSignature class="form-select">
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
                     <input name="frontColor" contentFrontColorNumber type="number" min="0" placeholder="<fmt:message key='front'/>" max="5" style="postion-relative-left:2px;position: relative;left: 4px;">                 
				     </div>
				     <div class="col-6 volume-cover-w">
				       <input name="backColor" type="number" min="0" max="5"  contentBackColorNumber placeholder="<fmt:message key='back'/>">  
                    </div>
				  </div>
                  </div>
                  <div class ="col-lg-3 px8" style="position: relative; left:10px;">
				  <label for="" class="form-label"><a><fmt:message key="signature"/></a></label>
				  <div>
				  <input name="signature" type="number" step=".1" delContentSign allSignatures style="width:70px;color:red; text-align:center" onclick="this.oldValue=this.value" onchange="signatureChange(this.value,this.parentNode.parentNode.parentNode.parentNode, oldValue)">
				  <span> <button style="background:transparent;border-style: none;color: orange; font-size: 20px;" type="button" onclick="deleteContentsignature(this.parentNode.parentNode.parentNode.parentNode,this.parentNode.parentNode.parentNode.parentNode.parentNode)" style="background:red"><i class="ri-delete-bin-3-line"></i></i></button> </span>
				 </div> 
	            </div>
	          		  
				</div>

			  <div class="row py-3" id="test-me">
			   <div class ="col-lg-3 px8" >
				  <label for="" class="form-label"><a><fmt:message key="printing.machine"/></a></label> 
				 <select  contentPrintingMachine name="printingMachine" class="form-select" onchange="signatureCalculation(this.value,this.parentNode.parentNode.parentNode)">
			      <option >Choose...</option>
				    <c:forEach items="${printingMachines}" var="printingMachine">
				    <c:if test="${jobColorCombinaition.printingMachine.id==printingMachine.id}">
				    	<option selected value="${printingMachine.id},${printingMachine.plateLength},${printingMachine.plateWidth}">${printingMachine.name}</option>
				    </c:if>
				     <c:if test="${jobColorCombinaition.printingMachine.id!=printingMachine.id}">
				    	<option  value="${printingMachine.id},${printingMachine.plateLength},${printingMachine.plateWidth}">${printingMachine.name}</option>
				    </c:if>
		                
		            </c:forEach>
		         </select>
			   </div>
			   <div class ="col-lg-3 px8" style="position: relative; left: 10px;">
				 <label for="" class="form-label"><a><fmt:message key="content.print.type"/></a> </label>
				<select contentPrintType name="printType" class="form-select">
				   <option >Choose...</option>
				  <c:forEach items="${printTypes}" var="printType">

                     <c:if test="${jobColorCombinaition.printType.id==printType.id}">
				    	<option selected value="${printType.id}">${printType.name}</option>
				    </c:if>
				    <c:if test="${jobColorCombinaition.printType.id!=printType.id}">
				    	<option value="${printType.id}">${printType.name}</option>
				    </c:if>

                   </c:forEach>
                 </select>

               </div>
				  <div class ="col-lg-3 px8" style="position: relative; left: 10px;">
					<label for="" class="form-label"><a><fmt:message key="content.color.combination"/></a></label>
					 <div class="row">
				   <div class="col-6 volume-cover-l">
                     <input name="frontColor" value="${jobColorCombinaition.frontColorNumber}"  contentFrontColorNumber type="number" min="0" max="5" placeholder="<fmt:message key='front'/>" style="postion-relative-left:2px;position: relative;left: 4px;" >
					                  
				     </div>
				     <div class="col-6 volume-cover-w">
				       <input  name="backColor" value="${jobColorCombinaition.backColorNumber}"  type="number" min="0" max="5" placeholder="<fmt:message key='back'/>"  contentBackColorNumber>
					     
                    </div>
				  </div>
                  </div>
                  <div class ="col-lg-3 px8" style="position: relative; left:10px;">
				  <label for="" class="form-label"><a><fmt:message key="signature"/></a></label>
				  <div> <input name="signature" value="${jobColorCombinaition.numberOfSignature}"  type="number" id="signature-content-to-randup" step=".1" style="width:70px;color:red; text-align:center" readonly="readonly" allSignatures inputSignReadonly>
				  	<span><button  type="button" style="display: inline;" id="duplicateButton" onclick="updateContentSignature(this.parentNode.parentNode.parentNode.parentNode.parentNode,0,this.parentNode.parentNode.parentNode.parentNode)" ><i class="ri-add-fill"></i></button> </span>
<!-- 				  	<span onclick="randUpContentSignature()"><i style="color: green; font-size: 22px" class="bi bi-arrow-up"></i></span> -->
				 </div> 
				  <div>
					 <span style="position: relative;left: 48%;bottom: 113%;" onclick="randUpContentSignature()"><i style="color: green; font-size: 22px" class="bi bi-arrow-up"></i></span>
				</div> 
	            </div>
				</div>
		
				</div>
				<!-- 	End SignatureDiv bloc -->
				
			<c:if test="${paperTypeCounter==1 and colorCounter!=1 }">
				<!-- start	First ContententPaper childs -->	
			  <div class="row py-3">
			    <div class ="col-lg-3 px8">
				  <label for="" class="form-label"><a><fmt:message key="printing.machine"/></a></label> 
				  <select  contentPrintingMachine name="printingMachine class="form-select" onchange="signatureCalculation(this.value,this.parentNode.parentNode.parentNode)">
			      <option >Choose...</option>
				    <c:forEach items="${printingMachines}" var="printingMachine">
				    <c:if test="${jobColorCombinaition.printingMachine.id==printingMachine.id}">
				    	<option selected value="${printingMachine.id},${printingMachine.plateLength},${printingMachine.plateWidth}">${printingMachine.name}</option>
				    </c:if>
				     <c:if test="${jobColorCombinaition.printingMachine.id!=printingMachine.id}">
				    	<option  value="${printingMachine.id},${printingMachine.plateLength},${printingMachine.plateWidth}">${printingMachine.name}</option>
				    </c:if>
		                
		            </c:forEach>
		         </select>
			   </div>
			   <div class ="col-lg-3 px8" style="position: relative; left: 10px;">
				 <label for="" class="form-label"><a><fmt:message key="content.print.type"/> </a></label>
				 <select contentPrintType name="printType" class="form-select">
				   <option >Choose...</option>
				  <c:forEach items="${printTypes}" var="printType">

                     <c:if test="${jobColorCombinaition.printType.id==printType.id}">
				    	<option selected value="${printType.id}">${printType.name}</option>
				    </c:if>
				    <c:if test="${jobColorCombinaition.printType.id!=printType.id}">
				    	<option value="${printType.id}">${printType.name}</option>
				    </c:if>

                   </c:forEach>
                 </select>

               </div>
				  <div class ="col-lg-3 px8" style="position: relative; left: 10px;">
					<label for="" class="form-label"><a><fmt:message key="content.color.combination"/></a></label>
				<div class="row">
				   <div class="col-6 volume-cover-l">
                     <input name="frontColor" value="${jobColorCombinaition.frontColorNumber}"  contentFrontColorNumber type="number" min="0" placeholder="<fmt:message key='front'/>" max="5" style="postion-relative-left:2px;position: relative;left: 4px;">                 
				     </div>btn btn-primary
				     <div class="col-6 volume-cover-w">
				       <input name="backColor" value="${jobColorCombinaition.backColorNumber}"  type="number" min="0" max="5"  contentBackColorNumber placeholder="<fmt:message key='back'/>">  
                    </div>
				  </div>
                  </div>
                  <div class ="col-lg-3 px8" style="position: relative; left:10px;">
				  <label for="" class="form-label"><a><fmt:message key="signature"/></a></label>
				  <div>
				  <input name="signature" value="${jobColorCombinaition.numberOfSignature}"  type="number" step=".1" delContentSign allSignatures style="width:70px;color:red; text-align:center" onclick="this.oldValue=this.value" onchange="signatureChange(this.value,this.parentNode.parentNode.parentNode.parentNode, oldValue)">
				  <span> <button style="background:transparent;border-style: none;color: orange; font-size: 20px;" type="button" onclick="deleteContentsignature(this.parentNode.parentNode.parentNode.parentNode,this.parentNode.parentNode.parentNode.parentNode.parentNode)" style="background:red"><i class="ri-delete-bin-3-line"></i></i></button> </span>
				 </div> 
	            </div>
	          		  
				</div>
				<!-- End	First ContententPaper childs -->
				
			   </c:if>
				</c:if>
				
				<!-- Start	other ContententPaper -->
				
				<c:if test="${paperTypeCounter!=1 and colorCounter==1 }">
			 <div  id="eachSignatureDiv"> 
			 <hr style="margin-top: 10px; ">	
			  <div class="row py-3" style="display:none">
			    <div class ="col-lg-3 px8" >
				  <label for="" class="form-label"><a><fmt:message key="printing.machine"/></a></label> 
				  <select contentPrintingMachine name="printingMachine" class="form-select" disabled="disabled">
				    <option selected>Choose...</option>
				    <c:forEach items="${printingMachines}" var="printingMachine">
		                <option value="${printingMachine.id},${printingMachine.plateLength},${printingMachine.plateWidth}">${printingMachine.name}</option>
		            </c:forEach>
		         </select>
			   </div>
			   <div class ="col-lg-3 px8" style="position: relative; left: 10px;">
				 <label for="" class="form-label"><a><fmt:message key="content.print.type"/></a> </label>
				 <select contentPrintType name="printType" class="form-select">
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
                     <input name="frontColor" contentFrontColorNumber placeholder="<fmt:message key='front'/>" type="number" min="0" max="5" style="postion-relative-left:2px;position: relative;left: 4px;">
					                  
				     </div>
				     <div class="col-6 volume-cover-w">
				       <input name="backColor" type="number" min="0" max="5" placeholder="<fmt:message key='back'/>" contentBackColorNumber>
					     
                    </div>
				  </div>
                  </div>
                  <div class ="col-lg-3 px8" style="position: relative; left:10px;">
				  <label for="" class="form-label"><a><fmt:message key="signature"/></a></label>
				  <div> 
				  <input name="signature" type="number" step=".1" delContentSign allSignatures style="width:70px;color:red; text-align:center" onclick="this.oldValue=this.value" onchange="signatureChange(this.value,this.parentNode.parentNode.parentNode.parentNode,oldValue)">
				  <span> <button  type="button" onclick="deleteContentsignature(this.parentNode.parentNode.parentNode.parentNode,this.parentNode.parentNode.parentNode.parentNode.parentNode)" style="background:red"><i class="ri-delete-bin-3-line"></i></i></button> </span>
				 </div> 
	            </div>
	           
				</div>

			  <div class="row py-3" >
			    <div class ="col-lg-3 px8" >
				  <label for="" class="form-label"><a><fmt:message key="printing.machine"/></a></label> 
				  <select  contentPrintingMachine name="printingMachine" class="form-select" onchange="signatureCalculation(this.value,this.parentNode.parentNode.parentNode)">
			      <option >Choose...</option>
				    <c:forEach items="${printingMachines}" var="printingMachine">
				    <c:if test="${jobColorCombinaition.printingMachine.id==printingMachine.id}">
				    	<option selected value="${printingMachine.id},${printingMachine.plateLength},${printingMachine.plateWidth}">${printingMachine.name}</option>
				    </c:if>
				     <c:if test="${jobColorCombinaition.printingMachine.id!=printingMachine.id}">
				    	<option  value="${printingMachine.id},${printingMachine.plateLength},${printingMachine.plateWidth}">${printingMachine.name}</option>
				    </c:if>
		                
		            </c:forEach>
		         </select>
			   </div>
			   <div class ="col-lg-3 px8" style="position: relative; left: 10px;">
				 <label for="" class="form-label"><a><fmt:message key="content.print.type"/> </a></label>
				<select contentPrintType name="printType" class="form-select">
				   <option >Choose...</option>
				  <c:forEach items="${printTypes}" var="printType">

                     <c:if test="${jobColorCombinaition.printType.id==printType.id}">
				    	<option selected value="${printType.id}">${printType.name}</option>
				    </c:if>
				    <c:if test="${jobColorCombinaition.printType.id!=printType.id}">
				    	<option value="${printType.id}">${printType.name}</option>
				    </c:if>

                   </c:forEach>
                 </select>

               </div>
				  <div class ="col-lg-3 px8" style="position: relative; left: 10px;">
					<label for="" class="form-label"><a><fmt:message key="content.color.combination"/></a></label>
					 <div class="row">
				   <div class="col-6 volume-cover-l">
                     <input name="frontColor value="${jobColorCombinaition.frontColorNumber}"  contentFrontColorNumber type="number" min="0" placeholder="<fmt:message key='front'/>" max="5" style="postion-relative-left:2px;position: relative;left: 4px;">
					                  
				     </div>
				     <div class="col-6 volume-cover-w">
				       <input name="backColor" value="${jobColorCombinaition.backColorNumber}"  type="number" min="0" max="5" placeholder="<fmt:message key='back'/>" contentBackColorNumber>
					     
                    </div>
				  </div>
                  </div>
                  <div class ="col-lg-3 px8" style="position: relative; left:10px;">
				  <label for="" class="form-label"><a><fmt:message key="signature"/></a></label>
				  <div> <input name="signature" value="${jobColorCombinaition.numberOfSignature}"  type="number" step=".1" id="" style="width:70px;color:red; text-align:center" allSignatures inputSignReadonly readonly="readonly">
				  <span> <button   type="button" id="duplicateButton" style="display: inline;" onclick="updateContentSignature(this.parentNode.parentNode.parentNode.parentNode.parentNode,1,this.parentNode.parentNode.parentNode.parentNode)"><i class="ri-add-fill"></i></button> </span>
				 </div> 
				 
	            </div>
				</div>
			</div>	
			<!-- End	other ContententPaper  -->
				</c:if>
				
				
				
				<!-- Start	other ContententPaper childs -->
				<c:if test="${paperTypeCounter!=1 and colorCounter!=1 }">
					 <div class="row py-3">
			    <div class ="col-lg-3 px8" >
				  <label for="" class="form-label"><a><fmt:message key="printing.machine"/></a></label> 
				 <select  contentPrintingMachine name="printingMachine" class="form-select" onchange="signatureCalculation(this.value,this.parentNode.parentNode.parentNode)">
			      <option >Choose...</option>
				    <c:forEach items="${printingMachines}" var="printingMachine">
				    <c:if test="${jobColorCombinaition.printingMachine.id==printingMachine.id}">
				    	<option selected value="${printingMachine.id},${printingMachine.plateLength},${printingMachine.plateWidth}">${printingMachine.name}</option>
				    </c:if>
				     <c:if test="${jobColorCombinaition.printingMachine.id!=printingMachine.id}">
				    	<option  value="${printingMachine.id},${printingMachine.plateLength},${printingMachine.plateWidth}">${printingMachine.name}</option>
				    </c:if>
		                
		            </c:forEach>
		         </select>
			   </div>
			   <div class ="col-lg-3 px8" style="position: relative; left: 10px;">
				 <label for="" class="form-label"><a><fmt:message key="content.print.type"/></a> </label>
				<select contentPrintType name="printType" class="form-select">
				   <option >Choose...</option>
				  <c:forEach items="${printTypes}" var="printType">

                     <c:if test="${jobColorCombinaition.printType.id==printType.id}">
				    	<option selected value="${printType.id}">${printType.name}</option>
				    </c:if>
				    <c:if test="${jobColorCombinaition.printType.id!=printType.id}">
				    	<option value="${printType.id}">${printType.name}</option>
				    </c:if>

                   </c:forEach>
                 </select>

               </div>
				  <div class ="col-lg-3 px8" style="position: relative; left: 10px;">
					<label for="" class="form-label"><a><fmt:message key="content.color.combination"/></a></label>
					 <div class="row">
				   <div class="col-6 content-cover-l">
                     <input name="frontColor" value="${jobColorCombinaition.frontColorNumber}"  contentFrontColorNumber placeholder="<fmt:message key='front'/>" type="number" min="0" max="5" style="postion-relative-left:2px;position: relative;left: 4px;">
					                  
				     </div>
				     <div class="col-6 volume-cover-w">
				       <input name="backColor" value="${jobColorCombinaition.backColorNumber}"  type="number" min="0" max="5" placeholder="<fmt:message key='back'/>" contentBackColorNumber>
					     
                    </div>
				  </div>
                  </div>
                  <div class ="col-lg-3 px8" style="position: relative; left:10px;">
				  <label for="" class="form-label"><a><fmt:message key="signature"/></a></label>
				  <div> 
				  <input name="signature" type="number"  value="${jobColorCombinaition.numberOfSignature}"  step=".1" delContentSign allSignatures style="width:70px;color:red; text-align:center" onclick="this.oldValue=this.value" onchange="signatureChange(this.value,this.parentNode.parentNode.parentNode.parentNode,oldValue)">
				  <span> <button  type="button" onclick="deleteContentsignature(this.parentNode.parentNode.parentNode.parentNode,this.parentNode.parentNode.parentNode.parentNode.parentNode)" style="background:red"><i class="ri-delete-bin-3-line"></i></i></button> </span>
				 </div> 
	            </div>
	           
				</div>
					
					<!-- End	other ContententPaper childs -->
				</c:if>
				
				<c:set var="colorCounter" value="2"/>
				</c:forEach>
				<c:set var="paperTypeCounter" value="2"/>
			</c:forEach>		
			
		</div> 
		
		
	
			<div class ="row py-3 "style="margin-top:50px" >
		     <div class ="col-sm-6"> 
		      <button type="button" style="width:125px;float:left" class="all-button-style" onclick="navigate(3,2);"><fmt:message key="previews"/></button>	
		    </div>
	        <div class ="col-sm-6">
	        	
	         <button style="width:125px;float:right"  type="button" class="all-button-style" id="next-btn1" onclick="validateTab3();navigate(3,4);"><fmt:message key="next"/></button>	
	        		
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
					<input type="number" id="xPerforated" value = "${jobActivit}">
                  </div>
				  <div class ="col-lg-3 px8" style="position: relative; left: 10px;">
					<label for="" class="form-label"><fmt:message key="x.numbered"/></label>
					<input type="number" id="xNumbered" value="${numbered}">
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
					<input type="number" id="creased" value="${creased}">
			      </div>
				 <div class ="col-lg-3 px8" style="position: relative; left:10px;">
					<label for="" class="form-label"><fmt:message key="x.Wire-stitched"/></label>
					<input type="number" id="xWire-stitched" value="${wireStiched}">
                  </div>
				  <div class ="col-lg-3 px8" style="position: relative; left: 10px;">
					<label for="" class="form-label"><fmt:message key="x.cross"/></label>
					<input type="number" id="xcross" value="${cross}">
                  </div>
				</div>	
			 <div class="row py-4">
			     <div class ="col-lg-3 px8" style="position: relative; left:10px;">
			   <label for="" class="form-label"><a><fmt:message key="binding.type"/></a></label> 
			      <select id="bindingType" name="name" class="form-select">
					<c:forEach items="${bindingTypes}" var="bindingTyp">
					<c:if test="${jobActivity.bindingType.id==bindingTyp.id}">
                      <option selected value="${bindingTyp.id}">${bindingTyp.name}</option>
                     </c:if>
                     <c:if test="${jobActivity.bindingType.id!=bindingTyp.id}">
                      <option  value="${bindingTyp.id}">${bindingTyp.name}</option>
                     </c:if>
                    </c:forEach>
                  </select>
			    </div>
			    <div class ="col-lg-3 px8" style="position: relative; left:10px;">
				  <label for="" class="form-label"><a><fmt:message key="handFoldCov"/></a></label>
				  <input type="number" id="handFoldCov" value="${handFoldCov}">
                </div>
                 <div class ="col-lg-3 px8" style="position: relative; left:10px;">
			   		<label for="" class="form-label"><a>Stitching</a></label> 
				      <select  id="stitching" name="name" class="form-select">
				        <option selected value="${job.getJobActivity().getIsStitching()} "selected> ${job.getJobActivity().getIsStitching()} </option>
	                    <option value="Left-Stitch">Head</option>
	                    <option value="Head-Stitch">Left</option>
	                  </select>
			    </div>
			   </div>
				
                 <div class="row py-4">
				   <div class ="col-lg-2 px2" >
				    <div>
                     <div class="form-check">
                      <label class="form-check-label" for="trimmed"><fmt:message key="trimmed"/></label>
                       <input class="form-check-input" type="checkbox" id="trimmed" ${jobActivity.trimmed ? 'checked':''}>
                    </div>
				  </div>
			    </div>
				<div class ="col-lg-2 px2" style="position: relative; left: 10px;">
                     <div class="form-check">
                      <label class="form-check-label" for="sellotaped"><fmt:message key="sellotaped"/></label>
                       <input class="form-check-input" type="checkbox" id="sellotaped" ${jobActivity.selloptaped ? 'checked':''}>
                    </div>
                  </div>
				  <div class ="col-lg-2 px2" style="position: relative; left: 10px;">
                     <div class="form-check">
                      <label class="form-check-label" for="sewn"><fmt:message key="sewn"/></label>
                       <input class="form-check-input" type="checkbox" id="sewn" ${jobActivity.sewn ? 'checked':''}>
                    </div>
				  </div>
				  
				   <div class ="col-lg-2 px2">
                     <div class="form-check">
                      <label class="form-check-label" for="handgather"><fmt:message key="handgather"/></label>
                       <input class="form-check-input" type="checkbox" id="handgather" ${jobActivity.handgather ? 'checked':''}>
                    </div>
                    </div>
<!--                     <div class ="col-lg-2 px2"> -->
<!--                     <div class="form-check"> -->
<%--                       <label class="form-check-label" for="stitching"><fmt:message key="stitching"/></label> --%>
<%--                        <input class="form-check-input" type="checkbox" id="stitching"  ${jobActivity.stitching ? 'checked':''}> --%>
<!--                     </div> -->
<!-- 				  </div> -->
                  </div>
				</div>	
		           <div class ="row py-3 "style="margin-top:50px" >
				     <div class ="col-sm-6"> 
				      <button type="button" style ="width:125px;float:left" class="all-button-style" onclick="navigate(4,3);"> <fmt:message key="previews"/></button>	
				     </div>
			         <div class ="col-sm-6">
			         <button type="button" style="width:125px;float:right"  class="all-button-style" id="next-btn1" onclick="navigate(4,5);summary()"><fmt:message key="next"/></button>			
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
					       <th scope="col"><fmt:message key="paper.type"/></th>
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
					      <th scope="col"><fmt:message key="paper.type"/></th>
					      <th scope="col"><fmt:message key="machine"/></th>
					      <th scope="col"> <fmt:message key="print.type"/> </th>
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
	                        <div><fmt:message key="x.perforated"/> : <span id="x-perforated"> </span>${job.getJobActivity().getXPerforated()}</div>
	                        <div><fmt:message key="x.numbered"/> : <span id="x-numbered"></span> ${job.getJobActivity().getXNumbered()}</div>
	                        <div><fmt:message key="x.crossed"/>  : <span id="x-crossed"></span>${job.getJobActivity().getXCross()}</div>
	                        <div><fmt:message key="x.wired.stitched"/>  : <span id="x-wired"></span>${job.getJobActivity().getXWiredStiched()}</div>
	                        <div><fmt:message key="creased"/> : <span id="crease"></span>${job.getJobActivity().getXCreased()}</div>
	                    </div>
	                    <div class="col-sm-4">
	                        <div><fmt:message key="lamination.sides"/> : <span id="laminated-sides"></span> ${job.getJobActivity().getLamination()} </div>
	<%--                         <div> Glueing Bound: <span id="glue-bound"></span>${job.getJobActivity().getGlueOption()}</div> --%>
	                        <div><fmt:message key="binding.type"/> : <span id="binding-type"></span> ${job.getJobActivity().getBindingType().getName()}</div>
	                        <div><fmt:message key="sewn"/> :<span class=" ${job.getJobActivity().isSewn() ? 'true' : 'false'}"> ${job.getJobActivity().isSewn() ? 'yes' : 'no'}</span>
	                        </div>
	                    </div>
                    <div class="col-sm-4">
                        <div><fmt:message key="handgather"/> :<span class=" ${job.getJobActivity().isHandgather() ? 'true' : 'false'}"> ${job.getJobActivity().isHandgather() ? 'yes' : 'no'}</span>
                        </div>
                        <div><fmt:message key="stitching"/> : ${job.getJobActivity().getIsStitching()}
                        </div>
                        <div><fmt:message key="trimmed"/> :<span class=" ${job.getJobActivity().isTrimmed() ? 'true' : 'false'}"> ${job.getJobActivity().isTrimmed() ? 'yes' : 'no'}</span>
                        </div>
                        <div> <fmt:message key="sellotaped"/> :<span class=" ${job.getJobActivity().isSelloptaped() ? 'true' : 'false'}"> ${job.getJobActivity().isSelloptaped() ? 'yes' : 'no'}</span>
                        </div>
                    </div>
                </div>
		           <div class ="row py-3 "style="margin-top:50px" >
				     <div class ="col-sm-6"> 
				      <button type="button" style="width:125px;float:left" class="all-button-style" onclick="navigate(5,4);removeRows()"> <fmt:message key="previews"/></button>	
				     </div>
			         <div class ="col-sm-6">
			         <button   type="button" style="width:125px;float:right"  class="all-button-style" id="next-btn1" onclick="navigate(4,5); submitForm('${job.id}')"><fmt:message key="submit"/></button>			
			        </div>
			        </div>
               	</div>
               	<!-- <--------------------tab 5 ends ----------------------------------------------------------------------->  
               	</div>
               	</div>
               	
             </form>  
            </div>
         </div>
      </div>
      
      <!-- End Default Tabs -->
      </div>
    </section>  
<script src="assets/js/billing/job.js"></script> 




