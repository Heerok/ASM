'use strict';
var readOnlyMap = {};
var app = angular.module('app', ['ngRoute',
    'dndLists',
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngSanitize',
    'ngTouch',
    'ngStorage',
    'ui.router',
    'ncy-angular-breadcrumb',
    'ui.bootstrap',
    'ui.utils',
    'oc.lazyLoad',
    'angular-dimple',
    'DragNDrop',
    'oi.select',
    'angular-jwt',
    'ngJsonExportExcel',
    'google.places',
    'xeditable'
]);

app.run(function ($rootScope,  $location, $localStorage, $http, jwtHelper) {
    $rootScope.$on('$locationChangeStart', function (event, next, current) {

        console.log(event);
        var restrictedPage = $.inArray($location.path(), ['/login', '/register', '/forgotPassword', '/resetPassword']) === -1;
        var loggedIn = $localStorage.token;
        if (restrictedPage && !loggedIn) {
            $location.path('/login');
        }
    });
});
