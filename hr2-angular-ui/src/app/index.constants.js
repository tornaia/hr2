/* global moment:false */
(function() {
  'use strict';

  angular
    .module('hr2AngularUi')
    .constant('moment', moment)
    .constant('DATE_EXPORT_FORMAT', 'yyyy.MM.dd')
    .constant('MONTH_EXPORT_FORMAT', 'yyyy.MM')
    .constant('LOCALES', ['hu_HU', 'en_US'])
    .constant('ROLES', ['DOLGOZO', 'BETEKINTO', 'ADMINISTRATOR'])
    .constant('EXCEPTIONDAYS', ['PIHENONAP', 'MUNKANAP'])
    .constant('VALUESET_TYPES', ['ALLAMPOLGARSAG', 'SZERVEZETI_EGYSEG', 'KOLTSEGHELY', 'FOGLALKOZASI_VISZONY', 'FOGLALKOZTATAS_JELLEGE', 'FEOR', 'MUNKAKOR'])
    .constant('REPORT_STAFF_STATUSES', ['OSSZES', 'AKTIV', 'KILEPETT', 'GYESGYED']);

})();
