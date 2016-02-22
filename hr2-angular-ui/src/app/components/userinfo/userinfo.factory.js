(function() {
  'use strict';

  angular
  .module('hr2AngularUi')
  .factory('userInfoCache', userInfoCache);

  /** @ngInject */
  function userInfoCache($cacheFactory) {
    return $cacheFactory('userInfo');
  }
})();