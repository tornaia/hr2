app.directive('ngClickConfirmationOnDelete', ['$timeout', 'ngDialog', function($timeout, ngDialog) {
    return {
        priority: 1,
        link: function (scope, element, attr) {
            element.bind('click', function () {
                ngDialog.open({
                    templateUrl: 'module/core/confirmation/delete.html',
                    controller: function($scope) {
                        $scope.confirm = function() {
                            scope.$eval(attr.ngClickConfirmationOnDelete);
                            $scope.closeThisDialog();
                        };

                        $scope.cancel = function() {
                            $scope.closeThisDialog();
                        };
                    }
                });

                $timeout(function() {$("#confirmationPopupCancelButton").focus();}, 100);
            });
        }
    };
}]);

app.directive('ngClickConfirmationOnUnsavedData', ['$timeout', '$q', 'ngDialog', function($timeout, $q, ngDialog) {
    return {
        link: function (scope, element, attr) {
            element.bind('click', function () {
                var hasModification = elementIsInAModifiedForm(element[0]);
                if (hasModification) {
                    this.ignoreUnsavedDataPromise = function() {
                        var deferred = $q.defer();

                        ngDialog.open({
                            className: 'confirmation-unsaved-data-click-dialog ngdialog-theme-default',
                            templateUrl: 'module/core/confirmation/unsaved-data.html',
                            controller: function($scope) {
                                $scope.confirm = function() {
                                    $scope.closeThisDialog();
                                    deferred.resolve(true);
                                };

                                $scope.cancel = function() {
                                    $scope.closeThisDialog();
                                };
                            }
                        });
                        $timeout(function() {$("#confirmationPopupCancelButton").focus();}, 100);

                        return deferred.promise;
                    };

                    return {
                        closePromise:
                            this.ignoreUnsavedDataPromise().then(function(result) {
                                scope.$eval(attr.ngClickConfirmationOnUnsavedData);
                                return result;
                            })
                    };
                } else {
                    scope.$eval(attr.ngClickConfirmationOnUnsavedData);
                }
            });
        }
    };
}]);

app.directive('ngMousedownConfirmationOnUnsavedData', ['$timeout', '$q', 'ngDialog', function($timeout, $q, ngDialog) {
    return {
        link: function (scope, element) {
            element.bind('mousedown', function (event) {
                var hasModification = elementIsInAModifiedForm(element[0]);
                if (hasModification) {
                    ngDialog.open({
                        className: 'confirmation-unsaved-data-click-dialog ngdialog-theme-default',
                        templateUrl: 'module/core/confirmation/unsaved-data.html',
                        controller: function($scope) {
                            $scope.confirm = function() {
                                setTimeout(function() { angular.element(event.target).trigger('click'); });
                                $scope.closeThisDialog();
                            };

                            $scope.cancel = function() {
                                $scope.closeThisDialog();
                            };
                        }
                    });
                    $timeout(function() {$("#confirmationPopupCancelButton").focus();}, 100);
                    return false;
                } else {
                    return true;
                }
            });
        }
    };
}]);

app.run(['$q', '$timeout', 'ngDialog', function($q, $timeout, ngDialog) {
    var defaults = ngDialog.getDefaults();
    defaults.preCloseCallback = function(value) {
        // ESC-nél es Save/Create azaz programbol hivott closeDialog-nal a value undefined. Lehetne a sikeres mentes callbackeknel $scope.closeThisDialog();-ban passzolni egy parametert? De egyelőre jó ez igy
        var closeAfterOkEvent = value !== '$document' && value !== '$closeButton';
        if (closeAfterOkEvent) {
            return true;
        }

        // do not ask for confirmation to close a confirmation popup to avoid endless loops
        var isConfirmPopupCallback = elementHasClassName(this[0], 'confirmation-unsaved-data-click-dialog');
        if (isConfirmPopupCallback) {
            return true;
        }

        var hasModification = elementIsInAModifiedForm(this[0]);
        if (hasModification) {
            this.ignoreUnsavedDataPromise = function() {
                var deferred = $q.defer();

                ngDialog.open({
                    className: 'confirmation-unsaved-data-click-dialog ngdialog-theme-default',
                    templateUrl: 'module/core/confirmation/unsaved-data.html',
                    controller: function($scope) {
                        $scope.confirm = function() {
                            $scope.closeThisDialog();
                            deferred.resolve(true);
                        };

                        $scope.cancel = function() {
                            $scope.closeThisDialog();
                        };
                    }
                });
                $timeout(function() {$("#confirmationPopupCancelButton").focus();}, 100);

                return deferred.promise;
            };

            return {closePromise: this.ignoreUnsavedDataPromise().then(function(result) {return result;})};
        } else {
            return true;
        }
    };
}]);
