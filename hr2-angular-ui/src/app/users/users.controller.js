(function() {
  'use strict';

  angular
  .module('hr2AngularUi')
  .config(routerConfig)
  .controller('UsersController', usersController);

  /** @ngInject */
  function routerConfig($stateProvider) {
    $stateProvider
    .state('app.users', {
      url: '/users',
      templateUrl: 'app/users/users.html',
      controller: 'UsersController',
      controllerAs: 'vm'
    });
  }

  /** @ngInject */
  function usersController(usersService, roleRequiredService, $translate, uiGridConstants, toastr) {
    var vm = this;

    vm.userGridOptions = {
        enableFiltering: true,
        showGridFooter: true,
        enableFooterTotalSelected: false,
        enableRowSelection: true,
        enableRowHeaderSelection: false,
        multiSelect: false,
        enableHorizontalScrollbar: uiGridConstants.scrollbars.NEVER,
        columnDefs: [{ field: 'nev',
                       displayName: $translate.instant('felhasznalok.Header.Azonosito')
                     },
                     { field: 'szerep',
                        displayName: $translate.instant('felhasznalok.Header.Szerep'),
                        filter: {
                          type: uiGridConstants.filter.SELECT,
                          selectOptions: [ { value: 'DOLGOZO', label: $translate.instant('enum.Szerep.DOLGOZO') },
                                           { value: 'BETEKINTO', label: $translate.instant('enum.Szerep.BETEKINTO') },
                                           { value: 'ADMINISTRATOR', label: $translate.instant('enum.Szerep.ADMINISTRATOR')}
                                         ]
                        },
                        cellTemplate: '<div class="ui-grid-cell-contents">{{\'enum.Szerep.\' + row.entity[\'szerep\'] | translate}}</div>'
                     },
                     { field: 'vezeteknev',
                        displayName: $translate.instant('felhasznalok.Header.Hozzarendelt.Szemelyi.Torzs')
                      },
                     { field: 'enabled',
                       displayName: $translate.instant('felhasznalok.Header.Aktiv'),
                       filter: {
                         type: uiGridConstants.filter.SELECT,
                         selectOptions: [ { value: true, label: 'Aktiv' }, { value: false, label: 'Inaktiv' } ]
                       },
                       cellTemplate: '<div class="ui-grid-cell-contents button-cell text-center"><span class="glyphicon glyphicon-{{row.entity[\'enabled\'] ? \'ok-circle\' : \'ban-circle\'}}"></span></div>'
                    },
                    { field: 'locale',
                      displayName: $translate.instant('felhasznalok.Header.Nyelv'),
                      filter: {
                        type: uiGridConstants.filter.SELECT,
                        selectOptions: [ { value: 'hu_HU', label: $translate.instant('enum.Locale.hu_HU') }, { value: false, label: $translate.instant('enum.Locale.en_US') } ]
                      }
                    },
                    { field: 'id',
                      visible: roleRequiredService.hasRole('ROLE_ADMINISTRATOR'),
                      displayName: $translate.instant('felhasznalok.Header.Muveletek'),
                      enableFiltering: false,
                      enableSorting: false,
                      enableColumnMenu: false,
                      cellTemplate: '<div class="ui-grid-cell-contents button-cell pull-right"><span ui-sref="app.user-edit({nev:row.entity[\'nev\']})" class="col-xs-1 glyphicon glyphicon-pencil ui-grid-action-button"></span><span click-confirmation-on-delete="grid.appScope.deleteUser(row.entity[\'id\'])" class="col-xs-1 glyphicon glyphicon-trash ui-grid-action-button"></span></div>'
                    }
                  ],
        appScopeProvider: vm
    };

    vm.deleteUser = function(id) {
      usersService.deleteUser(id).then(function() {
        toastr.success($translate.instant('common.delete.succeed'));
        getUsers();
      });
    };

    function getUsers() {
      usersService.getUsers().then(function(felhasznalokResponseDTO) {
        vm.userGridOptions.data = felhasznalokResponseDTO.felhasznalok;
      });
    }

    getUsers();
  }

  angular
  .module('hr2AngularUi')
  .directive('usersTable', function() {
    return {
        restrict: 'E',
        templateUrl: 'app/users/users-table.html'
    };
});
})();
