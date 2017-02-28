app.controller("SattraController", ['$scope','$http','$modal',
function ($scope, $http,$modal) {
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

        $http.get("/admin/sattra/findAll").then(function(res){
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

    $scope.addArticles = function(e){

        $http.get("/admin/articles/findAll").then(function(res){
            $scope.articles = res.data;
        });
        $scope.event = e;
        $scope.modalInstance = $modal.open({
            templateUrl: 'articleModal',
            size: 'md',
            scope: $scope
        });
    }

    $scope.AddArticle = function(a){
        $http.post("/admin/sattra/addArticle?sattraId="+$scope.event.id+"&articleId="+a).then(function(res){
            console.log(JSON.stringify(res));
            if(res.data.status){
                $.notify(res.data.message,"success");
            }else{
                $.notify(res.data.message,"error");
            }
            $scope.modalInstance.close();
            $scope.init();
        });
    }

    $scope.viewArticle = function(e){
        $scope.event = e;
        $scope.articles = e.articles;
        $scope.modalInstance = $modal.open({
            templateUrl: 'viewArticleModal',
            size: 'md',
            scope: $scope
        });
    }

    $scope.deleteArticle = function(articleId){
        $http.post("/admin/sattra/deleteArticle?sattraId="+$scope.event.id+"&articleId="+articleId).then(function(res){
            console.log(JSON.stringify(res));
            if(res.data.status){
                $.notify(res.data.message,"success");
            }else{
                $.notify(res.data.message,"error");
            }
            $scope.modalInstance.close();
            $scope.init();
        });
    }


}]);

