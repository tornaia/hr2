(function () {
  'use strict';

  /**
   * @ngdoc service
   * @name components.factory:dateTransformer
   *
   * @description A helper service for use in ng-resource transform methods.
   * Example of usage:
   * <code>
   *    var Session = $resource('/api/sessions/:id', {id: '@id'}, {
   *      query: {
   *        method: 'GET',
   *        isArray: true,
   *        transformResponse: dateTransformer.transformResponse.toDate(['start', 'end'])
   *      },
   *      update: {
   *        method: 'PUT',
   *        transformRequest: dateTransformer.transformRequest.toLocalIsoString(['start', 'end'])
   *      }
   *    });
   * </code>
   *
   * After Session.query() fields 'start' and 'end' in returned resource objects will be JS Date objects.
   * After session.$update() server will receive JSON where JS Date objects in fields 'start' and 'end'
   * will be just strings in ISO format without timezone.
   *
   * Supported transformers: toDate, toLocalIsoString, toZonedIsoString.
   *
   * This service uses moment.js for date parsing and format because native JS Date doesn't support
   * dates without timezone (they always parsed as UTC+0).
   *
   * Also Lodash is used for object manipulations.
   *
   */
  angular
    .module('hr2AngularUi')
    .factory('dateTransformer', dateTransformerFactory);

  function dateTransformerFactory($http, _, moment) {
    var dateTransformer = {

      toDate: toDate,
      toLocalIsoString: toLocalIsoString,
      toZonedIsoString: toZonedIsoString,

      transformResponse: {
        toDate: transformResponseToDate,
        toLocalIsoString: transformResponseToLocalIsoString,
        toZonedIsoString: transformResponseToZonedIsoString
      },

      transformRequest: {
        toDate: transformRequestToDate,
        toLocalIsoString: transformRequestToLocalIsoString,
        toZonedIsoString: transformRequestToZonedIsoString
      }

    };

    return dateTransformer;

    function transform (predicate, transformDate) {
      return function (data) {
        if (_.isArray(data)) {
          return data.map(function (object) {
            return transformObject(object, predicate, transformDate);
          });
        } else if (_.isObject(data)) {
          return transformObject(data, predicate, transformDate);
        }
      };
    }

    function transformObject (object, predicate, transformDate) {
      var clonedObject;
      _(object).pick(predicate).forEach(function (val, key) {
        if (_.isDate(object[key]) || _.isString(object[key])) {
          if (_.isUndefined(clonedObject)) {
            clonedObject = _.clone(object);
          }
          clonedObject[key] = transformDate(val);
        }
      }).value();
      return _.isUndefined(clonedObject) ? object : clonedObject;
    }

    function transformResponseWith (transformer) {
      return $http.defaults.transformResponse.concat(transformer);
    }

    function transformRequestWith (transformer) {
      return [transformer].concat($http.defaults.transformRequest);
    }

    function transformResponseToDate (predicate) {
      return transformResponseWith(toDate(predicate));
    }

    function transformResponseToLocalIsoString (predicate) {
      return transformResponseWith(toLocalIsoString(predicate));
    }

    function transformResponseToZonedIsoString (predicate) {
      return transformResponseWith(toZonedIsoString(predicate));
    }

    function transformRequestToDate (predicate) {
      return transformRequestWith(toDate(predicate));
    }

    function transformRequestToLocalIsoString (predicate) {
      return transformRequestWith(toLocalIsoString(predicate));
    }

    function transformRequestToZonedIsoString (predicate) {
      return transformRequestWith(toZonedIsoString(predicate));
    }

    function toDate (predicate) {
      return transform(predicate, function (val) {
        return moment(val).toDate();
      });
    }

    function toLocalIsoString (predicate) {
      return transform(predicate, function (val) {
        return moment(val).format('YYYY-MM-DDTHH:mm:ss.SSS');
      });
    }

    function toZonedIsoString (predicate) {
      return transform(predicate, function (val) {
        return moment(val).format();
      });
    }

  }

})();