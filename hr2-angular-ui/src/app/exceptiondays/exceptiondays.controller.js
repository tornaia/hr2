(function() {
  'use strict';

  angular
  .module('hr2AngularUi')
  .config(routerConfig)
  .controller('ExceptionDaysController', exceptionDaysController);

  /** @ngInject */
  function routerConfig($stateProvider) {
    $stateProvider
    .state('app.exceptiondays', {
      url: '/exceptiondays',
      templateUrl: 'app/exceptiondays/exceptiondays.html',
      controller: 'ExceptionDaysController',
      controllerAs: 'vm'
    });
  }

  /** @ngInject */
  function exceptionDaysController(exceptionDaysService, roleRequiredService, $translate, uiGridConstants, toastr) {
    var vm = this;

    vm.exceptiondayGridOptions = {
        enableFiltering: true,
        showGridFooter: true,
        enableFooterTotalSelected: false,
        enableRowSelection: true,
        enableRowHeaderSelection: false,
        multiSelect: false,
        enableHorizontalScrollbar: uiGridConstants.scrollbars.NEVER,
        columnDefs: [{ field: 'datum',
                       displayName: $translate.instant('kivetelnapok.Header.Datum')
                     },
                     { field: 'tipus',
                        displayName: $translate.instant('kivetelnapok.Header.Tipus'),
                        filter: {
                          type: uiGridConstants.filter.SELECT,
                          selectOptions: [ { value: 'PIHENONAP', label: $translate.instant('enum.KivetelnapTipus.PIHENONAP') },
                                           { value: 'MUNKANAP', label: $translate.instant('enum.KivetelnapTipus.MUNKANAP')}
                                         ]
                        },
                        cellTemplate: '<div class="ui-grid-cell-contents">{{\'enum.KivetelnapTipus.\' + row.entity[\'tipus\'] | translate}}</div>'
                     },
                    { field: 'id',
                      visible: roleRequiredService.hasRole('ROLE_ADMINISTRATOR'),
                      displayName: $translate.instant('kivetelnapok.Header.Muveletek'),
                      enableFiltering: false,
                      enableSorting: false,
                      enableColumnMenu: false,
                      cellTemplate: '<div class="ui-grid-cell-contents button-cell pull-right"><span click-confirmation-on-delete="grid.appScope.deleteExceptionDay(row.entity[\'id\'])" class="col-xs-1 glyphicon glyphicon-trash ui-grid-action-button"></span></div>'
                    }
                  ],
        appScopeProvider: vm
    };

    vm.deleteExceptionDay = function(id) {
      exceptionDaysService.deleteExceptionDay(id).then(function() {
        toastr.success($translate.instant('common.delete.succeed'));
        getExceptionDays();
      });
    };

    function getExceptionDays() {
      exceptionDaysService.getExceptionDays().then(function(kivetelnapokResponseDTO) {
        vm.exceptiondayGridOptions.data = kivetelnapokResponseDTO.kivetelnapok;
      });
    }

    getExceptionDays();
  }

  angular
  .module('hr2AngularUi')
  .directive('exceptiondaysTable', function() {
    return {
        restrict: 'E',
        templateUrl: 'app/exceptiondays/exceptiondays-table.html'
    };
  });
})();
