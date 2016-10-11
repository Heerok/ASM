dashboard.controller("EventsController", ['$rootScope', '$scope', '$state', '$location', 'dashboardService', 'Flash','$http','$modal',
function ($rootScope, $scope, $state, $location, dashboardService, Flash,$http,$modal) {
    var vm = this;

    $scope.init = function(){

        $http.get("/admin/events/findAll").then(function(res){
            vm.events = res.data;
        });

    }

    $scope.init();

    $scope.edit = function(e){
        $scope.event = e;
        if(!e || !e.id){
            $scope.update = "Add";
        }else{
            $scope.update = "Edit";
        }
        $scope.modalInstance = $modal.open({
            templateUrl: 'editEventModal',
            size: 'md',
            scope: $scope
        });
    }

    $scope.save = function(e){
        console.log(JSON.stringify(e));
        $http.post("/admin/events/save",e).then(function(res){
            console.log("saved==");
            $scope.init();
        });
        $scope.modalInstance.close();
    }

    $scope.delete = function(id){
        $http.delete("/admin/events/delete?id="+id).then(function(res){
            $scope.init();
        });
    }


}]);

