app.controller('systemparametersCtrl', ['$scope', '$http', '$filter', 'ngTableParams', 'toaster', function($scope, $http, $filter, ngTableParams, toaster) {

    $scope.systemparameters = [];
    $scope.systemsparametersTableParams = new ngTableParams({
        page: 1,
        count: 10,
        sorting: {
            tipus: 'asc'
        },
        filter: {
        }
    }, {
        // hide page counts control
        counts: [],
        // value less than count hide pagination
        total: 1,
        getData: function($defer, params) {
            var currentPage = Math.max(params.page(), 1);
            var itemsPerPage = params.count();
            var fromIdx = (currentPage - 1) * itemsPerPage;
            var toIdx = (currentPage) * itemsPerPage;
            var items = $scope.systemparameters.slice(fromIdx, toIdx);
            $defer.resolve(items);
        }
    });

     $scope.changeSelection = function(systemparameter) {
        for (var i=0; i<$scope.systemparameters.length; ++i) {
            var entry = $scope.systemparameters[i];
            entry.$selected = false;
        }

        var isDeselect = systemparameter === $scope.selectedSystemparameter;
        if (isDeselect) {
            $scope.selectedSystemparameter = null;
        } else {
            systemparameter.$selected = true;
            $scope.selectedSystemparameter = systemparameter;
        }
    };

    $scope.loadList = function() {
        $http.get('api/v1/systemparameters/list')
            .success(function(response) {
                $scope.systemparameters = response.rendszerParameterek;
                $scope.systemsparametersTableParams.reload();
            });
    };

    $scope.cancel = function() {
        $scope.loadList();
    };

    $scope.save = function() {
        $http.post('api/v1/systemparameters/save', {rendszerParameterek: $scope.systemparameters})
        .success(function() {
            toaster.pop('success', global_translation['ALERT_SAVE_SUCCEED']);
        });
    };

    $scope.loadList();
}]);

app.directive('systemparametersBody', function() {
    return {
        restrict: 'E',
        templateUrl: 'module/systemparameters/systemparameters-body.html',
        controller: 'systemparametersCtrl'
    };
});
