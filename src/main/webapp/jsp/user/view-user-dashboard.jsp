<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Dashboard | ${user.username}</title>

    <!-- Google Font -->
<!--     <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&display=swap" rel="stylesheet"> -->
    <!-- LineIcons -->
<!--     <link rel="stylesheet" href="https://cdn.lineicons.com/4.0/lineicons.css"> -->

    <style>
        :root{
            --bg:#f5f7fa;
            --panel:#ffffff;
            --accent:#6366f1;
            --accent2:#10b981;
            --text:#1f2937;
            --text2:#6b7280;
            --green:#10b981;
            --red:#ef4444;
            --yellow:#f59e0b;
        }
        *{box-sizing:border-box;margin:0;padding:0;font-family:'Inter',sans-serif}
        body{background:var(--bg);color:var(--text);min-height:100vh;display:flex;flex-direction:column}

        /* soft animated gradient wallpaper */
        body::before{
            content:'';position:fixed;inset:0;z-index:-1;
            background:radial-gradient(at 20% 20%, hsla(210,80%,80%,.25) 0%, transparent 40%),
                       radial-gradient(at 80% 80%, hsla(280,80%,80%,.25) 0%, transparent 40%);
            animation: pulse 15s ease-in-out infinite;
        }
        @keyframes pulse{
            0%,100%{transform:scale(1)}
            50%{transform:scale(1.02)}
        }

        /* ----- layout ----- */
        .top-bar{display:flex;align-items:center;justify-content:space-between;margin:2rem 0}
        .user-pill{display:flex;align-items:center;gap:.75rem;background:var(--panel);padding:.5rem 1rem;border-radius:999px;box-shadow:0 2px 8px rgba(0,0,0,.05)}
        .user-pill img{width:36px;height:36px;border-radius:50%;object-fit:cover}
        .tab-nav{display:flex;gap:1.5rem;list-style:none;margin-bottom:2rem}
        .tab-nav li{padding:.5rem 0;cursor:pointer;border-bottom:2px solid transparent;transition:.3s;font-weight:500}
        .tab-nav li.active{border-color:var(--accent);color:var(--accent)}
        .tab-content{display:none}
        .tab-content.active{display:block}

        /* ----- KPI pills ----- */
        .kpi-grid{display:grid;grid-template-columns:repeat(auto-fit,minmax(200px,1fr));gap:1.5rem;margin-bottom:2rem}
        .kpi-pill{background:var(--panel);border-radius:1rem;padding:1.5rem;box-shadow:0 2px 12px rgba(0,0,0,.04);display:flex;align-items:center;gap:1rem}
        .kpi-icon{width:48px;height:48px;border-radius:50%;display:grid;place-items:center;font-size:1.25rem}
        .kpi-info{flex:1}
        .kpi-val{font-size:1.5rem;font-weight:600}
        .kpi-label{font-size:.75rem;color:var(--text2)}
        .kpi-foot{font-size:.7rem;margin-top:.25rem;color:var(--text2)}

        /* ----- recent sales ----- */
        .sales-box{background:var(--panel);border-radius:1rem;padding:1.5rem;box-shadow:0 2px 12px rgba(0,0,0,.04)}
        .sales-table{width:100%;border-collapse:collapse}
        .sales-table thead tr{border-bottom:1px solid #e5e7eb}
        .sales-table th{text-align:left;padding:.75rem 1rem;font-size:.7rem;text-transform:uppercase;color:var(--text2);letter-spacing:.5px}
        .sales-table td{padding:.75rem 1rem;font-size:.8rem}
        .sales-table tbody tr:hover{background:#f9fafb}
        .badge{padding:.25rem .5rem;border-radius:.5rem;font-size:.7rem;font-weight:500}
        .badge-paid{background:var(--green);color:#fff}
        .badge-partial{background:var(--yellow);color:#fff}
        .badge-unpaid{background:var(--red);color:#fff}

        /* ----- forms ----- */
        .form-grid{display:grid;grid-template-columns:1fr 1fr;gap:1rem}
        .form-grid.single{grid-template-columns:1fr}
        .form-group{display:flex;flex-direction:column}
        .form-group label{margin-bottom:.25rem;font-size:.75rem;color:var(--text2)}
        .form-group input,.form-group select{padding:.5rem .75rem;border:1px solid #e5e7eb;border-radius:.5rem;background:#fff;color:var(--text);transition:.3s}
        .form-group input:focus{outline:none;border-color:var(--accent)}
        .btn{padding:.5rem 1.25rem;border:none;border-radius:.5rem;font-weight:500;cursor:pointer;transition:.3s}
        .btn-primary{background:var(--accent);color:#fff}
        .btn-primary:hover{filter:brightness(1.1)}
        .btn-success{background:var(--green);color:#fff}

        @media (max-width:768px){
            .form-grid{grid-template-columns:1fr}
            .sales-table thead{display:none}
            .sales-table,.sales-table tbody,.sales-table tr{display:block}
            .sales-table tr{margin-bottom:.75rem;background:var(--panel);border-radius:.75rem;padding:.75rem;box-shadow:0 2px 8px rgba(0,0,0,.04)}
            .sales-table td{display:flex;justify-content:space-between;padding:.25rem 0}
            .sales-table td::before{content:attr(data-label);font-weight:500;color:var(--text2)}
        }
    </style>
</head>
<body>

<!-- =========================  TOP BAR  ========================= -->
<div class="container top-bar">
    <div>
        <h1 style="font-weight:600;font-size:1.5rem">Hello, ${user.staff.firstName} 👋</h1>
        <p style="color:var(--text2);font-size:.85rem">Here is what’s happening today.</p>
    </div>
    <div class="user-pill">
        <img src="${not empty user.imagePath ? pageContext.request.contextPath.concat('/file/download?file=').concat(user.imagePath).concat('&dir=folder.user.images') : 'assets/img/default.png'}" alt="avatar">
        <div>
            <div style="font-weight:500;font-size:.8rem">${user.staff.firstName} ${user.staff.lastName}</div>
            <div style="font-size:.7rem;color:var(--text2)">${user.groupe.name}</div>
        </div>
    </div>
</div>

<!-- =========================  TAB NAV  ========================= -->
<div class="container">
    <ul class="tab-nav">
        <li class="active" data-tab="overview">Overview</li>
        <li data-tab="edit-profile">Edit Profile</li>
        <li data-tab="change-password">Change Password</li>
    </ul>
</div>

<!-- =============================================================
     1.  OVERVIEW
============================================================== -->
<div id="overview" class="tab-content active">
    <div class="container">
        <!-- KPI pills -->
        <div class="kpi-grid">
            <div class="kpi-pill">
                <div class="kpi-icon" style="background:#e0e7ff;color:var(--accent)"><i class="lni lni-cart"></i></div>
                <div class="kpi-info">
                    <div class="kpi-label">Pharmacy Sales</div>
                    <div class="kpi-val">${count}</div>
                    <div class="kpi-foot"><span class="text-success">+12 %</span> vs yesterday</div>
                </div>
            </div>
            <div class="kpi-pill">
                <div class="kpi-icon" style="background:#d1fae5;color:var(--green)"><i class="lni lni-dollar"></i></div>
                <div class="kpi-info">
                    <div class="kpi-label">Revenue Today</div>
                    <div class="kpi-val"><fmt:formatNumber value="${amount}" type="number" pattern="#,###"/> FCFA</div>
                    <div class="kpi-foot"><span class="text-success">+8 %</span> vs yesterday</div>
                </div>
            </div>
            <div class="kpi-pill">
                <div class="kpi-icon" style="background:#dbeafe;color:#3b82f6"><i class="lni lni-users"></i></div>
                <div class="kpi-info">
                    <div class="kpi-label">Patients</div>
                    <div class="kpi-val">10</div>
                    <div class="kpi-foot"><span>+2 new</span> today</div>
                </div>
            </div>
            <div class="kpi-pill">
                <div class="kpi-icon" style="background:#fee2e2;color:var(--red)"><i class="lni lni-warning"></i></div>
                <div class="kpi-info">
                    <div class="kpi-label">Pending Bills</div>
                    <div class="kpi-val">3</div>
                    <div class="kpi-foot"><span class="text-danger">-5 %</span> vs yesterday</div>
                </div>
            </div>
        </div>

        <!-- Recent Sales -->
        <div class="sales-box">
            <div style="display:flex;justify-content:space-between;align-items:center;margin-bottom:1rem">
                <h3 style="font-size:1.1rem;font-weight:500">Recent Sales</h3>
                <a href="<c:url value='/sales/today'/>" style="font-size:.75rem;color:var(--accent)">See all →</a>
            </div>
            <table class="sales-table">
                <thead>
                    <tr>
                        <th>#</th><th>Facture</th><th>Patient</th><th>Reason</th>
                        <th style="text-align:right">Net (CDF)</th><th style="text-align:center">Status</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="s" items="${recentSales}" varStatus="loop">
                        <tr>
                            <td data-label="#">${loop.index+1}</td>
                            <td data-label="Facture">${s.factureId}</td>
                            <td data-label="Patient">${s.patientName}</td>
                            <td data-label="Reason">${s.reasonName}</td>
                            <td data-label="Net" style="text-align:right">${s.netAmount}</td>
                            <td data-label="Status" style="text-align:center">
                                <span class="badge ${s.paymentStatus eq 'PAID' ? 'badge-paid' : s.paymentStatus eq 'PARTIAL' ? 'badge-partial' : 'badge-unpaid'}">
                                    ${s.paymentStatus}
                                </span>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty recentSales}">
                        <tr><td colspan="6" class="text-center" style="padding:2rem 0;color:var(--text2)">No sales recorded today</td></tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>
</div><!-- /overview -->

<!-- =============================================================
     2.  EDIT PROFILE
============================================================== -->
<div id="edit-profile" class="tab-content">
    <div class="container">
        <div class="sales-box">
            <h3 style="margin-bottom:1.2rem;font-weight:500">Edit Profile</h3>
            <form class="row g-3 needs-validation" novalidate>
                <div class="form-grid">
                    <div class="form-group">
                        <label>First name</label>
                        <input type="text" name="firstName" value="${user.staff.firstName}" required>
                    </div>
                    <div class="form-group">
                        <label>Last name</label>
                        <input type="text" name="lastName" value="${user.staff.lastName}" required>
                    </div>
                    <div class="form-group">
                        <label>Email</label>
                        <input type="email" name="email" value="${user.staff.email}" required>
                    </div>
                    <div class="form-group">
                        <label>Phone</label>
                        <input type="text" name="mobile" value="${user.staff.phone}" required>
                    </div>
                </div>
                <div class="form-grid single">
                    <div class="form-group">
                        <label>Address</label>
                        <input type="text" name="address" value="${user.staff.address}" required>
                    </div>
                    <div class="form-group">
                        <label>Photo</label>
                        <input type="file" name="imageFile" accept="image/*">
                    </div>
                </div>
                <div class="text-end" style="margin-top:1rem">
                    <button type="button" class="btn btn-success" onclick="updateStaff('${user.staff.id}'); loadPage('user/list-users')">Save</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- =============================================================
     3.  CHANGE PASSWORD
============================================================== -->
<div id="change-password" class="tab-content">
    <div class="container">
        <div class="sales-box">
            <h3 style="margin-bottom:1.2rem;font-weight:500">Change Password</h3>
            <form class="row g-3 needs-validation" novalidate>
                <div class="form-grid">
                    <div class="form-group">
                        <label>Username</label>
                        <input type="text" value="${user.username}" readonly style="background:#f3f4f6">
                    </div>
                    <div class="form-group">
                        <label>Current Password</label>
                        <input type="password" id="rpassword" required>
                    </div>
                    <div class="form-group">
                        <label>New Password</label>
                        <input type="password" id="newpassword" required>
                    </div>
                    <div class="form-group">
                        <label>Confirm Password</label>
                        <input type="password" id="rconfirmPassword" required>
                    </div>
                </div>
                <div class="text-end" style="margin-top:1rem">
                    <button type="button" class="btn btn-secondary" onclick="resetPassword()">Update</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- =========================  JS  ========================= -->
<script>
    /* ----- tab switch ----- */
    document.querySelectorAll('.tab-nav li').forEach(li=>{
        li.addEventListener('click',()=>{
            document.querySelectorAll('.tab-nav li').forEach(l=>l.classList.remove('active'));
            document.querySelectorAll('.tab-content').forEach(t=>t.classList.remove('active'));
            li.classList.add('active');
            document.getElementById(li.dataset.tab).classList.add('active');
        });
    });
</script>
<script src="assets/js/users.js"></script>
<script src="assets/js/statistics/revenue.js"></script>
</body>
</html>