<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
							pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

 <meta content="width=device-width, initial-scale=1.0" name="viewport">

	<link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">
     <link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">
	 <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
 
 <link href="assets/css/list-users.css" rel="stylesheet">
 
  <main id="users-list" class="main">
  <div class="pagetitle">
      <nav>
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a href="/">Home</a></li>
          <li class="breadcrumb-item">User</li>
          <li class="breadcrumb-item active">List</li>
        </ol>
      </nav>
    </div>
    <section class="section">
      <div class="row">
        <div class="col-lg-12">

          <div class="card">
            <div class="card-body">
              <h5 class="card-title">Users list</h5>
			    <button onclick="loadPage('/user/add-user')" type="button" class="btn btn-primary">Add Job Sheet</button>
              <!-- Table with stripped rows -->
              <table class="table datatable">
                <thead>
                  <tr>
                    <th scope="col">Photo</th>
                    <th scope="col">Name(s)</th>
                    <th scope="col">Email</th>
                    <th scope="col">Address</th>
                    <th scope="col">Group</th>
                    <th scope="col">Mobile</th>
                    <th scope="col">Date</th>
                    <th scope="col">Actions</th>
                  </tr>
                </thead>
                <tbody>
                <c:forEach var="user" items="${users}" varStatus="loop">
                  <tr class="${loop.index % 2 == 0 ? 'even-row' : 'odd-row'} bonjour">
                    <th></th>
                    <td></td>
                    <td></td>
                    <td>
                      <button class="button-see"><i class="fas fa-eye"></i></button>
                      <button class="button-edite"><i class="fas fa-pencil-alt"></i></button>
				      <button class="button-delete"  id="startDeleting" data-bs-toggle="modal"><i class="fas fa-trash-alt"></i></button>
                    </td>
                  </tr>
                 </c:forEach>
                 </tbody>  
              </table>
 
              <!-- End Table with stripped rows -->
               <!-- Right/End Aligned Pagination -->
              <nav aria-label="Page navigation example">
                <ul class="pagination justify-content-end">
                  <li class="page-item disabled">
                    <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Previous</a>
                  </li>
                  <li class="page-item"><a class="page-link" href="#">1</a></li>
                  <li class="page-item"><a class="page-link" href="#">2</a></li>
                  <li class="page-item"><a class="page-link" href="#">3</a></li>
                  <li class="page-item">
                    <a class="page-link" href="#">Next</a>
                  </li>
                </ul>
              </nav><!-- End Right/End Aligned Pagination -->
            </div>
          </div>
        </div>
      </div>
    </section>
  </main><!-- End #main -->
  
 <script src="assets/js/main.js"></script>

  
  