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
                  <button style="color: blue;" disabled="disabled" class="nav-link w-100 active" id="home-tab" data-bs-toggle="tab" data-bs-target="#tab1" type="button" role="tab" aria-controls="home" aria-selected="true">JOB DESCRIPTION</button>
                </li>
                <li class="nav-item flex-fill" role="presentation">
                  <button disabled="disabled" class="nav-link w-100"  data-bs-toggle="tab" data-bs-target="#tab2" type="button" role="tab" aria-controls="profile" aria-selected="false">SUMMARY</button>
                </li>
              </ul>
           <form action="" method="post" id="myForm" style=" padding-left: 5%;">             
              <div class="tab-content pt-2" id="myTabjustifiedContent">
                <div class=" container tab-pane fade show active" id="tab1" role="tabpanel" aria-labelledby="home-tab">  
                              
<!-- <--------------------------------- TAB 1----------------------------------------------------------> 
                <div style="position: relative;bottom: -20px;" >		
			     <div class="row py-4 px-6">
				  <div class ="col-lg-3 px8" >					 
				   <label for="" class="form-label"><a> <fmt:message key="customer"/> </a></label>
				   <select id="customer"  class="form-select" >
				   <option>Choose...</option>
				    <option data-bs-toggle="modal" data-bs-target="#ExtralargeModal" onclick="loadPageModalForm('customer/customerForm')"><fmt:message key="new.customer"/></option>
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
				  <label for="title" class="form-label"><a><fmt:message key="title"/></a> </label>
					<div>
					  <input id= "title" name="title" type= "text">
					</div>
                  </div>
				</div>	
							 <div class="row py-3">
			  <div class ="col-lg-3 px8" >
				   <label for="" class="form-label"> <a><fmt:message key="format"/></a> </label>
              	   <select id="paperFormat" onchange="paperF(this.value)" name="name" class="form-select">
              	      <option selected>Choose...</option>
              	      <option onclick="">Custom Format...</option>
					  <c:forEach items="${paperFormats}" var="paperFormat">
                        <option value="${paperFormat.id},${paperFormat.length},${paperFormat.width}">${paperFormat.name}</option>
                      </c:forEach>
                    </select>
			      </div>
			   <div class ="col-lg-3 px8" style="position: relative; left: 10px;">
			     <label for="" class="form-label"> <a><fmt:message key="open.format"/></a> </label> 
			       <div class="row">
				     <div class="col-6 volume-cover-l">
                       <input id="openWidth" type="number" style="postion-relative-left:2px;position: relative;left: 4px;"  placeholder= "<fmt:message key='open.width'/> ">
				      </div>
				      <div  class="col-6 volume-cover-w">
				      <input id="openLength" type="number" placeholder="<fmt:message key='open.legnth'/>">
					  
                   </div>
				 </div>
			  </div>
			  <div class ="col-lg-3 px8" id="closeDimensionDiv" style="position: relative; left: 10px;"> 
			   <label for="" class="form-label"> <a><fmt:message key="close.format"/></a> </label>
			     <div class="row">
				   <div class="col-6 volume-cover-l">
                     <input type="number" id="closeWidth" style="postion-relative-left:2px;position: relative;left: 4px;"  placeholder= "<fmt:message key="open.width"/> ">
					                 
				     </div>
				     <div  class="col-6 volume-cover-w">
				       <input id="closeLength" type="number" placeholder="<fmt:message key="open.legnth"/>">
					 
                    </div>
				  </div>
			   </div>
			
		      </div>
									
			     <div class="row py-3">
				  <div id="volumeofCover" class ="col-lg-3 px8">
				    <label for="coverVolume" class="form-label"> <a><fmt:message key="volume.cover"/></a></label> 
				     <div>
					  <input id= "volumeOfCover" name="volumeOfCover" type="number" value=4>
				    </div>
				  </div>
				  <div id="volumeofContent" class ="col-lg-3 px8" style="position: relative; left: 10px;"> 
				    <label for="volumeOfContent"   class="form-label"> <a><fmt:message key="volume.content"/></a></label>
				    <div>
					  <input id= "volumeOfContent" name="volumeOfContent" type="number" onchange="totalContentVolumeChange()">
				    </div>
				  </div>
				  <div class ="col-lg-3 px8"  style="position: relative; left: 10px;">
				    <label for="" class="form-label"><a> <fmt:message key="ctp.fees"/></a></label> 
					<input type="number" id="ctpFees" value="0">
			      </div>
		       </div>		
	
		      <div class="row py-3">
			    <div class ="col-lg-3 px3">
                  <div class="form-check">
                    <label class="form-check-label" for="existingPlate"><a><fmt:message key="existing.plate"/></a> </label>
                      <input class="form-check-input" type="checkbox" name="existingPlate" id="existingPlate">
                  </div>
			    </div>
			    <div class ="col-lg-3 px3">
			    <div class="form-check">                     
                    <label class="form-check-label" for="gridCheck1"> <a><fmt:message key="type.setting.by.us"/></a></label>
                    <input class="form-check-input" type="checkbox" name="name" id="typesettingByUs">
                  </div>
			    </div>
			  <div class ="col-lg-3 px3" style="position: relative; left: 10px;"> 
			     <div>
			      <div class="form-check">                      
                    <label class="form-check-label" for="gridCheck1"><a><fmt:message key="data.supplied.by.customer"/></a> </label>
                    <input class="form-check-input" type="checkbox" id="dataSuppliedByCustomer">
                  </div>
                  
			    </div>
			  </div>
			  <div class ="col-lg-3 px3" style="position: relative; left: 10px;">
			  <div class="form-check">
                    <label class="form-check-label" for="gridCheck1"><a><fmt:message key="layout.by.us"/></a> </label>
                    <input class="form-check-input" type="checkbox" id="layoutByUs">
                  </div>
			  </div>
		    </div>	
		     <div class="row p-3">    
			<button  style=" position:relative;width: 94px;left:90%" type="button"  class="btn btn-primary" onclick="tab1NextBtn(),summaryDraftUpdate()" id="next-btn-draft" >Next</button>	
			
		   </div>  	
          </div>             
        </div>
        
 <!-- <----------------------------------- Tab2 ------------------------------->   
 
       <div class=" container tab-pane fade"  id="tab2" role="tabpanel" aria-labelledby="profile-tab">
         <div style="position: relative;bottom: -20px;" id="mainDiv" >	
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
               	</div>
	    	 </div>	
	     	<div class ="row py-3 "style="margin-top:50px" >
		     <div class ="col-sm-6"> <button type="button" style ="width:125px;float:left" class="btn btn-primary" onclick="navigate(2,1);"><fmt:message key="previews"/></button>	
		    </div>
	        <div class ="col-sm-6">
			         <button   type="button" style="width:125px;float:right"  class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#creation" id="next-btn1" onclick="submitFormDraft()"><fmt:message key="submit"/></button>			
	       </div>
	     </div>
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

   
  
  
  
  
  
  