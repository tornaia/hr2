(function() {
  'use strict';

  angular
  .module('hr2AngularUi')
  .config(routerConfig)
  .controller('UserEditController', userEditController);

  /** @ngInject */
  function routerConfig($stateProvider) {
    $stateProvider
    .state('app.user-edit', {
      url: '/user/edit/:nev',
      templateUrl: 'app/users/edit/user-edit.html',
      controller: 'UserEditController',
      controllerAs: 'vm'
    });
  }

  /** @ngInject */
  function userEditController(usersService, personalDataService, $state, $stateParams, $translate, toastr, _, ROLES, LOCALES) {
    var vm = this;
    vm.roles = ROLES;
    vm.locales = LOCALES;
    vm.idsAndNames = [];
    vm.felhasznaloEditDTO = {};

    function getUserByName(name) {
      usersService.getUsers().then(function(felhasznalokResponseDTO) {
        return _.find(felhasznalokResponseDTO.felhasznalok,
          function(felhasznaloDTO) {
            return felhasznaloDTO.nev === name;
          }
        ).id;
      }).then(function(id) {
        usersService.getUser(id).then(function(felhasznaloResponseDTO) {
          vm.felhasznaloEditDTO = felhasznaloResponseDTO;
        });
      });
    }

    function getIdAndNameList() {
      personalDataService.getIdAndNameList(true).then(function(tszekEsNevekDTO) {
        vm.idsAndNames = tszekEsNevekDTO.tszekEsNevek;
      });
    }

    vm.roleChanged = function(newRole) {
      vm.felhasznaloEditDTO.tsz = newRole === 'DOLGOZO' ? vm.felhasznaloEditDTO.tsz : undefined;
    };

    vm.edit = function() {
      usersService.editUser({nev: vm.felhasznaloEditDTO.nev, szerep: vm.felhasznaloEditDTO.szerep, jelszo: vm.felhasznaloEditDTO.jelszo, tsz: vm.felhasznaloEditDTO.tsz, enabled: vm.felhasznaloEditDTO.enabled, locale: vm.felhasznaloEditDTO.locale}).then(function() {
        toastr.success($translate.instant('common.edit.succeed'));
        vm.usereditform.$setPristine();
      });
    };

    getUserByName($stateParams.nev);
    getIdAndNameList();
  }
})();
