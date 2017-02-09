app.controller('ArticleController', function ($scope, $http,urls) {

    $scope.init = function(){

        $scope.englishArticles=[];
        $scope.assameseArticles=[];
        $scope.hindiArticles=[];
        $http.get(urls.BASE_API + "/admin/articles/English/findAllActiveByLang").then(function(res){
            $scope.englishArticles = res.data;
        });
        $http.get(urls.BASE_API + "/admin/articles/Hindi/findAllActiveByLang").then(function(res){
            $scope.hindiArticles = res.data;
        });
        $http.get(urls.BASE_API + "/admin/articles/Assamese/findAllActiveByLang").then(function(res){
            $scope.assameseArticles = res.data;
        });
    }

    $scope.init();

});