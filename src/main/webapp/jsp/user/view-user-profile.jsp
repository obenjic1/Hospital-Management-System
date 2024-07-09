
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<link href="assets/css/profile.css" rel="stylesheet">

<main id="users-list" class="main">
    <section class="section profile" id="modal-details">
        <div class="row">
            <div class="col-xl-8" style="width: 150%">
                <div class="card">
                    <div class="card-body pt-3">
                        <ul class="nav nav-tabs nav-tabs-bordered" style="justify-content: center;">
                            <li class="nav-item">
                                <button class="nav-link active" data-bs-toggle="tab" data-bs-target="#profile-overview" > <fmt:message key="overview"/></button>
                            </li>
                            <li class="nav-item">
                                <button class="nav-link"  data-bs-toggle="tab" data-bs-target="#profile-edit"><fmt:message key="edit.profile"/>${user.username}</button>
                            </li>
                        </ul>
                        <div class="tab-content pt-2">
                            <div class="row tab-pane fade show active profile-overview" id="profile-overview">
                                <div class="row" >
                                    <div class="col-xl-8" style="padding-left: 30px;" >
                                        <h5 class="card-title"><fmt:message key="profile.details"/></h5>
                                        <div class="row">
                                            <div class="col-lg-3 col-md-4 label "><fmt:message key="names"/></div>
                                            <div class="col-lg-9 col-md-8">${user.firstName} ${user.lastName}</div>
                                        </div>
                                        <div class="row">
                                            <div class="col-lg-3 col-md-4 label "> <fmt:message key="username"/></div>
                                            <div class="col-lg-9 col-md-8">${user.username}</div>
                                        </div>
                                        <div class="row">
                                            <div class="col-lg-3 col-md-4 label"><fmt:message key="list.groups"/></div>
                                            <div class="col-lg-9 col-md-8">${user.groupe.name}</div>
                                        </div>
                                        <div class="row">
                                            <div class="col-lg-3 col-md-4 label"><fmt:message key="address"/></div>
                                            <div class="col-lg-9 col-md-8">${user.address}</div>
                                        </div>
                                        <div class="row">
                                            <div class="col-lg-3 col-md-4 label"><fmt:message key="phone"/></div>
                                            <div class="col-lg-9 col-md-8">${user.mobile}</div>
                                        </div>
                                        <div class="row">
                                            <div class="col-lg-3 col-md-4 label"><fmt:message key="email"/></div>
                                            <div class="col-lg-9 col-md-8">${user.email}</div>
                                        </div>
                                        <div class="row">
                                            <div class="col-lg-3 col-md-4 label"><fmt:message key="created.date"/></div>
                                            <div class="col-lg-9 col-md-8">${user.createdAt}</div>
                                        </div>
                                    </div>
                                    <div class="col-xl-4">
                                        <c:if test="${not empty user.imagePath}">
                                            <img src="${pageContext.request.contextPath}/file/download?file=${user.imagePath}&dir=folder.user.images" style="height:300px">
                                        </c:if>
                                        <c:if test="${empty user.imagePath}">
                                            <img class="img-responsive" src="assets/img/default.png" style=" min-height:250px; max-height:300px;">
                                        </c:if>

                                    </div>

                                </div>

                            </div>

                            <div class="tab-pane fade profile-edit pt-3" id="profile-edit">
                                <form style="margin-left: 5%">
                                    <div class="row mb-3">  </div>
                                    <div class="row mb-3">
                                        <label for="firstName" class="col-md-4 col-lg-3 col-form-label"><fmt:message key="first.name"/></label>
                                        <div class="col-md-8 col-lg-9">
                                            <input name="firstName" type="text" class="form-control" id="firstName" value="${user.firstName}">
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <label for="lastName" class="col-md-4 col-lg-3 col-form-label"><fmt:message key="last.name"/></label>
                                        <div class="col-md-8 col-lg-9">
                                            <input name="lastName" type="text" class="form-control" id="lastName" value="${user.lastName}">
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <label for="username" class="col-md-4 col-lg-3 col-form-label"><fmt:message key="username"/></label>
                                        <div class="col-md-8 col-lg-9">
                                            <input name="username" type="text" class="form-control" id="username" value="${user.username}">
                                        </div>
                                    </div>
                                     <div class="row mb-3">
                                        <label for="mobile" class="col-md-4 col-lg-3 col-form-label"><fmt:message key="phone"/></label>
                                        <div class="col-md-8 col-lg-9">
                                            <input name="mobile" type="text" class="form-control" id="mobile" value="${user.mobile}">
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <label for="email" class="col-md-4 col-lg-3 col-form-label"><fmt:message key="email"/></label>
                                        <div class="col-md-8 col-lg-9">
                                            <input name="email" type="email" class="form-control" id="email" value="${user.email}">
                                        </div>
                                    </div>
                                    
                                     <div class="row mb-3">
                                         <label for="address" class="col-md-4 col-lg-3 col-form-label"><fmt:message key="address"/></label>
                                         <div class="col-md-8 col-lg-9">
                                             <input name="address" type="text" class="form-control" id="address" value="${user.address}">
                                         </div>
                                     </div>
                                     <div class="row mb-3">
                                         <label for="ImageFile" class="col-md-4 col-lg-3 col-form-label"><fmt:message key="address"/></label>
                                         <div class="col-md-8 col-lg-9">
                                             <input name="imageFile" type="file" class="form-control" id="imageFile" accept="image/*" value="${user.address}">
                                         </div>
                                     </div>
                                    <div class="text-center">
                                    	<input data-bs-toggle="modal" data-bs-target="#creation" type="button" onclick="updateUserById('${user.id}')" class="btn btn-primary" value="Save Changes" style="left: 42%;bottom: -18%;"/>
                                    </div>
                 
                                </form><!-- End Profile Edit Form -->
                            </div>
                <div class="tab-pane fade pt-3" id="profile-change-password">
                </div>
              </div><!-- End Bordered Tabs -->
            </div>
          </div>
        </div>
      </div>
    </section>
</main><!-- End #main -->
  
  
  