app.controller('timeandattendanceCtrl', ['$scope', '$http', '$routeParams', '$window', '$filter', '$q', 'ngTableParams', 'ngDialog', 'toaster', 'enums', 'grantedAuthorities', function($scope, $http, $routeParams, $window, $filter, $q, ngTableParams, ngDialog, toaster, enums, grantedAuthorities) {
    $scope.jelenletiAdatTipusok = enums.jelenletiAdatTipusok;

    $scope.selectedMonth = new Date();

    $scope.isAdministrator = arrayContains(grantedAuthorities.list, 'ADMINISTRATOR');
    $scope.honapSzerkesztheto = arrayContains(grantedAuthorities.list, 'ADMINISTRATOR');

    $scope.exceptiondays = [];
    $scope.timeandattendancerows = [];
    $scope.timeandattendanceTableParams = new ngTableParams({
        sorting: {
            datum: 'asc'
        }
    }, {
        getData: function($defer, params) {
             $defer.resolve($scope.timeandattendancerows);
        }
    });

     $scope.changeTimeandattendancerowSelection = function(timeandattendancerow) {
        for (var i=0; i<$scope.timeandattendancerows.length; ++i) {
            var entry = $scope.timeandattendancerows[i];
            entry.$selected = false;
        }

        var isDeselect = timeandattendancerow === $scope.selectedTimeandattendancerow;
        if (isDeselect) {
            $scope.selectedTimeandattendancerow = null;
        } else {
            timeandattendancerow.$selected = true;
            $scope.selectedTimeandattendancerow = timeandattendancerow;
        }
    };

     $scope.saveTimeandattendance = function() {
        $http.post('api/v1/timeandattendance/edit', $scope.timeandattendanceDetails)
        .success(function() {
            toaster.pop('success', global_translation['ALERT_SAVE_SUCCEED']);
            $scope.loadTimeandattendance();
        });
     };

     $scope.isFreeday = function(date) {
         for (var i=0;i<$scope.exceptiondays.length;++i) {
             var exRow = $scope.exceptiondays[i];
             if (isSameDate(new Date(exRow.datum), date) && exRow.tipus === 'PIHENONAP') {
                 return true;
             }
         }
         for (var i=0;i<$scope.exceptiondays.length;++i) {
             var exRow = $scope.exceptiondays[i];
             if (isSameDate(new Date(exRow.datum), date) && exRow.tipus === 'MUNKANAP') {
                 return false;
             }
         }

         return isSunday(date);
     };

     $scope.isNotWorkingday = function(date) {
         if ($scope.isFreeday(date)) {
             return false;
         }
         for (var i=0;i<$scope.exceptiondays.length;++i) {
             var exRow = $scope.exceptiondays[i];
             if (isSameDate(new Date(exRow.datum), date) && exRow.tipus === 'MUNKANAP') {
                 return false;
             }
         }

         return isSaturaday(date);
     };

     $scope.monthChanged = function() {
         $scope.loadTimeandattendance();
     };

     $scope.loadTimeandattendance = function() {
        $q.all([$http.get('api/v1/exceptiondays/findbymonth?month=' + $filter('date')($scope.selectedMonth, monthFormat)), $http.get('api/v1/timeandattendance/get?tsz=' + $routeParams.tsz + '&honap=' + $filter('date')($scope.selectedMonth, monthFormat))]).then(function(result) {
             $scope.exceptiondays = result[0].data;
            $scope.timeandattendanceDetails = result[1].data;
            $scope.timeandattendancerows = result[1].data.jelenletiAdatok;

            for (var i=0;i<$scope.timeandattendancerows.length;++i) {
                var row = $scope.timeandattendancerows[i];
                row.freeday = $scope.isFreeday(new Date(row.datum));
                row.notworkingday = $scope.isNotWorkingday(new Date(row.datum));
            }

            $scope.selectedTimeandattendancerow = null;
            $scope.honapSzerkesztheto = arrayContains(grantedAuthorities.list, 'ADMINISTRATOR') || $scope.timeandattendanceDetails.honapSzerkesztheto;
            if (arrayContains(grantedAuthorities.list, 'BETEKINTO') && !arrayContains(grantedAuthorities.list, 'ADMINISTRATOR')) {
                $scope.honapSzerkesztheto = false;
            }

            setTimeout(function () {
                $scope.$apply(function () {
                    $scope.timeandattendanceTableParams.reload();
                });
            }, 0);
         });
    };

    $scope.lockMonth = function() {
        $http.post('api/v1/timeandattendance/lockmonth?month=' + $filter('date')($scope.selectedMonth, monthFormat))
        .success(function() {
            toaster.pop('success', global_translation['ALERT_MONTH_LOCK_SUCCEED']);
            $scope.loadTimeandattendance();
        });
    };

    $scope.unlockMonth = function() {
        $http.post('api/v1/timeandattendance/unlockmonth?month=' + $filter('date')($scope.selectedMonth, monthFormat))
        .success(function() {
            toaster.pop('success', global_translation['ALERT_MONTH_UNLOCK_SUCCEED']);
            $scope.loadTimeandattendance();
        });
    };

    $scope.timeandattendancerowChanged = function(timeandattendancerow) {
        if (timeandattendancerow.tipus === 'MUNKA') {
             timeandattendancerow.kezdet = convertTimeStringToDate('08:00');
             timeandattendancerow.veg = convertTimeStringToDate('16:30');
             timeandattendancerow.ledolgozott = convertTimeStringToDate('08:00');
             timeandattendancerow.to50 = timeandattendancerow.to100 = timeandattendancerow.m30 = null;
        } else {
            timeandattendancerow.kezdet = timeandattendancerow.veg = timeandattendancerow.ledolgozott = timeandattendancerow.to50 = timeandattendancerow.to100 = timeandattendancerow.m30 = null;
        }
    };

    if (parseInt($routeParams.tsz) !== 0) {
        $scope.loadTimeandattendance();
    }

}]);
