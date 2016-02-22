app.controller('menuCtrl', [ '$scope', '$http', function($scope, $http) {
  $scope.store = {
    loggedIn : false,
    username : '',
    token : ''
  };

  $scope.globalMenu = [];

  $http.get('api/v1/userinfo').success(function(response) {
    $scope.userinfo = response;
  });

  $http.get('api/v1/menu').success(function(response) {
    $scope.globalMenu = response;
  });
} ]);

app.directive('menu', function() {
  return {
    restrict : 'E',
    templateUrl : 'module/core/menu/menu.html',
    controller : 'menuCtrl'
  };
});
