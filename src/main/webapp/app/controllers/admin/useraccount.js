'use strict';

app.controller('UserAccountController', function ($scope, $http, $location, Auth) {

            $http({
                   method: 'GET',
                   url: '/api/loggedinuser',
            }).success(function(data){
                    $scope.logoPath = "assets/img/orgLogo/humaralab_logo.png";
                    if(data.orgName=="vulcan")
                        //$scope.logoPath = "assets/img/orgLogo/vulcan-logo.png";
                        $scope.logoPath = "assets/img/orgLogo/humaralab_logo.png";
                    if(data.orgName=="Meratask")
                        $scope.logoPath = "assets/img/orgLogo/meratask-logo.png";
                    if(data.orgName=="Innovex")
                        $scope.logoPath = "assets/img/orgLogo/humaralab_logo.png";
                    if(data.orgName=="Streetwise Logistic")
                        $scope.logoPath = "assets/img/orgLogo/streetwise-logo.png";
                    $scope.roleHypCustomer = false;
                    $http.get('/api/role/loadCurrentUserRoles').then(function(response){
                        var roleList = response.data;
                        if(roleList){
                            for(var i=0;i<roleList.length;i++){
                                if(roleList[i].name=='HYPCUSTOMER'){
                                    $scope.roleHypCustomer = true;
                                    break;
                                }
                            }
                        }
                    });
                    $scope.user = data;
            }).error(function(){
                alert("hey there is error in this page");
            });

            $scope.logout = function () {
                Auth.logout();
            };
});