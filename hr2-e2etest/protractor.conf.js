exports.config = {
    // Direct connection to the browser, selenium server is not needed
    directConnect: true,

    capabilities: {
        'browserName': 'chrome'
    },

    specs: ['src/test/resources/e2e/protractor/**/*spec.js'],
	
	chromeDriver: 'node_modules/protractor/selenium/chromedriver',

    baseUrl: 'http://localhost:8080/',

    framework: 'jasmine',

    jasmineNodeOpts: {
        showColors: true,
        defaultTimeoutInterval: 30000,
        isVerbose: true,
        includeStackTrace: true
    }
};