var dynamicConfig = function(){
      var config = [
          {
            allowedForUrl : "/admin/messages",
            state:"app.Messages",
            urlRender:{
                        url: '/admin/messages',
                        templateUrl: 'views/admin/messages.html',
                        ncyBreadcrumb: {
                              label: 'Messages',
                              description: 'Messages',
                              url: '/admin/messages'
                        },
                        resolve: {
                              deps: [
                                  '$ocLazyLoad',
                                  function ($ocLazyLoad) {
                                      return $ocLazyLoad.load({
                                          serie: true,
                                          files: [
                                                'app/service/optionService.js',
                                                'app/controllers/admin/facilitiesformessage.js',
                                                'app/controllers/admin/messages.js',
                                                'app/controllers/seller/booknewpickup.js',
                                                'app/controllers/seller/updatebookedpickup.js',
                                                'app/service/communicator.js',
                                                'app/service/holder.js'
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
