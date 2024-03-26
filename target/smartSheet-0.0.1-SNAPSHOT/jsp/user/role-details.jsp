
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


  <main id=" view-role-details">
    <section class="section profile">
        <div class="col-xl-8">
          <div class="card" style="position: relative; width: 150%">
            <div class="card-body pt-3">
              <!-- Bordered Tabs -->
              <ul class="nav nav-tabs nav-tabs-bordered" style=" justify-content: center;">
                <li class="nav-item">
                  <button class="nav-link active" data-bs-toggle="tab" data-bs-target="#profile-overview"
                   style="position: relative; ">Overview</button>
                </li>
              </ul>
              <div class="tab-content pt-2" style="margin-left: 5%">
                <div class="tab-pane fade show active profile-overview" id="profile-overview">              
                  <h5 class="card-title">Roles Details</h5>
                  <div class="row">
                    <div class="col-lg-3 col-md-4 label ">Name :</div>
                    <div class="col-lg-9 col-md-8">${roleFind.name}</div>
                  </div>
                  <div class="row">
                    <div class="col-lg-3 col-md-4 label">Descriptions :</div>
                    <div class="col-lg-9 col-md-8">${roleFind.description}</div>
                  </div>
                  <div class="row">
                    <div class="col-lg-3 col-md-4 label">Created Date :</div>
                    <div class="col-lg-9 col-md-8">${roleFind.createdAt}</div>
                  </div>
                </div>
            </div>
          </div>
          </div>
        </div>
    </section>
  </main><!-- End #main -->
  
  <script src="assets/js/users.js"></script>
  
  