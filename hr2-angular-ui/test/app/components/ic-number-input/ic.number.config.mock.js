(function() {
  'use strict';

  angular
  .module('mock.icNumberConfig', [])
  .provider('icNumberConfig', function() {

    var _defaultOptions;
    _defaultOptions = {};
    this.setDefaultOptions = function(defaultOptions) {
      return _defaultOptions = defaultOptions;
    };
    this.$get = function() {
      return {
        defaultOptions: _defaultOptions
      };
    };
  });
})();
