(function() {
  'use strict';

  angular.module('hr2AngularUi').service('scheduledtasksService', scheduledtasksService);

  /** @ngInject */
  function scheduledtasksService($http, dateTransformer) {

    this.getScheduledTasks = function() {
      return $http.get('/api/v1/scheduledtasks/list').then(function(idozitettFuttatasokResponseDTO) {
        return idozitettFuttatasokResponseDTO.data;
      });
    };

    this.getScheduledTaskDetails = function(task) {
      return $http.get('/api/v1/scheduledtasks/details/' + task).then(function(idozitettFuttatasokResponseDTO) {
        return idozitettFuttatasokResponseDTO.data;
      });
    };

    this.getScheduledTaskDetail = function(id) {
      return $http.get('/api/v1/scheduledtasks/detail/' + id, {transformResponse: dateTransformer.transformResponse.toDate(['inditas', 'befejezes'])}).then(function(idozitettFuttatasResponseDTO) {
        return idozitettFuttatasResponseDTO.data;
      });
    };

    this.startTask = function(task) {
      return $http.post('/api/v1/scheduledtasks/start/' + task).then(function(response) {
        return response.data;
      });
    };

  }
})();
