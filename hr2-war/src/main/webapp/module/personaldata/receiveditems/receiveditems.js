app.controller('receiveditemsCtrl', ['$scope', '$http', '$routeParams', '$window', '$filter', '$q', 'ngTableParams', 'ngDialog', 'toaster', function($scope, $http, $routeParams, $window, $filter, $q, ngTableParams, ngDialog, toaster) {

    $scope.receiveditems = [];
    $scope.receiveditemsTableParams = new ngTableParams({
        page: 1,
        count: 10,
        sorting: {
            megnevezes: 'asc'
        },
        filter: {
            megnevezes: ''
        }
    }, {
        total: $scope.receiveditems.length,
        getData: function($defer, params) {
            if (($scope.receiveditems.length + $scope.receiveditemsTableParams.count() - 1) / $scope.receiveditemsTableParams.count() < $scope.receiveditemsTableParams.page()) {
                $scope.receiveditemsTableParams.page($scope.receiveditems.length / $scope.receiveditemsTableParams.count());
            }
            var filteredData = params.filter() ? $filter('filter')($scope.receiveditems, params.filter()) : $scope.receiveditems;
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
            if (visibleItems[i] === $scope.selectedReceiveditem) {
                return;
            }
        }
        $scope.changeReceiveditemSelection($scope.selectedReceiveditem);
    };

    $scope.loadReceiveditems = function() {
        $http.get('api/v1/receiveditems/findall?tsz=' + $routeParams.tsz)
        .success(function(response) {
            $scope.receiveditems = response.atvettEszkozok;
            $scope.selectedReceiveditem = undefined;
            $scope.receiveditemsTableParams.reload();
        });
    };

    $scope.changeReceiveditemSelection = function(receiveditem) {
        for (var i=0; i<$scope.receiveditems.length; ++i) {
            var entry = $scope.receiveditems[i];
            entry.$selected = false;
        }

        var isDeselect = receiveditem === $scope.selectedReceiveditem;
        if (isDeselect) {
            $scope.selectedReceiveditem = null;
        } else {
            receiveditem.$selected = true;
            $scope.selectedReceiveditem = receiveditem;
        }
    };

    $scope.deleteReceiveditem = function() {
        $http.post('api/v1/receiveditems/delete/' + $scope.selectedReceiveditem.id)
        .success(function() {
            toaster.pop('success', global_translation['ALERT_DELETE_SUCCEED']);
            $scope.loadReceiveditems();
        });
    };

    $scope.showReceiveditemCreate = function() {
        ngDialog.open({
            scope: $scope,
            templateUrl: 'module/personaldata/receiveditems/receiveditem-create.html',
            controller: 'createReceiveditemCtrl'
        });
    };

    $scope.showReceiveditemEdit = function() {
        ngDialog.open({
            scope: $scope,
            templateUrl: 'module/personaldata/receiveditems/receiveditem-edit.html',
            controller: 'editReceiveditemCtrl'
        });
    };

    $scope.downloadReceiveditem = function() {
        $window.open('api/v1/receiveditems/download?id=' + $scope.selectedReceiveditem.id + '&X-Token=' + $http.defaults.headers.common['X-Token']);
    };

    $scope.loadReceiveditems();
}]);

app.controller('createReceiveditemCtrl', ['$scope', '$q', '$http', '$routeParams', 'toaster', function($scope, $q, $http, $routeParams, toaster) {
    $scope.form = {};
    $scope.storeAttachment = function(files) {
        var file = files[0];
        var reader = new FileReader();
        reader.onload = function() {
            $scope.form.adat = window.btoa(reader.result);
            $scope.form.eredetiNev = file.name;
            $scope.$digest();
        };
        reader.readAsBinaryString(file);
    };
    $scope.createReceiveditem = function() {
        $http.post('api/v1/receiveditems/create', {szemelyitorzs : {tsz: $routeParams.tsz}, megnevezes: $scope.form.megnevezes, megjegyzes: $scope.form.megjegyzes, eredetiNev: $scope.form.eredetiNev, adat: $scope.form.adat})
        .success(function() {
            toaster.pop('success', global_translation['ALERT_CREATE_SUCCEED']);
            $scope.closeThisDialog();
            $scope.loadReceiveditems();
        });
    };
}]);

app.controller('editReceiveditemCtrl', ['$scope', '$http', 'toaster', function($scope, $http, toaster) {
    $scope.form = angular.copy($scope.selectedReceiveditem);
    $scope.storeAttachment = function(files) {
        var file = files[0];
        var reader = new FileReader();
        reader.onload = function() {
            $scope.form.adat = window.btoa(reader.result);
            $scope.form.eredetiNev = file.name;
            $scope.$digest();
        };
        reader.readAsBinaryString(file);
    };
    $scope.saveReceiveditem = function() {
        $http.post('api/v1/receiveditems/edit', $scope.form)
        .success(function() {
            toaster.pop('success', global_translation['ALERT_SAVE_SUCCEED']);
            $scope.closeThisDialog();
            $scope.loadReceiveditems();
        });
    };
}]);
