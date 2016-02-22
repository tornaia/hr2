(function() {
  'use strict';

  angular
  .module('hr2AngularUi')
  .directive('icNumberInput', icNumberInput);

  var __hasProp = {}.hasOwnProperty;

  /** @ngInject */
  function icNumberInput(icNumberConfig, $locale) {
    var addCommasToInteger, controlKeys, defaultOptions, getOptions, hasMultipleDecimals, isNotControlKey, isNotDigit, isNumber, makeIsValid, makeMaxDecimals, makeMaxDigits, makeMaxNumber, makeMinNumber;
      defaultOptions = icNumberConfig.defaultOptions;
      getOptions = function(scope) {
          var option, options, value, _ref;
          options = angular.copy(defaultOptions);
          if (scope.options != null) {
              _ref = scope.$eval(scope.options);
              for (option in _ref) {
                  if (!__hasProp.call(_ref, option)) continue;
                  value = _ref[option];
                  options[option] = value;
              }
          }
          return options;
      };

      function escapeRegExp(str) {
          return str.replace(/[\-\[\]\/\{\}\(\)\*\+\?\.\\\^\$\|]/g, "\\$&");
      }

      function getDecimalSep() {
          return $locale.NUMBER_FORMATS.DECIMAL_SEP;
      }

      function getGroupSep() {
          return $locale.NUMBER_FORMATS.GROUP_SEP;
      }
      function getGroupSepRegex() {
          return escapeRegExp(getGroupSep());
      }

      isNumber = function(val) {
          return !isNaN(parseFloat(val)) && isFinite(val);
      };
      isNotDigit = function(which) {
          return which < 44 || which > 57 || which === 47;
      };
      controlKeys = [0, 8, 13/*, 32*/];
      isNotControlKey = function(which) {
          return controlKeys.indexOf(which) === -1;
      };
      hasMultipleDecimals = function(val) {
          return (val != null) && val.toString().split('.').length > 2;
      };
      makeMaxDecimals = function(maxDecimals) {
          var regexString, validRegex;
          if (maxDecimals > 0) {
              regexString = "^-?(\\d[" + getGroupSepRegex() + "]*){0,99}\\.?\\d{0," + maxDecimals + "}$";
          } else {
              regexString = "^-?\\d*$";
          }
          validRegex = new RegExp(regexString);
          return function(val) {
              return validRegex.test(val);
          };
      };
      makeMaxNumber = function(maxNumber) {
          return function(val, number) {
              return number <= maxNumber;
          };
      };
      makeMinNumber = function(minNumber) {
          return function(val, number) {
              return number >= minNumber;
          };
      };
      makeMaxDigits = function(maxDigits) {
          var validRegex;
          validRegex = new RegExp("^-?\\d{0," + maxDigits + "}(\\.\\d*)?$");
          return function(val) {
              return validRegex.test(val);
          };
      };
      makeIsValid = function(options) {
          var validations;
          validations = [];
          if (options.maxDecimals != null) {
              validations.push(makeMaxDecimals(options.maxDecimals));
          }
          if (options.max != null) {
              validations.push(makeMaxNumber(options.max));
          }
          if (options.min != null) {
              validations.push(makeMinNumber(options.min));
          }
          if (options.maxDigits != null) {
              validations.push(makeMaxDigits(options.maxDigits));
          }
          return function(val) {
              var i, number, _i, _ref;
              if (!isNumber(val)) {
                  return false;
              }
              if (hasMultipleDecimals(val)) {
                  return false;
              }
              number = Number(val);
              for (i = _i = 0, _ref = validations.length; 0 <= _ref ? _i < _ref : _i > _ref; i = 0 <= _ref ? ++_i : --_i) {
                  if (!validations[i](val, number)) {
                      return false;
                  }
              }
              return true;
          };
      };
      addCommasToInteger = function(val) {
          var decimals, wholeNumbers, formattedWholeNumbers;
          decimals = val.indexOf('.') == -1 ? '' : val.replace(/^-?\d+\./, '');
          wholeNumbers = val.replace(/(\.\d+)$/, '');
          formattedWholeNumbers = wholeNumbers.replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1' + getGroupSepRegex());
          return "" + formattedWholeNumbers + (decimals > 0 ? getDecimalSep() + decimals : '');
      };

    return {
      restrict: 'A',
      require: 'ngModel',
      scope: {
          options: '@icNumber'
      },
      link: function(scope, elem, attrs, ngModelCtrl) {
          var isValid, options;
          options = getOptions(scope);
          isValid = makeIsValid(options);
          var errorCode = options.errorCode || 'invalid';
          ngModelCtrl.$parsers.unshift(function(viewVal) {
              if (viewVal == "") {
                  return null;
              }
              viewVal = viewVal.replace(/\s/g, '').replace(new RegExp(getGroupSepRegex(), "g"), '');
              if (viewVal.indexOf('-') != -1) {
                  viewVal = '-' + viewVal.replace(/-/g, '').replace(/^0+/, '');
                  if (viewVal === '-') {
                      viewVal = "0";
                  }
              }
              if (viewVal !== "0") {
                  var noCommasAndSpecialCharactersVal = viewVal.replace(/^0+/, '0').replace(/\u00a0/g, '').replace(/,/g, '.').replace(/\.(?=.*\.)/g, '').replace(/,/g, '.').replace(/(\.[0-9]*?)0+$/, "$1").replace(/\.$/, '');
              } else if (viewVal === "") {
                  noCommasAndSpecialCharactersVal = undefined;
              } else {
                  noCommasAndSpecialCharactersVal = viewVal;
              }

              if (isValid(noCommasAndSpecialCharactersVal) || noCommasAndSpecialCharactersVal !== '' && !noCommasAndSpecialCharactersVal) {
                  ngModelCtrl.$setValidity('icNumberInvalid', true);
                  ngModelCtrl.$error = {};
                  return parseFloat(noCommasAndSpecialCharactersVal);
              } else {
                  ngModelCtrl.$setValidity('icNumberInvalid', false);
                  ngModelCtrl.$error = {};
                  ngModelCtrl.$error[errorCode] = errorCode;
                  return null;
              }
          });
          ngModelCtrl.$formatters.push(function(val) {
              if ((options.nullDisplay != null) && (!val || val === '')) {
                  return options.nullDisplay;
              }
              if ((val == null) || !isValid(val)) {
                  return val;
              }
              ngModelCtrl.$setValidity('icNumberInvalid', true);
              val = addCommasToInteger(val.toString());
              if (options.prepend != null) {
                  val = "" + options.prepend + val;
              }
              if (options.append != null) {
                  val = "" + val + options.append;
              }

              return val;
          });
          elem.on('blur', function() {
              var formatter, viewValue, _i, _len, _ref;
              viewValue = ngModelCtrl.$modelValue;
              if ((viewValue == null) || !isValid(viewValue)) {
                  return;
              }
              _ref = ngModelCtrl.$formatters;
              for (_i = 0, _len = _ref.length; _i < _len; _i++) {
                  formatter = _ref[_i];
                  viewValue = formatter(viewValue);
              }
              ngModelCtrl.$viewValue = viewValue;
              return ngModelCtrl.$render();
          });
          elem.on('focus', function() {
              var val;
              val = elem.val();
              if (options.prepend != null) {
                  val = val.replace(options.prepend, '');
              }
              if (options.append != null) {
                  val = val.replace(options.append, '');
              }
              var numberAsString = val.replace(new RegExp(getGroupSepRegex(), "g"), '');
              elem.val(numberAsString);

              var $scope = angular.element(elem).scope();
              var ngModelValue = elem[0].attributes['ng-model'].nodeValue;
              $scope.$apply(function() {
                  var pathToPropertyIndex = ngModelValue.lastIndexOf('.');
                  var propertyName = ngModelValue.substring(pathToPropertyIndex+1);
                  var pathToOwnerObject = ngModelValue.substring(0, pathToPropertyIndex);
                  var ownerObject = eval('$scope.' + pathToOwnerObject);
                  var numberAsNumber = val === "" ? null : parseFloat(val.replace(new RegExp(getGroupSepRegex(), "g"), '').replace(getDecimalSep(), '.'));
                  ownerObject[propertyName] = numberAsNumber;
              });

              return elem[0].select();
          });
          if (options.preventInvalidInput === true) {
              return elem.on('keypress', function(e) {
                  if (isNotDigit(e.which) && isNotControlKey(e.which)) {
                      return e.preventDefault();
                  }
              });
          }
      }
    };
  }

  angular
  .module('hr2AngularUi')
  .provider('icNumberConfig', icNumberConfig);

  function icNumberConfig() {
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
  }

})();
