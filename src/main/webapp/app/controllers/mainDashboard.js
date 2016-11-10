'use strict'

app.controller("mainDashboardController", function($scope, $http){
    $scope.dasboardRole1='';$scope.dasboardRole2='';$scope.dasboardRole3='';
    $http.post('/api/admin/usermanagement/userrole').success(function(data) {
            $scope.currentUserRoles = data;
            data.Records = data;
            for(var i=0;i<data.Records.length;i++){
            if(data.Records[i].name=='FACILITYADMIN'||data.Records[i].name=='SUPERVISOR'||data.Records[i].name=='SUPERUSER'||data.Records[i].name=='CREW'||
            data.Records[i].name=='ORGADMIN'||data.Records[i].name=='TRANSPORTADMIN'||data.Records[i].name=='RESOURCEADMIN'||data.Records[i].name=='RESOURCEAPPROVER'
            ||data.Records[i].name=='RESOURCEBUYER'){
             $scope.dasboardRole1='mainDashboard';
            }
            if(data.Records[i].name=='CUSTOMERCARE'){$scope.dasboardRole2='careDashboard';}
            if(data.Records[i].name=='SELLER'){$scope.dasboardRole3='sellerDashboard';}
            if(data.Records[i].name=='FieldOperation'){$scope.dasboardRole4='fieldOperationDashboard';}
            if(data.Records[i].name=='HYPCUSTOMER'){$scope.dasboardRole5='CRMDashboard';}
            };
    });

});