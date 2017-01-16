var dynamicConfig = function(){
      var config = [
          {
            allowedForUrl : "/admin/sattra",
            state:"app.Sattra List",
            urlRender:{
                        url: '/admin/sattra',
                        templateUrl: 'app/modules/dashboard/views/satras.html',
                        ncyBreadcrumb: {
                              label: 'Sattra List',
                              description: 'Sattra List'
                        },
                        resolve: {
                              deps: [
                                  '$ocLazyLoad',
                                  function ($ocLazyLoad) {
                                      return $ocLazyLoad.load({
                                          serie: true,
                                          files: [
                                                'app/modules/dashboard/controllers/sattras.js',
                                            '/app/directives/dirPagination.js'
                                          ]
                                      });
                                  }
                              ]
                        }
                     }
          },{
              allowedForUrl : "/admin/articles",
              state:"app.Articles",
              urlRender:{
                          url: '/admin/articles',
                          templateUrl: 'app/modules/dashboard/views/articles.html',
                          ncyBreadcrumb: {
                                label: 'Articles',
                                description: 'Articles'
                          },
                          resolve: {
                                deps: [
                                    '$ocLazyLoad',
                                    function ($ocLazyLoad) {
                                        return $ocLazyLoad.load({
                                            serie: true,
                                            files: [
                                                  '/app/directives/dirPagination.js',
                                                  'app/modules/dashboard/controllers/articles.js'
                                            ]
                                        });
                                    }
                                ]
                          }
                       }
            },{
                allowedForUrl : "/admin/events",
                state:"app.Events",
                urlRender:{
                           url: '/admin/events',
                           templateUrl: 'app/modules/dashboard/views/events.html',
                           ncyBreadcrumb: {
                                 label: 'Events',
                                 description: 'Events'
                           },
                           resolve: {
                                 deps: [
                                     '$ocLazyLoad',
                                     function ($ocLazyLoad) {
                                         return $ocLazyLoad.load({
                                             serie: true,
                                             files: [
                                                   'app/modules/dashboard/controllers/events.js',
                                            '/app/directives/dirPagination.js'
                                             ]
                                         });
                                     }
                                 ]
                           }
                        }
            },{
                 allowedForUrl : "/pricing/products",
                 state:"app.Products",
                 urlRender:{
                            url: '/pricing/products',
                            templateUrl: 'app/modules/dashboard/views/products.html',
                            ncyBreadcrumb: {
                                  label: 'Events',
                                  description: 'Events'
                            },
                            resolve: {
                                  deps: [
                                      '$ocLazyLoad',
                                      function ($ocLazyLoad) {
                                          return $ocLazyLoad.load({
                                              serie: true,
                                              files: [
                                                    'app/modules/dashboard/controllers/events.js',
                                             '/app/directives/dirPagination.js'
                                              ]
                                          });
                                      }
                                  ]
                            }
                         }
             }

      ];
      return config;
};
