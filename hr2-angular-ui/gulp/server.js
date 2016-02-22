'use strict';

var path = require('path');
var gulp = require('gulp');
var conf = require('./conf');

var browserSync = require('browser-sync');
var browserSyncSpa = require('browser-sync-spa');

var util = require('util');

var proxyMiddleware = require('http-proxy-middleware');

var connect = require('gulp-connect');
var proxy = require('proxy-middleware');

function browserSyncInit(baseDir, browser) {
  browser = browser === undefined ? 'default' : browser;

  var routes = null;
  if((baseDir === conf.paths.src || baseDir === conf.paths.src_test) || (util.isArray(baseDir) && (baseDir.indexOf(conf.paths.src) !== -1) || baseDir.indexOf(conf.paths.src_test) !== -1)) {
    routes = {
      '/bower_components': 'bower_components'
    };
  }

  var server = {
    baseDir: baseDir,
    routes: routes
  };

  /*
   * You can add a proxy to your backend by uncommenting the line below.
   * You just have to configure a context which will we redirected and the target url.
   * Example: $http.get('/users') requests will be automatically proxified.
   *
   * For more details and option, https://github.com/chimurai/http-proxy-middleware/blob/v0.0.5/README.md
   * Also useful https://www.npmjs.com/package/http-proxy-middleware
   */

  var context = ['/api', '/logout', '/app/assets', '/app/fonts'];
  var options = {
	  target: 'localhost:3000',         // target host 
	  changeOrigin: true,               // needed for virtual hosted sites 
	  ws: true,                         // proxy websockets 
	  pathRewrite: {
            '^/api' : '/api',               // rewrite paths
			'^/logout' : '/logout',         // rewrite paths
			'^/app/assets' : '/app/assets', // rewrite paths
			'^/app/fonts' : '/app/fonts'    // rewrite paths
	  },
	  proxyTable: {
		  // when request.headers.host == 'localhost:3000', 
          // override target 'localhost:3000' to 'http://localhost:8081' 
		  'localhost:3000' : 'http://localhost:8081'
	  }
  };

  server.middleware = proxyMiddleware(context, options);

  browserSync.instance = browserSync.init({
    startPath: '/',
    server: server,
    browser: browser
  });
}

browserSync.use(browserSyncSpa({
  selector: '[ng-app]'// Only needed for angular apps
}));

gulp.task('serve', ['watch'], function () {
  browserSyncInit([path.join(conf.paths.tmp, '/serve'), conf.paths.src, conf.paths.src_test]);
});

gulp.task('serve:dist', ['build'], function () {
  browserSyncInit(conf.paths.dist);
});

gulp.task('serve:e2e', ['inject'], function () {
  browserSyncInit([conf.paths.tmp + '/serve', conf.paths.src, conf.paths.src], []);
});

gulp.task('serve:e2e-dist', ['build'], function () {
  browserSyncInit(conf.paths.dist, []);
});
