(function() {
  'use strict';

  angular.module('hr2AngularUi').service('personalDataService', personalDataService);

  /** @ngInject */
  function personalDataService($http) {

    this.getIdAndNameList = function(all) {
      return $http.get('/api/v1/personaldata/getidandnamelist?all=' + all).then(function(response) {
        return response.data;
      });
    };

  }
})();
