<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
							pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

  <link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
 <link href="assets/css/groups.css"  rel="stylesheet">

 <main id="create-group">
 <div class="container" >
 	<div class="pagetitle">
         <nav>
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a href="/">Home</a></li>
          <li class="breadcrumb-item">Form</li>
          <li class="breadcrumb-item active">Group</li>
        </ol>
      </nav>
    </div>
 
      <section>
        <div class="container">
          <div class="row justify-content-center">
            <div class="col-lg-4 col-md-6 d-flex flex-column align-items-center justify-content-center">
              <div class="card mb-3" style="width: 328%;bottom: 41px;">
                <div class="card-body">
                    <h5 class="card-title text-center pb-0 fs-4"> <fmt:message key="add.group"/></h5>
                    <p class="text-center small"> <fmt:message key="group.informations"/></p>
                  <form  class="row g-3 needs-validation">
                    <div class="col-md-6">
                        <label for="name" class="form-label"><fmt:message key="name"/></label>
                     	 <div class="input-group has-validation">
                        <input type="text" id="name" name="name" class="form-control" style="left: 2px"/>
                      <div class="invalid-feedback"><fmt:message key="please.enter.the.group.name"/></div>
                      </div>
                    </div>
                    <div class="col-md-6">
                       <label for="description" class="form-label"> <fmt:message key="description"/></label>
                      <div class="input-group has-validation">
                        <textarea style="left: -1px"  id="description" name="description" class="form-control" placeholder=" <fmt:message key="enter.the.description.of.the.groupe"/>" ></textarea>
                      </div>
                    </div>
                
	                  <div class="col-md-12">
					  <div class="row">
					    <div class="col-md-12">
					      <label class="ids"><fmt:message key="select.role"/></label>
					    </div>
					    <div class="col-md-12">
					      <div class="input-group has-validation border p-3 d-flex flex-column">
					        <div class="scrollable-div">
					          <c:forEach items="${roles}" var="role" varStatus="loop">
					            <div class="role-column-${loop.index % 2}">
					              <input class="check-box" type="checkbox" value="${role.name}" id="role_${role.name}" name="ids" class="form-check-input">
					              <label for="role_${role.name}">${role.name}</label>
					            </div>
					          </c:forEach>
					        </div>
					      </div>
					    </div>
					  </div>
					</div>
                    <div class="col-md-3">
                      <input type="button" style=" left: 832px;  bottom: 2px;" id="create-btn" onclick="addGroupe(); loadPage('group/list-groups');" class="btn btn-primary w-100"  value="Save"/>
                     </div>	              
                  </form>
                </div>
              </div>
            </div>
          </div>
          </div>
      </section>
      </div>
  </main><!-- End #main -->
  
 <script src="assets/js/groups.js"></script>