(function() {
  'use strict';

  angular
  .module('hr2AngularUi')
  .directive('uiSrefConfirmUnsavedData', uiSrefConfirmUnsavedData);

  // https://github.com/angular-ui/ui-router/blob/539e207731daaafda34876a462d6624a12bfe03b/src/stateDirectives.js
  function parseStateRef(ref, current) {
    var preparsed = ref.match(/^\s*({[^}]*})\s*$/), parsed;
    if (preparsed) {
      ref = current + '(' + preparsed[1] + ')';
    }
    parsed = ref.replace(/\n/g, " ").match(/^([^(]+?)\s*(\((.*)\))?$/);
    if (!parsed || parsed.length !== 4) {
      throw new Error("Invalid state ref '" + ref + "'");
    }
    return { state: parsed[1], paramExpr: parsed[3] || null };
  }

  /** @ngInject */
  function uiSrefConfirmUnsavedData($document, $q, ngDialog, $state) {
    var directive = {
      restrict : 'A',
      link: function (scope, element, attr) {
            element.bind('click', function () {
                var hasDirtyForm = angular.element($document[0].querySelectorAll('form.ng-dirty')).length !== 0;
                if (hasDirtyForm) {
                    this.ignoreUnsavedDataPromise = function() {
                        var deferred = $q.defer();

                        ngDialog.open({
                            templateUrl: 'app/components/confirmation/unsaved-data.html',
                            controller: ['$scope', function($scope) {
                                $scope.confirm = function() {
                                  var ref = parseStateRef(attr.uiSrefConfirmUnsavedData, $state.current.name);
                                  var params = scope.$eval(ref.paramExpr) || {};
                                  $state.go(ref.state, params);
                                  $scope.closeThisDialog();
                                };

                                $scope.cancel = function() {
                                    $scope.closeThisDialog();
                                };
                            }]
                        });

                        return deferred.promise;
                    };

                    return {
                        closePromise:
                            this.ignoreUnsavedDataPromise().then(function(result) {
                                scope.$eval(attr.uiSrefConfirmUnsavedData);
                                return result;
                            })
                    };
                } else {
                  var ref = parseStateRef(attr.uiSrefConfirmUnsavedData, $state.current.name);
                  var params = scope.$eval(ref.paramExpr) || {};
                  $state.go(ref.state, params);
                }
            });
    }};

    return directive;
  }

})();
