(function() {
  'use strict';

  angular.module('hr2AngularUi').service('systemparametersService', systemparametersService);

  /** @ngInject */
  function systemparametersService($http) {

    this.getSystemParameters = function() {
      return $http.get('/api/v1/systemparameters/list').then(function(rendszerParameterekResponseDTO) {
        return rendszerParameterekResponseDTO.data;
      });
    };

    this.editSystemParameters = function(rendszerParameterekEditDTO) {
      return $http.post('/api/v1/systemparameters/edit', rendszerParameterekEditDTO).then(function(response) {
        return response.data;
      });
    };

  }
})();
