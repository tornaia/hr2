(function() {
  'use strict';

  angular
    .module('hr2AngularUi')
    .run(runBlock);

  /** @ngInject */
  function runBlock($log) {
    $log.debug('App started');
  }

})();
