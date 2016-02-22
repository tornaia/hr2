(function() {
  'use strict';

  angular.module('hr2AngularUi').service('userinfoService', userinfoService);

  /** @ngInject */
  function userinfoService($http) {

    this.getUserInfo = function() {
      return $http.get('/api/v1/userinfo').then(function(response) {
        return response.data;
      });
    };

  }
})();
