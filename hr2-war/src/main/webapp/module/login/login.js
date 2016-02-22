app.directive('loginForm', [
    '$window',
    '$http',
    '$location',
    '$routeParams',
    '$timeout',
    'grantedAuthorities',
    'toaster',
    function($window, $http, $location, $routeParams, $timeout,
        grantedAuthorities, toaster) {
      return {
        restrict : 'E',
        templateUrl : 'module/login/login-form.html',
        controller : function($scope) {
          $scope.form = {
            username : '',
            password : ''
          };
          $scope.login = function() {
            $http.post('loginprocess', $scope.form).success(
                function(data) {
                  grantedAuthorities.list = data.grantedAuthorities;
                  console
                      .log('Granted authorities: ' + grantedAuthorities.list);
                  $http.defaults.headers.common['X-Token'] = data.token;
                  console.log('X-Token: ' + data.token);
                  $location.path($routeParams.redirect ? $routeParams.redirect
                      .replace(/_/g, '/') : 'dashboard');
                  toaster.pop('success', global_translation['LOGIN_SUCCEED'],
                      data.username);
                }).error(function() {
              $scope.form.password = '';
            });
          };
          $timeout(function() {
            $("[ng-model='form.username']").focus();
          }, 100);
        }
      };
    } ]);

app.directive('footer', function() {
  return {
    restrict : 'E',
    templateUrl : 'module/login/footer.html',
    scope : {
      translation : '='
    },
    controller : function($scope, $http) {
      $http.get('api/v1/applicationinfo').then(function(result) {
        console.log('App version: ' + result.data.buildNumber);
        $scope.buildNumber = result.data.buildNumber;
      });
    }
  };
});
