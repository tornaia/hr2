(function() {
  'use strict';
  angular.module('mock.applicationInfoService', []).factory('applicationInfoService', function($q) {
    var applicationInfoService = {};

    applicationInfoService.getApplicationInfo = function() {
      var mockApplicationInfoResponse =  {
                buildNumber : -1, 
                startDateTime : new Date(2015, 0, 16, 22, 55, 59, 647)
              };
      return $q.when(mockApplicationInfoResponse);
    };
    
    return applicationInfoService;
  });
})();