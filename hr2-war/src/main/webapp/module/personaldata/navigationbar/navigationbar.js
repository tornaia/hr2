app.controller('navigationbarCtrl', ['$scope', '$http', '$routeParams', '$location', '$filter', '$q', 'ngTableParams', 'ngDialog', 'toaster', function($scope, $http, $routeParams, $location, $filter, $q, ngTableParams, ngDialog, toaster) {
    $scope.tokenParam = '&X-Token=' + $http.defaults.headers.common['X-Token'];

    $scope.filter = $routeParams.filter;
    $scope.showAll = $scope.filter === 'all';
    $scope.visiblePersonalDetailIds = [];
    $scope.indexOfSelected = -1;
    $scope.selectedPersonaldata = undefined;
    $scope.profilePicture = undefined;

    $scope.load = function() {
        $scope.indexOfSelected = -1;
        if ($scope.showAll) {
            $scope.loadAllPersonalDetailId();
        } else {
            $scope.loadAllActivePersonalDetailId();
        }
    };

    $scope.changeFilter = function(filter) {
        $scope.showAll = filter === 'all';
        $location.path('/personaldata/' + filter + '/' + $routeParams.tsz);
        var eventTriggeredFromAngularWorld = $scope.$$phase;
        if (!eventTriggeredFromAngularWorld) {
            $scope.$apply();
        }
    };

    $scope.loadAllActivePersonalDetailId = function() {
        $http.get('api/v1/personaldata/getallactive')
        .success(function(response) {
            $scope.visiblePersonalDetailIds = response;
            if (parseInt($routeParams.tsz) === 0) {
                if ($scope.visiblePersonalDetailIds.length > 0) {
                    $location.path('/personaldata/' + $routeParams.filter + '/' + $scope.visiblePersonalDetailIds[0]);
                } else {
                    $location.path('/personaldata/' + $routeParams.filter + '/' + '-1');
                }
                return;
            }
            $scope.updateIndexOfSelected();
            $scope.loadPersonalDetail();
        });
    };

    $scope.loadAllPersonalDetailId = function() {
        $http.get('api/v1/personaldata/getall')
        .success(function(response) {
            $scope.visiblePersonalDetailIds = response;
            if (parseInt($routeParams.tsz) === 0) {
                if ($scope.visiblePersonalDetailIds.length > 0) {
                    $location.path('/personaldata/' + $routeParams.filter + '/' + $scope.visiblePersonalDetailIds[0]);
                } else {
                    $location.path('/personaldata/' + $routeParams.filter + '/' + '-1');
                }
                return;
            }
            $scope.updateIndexOfSelected();
            $scope.loadPersonalDetail();
        });
    };

    $scope.loadPersonalDetail = function() {
        var isCreateNew = $routeParams.tsz === undefined;
        if (isCreateNew) {
            $scope.selectedPersonaldata = $scope.createNewPersonaldata();
        } else {
            $http.get('api/v1/personaldata/get?tsz=' + $routeParams.tsz)
            .success(function(response) {
                $scope.selectedPersonaldata = response;
            });
        }
        $scope.reloadPicture();
    };

    $scope.updateIndexOfSelected = function() {
        if ($routeParams.tsz === undefined) {
            return;
        }

        for (var i=0;i<$scope.visiblePersonalDetailIds.length;i++) {
            if (parseInt($routeParams.tsz) === $scope.visiblePersonalDetailIds[i]) {
                $scope.indexOfSelected = i;
                break;
            }
        }

        if ($scope.indexOfSelected === -1 && $scope.visiblePersonalDetailIds.length === 0) {
            $location.path('/personaldata/' + $routeParams.filter + '/' + '-1');
            return;
        }

        if ($scope.indexOfSelected === -1 && ($scope.visiblePersonalDetailIds.length > 0)) {
            $scope.setIndexOfSelected(0);
        }
    };

    $scope.setIndexOfSelected = function(idx) {
        $scope.indexOfSelected = idx;
        var filter = $routeParams.filter ? $routeParams.filter : 'active';
        if (idx === -1) {
            $location.path('/personaldata/' + filter + '/' + '-1');
        } else {
            $location.path('/personaldata/' + filter + '/' + $scope.visiblePersonalDetailIds[idx]);
        }

        var eventTriggeredFromAngularWorld = $scope.$$phase;
        if (!eventTriggeredFromAngularWorld) {
            $scope.$apply();
        }
    };

    $scope.jumpFirst = function() {
        if ($scope.visiblePersonalDetailIds.length === 0 || $scope.indexOfSelected === 0) {
            return;
        }
        $scope.setIndexOfSelected(0);
    };

    $scope.stepBack = function() {
        if ($scope.indexOfSelected-1 < 0) {
            return;
        }
        $scope.setIndexOfSelected($scope.indexOfSelected-1);
    };

    $scope.stepForward = function() {
        if ($scope.indexOfSelected+1 >= $scope.visiblePersonalDetailIds.length) {
            return;
        }
        $scope.setIndexOfSelected($scope.indexOfSelected+1);
    };

    $scope.jumpLast = function() {
        if ($scope.visiblePersonalDetailIds.length === 0 || $scope.visiblePersonalDetailIds.length-1 === $scope.indexOfSelected) {
            return;
        }
        $scope.setIndexOfSelected($scope.visiblePersonalDetailIds.length-1);
    };

    $scope.showPersonaldataCreate = function() {
        $location.path('/personaldata/create');

        var eventTriggeredFromAngularWorld = $scope.$$phase;
        if (!eventTriggeredFromAngularWorld) {
            $scope.$apply();
        }
    };

    $scope.showPersonaldataSearch = function() {
        ngDialog.open({
            scope: $scope,
            templateUrl: 'module/personaldata/popup-search.html',
            controller: 'searchPersonaldataCtrl'
        });
    };

    $scope.cancelPersonaldata = function() {
        $scope.load();
    };

    $scope.createPersonaldata = function() {
        $http.post('api/v1/personaldata/create', $scope.selectedPersonaldata)
        .success(function() {
            toaster.pop('success', global_translation['ALERT_CREATE_SUCCEED']);
            $location.path('/personaldata/active/' + $scope.selectedPersonaldata.tsz);
        });
    };

    $scope.savePersonaldata = function() {
        $http.post('api/v1/personaldata/edit', $scope.selectedPersonaldata)
        .success(function() {
            toaster.pop('success', global_translation['ALERT_SAVE_SUCCEED']);
            $scope.load();
        });
    };

    $scope.deletePersonaldata = function() {
        $http.post('api/v1/personaldata/delete/' + $scope.selectedPersonaldata.tsz)
        .success(function() {
            toaster.pop('success', global_translation['ALERT_DELETE_SUCCEED']);
            $location.path('/personaldata/' + $routeParams.filter + '/' + '0');
        });
    };

    $scope.createNewPersonaldata = function() {
        return {tsz: undefined,
                szemelyiAdatok:         {},
                munkakoriBesorolas:     { munkaidoNapi: 8,
                                          munkaidoHeti: 40
                                        },
                jogviszonyAdatok:       { allomanymod: 'AKTIV'
                                        },
                csalad:                 {},
                orvosiVizsgalat:        { gyakorisag: 12
                                        },
                szabadsagnyilvantartas: { megvaltottSzabadsag: 0}
            };
    };

    $scope.showPersonaldataComment = function() {
        ngDialog.open({
            templateUrl: 'module/personaldata/popup-comment.html',
            controller: 'commentCtrl'
        });
    };

    $scope.showPersonaldataPhoto = function() {
        ngDialog.open({
            scope: $scope,
            templateUrl: 'module/personaldata/popup-photos.html',
            controller: 'photosCtrl'
        });
    };

    $scope.reloadPicture = function() {
        var forceReloadPostfix = '#' + new Date().getTime();
        $scope.profilePicture = 'api/v1/photos/getdefault?tsz=' + $routeParams.tsz + $scope.tokenParam  + forceReloadPostfix;
    };

    $scope.load();
}]);

app.controller('commentCtrl', ['$scope', '$http', '$routeParams', 'toaster', function($scope, $http, $routeParams, toaster) {
    $http.get('api/v1/comment/get?tsz=' + $routeParams.tsz)
    .success(function(response) {
        $scope.comment = response;
    });

    $scope.save = function() {
        $http.post('api/v1/comment/save', $scope.comment)
        .success(function() {
            toaster.pop('success', global_translation['ALERT_SAVE_SUCCEED']);
            $scope.closeThisDialog();
        });
    };
}]);

app.controller('photosCtrl', ['$scope', '$http', '$routeParams', '$window', 'ngTableParams', 'toaster', function($scope, $http, $routeParams, $window, ngTableParams, toaster) {
    $scope.tokenParam = '&X-Token=' + $http.defaults.headers.common['X-Token'];

    $scope.photos = [];
    $scope.resetSelectedItemIfItsNotVisible = function(visibleItems) {
        for (var i=0;i<visibleItems.length;i++) {
            if (visibleItems[i] === $scope.selectedPhoto) {
                return;
            }
        }
        $scope.changePhotoSelection($scope.selectedPhoto);
    };

     $scope.changePhotoSelection = function(photo) {
            for (var i=0; i<$scope.photos.length; ++i) {
                var entry = $scope.photos[i];
                entry.$selected = false;
            }

            var isDeselect = photo === $scope.selectedPhoto;
            if (isDeselect) {
                $scope.selectedPhoto = null;
            } else {
                photo.$selected = true;
                $scope.selectedPhoto = photo;
            }
        };

    $scope.load = function() {
        $http.get('api/v1/photos/list?tsz=' + $routeParams.tsz)
        .success(function(response) {
            $scope.photos = response.fenykepek;
            $scope.selectedPhoto = null;
        });
    };

    $scope.defaultPicSelected = function(photo) {
        for (var i=0; i<$scope.photos.length; ++i) {
            var entry = $scope.photos[i];
            entry.miniatur = entry === photo;
        }
    };

    $scope.save = function() {
        $http.post('api/v1/photos/save', { fenykepek: $scope.photos})
        .success(function() {
            $scope.closeThisDialog();
            $scope.reloadPicture();
        });
    };

    $scope.delete = function(photo) {
        $http.post('api/v1/photos/delete/' + photo.id)
        .success(function() {
            toaster.pop('success', global_translation['ALERT_DELETE_SUCCEED']);
            $scope.load();
            $scope.reloadPicture();
        });
    };

    $scope.form = {};
    $scope.storePhoto = function(files) {
        var file = files[0];
        var reader = new FileReader();
        reader.onload = function() {
            $scope.form.tsz = $routeParams.tsz;
            $scope.form.adat = window.btoa(reader.result);
            $scope.form.eredetiNev = file.name;
            $http.post('api/v1/photos/create', $scope.form)
            .success(function() {
                $scope.load();
            });
        };
        reader.readAsBinaryString(file);
    };

    $scope.download = function() {
        $window.open('api/v1/photos/download?id=' + $scope.selectedPhoto.id + '&X-Token=' + $http.defaults.headers.common['X-Token']);
    };

    $scope.load();
}]);

app.controller('searchPersonaldataCtrl', ['$scope', '$filter', '$timeout', '$http', '$location', 'ngTableParams', function($scope, $filter, $timeout, $http, $location, ngTableParams) {

    $scope.selectDisabled = false;
    $scope.tszesnevlist = [];
    $http.get('api/v1/personaldata/getidandnamelist?all=' + $scope.showAll)
    .success(function(response) {
        $scope.tszesnevlist = response.tszekEsNevek;
        angular.forEach($scope.tszesnevlist, function(value, key) {
            value.nev = value.vezeteknev + ' ' + value.keresztnev;
        });

        $scope.searchPersonaldataTableParams.reload();
    });

    $scope.searchPersonaldataTableParams = new ngTableParams({
        page: 1,
        count: 10,
        sorting: {
            tsz: 'asc'
        },
        filter: {
            tsz: '',
            nev: ''
        }
    }, {
        total: $scope.tszesnevlist.length,
        filterDelay: 0,
        getData: function($defer, params) {
            if (($scope.tszesnevlist.length + $scope.searchPersonaldataTableParams.count() - 1) / $scope.searchPersonaldataTableParams.count() < $scope.searchPersonaldataTableParams.page()) {
                $scope.searchPersonaldataTableParams.page($scope.tszesnevlist.length / $scope.searchPersonaldataTableParams.count());
            }
            var filteredData = params.filter() ? $filter('filter')($scope.tszesnevlist, params.filter()) : $scope.tszesnevlist;
            var orderedAndFilteredData = params.sorting() ? $filter('orderBy')(filteredData, params.orderBy()) : filteredData;
            params.total(orderedAndFilteredData.length);
            var currentPage = Math.max(params.page(), 1);
            var itemsPerPage = params.count();
            var fromIdx = (currentPage - 1) * itemsPerPage;
            var toIdx = (currentPage) * itemsPerPage;
            var items = orderedAndFilteredData.slice(fromIdx, toIdx);
            $defer.resolve(items);
            $scope.resetSelectedItemIfItsNotVisible(items);
            $scope.selectDisabled = items.length !== 1 && $scope.selectedTszesnev == null;
        }
    });

    $scope.resetSelectedItemIfItsNotVisible = function(visibleItems) {
        for (var i=0;i<visibleItems.length;i++) {
            if (visibleItems[i] === $scope.selectedTszesnev) {
                return;
            }
        }
        $scope.changeTszesnevSelection($scope.selectedTszesnev);
    };

     $scope.changeTszesnevSelection = function(tszesnev) {
        for (var i=0; i<$scope.tszesnevlist.length; ++i) {
            var entry = $scope.tszesnevlist[i];
            entry.$selected = false;
        }

        var isDeselect = tszesnev === $scope.selectedTszesnev;
        if (isDeselect) {
            $scope.selectedTszesnev = null;
        } else {
            tszesnev.$selected = true;
            $scope.selectedTszesnev = tszesnev;
        }
        $scope.selectDisabled = $scope.searchPersonaldataTableParams.data.length !== 1 && ($scope.selectedTszesnev == null);
    };

     $scope.selectTszesnev = function() {
        $location.path('/personaldata/' + ($scope.showAll ? 'all' : 'active') + '/' + ($scope.selectedTszesnev != null ? $scope.selectedTszesnev.tsz : $scope.searchPersonaldataTableParams.data[0].tsz));
        $scope.closeThisDialog();
     };

    $timeout(function() {$("[ng-model='params.filter()[name]']").focus();}, 100);
}]);

app.directive('navigationbarCreate', function() {
    return {
        require: '^tabPersonalDetails',
        restrict: 'E',
        templateUrl: 'module/personaldata/navigationbar/navigationbar-create.html',
        controller: 'navigationbarCtrl'
    };
});

app.directive('navigationbarEdit', function() {
    return {
        require: '^tabPersonalDetails',
        restrict: 'E',
        templateUrl: 'module/personaldata/navigationbar/navigationbar-edit.html',
        controller: 'navigationbarCtrl'
    };
});
