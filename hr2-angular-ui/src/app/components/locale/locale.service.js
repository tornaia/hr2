(function() {
  'use strict';

  angular.module('hr2AngularUi').service('localeService', localeService);

  /** @ngInject */
  function localeService($q, tmhDynamicLocale, $translate, i18nService, moment, $log) {
    this.use = function(localeToUse) {
      var languageToUse = localeToUse.substring(0, 2);
      $log.debug('Set tmhDynamicLocale to use language ' + languageToUse);
      $log.debug('Set $translate to use locale ' + localeToUse);
      $log.debug('Set i18nService to use language ' + languageToUse);
      $log.debug('Set moment to use language ' + languageToUse);
      tmhDynamicLocale.set(languageToUse);
      var translateUsePromise = $translate.use(localeToUse);
      var i18nServiceSetCurrentLangPromise = i18nService.setCurrentLang(languageToUse);
      moment.locale(languageToUse);
      return $q.all([translateUsePromise, i18nServiceSetCurrentLangPromise]);
    };

  }
})();
