<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Service Items</title>
            <link href="assets/css/list-users.css" rel="stylesheet">
    
</head>
<body class="container mt-4">
    <div class="d-flex justify-content-between  my-3">
  <h4>Offered Services</h4>
  <button class="btn btn-outline-primary"   onclick="loadPageModalForm('services/new')" data-toggle="tooltip" data-placement="top" title="create new Service" type="button" data-bs-toggle="modal" data-bs-target="#ExtralargeModal">
    <i class="bi bi-plus-circle"></i> New Service</button>
</div>

<form  class="d-flex m-4" >
			<input type="text" name="q" class="form-control search-bar m-6" id="searchBoxer"  style="width: 49%;" placeholder="Search a service..." />
                <select name="category" id="category" class="form-select ms-2" style="width:180px;">
                    <option value="All" ${selectedCategory == 'All' ? 'selected' : ''}>All Categories</option>
                    <c:forEach var="cat" items="${categories}">
                        <option value="${cat.name}">${cat.name}</option>
                    </c:forEach>
                    <option  onclick="loadMainModalForm('store/add-category')" data-bs-toggle="modal" data-bs-target="#MainModal" class="btn btn-gradient" style="margin-right:121px">Add New Category</option>
                </select>
                <button type="button"  onclick="event.preventDefault(); searchPharmacyMedicine()" class="btn btn-outline-primary ms-2">Search</button>
            </form>
    <table class=" table table-bordered table-hover shadow-sm" style="text-align: center;">
        <thead  class="table-dark">
            <tr>
                <th>ID</th><th>Service Name</th><th>Category</th><th>Price</th><th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="i" items="${items}" varStatus="loop">
                <tr>
                    <td>${loop.index + 1}</td>
                    <td>${i.name}</td>
                    <td>${i.category}</td>
                    <td>${i.price}</td>
                    <td>
                         <a  data-bs-toggle="modal" data-bs-target="#ExtralargeModal" onclick="loadPageModalForm('services/view/${i.id}')" class="btn btn-sm btn-outline-primary">view</a>
                        <a  data-bs-toggle="modal" data-bs-target="#ExtralargeModal" onclick="loadPageModalForm('services/edit/${i.id}')" class="btn btn-sm btn-secondary">Edit</a>
                        
                         <a  data-bs-toggle="modal" data-bs-target="#ExtralargeModal" class="btn btn-sm btn-danger">Disable</a>
                           <button class="button-see" data-bs-toggle="modal" data-bs-target="#ExtralargeModal" onclick="loadPageModalForm('services/history/${i.id}')">
										       <i class="ri-eye-line"></i>
						</button>
                        
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
