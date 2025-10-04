<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>Facture ${facture.id}</title>
    <!-- Bootstrap CSS (optional, keeps same look) -->
    <style>
        /* ---- A4 portrait ---- */
        @media print{
            @page{size:A4;margin:10mm}
            body{margin:0}
            .no-print{display:none}
        }
        body{background:#fff;font-family:'Segoe UI',Tahoma,Geneva,Verdana,sans-serif}
        .invoice-box{max-width:800px;margin:auto;padding:30px;border:1px solid #eee;box-shadow:0 0 10px rgba(0,0,0,.15);font-size:14px;line-height:24px;color:#555}
        .invoice-box table{width:100%;line-height:inherit;text-align:left}
        .invoice-box table td{padding:5px;vertical-align:top}
        .invoice-box table tr td:nth-child(2){text-align:right}
        .invoice-box table tr.top table td{padding-bottom:20px}
        .invoice-box table tr.information table td{padding-bottom:40px}
        .invoice-box table tr.heading td{background:#eee;border-bottom:1px solid #ddd;font-weight:bold}
        .invoice-box table tr.details td{padding-bottom:20px}
        .invoice-box table tr.item td{border-bottom:1px solid #eee}
        .invoice-box table tr.item.last td{border-bottom:none}
        .invoice-box table tr.total td:nth-child(2){border-top:2px solid #eee;font-weight:bold}
        .text-end{text-align:right}
    </style>
</head>
<body>
<div class="container-fluid no-print mt-3">
    <div class="row">
        <div class="col text-end">
            <button id="downloadBtn" class="btn btn-primary mb-3">
                <i class="bi bi-download"></i> Download PDF
            </button>
        </div>
    </div>
</div>

<!--  ==========  FACTURE CONTENT  ==========  -->
<div class="invoice-box" id="factureContent">
    <!-- header -->
    <table cellpadding="0" cellspacing="0">
        <tr class="top">
            <td colspan="2">
                <table>
                    <tr>
                        <td class="title">
                            <h2 class="mb-0">FACTURE</h2>
                            <span class="text-muted">N° ${facture.id}</span>
                        </td>
                        <td class="text-end">
                            <strong>Date:</strong> <fmt:formatDate value="${facture.createdAt}" pattern="dd MMM yyyy"/><br>
                            <strong>Due Date:</strong> <fmt:formatDate value="${facture.dueDate}" pattern="dd MMM yyyy"/>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr class="information">
            <td colspan="2">
                <table>
                    <tr>
                        <td>
                            <strong>From:</strong><br>
                            <strong>Pharma-Name</strong><br>
                            123 Street, City<br>
                            +243 000 000 000<br>
                            info@pharma.com
                        </td>
                        <td class="text-end">
                            <strong>To:</strong><br>
                            ${facture.patientName}<br>
                            ${facture.patientPhone}<br>
                            ${facture.patientAddress}
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>

    <!-- lines -->
    <table cellpadding="0" cellspacing="0">
        <tr class="heading">
            <td>Item</td>
            <td>Qty</td>
            <td class="text-end">Unit Price (CDF)</td>
            <td class="text-end">Total (CDF)</td>
        </tr>
        <c:forEach var="l" items="${facture.lines}">
            <tr class="item">
                <td>${l.productName}</td>
                <td>${l.quantity}</td>
                <td class="text-end"><fmt:formatNumber value="${l.unitPrice}" pattern="#,##0.00"/></td>
                <td class="text-end"><fmt:formatNumber value="${l.total}" pattern="#,##0.00"/></td>
            </tr>
        </c:forEach>
        <tr class="total">
            <td colspan="3"></td>
            <td class="text-end">Total: <fmt:formatNumber value="${facture.netAmount}" pattern="#,##0.00"/> CDF</td>
        </tr>
    </table>

    <!-- footer -->
    <div class="mt-4 small text-muted">
        Payment status:
        <span class="badge ${facture.paymentStatus eq 'PAID' ? 'bg-success' : facture.paymentStatus eq 'PARTIAL' ? 'bg-warning' : 'bg-danger'}">
            ${facture.paymentStatus}
        </span>
    </div>
</div><!-- /factureContent -->

<!-- html2pdf.js -->
<script>
    document.getElementById('downloadBtn').addEventListener('click', () => {
        const element = document.getElementById('factureContent');
        const opt = {
            margin:       10,
            filename:     'Facture-${facture.id}.pdf',
            image:        { type: 'jpeg', quality: 0.98 },
            html2canvas:  { scale: 2 },
            jsPDF:        { unit: 'mm', format: 'a4', orientation: 'portrait' }
        };
        html2pdf().set(opt).from(element).save();
    });
</script>
<script src=""></script>
</body>
</html>