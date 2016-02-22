app.filter('isRunning', function () {
    return function (befejezes) {
        return befejezes ? global_translation['TASK_NOT_RUNNING'] : global_translation['TASK_RUNNING'];
    };
});

app.controller('scheduledtasksCtrl', ['$scope', '$http', '$filter', '$interval', 'ngTableParams', 'ngDialog', 'toaster', function($scope, $http, $filter, $interval, ngTableParams, ngDialog, toaster) {

    $scope.scheduledtasks = [];
    $scope.scheduledtasksTableParams = new ngTableParams({
        page: 1,
        count: 10,
        sorting: {
            azonosito: 'asc'
        },
        filter: {
        }
    }, {
        // hide page counts control
        counts: [],
        // value less than count hide pagination
        total: 1,
        getData: function($defer, params) {
            var currentPage = params.page();
            var itemsPerPage = params.count();
            var fromIdx = (currentPage - 1) * itemsPerPage;
            var toIdx = (currentPage) * itemsPerPage;
            var items = $scope.scheduledtasks.slice(fromIdx, toIdx);
            $defer.resolve(items);
        }
    });

     $scope.changeSelection = function(scheduledtask) {
        for (var i=0; i<$scope.scheduledtasks.length; ++i) {
            var entry = $scope.scheduledtasks[i];
            entry.$selected = false;
        }

        var isDeselect = scheduledtask === $scope.selectedScheduledtask;
        if (isDeselect) {
            $scope.selectedScheduledtask = null;
        } else {
            scheduledtask.$selected = true;
            $scope.selectedScheduledtask = scheduledtask;
        }
    };

    $scope.loadList = function() {
        $http.get('api/v1/scheduledtasks/list')
            .success(function(response) {
                $scope.scheduledtasks = response.idozitettFuttatasok;
                $scope.scheduledtasksTableParams.reload();
                // do not lose focus after refresh
                for (var i=0; i<$scope.scheduledtasks.length; ++i) {
                    var entry = $scope.scheduledtasks[i];
                    if ($scope.selectedScheduledtask && entry.azonosito === $scope.selectedScheduledtask.azonosito) {
                        entry.$selected = true;
                        $scope.selectedScheduledtask = entry;
                    }
                }
            });
    };

    $scope.showDetails = function() {
        ngDialog.open({
            templateUrl: 'module/scheduledtasks/scheduledtask-details.html',
            controller: 'scheduledtaskDetailsCtrl',
            data: {selectedAzonosito: $scope.selectedScheduledtask.azonosito}
        });
    };

    $scope.start = function() {
        $http.post('api/v1/scheduledtasks/start/' + $scope.selectedScheduledtask.azonosito)
        .success(function() {
            toaster.pop('success', 'Sikersen elindítva!');
            $scope.loadList();
        });
    };

    var stop;
    $scope.refreshList = function() {
        // do not start polling twice
        if (angular.isDefined(stop)) {
            return;
        }

        stop = $interval(function() {
            $scope.loadList();
        }, 3000);
    };

    $scope.stopFight = function() {
        if (angular.isDefined(stop)) {
          $interval.cancel(stop);
          stop = undefined;
        }
    };

    $scope.$on('$destroy', function() {
        // make sure that the interval is destroyed too
        $scope.stopFight();
    });

    $scope.loadList();
    $scope.refreshList();
}]);

app.controller('scheduledtaskDetailsCtrl', ['$scope', '$http', '$filter', 'ngTableParams', function($scope, $http, $filter, ngTableParams) {

    $scope.scheduledtaskdetails = [];
    $scope.scheduledtaskdetailsTableParams = new ngTableParams({
        page: 1,
        count: 10,
        sorting: {
            inditas: 'desc'
        },
        filter: {
            inditas: ''
        }
    }, {
        getData: function($defer, params) {
            if (($scope.scheduledtaskdetails.length + $scope.scheduledtaskdetailsTableParams.count() - 1) / $scope.scheduledtaskdetailsTableParams.count() < $scope.scheduledtaskdetailsTableParams.page()) {
                $scope.scheduledtaskdetailsTableParams.page($scope.scheduledtaskdetails.length / $scope.scheduledtaskdetailsTableParams.count());
            }
            var filteredData = params.filter() ? $filter('filter')($scope.scheduledtaskdetails, params.filter()) : $scope.scheduledtaskdetails;
            var orderedAndFilteredData = params.sorting() ? $filter('orderBy')(filteredData, params.orderBy()) : filteredData;
            params.total(orderedAndFilteredData.length);
            var currentPage = Math.max(params.page(), 1);
            var itemsPerPage = params.count();
            var fromIdx = (currentPage - 1) * itemsPerPage;
            var toIdx = (currentPage) * itemsPerPage;
            var items = orderedAndFilteredData.slice(fromIdx, toIdx);
            $defer.resolve(items);
        }
    });

     $scope.changeSelection = function(scheduledtaskdetail) {
        for (var i=0; i<$scope.scheduledtaskdetails.length; ++i) {
            var entry = $scope.scheduledtaskdetails[i];
            entry.$selected = false;
        }

        var isDeselect = scheduledtaskdetail === $scope.selectedScheduledtaskdetail;
        if (isDeselect) {
            $scope.selectedScheduledtaskdetail = null;
        } else {
            scheduledtaskdetail.$selected = true;
            $scope.selectedScheduledtaskdetail = scheduledtaskdetail;
        }
    };

    $scope.loadList = function() {
        $http.get('api/v1/scheduledtasks/details/' + $scope.ngDialogData.selectedAzonosito)
            .success(function(response) {
                $scope.scheduledtaskdetails = response;
                $scope.scheduledtaskdetailsTableParams.reload();
                $scope.selectedScheduledtaskdetail = null;
            });
    };

    $scope.loadList();
}]);

app.directive('scheduledtasksTable', function() {
    return {
        restrict: 'E',
        templateUrl: 'module/scheduledtasks/scheduledtasks-table.html',
        controller: 'scheduledtasksCtrl'
    };
});
