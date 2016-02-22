app.controller('usersCtrl', ['$scope', '$http', '$filter', '$q', 'ngTableParams', 'ngDialog', 'toaster', 'enums', function($scope, $http, $filter, $q, ngTableParams, ngDialog, toaster, enums) {
    $scope.szerepek = enums.szerepek;
    $scope.locales = enums.locales;

    $scope.tszesnevlist = [];
    $scope.users = [];
    $scope.usersTableParams = new ngTableParams({
        page: 1,
        count: 10,
        sorting: {
            azonosito: 'asc'
        }
    }, {
        total: $scope.users.length,
        getData: function($defer, params) {
            if (($scope.users.length + $scope.usersTableParams.count() - 1) / $scope.usersTableParams.count() < $scope.usersTableParams.page()) {
                $scope.usersTableParams.page($scope.users.length / $scope.usersTableParams.count());
            }
            var filteredData = params.filter() ? $filter('filter')($scope.users, params.filter()) : $scope.users;
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
            if (visibleItems[i] === $scope.selectedUser) {
                return;
            }
        }
        $scope.changeUserSelection($scope.selectedUser);
    };

    $scope.loadList = function() {
        $http.get('api/v1/users/list').then(function(result) {
            $scope.users = result.data.felhasznalok;
            $scope.usersTableParams.reload();
            $scope.selectedUser = null;
        });
    };

     $scope.changeUserSelection = function(user) {
        for (var i=0; i<$scope.users.length; ++i) {
            var entry = $scope.users[i];
            entry.$selected = false;
        }

        var isDeselect = user === $scope.selectedUser;
        if (isDeselect) {
            $scope.selectedUser = null;
        } else {
            user.$selected = true;
            $scope.selectedUser = user;
        }
    };

    $scope.delete = function() {
        $http.post('api/v1/users/delete/' + $scope.selectedUser.id)
        .success(function() {
            toaster.pop('success', global_translation['ALERT_DELETE_SUCCEED']);
            $scope.loadList();
        });
    };

    $scope.showCreate = function() {
        $scope.form = {};
        ngDialog.open({
            scope: $scope,
            templateUrl: 'module/users/user-create.html',
            controller: 'createUserCtr'
        });
    };

    $scope.showEdit = function() {
        $scope.form = {};
        ngDialog.open({
            scope: $scope,
            templateUrl: 'module/users/user-edit.html',
            controller: 'editUserCtr'
        });
    };

    $scope.loadList();
}]);

app.controller('createUserCtr', ['$scope', '$http', 'toaster', function($scope, $http, toaster) {
    $http.get('api/v1/personaldata/getidandnamelist?all=true')
        .success(function(response) {
            $scope.tszesnevlist = response.tszekEsNevek;
            angular.forEach($scope.tszesnevlist, function(value, key) {
                value.nev = value.vezeteknev + ' ' + value.keresztnev;
            });
        });

    $scope.szerepChanged = function() {
        $scope.szemelyitorzsRequired = $scope.form.szerep === 'DOLGOZO';
        $scope.form.tsz = undefined;
    };

    $scope.create = function() {
        $http.post('api/v1/users/create', {nev: $scope.form.nev, szerep: $scope.form.szerep, tsz: $scope.form.tsz, jelszo: $scope.form.jelszo, enabled: $scope.form.enabled, locale: $scope.form.locale})
        .success(function() {
            toaster.pop('success', global_translation['ALERT_CREATE_SUCCEED']);
            $scope.closeThisDialog();
            $scope.loadList();
        });
    };
}]);

app.controller('editUserCtr', ['$scope', '$q', '$http', 'toaster', function($scope, $q, $http, toaster) {
    $q.all([$http.get('api/v1/personaldata/getidandnamelist?all=true'), $http.get('api/v1/users/get?id=' + $scope.selectedUser.id)]).then(function(result) {
        $scope.tszesnevlist = result[0].data.tszekEsNevek;
        angular.forEach($scope.tszesnevlist, function(value, key) {
            value.nev = value.vezeteknev + ' ' + value.keresztnev;
        });
        $scope.form = result[1].data;
        $scope.szemelyitorzsRequired = $scope.form.szerep === 'DOLGOZO';
     });

    $scope.szerepChanged = function() {
        $scope.szemelyitorzsRequired = $scope.form.szerep === 'DOLGOZO';
        $scope.form.tsz = undefined;
    };

    $scope.save = function() {
        $http.post('api/v1/users/save', {id: $scope.form.id, nev: $scope.form.nev, szerep: $scope.form.szerep, tsz: $scope.form.tsz, jelszo: $scope.form.jelszo, enabled: $scope.form.enabled, locale: $scope.form.locale})
        .success(function() {
            toaster.pop('success', global_translation['ALERT_SAVE_SUCCEED']);
            $scope.closeThisDialog();
            $scope.loadList();
        });
    };
}]);

app.directive('usersTable', function() {
    return {
        restrict: 'E',
        templateUrl: 'module/users/users-table.html',
        controller: 'usersCtrl'
    };
});

app.directive('passwordMatch', [function () {
    return {
        restrict: 'A',
        scope: true,
        require: 'ngModel',
        link: function (scope, elem , attrs,control) {
            var checker = function () {

                //get the value of the first password
                var e1 = scope.$eval(attrs.ngModel);

                //get the value of the other password
                var e2 = scope.$eval(attrs.passwordMatch);
                return e1 === e2;
            };
            scope.$watch(checker, function (n) {

                //set the form control to valid if both
                //passwords are the same, else invalid
                control.$setValidity("unique", n);
            });
        }
    };
}]);
