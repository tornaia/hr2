(function() {
  'use strict';

  angular
  .module('hr2AngularUi')
  .config(routerConfig)
  .controller('ScheduledtasksController', scheduledtasksController);

  /** @ngInject */
  function routerConfig($stateProvider) {
    $stateProvider
    .state('app.scheduledtasks', {
      url: '/scheduledtasks',
      templateUrl: 'app/scheduledtasks/scheduledtasks.html',
      controller: 'ScheduledtasksController',
      controllerAs: 'vm'
    });
  }

  /** @ngInject */
  function scheduledtasksController(scheduledtasksService, roleRequiredService, $translate, uiGridConstants, toastr, $interval, $scope) {
    var vm = this;

    vm.scheduledtasksGridOptions = {
        enableFiltering: true,
        showGridFooter: true,
        enableFooterTotalSelected: false,
        enableRowSelection: true,
        enableRowHeaderSelection: false,
        multiSelect: false,
        enableHorizontalScrollbar: uiGridConstants.scrollbars.NEVER,
        columnDefs: [{ field: 'azonosito',
                       displayName: $translate.instant('idozitettfuttatasok.Header.Azonosito'),
                       cellTemplate: '<div class="ui-grid-cell-contents" title="{{\'idozitettfuttatasok.Azonosito.\' + row.entity[\'azonosito\'] | translate}}">{{\'idozitettfuttatasok.Azonosito.\' + row.entity[\'azonosito\'] | translate}}</div>',
                       filter: {
                         noTerm: true,
                         condition: function(searchTerm, cellValue) {
                           return angular.isUndefined(searchTerm) || $translate.instant('idozitettfuttatasok.Azonosito.' + cellValue).toLowerCase().indexOf(searchTerm.toLowerCase()) !== -1;
                         }
                       }
                     },
                     { field: 'aktualisAllapot',
                       displayName: $translate.instant('idozitettfuttatasok.Header.Aktualis.Allapot'),
                       cellTemplate: '<div class="ui-grid-cell-contents">{{\'idozitettfuttatasok.label.\' + (angular.isUndefined(row.entity[\'befejezes\']) ? \'fut\' : \'nemfut\') | translate}}</div>'
                     },
                     { field: 'inditas',
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
                     { field: 'azonosito',
                       visible: roleRequiredService.hasRole('ROLE_BETEKINTO'),
                       displayName: $translate.instant('idozitettfuttatasok.Header.Muveletek'),
                       enableFiltering: false,
                       enableSorting: false,
                       enableColumnMenu: false,
                       cellTemplate: '<div class="ui-grid-cell-contents button-cell pull-right"><span ng-click="grid.appScope.start(row.entity[\'azonosito\'])" class="col-xs-1 glyphicon glyphicon-repeat ui-grid-action-button" role-required="ROLE_ADMINISTRATOR"></span><span ui-sref="app.scheduledtask-details({feladat:row.entity[\'azonosito\']})" class="col-xs-1 glyphicon glyphicon-list ui-grid-action-button"></span></div>'
                     }
                    ],
        appScopeProvider: vm
    };

    function getScheduledtasks() {
      scheduledtasksService.getScheduledTasks().then(function(idozitettFuttatasokResponseDTO) {
        vm.scheduledtasksGridOptions.data = idozitettFuttatasokResponseDTO.idozitettFuttatasok;
      });
    }

    vm.start = function(azonosito) {
      scheduledtasksService.startTask(azonosito).then(function() {
        toastr.success($translate.instant('idozitettfuttatasok.alert.task.started'));
      });
    };

    var autoPageReload = $interval(getScheduledtasks, 3000);
    $scope.$on('$destroy', function() {
      $interval.cancel(autoPageReload);
    });

    getScheduledtasks();
  }

  angular
  .module('hr2AngularUi')
  .directive('scheduledtasksTable', function() {
    return {
      restrict: 'E',
      templateUrl: 'app/scheduledtasks/scheduledtasks-table.html'
    };
});
})();
