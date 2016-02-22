(function() {
  'use strict';

  angular
  .module('hr2AngularUi')
  .config(routerConfig)
  .controller('ValuesetsController', valuesetsController);

  /** @ngInject */
  function routerConfig($stateProvider) {
    $stateProvider
    .state('app.valuesets', {
      url: '/valuesets',
      templateUrl: 'app/valuesets/valuesets.html',
      controller: 'ValuesetsController',
      controllerAs: 'vm'
    });
  }

  /** @ngInject */
  function valuesetsController(valuesetsService, roleRequiredService, $translate, uiGridConstants, toastr) {
    var vm = this;

    vm.valuesetsGridOptions = {
        enableFiltering: true,
        showGridFooter: true,
        enableFooterTotalSelected: false,
        enableRowSelection: true,
        enableRowHeaderSelection: false,
        multiSelect: false,
        enableHorizontalScrollbar: uiGridConstants.scrollbars.NEVER,
        columnDefs: [{ field: 'tipus',
                       displayName: $translate.instant('ertekkeszletek.Header.Tipus'),
                       filter: {
                           type: uiGridConstants.filter.SELECT,
                           selectOptions: [ { value: 'ALLAMPOLGARSAG', label: $translate.instant('enum.ErtekkeszletElemTipus.ALLAMPOLGARSAG') },
                                            { value: 'SZERVEZETI_EGYSEG', label: $translate.instant('enum.ErtekkeszletElemTipus.SZERVEZETI_EGYSEG') },
                                            { value: 'KOLTSEGHELY', label: $translate.instant('enum.ErtekkeszletElemTipus.KOLTSEGHELY') },
                                            { value: 'FOGLALKOZASI_VISZONY', label: $translate.instant('enum.ErtekkeszletElemTipus.FOGLALKOZASI_VISZONY') },
                                            { value: 'FOGLALKOZTATAS_JELLEGE', label: $translate.instant('enum.ErtekkeszletElemTipus.FOGLALKOZTATAS_JELLEGE') },
                                            { value: 'FEOR', label: $translate.instant('enum.ErtekkeszletElemTipus.FEOR') },
                                            { value: 'MUNKAKOR', label: $translate.instant('enum.ErtekkeszletElemTipus.MUNKAKOR') }
                                          ]
                         },
                         cellTemplate: '<div class="ui-grid-cell-contents">{{\'enum.ErtekkeszletElemTipus.\' + row.entity[\'tipus\'] | translate}}</div>'
                     },
                     { field: 'megnevezesMagyar',
                       displayName: $translate.instant('ertekkeszletek.Header.Magyar')
                     },
                     { field: 'megnevezesAngol',
                       displayName: $translate.instant('ertekkeszletek.Header.Angol')
                     },
                     { field: 'id',
                       visible: roleRequiredService.hasRole('ROLE_ADMINISTRATOR'),
                       displayName: $translate.instant('ertekkeszletek.Header.Muveletek'),
                       enableFiltering: false,
                       enableSorting: false,
                       enableColumnMenu: false,
                       cellTemplate: '<div class="ui-grid-cell-contents button-cell pull-right"><span ui-sref="app.valueset-edit({id:row.entity[\'id\']})" class="col-xs-1 glyphicon glyphicon-pencil ui-grid-action-button"></span><span click-confirmation-on-delete="grid.appScope.deleteValueset(row.entity[\'id\'])" class="col-xs-1 glyphicon glyphicon-trash ui-grid-action-button"></span></div>'
                     }
                  ],
        appScopeProvider: vm
    };

    vm.deleteValueset = function(id) {
      valuesetsService.deleteValueset(id).then(function() {
        toastr.success($translate.instant('common.delete.succeed'));
        getValuesets();
      });
    };

    function getValuesets() {
      valuesetsService.getValuesets().then(function(ertekkeszletElemekResponseDTO) {
        vm.valuesetsGridOptions.data = ertekkeszletElemekResponseDTO.ertekkeszletElemek;
      });
    }

    getValuesets();
  }

  angular
  .module('hr2AngularUi')
  .directive('valuesetsTable', function() {
    return {
        restrict: 'E',
        templateUrl: 'app/valuesets/valuesets-table.html'
    };
});
})();
