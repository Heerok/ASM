app.controller("ProductsDataController", ['$rootScope', '$scope', '$state', '$location', '$http','$modal',
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

        $http.get("/api/pricing/listAllProductPrices").then(function(res){
            $scope.list = res.data;
            $scope.TotalRecordCount = res.data.length;
        });

    }

    $scope.calcLoading = false;

    $scope.scrap = function(code){
        $http.get("/api/pricing/scrap?productCode="+code).then(function(){
            $http.get("/api/pricing/listProductPrices?productCode="+code).then(function(res){
                if(res.data){
                    var index=$scope.list.findIndex(p=>p.code==code);
                    if(index>0)
                    $scope.list[index]=res.data;
                    $.notify("Scraping Completed","success");
                }
            });
        });
    }

    $scope.scrapAll = function(){
        $http.get("/api/pricing/scrapAll").then(function(){
            $.notify("Scraping Completed","success");
        });
    }

    $scope.init();

    $scope.viewPrices = function(e) {
        $scope.modal = e;
        $scope.modalInstance = $modal.open({
            templateUrl: 'productDataModal',
            size: 'md',
            scope: $scope
        });
    }

    $scope.propertyName = 'date';
    $scope.reverse = true;
    $scope.sortBy = function(propertyName) {
        $scope.reverse = ($scope.propertyName === propertyName) ? !$scope.reverse : false;
        $scope.propertyName = propertyName;
    };


}]);

