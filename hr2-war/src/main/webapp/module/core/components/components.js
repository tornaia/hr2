app.directive('numberAsYear', function() {
    return {
        require: 'ngModel',
        link: function(scope, element, attrs, ngModelController) {
            ngModelController.$parsers.push(function(data) {
                // convert data from view format to model format
                return new Date(data, 0, 1, 0, 0, 0);
            });

            ngModelController.$formatters.push(function(data) {
                // convert data from model format to view format
                return data ? new Date(data).getFullYear() : undefined;
            });
        }
    };
});

app.directive('monthPager', function() {
    return {
        restrict: 'E',
        templateUrl: 'module/core/components/month-pager.html',
        scope: {
            //Two-way data binding
            selectedMonth: '=',
            translation: '='
        },
        controller: function($scope) {
            $scope.months = ['JANUARY', 'FEBRUARY', 'MARCH', 'APRIL', 'MAY', 'JUNE', 'JULY', 'AUGUST', 'SEPTEMBER', 'OCTOBER', 'NOVEMBER', 'DECEMBER'];

            $scope.selectedDate = new Date(new Date().getFullYear(), new Date().getMonth(), 1, 0, 0, 0, 0);

            $scope.move = function(value) {
                $scope.selectedDate.setMonth($scope.selectedDate.getMonth() + value);
                $scope.selectedMonth = new Date($scope.selectedDate.getFullYear(), $scope.selectedDate.getMonth(), 1, 0, 0, 0, 0);
                $scope.fullYear = $scope.selectedDate.getFullYear();
                $scope.month = $scope.selectedDate.getMonth();
            };

            $scope.move(0);
        }
    };
});

app.directive('yearPager', function() {
    return {
        require: 'ngModel',
        restrict: 'E',
        templateUrl: 'module/core/components/year-pager.html',
        scope: {
            ngModel: '=',
            ngChange: '&'
        },
        link: function (scope, element) {
            element.bind('mousedown', function (event) {
                var isLeftArrow = elementHasClassName(event.target, 'glyphicon-chevron-left');
                var isRightArrow = elementHasClassName(event.target, 'glyphicon-chevron-right');
                var isArrowEmbedder = event.target.attributes['ng-click'] !== undefined;
                if (!isLeftArrow && !isRightArrow && !isArrowEmbedder) {
                    event.preventDefault();
                    event.stopImmediatePropagation();
                    return false;
                }
            });
        },
        controller: function($scope) {
            $scope.selectedDate = new Date(new Date().getFullYear(), 0, 1, 0, 0, 0, 0);

            $scope.move = function(value) {
                $scope.selectedDate.setYear($scope.selectedDate.getFullYear() + value);
                $scope.ngModel = $scope.selectedDate.getFullYear();
                setTimeout(function() { $scope.ngChange(); });
            };

            $scope.move(0);
        }
    };
});
