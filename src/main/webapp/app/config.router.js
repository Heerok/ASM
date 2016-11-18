'use strict';
var userAllowedActions;
var $stateProviderRef;
var $urlRouterProviderRef;

var moduleApp = angular.module('app');
moduleApp.run(
    [
        '$rootScope', '$state', '$stateParams', '$http',
        function ($rootScope, $state, $stateParams, $http) {
            $rootScope.$state = $state;
            $rootScope.$stateParams = $stateParams;
        }
    ])
    .config(
        [
            '$stateProvider', '$urlRouterProvider', '$locationProvider',
            function ($stateProvider, $urlRouterProvider, $locationProvider) {

                $urlRouterProvider.otherwise("/app/user/profile");

                $stateProvider
                    .state('app', {
                        abstract: true,
                        url: '/app',
                        templateUrl: 'layout.html'
                    })
                    .state('login', {
                        url: "/login",
                        controller: 'LoginController',
                        templateUrl: "login.html",
                        ncyBreadcrumb: {
                            label: 'Login'
                        }
                    })
                    .state('app.dashboard', {
                        url: '/dashboard',
                        templateUrl: 'views/dashboard.html',
                        ncyBreadcrumb: {
                            label: 'Dashboard'
                        }
                    })
                    .state('app.pwdChange', {
                        url: '/pwdChange',
                        templateUrl: 'views/passwordchange.html',
                        ncyBreadcrumb: {
                            label: 'Change Password'
                        }
                    })
                    .state('error404', {
                        url: '/error404',
                        templateUrl: 'views/error-404.html',
                        ncyBreadcrumb: {
                            label: 'Error 404 - The page not found'
                        }
                    })
                    .state('error500', {
                        url: '/error500',
                        templateUrl: 'views/error-500.html',
                        ncyBreadcrumb: {
                            label: 'Error 500 - something went wrong'
                        }
                    })

                    .state('app.blank', {
                        url: '/blank',
                        templateUrl: 'views/blank.html',
                        ncyBreadcrumb: {
                            label: 'Blank Page'
                        }
                    });
                $urlRouterProviderRef = $urlRouterProvider;
                $stateProviderRef = $stateProvider;
            }
        ]
    );
moduleApp.run(['$q', '$rootScope', '$http', '$urlRouter',
    function ($q, $rootScope, $http, $urlRouter) {
        var config = dynamicConfig();
        var index = 0;
        for (index = 0; index < config.length; index++) {
            console.log("Adding state", config[index].state);
            $stateProviderRef.state(config[index].state, config[index].urlRender);
        }
        $urlRouter.sync();
        $urlRouter.listen();

        $rootScope.$state.go("app.dashboard");
    }
]);