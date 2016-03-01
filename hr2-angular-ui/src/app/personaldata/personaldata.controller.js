(function () {
  'use strict';

  angular
    .module('hr2AngularUi')
    .config(routerConfig)
    .controller('PersonalDataController', personalDataController);

  /** @ngInject */
  function routerConfig($stateProvider) {
    $stateProvider
      .state('app.personaldata', {
        url: '/personaldata',
        templateUrl: 'app/personaldata/personaldata.html',
        controller: 'PersonalDataController',
        controllerAs: 'vm'
      });
  }

  /** @ngInject */
  function personalDataController(personalDataService, roleRequiredService, $translate, uiGridConstants) {
    var vm = this;

    vm.personalDataGridOptions = {
      enableFiltering: true,
      showGridFooter: true,
      enableFooterTotalSelected: false,
      enableRowSelection: true,
      enableRowHeaderSelection: false,
      multiSelect: false,
      enableHorizontalScrollbar: uiGridConstants.scrollbars.NEVER,
      columnDefs: getPersonalDataGridColumnDefs(),
      appScopeProvider: vm
    };

    function getPersonalDataGridColumnDefs() {
      return [
        {
          field: 'tsz',
          displayName: $translate.instant('personaldata.Header.PersonalDataNumber')
        }, {
          field: 'vezeteknev',
          displayName: $translate.instant('personaldata.Header.LastName')
        }, {
          field: 'keresztnev',
          displayName: $translate.instant('personaldata.Header.FirstName')
        }, {
          field: 'id',
          visible: roleRequiredService.hasRole('ROLE_ADMINISTRATOR'),
          displayName: $translate.instant('personaldata.Header.Actions'),
          enableFiltering: false,
          enableSorting: false,
          enableColumnMenu: false,
          cellTemplate: '<div class="ui-grid-cell-contents button-cell pull-right"><span ui-sref="app.user-edit({nev:row.entity[\'nev\']})" class="col-xs-1 glyphicon glyphicon-pencil ui-grid-action-button"></span><span click-confirmation-on-delete="grid.appScope.deleteUser(row.entity[\'id\'])" class="col-xs-1 glyphicon glyphicon-trash ui-grid-action-button"></span></div>'
        }
      ];
    }

    function getPersonalData() {
      personalDataService.getIdAndNameList(true).then(function(tszekEsNevekDTO) {
        vm.personalDataGridOptions.data = tszekEsNevekDTO.tszekEsNevek;
      });
    }

    getPersonalData();
  }

  angular
    .module('hr2AngularUi')
    .directive('personalDataTable', function () {
      return {
        restrict: 'E',
        templateUrl: 'app/personaldata/personaldata-table.html'
      };
    });
})();
