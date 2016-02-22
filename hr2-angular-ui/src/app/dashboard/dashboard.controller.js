(function() {
  'use strict';

  angular
  .module('hr2AngularUi')
  .config(routerConfig)
  .controller('DashboardController', DashboardController);

  /** @ngInject */
  function routerConfig($stateProvider) {
    $stateProvider
    .state('app.dashboard', {
      url: '/dashboard',
      templateUrl: 'app/dashboard/dashboard.html',
      controller: 'DashboardController',
      controllerAs: 'vm'
    });
  }

  /** @ngInject */
  function DashboardController(applicationInfoService, moment) {
    var vm = this;

    getApplicationInfo();

    function getApplicationInfo() {
      applicationInfoService.getApplicationInfo().then(function(response) {
        vm.applicationInfo = response;
        vm.applicationInfo.startDateTime = moment(response.startDateTime).fromNow();
      });
    }

  }
})();
