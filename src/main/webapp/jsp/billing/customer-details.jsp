
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<main id=" view-customer-details">
    <section class="section profile">
        <div class="col-xl-8">
            <div class="card" style="position: relative; width: 150%">
                <div class="card-body pt-3">
                <!-- Bordered Tabs -->
                    <ul class="nav nav-tabs nav-tabs-bordered" style=" justify-content: center;">
                        <li class="nav-item">
                            <button class="nav-link active" data-bs-toggle="tab" data-bs-target="#profile-overview"
                                style="position: relative; "><fmt:message key="overview"/>
                            </button>
                        </li>
                        <li class="nav-item">
                            <button class="nav-link" data-bs-toggle="tab" data-bs-target="#customer-jobs"><fmt:message key="jobs"/></button>
                        </li>
                    </ul>
                    <div class="tab-content pt-2" style="margin-left: 5%">
                        <div  class="tab-pane fade show active profile-overview" id="profile-overview">
                            <div class="row">
                                <div class="col-xl-8">
                                    <h5 class="card-title">Customer Details</h5>
                                    <div class="row">
                                        <div class="col-lg-3 col-md-4 label "><fmt:message key="name"/></div>
                                        <div class="col-lg-9 col-md-8">${customer.name}</div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-3 col-md-4 label"><fmt:message key="email"/></div>
                                        <div class="col-lg-9 col-md-8">${customer.email}</div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-3 col-md-4 label"><fmt:message key="address"/></div>
                                        <div class="col-lg-9 col-md-8">${customer.address}</div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-3 col-md-4 label"><fmt:message key="phone"/></div>
                                        <div class="col-lg-9 col-md-8">${customer.telephone}</div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-3 col-md-4 label"><fmt:message key="created.date"/></div>
                                        <div class="col-lg-9 col-md-8">${customer.creationDate}</div>
                                    </div>
                                </div>
                                <div class="col-xl-4" >
                                    <c:if test="${not empty customer.thumbnail}">
                                        <img src="${pageContext.request.contextPath}/file/download?file=${customer.thumbnail}&dir=folder.customer.images" style="height:250px">
                                    </c:if>
                                    <c:if test="${empty customer.thumbnail}">
                                       <img src="assets/img/default.png" style="height:250px">
                                    </c:if>
                                </div>

                            </div>

                        </div>
                        <div class="tab-pane fade customer-jobs" id="customer-jobs">
                            <table class="table datatable">
                                <thead style="background-color: #dddfe3;">
                                    <tr>
                                        <th scope="col">Number</th>
                                        <th scope="col">Job type</th>
                                        <th scope="col">Title</th>
                                        <th scope="col">Reference</th>
                                        <th scope="col">JobStatus</th>
                                        <th scope="col">Creation date</th>
                                        <th scope="col">Customer</th>
                                        <th scope="col">Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="job" items="${jobs}" varStatus="loop">
                                        <tr class="${loop.index % 2 == 0 ? 'even-row' : 'odd-row'}">
                                            <c:set var="index" value="${loop.index}" />
                                            <%    int index = (Integer) pageContext.getAttribute("index");  %>
                                            <td>  <%= index + 1 %></td>
                                            <td><a>${job.title}</a></td>
                                            <td><a>${job.jobType.name}</a></td>
                                            <td><a>${job.referenceNumber}</a></td>
                                            <td><a>${job.status}</a></td>
                                            <td><a><fmt:formatDate type = "both" value = "${job.creationDate}" /></a></td>
                                            <td><a>${job.customer.name}</a></td>
                                            <td>

                                            </td>

                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</main><!-- End #main -->
  
  <script src="assets/js/users.js"></script>
  
  