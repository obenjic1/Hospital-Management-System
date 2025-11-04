<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Doctor Consultation Report</title>

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600&display=swap" rel="stylesheet">
    <!-- LineIcons -->
    <link rel="stylesheet" href="https://cdn.lineicons.com/4.0/lineicons.css">

    <style>
        :root{
            --bg:#f7f9fc;
            --text:#1f2937;
            --text2:#6b7280;
            --accent:#6366f1;
            --green:#10b981;
            --yellow:#f59e0b;
            --red:#ef4444;
            --border:#e5e7eb;
            --row:#ffffff;
        }
        *{box-sizing:border-box;margin:0;padding:0;font-family:'Inter',sans-serif}
        body{background:var(--bg);color:var(--text);min-height:100vh;display:flex;flex-direction:column}

        /* soft animated wallpaper */
        body::before{
            content:'';position:fixed;inset:0;z-index:-1;
            background:radial-gradient(at 20% 20%, hsla(210,80%,80%,.18) 0%, transparent 40%),
                       radial-gradient(at 80% 80%, hsla(280,80%,80%,.18) 0%, transparent 40%);
            animation: pulse 18s ease-in-out infinite;
        }
        @keyframes pulse{
            0%,100%{transform:scale(1)}
            50%{transform:scale(1.03)}
        }

        /* ----- header ----- */
        .header{display:flex;justify-content:space-between;align-items:center;margin:2.5rem 0 1.5rem}
        .date-range{font-size:.85rem;color:var(--text2)}

        /* ----- KPI coins ----- */
        .kpi-grid{display:grid;grid-template-columns:repeat(auto-fit,minmax(200px,1fr));gap:1.5rem;margin-bottom:2rem}
        .kpi-coin{background:#fff;border-radius:1rem;padding:1.5rem;box-shadow:0 2px 12px rgba(0,0,0,.04);display:flex;align-items:center;gap:1rem}
        .kpi-icon{width:52px;height:52px;border-radius:50%;display:grid;place-items:center;font-size:1.5rem}
        .kpi-info{flex:1}
        .kpi-label{font-size:.75rem;color:var(--text2)}
        .kpi-val{font-size:1.4rem;font-weight:600;margin-top:.25rem}
        .kpi-foot{font-size:.7rem;margin-top:.5rem;color:var(--text2)}

        /* ----- filter pills ----- */
        .filter-bar{display:flex;gap:.5rem;margin-bottom:1rem;flex-wrap:wrap}
        .filter-pill{padding:.35rem .9rem;border-radius:999px;font-size:.75rem;font-weight:500;cursor:pointer;transition:.2s;border:1px solid transparent}
        .filter-pill.active{background:var(--accent);color:#fff}
        .filter-pill:not(.active){background:#fff;color:var(--text2);border-color:var(--border)}
        .filter-pill:not(.active):hover{border-color:var(--accent)}

        /* ----- table ----- */
        .table-wrap{background:#fff;border-radius:1rem;box-shadow:0 2px 12px rgba(0,0,0,.03);overflow:hidden}
        .report-table{width:100%;border-collapse:collapse}
        .report-table thead{background:#f9fafb}
        .report-table th{padding:.75rem 1rem;font-size:.7rem;text-transform:uppercase;color:var(--text2);letter-spacing:.5px;text-align:left}
        .report-table tbody tr{border-bottom:1px solid var(--border);transition:background .2s}
        .report-table tbody tr:hover{background:#f3f4f6}
        .report-table td{padding:.75rem 1rem;font-size:.8rem}
        .report-table td:nth-child(3),.report-table td:nth-child(4),.report-table td:nth-child(5),.report-table td:nth-child(6){text-align:right}

        /* row tints */
        tr.paid{background:#ecfdf5}
        tr.partial{background:#fffbeb}
        tr.unpaid{background:#fef2f2}

        /* ----- total bar ----- */
        .total-bar{background:#f9fafb;display:flex;justify-content:space-between;align-items:center;padding:.75rem 1rem;font-size:.8rem;font-weight:500}
    </style>
</head>
<body>

<div class="container">
    <!-- Header -->
    <div class="header">
        <div>
            <h1 style="font-weight:600;font-size:1.5rem">Doctor Consultation Report</h1>
<%--             <p class="date-range">Period: <fmt:formatDate value="${startDate}" pattern="dd MMM yyyy"/> → <fmt:formatDate value="${endDate}" pattern="dd MMM yyyy"/></p> --%>
        </div>
    </div>

    <!-- KPI coins -->
    <div class="kpi-grid">
        <div class="kpi-coin">
            <div class="kpi-icon" style="background:#e0e7ff;color:var(--accent)"><i class="lni lni-users"></i></div>
            <div class="kpi-info">
                <div class="kpi-label">Total Consultations</div>
                <div class="kpi-val"><fmt:formatNumber value="${totalConsultations}" type="number"/></div>
            </div>
        </div>
        <div class="kpi-coin">
            <div class="kpi-icon" style="background:#d1fae5;color:var(--green)"><i class="lni lni-dollar"></i></div>
            <div class="kpi-info">
                <div class="kpi-label">Total Revenue</div>
                <div class="kpi-val"><fmt:formatNumber value="${totalRevenue}" type="number"/> CDF</div>
            </div>
        </div>
        <div class="kpi-coin">
            <div class="kpi-icon" style="background:#fffbeb;color:var(--yellow)"><i class="lni lni-money-location"></i></div>
            <div class="kpi-info">
                <div class="kpi-label">Total Doctor Pay</div>
                <div class="kpi-val"><fmt:formatNumber value="${totalDoctorPay}" type="number"/> CDF</div>
            </div>
        </div>
        <div class="kpi-coin">
            <div class="kpi-icon" style="background:#fee2e2;color:var(--red)"><i class="lni lni-graph"></i></div>
            <div class="kpi-info">
                <div class="kpi-label">Hospital Profit</div>
                <div class="kpi-val"><fmt:formatNumber value="${totalHospitalProfit}" type="number"/> CDF</div>
            </div>
        </div>
    </div>

  <div class="date-range-picke row mb-2">
        	
        	<div class="col-md-4"><label>From:</label> <input type="date" id="startDateD" value="${startDate}" class="form-control" width="50%"></div>
        	<div class="col-md-4"><label> To:</label> <input type="date" id="endDateD" value="${endDate}" class="form-control" width="50%"></div>
            <div class="col-md-3"> <button  class = "btn btn-outline-primary " style="margin-top: 25px;" onclick="searchDoctor()">Apply</button></div>
           
          
        </div>
    <!-- Filter pills -->
    <div class="filter-bar">
        <span class="filter-pill active" data-status="ALL">All</span>
        <span class="filter-pill" data-status="PAID">Paid</span>
        <span class="filter-pill" data-status="PARTIAL">Partial</span>
        <span class="filter-pill" data-status="UNPAID">Unpaid</span>
    </div>

    <!-- Table -->
    <div class="table-wrap">
        <table class="report-table" id="dataTable">
            <thead>
                <tr>
                    <th>Doctor</th>
                    <th>Consultation Type</th>
                    <th style="text-align:right"># Consultations</th>
                    <th style="text-align:right">Total Amount</th>
                    <th style="text-align:right">Doctor Pay</th>
                    <th style="text-align:right">Hospital Profit</th>
<!--                     <th style="text-align:center">Status</th> -->
                </tr>
            </thead>
            <tbody>
                <c:forEach var="stat" items="${stats}" varStatus="loop">
                    <tr >
                        <td>${stat.doctorName}</td>
                        <td>${stat.consultationName}</td>
                        <td style="text-align:right"><fmt:formatNumber value="${stat.consultationCount}" type="number"/></td>
                        <td style="text-align:right"><fmt:formatNumber value="${stat.totalAmount}" type="number"/></td>
                        <td style="text-align:right"><fmt:formatNumber value="${stat.doctorPay}" type="number"/></td>
                        <td style="text-align:right"><fmt:formatNumber value="${stat.hospitalProfit}" type="number"/></td>
<!--                         <td style="text-align:center"> -->
<%--                             <span class="badge ${stat.paymentStatus eq 'PAID' ? 'badge-paid' : stat.paymentStatus eq 'PARTIAL' ? 'badge-partial' : 'badge-unpaid'}"> --%>
<%--                                 ${stat.paymentStatus} --%>
<!--                             </span> -->
<!--                         </td> -->
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <!-- Total bar -->
        <div class="total-bar">
            <span>Visible rows: <strong id="visibleRows">-</strong></span>
            <span>Visible revenue: <strong id="visibleRev">-</strong> XAF</span>
        </div>
    </div>
</div>

<script src="assets/js/hospital/consultation.js"></script>

</body>
</html>