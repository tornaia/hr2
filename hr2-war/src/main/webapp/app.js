var app = angular.module('hrIntranetApplication', ['ngRoute', 'ngResource', 'ngTable', 'ngDialog', 'ngAnimate', 'ui.bootstrap']);

// http://stackoverflow.com/questions/15637133/unsafe-link-in-angular
app.config(['$compileProvider', function($compileProvider) {
    $compileProvider.aHrefSanitizationWhitelist(/^\s*(https?|ftp|mailto|chrome-extension):/);
}]);

app.config(['$routeProvider', '$httpProvider', function($routeProvider, $httpProvider) {
        var unauthorized = $httpProvider.defaults.headers.common['X-Token'] === undefined;
        var isLoginPage = window.location.hash === '' || window.location.hash === '#/';
        if (unauthorized && !isLoginPage) {
            var loc = window.location.hash.substring('#/'.length);
            if (loc.indexOf('login') === 0) {
                window.location = '#/' + loc;
            } else {
                loc = loc.replace(/\//g, '_');
                window.location = '#/login/' + loc;
            }
        }

      // Router
      $routeProvider.
          when('/', {
            redirectTo: '/login'
          }).
          when('/login', {
              templateUrl:'module/login/login.html'
          }).
          when('/login/:redirect', {
              templateUrl:'module/login/login.html'
          }).
        when('/dashboard', {
            templateUrl: 'module/dashboard/dashboard.html'
        }).
        when('/logout', {
            templateUrl: 'module/logout/logout.html'
        }).
        when('/timeandattendance/:tsz', {
            templateUrl: 'module/timeandattendance/timeandattendance.html'
        }).
        when('/personaldata', {
            redirectTo: '/personaldata/active/0'
        }).
        when('/personaldata/create', {
            templateUrl: 'module/personaldata/personaldata-create.html'
        }).
        when('/personaldata/:filter/:tsz', {
            templateUrl: 'module/personaldata/personaldata-edit.html'
        }).
        when('/reports', {
            templateUrl: 'module/reports/reports.html'
        }).
        when('/exceptiondays', {
            templateUrl: 'module/exceptiondays/exceptiondays.html'
        }).
        when('/scheduledtasks', {
            templateUrl: 'module/scheduledtasks/scheduledtasks.html'
        }).
        when('/systemparameters', {
            templateUrl: 'module/systemparameters/systemparameters.html'
        }).
        when('/valuesets', {
            templateUrl: 'module/valuesets/valuesets.html'
        }).
        when('/users', {
            templateUrl: 'module/users/users.html'
        }).
        otherwise({
            redirectTo: '/login'
        });

      // Error handling
      $httpProvider.interceptors.push(function($q) {
          return {
              'response': function (response) {
                  return response;
              },
              'responseError': function(rejection) {
                  console.log('Error occured! Status: ' + rejection.status + ', data: ' + JSON.stringify(rejection.data));
                  var title = global_translation['ERROR_' + rejection.status];
                  global_toaster.pop('error', rejection.status + ' ' + title, global_translation[rejection.data.errorcode] === undefined ? rejection.data.errorcode : global_translation[rejection.data.errorcode]);
                  return $q.reject(rejection);
              }
          };
      });
    }
]);

app.config(['$httpProvider', function($httpProvider) {
    $httpProvider.interceptors.push('RequestResponseInterceptor');
}]);

app.factory('RequestResponseInterceptor', ['$rootScope', '$window', '$q', function ($rootScope, $window, $q) {
    return {
        request: function (config) {
            $rootScope.data = {pendingRequests: $rootScope.data.pendingRequests + 1};
            return config;
        },
        response: function (response) {
            $rootScope.data = {pendingRequests: $rootScope.data.pendingRequests - 1};
            return response || $q.when(response);
        },
        requestError: function (rejection) {
            $rootScope.data = {pendingRequests: $rootScope.data.pendingRequests - 1};
            return $q.reject(rejection);
        },
        responseError: function (response) {
            $rootScope.data = {pendingRequests: $rootScope.data.pendingRequests - 1};
            return $q.reject(response);
        }
    };
}]);

app.value('enums', {
    locales: ['hu_HU', 'en_US'],
    szerepek: ['DOLGOZO', 'BETEKINTO', 'ADMINISTRATOR'],
    ertekkeszletElemTipusok: ['ALLAMPOLGARSAG', 'SZERVEZETI_EGYSEG', 'KOLTSEGHELY', 'FOGLALKOZASI_VISZONY', 'FOGLALKOZTATAS_JELLEGE', 'FEOR', 'MUNKAKOR'],
    kivetelnapTipusok: ['PIHENONAP', 'MUNKANAP'],
    nemek: ['FERFI', 'NO'],
    kozteruletTipusok: ['UTCA', 'UT', 'TER', 'KOZ', 'KORUT', 'PARK'],
    allomanymodok: ['AKTIV', 'KILEPETT', 'GYESGYED'],
    munkakorJellegek: ['FIZIKAI', 'SZELLEMI'],
    jogviszonyMegszunesenekModjai: ['KOZOS_MEGEGYEZES', 'FELMONDAS', 'RENDKIVULI_FELMONDAS'],
    csaladiAllapotok: ['HAZAS', 'EGYEDULALLO', 'ELETTARSI_VISZONY'],
    jelenletiAdatTipusok: ['MUNKA', 'SZABADNAP', 'SZABADSAG', 'BETEGSZABADSAG', 'TEMETESI_SZABADSAG', 'APA_SZABADSAG', 'TANULMANYI_SZABADSAG'],
    felhasznaltSzabadnapJellegek: ['SZABADSAG', 'BETEGSZABADSAG', 'TEMETESI_SZABADSAG', 'APA_SZABADSAG', 'TANULMANYI_SZABADSAG']
});

app.directive('input', function() {
    return {
        require: '?ngModel',
        link: function (scope, element, attrs, ngModel) {
            if (attrs['type'] !== 'date') {
                return;
            }
            ngModel.$formatters.push(formatter);
            ngModel.$parsers.push(parser);

            element.on('change', function (e) {
                var element = e.target;
                var newValue = ngModel.$modelValue;

                if (newValue === undefined || newValue === null) {
                    element.value = newValue;
                    return;
                }

                if (isDate(newValue)) {
                    element.value = newValue.getFullYear() + '-' + pad(newValue.getMonth() + 1) + '-' + pad(newValue.getDate());
                    return;
                }

                throw 'Client side technical exception #0002!';
            });

            function parser(value) {
                if (value === undefined || value === null) {
                    return value;
                }

                if (isDate(value)) {
                    return value;
                }

                throw 'Client side technical exception #0003!';
            }

            function formatter(value) {
                return convertIso8601StringOrDateToDate(value);
            }
        }
    };
});

app.directive('mytimeinput', function() {
    return {
        require: 'ngModel',
        link: function(scope, element, attrs, ngModel) {
            ngModel.$parsers.push(function(data) {
                if (data === undefined || data === null) {
                    return data;
                }

                var PATTERN_HH_MM = '^([0-2]|0[0-9]|1[0-9]|2[0-3]):?[0-5][0-9]$';
                if (!data.match(PATTERN_HH_MM)) {
                    ngModel.$invalid = true;
                    return undefined;
                }

                ngModel.$invalid = false;
                return convertTimeStringToDate(data);
            });

            ngModel.$formatters.push(function(data) {
                if (data === undefined || data === null) {
                    return data;
                }

                if (isString(data)) {
                    // data not from DB is java.util.Date -> String
                    ngModel.$modelValue = convertTimeStringToDate(data);
                    ngModel.$$writeModelToScope();
                    return data.split(':')[0] + ':' + data.split(':')[1];
                }

                if (isNumber(data)) {
                    // persisted data from DB is java.sql.Time -> Number
                    ngModel.$modelValue = convertIso8601StringOrDateToDate(data);
                    ngModel.$$writeModelToScope();
                    return pad(ngModel.$modelValue.getHours()) + ':' + pad(ngModel.$modelValue.getMinutes());
                }

                return pad(data.getHours()) + ':' + pad(data.getMinutes());
            });
        }
    };
});

function convertTimeStringToDate(timeString) {
    var hours = timeString.split(':')[0];
    var minutes = timeString.split(':')[1];
    return new Date(1970, 0, 1, hours, minutes, 0, 0);
}


app.run(['$rootScope', function ($rootScope) {
    $rootScope.data = { pendingRequests: 0 };
}]);
