var global_locale;
var global_translation = [];

app.service('translationService', function($resource, $http, $rootScope) {
    this.getTranslation = function($scope, language) {
        var languageFilePath = 'translation/translation_' + language + '.json';
        $resource(languageFilePath).get(function (data) {
            $scope.translation = data;
            $rootScope.translation = data;
            global_translation = data;
        });

        $http.get('api/v1/valuesets/get?tipus=ALLAMPOLGARSAG').then(function(result) {
            $scope.addDynamicEnum('Allampolgarsag', result.data.ertekkeszletElemek);
            $scope.allampolgarsagok = result.data.ertekkeszletElemek;
        });

        $http.get('api/v1/valuesets/get?tipus=SZERVEZETI_EGYSEG').then(function(result) {
            $scope.addDynamicEnum('SzervezetiEgyseg', result.data.ertekkeszletElemek);
            $scope.szervezetiEgysegek = result.data.ertekkeszletElemek;
        });

        $http.get('api/v1/valuesets/get?tipus=FOGLALKOZASI_VISZONY').then(function(result) {
            $scope.addDynamicEnum('FoglalkozasiViszony', result.data.ertekkeszletElemek);
            $scope.foglalkozasiViszonyok = result.data.ertekkeszletElemek;
        });

        $http.get('api/v1/valuesets/get?tipus=FOGLALKOZTATAS_JELLEGE').then(function(result) {
            $scope.addDynamicEnum('FoglalkoztatasJellege', result.data.ertekkeszletElemek);
            $scope.foglalkoztatasJellegek = result.data.ertekkeszletElemek;
        });

        $http.get('api/v1/valuesets/get?tipus=KOLTSEGHELY').then(function(result) {
            $scope.addDynamicEnum('Koltseghely', result.data.ertekkeszletElemek);
            $scope.koltseghelyek = result.data.ertekkeszletElemek;
        });

        $http.get('api/v1/valuesets/get?tipus=FEOR').then(function(result) {
            $scope.addDynamicEnum('FEOR', result.data.ertekkeszletElemek);
            $scope.fEORok = result.data.ertekkeszletElemek;
        });

        $http.get('api/v1/valuesets/get?tipus=MUNKAKOR').then(function(result) {
            $scope.addDynamicEnum('Munkakor', result.data.ertekkeszletElemek);
            $scope.munkakorok = result.data.ertekkeszletElemek;
        });

        $scope.addDynamicEnum = function(type, valueSetsElements) {
            for (var i in valueSetsElements) {
                var valueSetElement = valueSetsElements[i];
                $scope.translation[type + '_' + valueSetElement.id] = global_locale === 'en_US' ? valueSetElement.megnevezesAngol : valueSetElement.megnevezesMagyar;
            }
        };
    };
});

app.controller('localeCtrl', ['$scope', 'translationService', function($scope, translationService) {
    if (!global_locale) {
        var browserLocale = navigator.language || navigator.userLanguage;
        browserLocale = browserLocale.toLowerCase();
        console.log('Browser locale: ' + browserLocale);
        var convertedLocale = 'hu_HU';
        if (navigator.language.toLowerCase().indexOf('en') === 0) {
            convertedLocale = 'en_US';
        }
        global_locale = convertedLocale;
    }
    $scope.selectedLanguage = global_locale;

    $scope.change = function() {
       translationService.getTranslation($scope, $scope.selectedLanguage);
       global_locale = $scope.selectedLanguage;
    };

    console.log("Setting locale to: " + global_locale);
    translationService.getTranslation($scope, global_locale);
}]);

app.directive('localeBody', function() {
    return {
        restrict: 'E',
        templateUrl: 'module/core/locale/locale-body.html',
        controller: 'localeCtrl'
    };
});

app.filter('orderedEnum', function() {
     return function(input, enumclass) {
        if (!angular.isObject(input)) {
            return input;
        }

        var labelValuePairs = [];
        for (var i in input) {
            var v = input[i];
            var lvPair = {};
            try {
                lvPair['label'] = global_translation[enumclass + '_' + v];
            } catch (error) {
                // global_translation is not ready yet
            }
            lvPair['value'] = v;
            labelValuePairs.push(lvPair);
        }

        labelValuePairs.sort(function(a, b) {
            a = a['label'];
            b = b['label'];
            try {
                if (a === 'All' || a === 'Összes') {
                    return -1;
                }
                if (b === 'All' || b === 'Összes') {
                    return 1;
                }
                return a.localeCompare(b);
            } catch (error) {
                return undefined;
            }
        });

        var sortedValues = [];
        for (var i in labelValuePairs) {
            var lvPair = labelValuePairs[i];
            sortedValues.push(lvPair['value']);
        }

        return sortedValues;
     };
});
