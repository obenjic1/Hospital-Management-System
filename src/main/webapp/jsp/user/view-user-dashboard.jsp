<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link href="assets/css/profile.css" rel="stylesheet">

<main id="users-list" class="container-fluid py-4">
  <div class="row justify-content-center">
    <div class="col-12">

      <!-- =====  BREADCRUMB  ===== -->
      <nav aria-label="breadcrumb" class="mb-3">
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a href="index.html">Home</a></li>
          <li class="breadcrumb-item active">Dashboard</li>
        </ol>
      </nav>

      <!-- =====  CARD  ===== -->
      <div class="card shadow-sm border-0">
        <div class="card-header bg-transparent">
          <!-- custom tabs -->
          <ul class="nav nav-pills nav-justified flex-column flex-md-row" id="profileTab" role="tablist">
            <li class="nav-item" role="presentation">
              <button class="nav-link active" data-bs-toggle="pill" data-bs-target="#profile-overview" type="button" role="tab">
                <i class="bi bi-person me-2"></i><fmt:message key="overview"/>
              </button>
            </li>
            <li class="nav-item" role="presentation">
              <button class="nav-link" data-bs-toggle="pill" data-bs-target="#profile-change-password" type="button" role="tab">
                <i class="bi bi-lock me-2"></i>Change Password
              </button>
            </li>
            <li class="nav-item" role="presentation">
              <button class="nav-link" data-bs-toggle="pill" data-bs-target="#profile-edit" type="button" role="tab">
                <i class="bi bi-pencil-square me-2"></i><fmt:message key="edit.profile"/> ${user.username}
              </button>
            </li>
          </ul>
        </div>

        <div class="card-body">
          <div class="tab-content" id="profileTabContent">

            <!-- =========================================================
                 1.  OVERVIEW
            ========================================================== -->
            <div class="tab-pane fade show active" id="profile-overview" role="tabpanel">
              <!-- stat cards -->
              <div class="row g-3 mb-4">
                <!-- Profile pic -->
                <div class="col-md-6 col-lg-3">
                  <div class="card h-100 text-center pt-3">
                    <img src="${not empty user.imagePath ? pageContext.request.contextPath.concat('/file/download?file=').concat(user.imagePath).concat('&dir=folder.user.images') : 'assets/img/default.png'}"
                         alt="Avatar" class="rounded-circle mx-auto mb-2" width="90" height="90">
                    <h6 class="mb-0">${user.staff.firstName} ${user.staff.lastName}</h6>
                    <small class="text-muted">${user.groupe.name}</small>
                  </div>
                </div>

                <!-- Pharmacy Sales -->
                <div class="col-md-6 col-lg-3">
                  <div class="card info-card sales-card h-100">
                    <div class="card-body">
                      <div class="d-flex align-items-center">
                        <div class="card-icon rounded-circle d-flex align-items-center justify-content-center me-3">
                          <i class="bi bi-cart text-primary fs-5"></i>
                        </div>
                        <div>
                          <h6 class="mb-0">Pharmacy Sales</h6>
                          <span class="text-muted small">Today</span>
                          <div class="fs-5 fw-bold" id="phamarcy-sales">${count}</div>
                          <span class="text-success small">+12 %</span>
                        </div>
                      </div>
                    </div>
                    <div class="card-footer bg-transparent p-2">
                      <div class="dropdown">
                        <a class="small text-muted dropdown-toggle" href="#" data-bs-toggle="dropdown">Filter</a>
                        <ul class="dropdown-menu dropdown-menu-end">
                          <li><a class="dropdown-item" onclick="pharmacy(1)" href="#">Today</a></li>
                          <li><a class="dropdown-item" onclick="pharmacy(2)" href="#">This Month</a></li>
                          <li><a class="dropdown-item" onclick="pharmacy(3)" href="#">This Year</a></li>
                        </ul>
                      </div>
                    </div>
                  </div>
                </div>

                <!-- Revenue -->
                <div class="col-md-6 col-lg-3">
                  <div class="card info-card revenue-card h-100">
                    <div class="card-body">
                      <div class="d-flex align-items-center">
                        <div class="card-icon rounded-circle d-flex align-items-center justify-content-center me-3">
                          <i class="bi bi-currency-dollar text-success fs-5"></i>
                        </div>
                        <div>
                          <h6 class="mb-0">Revenue</h6>
                          <span class="text-muted small">Today</span>
                          <div class="fs-5 fw-bold">FCFA <fmt:formatNumber value="${amount}" type="number" pattern="#,###,###"/></div>
                          <span class="text-success small">+8 %</span>
                        </div>
                      </div>
                    </div>
                    <div class="card-footer bg-transparent p-2">
                      <div class="dropdown">
                        <a class="small text-muted dropdown-toggle" href="#" data-bs-toggle="dropdown">Filter</a>
                        <ul class="dropdown-menu dropdown-menu-end">
                          <li><a class="dropdown-item" onclick="revenue(1)" href="#">Today</a></li>
                          <li><a class="dropdown-item" onclick="revenue(2)" href="#">This Month</a></li>
                          <li><a class="dropdown-item" onclick="revenue(3)" href="#">This Year</a></li>
                        </ul>
                      </div>
                    </div>
                  </div>
                </div>

                <!-- Staff -->
                <div class="col-md-6 col-lg-3">
                  <div class="card info-card customers-card h-100">
                    <div class="card-body">
                      <div class="d-flex align-items-center">
                        <div class="card-icon rounded-circle d-flex align-items-center justify-content-center me-3">
                          <i class="bi bi-people text-info fs-5"></i>
                        </div>
                        <div>
                          <h6 class="mb-0">Patients</h6>
                          <span class="text-muted small">Today</span>
                          <div class="fs-5 fw-bold">10</div>
                          <span class="text-danger small">+2 new</span>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div><!-- /stat cards -->

              <!-- Profile details + Recent Sales -->
              <div class="row g-4">
                <!-- Left: profile fields -->
                <div class="col-lg-4">
                  <div class="card h-100">
                    <div class="card-header bg-transparent"><fmt:message key="overview"/></div>
                    <div class="card-body">
                      <div class="row mb-2">
                        <div class="col-5 fw-semibold"><fmt:message key="username"/> :</div>
                        <div class="col-7">${user.username}</div>
                      </div>
                      <div class="row mb-2">
                        <div class="col-5 fw-semibold"><fmt:message key="names"/> :</div>
                        <div class="col-7">${user.staff.firstName} ${user.staff.lastName}</div>
                      </div>
                      <div class="row mb-2">
                        <div class="col-5 fw-semibold"><fmt:message key="list.groups"/> :</div>
                        <div class="col-7">${user.groupe.name}</div>
                      </div>
                      <div class="row mb-2">
                        <div class="col-5 fw-semibold"><fmt:message key="address"/> :</div>
                        <div class="col-7">${user.staff.address}</div>
                      </div>
                      <div class="row mb-2">
                        <div class="col-5 fw-semibold"><fmt:message key="phone"/> :</div>
                        <div class="col-7">${user.staff.phone}</div>
                      </div>
                      <div class="row">
                        <div class="col-5 fw-semibold"><fmt:message key="email"/> :</div>
                        <div class="col-7">${user.staff.department.name}</div>
                      </div>
                    </div>
                  </div>
                </div>

                <!-- Right: recent sales table -->
                <div class="col-lg-8">
                  <div class="card h-100">
                    <div class="card-header bg-transparent d-flex justify-content-between align-items-center">
                      <span>Recent Sales</span>
                      <form action="dashboard" method="get" class="row g-2 align-items-center">
                        <div class="col-auto"><input type="date" class="form-control form-control-sm" name="startDate" value="${param.startDate}"></div>
                        <div class="col-auto"><input type="date" class="form-control form-control-sm" name="endDate" value="${param.endDate}"></div>
                        <div class="col-auto">
                          <select class="form-select form-select-sm" name="pharmacistId">
                            <option value="">All Pharmacists</option>
                            <c:forEach var="user" items="${pharmacists}">
                              <option value="${user.id}" ${param.pharmacistId == user.id ? 'selected' : ''}>${user.name}</option>
                            </c:forEach>
                          </select>
                        </div>
                        <div class="col-auto"><button type="submit" class="btn btn-sm btn-success">Filter</button></div>
                      </form>
                    </div>
                    <div class="card-body p-0">
                      <div class="table-responsive">
                        <table class="table table-hover table-striped mb-0">
                          <thead class="table-light">
                            <tr>
                              <th>Receipt #</th>
                              <th>Customer</th>
                              <th>Pharmacist</th>
                              <th>Payment</th>
                              <th class="text-end">Total (CFA)</th>
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
                                <td class="text-success fw-bold text-end">${sale.total}</td>
                                <td>${sale.saleDate}</td>
                              </tr>
                            </c:forEach>
                          </tbody>
                        </table>
                      </div>
                    </div>
                  </div>
                </div>
              </div><!-- /row -->
            </div><!-- /overview -->

            <!-- =========================================================
                 2.  EDIT PROFILE
            ========================================================== -->
            <div class="tab-pane fade" id="profile-edit" role="tabpanel">
              <div class="card border-0">
                <div class="card-header bg-transparent"><h5 class="mb-0 text-primary"><fmt:message key="update.user"/> : ${user.username}</h5></div>
                <div class="card-body">
                  <form class="row g-3 needs-validation" novalidate>
                    <div class="col-md-6">
                      <label class="form-label"><fmt:message key="first.name"/></label>
                      <input type="text" class="form-control" name="firstName" value="${user.staff.firstName}" required>
                    </div>
                    <div class="col-md-6">
                      <label class="form-label"><fmt:message key="last.name"/></label>
                      <input type="text" class="form-control" name="lastName" value="${user.staff.lastName}" required>
                    </div>
                    <div class="col-md-6">
                      <label class="form-label"><fmt:message key="email"/></label>
                      <input type="email" class="form-control" name="email" value="${user.staff.email}" required>
                    </div>
                    <div class="col-md-6">
                      <label class="form-label"><fmt:message key="phone"/></label>
                      <input type="text" class="form-control" name="mobile" value="${user.staff.phone}" required>
                    </div>
                    <div class="col-12">
                      <label class="form-label"><fmt:message key="address"/></label>
                      <input type="text" class="form-control" name="address" value="${user.staff.address}" required>
                    </div>
                    <div class="col-12">
                      <label class="form-label"><fmt:message key="photo"/></label>
                      <input type="file" class="form-control" name="imageFile" accept="image/*">
                    </div>
                    <div class="col-12 text-end">
                      <button type="button" class="btn btn-primary" onclick="updateStaff('${user.staff.id}'); loadPage('user/list-users')">
                        <fmt:message key="save"/>
                      </button>
                    </div>
                  </form>
                </div>
              </div>
            </div><!-- /edit -->

            <!-- =========================================================
                 3.  CHANGE PASSWORD
            ========================================================== -->
            <div class="tab-pane fade" id="profile-change-password" role="tabpanel">
              <div class="card border-0">
                <div class="card-header bg-transparent"><h5 class="mb-0 text-primary">Change Password</h5></div>
                <div class="card-body">
                  <form class="row g-3 needs-validation" novalidate>
                    <div class="col-md-6">
                      <label class="form-label"><fmt:message key="username"/></label>
                      <div class="input-group">
                        <span class="input-group-text"><i class="bi bi-person"></i></span>
                        <input type="text" id="pusername" class="form-control" value="${user.username}" readonly>
                      </div>
                    </div>
                    <div class="col-md-6 d-none">
                      <input type="text" id="rid" class="form-control" value="${user.id}">
                    </div>
                    <div class="col-md-6">
                      <label class="form-label">Current Password</label>
                      <div class="input-group">
                        <span class="input-group-text"><i class="bi bi-lock"></i></span>
                        <input type="password" id="rpassword" class="form-control" required>
                      </div>
                    </div>
                    <div class="col-md-6">
                      <label class="form-label">New Password</label>
                      <div class="input-group">
                        <span class="input-group-text"><i class="bi bi-lock"></i></span>
                        <input type="password" id="newpassword" class="form-control" required>
                      </div>
                    </div>
                    <div class="col-md-6">
                      <label class="form-label"><fmt:message key="confirm.password"/></label>
                      <div class="input-group">
                        <span class="input-group-text"><i class="bi bi-lock"></i></span>
                        <input type="password" id="rconfirmPassword" class="form-control" required>
                      </div>
                    </div>
                    <div class="col-md-6">
                      <label class="form-label"><fmt:message key="photo"/></label>
                      <input type="file" id="rimageFile" class="form-control" accept="image/*">
                    </div>
                    <div class="col-12 text-end">
                      <button type="button" class="btn btn-success" onclick="resetPassword()">
                        <fmt:message key="save"/>
                      </button>
                    </div>
                  </form>
                </div>
              </div>
            </div><!-- /change-password -->

          </div><!-- /tab-content -->
        </div><!-- /card-body -->
      </div><!-- /card -->
    </div><!-- /col -->
  </div><!-- /row -->
</main>

<!-- =====  SCRIPTS  ===== -->
<script src="assets/js/users.js"></script>
<script src="assets/js/statistics/revenue.js"></script>