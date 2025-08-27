
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
                                <button class="nav-link"  data-bs-toggle="tab" data-bs-target="#profile-change-password">Change Password</button>
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
         							 	<div class="card">
								            <div class="card-body profile-card pt-2 pb-2 d-flex flex-column align-items-center">
								              <c:if test="${not empty user.imagePath}">
							                      <img src="${pageContext.request.contextPath}/file/download?file=${user.imagePath}&dir=folder.user.images" class="rounded-circle">
							                  </c:if>
							                  <c:if test="${empty user.imagePath}">
							                    <img src="assets/img/default.png" class="rounded-circle">
							                  </c:if>
							                  	 <h4>${user.staff.firstName} ${user.staff.lastName}</h4>
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
							
							                    <li><a class="dropdown-item" onclick="pharmacy(1)">Today</a></li>
							                    <li><a class="dropdown-item" onclick="pharmacy(2)">This Month</a></li>
							                    <li><a class="dropdown-item" onclick="pharmacy(3)">This Year</a></li>
							                  </ul>
							                </div>
							
							                <div class="card-body">
							                  <h5 class="card-title">Phamarcy Sales <span> | Today</span></h5>
							
							                  <div class="d-flex align-items-center">
							                    <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
							                      <i class="bi bi-cart"></i>
							                    </div>
							                    <div class="ps-3">
							                      <h6 id="phamarcy-sales" >${count}</h6>
							                      <span class="text-success small pt-1 fw-bold"  id="phamarcy-change">12%</span> <span class="text-muted small pt-2 ps-1">from total</span>
							
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
							
							                    <li><a class="dropdown-item"  onclick="revenue(1)">Today</a></li>
							                    <li><a class="dropdown-item"  onclick="revenue(2)" >This Month</a></li>
							                    <li><a class="dropdown-item" onclick="revenue(3)" >This Year</a></li>
							                  </ul>
							                </div>
							
							                <div class="card-body">
							                  <h5 class="card-title">Revenue <span>| Today</span></h5>
							
							                  <div class="d-flex align-items-center">
							                    <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
							                      <i class="bi bi-currency-dollar"></i>
							                    </div>
							                    <div class="ps-3">
							                      FCFA<h6 id="revenue-sales">25 <fmt:formatNumber    value="${amount }" type="currency"   pattern = "#,###,###"/> </h6>
							                      <span class="text-success small pt-1 fw-bold" id="revenue-change">8%</span> <span class="text-muted small pt-2 ps-1">increase</span>
							
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
							                  <h5 class="card-title">Staffs <span>| Today</span></h5>
							
							                  <div class="d-flex align-items-center">
							                    <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
							                      <i class="bi bi-people"></i>
							                    </div>
							                    <div class="ps-3">
							                      <h6>10</h6>
							                      <span class="text-danger small pt-1 fw-bold">2</span> <span class="text-muted small pt-2 ps-1">new Customer</span>
							
							                    </div>
							                  </div>
							
							                </div>
							              </div>
							
							            </div><!-- End Customers Card -->
							            
         							</div>
         							<div class="row">
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
		                                            <div class="col-lg-7 col-md-8">${user.staff.firstName} ${user.staff.lastName}</div>
	                                        	</div> 
	                                        	<div class="row">
		                                            <div class="col-lg-5 col-md-4 label "><fmt:message key="list.groups"/> :</div>
		                                            <div class="col-lg-7 col-md-8">${user.groupe.name}</div>
	                                        	</div> 
	                                        	<div class="row">
		                                            <div class="col-lg-5 col-md-4 label "><fmt:message key="address"/> :</div>
		                                            <div class="col-lg-7 col-md-8">${user.staff.address}</div>
	                                        	</div> 
	                                        	<div class="row">
		                                            <div class="col-lg-5 col-md-4 label "><fmt:message key="phone"/> :</div>
		                                            <div class="col-lg-7 col-md-8">${user.staff.phone}</div>
	                                        	</div> 
												<div class="row">
		                                            <div class="col-lg-5 col-md-4 label "><fmt:message key="email"/> :</div>
		                                            <div class="col-lg-7 col-md-8">${user.staff.department.name} </div>
	                                        	</div> 
							                </div>
							              </div>
							            </div><!-- End Recent Sales -->
							         	<div class="col-9">
							              <div class="card recent-sales overflow-auto">
							                <div class="card-body">
							                  <h5 class="card-title">User 5  Recent Sales  </h5>
							      			 <div class="mt-1">
										        <!-- Filter Form -->
										        <form action="dashboard" method="get" class="row g-3 my-3">
										            <div class="col-md-2">
										                <label class="form-label">From Date</label>
										                <input type="date" class="form-control" name="startDate" value="${param.startDate}">
										            </div>
										            <div class="col-md-2">
										                <label class="form-label">To Date</label>
										                <input type="date" class="form-control" name="endDate" value="${param.endDate}">
										            </div>
										            <div class="col-md-1">
										                <label class="form-label">Pharmacist</label>
										                <select class="form-select" name="pharmacistId">
										                    <option value="">All</option>
										                    <c:forEach var="user" items="${pharmacists}">
										                        <option value="${user.id}" ${param.pharmacistId == user.id ? 'selected' : ''}>
										                            ${user.name}
										                        </option>
										                    </c:forEach>
										                </select>
										            </div>
										            <div class="col-md-1 d-flex align-items-end">
										                <button type="submit" class="btn btn-success w-100">Filter</button>
										            </div>
										        </form>
										
										        <!-- Table -->
										        <table class="table table-hover table-bordered">
										            <thead class="table-dark">
										            <tr>
										                <th>Receipt #</th>
										                <th>Customer</th>
										                <th>Pharmacist</th>
										                <th>Payment</th>
										                <th>Total (CFA)</th>
										                <th>Date</th>
										            </tr>
										            </thead>
										            <tbody>
										            <c:forEach var="sale" items="${salesHistory}">
										                <tr>
										                    <td>${sale.receiptNumber}</td>
										                    <td>${sale.customerName}</td>
										                    <td>${sale.pharmacist.name}</td>
										                    <td>${sale.paymentMethod}</td>
										                    <td class="text-success fw-bold">${sale.total}</td>
										                    <td>${sale.saleDate}</td>
										                </tr>
										            </c:forEach>
										            </tbody>
										        </table>
										    </div>
																	
							                </div>
							
							              </div>
							            </div><!-- End Recent Sales -->
							           
	         					</div>
	                            </div>
	                            </section>
                            </div>
              <div class="tab-pane fade profile-edit pt-3" id="profile-edit">
       <section>
			<div class="card" style="width: 102%; left: -1%;">
				<div class="card-body">
				  <h5 class="card-title text-center pb-0 fs-4 update-user-title" style="color: #012970;"><fmt:message key="update.user"/> : ${user.username}</h5>
				  <p style="font-family: bold;" class="text-center small"><fmt:message key="enter.the.user.informations"/></p>
					<form style="width: 55%;margin-left:30px">
                      <div class="row mb-3">
                          <label for="firstName" class="col-md-4 col-lg-3 col-form-label"><fmt:message key="first.name"/></label>
                          <div class="col-md-8 col-lg-9">
                              <input style="font-family: bold;" name="firstName" type="text" class="form-control" id="firstName" value="${user.staff.firstName}">
                          </div>
                      </div>
                      <div class="row mb-3">
                          <label style="font-family: bold;" for="lastName" class="col-md-4 col-lg-3 col-form-label"><fmt:message key="last.name"/></label>
                          <div class="col-md-8 col-lg-9">
                              <input name="lastName" type="text" class="form-control" id="lastName" value="${user.staff.lastName}">
                          </div>
                      </div>
                       	 <div class="row mb-3">
                          <label style="font-family: bold;" for="email" class="col-md-4 col-lg-3 col-form-label"><fmt:message key="email"/></label>
                          <div class="col-md-8 col-lg-9">
                          <input name="email" type="email" class="form-control" id="email" value="${user.staff.email}">
                      	</div>
                      </div>
                       <div class="row mb-3">
                          <label style="font-family: bold;" for="mobile" class="col-md-4 col-lg-3 col-form-label"><fmt:message key="phone"/></label>
                          <div class="col-md-8 col-lg-9">
                              <input name="mobile" type="text" class="form-control" id="mobile" value="${user.staff.phone}">
                          </div>
                      </div>

                      
                       <div class="row mb-3">
                           <label style="font-family: bold;" for="address" class="col-md-4 col-lg-3 col-form-label"><fmt:message key="address"/></label>
                           <div class="col-md-8 col-lg-9">
                               <input name="address" type="text" class="form-control" id="address" value="${user.staff.address}">
                           </div>
                       </div>
<!--                        <div class="row mb-3"> -->
<%-- 						  <label style="font-family: bold;" for="address" class="col-md-4 col-lg-3 col-form-label"><fmt:message key="list.departement"/></label> --%>
<!-- 					 <div class="col-md-8 col-lg-9"> -->
<%--                                <input name="address" type="text" class="form-control" id="address" value="${user.staff.department.name}" readonly="readonly"> --%>
<!--                      </div> -->
<!-- 						</div> -->
                       <div class="row mb-3">
                           <label style="font-family: bold;" for="ImageFile" class="col-md-4 col-lg-3 col-form-label"><fmt:message key="photo"/></label>
                           <div class="col-md-8 col-lg-9">
                               <input name="imageFile" type="file" class="form-control" id="imageFile" accept="image/*">
                           </div>
                       </div> 
                      <div class="text-center">
                      	<input type="button" onclick="updateStaff('${user.staff.id}'); loadPage('user/list-users')" class="btn btn-primary"  data-bs-dismiss="modal" value="Save Changes" style="left: 96%;bottom: -18%;"/>
                      </div>
   
                  </form>
				</div>
			</div>
		</section>
               </div>
                <div class="tab-pane fade  profile-change-password pt-3" id="profile-change-password">
                 <section>
                 <div class="row">
                 	<div class="col-md-6 mt-2">
							<label for="username" class="form-label"><fmt:message key="username"/></label>
							<div class="input-group has-validation">
							  <span class="input-group-text"><i class="fas fa-phone"></i></span>
							  <input type="text" id="pusername" name="username" class="form-control"  value="${user.username}" required="required" />
						    </div>
						</div>
						<div class="col-md-6 mt-2 d-none">
							<label for="username" class="form-label"><fmt:message key="username"/></label>
							<div class="input-group has-validation">
							  <span class="input-group-text"><i class="fas fa-phone"></i></span>
							  <input type="text" id="rid" name="username" class="form-control"  value="${user.id}" required="required" />
						    </div>
						</div>
						<div class="col-md-6 mt-3">
						  <label for="Password" class="form-label">Current Password</label>
						  <div class="input-group has-validation">
							<span class="input-group-text"><i class="fas fa-lock"></i></span>
							<input type="password" id="rpassword" name="rpassword" class="form-control" required="required" />
						  </div>
						</div>
						<div class="col-md-6 mt-2">
						  <label for="Password" class="form-label">New Password</label>
						  <div class="input-group has-validation">
							<span class="input-group-text"><i class="fas fa-lock"></i></span>
							<input type="password" id="newpassword" name="newpassword" class="form-control" required="required" />
						  </div>
						</div>
						<div class="col-md-6 mt-2">
						  <label for="ConfirmPassword" class="form-label"><fmt:message key="confirm.password"/></label>
						  <div class="input-group has-validation">
							<span class="input-group-text"><i class="fas fa-lock"></i></span>
							<input type="password" id="rconfirmPassword" name="confirmPassword" class="form-control" required="required" />
						  </div>
						</div>
						<div class="col-md-6 mt-2">
						  <label for="imageFile" class="form-label"><fmt:message key="photo"/></label>
						  <div class="input-group has-validation" style="width: 100%">
						    <input type="file" id="rimageFile" name="imageFile" class="form-control" accept="image/*">
						  </div>
						</div>
                 	
	                 	<div class="col-md-6"  style="position: relative;bottom: -37px; text-align:right">
							<input type="button" id="createBtn"  onclick="resetPassword()" style=" bottom: -42%; width:36%" class="btn btn-success " value="Save" >
						</div>
                 
                 </section>
                </div>
              </div><!-- End Bordered Tabs -->
            </div>
          </div>
        </div>
      </div>
    </section>
	<script src="assets/js/users.js"></script>
   <script src="assets/js/statistics/revenue.js"></script> 
    
    
</main><!-- End #main -->
  
  
  