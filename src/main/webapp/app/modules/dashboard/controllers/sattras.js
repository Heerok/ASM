dashboard.controller("SattraController", ['$rootScope', '$scope', '$state', '$location', 'dashboardService', 'Flash','$http','$modal',
function ($rootScope, $scope, $state, $location, dashboardService, Flash,$http,$modal) {
    var vm = this;

    $scope.currentPage = 1;
    $scope.numPerPage = 10
      ,$scope.maxSize = 5;

    $scope.init = function(){

        $http.get("/admin/sattra/findAll").then(function(res){
            vm.events = res.data;
    $scope.$watch('currentPage + numPerPage', function() {
        var begin = (($scope.currentPage - 1) * $scope.numPerPage)
        , end = begin + $scope.numPerPage;

        $scope.filteredTodos = vm.events.slice(begin, end);
      });
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
        $http.post("/admin/sattra/save",e).then(function(res){
            console.log("saved==");
            $scope.init();
        });
        $scope.modalInstance.close();
    }

    $scope.delete = function(id){
        $http.delete("/admin/sattra/delete?id="+id).then(function(res){
            $scope.init();
        });
    }


}]);

