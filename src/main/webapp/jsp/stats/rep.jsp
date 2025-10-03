<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Consultation Subtype Report</title>

    <!-- Google Font -->
<!--     <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600&display=swap" rel="stylesheet"> -->
    <!-- LineIcons -->
<!--     <link rel="stylesheet" href="https://cdn.lineicons.com/4.0/lineicons.css"> -->

    <style>
        :root{
            --bg:#f7f9fc;
            --text:#1f2937;
            --text2:#6b7280;
            --accent:#6366f1;
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
        .period-picker{display:flex;align-items:center;gap:.5rem;background:#fff;padding:.5rem 1rem;border-radius:999px;box-shadow:0 2px 8px rgba(0,0,0,.04)}
        .period-picker select{border:none;background:transparent;font-weight:500;outline:none;color:var(--text)}

        /* ----- table ----- */
        .table-wrap{background:#fff;border-radius:1rem;box-shadow:0 2px 12px rgba(0,0,0,.03);overflow:hidden}
        .report-table{width:100%;border-collapse:collapse}
        .report-table thead{background:#f9fafb}
        .report-table th{padding:.75rem 1rem;font-size:.7rem;text-transform:uppercase;color:var(--text2);letter-spacing:.5px;text-align:left}
        .report-table tbody tr{border-bottom:1px solid var(--border);transition:background .2s}
        .report-table tbody tr:hover{background:#f3f4f6}
        .report-table td{padding:.75rem 1rem;font-size:.8rem}
        .report-table td:nth-child(2),.report-table td:nth-child(3){text-align:right}

        /* ----- empty ----- */
        .empty-state{text-align:center;padding:3rem 0}
        .empty-state img{width:130px;opacity:.7;margin-bottom:1rem}
    </style>
</head>
<body>

<div class="container">
    <!-- Header -->
    <div class="header">
        <div>
            <h1 style="font-weight:600;font-size:1.5rem">Consultation Subtype Report</h1>
            <p style="color:var(--text2);font-size:.85rem">Summary for the selected period</p>
        </div>
        <div class="period-picker">
            <i class="lni lni-calendar"></i>
            <select id="periodSelect" onchange="changePeriod()">
                <option value="daily"  ${period eq 'daily'  ? 'selected' : ''}>Today</option>
                <option value="weekly" ${period eq 'weekly' ? 'selected' : ''}>This Week</option>
                <option value="monthly"${period eq 'monthly'? 'selected' : ''}>This Month</option>
            </select>
        </div>
    </div>

    <!-- Table only -->
    <div class="table-wrap">
        <table class="report-table">
            <thead>
                <tr>
                    <th>Consultation Subtype</th>
                    <th style="text-align:right">Count</th>
                    <th style="text-align:right">Total Revenue (CDF)</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="stat" items="${stats}" varStatus="loop">
                    <tr>
                        <td>${stat.subtypeName}</td>
                        <td style="text-align:right"><fmt:formatNumber value="${stat.count}" type="number"/></td>
                        <td style="text-align:right"><fmt:formatNumber value="${stat.revenue}" type="number"/></td>
                    </tr>
                </c:forEach>
                <c:if test="${empty stats}">
                    <tr>
                        <td colspan="3">
                            <div class="empty-state">
<!--                                 <img src="https://cdn-icons-png.flaticon.com/512/5911/5911466.png" alt="No data"> -->
                                <h5 style="color:var(--text2)">No records for the selected period</h5>
                                <p style="color:var(--text2);font-size:.8rem">Try choosing another time range.</p>
                            </div>
                        </td>
                    </tr>
                </c:if>
            </tbody>
        </table>
    </div>
</div>

<script>
    function changePeriod(){
        const p = document.getElementById('periodSelect').value;
        window.location.href = '${pageContext.request.contextPath}/statistics/subtype?period=' + p;
    }
</script>
</body>
</html>