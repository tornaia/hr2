app.controller('valueSetsCtrl', ['$scope', '$http', '$filter', '$q', 'ngTableParams', 'ngDialog', 'toaster', 'enums', function($scope, $http, $filter, $q, ngTableParams, ngDialog, toaster, enums) {
    $scope.ertekkeszletElemTipusok = enums.ertekkeszletElemTipusok;

    $scope.valuesets = [];
    $scope.valuesetsTableParams = new ngTableParams({
        page: 1,
        count: 10,
        sorting: {
            tipus: 'asc'
        },
        filter: {
            tipus: '',
            megnevezesMagyar: '',
            megnevezesAngol: ''
        }
    }, {
        total: $scope.valuesets.length,
        getData: function($defer, params) {
            if (($scope.valuesets.length + $scope.valuesetsTableParams.count() - 1) / $scope.valuesetsTableParams.count() < $scope.valuesetsTableParams.page()) {
                $scope.valuesetsTableParams.page($scope.valuesets.length / $scope.valuesetsTableParams.count());
            }
            var filteredData = params.filter() ? $filter('filter')($scope.valuesets, params.filter()) : $scope.valuesets;
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
            if (visibleItems[i] === $scope.selectedValueset) {
                return;
            }
        }
        $scope.changeSelection($scope.selectedValueset);
    };

    $scope.changeSelection = function(valueset) {
        for (var i=0; i<$scope.valuesets.length; ++i) {
            var entry = $scope.valuesets[i];
            entry.$selected = false;
        }

        var isDeselect = valueset === $scope.selectedValueset;
        if (isDeselect) {
            $scope.selectedValueset = null;
        } else {
            valueset.$selected = true;
            $scope.selectedValueset = valueset;
        }
    };

    $scope.loadList = function() {
        $http.get('api/v1/valuesets/list')
            .success(function(response) {
                $scope.valuesets = response.ertekkeszletElemek;
                $scope.valuesetsTableParams.reload();
                $scope.selectedValueset = null;
            });
    };

    $scope.delete = function() {
        $http.post('api/v1/valuesets/delete/'+ $scope.selectedValueset.id)
        .success(function() {
            toaster.pop('success', global_translation['ALERT_DELETE_SUCCEED']);
            $scope.loadList();
        });
    };

    $scope.showCreate = function() {
        $scope.form = {};
        ngDialog.open({
            scope: $scope,
            templateUrl: 'module/valuesets/valueset-create.html',
            controller: 'createValuesetCtr'
        });
    };

    $scope.showEdit = function() {
        $scope.form = angular.copy($scope.selectedValueset);
        ngDialog.open({
            scope: $scope,
            templateUrl: 'module/valuesets/valueset-edit.html',
            controller: 'editValuesetCtr'
        });
    };

    $scope.loadList();
}]);

app.controller('createValuesetCtr', ['$scope', '$http', 'toaster', function($scope, $http, toaster) {
    $scope.create = function() {
        $http.post('api/v1/valuesets/create', {tipus: $scope.form.tipus, megnevezesMagyar: $scope.form.megnevezesMagyar, megnevezesAngol: $scope.form.megnevezesAngol})
        .success(function() {
            toaster.pop('success', global_translation['ALERT_CREATE_SUCCEED']);
            $scope.closeThisDialog();
            $scope.loadList();
        });
    };
}]);

app.controller('editValuesetCtr', ['$scope', '$http', 'toaster', function($scope, $http, toaster) {
    $scope.save = function() {
        $http.post('api/v1/valuesets/save', {id: $scope.form.id, tipus: $scope.form.tipus, megnevezesMagyar: $scope.form.megnevezesMagyar, megnevezesAngol: $scope.form.megnevezesAngol})
        .success(function() {
            toaster.pop('success', global_translation['ALERT_SAVE_SUCCEED']);
            $scope.closeThisDialog();
            $scope.loadList();
        });
    };
}]);

app.directive('valuesetsTable', function() {
    return {
        restrict: 'E',
        templateUrl: 'module/valuesets/valuesets-table.html',
        controller: 'valueSetsCtrl'
    };
});
