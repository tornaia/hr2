(function() {
  'use strict';

  describe('test\\app\\components\\userinfo\\userinfo.service.spec.js', function() {
    var userInfoService;
    var $rootScope;

    beforeEach(module('hr2AngularUi'));
    beforeEach(module('mock.userInfoService'));
    beforeEach(inject(function(_userInfoService_, _$rootScope_) {
      userInfoService = _userInfoService_;
      $rootScope = _$rootScope_;
    }));
    
    describe('getUserInfo function', function() {
      it('should exist', function() {
        expect(userInfoService.getUserInfo).toBeDefined();
      });

      it('should return UserInfoResponseDTO', function() {
        spyOn(userInfoService, 'getUserInfo').and.callThrough();
        
        userInfoService.getUserInfo().then(function(response) {
          expect(response).toEqual({username : 'admin',
                                    grantedAuthorities : ['ROLE_ADMINISTRATOR', 'ROLE_BETEKINTO', 'ROLE_DOLGOZO', 'ROLE_NEM_VEDETT']});
          expect(userInfoService.getUserInfo.calls.count()).toEqual(1);
        });
        
        $rootScope.$digest();
      });
    });
  });
})();
