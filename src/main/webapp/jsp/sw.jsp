<%@ page language="java" contentType="application/javascript; charset=UTF-8" pageEncoding="UTF-8"%>

/**
 * 
 *RADIUS BIKADAI 
 */


var staticCacheName = 'ppp_billing_v2';
var allCaches = [staticCacheName];
self.addEventListener('install', function(event) {
  event.waitUntil(
    caches.open(staticCacheName).then(function(cache) {
      return cache.addAll([
       ]);
    })
  );
});

self.addEventListener('activate', function(event) {
  event.waitUntil(
    caches.keys().then(function(cacheNames) {
      return Promise.all(
        cacheNames.filter(function(cacheName) {
          return cacheName.startsWith('ppp_ssdt-worker-') &&
                 !allCaches.includes(cacheName);
        }).map(function(cacheName) {
          return caches.delete(cacheName);
        })
      );
    })
  );
});

self.addEventListener('fetch', function(event) {
    event.respondWith(
   caches.open(staticCacheName).then(function(cache) {
      return cache.match(event.request).then(function(response){
          if(response) return response;
              return fetch(event.request).then(function(response){
                  if(event.request.url.endsWith("schooling/") || event.request.url.endsWith(".css") ||event.request.url.endsWith(".jpg") || event.request.url.endsWith(".png") ||event.request.url.endsWith(".js"))
                  cache.put(event.request,response.clone());
                  return response;
          })
      })
   }
   )
  );
});

self.addEventListener('message', function(event) {
  if (event.data.action === 'skipWaiting') {
    self.skipWaiting();
  }
});
