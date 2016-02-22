(function() {
  'use strict';

  angular
  .module('hr2AngularUi')
  .config(routerConfig)
  .controller('ParentController', parentController);

  /** @ngInject */
  function routerConfig($stateProvider) {
    $stateProvider
    .state('app', {
      abstract: true,
      template: '<ui-view/>',
      controller: 'ParentController',
      controllerAs: 'vm',
      resolve: {
          userInfo: ['$rootScope', 'userinfoService', '$log', function($rootScope, userinfoService, $log) {
            if ($rootScope.preloadFinished) {
              return;
            }
            $log.debug('Preload userInfo');
            return userinfoService.getUserInfo();
          }],
          localeResource: ['$rootScope', 'userInfo', 'localeService', '$log', function($rootScope, userInfo, localeService, $log) {
            if ($rootScope.preloadFinished) {
              return;
            }
            var userLocale = userInfo.locale;
            $log.debug('Preload language resources: ' + userLocale);
            return localeService.use(userLocale);
          }],
          grantedAuthorities: ['$rootScope', 'userInfoCache', 'userInfo', function($rootScope, userInfoCache, userInfo) {
            if ($rootScope.preloadFinished) {
              return;
            }
            userInfoCache.put('grantedAuthorities', userInfo.grantedAuthorities);
          }]
        }
    });
  }

  /** @ngInject */
  function parentController($rootScope) {
    $rootScope.preloadFinished = true;
  }

})();
