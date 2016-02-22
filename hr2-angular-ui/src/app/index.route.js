(function() {
  'use strict';

  angular
    .module('hr2AngularUi')
    .config(routerConfig);

  /** @ngInject */
  function routerConfig($urlRouterProvider) {
    $urlRouterProvider.otherwise('/dashboard');
  }

})();
