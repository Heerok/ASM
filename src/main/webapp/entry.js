// /js/entry.js
// Expose any global variables you need for your application
window.$ = window.jQuery = require('./js/jquery.min');

// Include all of your Angular modules
require('./js/bootstrap.min');
require('./lib/angular/angular.min');
require('./js/lodash');
require('./lib/utilities');
require('./lib/angular/angular-dndLists/angular-drag-and-drop-lists');
require('./lib/angular/angular-animate/angular-animate');
require('./lib/angular/angular-cookies/angular-cookies');
require('./lib/angular/angular-resource/angular-resource');
require('./lib/angular/angular-sanitize/angular-sanitize');
require('./lib/angular/angular-touch/angular-touch');
require('./lib/angular/angular-dimple/angular-dimple');
require('./lib/angular/angular-ui-router/angular-ui-router.min');
require('./lib/angular/angular-ocLazyLoad/ocLazyLoad');
require('./lib/angular/angular-ngStorage/ngStorage');
require('./lib/angular/angular-ui-utils/angular-ui-utils');
require('./lib/angular/angular-breadcrumb/angular-breadcrumb');
require('./lib/angular/angular-route/angular-route');

require('./lib/angular/angular-ui-bootstrap/ui-bootstrap');
require('./lib/jquery/slimscroll/jquery.slimscroll');

require('./js/select-oi');

// Main Application
require('./app/app.js');
require('./app/config.js');
require('./app/config.lazyload.js');
require('./app/config.data.js');
require('./app/config.router.js');
require('./app/beyond.js');
//require('./app/controllers/facilityfilter.js');
require('./app/controllers/menucontroller.js');


require('./app/directives/loading.js');
require('./app/directives/skin.js');
require('./app/directives/sidebar.js');
require('./app/directives/header.js');
require('./app/directives/navbar.js');
require('./app/directives/jtable.js');
require('./app/directives/widget.js');


// from http://rok3.me/programming/managing-angular-dependencies-webpack/