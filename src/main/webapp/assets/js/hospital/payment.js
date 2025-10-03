
function payFacture() {
    event.preventDefault();
    const amountPaid = document.getElementById("amountPaid").value;
    const factureId = document.getElementById("factureId").value;
    const paymentMethod = document.getElementById("paymentMethod").value;

    if (!paymentMethod) {
        Swal.fire('Choose payment method', '', 'warning');
        return;
    }

    const fd = new FormData();
    fd.append('factureId', factureId);
    fd.append('amountPaid', amountPaid);
    fd.append('paymentMethod', paymentMethod);

    fetch('payments', { method: 'POST', body: fd })
        .then(r => {
            if (!r.ok) throw r;
            return r.text();              
        })
        .then(ref => {
            Swal.fire("Success!", "Payment " + ref + " recorded", "success");
            const modalEl = document.getElementById('ExtralargeModal');
            bootstrap.Modal.getInstance(modalEl).hide();

            /*  open receipt in new tab  */
            window.open('payments/receipt/' + ref, '_blank');

            /*  refresh list  */
            loadPage('factures');
        })
        .catch(err => err.text().then(t => Swal.fire({ icon: "error", title: "Oops...", text: t })));
}