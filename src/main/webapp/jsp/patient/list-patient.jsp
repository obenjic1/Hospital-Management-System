<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Patients</title>
    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: 'Segoe UI', sans-serif;
        }

        .header-bar {
            margin-top: 30px;
            margin-bottom: 20px;
        }

        .table th, .table td {
            vertical-align: middle;
        }

        .search-bar {
            max-width: 400px;
        }

        .action-btn {
            width: 100%;
        }

        .dropdown-toggle::after {
            margin-left: 0.5rem;
        }

        .table-hover tbody tr:hover {
            background-color: #f1f3f5;
        }

        @media (max-width: 768px) {
            .search-form {
                flex-direction: column;
                gap: 10px;
            }

            .search-bar {
                width: 100%;
            }

            .btn {
                width: 100%;
            }
        }
    </style>
</head>

<body class="container">

    <!-- Header & Add Button -->
    <div class="d-flex justify-content-between align-items-center header-bar">
        <h2>Patients</h2>
        <button onclick="loadPage('patients/new')" class="btn btn-outline-primary">
            <i class="bi bi-plus-circle"></i> Add Patient
        </button>
    </div>

    <!-- Search Form -->
    <form class="d-flex search-form mb-4">
        <input type="text" name="q" class="form-control search-bar" id="patientName" placeholder="Search patient..." />
        <button type="button" onclick="event.preventDefault(); searchPatient()" class="btn btn-outline-primary ms-2">
            <i class="bi bi-search"></i> Search
        </button>
    </form>

    <!-- Patients Table -->
    <div class="table-responsive">
        <table class="table table-bordered table-hover shadow-sm bg-dark text-center">
            <thead class="table-dark">
                <tr>
                    <th>#</th>
                    <th>Name</th>
                    <th>Age</th>
                    <th>Gender</th>
                    <th>Contact</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="p" items="${patients}" varStatus="loop">
                    <tr>
                        <td>${loop.index + 1}</td>
                        <td>${p.name}</td>
                        <td>${p.age}</td>
                        <td>${p.gender}</td>
                        <td>${p.contact}</td>
                        <td>
                            <div class="dropdown">
                                <button class="btn btn-outline-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    Actions
                                </button>
                                <ul class="dropdown-menu dropdown-menu-end">
                                    <li>
                                        <a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#ExtralargeModal" onclick="loadPageModalForm('appointments/new?patientId=${p.id}')">
                                            <i class="bi bi-calendar-plus"></i> Book Appointment
                                        </a>
                                    </li>
                                    <li>
                                        <a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#ExtralargeModal" onclick="loadPageModalForm('patients/view/${p.id}')">
                                            <i class="bi bi-eye"></i> View Details
                                        </a>
                                    </li>
                                    <li>
                                        <a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#ExtralargeModal" onclick="loadPageModalForm('patients/history/${p.id}')">
                                            <i class="bi bi-clock-history"></i> View History
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- Bootstrap JS (Required for dropdowns) -->
    <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>
</html>
