app.controller("ArticlesController", ['$rootScope', '$scope', '$state', '$location','$http','$modal','urls',
function ($rootScope, $scope, $state, $location, $http,$modal,urls) {
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

        $http.get(urls.BASE_API + "/admin/articles/findAll").then(function(res){
            $scope.articles = res.data;
        });

        $scope.uploadTypes = [{Code:"Assamese", Value:"Assamese"},
            {Code:"English", Value:"English"},
            {Code:"Hindi", Value:"Hindi"}];


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
            size: 'lg',
            scope: $scope
        });
    }

    $scope.save = function(e,file){
        console.log(JSON.stringify(e));
        //todo active inactive

        if($scope.update=="Add"){
            url="/admin/articles/save";
        }else{
            url="/admin/articles/update";
        }
        var fd = new window.FormData();
        if(file){
            fd.append('file', file);
        }
        if(e.author){
            fd.append('author',e.author);
        }
        if(e.name){
            fd.append('name',e.name);
        }
        if(e.articleString){
            fd.append('articleString',e.articleString);
        }
        fd.append('active',e.active);
        if(e.id){
            fd.append('Id',e.id);
        }
        if(e.language){
            fd.append('language',e.language);
        }
        $http.post(urls.BASE_API + url, fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        })
        .success(function(data){
            $scope.init();
        })
        .error(function(data){
            alert("Missing Input");
        });

        $scope.modalInstance.close();
    }

    $scope.viewFile = function(e){
        $scope.pdfFile = "articles/"+e.filePath;
        $scope.modalInstance = $modal.open({
            templateUrl: 'pdf',
            size: 'md',
            scope: $scope
        });
    }

    $scope.delete = function(id){
        $http.delete(urls.BASE_API + "/admin/articles/delete?id="+id).then(function(res){
            $scope.init();
        });
    }



}]);

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
