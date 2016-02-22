(function() {
  'use strict';

  angular
  .module('hr2AngularUi')
  .config(routerConfig)
  .controller('ValuesetCreateController', valuesetCreateController);

  /** @ngInject */
  function routerConfig($stateProvider) {
    $stateProvider
    .state('app.valueset-create', {
      url: '/valueset/create',
      templateUrl: 'app/valuesets/create/valueset-create.html',
      controller: 'ValuesetCreateController',
      controllerAs: 'vm'
    });
  }

  /** @ngInject */
  function valuesetCreateController(valuesetsService, $state, $translate, toastr, VALUESET_TYPES) {
    var vm = this;
    vm.valuesetTypes = VALUESET_TYPES;
    vm.ertekkeszletElemCreateDTO = {};

    vm.create = function() {
      valuesetsService.createValueset(vm.ertekkeszletElemCreateDTO).then(function() {
        toastr.success($translate.instant('common.create.succeed'));
        $state.go('app.valuesets');
      });
    };
  }
})();
