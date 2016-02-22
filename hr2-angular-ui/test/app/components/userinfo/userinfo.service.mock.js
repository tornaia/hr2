(function() {
  'use strict';
  angular.module('mock.userInfoService', []).factory('userInfoService', function($q) {
    var userInfoService = {};

    userInfoService.getUserInfo = function() {
      var mockUserInfoResponse =   {
                username : 'admin', 
                grantedAuthorities : ['ROLE_ADMINISTRATOR', 'ROLE_BETEKINTO', 'ROLE_DOLGOZO', 'ROLE_NEM_VEDETT']
              };
      return $q.when(mockUserInfoResponse);
    };

    return userInfoService;
  });
})();