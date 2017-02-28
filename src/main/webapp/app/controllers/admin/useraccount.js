'use strict';

app.controller('UserAccountController', function ($scope, $http, $location, Auth) {

            $scope.logout = function () {
                Auth.logout();
            };
});