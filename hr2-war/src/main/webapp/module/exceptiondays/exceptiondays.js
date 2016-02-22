app.controller('exceptiondaysCtrl', ['$scope', '$http', '$filter', 'ngTableParams', 'ngDialog', 'toaster', 'enums', function($scope, $http, $filter, ngTableParams, ngDialog, toaster, enums) {
    $scope.kivetelnapTipusok = enums.kivetelnapTipusok;

    $scope.exceptiondays = [];
    $scope.exceptiondaysTableParams = new ngTableParams({
        page: 1,
        count: 10,
        sorting: {
            datum: 'desc'
        },
        filter: {
            tipus: ''
        }
    }, {
        total: $scope.exceptiondays.length,
        getData: function($defer, params) {
            if (($scope.exceptiondays.length + $scope.exceptiondaysTableParams.count() - 1) / $scope.exceptiondaysTableParams.count() < $scope.exceptiondaysTableParams.page()) {
                $scope.exceptiondaysTableParams.page($scope.exceptiondays.length / $scope.exceptiondaysTableParams.count());
            }
            var filteredData = params.filter() ? $filter('filter')($scope.exceptiondays, params.filter()) : $scope.exceptiondays;
            var orderedAndFilteredData = params.sorting() ? $filter('orderBy')(filteredData, params.orderBy()) : filteredData;
            params.total(orderedAndFilteredData.length);
            var currentPage = Math.max(params.page(), 1);
            var itemsPerPage = params.count();
            var fromIdx = (currentPage - 1) * itemsPerPage;
            var toIdx = (currentPage) * itemsPerPage;
            var items = orderedAndFilteredData.slice(fromIdx, toIdx);
            $defer.resolve(items);
            $scope.resetSelectedItemIfItsNotVisible(items);
        }
    });

    $scope.resetSelectedItemIfItsNotVisible = function(visibleItems) {
        for (var i=0;i<visibleItems.length;i++) {
            if (visibleItems[i] === $scope.selectedExceptionday) {
                return;
            }
        }
        $scope.changeSelection($scope.selectedExceptionday);
    };

     $scope.changeSelection = function(exceptionday) {
        for (var i=0; i<$scope.exceptiondays.length; ++i) {
            var entry = $scope.exceptiondays[i];
            entry.$selected = false;
        }

        var isDeselect = exceptionday === $scope.selectedExceptionday;
        if (isDeselect) {
            $scope.selectedExceptionday = null;
        } else {
            exceptionday.$selected = true;
            $scope.selectedExceptionday = exceptionday;
        }
    };

    $scope.loadList = function() {
        $http.get('api/v1/exceptiondays/list')
            .success(function(response) {
                $scope.exceptiondays = response.kivetelnapok;
                $scope.exceptiondaysTableParams.reload();
                $scope.selectedExceptionday = null;
            });
    };

    $scope.delete = function() {
        $http.post('api/v1/exceptiondays/delete/'+ $scope.selectedExceptionday.id)
        .success(function() {
            toaster.pop('success', global_translation['ALERT_DELETE_SUCCEED']);
            $scope.loadList();
        });
    };

    $scope.showCreate = function() {
        ngDialog.open({
            scope: $scope,
            templateUrl: 'module/exceptiondays/exceptionday-create.html',
            controller: 'createExceptiondayCtrl'
        });
    };

    $scope.loadList();
}]);

app.controller('createExceptiondayCtrl', ['$scope', '$http', 'toaster', function($scope, $http, toaster) {
    $scope.form = {};
    $scope.create = function() {
        $http.post('api/v1/exceptiondays/create', {datum: $scope.form.datum, tipus: $scope.form.tipus})
        .success(function() {
            toaster.pop('success', global_translation['ALERT_CREATE_SUCCEED']);
            $scope.closeThisDialog();
            $scope.loadList();
        });
    };
}]);

app.directive('exceptiondaysTable', function() {
    return {
        restrict: 'E',
        templateUrl: 'module/exceptiondays/exceptiondays-table.html',
        controller: 'exceptiondaysCtrl'
    };
});
