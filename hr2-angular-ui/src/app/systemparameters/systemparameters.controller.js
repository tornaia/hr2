(function() {
  'use strict';

  angular
  .module('hr2AngularUi')
  .config(routerConfig)
  .controller('SystemparametersController', systemparametersController);

  /** @ngInject */
  function routerConfig($stateProvider) {
    $stateProvider
    .state('app.systemparameters', {
      url: '/systemparameters',
      templateUrl: 'app/systemparameters/systemparameters.html',
      controller: 'SystemparametersController',
      controllerAs: 'vm'
    });
  }

  /** @ngInject */
  function systemparametersController(systemparametersService, roleRequiredService, $translate, uiGridConstants, toastr) {
    var vm = this;
    vm.roleRequiredService = roleRequiredService;

    vm.systemparametersGridOptions = {
        enableFiltering: true,
        showGridFooter: true,
        enableFooterTotalSelected: false,
        enableRowSelection: true,
        enableRowHeaderSelection: false,
        multiSelect: false,
        enableHorizontalScrollbar: uiGridConstants.scrollbars.NEVER,
        columnDefs: [{ field: 'tipus',
                       displayName: $translate.instant('rendszerparameterek.Header.Tipus'),
                       cellTemplate: '<div class="ui-grid-cell-contents">{{\'enum.RendszerParameterTipus.\' + row.entity[\'tipus\'] | translate}}</div>',
                       enableCellEdit: false,
                       enableFiltering: false,
                       filter: {
                         noTerm: true,
                         condition: function(searchTerm, cellValue) {
                           return angular.isUndefined(searchTerm) || $translate.instant('enum.RendszerParameterTipus.' + cellValue).toLowerCase().indexOf(searchTerm.toLowerCase()) !== -1;
                         }
                       }
                     },
                     { field: 'ertek',
                       displayName: $translate.instant('rendszerparameterek.Header.Ertek'),
                       enableCellEdit: roleRequiredService.hasRole('ROLE_ADMINISTRATOR'),
                       cellTemplate: '<div class="ui-grid-cell-contents" ng-class="{editablecell: grid.appScope.roleRequiredService.hasRole(\'ROLE_ADMINISTRATOR\')}">{{row.entity[\'ertek\']}}</div>',
                       enableFiltering: false,
                       enableSorting: false,
                       enableColumnMenu: false
                     }
                    ],
        appScopeProvider: vm
    };

    function getSystemparameters() {
      systemparametersService.getSystemParameters().then(function(rendszerParameterekResponseDTO) {
        vm.systemparametersGridOptions.data = rendszerParameterekResponseDTO.rendszerParameterek;
      });
    }

    vm.edit = function() {
      systemparametersService.editSystemParameters({rendszerParameterek: vm.systemparametersGridOptions.data}).then(function() {
        toastr.success($translate.instant('common.edit.succeed'));
        vm.systemparameterseditform.$setPristine();
      });
    };

    getSystemparameters();
  }

  angular
  .module('hr2AngularUi')
  .directive('systemparametersTable', function() {
    return {
      restrict: 'E',
      templateUrl: 'app/systemparameters/systemparameters-table.html'
    };
});
})();
