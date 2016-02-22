'use strict';

var LoginPage = function () {
    browser.ignoreSynchronization = true;

    this.get = function () {
        browser.get(browser.baseUrl);
    };

    this.setUsername = function (username) {
      browser.driver.findElement(by.name('username')).sendKeys(username);
    };

    this.setPassword = function (password) {
      browser.driver.findElement(by.id('password')).sendKeys(password);
    };

    this.submit = function () {
      browser.driver.findElement(by.id('loginform')).submit();
      browser.ignoreSynchronization = false;
    };
};
module.exports = LoginPage;