app.controller('dashboardCtrl', ['$scope', 'grantedAuthorities', function($scope, grantedAuthorities) {
    $scope.grantedAuthoritiesList = grantedAuthorities.list;
}]);

app.directive('dashboardBody', function() {
    return {
        restrict: 'E',
        templateUrl: 'module/dashboard/dashboard-body.html',
        controller: 'dashboardCtrl'
    };
});
