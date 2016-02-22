'use strict';

var DashboardPage = function () {

    this.assertBuildNumberToBe = function (expectedBuildNumber) {
      var buildNumber = element(by.binding('vm.applicationInfo.buildNumber'));
      expect(buildNumber.getText()).toBe(expectedBuildNumber);
    };

};
module.exports = DashboardPage;