
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<link href="assets/css/profile.css" rel="stylesheet">

  <main id="job-details" class="main">
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
                  		<h5 class="card-title">Machine Details</h5>
                  		<div class="row">
                   			<div class="col-lg-3 col-md-4 label ">Name :</div>
                    		<div class="col-lg-9 col-md-8">${findMachine.name}</div>
                 		 </div>
                   		<div class="row">
                   			 <div class="col-lg-3 col-md-4 label "> Abbreviation :</div>
                    		 <div class="col-lg-9 col-md-8">${findMachine.abbreviation}</div>
                  		</div>
                 		<div class="row">
                    		<div class="col-lg-3 col-md-4 label">Plate Length :</div>
                    		<div class="col-lg-9 col-md-8">${findMachine.plateLength}</div>
                  		</div>
                 		<div class="row">
                    		 <div class="col-lg-3 col-md-4 label">Plate Width :</div>
                   			 <div class="col-lg-9 col-md-8">${findMachine.plateWidth}</div>
                		</div>
                  		<div class="row">
                  			  <div class="col-lg-3 col-md-4 label">Creation date :</div>
                   			  <div class="col-lg-9 col-md-8">${findMachine.creationDate}</div>
                		</div>
                  		<div class="row">
                    		<div class="col-lg-3 col-md-4 label">Status</div>
                    		<div class="col-lg-9 col-md-8 ${machine.active ? 'Active' : 'Blocked' }">
                    			<a class="${machine.active ? 'Blocked' : 'Active' }">${machine.active ? 'Blocked' : 'Active'}</a>
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
						 <input type="button" data-bs-toggle="modal" data-bs-target="#creation" id="submitButton" style="left: 42%; bottom: 2%" value="Save Changes" class="btn btn-primary w-30" onclick="updateMachine('${findMachine.id}')" > 
                    </div>

                  <!--------------Machine updated successfully modal ------------->
	              <div class="modal fade" id="userUdatedSuccessfully" tabindex="-1">
		            <div class="modal-dialog modal-dialog-centered">
		              <div class="modal-content">
		                <button onclick="loadPage('/user/list-users')" type="button" class="btn-close"  data-bs-dismiss="modal" aria-label="Close"></button>
		                <div class="modal-body">
		                  <img src="assets/img/success_icon.png" alt="">
		                  <p><fmt:message key="user.updated.successfully"/></p>
		                </div>
		              </div>
		            </div>
		          </div>
		          <div class="modal fade" id="userNotDeleted" tabindex="-1">
		            <div class="modal-dialog modal-dialog-centered">
		              <div class="alert alert-danger alert-dismissible fade show" role="alert">
				        <i class="bi bi-exclamation-octagon me-1"></i>
				        <p> <fmt:message key="something.when.wrong"/></p>
				        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
			          </div>
		            </div>
		          </div>
                  </form><!-- End Profile Edit Form -->
                </div>				
            </div>
          </div>
        </div>
      </div>
    </section>
  </main><!-- End #main -->
  
  
  