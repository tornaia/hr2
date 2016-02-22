(function() {
  angular
    .module('mock.icNumberConfig', ['tmh.dynamicLocale'])
    .config(configTmhDynamicLocaleProvider);

  /** @ngInject */
  function configTmhDynamicLocaleProvider(tmhDynamicLocaleProvider) {
    tmhDynamicLocaleProvider.localeLocationPattern('base/bower_components/angular-i18n/angular-locale_{{locale}}.js');
  }
})();

(function() {
  'use strict';

  describe('test\\app\\components\\ic-number-input\\ic.number.input.spec.js', function() {
    var form;
    var input;
    var scope;
    var $compile;
    var $window;
    var tmhDynamicLocale;

    beforeEach(module('hr2AngularUi'));
    beforeEach(module('mock.icNumberConfig'));
    beforeEach(inject(function($rootScope, _$compile_, _$window_, _tmhDynamicLocale_) {
      scope = $rootScope.$new();
      $compile = _$compile_;
      $window = _$window_;
      tmhDynamicLocale = _tmhDynamicLocale_;
    }));

    function createFormObject() {
      form = $compile('<form name="form"><input type="text" ng-model="vm.numberOfItems" ic-number-input></form>')(scope);
      input = form.find('input');
    }

    function setLocale(locale) {
      tmhDynamicLocale.set(locale);
    }

    describe('icNumberInput', function() {
      beforeEach(function() {
        createFormObject();
      });

      it('should be compiled', function() {
        expect(form.html()).not.toEqual(null);
      });

      it('should set form to valid on init', function() {
        expect(scope.form.$valid).toBe(true);
      });

      it('should set form to invalid on invalid input', function() {
        input.val('67a').triggerHandler('input');
        input.triggerHandler('blur');
        expect(input.val()).toEqual('67a');
        expect(scope.vm.numberOfItems).toBeNull();
        expect(scope.form.$valid).toBe(false);
      });

      describe('when locale is en-us and having user input then it', function() {
        beforeEach(function(done) {
          setLocale('en-us');
          createFormObject();
          $window.setInterval(function(){ done(); }, 0);
        });

        it('should handle empty string', function() {
          input.val('').triggerHandler('input');
          input.triggerHandler('blur');
          expect(input.val()).toEqual('');
          expect(scope.vm.numberOfItems).toBeNull();
        });

        it('should allow space on paste from clipboard#1', function() {
          input.val('1 1').triggerHandler('input');
          input.triggerHandler('blur');
          expect(input.val()).toEqual('11');
          expect(scope.vm.numberOfItems).toEqual(11);
        });

        it('should allow unicode space on paste from clipboard#1', function() {
          input.val('1\u00a01').triggerHandler('input');
          input.triggerHandler('blur');
          expect(input.val()).toEqual('11');
          expect(scope.vm.numberOfItems).toEqual(11);
        });

        it('should remove leading zeros', function() {
          input.val('0000067').triggerHandler('input');
          input.triggerHandler('blur');
          expect(input.val()).toEqual('67');
          expect(scope.vm.numberOfItems).toEqual(67);
        });

        it('should ignore group separator', function() {
          input.val('6,7').triggerHandler('input');
          input.triggerHandler('blur');
          expect(input.val()).toEqual('67');
          expect(scope.vm.numberOfItems).toEqual(67);
        });

        it('should remove trailing decimal zeros', function() {
          input.val('6.70').triggerHandler('input');
          input.triggerHandler('blur');
          expect(input.val()).toEqual('6.7');
          expect(scope.vm.numberOfItems).toEqual(6.7);
        });

        it('should remove leading and trailing zeros', function() {
          input.val('06.70').triggerHandler('input');
          input.triggerHandler('blur');
          expect(input.val()).toEqual('6.7');
          expect(scope.vm.numberOfItems).toEqual(6.7);
        });

        it('should convert single hyphen to 0', function() {
          input.val('-').triggerHandler('input');
          input.triggerHandler('blur');
          expect(input.val()).toEqual('0');
          expect(scope.vm.numberOfItems).toEqual(0);
        });

        it('should convert multiple hyphens to 0#1', function() {
          input.val('--').triggerHandler('input');
          input.triggerHandler('blur');
          expect(input.val()).toEqual('0');
          expect(scope.vm.numberOfItems).toEqual(0);
        });

        it('should convert multiple hyphens to 0#2', function() {
          input.val('---').triggerHandler('input');
          input.triggerHandler('blur');
          expect(input.val()).toEqual('0');
          expect(scope.vm.numberOfItems).toEqual(0);
        });

        it('should handle hyphen', function() {
          input.val('-6').triggerHandler('input');
          input.triggerHandler('blur');
          expect(input.val()).toEqual('-6');
          expect(scope.vm.numberOfItems).toEqual(-6);
        });

        it('should handle multiple hyphens', function() {
          input.val('-6-7').triggerHandler('input');
          input.triggerHandler('blur');
          expect(input.val()).toEqual('-67');
          expect(scope.vm.numberOfItems).toEqual(-67);
        });

        it('should handle zero', function() {
          input.val('0').triggerHandler('input');
          input.triggerHandler('blur');
          expect(input.val()).toEqual('0');
          expect(scope.vm.numberOfItems).toEqual(0);
        });

        it('should handle multiple zeros#1', function() {
          input.val('00').triggerHandler('input');
          input.triggerHandler('blur');
          expect(input.val()).toEqual('0');
          expect(scope.vm.numberOfItems).toEqual(0);
        });

        it('should handle multiple zeros#2', function() {
          input.val('000').triggerHandler('input');
          input.triggerHandler('blur');
          expect(input.val()).toEqual('0');
          expect(scope.vm.numberOfItems).toEqual(0);
        });

        it('should handle trailing decimal separator', function() {
          input.val('6.').triggerHandler('input');
          input.triggerHandler('blur');
          expect(input.val()).toEqual('6');
          expect(scope.vm.numberOfItems).toEqual(6);
        });

        it('should handle trailing group separator', function() {
          input.val('6,').triggerHandler('input');
          input.triggerHandler('blur');
          expect(input.val()).toEqual('6');
          expect(scope.vm.numberOfItems).toEqual(6);
        });

        it('should handle multiple group separators#1', function() {
          input.val('6,7,').triggerHandler('input');
          input.triggerHandler('blur');
          expect(input.val()).toEqual('67');
          expect(scope.vm.numberOfItems).toEqual(67);
        });

        it('should handle multiple group separators#2', function() {
          input.val('6,,7').triggerHandler('input');
          input.triggerHandler('blur');
          expect(input.val()).toEqual('67');
          expect(scope.vm.numberOfItems).toEqual(67);
        });

        it('should preserve invalid input but write null to model', function() {
          input.val(',').triggerHandler('input');
          input.triggerHandler('blur');
          expect(input.val()).toEqual(',');
          expect(scope.vm.numberOfItems).toBeNull();
        });

        it('should preserve invalid input without any format but write null to model', function() {
          input.val('67a').triggerHandler('input');
          input.triggerHandler('blur');
          expect(input.val()).toEqual('67a');
          expect(scope.vm.numberOfItems).toBeNull();
        });

        it('should use group separator', function() {
          input.val('4321').triggerHandler('input');
          input.triggerHandler('blur');
          expect(input.val()).toEqual('4,321');
          expect(scope.vm.numberOfItems).toEqual(4321);
        });

        it('should use multiple group separators', function() {
          input.val('7654321').triggerHandler('input');
          input.triggerHandler('blur');
          expect(input.val()).toEqual('7,654,321');
          expect(scope.vm.numberOfItems).toEqual(7654321);
        });

        it('should use group and decimal separators#1', function() {
          input.val('4321.12').triggerHandler('input');
          input.triggerHandler('blur');
          expect(input.val()).toEqual('4,321.12');
          expect(scope.vm.numberOfItems).toEqual(4321.12);
        });

        it('should use group and decimal separators#2', function() {
          input.val('7654321.12').triggerHandler('input');
          input.triggerHandler('blur');
          expect(input.val()).toEqual('7,654,321.12');
          expect(scope.vm.numberOfItems).toEqual(7654321.12);
        });

        it('should remove fraction and decimal point is it is zero#1', function() {
          input.val('6.0').triggerHandler('input');
          input.triggerHandler('blur');
          expect(input.val()).toEqual('6');
          expect(scope.vm.numberOfItems).toEqual(6);
        });

        it('should remove fraction and decimal point is it is zero#2', function() {
          input.val('6.00').triggerHandler('input');
          input.triggerHandler('blur');
          expect(input.val()).toEqual('6');
          expect(scope.vm.numberOfItems).toEqual(6);
        });

        it('should handle corner case#1 -.', function() {
          input.val('-.').triggerHandler('input');
          input.triggerHandler('blur');
          expect(input.val()).toEqual('-.');
          expect(scope.vm.numberOfItems).toBeNull();
        });

        it('should handle corner case#2 .-', function() {
          input.val('-.').triggerHandler('input');
          input.triggerHandler('blur');
          expect(input.val()).toEqual('-.');
          expect(scope.vm.numberOfItems).toBeNull();
        });
      });

      describe('when locale is hu-hu and having user input then it', function() {
        beforeEach(function(done) {
          setLocale('hu-hu');
          createFormObject();
          $window.setInterval(function(){ done(); }, 0);
        });

        it('should allow zero', function() {
          input.val('0').triggerHandler('input');
          expect(input.hasClass('ng-valid')).toEqual(true);
          expect(scope.vm.numberOfItems).toEqual(0);
        });

        it('should allow space on paste from clipboard#2', function() {
          input.val('1 1').triggerHandler('input');
          input.triggerHandler('blur');
          expect(input.val()).toEqual('11');
          expect(scope.vm.numberOfItems).toEqual(11);
        });

        it('should allow unicode space on paste from clipboard#2', function() {
          input.val('1\u00a01').triggerHandler('input');
          input.triggerHandler('blur');
          expect(input.val()).toEqual('11');
          expect(scope.vm.numberOfItems).toEqual(11);
        });

        it('should handle decimal separator#1', function() {
          input.val('1,1').triggerHandler('input');
          input.triggerHandler('blur');
          expect(input.val()).toEqual('1,1');
          expect(scope.vm.numberOfItems).toEqual(1.1);
        });

        it('should handle decimal separator#2', function() {
          input.val('4 321,12').triggerHandler('input');
          input.triggerHandler('blur');
          expect(input.val()).toEqual('4\u00a0321,12');
          expect(scope.vm.numberOfItems).toEqual(4321.12);
        });

        it('should handle unicode decimal separator', function() {
          input.val('4\u00a0321,12').triggerHandler('input');
          input.triggerHandler('blur');
          expect(input.val()).toEqual('4\u00a0321,12');
          expect(scope.vm.numberOfItems).toEqual(4321.12);
        });
      });
    });
  });
})();
