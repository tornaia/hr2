(function() {
  'use strict';

  angular.module('hr2AngularUi').service('valuesetsService', valuesetsService);

  /** @ngInject */
  function valuesetsService($http) {

    this.getValuesets = function() {
      return $http.get('/api/v1/valuesets/list').then(function(ertekkeszletElemekResponseDTO) {
        return ertekkeszletElemekResponseDTO.data;
      });
    };

    this.getValuesetById = function(id) {
        return $http.get('/api/v1/valuesets/getbyid?id=' + id).then(function(ertekkeszletElemResponseDTO) {
          return ertekkeszletElemResponseDTO.data;
        });
      };

    this.createValueset = function(ertekkeszletElemCreateDTO) {
        return $http.post('/api/v1/valuesets/create', ertekkeszletElemCreateDTO).then(function(response) {
          return response.data;
        });
    };

    this.editValueset = function(felhasznaloEditDTO) {
      return $http.post('/api/v1/valuesets/edit', felhasznaloEditDTO).then(function(response) {
        return response.data;
      });
    };

    this.deleteValueset = function(id) {
      return $http.post('/api/v1/valuesets/delete/' + id).then(function(response) {
        return response.data;
      });
    };

  }
})();
