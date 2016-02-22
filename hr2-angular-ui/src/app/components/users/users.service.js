(function() {
  'use strict';

  angular.module('hr2AngularUi').service('usersService', usersService);

  /** @ngInject */
  function usersService($http) {

    this.getUsers = function() {
      return $http.get('/api/v1/users/list').then(function(felhasznalokResponseDTO) {
        return felhasznalokResponseDTO.data;
      });
    };

    this.getUser = function(id) {
      return $http.get('/api/v1/users/get?id=' + id).then(function(felhasznaloResponseDTO) {
        return felhasznaloResponseDTO.data;
      });
    };

    this.createUser = function(felhasznaloCreateDTO) {
      return $http.post('/api/v1/users/create', felhasznaloCreateDTO).then(function(response) {
        return response.data;
      });
    };

    this.editUser = function(felhasznaloEditDTO) {
      return $http.post('/api/v1/users/edit', felhasznaloEditDTO).then(function(response) {
        return response.data;
      });
    };

    this.deleteUser = function(id) {
      return $http.post('/api/v1/users/delete/' + id).then(function(response) {
        return response.data;
      });
    };

  }
})();
