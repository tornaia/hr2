app.controller('qualificationsCtrl', ['$scope', '$http', '$routeParams', '$filter', '$q', 'ngTableParams', 'ngDialog', 'toaster', function($scope, $http, $routeParams, $filter, $q, ngTableParams, ngDialog, toaster) {

    $scope.qualifications = [];
    $scope.qualificationsTableParams = new ngTableParams({
        page: 1,
        count: 10,
        sorting: {
            ev: 'desc'
        },
        filter: {
            tipus: ''
        }
    }, {
        total: $scope.qualifications.length,
        getData: function($defer, params) {
            if (($scope.qualifications.length + $scope.qualificationsTableParams.count() - 1) / $scope.qualificationsTableParams.count() < $scope.qualificationsTableParams.page()) {
                $scope.qualificationsTableParams.page($scope.qualifications.length / $scope.qualificationsTableParams.count());
            }
            var filteredData = params.filter() ? $filter('filter')($scope.qualifications, params.filter()) : $scope.qualifications;
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
            if (visibleItems[i] === $scope.selectedQualification) {
                return;
            }
        }
        $scope.changeQualificationSelection($scope.selectedQualification);
    };

    $scope.loadQualifications = function() {
        $http.get('api/v1/qualifications/findall?tsz=' + $routeParams.tsz)
        .success(function(response) {
            $scope.qualifications = response.kepzettsegek;
            $scope.selectedQualification = undefined;
            $scope.qualificationsTableParams.reload();
        });
    };

    $scope.changeQualificationSelection = function(qualification) {
        for (var i=0; i<$scope.qualifications.length; ++i) {
            var entry = $scope.qualifications[i];
            entry.$selected = false;
        }

        var isDeselect = qualification === $scope.selectedQualification;
        if (isDeselect) {
            $scope.selectedQualification = null;
        } else {
            qualification.$selected = true;
            $scope.selectedQualification = qualification;
        }
    };

    $scope.deleteQualification = function() {
        $http.post('api/v1/qualifications/delete/' + $scope.selectedQualification.id)
        .success(function() {
            toaster.pop('success', global_translation['ALERT_DELETE_SUCCEED']);
            $scope.loadQualifications();
        });
    };

    $scope.showQualificationCreate = function() {
        ngDialog.open({
            scope: $scope,
            templateUrl: 'module/personaldata/qualifications/qualification-create.html',
            controller: 'createQualificationCtrl'
        });
    };

    $scope.showQualificationEdit = function() {
        ngDialog.open({
            scope: $scope,
            templateUrl: 'module/personaldata/qualifications/qualification-edit.html',
            controller: 'editQualificationCtrl'
        });
    };

    $scope.loadQualifications();
}]);

app.controller('createQualificationCtrl', ['$scope', '$http', '$routeParams', 'toaster', function($scope, $http, $routeParams, toaster) {
    $scope.form = {};
    $scope.createQualification = function() {
        $http.post('api/v1/qualifications/create', {szemelyitorzs : {tsz: $routeParams.tsz}, tipus: $scope.form.tipus, megnevezes: $scope.form.megnevezes, modFok: $scope.form.modFok, ev: $scope.form.ev, ervenyessegVege: $scope.form.ervenyessegVege, megjegyzes: $scope.form.megjegyzes})
        .success(function() {
            toaster.pop('success', global_translation['ALERT_CREATE_SUCCEED']);
            $scope.closeThisDialog();
            $scope.loadQualifications();
        });
    };
}]);

app.controller('editQualificationCtrl', ['$scope', '$http', 'toaster', function($scope, $http, toaster) {
    $scope.form = angular.copy($scope.selectedQualification);
    $scope.saveQualification = function() {
        $http.post('api/v1/qualifications/edit', $scope.form)
        .success(function() {
            toaster.pop('success', global_translation['ALERT_SAVE_SUCCEED']);
            $scope.closeThisDialog();
            $scope.loadQualifications();
        });
    };
}]);
