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
              <h5 class="card-title"> <fmt:message key="job.creation"/></h5>

              <!-- Default Tabs -->
              <ul style="background-color: #fbfbfb;"  class="nav nav-tabs d-flex" id="myTabjustified" role="tablist">
                <li class="nav-item flex-fill" role="presentation">
                  <button class="nav-link w-100 active" id="home-tab" data-bs-toggle="tab" data-bs-target="#home-justified" type="button" role="tab" aria-controls="home" aria-selected="true">JOB DESCRIPTION</button>
                </li>
                <li class="nav-item flex-fill" role="presentation">
                  <button class="nav-link w-100" id="profile-tab" data-bs-toggle="tab" data-bs-target="#profile-justified" type="button" role="tab" aria-controls="profile" aria-selected="false">PAPER OPTIONS</button>
                </li>
                <li class="nav-item flex-fill" role="presentation">
                  <button class="nav-link w-100" id="contact-tab" data-bs-toggle="tab" data-bs-target="#contact-justified" type="button" role="tab" aria-controls="contact" aria-selected="false">PRINTING OPTIONS</button>
                </li>
                <li class="nav-item flex-fill" role="presentation">
                  <button class="nav-link w-100" id="contact-tabm" data-bs-toggle="tab" data-bs-target="#justified" type="button" role="tab" aria-controls="justified" aria-selected="false">FINISHING OPTIONS</button>
                </li>
                 <li class="nav-item flex-fill" role="presentation">
                  <button class="nav-link w-100" id="contact-taob" data-bs-toggle="tab" data-bs-target="#samary" type="button" role="tab" aria-controls="samary" aria-selected="false">SUMMARY</button>
                </li>
              </ul>
           <form action="" method="post" id="myForm" style=" padding-left: 5%;">             
              <div class="tab-content pt-2" id="myTabjustifiedContent">
                <div class=" container tab-pane fade show active" id="home-justified" role="tabpanel" aria-labelledby="home-tab">                
<!-- <-------------------------------------------------------------------------------------------> 
                <div style="position: relative;bottom: -20px;" >		
			     <div class="row py-4">
				  <div class ="col-lg-3 px8" >					 
				   <label for="" class="form-label"><fmt:message key="customer"/></label>
				   <select id="customer"  class="form-select" >
				   <option>Choose...</option>
				    <option value="0"><fmt:message key="new.customer"/></option>
	                 <c:forEach items="${customers}" var="customer">
	                   <option value="${customer.id}">${customer.name}</option>
	                 </c:forEach>
	                 
	                </select>
			      </div>
				  <div class ="col-lg-3 px8" style="position: relative; left: 80px;"> 
					<label for="" class="form-label"><fmt:message key="job.type"/></label> 
					<select id="jobType" name="jobType" class="form-select" >
					  <option>Choose...</option>
					  <c:forEach items="${jobTypes}" var="jobType">
                        <option value="${jobType.id}" >${jobType.name}</option>
                      </c:forEach>
                     
                    </select>
				  </div>
				  <div class ="col-lg-3 px8" style="position: relative; left: 139px;">
				  <label for="title" class="form-label"><fmt:message key="title"/></label>
					<div>
					  <input id= "title" name="title" type= "text">
					</div>
                  </div>
				</div>						
			     <div class="row py-3">
				  <div class ="col-lg-3 px8">
				    <label for="coverVolume" class="form-label"><fmt:message key="volume.cover"/></label> 
				     <div>
					  <input id= "volumeOfCover" name="volumeOfCover" type="number" value=4>
				    </div>
				  </div>
				  <div class ="col-lg-3 px8" style="position: relative; left: 80px;"> 
				    <label for="volumeOfContent"   class="form-label"><fmt:message key="volume.content"/></label>
				    <div>
					  <input id= "volumeOfContent" name="volumeOfContent" type="number" onchange="totalContentVolumeChange()">
				    </div>
				  </div>
				  <div class ="col-lg-3 px8" style="position: relative; left: 139px;">
				   <label for="" class="form-label"> <fmt:message key="format"/></label>
              	   <select id="paperFormat" onchange="paperF(this.value)" name="name" class="form-select">
              	      <option selected>Choose...</option>
              	      <option onclick="">Custom Format...</option>
					  <c:forEach items="${paperFormats}" var="paperFormat">
                        <option value="${paperFormat.id},${paperFormat.length},${paperFormat.width}">${paperFormat.name}</option>
                      </c:forEach>
                    </select>
			      </div>
		       </div>		
			 <div class="row py-3">
			   <div class ="col-lg-3 px8">
			     <label for="" class="form-label"> <fmt:message key="open.format"/></label> 
			       <div class="row">
				     <div class="col-6 volume-cover-l">
                       <input id="openWidth" type="number" style="postion-relative-left:2px;position: relative;left: 4px;"  placeholder= "<fmt:message key='open.width'/> ">
					                
				      </div>
				      <div  class="col-6 volume-cover-w">
				      <input id="openLength" type="number" placeholder="<fmt:message key='open.legnth'/>">
					  
                   </div>
				 </div>
			  </div>
			  <div class ="col-lg-3 px8" style="position: relative; left: 80px;"> 
			   <label for="" class="form-label"><fmt:message key="close.format"/></label>
			     <div class="row">
				   <div class="col-6 volume-cover-l">
                     <input type="number" id="closeWidth" style="postion-relative-left:2px;position: relative;left: 4px;"  placeholder= "<fmt:message key="close.width"/> ">
					                 
				     </div>
				     <div  class="col-6 volume-cover-w">
				       <input id="closeLength" type="number" placeholder="<fmt:message key="open.legnth"/>">
					 
                    </div>
				  </div>
			   </div>
			
		      </div>	
		      <div class="row py-3">
			    <div class ="col-lg-3 px8">
			      <div>
                  <div class="form-check">
                    <label class="form-check-label" for="existingPlate"><fmt:message key="existing.plate"/></label>
                      <input class="form-check-input" type="checkbox" name="existingPlate" id="existingPlate">
                  </div>
                  <div class="form-check">                     
                    <label class="form-check-label" for="gridCheck1"><fmt:message key="type.setting.by.us"/></label>
                    <input class="form-check-input" type="checkbox" name="name" id="typesettingByUs">
                  </div>
			    </div>
			    </div>
			  <div class ="col-lg-3 px8" style="position: relative; left: 139px;"> 
			     <div>
			      <div class="form-check">                      
                    <label class="form-check-label" for="gridCheck1"><fmt:message key="data.supplied.by.customer"/></label>
                    <input class="form-check-input" type="checkbox" id="dataSuppliedByCustomer">
                  </div>
                  <div class="form-check">
                    <label class="form-check-label" for="gridCheck1"><fmt:message key="layout.by.us"/></label>
                    <input class="form-check-input" type="checkbox" id="layoutByUs">
                  </div>
			    </div>
			  </div>
			  <div class ="col-lg-3 px8" style="position: relative; left: 139px;">
			  
			  </div>
		    </div>	
			<button style=" width: 94px;" type="button"  class="next" onclick="tab1NextBtnAction()" id="next-btn" >Next</button>			
          </div>             
        </div>
 <!-- <----------------------------------- Tab2 ------------------------------->   
 
       <div class=" container tab-pane fade"  id="profile-justified" role="tabpanel" aria-labelledby="profile-tab">
         <div style="position: relative;bottom: -20px;" id="mainDiv" >	
         	
		   <div class="row py-4">
			 <div class ="col-lg-3 px8" >
			   <label for="" class="form-label"><fmt:message key="cover.paper.type"/></label> 
			   <select id="coverPaperType" name="name" class="form-select">
			     <option selected>Choose...</option>
				 <c:forEach items="${paperTypes}" var="paperType">
                   <option value="${paperType.id}">${paperType.name}</option>
                 </c:forEach>
               </select>
			 </div>

			 <div class ="col-lg-3 px8" style="position: relative; left: 70px;">
			   <label for="" class="form-label"><fmt:message key="grammage"/></label>
			   <input style="postion-relative-left:2px;position: relative;left: 4px;" list="coverGrammage" id="xx" name="xx">
					   <datalist id="coverGrammage">
	                    <c:forEach items="${paperGrammages}" var="paperGrammage">
                    <option value="${paperGrammage.value}"></option>
                    </c:forEach>
	                   </datalist>  
              </div>
			  <div class ="col-lg-3 px8" style="position: relative; left: 189px;">
			    <label for="" class="form-label"><fmt:message key="volume"/></label>
			     <input id="coverVolume" type="number"  name="fname" readonly="readonly">
              </div>
		    </div>	
		    
		   <div id="contentDiv">
		   
		   
		   		
			<div class="row py-3"  style="display:none">
			
			  <div class="col-lg-3 px-8" >
			    <label for="" class="form-label"><fmt:message key="content.paper.type"/> </label>
				<select contentPaperType name="name" class="form-select">
				  <option >Choose...</option>
			  <c:forEach items="${paperTypes}" var="paperType">
                    <option value="${paperType.id}">${paperType.name}</option>
                  </c:forEach>
                </select>
			  </div>
			  <div class="col-lg-3 px-8" style="position: relative; left: 70px;">
			    <label for="" class="form-label"><fmt:message key="grammage"/></label>
			     <input contentGrammage type="text"  list="contentGrammage" >
				  <datalist  id="contentGrammage">
	               <c:forEach items="${paperGrammages}" var="paperGrammage">
                    <option value="${paperGrammage.value}"></option>
                    </c:forEach>
	            </datalist>   
			  </div>
			  <div class="col-lg-3 px-8 coverDup" style="position: relative; left: 189px;float:left">
			    <label for="" class="form-label"><fmt:message key="volume"/> </label> 
				<input type="number" contentVolume name="contentVolume" onchange="updateTotalContentvolume(this.value)">
				<button type="button" style="float:right; background:red" onclick="removeContentNode(this,this.previousElementSibling)"><i class="fas fa-trash-alt"></i> </button>
			  </div>
			  
		   </div>
		   
		   <div class="row py-3"  >
			  <div class="col-lg-3 px-8">
			    <label for="" class="form-label"><fmt:message key="content.paper.type"/> </label>
				<select contentPaperType name="name" class="form-select">
				  <option >Choose...</option>
			  <c:forEach items="${paperTypes}" var="paperType">
                    <option value="${paperType.id}">${paperType.name}</option>
                  </c:forEach>
                </select>
			  </div>
			  
			  <div class="col-lg-3 px-8" style="position: relative; left: 70px;">
			    <label for="" class="form-label"><fmt:message key="grammage"/></label>
			     <input contentGrammage type="text" list="contentGrammage">
				  <datalist  id="contentGrammage">
	               <c:forEach items="${paperGrammages}" var="paperGrammage">
                    <option value="${paperGrammage.value}"></option>
                    </c:forEach>
	            </datalist>   
			  </div>
			  <div class="col-lg-3 px-8 coverDup" style="position: relative; left: 189px;">
			    <label for="" class="form-label"><fmt:message key="volume"/> </label> 
				<input type="number" contentVolume name="contentVolume" readonly="readonly">
				 <button type="button"  id="duplicateButton"  onclick="addContentPaperChild()" style="float:right; margin-top:20px;width: 40px; heigth: 40px;"><i class="ri-add-fill"></i> </button>
			  </div>
		   </div>
		   
		    </div>
		  
	     </div>	
	     <div class ="row py-3 "style="margin-top:50px" >
		     <div class ="col-sm-6"> <button   style ="width:125px;float:left"class="previous"><fmt:message key="previews"/></button>	
		    </div>
	        <div class ="col-sm-6">
	        <button   style ="width:125px;float:right"  class="next" id=""><fmt:message key="next"/></button>	
	       </div>
	     
		
	     </div>
				
       </div>         
<!------------------------------- TAB 3 BIGINS --------------->
       <div class="tab-pane fade" id="contact-justified" role="tabpanel" aria-labelledby="contact-tab">
        <div class="container" >	
          <div class="row py-3">
		    <div class ="col-lg-3 px8" >
			  <label for="" class="form-label"><fmt:message key="printing.machine"/></label> 
			  <select id="coverPrintingMachine" name="name" class="form-select">
			    <option selected>Choose...</option>
			    <c:forEach items="${printingMachines}" var="printingMachine">
                  <option value="${printingMachine.id}">${printingMachine.name}</option>
                </c:forEach>
              </select>
			</div>
			<div class ="col-lg-3 px8" style="position: relative; left: 10px;">
			  <label for="" class="form-label"><fmt:message key="cover.print.type"/></label>
			  <select id="coverPrintType" name="name" class="form-select">
			    <option selected>Choose...</option>
			    <c:forEach items="${printTypes}" var="printType">
                  <option value="${printType.id}">${printType.name}</option>
                </c:forEach>
              </select>
            </div>
			<div class ="col-lg-3 px8" style="position: relative; left: 10px;">
			  <label for="" class="form-label"><fmt:message key="color.combination"/></label>
				 <div class="row">
				   <div class="col-6 volume-cover-l">
                     <input id="coverFrontColorNumber" type="number" min="0" max="5" style="postion-relative-left:2px;position: relative;left: 4px;">
					                    
				     </div>
				     <div  class="col-6 volume-cover-w">
				       <input id="converBackColorNumber" type="number" min="0" max="5">
					   
                    </div>
				  </div>
              </div>
              <div class ="col-lg-3 px8" style="position: relative; left:10px;">
			  <label for="" class="form-label"></label>
			  <div> <span class="coverSpace"><fmt:message key="signature"/> 250 </span>
			 </div> 
            </div>
			</div>	
			
		<!-- 			main content div -->
			<div id="mainContentSignature">
			 <div class="row py-3" id="addShow" style="display:none">
			    <div class ="col-lg-3 px8" >
				  <label for="" class="form-label"><fmt:message key="printing.machine"/></label> 
				  <select contentPrintingMachine name="name" class="form-select">
				    <option selected>Choose...</option>
				    <c:forEach items="${printingMachines}" var="printingMachine">
		                <option value="${printingMachine.id}">${printingMachine.name}</option>
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
					<label for="" class="form-label"><fmt:message key="color.combination"/></label>
					 <div class="row">
				   <div class="col-6 volume-cover-l">
                     <input contentFrontColorNumber type="number" min="0" max="5" style="postion-relative-left:2px;position: relative;left: 4px;">               
				     </div>
				     <div class="col-6 volume-cover-w">
				       <input type="number" min="0" max="5"  contentBackColorNumber>
					     
                    </div>
				  </div>
                  </div>
                  <div class ="col-lg-3 px8" style="position: relative; left:10px;">
                 
				  <label for="" class="form-label"></label>
				  <div>

				   <span class="coverSpace"><fmt:message key="signature"/> 30 </span>
				  <span> <button  id="duplicateButton" ><i class="ri-add-fill"></i></button> </span>
				 </div> 
	            </div>
	            <hr style="margin-top: 10px; ">	
				</div>
			
			
			  <div class="row py-3">
			    <div class ="col-lg-3 px8" >
				  <label for="" class="form-label"><fmt:message key="printing.machine"/></label> 
				  <select contentPrintingMachine name="name" class="form-select">
				    <option selected>Choose...</option>
				    <c:forEach items="${printingMachines}" var="printingMachine">
		                <option value="${printingMachine.id}">${printingMachine.name}</option>
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
					<label for="" class="form-label"><fmt:message key="color.combination"/></label>
					 <div class="row">
				   <div class="col-6 volume-cover-l">
                     <input contentFrontColorNumber type="number" min="0" max="5" style="postion-relative-left:2px;position: relative;left: 4px;">
					                  
				     </div>
				     <div class="col-6 volume-cover-w">
				       <input type="number" min="0" max="5"  contentBackColorNumber>
					     
                    </div>
				  </div>
                  </div>
                  <div class ="col-lg-3 px8" style="position: relative; left:10px;">
				  <label for="" class="form-label"></label>
				  <div> <span class="coverSpace"><fmt:message key="signature"/> 30 </span>
				  <span> 		  <button  id="duplicateButton" ><i class="ri-add-fill"></i></button> </span>
				 </div> 
	            </div>
	            <hr style="margin-top: 10px; ">	
				</div>
				
				</div>
		<!-- 			main content div -->
				
				
				
				
				<div style=" position: relative; bottom: 15px;">
                   <button style=" position: relative;" class="previous"><fmt:message key="previews"/></button>	
			       <button style=" position: relative; left: 85%; width: 90px;"  type="button" class="next" id="next-btn1"><fmt:message key="next"/></button>	
			     </div>		
               </div>
             </div>
<!-- <--------------------TAB 4 BEGINS HERE----------------------------------------------------------------------->  
             <div class="tab-pane fade" id="justified" role="tabpanel" aria-labelledby="contact-tab">
                <div class="container" style="position: relative;bottom: -20px;" >
                <div class="row py-4">
				  <div class ="col-lg-3 px8" >
				    <label for="" class="form-label"><fmt:message key="ctp.fees"/></label> 
					<input type="text" id="ctpFees">
			      </div>
				 <div class ="col-lg-3 px8" style="position: relative; left: 70px;">
					<label for="" class="form-label"><fmt:message key="x.Perforated"/></label>
					<input type="number" id="xPerforated">
                  </div>
				  <div class ="col-lg-3 px8" style="position: relative; left: 189px;">
					<label for="" class="form-label"><fmt:message key="x.Numbered"/></label>
					<input type="number" id="xNumbered">
                  </div>
				</div>	
				   <div class="row py-4">
				  <div class ="col-lg-3 px8" >
				    <label for="" class="form-label"><fmt:message key="creased"/></label> 
					<input type="number" id="creased">
			      </div>
				 <div class ="col-lg-3 px8" style="position: relative; left: 70px;">
					<label for="" class="form-label"><fmt:message key="x.Wire-stitched"/></label>
					<input type="number" id="xWire-stitched">
                  </div>
				  <div class ="col-lg-3 px8" style="position: relative; left: 189px;">
					<label for="" class="form-label"><fmt:message key="x.cross"/></label>
					<input type="number" id="xcross">
                  </div>
				</div>	
				 <div class="row py-4">
				<div class ="col-lg-3 px8" >
				  <label for="" class="form-label"><fmt:message key="glued.option"/></label> 
				  <select id="glueingOption" name="name" class="form-select">
				    <option  selected>Choose...</option>
				    <option value="leftSide"><fmt:message key="left.side"/></option>
				   <option value="head"><fmt:message key="head"/></option>
				   <option value="glueBound"><fmt:message key="glue.bound"/></option>
		         </select>
			   </div>
			     <div class ="col-lg-3 px8" style="position: relative; left: 70px;">
			   <label for="" class="form-label"><fmt:message key="binding.type"/></label> 
			      <select id="bindingType" name="name" class="form-select">
			        <option selected>Choose...</option>
					<c:forEach items="${bindingTypes}" var="bindingTyp">
                      <option value="${bindingTyp.id}">${bindingTyp.name}</option>
                    </c:forEach>
                  </select>
			  
			    </div>
			   </div>
				
                 <div class="row py-4">
				   <div class ="col-lg-3 px8" >
				    <div>
                     <div class="form-check">
                      <label class="form-check-label" for="trimmed"><fmt:message key="trimmed"/></label>
                       <input class="form-check-input" type="checkbox" id="trimmed">
                    </div>
				  </div>
			    </div>
				<div class ="col-lg-3 px8" style="position: relative; left: 70px;">
				  <div>

                     <div class="form-check">
                      <label class="form-check-label" for="sellotaped"><fmt:message key="sellotaped"/></label>
                       <input class="form-check-input" type="checkbox" id="sellotaped">
                    </div>
				  </div>
                  </div>
				  <div class ="col-lg-3 px8" style="position: relative; left: 189px;">
                     <div class="form-check">
                      <label class="form-check-label" for="sewn"><fmt:message key="sewn"/></label>
                       <input class="form-check-input" type="checkbox" id="sewn">
                    </div>
				  </div>
                  </div>
				</div>	
				<div class="row py-4">			
                  </div>
                  <div style=" position: relative; bottom: 20px;">
                   <button style=" position: relative;" class="previous"><fmt:message key="previews"/></button>	
			       <button style=" position: relative; left: 85%; width: 90px;"  type="button" class="next" id="next-btn1"><fmt:message key="next"/></button>	
			     </div>
               	</div>
               </div>
         </div>
       </form>   
              </div><!-- End Default Tabs -->

            </div>
          </div>
		</div>
    </section>  
<script src="assets/js/billing/job.js"></script> 
  
  
  
  
  
  
  