<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"   prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Daily Sales Report</title>
  <style>
    body{font-family:Arial,Helvetica,sans-serif;}
    table{border-collapse:collapse;margin-top:15px;}
    th,td{border:1px solid #999;padding:6px 10px;text-align:right;}
    th{background:#eee;}
  </style>
</head>
<body>

<h2>Pharmacy Daily Sales – ${date}</h2>

<table>
  <tr><th>Metric</th><th>Value</th></tr>
  <tr>
    <td>Total Revenue</td>
    <td><fmt:formatNumber type="currency" value="${revenue}"/></td>
  </tr>
  <tr>
    <td>Total Items Sold</td>
    <td><fmt:formatNumber value="${items}" groupingUsed="false"/></td>
  </tr>
</table>

<br>
<a href="<c:url value='/stats/dashboard'/>">← Back to Dashboard</a>

</body>
</html>