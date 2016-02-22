app.controller('reportsCtrl', ['$window', '$scope', '$http', '$filter', 'toaster', 'enums', function($window, $scope, $http, $filter, toaster, enums) {
    $scope.riportAllomanymodok = ['OSSZES'].concat(enums.allomanymodok);

    $scope.tszesnevlist = [];
    $http.get('api/v1/personaldata/getidandnamelist?all=true')
    .success(function(response) {
        $scope.tszesnevlist = response;
    });

    $scope.download = function(resource) {
        var tokenParam = 'X-Token=' + $http.defaults.headers.common['X-Token'];
        $window.open(resource + (resource.indexOf('?') === -1 ? '?' : '&') + tokenParam);
    };

    $scope.reportMEE = {};
    $scope.submitReportMedicalExaminationExpiryForm = function() {
        $scope.download('api/v1/report/uzemorvosiVizsgalatLejarat?kezdet=' + $filter('date')($scope.reportMEE.from, dateFormat) + '&veg=' + $filter('date')($scope.reportMEE.to, dateFormat));
    };

    $scope.reportIQE = {};
    $scope.submitReportIdentificationQualificationExpiryForm = function() {
        $scope.download('api/v1/report/igazolvanyokEsKepzettsegekLejarat?kezdet=' + $filter('date')($scope.reportIQE.from, dateFormat) + '&veg=' + $filter('date')($scope.reportIQE.to, dateFormat));
    };

    $scope.reportPPE = {};
    $scope.submitReportProbationaryPeriodEndForm = function() {
        $scope.download('api/v1/report/probaidoVege?kezdet=' + $filter('date')($scope.reportPPE.from, dateFormat) + '&veg=' + $filter('date')($scope.reportPPE.to, dateFormat));
    };

    $scope.reportLOP = {};
    $scope.submitReportListOfPeopleForm = function() {
        $scope.download('api/v1/report/nevsor?riportAllomanymod=' + $scope.reportLOP.allomanymod);
    };

    $scope.reportDLOP = {};
    $scope.submitReportDetailedListOfPeopleForm = function() {
        $scope.download('api/v1/report/allomanyiLista?');
    };

    $scope.reportEA = {};
    $scope.submitReportEmailAddressesForm = function() {
        $scope.download('api/v1/report/emailCimek?riportAllomanymod=' + $scope.reportEA.allomanymod);
    };

    $scope.reportPD = {};
    $scope.submitReportPersonaldataForm = function() {
        $scope.download('api/v1/report/alapadatok?riportAllomanymod=' + $scope.reportPD.allomanymod);
    };

    $scope.reportV = {};
    $scope.submitReportVacationForm = function() {
        $scope.download('api/v1/report/szabadsag?honap=' + $filter('date')($scope.reportV.month, monthFormat));
    };

    $scope.reportA = {};
    $scope.submitReportAttendanceForm = function() {
        $scope.download('api/v1/report/jelenletiIv?honap=' + $filter('date')($scope.reportA.month, monthFormat));
    };

    $scope.reportEAL = {};
    $scope.submitReportEntrantsAndLeaversForm = function() {
        $scope.download('api/v1/report/beEsKilepok?kezdet=' + $filter('date')($scope.reportEAL.from, dateFormat) + '&veg=' + $filter('date')($scope.reportEAL.to, dateFormat));
    };

    $scope.reportDLOP = {};
    $scope.submitDetailedListOfPeopleForm = function() {
        $scope.download('api/v1/report/kigyujtesiLista?riportAllomanymod=' + $scope.reportDLOP.allomanymod);
    };

    $scope.reportRI = {};
    $scope.submitReceivedItemsForm = function() {
        $scope.download('api/v1/report/atvettEszkozok?tsz=' + $scope.reportRI.szemelyitorzs.tsz);
    };

    $scope.reportMPD = {};
    $scope.submitMonthlyPayrollDataForm = function() {
        $scope.download('api/v1/report/haviSzamfejtesiAdatok?honap=' + $filter('date')($scope.reportMPD.month, monthFormat));
    };
}]);

app.directive('reportsBody', function() {
    return {
        restrict: 'E',
        templateUrl: 'module/reports/reports-body.html',
        controller: 'reportsCtrl'
    };
});
