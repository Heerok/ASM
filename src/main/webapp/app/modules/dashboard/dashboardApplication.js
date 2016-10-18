var dashboard = angular.module('dashboard', ['ui.router', 'ngAnimate','ngMaterial']);


dashboard.config(["$stateProvider", function ($stateProvider) {

    //dashboard home page state
    $stateProvider.state('app.dashboard', {
        url: '/dashboard',
        templateUrl: 'app/modules/dashboard/views/home.html',
        controller: 'HomeController',
        controllerAs: 'vm',
        data: {
            pageTitle: 'Home'
        }
    });

    //events page state
    $stateProvider.state('app.events', {
        url: '/events',
        templateUrl: 'app/modules/dashboard/views/events.html',
        controller: 'EventsController',
        controllerAs: 'vm',
        data: {
            pageTitle: 'Events'
        }
    });

    //satras page state
    $stateProvider.state('app.satras', {
        url: '/satras',
        templateUrl: 'app/modules/dashboard/views/satras.html',
        controller: 'SattraController',
        controllerAs: 'vm',
        data: {
            pageTitle: 'Satra List'
        }
    });

    //articles page state
    $stateProvider.state('app.articles', {
        url: '/articles',
        templateUrl: 'app/modules/dashboard/views/articles.html',
        controller: 'ArticlesController',
        controllerAs: 'vm',
        data: {
            pageTitle: 'Articles'
        }
    });


}]);

