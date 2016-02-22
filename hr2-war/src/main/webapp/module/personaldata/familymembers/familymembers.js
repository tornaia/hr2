app.controller('familymembersCtrl', ['$scope', '$http', '$routeParams', '$filter', '$q', 'ngTableParams', 'ngDialog', 'toaster', function($scope, $http, $routeParams, $filter, $q, ngTableParams, ngDialog, toaster) {

    $scope.familymembers = [];
    $scope.familymembersTableParams = new ngTableParams({
        page: 1,
        count: 10,
        sorting: {
            nev: 'asc'
        },
        filter: {
            nev: ''
        }
    }, {
        total: $scope.familymembers.length,
        getData: function($defer, params) {
            if (($scope.familymembers.length + $scope.familymembersTableParams.count() - 1) / $scope.familymembersTableParams.count() < $scope.familymembersTableParams.page()) {
                $scope.familymembersTableParams.page($scope.familymembers.length / $scope.familymembersTableParams.count());
            }
            var filteredData = params.filter() ? $filter('filter')($scope.familymembers, params.filter()) : $scope.familymembers;
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
            if (visibleItems[i] === $scope.selectedFamilymember) {
                return;
            }
        }
        $scope.changeFamilymemberSelection($scope.selectedFamilymember);
    };

    $scope.loadFamilymembers = function() {
        $http.get('api/v1/familymembers/findall?tsz=' + $routeParams.tsz)
        .success(function(response) {
            $scope.familymembers = response.csaladtagok;
            $scope.selectedFamilymember = undefined;
            $scope.familymembersTableParams.reload();
        });
    };

    $scope.changeFamilymemberSelection = function(familymember) {
        for (var i=0; i<$scope.familymembers.length; ++i) {
            var entry = $scope.familymembers[i];
            entry.$selected = false;
        }

        var isDeselect = familymember === $scope.selectedFamilymember;
        if (isDeselect) {
            $scope.selectedFamilymember = null;
        } else {
            familymember.$selected = true;
            $scope.selectedFamilymember = familymember;
        }
    };

    $scope.deleteFamilymember = function() {
        $http.post('api/v1/familymembers/delete/' + $scope.selectedFamilymember.id)
        .success(function() {
            toaster.pop('success', global_translation['ALERT_DELETE_SUCCEED']);
            $scope.loadFamilymembers();
        });
    };

    $scope.showFamilymemberCreate = function() {
        ngDialog.open({
            scope: $scope,
            templateUrl: 'module/personaldata/familymembers/familymember-create.html',
            controller: 'createFamilymemberCtrl'
        });
    };

    $scope.showFamilymemberEdit = function() {
        ngDialog.open({
            scope: $scope,
            templateUrl: 'module/personaldata/familymembers/familymember-edit.html',
            controller: 'editFamilymemberCtrl'
        });
    };

    $scope.loadFamilymembers();
}]);

app.controller('createFamilymemberCtrl', ['$scope', '$http', '$routeParams', 'toaster', function($scope, $http, $routeParams, toaster) {
    $scope.form = {};
    $scope.createFamilymember = function() {
        $http.post('api/v1/familymembers/create', {tsz: $routeParams.tsz, nev: $scope.form.nev, szuletesiDatum: $scope.form.szuletesiDatum, taj: $scope.form.taj, megjegyzes: $scope.form.megjegyzes})
        .success(function() {
            toaster.pop('success', global_translation['ALERT_CREATE_SUCCEED']);
            $scope.closeThisDialog();
            $scope.loadFamilymembers();
        });
    };
}]);

app.controller('editFamilymemberCtrl', ['$scope', '$http', 'toaster', function($scope, $http, toaster) {
    $scope.form = angular.copy($scope.selectedFamilymember);
    $scope.saveFamilymember = function() {
        $http.post('api/v1/familymembers/edit', $scope.form)
        .success(function() {
            toaster.pop('success', global_translation['ALERT_SAVE_SUCCEED']);
            $scope.closeThisDialog();
            $scope.loadFamilymembers();
        });
    };
}]);
