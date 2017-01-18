app.directive('fileModel', ['$parse', function ($parse) {
   return {
       restrict: 'A',
       link: function(scope, element, attrs) {
           var model = $parse(attrs.fileModel);
           var modelSetter = model.assign;

           element.bind('change', function(){
               scope.$apply(function(){
                   modelSetter(scope, element[0].files[0]);
               });
           });
       }
   };
}]);
app.service('fileUpload', ['$http', function ($http) {
    var service = {};
    service.uploadFileToUrl = function(file,uploadUrl,dryRun,sync){
        var fd = new window.FormData();
        fd.append('file', file);
        fd.append('dryrun',dryRun);
        $http.post(uploadUrl, fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        })
            .success(function(data){
                sync(data)
            })
            .error(function(data){
                sync(data)
                alert("Missing Input");
            });
    };
    return service;
}]);

app.controller("ScrapingDataController", ['$rootScope', '$scope', '$state', '$location', '$http','$modal','fileUpload',
function ($rootScope, $scope, $state, $location, $http,$modal,fileUpload) {
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

        $http.get("/api/pricing/listData").then(function(res){
            $scope.list = res.data;
            $scope.TotalRecordCount = res.data.length;
        });

    }

    $scope.init();

    $scope.openUploadModal = function() {
        $scope.modalInstance = $modal.open({
            templateUrl: 'uploadDataModal',
            size: 'md',
            scope: $scope
        });
    }

    $scope.modal = {};
    $scope.modal.enable = true;
    $scope.modal.dryRun = false;
    $scope.modal.result = null;
    $scope.modal.BatchResults = [];

    $scope.uploadWork = function(){
        $scope.modal.enable = false;
        $scope.modal.result = null;
        var file = $scope.modal.myFile;
        var uploadUrl = '/api/pricing/upload'
        fileUpload.uploadFileToUrl(file,uploadUrl,$scope.modal.dryRun,function(sync){
            if(sync!=null){
                if($scope.modal.dryRun){
                    $scope.modal.showBatch = false;
                }else{
                    $scope.modal.showBatch = true;
                }
                $scope.modal.result = sync;
                if(sync!=null)
                    $scope.modal.BatchResults = sync.results;
            }
            $scope.modal.enable = true;
        });
    };


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

