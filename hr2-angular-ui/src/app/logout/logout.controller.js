(function() {
  'use strict';

  angular
  .module('hr2AngularUi')
  .config(routerConfig)
  .controller('LogoutController', LogoutController);

  /** @ngInject */
  function routerConfig($stateProvider) {
    $stateProvider
    .state('app.logout', {
      url: '/logout',
      templateUrl: 'app/logout/logout.html',
      controller: 'LogoutController',
      controllerAs: 'vm'
    });
  }

  /** @ngInject */
  function LogoutController(logoutService, $window) {
    logoutService.logout().then(function() {
      $window.location.href = '/';
    });
  }

})();
