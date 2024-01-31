
 //User not foun exception
function loadPage(page) {
    fetch(page)
        .then(response => response.text())
        .then(html => {
            document.getElementById('main-content').innerHTML = html;
        })
        .catch(error => console.log(error));
}

document.getElementById('user-not-found').addEventListener('click', function() {
    loadPage('/findUser/{username}'); 
});
