(function() {
  'use strict';

  angular
  .module('hr2AngularUi')
  .config(routerConfig)
  .config(httpProviderWithCredentialsConfig)
  .controller('AngulardevloginController', AngulardevloginController);

  /** @ngInject */
  function routerConfig($stateProvider) {
    $stateProvider.state('angulardevlogin', {
      url : '/angulardevlogin',
      templateUrl : 'app/angulardevlogin/angulardevlogin.html',
      controller : 'AngulardevloginController',
      controllerAs : 'vm'
    });
  }

  /** @ngInject */
  function httpProviderWithCredentialsConfig($httpProvider) {
    $httpProvider.defaults.withCredentials = true;
  }

  /** @ngInject */
  function AngulardevloginController(applicationInfoService, moment, $log,
      $http, $state) {
     var vm = this;
     vm.status = 'Logging in with admin///admin\n';

    $http.get('http://localhost:8081/')
    .then(function(response) {
      vm.status += 'Creating http session and fetching csrf token\n';
      var body = response.data;
      var startCharSequence = 'name="_csrf" value="';
      var lengthOfCsrfToken = 36;
      var startPos = body.indexOf(startCharSequence) + startCharSequence.length;
      var csrfToken = body.substring(startPos, startPos+lengthOfCsrfToken);
      vm.status += '\t' + csrfToken + '\n';

      $http.post('http://localhost:8081/loginprocess?username=admin&password=admin&_csrf='+csrfToken)
      .then(function() {
        vm.status += 'Fetching userinfo\n';
        $http.defaults.headers.common['X-CSRF-TOKEN'] = csrfToken;
        var successCounter = 0;
        for (var i=0;i<10;i++) {
          $http.get('/api/v1/userinfo').then(function(response) {
            vm.status += '\t GET OK ' + angular.toJson(response.data) + '\n';
            successCounter++;
            if (successCounter === 20) {
              $state.go('app.dashboard');
            }
          });

          $http.post('/api/v1/users/delete/-1').then(function(response) {
            vm.status += '\t POST OK ' + angular.toJson(response.data) + '\n';
            successCounter++;
            if (successCounter === 20) {
              $state.go('app.dashboard');
            }
          });
        }
       });
    });
  }
})();
