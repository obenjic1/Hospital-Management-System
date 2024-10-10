
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
			 <div class="pagetitle">
			      <nav>
			        <ol class="breadcrumb">
			          <li class="breadcrumb-item"><a href="index.html">Home</a></li>
			          <li class="breadcrumb-item active">Dashboard</li>
			        </ol>
			      </nav>
			    </div>
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
	                            <section class="section dashboard">
	                            <div class="col-lg-12"> 
	                            
         							<div class="row m-4">
         							<!-- start profile pic-->
         							 <div class="col-xxl-3 col-md-6">
							              <div class="card info-card sales-card">
							                <div class="card-body">
							                  <h5 class="card-title">Profile Pic</h5>
							
							                  <div class="d-flex align-items-center">
							                    <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
							                    
							                    </div>
							               
							                  </div>
							                </div>
							
							              </div>
							            </div><!-- End profile pic-->
         							
         							
         								 <!-- JOb Card -->
							            <div class="col-xxl-3 col-md-6">
							              <div class="card info-card sales-card">
							
							                <div class="filter">
							                  <a class="icon" href="#" data-bs-toggle="dropdown"><i class="bi bi-three-dots"></i></a>
							                  <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
							                    <li class="dropdown-header text-start">
							                      <h6>Filter</h6>
							                    </li>
							
							                    <li><a class="dropdown-item" href="#">Today</a></li>
							                    <li><a class="dropdown-item" href="#">This Month</a></li>
							                    <li><a class="dropdown-item" href="#">This Year</a></li>
							                  </ul>
							                </div>
							
							                <div class="card-body">
							                  <h5 class="card-title">Jobs Done <span> | ThisYear</span></h5>
							
							                  <div class="d-flex align-items-center">
							                    <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
							                      <i class="bi bi-cart"></i>
							                    </div>
							                    <div class="ps-3">
							                      <h6>${count}</h6>
							                      <span class="text-success small pt-1 fw-bold">12%</span> <span class="text-muted small pt-2 ps-1">increase</span>
							
							                    </div>
							                  </div>
							                </div>
							
							              </div>
							            </div><!-- End Sales Card -->
							             <!-- Revenue Card -->
							            <div class="col-xxl-3 col-md-6">
							              <div class="card info-card revenue-card">
							
							                <div class="filter">
							                  <a class="icon" href="#" data-bs-toggle="dropdown"><i class="bi bi-three-dots"></i></a>
							                  <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
							                    <li class="dropdown-header text-start">
							                      <h6>Filter</h6>
							                    </li>
							
							                    <li><a class="dropdown-item" href="google.com">Today</a></li>
							                    <li><a class="dropdown-item" href="#">This Month</a></li>
							                    <li><a class="dropdown-item" href="#">This Year</a></li>
							                  </ul>
							                </div>
							
							                <div class="card-body">
							                  <h5 class="card-title">Revenue <span>| This Month</span></h5>
							
							                  <div class="d-flex align-items-center">
							                    <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
							                      <i class="bi bi-currency-dollar"></i>
							                    </div>
							                    <div class="ps-3">
							                      FCFA<h6> <fmt:formatNumber value="${Amount}" type="currency"   pattern = "#,###,###"/> </h6>
							                      <span class="text-success small pt-1 fw-bold">8%</span> <span class="text-muted small pt-2 ps-1">increase</span>
							
							                    </div>
							                  </div>
							                </div>
							
							              </div>
							            </div><!-- End Revenue Card -->
							             <!-- Customers Card -->
							            <div class="col-xxl-3 col-xl-12">
							
							              <div class="card info-card customers-card">
							
							                <div class="filter">
							                  <a class="icon" href="#" data-bs-toggle="dropdown"><i class="bi bi-three-dots"></i></a>
							                  <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
							                    <li class="dropdown-header text-start">
							                      <h6>Filter</h6>
							                    </li>
							
							                    <li><a class="dropdown-item" href="#">Today</a></li>
							                    <li><a class="dropdown-item" href="#">This Month</a></li>
							                    <li><a class="dropdown-item" href="#">This Year</a></li>
							                  </ul>
							                </div>
							
							                <div class="card-body">
							                  <h5 class="card-title">Customers <span>| This Year</span></h5>
							
							                  <div class="d-flex align-items-center">
							                    <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
							                      <i class="bi bi-people"></i>
							                    </div>
							                    <div class="ps-3">
							                      <h6>1244</h6>
							                      <span class="text-danger small pt-1 fw-bold">12%</span> <span class="text-muted small pt-2 ps-1">decrease</span>
							
							                    </div>
							                  </div>
							
							                </div>
							              </div>
							
							            </div><!-- End Customers Card -->
							            
         							</div>
         							<div class="row m-4">
         								<div class="col-3">
							              <div class="card recent-sales overflow-auto">
							                <div class="card-body">
							                  <h5 class="card-title">User Profile </h5>
								                 <div class="row">
		                                            <div class="col-lg-5 col-md-4 label "><fmt:message key="username"/> :</div>
		                                            <div class="col-lg-7 col-md-8">${user.username}</div>
	                                        	</div> 
	                                        	<div class="row">
		                                            <div class="col-lg-5 col-md-4 label "><fmt:message key="names"/> :</div>
		                                            <div class="col-lg-7 col-md-8">${user.firstName} ${user.lastName}</div>
	                                        	</div> 
	                                        	<div class="row">
		                                            <div class="col-lg-5 col-md-4 label "><fmt:message key="list.groups"/> :</div>
		                                            <div class="col-lg-7 col-md-8">${user.groupe.name}</div>
	                                        	</div> 
	                                        	<div class="row">
		                                            <div class="col-lg-5 col-md-4 label "><fmt:message key="address"/> :</div>
		                                            <div class="col-lg-7 col-md-8">${user.address}</div>
	                                        	</div> 
	                                        	<div class="row">
		                                            <div class="col-lg-5 col-md-4 label "><fmt:message key="phone"/> :</div>
		                                            <div class="col-lg-7 col-md-8">${user.mobile}</div>
	                                        	</div> 
												<div class="row">
		                                            <div class="col-lg-5 col-md-4 label "><fmt:message key="email"/> :</div>
		                                            <div class="col-lg-7 col-md-8">${user.email} </div>
	                                        	</div> 
							
							
							                </div>
							
							              </div>
							            </div><!-- End Recent Sales -->
							         	<div class="col-9">
							              <div class="card recent-sales overflow-auto">
							                <div class="card-body">
							                  <h5 class="card-title">User Recent Jobs </h5>
							                  <table class="table table-border ">
							                    <thead style="background-color: #dddfe3;">
							                      <tr>
							                        <th scope="col"><fmt:message key="number"/></th>
							                        <th scope="col"><fmt:message key="reference"/></th>
							                       <th scope="col"><fmt:message key="job.type"/></th>
							                        <th scope="col"><fmt:message key="title"/></th>
							                        <th scope="col"><fmt:message key="job.status"/></th>
							                      </tr>
							                    </thead>
							                    <tbody>
							                     <c:forEach var="job" items="${jobs}" varStatus="loop">
							                     <tr class="${loop.index % 2 == 0 ? 'even-row' : 'odd-row'}">
												    <c:set var="index" value="${loop.index}" />
												    <%    int index = (Integer) pageContext.getAttribute("index");  %>
												 <td>  <%= index + 1 %></td>
							                    
							                     <td><a>${job.jobType.name}</a></td>
							                     <td><a class="text-primary">${job.title}</a></td>
							                     <td><a>${job.referenceNumber}</a></td>
							   					 <td>
							   					  <c:if test="${job.status.name=='Registered'}"><a class=" badge bg-success">${job.status.name}</a></c:if>
							   					  <c:if test="${job.status.name=='Draft'}"><a>${job.status.name}</a></c:if>
							   					  <c:if test="${job.status.name=='Confrimed'}"><a class="badge bg-warning">${job.status.name}</a></c:if>
							   					  <c:if test="${job.status.name=='Approved'}"> <a class=" badge bg-success" >${job.status.name}</a></c:if>
							   					  <c:if test="${job.status.name=='Abort'}"><a>${job.status.name}</a></c:if>
							   					 
							   					 </td>
<!-- 							                      <tr> -->
<!-- 							                        <th scope="row"><a href="#">#2457</a></th> -->
<!-- 							                        <td>Brandon Jacob</td> -->
<!-- 							                        <td><a href="#" class="text-primary">At praesentium minu</a></td> -->
<!-- 							                        <td>$64</td> -->
<!-- 							                        <td><span class="badge bg-success">Approved</span></td> -->
<!-- 							                      </tr> -->
<!-- 							                      <tr> -->
<!-- 							                        <th scope="row"><a href="#">#2147</a></th> -->
<!-- 							                        <td>Bridie Kessler</td> -->
<!-- 							                        <td><a href="#" class="text-primary">Blanditiis dolor omnis similique</a></td> -->
<!-- 							                        <td>$47</td> -->
<!-- 							                        <td><span class="badge bg-warning">Pending</span></td> -->
<!-- 							                      </tr> -->
<!-- 							                      <tr> -->
<!-- 							                        <th scope="row"><a href="#">#2049</a></th> -->
<!-- 							                        <td>Ashleigh Langosh</td> -->
<!-- 							                        <td><a href="#" class="text-primary">At recusandae consectetur</a></td> -->
<!-- 							                        <td>$147</td> -->
<!-- 							                        <td><span class="badge bg-success">Approved</span></td> -->
<!-- 							                      </tr> -->
<!-- 							                      <tr> -->
<!-- 							                        <th scope="row"><a href="#">#2644</a></th> -->
<!-- 							                        <td>Angus Grady</td> -->
<!-- 							                        <td><a href="#" class="text-primar">Ut voluptatem id earum et</a></td> -->
<!-- 							                        <td>$67</td> -->
<!-- 							                        <td><span class="badge bg-danger">Rejected</span></td> -->
<!-- 							                      </tr> -->
<!-- 							                      <tr> -->
<!-- 							                        <th scope="row"><a href="#">#2644</a></th> -->
<!-- 							                        <td>Raheem Lehner</td> -->
<!-- 							                        <td><a href="#" class="text-primary">Sunt similique distinctio</a></td> -->
<!-- 							                        <td>$165</td> -->
<!-- 							                        <td><span class="badge bg-success">Approved</span></td> -->
<!-- 							                      </tr> -->
 												</c:forEach>
							                    </tbody>
							                  </table>
							
							                </div>
							
							              </div>
							            </div><!-- End Recent Sales -->
							           
	         					</div>
	                            </div>
	                            </section>
	                            
                            
                            
                            
                            
                            
<!--                                 <div class="row" > -->
<!--                                     <div class="col-xl-8" style="padding-left: 30px;" > -->
<%--                                         <h5 class="card-title"><fmt:message key="profile.details"/></h5> --%>
<!--                                         <div class="row"> -->
<%--                                             <div class="col-lg-3 col-md-4 label "><fmt:message key="username"/>123</div> --%>
<%--                                             <div class="col-lg-9 col-md-8">${user.username}</div> --%>
<!--                                         </div>  -->
<!--                                         <div class="row"> -->
<%--                                             <div class="col-lg-3 col-md-4 label "><fmt:message key="names"/>123</div> --%>
<%--                                             <div class="col-lg-9 col-md-8">${user.firstName} ${user.lastName}</div> --%>
<!--                                         </div>  -->
<!--                                         <div class="row"> -->
<%--                                             <div class="col-lg-3 col-md-4 label"><fmt:message key="list.groups"/></div> --%>
<%--                                             <div class="col-lg-9 col-md-8">${user.groupe.name}</div> --%>
<!--                                         </div> -->
<!--                                         <div class="row"> -->
<%--                                             <div class="col-lg-3 col-md-4 label"><fmt:message key="address"/></div> --%>
<%--                                             <div class="col-lg-9 col-md-8">${user.address}</div> --%>
<!--                                         </div> -->
<!--                                         <div class="row"> -->
<%--                                             <div class="col-lg-3 col-md-4 label"><fmt:message key="phone"/></div> --%>
<%--                                             <div class="col-lg-9 col-md-8">${user.mobile}</div> --%>
<!--                                         </div> -->
<!--                                         <div class="row"> -->
<%--                                             <div class="col-lg-3 col-md-4 label"><fmt:message key="email"/></div> --%>
<%--                                             <div class="col-lg-9 col-md-8">${user.email}</div> --%>
<!--                                         </div> -->
<!--                                         <div class="row"> -->
<%--                                             <div class="col-lg-3 col-md-4 label"><fmt:message key="created.date"/></div> --%>
<%--                                             <div class="col-lg-9 col-md-8"> <fmt:formatDate type = "both" value = "${user.createdAt}" /></div> --%>
<!--                                         </div> -->
<!--                                     </div> -->
<!--                                     <div class="col-xl-4"> -->
<%--                                         <c:if test="${not empty user.imagePath}"> --%>
<%--                                             <img src="${pageContext.request.contextPath}/file/download?file=${user.imagePath}&dir=folder.user.images" style="height:300px"> --%>
<%--                                         </c:if> --%>
<%--                                         <c:if test="${empty user.imagePath}"> --%>
<!--                                             <img class="img-responsive" src="assets/img/default.png" style=" min-height:250px; max-height:300px;"> -->
<%--                                         </c:if> --%>

<!--                                     </div> -->

<!--                                 </div> -->

                            </div>

                            <div class="tab-pane fade profile-edit pt-3" id="profile-edit">
                                <form style="margin-left: 5%">
                                    <div class="row mb-3">  </div>
                                    <div class="row mb-3">
                                        <label for="firstName" class="col-sm-4 col-lg-3 col-form-label"><fmt:message key="first.name"/></label>
                                        <div class="col-sm-8 col-lg-8">
                                            <input name="firstName" type="text" class="form-control" id="firstName" value="${user.firstName}">
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <label for="lastName" class="col-sm-4 col-lg-3 col-form-label"><fmt:message key="last.name"/></label>
                                        <div class="col-sm-8 col-lg-8">
                                            <input name="lastName" type="text" class="form-control" id="lastName" value="${user.lastName}">
                                        </div>
                                    </div>
                                     <div class="row mb-3">
                                        <label for="mobile" class="col-sm-4 col-lg-3 col-form-label"><fmt:message key="phone"/></label>
                                        <div class="col-sm-8 col-lg-8">
                                            <input name="mobile" type="text" class="form-control" id="mobile" value="${user.mobile}">
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <label for="email" class="col-sm-4 col-lg-3 col-form-label"><fmt:message key="email"/></label>
                                        <div class="col-sm-8 col-lg-8">
                                            <input name="email" type="email" class="form-control" id="email" value="${user.email}">
                                        </div>
                                    </div>
                                    
                                     <div class="row mb-3">
                                         <label for="address" class="col-sm-4 col-lg-3 col-form-label"><fmt:message key="address"/></label>
                                         <div class="col-sm-8 col-lg-8">
                                             <input name="address" type="text" class="form-control" id="address" value="${user.address}">
                                         </div>
                                     </div>
                                     <div class="row mb-3">
                                         <label for="ImageFile" class="col-sm-4 col-lg-3 col-form-label"><fmt:message key="photo"/></label>
                                         <div class="col-sm-8 col-lg-8">
                                             <input name="imageFile" type="file" class="form-control" id="imageFile" accept="image/*" >
                                         </div>
                                     </div>
                                    <div class="text-center">
                                    	<input type="button" onclick="updateUserById('${user.id}')" class="btn btn-primary" data-bs-dismiss="modal" value="Save Changes" style="left: 42%;bottom: -18%;"/>
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
  
  
  