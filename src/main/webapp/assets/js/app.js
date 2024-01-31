//
//function loadPage(page) {
//    fetch(page, {
//        headers: {
//            'Content-Type': 'text/html'
//        }
//    })
//        .then(response => response.text())
//        .then(html => {
//            document.getElementById('main').innerHTML = html;
//        })
//        .catch(error => console.log(error));
//}
//
//// Événements de clic pour charger les différentes pages
//document.getElementById('main').addEventListener('click', function() {
//    loadPage('user/list-roles.jsp');
//});