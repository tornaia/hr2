(function() {
  'use strict';

  angular
  .module('hr2AngularUi')
  .config(routerConfig)
  .controller('ScheduledtaskDetailController', scheduledtaskDetailController);

  /** @ngInject */
  function routerConfig($stateProvider) {
    $stateProvider
    .state('app.scheduledtask-detail', {
      url: '/scheduledtasks/detail/:id',
      templateUrl: 'app/scheduledtasks/detail/scheduledtask-detail.html',
      controller: 'ScheduledtaskDetailController',
      controllerAs: 'vm'
    });
  }

  /** @ngInject */
  function scheduledtaskDetailController(scheduledtasksService, $stateParams) {
    var vm = this;

    function getScheduledTaskDetail(id) {
      scheduledtasksService.getScheduledTaskDetail(id).then(function(idozitettFuttatasResponseDTO) {
        vm.idozitettFuttatasResponseDTO = idozitettFuttatasResponseDTO;
      });
    }

    getScheduledTaskDetail($stateParams.id);
  }
})();
