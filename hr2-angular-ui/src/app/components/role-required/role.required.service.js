(function() {
  'use strict';

  angular.module('hr2AngularUi').service('roleRequiredService', roleRequiredService);

  /** @ngInject */
  function roleRequiredService(userInfoCache) {

    this.hasRole = function(requiredRole) {
      var grantedAuthorities = userInfoCache.get('grantedAuthorities');
      return grantedAuthorities.indexOf(requiredRole) !== -1;
    };

  }
})();
