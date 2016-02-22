(function() {
  'use strict';

  angular
  .module('hr2AngularUi')
  .config(routerConfig)
  .controller('ProfileController', profileController);

  /** @ngInject */
  function routerConfig($stateProvider) {
    $stateProvider
    .state('app.profile', {
      url: '/profile/:nev',
      templateUrl: 'app/profile/profile.html',
      controller: 'ProfileController',
      controllerAs: 'vm'
    });
  }

  /** @ngInject */
  function profileController(profileService, $state, $stateParams, $translate, toastr, localeService, LOCALES) {
    var vm = this;
    vm.locales = LOCALES;
    vm.profileEditDTO = {};

    function getProfileByName(name) {
      profileService.getProfileByName(name).then(function(profileResponseDTO) {
        vm.profileEditDTO = profileResponseDTO;
      });
    }

    vm.edit = function() {
      profileService.editProfile({azonosito: vm.profileEditDTO.azonosito, locale: vm.profileEditDTO.locale, jelszo: vm.profileEditDTO.jelszo}).then(function() {
        toastr.success($translate.instant('common.edit.succeed'));
        vm.profileeditform.$setPristine();
        localeService.use(vm.profileEditDTO.locale);
      });
    };

    getProfileByName($stateParams.nev);
  }
})();
