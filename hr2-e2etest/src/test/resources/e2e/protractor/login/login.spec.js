'use strict';

var LoginPage = require('./login.po.js');
var DashboardPage = require('../dashboard/dashboard.po.js');
describe('HR2 Login', function () {
    var loginPage;
    var dashboardPage;

    beforeEach(function () {
        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
    });

    it('Sikeres bejelentkezes', function () {
        loginPage.get();
        loginPage.setUsername('admin');
        loginPage.setPassword('admin');
        loginPage.submit();
        dashboardPage.assertBuildNumberToBe('-1');
    });

});