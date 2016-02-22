(function() {
  'use strict';

  angular.module('hr2AngularUi').service('profileService', profileService);

  /** @ngInject */
  function profileService($http) {

    this.getProfileByName = function(name) {
      return $http.get('/api/v1/profile/get?name=' + name).then(function(response) {
        return response.data;
      });
    };

    this.editProfile = function(profileEditDTO) {
      return $http.post('/api/v1/profile/edit', profileEditDTO).then(function(response) {
        return response.data;
      });
    };

  }
})();
