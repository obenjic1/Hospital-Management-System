<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
							pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

 <link href="assets/css/group-update.css"  rel="stylesheet">

 <main id="create-group">
 <div class="container" >
      <section>
        <div class="container">
          <div class="row justify-content-center">
            <div class="col-lg-4 col-md-6 d-flex flex-column align-items-center justify-content-center">
              <div class="card mb-3" style="width: 1139px;">
                <div class="card-body">
                  <h5 class="card-title text-center pb-0 fs-4"> <fmt:message key="update.group"/></h5>
                  <p class="text-center small"><fmt:message key="change.to.update.group.informations"/></p>
                  <form  class="row g-3 needs-validation" style=" margin-left: 2%;">
                    <div class="col-md-6">
                      <label for="name" class="form-label"><fmt:message key="name"/></label>
                      <div class="input-group has-validation">
                        <input type="text" id="name" name="name" class="form-control" value="${groupeFinded.name}" readonly="readonly" style="margin-left: 14%;"/>
                      </div>
                    </div>
                    <div class="col-md-6">
                      <label for="description" class="form-label"><fmt:message key="description"/></label>
                      <div class="input-group has-validation">
                        <textarea  id="description" name="description" class="form-control"  style=" position: relative; left: -2%;">${groupeFinded.description}</textarea>
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
					              <input class="check-box" type="checkbox" value="${role.name}" id="role_${role.name}" name="ids" class="form-check-input"
						            <c:forEach var="rolechecked" items="${groupeFinded.groupRoles}">
						              <c:if test="${rolechecked.role.name == role.name}">checked</c:if>
						            </c:forEach>
						          >
					              <label for="role_${role.name}">${role.name}</label>
					            </div>
					          </c:forEach>
					        </div>
					      </div>
					    </div>
					  </div>
					</div>					
                    <div class="col-md-3">
                      <input type="button" data-bs-toggle="modal" data-bs-target="#creation" id="create-btn" onclick="updateGroupe('${groupeFinded.name}')" style=" left: 314%; bottom: 2%;" class="btn btn-primary w-100"  value="Save Changes"/>
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
