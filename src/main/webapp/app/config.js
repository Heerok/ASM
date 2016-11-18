var app =
    angular.module('app')
        .config(
        [
            '$controllerProvider', '$compileProvider', '$filterProvider', '$provide', '$httpProvider',
            function ($controllerProvider, $compileProvider, $filterProvider, $provide, $httpProvider) {
                app.controller = $controllerProvider.register;
                app.directive = $compileProvider.directive;
                app.filter = $filterProvider.register;
                app.factory = $provide.factory;
                app.service = $provide.service;
                app.constant = $provide.constant;
                app.value = $provide.value;

                $httpProvider.interceptors.push(['$q', '$location', '$localStorage', 'jwtHelper',
                    function ($q, $location, $localStorage, jwtHelper) {
                        return {
                            'request': function (config) {
                                config.headers = config.headers || {};
                                if ($localStorage.token) {
                                    config.headers.Authorization = 'Bearer ' + $localStorage.token;
                                    // console.log($location + ' Adding ' +  $localStorage.token);
                                }
                                return config;
                            },
                            'responseError': function (response) {
                                //do not redirect on app api rejects @ 403 and they may be due to
                                //autherisation error, not authentication error
                                if (response.status === 401) {
                                    $location.path('/login');
                                }
                                return $q.reject(response);
                            }
                        };
                    }]);

            }
        ]);


app.config(function ($breadcrumbProvider) {
    $breadcrumbProvider.setOptions({
        template: '<ul class="breadcrumb"><li><i class="fa fa-home"></i><a href="#">Home</a></li><li ng-repeat="step in steps" ng-class="{active: $last}" ng-switch="$last || !!step.abstract"><a ng-switch-when="false" href="{{step.ncyBreadcrumbLink}}">{{step.ncyBreadcrumbLabel}}</a><span ng-switch-when="true">{{step.ncyBreadcrumbLabel}}</span></li></ul>'
    });
});

   /* if(localStorage.getItem('ngStorage-token') !=null){
        $.ajaxSetup({
            beforeSend: function (xhr)
            {
                if(localStorage.getItem('ngStorage-token')){
                   xhr.setRequestHeader("Authorization","Bearer " + localStorage.getItem('ngStorage-token').slice(1,-1));
                }
            }
        });
    }*/
