app.controller('personaldataCtrl', ['$scope', '$routeParams', '$http', '$location', '$filter', '$q', 'ngTableParams', 'ngDialog', 'toaster', 'enums', function($scope, $routeParams, $http, $location, $filter, $q, ngTableParams, ngDialog, toaster, enums) {
    $scope.allomanymodok = enums.allomanymodok;
    $scope.nemek = enums.nemek;
    $scope.kozteruletTipusok = enums.kozteruletTipusok;
    $scope.munkakorJellegek = enums.munkakorJellegek;
    $scope.jogviszonyMegszunesenekModjai = enums.jogviszonyMegszunesenekModjai;
    $scope.csaladiAllapotok = enums.csaladiAllapotok;

    $scope.calculateNextMedicalExaminationDate = function() {
        if ($scope.selectedPersonaldata) {
            var utolsoOrvosiVizsgalatIdopontjaAsDate = $scope.selectedPersonaldata.orvosiVizsgalat.utolsoOrvosiVizsgalatIdopontja;
            var kovetkezoOrvosiVizsgalatIdopontjaAsDate = addMonths(utolsoOrvosiVizsgalatIdopontjaAsDate, $scope.selectedPersonaldata.orvosiVizsgalat.gyakorisag);
            $scope.selectedPersonaldata.orvosiVizsgalat.kovetkezoOrvosiVizsgalatIdopontja = kovetkezoOrvosiVizsgalatIdopontjaAsDate;
        }
    };

    var healthAssessmentAttributesToWatch = ['selectedPersonaldata.orvosiVizsgalat.utolsoOrvosiVizsgalatIdopontja', 'selectedPersonaldata.orvosiVizsgalat.gyakorisag'];
    for (var i=0;i<healthAssessmentAttributesToWatch.length;++i) {
        $scope.$watch(healthAssessmentAttributesToWatch[i], $scope.calculateNextMedicalExaminationDate);
    }
}]);

app.directive('personaldataBodyCreate', function() {
    return {
        restrict: 'E',
        templateUrl: 'module/personaldata/personaldata-body-create.html',
        controller: 'personaldataCtrl'
    };
});

app.directive('personaldataBodyEdit', function() {
    return {
        restrict: 'E',
        templateUrl: 'module/personaldata/personaldata-body-edit.html',
        controller: 'personaldataCtrl'
    };
});

app.directive('tabPersonalDetailsCreate', function() {
    return {
        require: '^personaldataBody',
        restrict: 'E',
        templateUrl: 'module/personaldata/tab-personal-details-create.html'
    };
});

app.directive('tabPersonalDetailsEdit', function() {
    return {
        require: '^personaldataBody',
        restrict: 'E',
        templateUrl: 'module/personaldata/tab-personal-details-edit.html'
    };
});

app.directive('tabJobClassification', function() {
    return {
        require: '^personaldataBody',
        restrict: 'E',
        templateUrl: 'module/personaldata/tab-job-classification.html'
    };
});

app.directive('tabEmploymentStatus', function() {
    return {
        require: '^personaldataBody',
        restrict: 'E',
        templateUrl: 'module/personaldata/tab-employment-status.html'
    };
});

app.directive('tabQualification', function() {
    return {
        require: '^personaldataBody',
        restrict: 'E',
        templateUrl: 'module/personaldata/tab-qualification.html'
    };
});

app.directive('tabReceivedItems', function() {
    return {
        require: '^personaldataBody',
        restrict: 'E',
        templateUrl: 'module/personaldata/tab-received-items.html'
    };
});

app.directive('tabFamily', function() {
    return {
        require: '^personaldataBody',
        restrict: 'E',
        templateUrl: 'module/personaldata/tab-family.html'
    };
});

app.directive('tabHealthAssessment', function() {
    return {
        require: '^personaldataBody',
        restrict: 'E',
        templateUrl: 'module/personaldata/tab-health-assessment.html'
    };
});

app.directive('tabVacation', function() {
    return {
        require: '^personaldataBody',
        restrict: 'E',
        templateUrl: 'module/personaldata/tab-vacation.html',
        controller: 'vacationsCtrl'
    };
});

app.directive('tabTimeAndAttendance', function() {
    return {
        require: '^personaldataBody',
        restrict: 'E',
        templateUrl: 'module/personaldata/tab-time-and-attendance.html',
        controller: 'timeandattendanceCtrl'
    };
});

app.directive('jobhistoryTable', function() {
    return {
        scope: true,
        restrict: 'E',
        templateUrl: 'module/personaldata/jobhistory/jobhistory-table.html',
        controller: 'jobhistoryCtrl'
    };
});

app.directive('qualificationsTable', function() {
    return {
        scope: true,
        restrict: 'E',
        templateUrl: 'module/personaldata/qualifications/qualifications-table.html',
        controller: 'qualificationsCtrl'
    };
});

app.directive('receiveditemsTable', function() {
    return {
        scope: true,
        restrict: 'E',
        templateUrl: 'module/personaldata/receiveditems/receiveditems-table.html',
        controller: 'receiveditemsCtrl'
    };
});

app.directive('familymembersTable', function() {
    return {
        scope: true,
        restrict: 'E',
        templateUrl: 'module/personaldata/familymembers/familymembers-table.html',
        controller: 'familymembersCtrl'
    };
});
