(function() {
  'use strict';

  angular.module('hr2AngularUi').service('menuService', menuService);

  /** @ngInject */
  function menuService($http) {

    this.getMenu = function() {
      return $http.get('/api/v1/menu').then(function(response) {
        return response.data;
      });
    };

  }
})();
