(function() {
  'use strict';

  describe('test\\app\\components\\applicationinfo\\applicationinfo.service.spec.js', function() {
    var applicationInfoService;
    var $rootScope;
    var $httpBackend;

    describe('getApplicationInfo function invokes $http', function() {
      beforeEach(module('hr2AngularUi'));
      beforeEach(inject(function(_applicationInfoService_, _$rootScope_, _$httpBackend_) {
        applicationInfoService = _applicationInfoService_;
        $rootScope = _$rootScope_;
        $httpBackend = _$httpBackend_;
      }));
      it('invokes specific url', function() {
        $httpBackend.when('GET', '/api/v1/applicationinfo').respond({whateverkey : 'whatevervalue'})
        applicationInfoService.getApplicationInfo();
        $httpBackend.flush();
      });
    });

    describe('getApplicationInfo function invokes mock', function() {
    beforeEach(module('hr2AngularUi'));
    beforeEach(module('mock.applicationInfoService'));
    beforeEach(inject(function(_applicationInfoService_, _$rootScope_) {
      applicationInfoService = _applicationInfoService_;
        $rootScope = _$rootScope_;
      }));

      it('should return ApplicationInfoResponseDTO', function() {

        spyOn(applicationInfoService, 'getApplicationInfo').and.callThrough();

        applicationInfoService.getApplicationInfo().then(function(response) {
          expect(response).toEqual({buildNumber: -1,
                                    startDateTime : new Date(2015, 0, 16, 22, 55, 59, 647)});
          expect(applicationInfoService.getApplicationInfo.calls.count()).toEqual(1);
        });

        $rootScope.$digest();
      });
    });

  });
})();
