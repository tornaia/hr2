(function() {
  'use strict';

  angular
  .module('hr2AngularUi')
  .config(routerConfig)
  .controller('ValuesetEditController', valuesetEditController);

  /** @ngInject */
  function routerConfig($stateProvider) {
    $stateProvider
    .state('app.valueset-edit', {
      url: '/valueset/edit/:id',
      templateUrl: 'app/valuesets/edit/valueset-edit.html',
      controller: 'ValuesetEditController',
      controllerAs: 'vm'
    });
  }

  /** @ngInject */
  function valuesetEditController(valuesetsService, $state, $stateParams, $translate, toastr) {
    var vm = this;
    vm.ertekkeszletElemEditDTO = {};

    function getValuesetById(id) {
      valuesetsService.getValuesetById(id).then(function(ertekkeszletElemResponseDTO) {
        vm.ertekkeszletElemEditDTO = ertekkeszletElemResponseDTO;
      });
    }

    vm.edit = function() {
      valuesetsService.editValueset(vm.ertekkeszletElemEditDTO).then(function() {
        toastr.success($translate.instant('common.edit.succeed'));
        vm.valueseteditform.$setPristine();
      });
    };

    getValuesetById($stateParams.id);
  }
})();
