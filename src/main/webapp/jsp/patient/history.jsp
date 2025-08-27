<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<head>
  <link href="assets/css/billing/job.css" rel="stylesheet">
</head>

<main id="add-machine">
<!-- 	action="machine/add-machine -->
	<section>
			<div class="card">
				<div class="card-body">
					<h5 class="card-title text-center pb-0 fs-4">Patient History</h5>
					<p class="text-center small"></p>
					<div class="row">
				 	<table class="ta" id="cover-table" style=":">
						<thead style=":">
							<tr>
								<th scope="col"><fmt:message key="date"/> </th>
								<th scope="col"><fmt:message key="user"/> </th>
<!-- 								<th scope="col">Action </th> -->
								<th scope="col"> <fmt:message key="operation"/></th>
							</tr>
						</thead>
								<tbody>
								 <c:forEach var="p" items="${patients.tracking}" varStatus="loop">
								 <tr>
								  <td>${p.creationDate}" </td>
								    <td>${p.performedBy}</td>
<%-- 								    <td>${med.action}</td> --%>
								    <td>${p.description}</td>
								  </tr>
								 </c:forEach>
								</tbody>
				</table>
				</div>
					</div>
				</div>
		</section>
		<script src="assets/js/main.js"></script>
		<script src="assets/js/billing/machine.js"></script> 
	</main>
		


