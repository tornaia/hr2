app.controller('vacationsCtrl', ['$scope', '$http', '$routeParams', '$window', '$filter', '$q', 'ngTableParams', 'ngDialog', 'toaster', 'enums', function($scope, $http, $routeParams, $window, $filter, $q, ngTableParams, ngDialog, toaster, enums) {
    $scope.felhasznaltSzabadnapJellegek = enums.felhasznaltSzabadnapJellegek;

    $scope.selectedYear = new Date().getFullYear();
    $scope.evesSzabadsagAdat = {};
    $scope.vacationConsumptionData = [];
    $scope.vacationConsumptionDetailsData = [];

    $scope.loadVacations = function() {
        $http.get('api/v1/vacations/get?tsz=' + $routeParams.tsz + '&ev=' + $scope.selectedYear)
        .success(function(response) {
            $scope.evesSzabadsagAdat = response;
            $scope.calculateSumOfVacations();
            $scope.vacationConsumptionData = $scope.evesSzabadsagAdat.consumptionTableDTO.rows;
            $scope.vacationConsumptionTableParams.reload();
            $scope.vacationConsumptionDetailsData = [];
            $scope.vacationConsumptionDetailsTableParams.reload();
        });
    };

    $scope.calculateSumOfVacations = function() {
        if ($scope.evesSzabadsagAdat && $scope.evesSzabadsagAdat.consumptionTableDTO && $scope.evesSzabadsagAdat.consumptionTableDTO.rows) {
            $scope.evesSzabadsagAdat.szabadsagNapOsszesen = $scope.evesSzabadsagAdat.alapszabadsag + $scope.evesSzabadsagAdat.gyermekekUtan + $scope.evesSzabadsagAdat.fiatalkoru + $scope.evesSzabadsagAdat.vakSzemely + $scope.evesSzabadsagAdat.egyebMunkakor + $scope.evesSzabadsagAdat.ktKaPotszabadsag + $scope.evesSzabadsagAdat.ktKaVezetoi - $scope.evesSzabadsagAdat.egyebCsokkento + $scope.evesSzabadsagAdat.tanulmanyi + $scope.evesSzabadsagAdat.multEviSzabadsag;

            var SZABADSAG_IDX = 0;
            var TANULMANYI_SZABADSAG_IDX = 4;
            var felhasznaltSzabadsagokSzama = $scope.getFelhasznaltAdottJelleguSzabadnapokSzama(SZABADSAG_IDX);
            var felhasznaltTanulmányiSzabadságokSzama = $scope.getFelhasznaltAdottJelleguSzabadnapokSzama(TANULMANYI_SZABADSAG_IDX);
            $scope.remainingVacation = $scope.evesSzabadsagAdat.szabadsagNapOsszesen - (felhasznaltSzabadsagokSzama + felhasznaltTanulmányiSzabadságokSzama);
        }
    };

    $scope.getFelhasznaltAdottJelleguSzabadnapokSzama = function(felhasznaltSzabadnapJellegIdx) {
        var rows = $scope.evesSzabadsagAdat.consumptionTableDTO.rows;
        var row = rows[felhasznaltSzabadnapJellegIdx];
        var napokSzama = row['january'] + row['february'] + row['march'] + row['april'] + row['may'] + row['june'] + row['july'] + row['august'] + row['september'] + row['october'] + row['november'] + row['december'];
        return napokSzama;
    };

    var attributesToWatch = ['alapszabadsag', 'gyermekekUtan', 'fiatalkoru', 'vakSzemely', 'egyebMunkakor', 'ktKaPotszabadsag', 'ktKaVezetoi', 'egyebCsokkento', 'tanulmanyi', 'multEviSzabadsag', 'bszJarandosagAdottEvi'];
    for (var i=0;i<attributesToWatch.length;++i) {
        $scope.$watch('evesSzabadsagAdat.' + attributesToWatch[i], $scope.calculateSumOfVacations);
    }

    $scope.yearChanged = function() {
        $scope.loadVacations();
    };

    $scope.createVacation = function() {
        var createObject = angular.copy($scope.evesSzabadsagAdat);
        delete createObject.consumptionTableDTO;
        $http.post('api/v1/vacations/create', createObject)
        .success(function() {
            toaster.pop('success', global_translation['ALERT_CREATE_SUCCEED']);
            $scope.loadVacations();
        });
    };

    $scope.saveVacation = function() {
        var saveObject = angular.copy($scope.evesSzabadsagAdat);
        delete saveObject.consumptionTableDTO;
        $http.post('api/v1/vacations/save', saveObject)
        .success(function() {
            toaster.pop('success', global_translation['ALERT_SAVE_SUCCEED']);
            $scope.loadVacations();
        });
    };

    $scope.deleteVacationconsumption = function() {
        $http.post('api/v1/vacations/deleteconsumption', {tsz: $routeParams.tsz, kezdet: $scope.selectedVacationConsumptionDetail.kezdet, veg: $scope.selectedVacationConsumptionDetail.veg})
        .success(function() {
            toaster.pop('success', global_translation['ALERT_DELETE_SUCCEED']);
            $scope.loadVacations();
        });
    };

    $scope.showVacationconsumptionCreate = function() {
        ngDialog.open({
            scope: $scope,
            templateUrl: 'module/personaldata/vacations/vacation-create.html',
            controller: 'createVacationconsumptionCtrl'
        });
    };

    $scope.vacationConsumptionTableParams = new ngTableParams({
        getData: function($defer, params) {
            var currentPage = params.page();
            var itemsPerPage = params.count();
            var fromIdx = (currentPage - 1) * itemsPerPage;
            var toIdx = (currentPage) * itemsPerPage;
            var items = $scope.vacationConsumptionData.slice(fromIdx, toIdx);
            $defer.resolve(items);
        }
    });

    var selectedTd = null;
    $scope.handleMouseClick = function(event, month) {
        $scope.changeVacationConsumptionDetailSelection($scope.selectedVacationConsumptionDetail);
        if (selectedTd === event.target) {
            selectedTd = null;
            event.target.className = '';
            $scope.vacationConsumptionDetailsData = [];
            $scope.vacationConsumptionDetailsTableParams.reload();
            return;
        }
        selectedTd = event.target;

        var td = event.target;
        var tr = td.parentElement;
        var tbody = tr.parentElement;
        var trs = tbody.children;
        for (var i=0;i<trs.length;++i) {
            var tri = trs[i];
            var trichildren = tri.children;
            for (var j=0;j<trichildren.length;++j) {
                var td = trichildren[j];
                td.className = '';
            }
        }
        event.target.className = 'highlight';

        // update detailsGrid
        var rowIndex = event.target.parentElement.rowIndex;
        $scope.updateDetailsGrid(rowIndex - 2, month);
    };

    $scope.vacationConsumptionDetailsTableParams = new ngTableParams({
        getData: function($defer, params) {
            var currentPage = params.page();
            var itemsPerPage = params.count();
            var fromIdx = (currentPage - 1) * itemsPerPage;
            var toIdx = (currentPage) * itemsPerPage;
            var items = $scope.vacationConsumptionDetailsData.slice(fromIdx, toIdx);
            $defer.resolve(items);
        }
    });

    $scope.changeVacationConsumptionDetailSelection = function(vacationConsumptionDetail) {
        for (var i=0; i<$scope.vacationConsumptionDetailsData.length; ++i) {
            var entry = $scope.vacationConsumptionDetailsData[i];
            entry.$selected = false;
        }

        var isDeselect = vacationConsumptionDetail === $scope.selectedVacationConsumptionDetail;
        if (isDeselect) {
            $scope.selectedVacationConsumptionDetail = null;
        } else {
            vacationConsumptionDetail.$selected = true;
            $scope.selectedVacationConsumptionDetail = vacationConsumptionDetail;
        }
    };

    $scope.updateDetailsGrid = function(idx, month) {
        var tipusAdatok = $scope.evesSzabadsagAdat.consumptionTableDTO.rows[idx];
        var tipusHoAdatok = tipusAdatok[month + 'Details'];
        $scope.vacationConsumptionDetailsData = tipusHoAdatok.reszletek;
        $scope.vacationConsumptionDetailsTableParams.reload();
    };

    $scope.handleMouseOver = function(event) {
        var td = event.target;
        var tr = td.parentElement;
        var tbody = tr.parentElement;
        var trs = tbody.children;
        for (var i=0;i<trs.length;++i) {
            var tri = trs[i];
            var trichildren = tri.children;
            for (var j=0;j<trichildren.length;++j) {
                var td = trichildren[j];
                td.className = '';
            }
        }

        event.target.className = 'highlight';
        if (selectedTd) {
            selectedTd.className = 'highlight';
        }
    };

    $scope.loadVacations();
}]);

app.controller('createVacationconsumptionCtrl', ['$scope', '$http', '$routeParams', 'toaster', function($scope, $http, $routeParams, toaster) {
    $scope.form = {};
    $scope.createVacationconsumption = function() {
        $http.post('api/v1/vacations/createconsumption', {tsz: $routeParams.tsz, kezdet: $scope.form.kezdet, veg: $scope.form.veg, jelleg: $scope.form.jelleg})
        .success(function() {
            toaster.pop('success', global_translation['ALERT_CREATE_SUCCEED']);
            $scope.closeThisDialog();
            $scope.loadVacations();
        });
    };
}]);

app.directive('consumptionTable', function() {
    return {
        require: '^tabVacation',
        restrict: 'E',
        templateUrl: 'module/personaldata/vacations/consumption-table.html'
    };
});

app.directive('consumptionDetailsTable', function() {
    return {
        require: '^tabVacation',
        restrict: 'E',
        templateUrl: 'module/personaldata/vacations/consumption-details-table.html'
    };
});
