(function() {
  'use strict';

  angular
  .module('hr2AngularUi')
  .directive('clickConfirmationOnDelete', clickConfirmationOnDelete);

  /** @ngInject */
  function clickConfirmationOnDelete(ngDialog) {
    var directive = {
      restrict : 'A',
      priority: 1,
      link: function (scope, element, attr) {
          element.bind('click', function () {
              ngDialog.open({
                  templateUrl: 'app/components/confirmation/delete.html',
                  controller: ['$scope', function($scope) {
                      $scope.confirm = function() {
                        var scope = angular.element(element).scope();
                        scope.$eval(attr.clickConfirmationOnDelete);
                      };

                      $scope.cancel = function() {
                          $scope.closeThisDialog();
                      };
                  }]
              });
          });
    }};

    return directive;
  }

})();
