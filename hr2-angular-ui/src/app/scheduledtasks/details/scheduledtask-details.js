(function() {
  'use strict';

  angular
  .module('hr2AngularUi')
  .config(routerConfig)
  .controller('ScheduledtaskDetailsController', scheduledtaskDetailsController);

  /** @ngInject */
  function routerConfig($stateProvider) {
    $stateProvider
    .state('app.scheduledtask-details', {
      url: '/scheduledtasks/details/:feladat',
      templateUrl: 'app/scheduledtasks/details/scheduledtask-details.html',
      controller: 'ScheduledtaskDetailsController',
      controllerAs: 'vm'
    });
  }

  /** @ngInject */
  function scheduledtaskDetailsController(scheduledtasksService, roleRequiredService, $stateParams, $translate, uiGridConstants) {
    var vm = this;

    vm.scheduledtaskDetailsGridOptions = {
            enableFiltering: true,
            showGridFooter: true,
            enableFooterTotalSelected: false,
            enableRowSelection: true,
            enableRowHeaderSelection: false,
            multiSelect: false,
            enableHorizontalScrollbar: uiGridConstants.scrollbars.NEVER,
            columnDefs: [{ field: 'inditas',
                           type: 'date',
                           cellFilter: 'date:\'short\'',
                           displayName: $translate.instant('idozitettfuttatasok.Header.Utolso.Futas.Inditas')
                         },
                         { field: 'befejezes',
                           type: 'date',
                           cellFilter: 'date:\'short\'',
                           displayName: $translate.instant('idozitettfuttatasok.Header.Utolso.Futas.Befejezes')
                         },
                         { field: 'eredmeny',
                           cellTooltip: true,
                           displayName: $translate.instant('idozitettfuttatasok.Header.Utolso.Futas.Eredmeny')
                         },
                         { field: 'id',
                           visible: roleRequiredService.hasRole('ROLE_BETEKINTO'),
                           displayName: $translate.instant('idozitettfuttatasok.Header.Muveletek'),
                           enableFiltering: false,
                           enableSorting: false,
                           enableColumnMenu: false,
                           cellTemplate: '<div class="ui-grid-cell-contents button-cell pull-right"><span ui-sref="app.scheduledtask-detail({id:row.entity[\'id\']})" class="col-xs-1 glyphicon glyphicon-list ui-grid-action-button"></span></div>'
                         }
                        ]
    };

    function getScheduledTaskDetails(task) {
      scheduledtasksService.getScheduledTaskDetails(task).then(function(idozitettFuttatasokResponseDTO) {
        vm.scheduledtaskDetailsGridOptions.data = idozitettFuttatasokResponseDTO.idozitettFuttatasok;
      });
    }

    getScheduledTaskDetails($stateParams.feladat);
  }
})();
