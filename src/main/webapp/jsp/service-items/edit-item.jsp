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
			<h5 class="card-title text-center pb-0 fs-4">Update a Service</h5>
				<p class="text-center small">carefully Modify the Details to Update  service</p>
<div class="container m-4" >
    <h2>Service Item Creation Form</h2>
    <form  class="m-4">
    <div class="row">
        <div class="my-2 col-md-6">
            <label> Name</label>
            <input type="text" class="form-control" id="uname" name="name" value="${item.name}" required/>
        </div>
        <div class="my-2 col-md-6">
            <label>Description</label>
            <input type="text" class="form-control" id="udescription" name="name" value="${item.description}" />
        </div>
     </div>
     <div class="row">
        <div class="my-2 col-md-6">
            <label class="mb-1">Category</label>
            <select name="category" id="ucategory" class="form-select " style="width:100%;">
                    <option value="">Choose Category</option>
                   
					 <option value="${item.category}" selected> ${item.category}</option>
                  				
                        <option value="Emmergency care"> Emmergency care</option>
                        <option value=" Surgical Service"> Surgical Service</option>
                         <option value=" Diagnostic Service"> Diagnostic Service</option>
                       <option value=" Support Service"> Support Service</option>
                        <option value=" Delivery Service"> Delivery Service</option>
                         <option value=" others"> Others</option>

<!--                     <option  onclick="loadMainModalForm('store/add-category')" data-bs-toggle="modal" data-bs-target="#MainModal" class="btn btn-gradient" style="margin-right:121px">Add New Category</option> -->
                </select>
        </div>
         <div class="my-2 col-md-6">
            <label>Price</label>
            <input type="number" step="0.01"  id="uprice" class="form-control" name="price" value="${item.price}" required/>
        </div>
      </div>
        <div class="d-flex justify-content-between">
         <a onclick="loadPage('services')" class="btn btn-secondary">Cancel</a>
         <button type="button" class="btn btn-success" onclick="updateService(${item.id})" >Save</button>
        </div>
       
       
    </form>
</div>
</div>
</div>
		</section>
		</main>







