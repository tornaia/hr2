(function() {
  'use strict';

  /** @ngInject */
  angular.module('hr2AngularUi').filter('enumFilter', function($translate, _) {
    return function(input, searchString, enumClass) {
      searchString = (searchString || '').toLowerCase();
      return _.filter(input, function(item) {
        return $translate.instant('enum.' + enumClass + '.' + item).toLowerCase().indexOf(searchString) !== -1;
      });
    };
  });
})();
