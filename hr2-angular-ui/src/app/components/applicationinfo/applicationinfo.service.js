(function() {
  'use strict';

  angular.module('hr2AngularUi').service('applicationInfoService', applicationInfoService);

  /** @ngInject */
  function applicationInfoService($http) {

    this.getApplicationInfo = function() {
      return $http.get('/api/v1/applicationinfo').then(function(response) {
        return response.data;
      });
    };

  }
})();
