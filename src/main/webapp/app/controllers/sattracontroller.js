app.controller('SattraController', function ($scope, $http,urls) {

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

        $http.get(urls.BASE_API + "/admin/sattra/findAll").then(function(res){
            $scope.events = res.data;
            $scope.TotalRecordCount = res.data.length;
        });
    }

    $scope.init();

});