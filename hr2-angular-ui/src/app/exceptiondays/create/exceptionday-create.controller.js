(function() {
  'use strict';

  angular
  .module('hr2AngularUi')
  .config(routerConfig)
  .controller('ExceptionDayCreateController', exceptionDayCreateController);

  /** @ngInject */
  function routerConfig($stateProvider) {
    $stateProvider
    .state('app.exceptionday-create', {
      url: '/exceptionday/create',
      templateUrl: 'app/exceptiondays/create/exceptionday-create.html',
      controller: 'ExceptionDayCreateController',
      controllerAs: 'vm'
    });
  }

  /** @ngInject */
  function exceptionDayCreateController(exceptionDaysService, $state, $translate, toastr, EXCEPTIONDAYS) {
    var vm = this;
    vm.exceptiondays = EXCEPTIONDAYS;
    vm.kivetelnapCreateDTO = {};

    vm.create = function() {
      exceptionDaysService.createExceptionDay(vm.kivetelnapCreateDTO).then(function() {
        toastr.success($translate.instant('common.create.succeed'));
        $state.go('app.exceptiondays');
      });
    };
  }
})();
