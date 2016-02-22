app.controller('jobhistoryCtrl', ['$scope', '$http', '$routeParams', '$filter', '$q', 'ngTableParams', 'ngDialog', 'toaster', function($scope, $http, $routeParams, $filter, $q, ngTableParams, ngDialog, toaster) {

    $scope.jobhistoryentries = [];
    $scope.jobhistoryTableParams = new ngTableParams({
        page: 1,
        count: 10,
        sorting: {
            datum: 'desc'
        },
        filter: {
            fizetes: ''
        }
    }, {
        total: $scope.jobhistoryentries.length,
        getData: function($defer, params) {
            if (($scope.jobhistoryentries.length + $scope.jobhistoryTableParams.count() - 1) / $scope.jobhistoryTableParams.count() < $scope.jobhistoryTableParams.page()) {
                $scope.jobhistoryTableParams.page($scope.jobhistoryentries.length / $scope.jobhistoryTableParams.count());
            }
            var filteredData = params.filter() ? $filter('filter')($scope.jobhistoryentries, params.filter()) : $scope.jobhistoryentries;
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
            if (visibleItems[i] === $scope.selectedJobhistoryentry) {
                return;
            }
        }
        $scope.changeJobhistoryentrySelection($scope.selectedJobhistoryentry);
    };

    $scope.loadJobhistoryentries = function() {
        $http.get('api/v1/jobhistory/findall?tsz=' + $routeParams.tsz)
        .success(function(response) {
            $scope.jobhistoryentries = response.munkakoriHistorikusAdatok;
            $scope.selectedJobhistoryentry = undefined;
            $scope.jobhistoryTableParams.reload();
        });
    };

    $scope.changeJobhistoryentrySelection = function(jobhistoryentry) {
        for (var i=0; i<$scope.jobhistoryentries.length; ++i) {
            var entry = $scope.jobhistoryentries[i];
            entry.$selected = false;
        }

        var isDeselect = jobhistoryentry === $scope.selectedJobhistoryentry;
        if (isDeselect) {
            $scope.selectedJobhistoryentry = null;
        } else {
            jobhistoryentry.$selected = true;
            $scope.selectedJobhistoryentry = jobhistoryentry;
        }
    };

    $scope.deleteJobhistoryentry = function() {
        $http.post('api/v1/jobhistory/delete/' + $scope.selectedJobhistoryentry.id)
        .success(function() {
            toaster.pop('success', global_translation['ALERT_DELETE_SUCCEED']);
            $scope.loadJobhistoryentries();
        });
    };

    $scope.showJobhistoryentryCreate = function() {
        ngDialog.open({
            scope: $scope,
            templateUrl: 'module/personaldata/jobhistory/jobhistory-create.html',
            controller: 'createJobhistoryCtrl'
        });
    };

    $scope.showJobhistoryentryEdit = function() {
        ngDialog.open({
            scope: $scope,
            templateUrl: 'module/personaldata/jobhistory/jobhistory-edit.html',
            controller: 'editJobhistoryCtrl'
        });
    };

    $scope.loadJobhistoryentries();
}]);

app.controller('createJobhistoryCtrl', ['$scope', '$http', '$routeParams', 'toaster', function($scope, $http, $routeParams, toaster) {
    $scope.form = {};
    $scope.createJobhistoryentry = function() {
        $http.post('api/v1/jobhistory/create', {tsz: $routeParams.tsz, datum: $scope.form.datum, fEOR: $scope.form.fEOR.id, fizetes: $scope.form.fizetes})
        .success(function() {
            toaster.pop('success', global_translation['ALERT_CREATE_SUCCEED']);
            $scope.closeThisDialog();
            $scope.loadJobhistoryentries();
        });
    };
}]);

app.controller('editJobhistoryCtrl', ['$scope', '$http', 'toaster', function($scope, $http, toaster) {
    $scope.form = angular.copy($scope.selectedJobhistoryentry);
    $scope.saveJobhistoryentry = function() {
        $http.post('api/v1/jobhistory/edit', $scope.form)
        .success(function() {
            toaster.pop('success', global_translation['ALERT_SAVE_SUCCEED']);
            $scope.closeThisDialog();
            $scope.loadJobhistoryentries();
        });
    };
}]);
