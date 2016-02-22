(function() {
  'use strict';

  angular
    .module('hr2AngularUi')
    .factory('messagesUrlLoader', messagesUrlLoader)
    .factory('missingTranslationHandler', missingTranslationHandler)
    .factory('_', lodashFactory)
    .config(configLogProvider)
    .config(configToastr)
    .config(configTranslateProvider)
    .config(configTmhDynamicLocaleProvider)
    .config(httpOnErrorResponse);

  /** @ngInject */
  function configLogProvider($logProvider) {
    $logProvider.debugEnabled(true);
  }

  /** @ngInject */
  function configToastr(toastrConfig) {
    toastrConfig.allowHtml = true;
    toastrConfig.timeOut = 3000;
    toastrConfig.positionClass = 'toast-top-right';
    toastrConfig.progressBar = true;
  }

  /** @ngInject */
  function configTranslateProvider($translateProvider) {
    $translateProvider
    .useMissingTranslationHandler('missingTranslationHandler')
    .useLoader('messagesUrlLoader')
    .useMessageFormatInterpolation()
    .useSanitizeValueStrategy('escaped');
  }

  /** @ngInject */
  function configTmhDynamicLocaleProvider(tmhDynamicLocaleProvider) {
    tmhDynamicLocaleProvider.localeLocationPattern('assets/i18n/angular-locale_{{locale}}.js');
  }

  /** @ngInject */
  function httpOnErrorResponse($httpProvider, $windowProvider) {
    function logoutAndRedirectBackToLogin() {
      var xhttp = new XMLHttpRequest();
      xhttp.onreadystatechange = function() {
        var $window = $windowProvider.$get();
        $window.location.href = '/';
      };
      xhttp.open('GET', '/logout', true);
      xhttp.send();
    }
    $httpProvider.interceptors.push(function($q) {
      return {
        'response': function (response) {
          var startOfIndexJsp = '<!DOCTYPE html>';
          var redirectProbablyOnTimeout = angular.isString(response.data) && response.data.indexOf(startOfIndexJsp) === 0;
          if (redirectProbablyOnTimeout) {
            logoutAndRedirectBackToLogin();
          }
          return response;
        },
        'responseError': function(rejection) {
          var status = rejection.status;
          var noPrivilege = status === 401 || status === 403;
          if (noPrivilege) {
            logoutAndRedirectBackToLogin();
          }
          return $q.reject(rejection);
        }
      };
    });
  }

  /** @ngInject */
  function messagesUrlLoader($http, $log) {
    return function (options) {
      return $http.get('/api/v1/messages/' + options.key).then(
          function (response) {
            return response.data;
          }, function () {
            $log.error('Failed to get messages: ' + options.key);
            return {};
          }
      );
    };
  }

  /** @ngInject */
  function missingTranslationHandler($log) {
    return function (translationID) {
      $log.warn('Missing translationID: ' + translationID);
      return '[ ' + translationID + ' ]';
    };
  }

  /** @ngInject */
  function lodashFactory($window) {
      // Get a local handle on the global lodash reference.
      var _ = $window._;
      // OPTIONAL: Sometimes I like to delete the global reference to make sure
      // that no one on the team gets lazy and tried to reference the library
      // without injecting it. It's an easy mistake to make, and one that won't
      // throw an error (since the core library is globally accessible).
      // ALSO: See .run() block above.
      delete ($window._);
      // Return the [formerly global] reference so that it can be injected
      // into other aspects of the AngularJS application.
      return (_);
  }

})();
