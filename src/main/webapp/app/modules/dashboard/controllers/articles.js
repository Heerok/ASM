dashboard.controller("ArticlesController", ['$rootScope', '$scope', '$state', '$location', 'dashboardService', 'Flash','$http','$modal',
function ($rootScope, $scope, $state, $location, dashboardService, Flash,$http,$modal) {
    var vm = this;

    $scope.init = function(){

        $http.get("/admin/articles/findAll").then(function(res){
            vm.articles = res.data;
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
            size: 'md',
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
        fd.append('active',e.active);
        if(e.id){
            fd.append('Id',e.id);
        }
        if(e.lang){
            fd.append('language',e.lang);
        }
        $http.post(url, fd, {
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
        $http.delete("/admin/articles/delete?id="+id).then(function(res){
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
