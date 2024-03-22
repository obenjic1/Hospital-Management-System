
$(document).ready(function() {
    // Gestionnaire de clic pour le lien "List Roles"
    $('#list-roles').on('click', function() {
        loadPage('/role/list-roles');
    });

    // Gestionnaire de clic pour le lien "View Role Details"
    $('#myTable').on('click', '.button-see', function() {
        var roleName = $(this).closest('tr').find('td:nth-child(2)').text();
        var page = 'role/view-role-details/' + roleName;
        loadPage(page);
    });
});

 function refreshTable(pageNo) {
    $.ajax({
        url: 'role/page/' + pageNo,
        type: 'GET',
        success: function(data) {
            $('#table-body').html(data);
        },
        error: function() {
            alert('Une erreur s\'est produite lors du chargement de la page.');
        }
    });
}