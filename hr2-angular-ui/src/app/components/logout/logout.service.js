(function() {
  'use strict';

  angular.module('hr2AngularUi').service('logoutService', logoutService);

  /** @ngInject */
  function logoutService($http) {

    this.logout = function() {
      return $http.get('/logout').then(function(response) {
        return response.data;
      });
    };

  }
})();
