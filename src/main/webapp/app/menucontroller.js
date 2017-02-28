'use strict';
//angular.module('fieldiqMenu', []).controller('menuCtrl', function($scope) {
app.controller('menuCtrl', function($scope, $http) {
  $http.get('/api/v2/menu').then(function(response){
        $scope.roleHypCustomer = false;
        var menus = response.data.submenu;
        /*$http.get('/api/role/loadCurrentUserRoles').then(function(response){
            var roleList = response.data;
            if(roleList){
                for(var i=0;i<roleList.length;i++){
                    if(roleList[i].name=='HYPCUSTOMER' && roleList.length==1){
                        $scope.roleHypCustomer = true;
                        break;
                    }
                }
            }
            if($scope.roleHypCustomer){
                for(var a=0;a<menus.length;a++){
                    if(menus[a].label=='HyperLocal'){
                        menus[a].label = 'Book Order';
                        break;
                    }
                }
            }
            $scope.menus = menus;
        });*/
        $scope.menus = menus;
    });


});