(function() {
  'use strict';

  angular
  .module('hr2AngularUi')
  .directive('navbar', menu);

  /** @ngInject */
  function menu() {
    var directive = {
      restrict : 'E',
      templateUrl : 'app/components/menu/menu.html',
      controller : MenuController,
      controllerAs : 'menuCtrl'
    };

    return directive;

    /** @ngInject */
    function MenuController(menuService, userinfoService) {
      var menuCtrl = this;
      menuCtrl.username = undefined;

      menuService.getMenu().then(function(menuResponseDTO) {
        menuCtrl.menu = menuResponseDTO;
      });

      userinfoService.getUserInfo().then(function(userInfoResponseDTO) {
        menuCtrl.username = userInfoResponseDTO.username;
      });
    }
  }

})();
