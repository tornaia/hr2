(function() {
  'use strict';

  
  
  
  describe('test\\app\\dashboard\\dashboard.controller.spec.js', function() {
    var vm;
    var $rootScope;
    var applicationInfoService;
    var moment;

    beforeEach(module('hr2AngularUi'));
    beforeEach(module('mock.applicationInfoService'));
    beforeEach(inject(function(_$controller_, _applicationInfoService_, _moment_, _$rootScope_) {
      applicationInfoService = _applicationInfoService_;
      moment = _moment_;
      $rootScope = _$rootScope_;
      
      spyOn(applicationInfoService, 'getApplicationInfo').and.callThrough();
      
      vm = _$controller_('DashboardController');
    }));
      
    it('should call applicationInfoService.getApplicationInfo once', function() {
      $rootScope.$digest();
      expect(applicationInfoService.getApplicationInfo.calls.count()).toEqual(1);
    });
    
    it('should have applicationInfo with moment formatted startDateTime', function() {
      $rootScope.$digest();
      expect(vm.applicationInfo).toEqual({buildNumber: -1, startDateTime: moment(new Date(2015, 0, 16, 22, 55, 59, 647)).fromNow()});
    });
    
  });
})();
