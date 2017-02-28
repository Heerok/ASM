/**
 * @author v.lugovsky
 * created on 16.12.2015
 */
(function () {
  'use strict';

  angular.module('BlurAdmin.pages.login', [])
      .config(routeConfig);

  /** @ngInject */
    function routeConfig($stateProvider) {
        $stateProvider
            .state('signin', {
                url: '/signin',
                templateUrl: 'app/pages/signin/signin.html',
                controller: 'LoginCtrl'

            })


        .state('forgotpwd', {
            url: '/signin/forgot',
            templateUrl: 'app/pages/signin/forgotpwd.html',
            controller: 'LoginCtrl'

        })

        .state('confirm', {
            url: '/signin/confirm',
            templateUrl: 'app/pages/signin/confirm.html',
            controller: 'LoginCtrl'


        });
    }
})();
