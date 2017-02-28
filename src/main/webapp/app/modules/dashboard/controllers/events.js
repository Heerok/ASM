app.controller("EventsController", ['$rootScope', '$scope', '$state', '$location', '$http','$modal',
function ($rootScope, $scope, $state, $location, $http,$modal) {
    var vm = this;

    $scope.curPage = 0;
     $scope.pageSize = 10;

     $scope.setCurrentPage = function(){
         $scope.curPage = 0;
     };

     $scope.numberOfPages = function() {
         $scope.totalPages = Math.ceil(($scope.TotalRecordCount) / ($scope.pageSize));
         return $scope.totalPages;
     };

     $scope.checkPage = function(pageNumber){
         if(pageNumber > $scope.totalPages){
             $("#shwErrMsg").html(" Page number is greater than total pages. ").show().fadeOut(3000);
             $scope.disableJumpButton = true;
         }else{
             $scope.disableJumpButton = false;
         }
     };

     $scope.jumpToPage = function(){
         $scope.curPage = $scope.goToPage - 1;
         $scope.goToPage = null;
     };

    $scope.init = function(){

        $http.get("/admin/events/findAll").then(function(res){
            $scope.events = res.data;
            $scope.TotalRecordCount = res.data.length;
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

