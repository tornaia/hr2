(function() {
  'use strict';

  angular
  .module('hr2AngularUi')
  .directive('roleRequired', roleRequired);

  /** @ngInject */
  function roleRequired(roleRequiredService) {
    return {
      restrict: 'A',
      link: function(scope, element, attrs) {
        var requiredRole = attrs['roleRequired'];
        var hasRole = roleRequiredService.hasRole(requiredRole);
        if (!hasRole) {
          element[0].parentNode.removeChild(element[0]);
        }
      }
    };
  }

})();