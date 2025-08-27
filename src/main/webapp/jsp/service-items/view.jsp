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
	<div class="card" >
		<div class="card-body">
			<h5 class="card-title text-center pb-0 fs-4">Service Details</h5>
				<p class="text-center small">carefully view the Details for ${item.name }</p>
<div class="container m-4" >
    <h2>Service Item Details</h2>
    <form  class="m-4">
    <div class="row">
        <div class="my-2 col-md-6">
            <label> Name   : </label>
            <span>  ${item.name}</span>
        </div>
        <div class="my-2 col-md-6">
            <label>Description  :</label>
            <span> ${item.description} </span>
        </div>
     </div>
     <div class="row">
        <div class="my-2 col-md-6">
            <label class="mb-1">Category  :</label>
            <span> ${item.category}</span>
        </div>
         <div class="my-2 col-md-6">
            <label>Cost  :</label>
            <span>  ${item.price}  CFA</span>
        </div>
         <div class="my-2 col-md-6">
            <label>Status  :</label>
            <span>  ${item.status} </span>
        </div>
      </div>
        <div class="d-flex justify-content-between">
        
        </div>
       
       
    </form>
</div>
</div>
</div>
		</section>
		</main>







