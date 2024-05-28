
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<link href="assets/css/profile.css" rel="stylesheet">
<link href="assets/css/job.css" rel="stylesheet">

  <main id="users-list" class="main">
    <section class="section profile" id="modal-details">
      <div class="row">
        <div class="col-xl-8" style="width: 100%">
          <div class="card">
            <div class="card-body pt-4">
              <ul class="nav nav-tabs nav-tabs-bordered" style="justify-content: center;">
                <li class="nav-item">
                  <button class="nav-link active" data-bs-toggle="tab" data-bs-target="#profile-overview" > <fmt:message key="overview"/></button>
                </li>
                <li class="nav-item">
                  <button class="nav-link"  data-bs-toggle="tab" data-bs-target="#profile-edit">Edit Machine</button>
                </li>
                
              </ul>
              <div class="tab-content pt-2">
                <div class="tab-pane fade show active profile-overview" id="profile-overview" style="margin-left: 10%">    
                         
                  <h5 class="card-title">job Details</h5>
    
      <div class="container" style="position: relative;bottom: -20px;" >
       				<h4>Job Description</h4>
       				<hr>
					  <div class="row">
					 	 <div class="row">
					      <div class="col-sm-4">
					    	The Type of Job : <span id="job-type">${job.jobType.name}</span>
					      </div>
					      <div class="col-sm-4">
					    	 Title of Job : <span id="job-title">${job.title} </span> 
					      </div>
					      <div class="col-sm-4">
					    	 Name of Customer : <span id="job-customer"> ${job.customer.name} </span> 
					      </div>
					    </div>
					   
					   	 <div class="row">
					    <div class="col-sm-4">
					    	Number of Pages for Cover : <span id="cover-pages">${job.coverVolume} </span>
					    </div>
					    <div class="col-sm-4">
					    	Number of Pages for Content :  <span id="content-pages"> ${job.contentVolume}</span> 
					    </div>
					    <div class="col-sm-4">
					    	 CTP Fees : <span id="ctp">${job.ctpFees}</span> 
					    </div>
					   </div>
					   
					     <div class="row">
					    <div class="col-sm-4">
					    	Paper Format : <span id="paper-format"></span>
					    </div>
					    <div class="col-sm-4">
					    	Open :<span id="open-l">${job.openLength}</span> | <span id="open-w">${job.openWidth}</span></div> 
					    	<div class="col-sm-4">
					    	Fold :<span id="fold-l">${job.closeLength}</span> | <span id="fold-w">${job.closeWidth}</span>
					    </div>
					    </div>
					    <div class="row">
					    <div class="col-sm-4">
					    	Existing Plate : <span id="existing-plate">${job.existingPlate}</span>
					    </div>
					    <div class="col-sm-4">
					    	Data Supply By Us : <span id="supply-data"> ${job.dataSuppliedByCustomer} </span>
					    </div>
					    <div class="col-sm-4">
					    	Lay Out by Us : <span id="data-layout">${job.layOutByUs}</span>
					    </div> 
					    <div class="col-sm-4" id="">
					    	Type Setting By Us : <span id="type-setting"> ${job.typesettingByUs}</span> 
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
					    	Paper Type : <span id="cover-paper"> ${job.jobPapers.contentType.}</span>
					    </div>
					    <div class="col-sm-4">
					    	 Paper Grammage (GSM) : <span id="cover-grammage"> 1</span> 
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
					  <tbody>
					
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
					 </div>
                   </div>
                <div class="tab-pane fade profile-edit pt-3" id="profile-edit">
                <form style="margin-left: 5%">
                  <div class="row mb-3">  </div>
                    <div class="row mb-3">
                      <label for="firstName" class="col-md-4 col-lg-3 col-form-label">Name</label>
                      <div class="col-md-6 col-lg-6">
                        <input name="Name" type="text" class="form-control" id="name" value="${findMachine.name}">
                      </div>
                    </div>
                    <div class="row mb-3">
                      <label for="abbreviation" class="col-md-4 col-lg-3 col-form-label">abbreviation</label>
                      <div class="col-md-8 col-lg-6">
                        <input name="abbreviation" type="text" class="form-control" id="abbreviation" value="${findMachine.abbreviation}">
                      </div>
                    </div>
                    <div class="row mb-3">
                      <label for="username" class="col-md-4 col-lg-3 col-form-label">PLate Length</label>
                      <div class="col-md-8 col-lg-6">
                        <input name="plateLength" type="number" class="form-control" id="plateLength" value="${findMachine.plateLength}">
                      </div>
                    </div>
                    <div class="row mb-3">
                      <label for="username" class="col-md-4 col-lg-3 col-form-label">PLate Width</label>
                      <div class="col-md-8 col-lg-6">
                       <input name="plateWidth" type="number" class="form-control" id="plateWidth" value="${findMachine.plateWidth}">
                      </div>
                    </div>
                      <div class="row mb-3"> 
                      <label for="thumbnail" class="col-md-4 col-lg-3 col-form-label">Logo</label> 
                      <div class="col-md-8 col-lg-6">
                       <input name="thumbnail" type="file" class="form-control" id="thumbnail" value="${findMachine.thumbnail}"> 
                      </div> 
                     </div> 
                    
                    <div class="text-center">
                      <input type="submit" data-bs-toggle="modal" type="button"  onsubmit="updateMachine('${findMachine.id}')" 
                      	style="left: 42%; bottom: 2%"
                       class="btn btn-primary" value="Save Changes"/>
                    </div>
                   </form><!-- End Profile Edit Form -->
                </div>				
            </div>
          </div>
        </div>
      </div>
    </section>
  </main><!-- End #main -->
  
  
  