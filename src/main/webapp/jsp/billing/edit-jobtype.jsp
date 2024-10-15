<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<main id="add-user">

		<section>
			<div class="card">
				<div class="card-body">
					<h5 class="card-title text-center pb-0 fs-4"><fmt:message key="save.jobType"/></h5>
					<p class="text-center small"><fmt:message key="enter.jobType.details"/></p>
					
					
					
					<form:form class="row g-3" id="machineForm" style=" margin-left: 5%;" method="POST"  modelAttribute="JobType" >
					  <div class="col-md-6">
						<label for="Name" class="form-label" style="margin-left: -50px;"><a>Name</a></label>
						  <div class="input-group has-validation">
							<span class="input-group-text"><i class="fas fa-user"></i></span>
							<input type="text" id="name" name="Name" class="form-control" placeholder="Name" value="${jobType.name}" required />
						  </div>
						  <div id="emptyNameAlert" class="alert alert-danger alert-dismissible fade show" role="alert" style="display: none;"></div>
					  </div>
					  <div class="col-md-6">
						 <label for="" class="form-label"><a>Category</a></label> 
					        <select id="category" name="name" class="form-select">
					        <c:if test="${jobType.category==jobType.category}">
					       <c:if test="${jobType.category ==0}"> 
						        <option selected value="0">OPEN JOB</option>
			                    <option value="1">FOLDED JOB WITHOUT COVER</option>
			                    <option value="2">FOLDED JOB WITH COVER</option>
			                    <option value="3">OPEN JOB WITH COVER</option>
					          </c:if>
					            <c:if test="${jobType.category==1}"> 
						        <option  selected value="1">FOLDED JOB WITHOUT COVER</option>
						        <option value="0">OPEN JOB</option>
			                    <option value="2">FOLDED JOB WITH COVER</option>
			                    <option value="3">OPEN JOB WITH COVER</option>
					          </c:if>
					           <c:if test="${jobType.category==2}"> 
					             <option selected value="2">FOLDED JOB WITH COVER</option>
					             <option value="0">OPEN JOB</option>
		                   		 <option value="1">FOLDED JOB WITHOUT COVER</option>
		                   		 <option value="3">OPEN JOB WITH COVER</option>
					          </c:if>
					           <c:if test="${jobType.category==3}"> 
					             <option selected value="3">OPEN JOB WITH COVER</option>
					             <option value="0">OPEN JOB</option>
		                   		 <option value="1">FOLDED JOB WITHOUT COVER</option>
		                   		 <option value="2">FOLDED JOB WITH COVER</option>
					          </c:if>
					        </c:if>
		                 
		                  </select>
					 </div>
						
				<div class="col-md-3" style="  width: 13%; left: 71%; position: relative; ">
				  <input type="button" onclick="saveJobType('${jobType.id}')" style=" bottom: -42%;" class="btn btn-primary w-100" value="Save" data-bs-dismiss="modal"  />
				</div>
				</form:form>
				</div>
			</div>
		</section>
</main>
<!-- End #main -->
<script src="assets/js/billing/papertype.js"></script>
