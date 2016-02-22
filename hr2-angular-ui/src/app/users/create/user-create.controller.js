(function() {
  'use strict';

  angular
  .module('hr2AngularUi')
  .config(routerConfig)
  .controller('UserCreateController', userCreateController);

  /** @ngInject */
  function routerConfig($stateProvider) {
    $stateProvider
    .state('app.user-create', {
      url: '/user/create',
      templateUrl: 'app/users/create/user-create.html',
      controller: 'UserCreateController',
      controllerAs: 'vm'
    });
  }

  /** @ngInject */
  function userCreateController(usersService, personalDataService, $state, $translate, toastr, ROLES, LOCALES) {
    var vm = this;
    vm.roles = ROLES;
    vm.locales = LOCALES;
    vm.idsAndNames = [];
    vm.felhasznaloCreateDTO = {};

    function getIdAndNameList() {
      personalDataService.getIdAndNameList(true).then(function(tszekEsNevekDTO) {
        vm.idsAndNames = tszekEsNevekDTO.tszekEsNevek;
      });
    }

    vm.roleChanged = function(newRole) {
      vm.felhasznaloEditDTO.tsz = newRole === 'DOLGOZO' ? vm.felhasznaloEditDTO.tsz : undefined;
    };

    vm.create = function() {
      usersService.createUser({nev: vm.felhasznaloCreateDTO.nev, szerep: vm.felhasznaloCreateDTO.szerep, jelszo: vm.felhasznaloCreateDTO.jelszo, tsz: vm.felhasznaloCreateDTO.tsz, enabled: vm.felhasznaloCreateDTO.enabled, locale: vm.felhasznaloCreateDTO.locale}).then(function() {
        toastr.success($translate.instant('common.create.succeed'));
        $state.go('app.user-edit', {nev: vm.felhasznaloCreateDTO.nev});
      });
    };

    getIdAndNameList();
  }
})();
