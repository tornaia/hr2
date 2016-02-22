(function() {
  'use strict';

  angular.module('hr2AngularUi').service('exceptionDaysService', exceptionDaysService);

  /** @ngInject */
  function exceptionDaysService($http) {

    this.getExceptionDays = function() {
      return $http.get('/api/v1/exceptiondays/list').then(function(kivetelnapokResponseDTO) {
        return kivetelnapokResponseDTO.data;
      });
    };

    this.createExceptionDay = function(kivetelnapCreateDTO) {
      return $http.post('/api/v1/exceptiondays/create', kivetelnapCreateDTO).then(function(response) {
        return response.data;
      });
    };

    this.deleteExceptionDay = function(id) {
      return $http.post('/api/v1/exceptiondays/delete/' + id).then(function(response) {
        return response.data;
      });
    };

  }
})();
