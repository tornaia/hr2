app.controller('logoutCtrl', ['$scope', '$http', '$location', 'grantedAuthorities', 'toaster', function($scope, $http, $location, grantedAuthorities, toaster) {
    grantedAuthorities.list = ['NEM_VEDETT'];
    delete $http.defaults.headers.common['X-Token'];
    $location.path('/');
    toaster.pop('success', global_translation['LOGOUT_SUCCEED']);
}]);

app.directive('logoutBody', function() {
    return {
        restrict: 'E',
        templateUrl: 'module/logout/logout-body.html',
        controller: 'logoutCtrl'
    };
});
