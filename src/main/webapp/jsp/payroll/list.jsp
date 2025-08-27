<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Payroll List</title>
</head>
<body>
<div class="container mt-4">
<div class="d-flex justify-content-between  my-3">
  <h4>Staff Payroll</h4>
  <button class="btn btn-outline-primary"   onclick="loadPageModalForm('payroll/create')" data-toggle="tooltip" data-placement="top" title="create new user" type="button" data-bs-toggle="modal" data-bs-target="#ExtralargeModal">
    <i class="bi bi-plus-circle"></i> New Payroll
  </button>
</div>
<form  class="d-flex m-4" >
			<input type="text" name="q" class="form-control search-bar m-6" id="searchBoxer"  style="width: 49%;" placeholder="Search Staff..." />
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
        	<th>No</th>
            <th>Staff</th>
            <th>Base Salary</th>
            <th>Allowances</th>
            <th>Deductions</th>
            <th>Net Salary</th>
            <th>Pay Date</th>
            <th>Status</th>
            <th>Status</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="p" items="${payrolls}" varStatus="loop">
                <tr>
                  <td>${loop.index + 1}</td>
                <td>${p.staff.firstName} ${p.staff.lastName}</td>
                <td>${p.basicSalary}</td>
                <td>${p.allowances}</td>
                <td>${p.deductions}</td>
                <td>${p.netSalary}</td>
                <td>${p.payDate}</td>
                <td>${p.status}</td>
                <td>
                <a class="btn btn-sm btn-success">
			        Download Payslip
			    </a>
               <button>Pay Staff</button> </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
