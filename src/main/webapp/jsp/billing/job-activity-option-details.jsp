
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<link href="assets/css/profile.css" rel="stylesheet">

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
              </ul>
              <div class="tab-content pt-2">
                <div class="tab-pane fade show active profile-overview" id="profile-overview" style="margin-left: 10%">             
                  <h5 class="card-title">Activity Details</h5>
                  <div class="row">
                    <div class="col-lg-3 col-md-4 label ">Name :</div>
                    <div class="col-lg-9 col-md-8">${activity.name}</div>
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
                  </form>
                  <!-- End Profile Edit Form -->
                </div>				
            </div>
          </div>
        </div>
  
    </section>
  </main><!-- End #main -->