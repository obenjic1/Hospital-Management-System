/**
 * 
 * @BIKADAI RADIUS
 */
function registerServiceWorker () {
  if (!navigator.serviceWorker) ;
  navigator.serviceWorker.register('./sw.js').then(function(reg) {
    if (!navigator.serviceWorker.controller) {
      return;
    }

    if (reg.waiting) {
      updateReady(reg.waiting);
      return;
    }

    if (reg.installing) {
       trackInstalling(reg.installing);
      return;
    }

    reg.addEventListener('updatefound', function() {
       trackInstalling(reg.installing);
    });
  });

  var refreshing;
  navigator.serviceWorker.addEventListener('controllerchange', function() {
    if (refreshing) return;
    window.location.reload();
    refreshing = true;
  });
}

function trackInstalling (worker) {
  worker.addEventListener('statechange', function() {
    if (worker.state === 'installed') {
     updateReady(worker);
    }
  });
}

function updateReady (worker) {
 
    if(confirm("Please we have some update, accept it; /Des mises à  jour sont disponibles, cliquez sur Ok"))
    worker.postMessage({action: 'skipWaiting'}); 
    else  worker.postMessage({action: 'skipWaiting'}); 

 }

