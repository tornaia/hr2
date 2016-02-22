(function() {
  'use strict';

  angular
  .module('ui.grid')
  .config(uiGrid_hu);

  /** @ngInject */
  function uiGrid_hu($provide) {
    $provide.decorator('i18nService', function($delegate) {
        $delegate.add('hu', {
            headerCell: {
                aria: {
                    defaultFilterLabel: 'Szűrés az oszlopra',
                    removeFilter: 'Szűrő eltávolítása',
                    columnMenuButtonLabel: 'Oszlop menü'
                },
                priority: 'Prioritás:',
                filterLabel: "Szűrő az oszlopra: "
            },
            aggregate: {
                label: 'elemek'
            },
            groupPanel: {
                description: 'Fogjon meg egy oszlopfejlécet és dobja ide, ha az alapján szeretne csoportosítani.'
            },
            search: {
                placeholder: 'Keresés...',
                showingItems: 'Sorok mutatása:',
                selectedItems: 'Kiválasztott sorok:',
                totalItems: 'Összes sor:',
                size: 'Oldal mérate:',
                first: 'Első oldal',
                next: 'Következő oldal',
                previous: 'Előző oldal',
                last: 'Utolsó oldal'
            },
            menu: {
                text: 'Choose Columns:'
            },
            sort: {
                ascending: 'Rendezés (A-Z)',
                descending: 'Rendezés (Z-A)',
                none: 'Nincs rendezés',
                remove: 'Rendezés eltávolítása'
            },
            column: {
                hide: 'Oszlop elrejtése'
            },
            aggregation: {
                count: 'sorok száma: ',
                sum: 'összeg: ',
                avg: 'átlag: ',
                min: 'min: ',
                max: 'max: '
            },
            pinning: {
                pinLeft: 'Rögzítés bal oldalt',
                pinRight: 'Rögzítést jobb oldalt',
                unpin: 'Rögzítés feloldása'
            },
            columnMenu: {
                close: 'Bezár'
            },
            gridMenu: {
                aria: {
                    buttonLabel: 'Táblázat menü'
                },
                columns: 'Oszlopok:',
                importerTitle: 'Fájl importálása',
                exporterAllAsCsv: 'Összes adat exportálása csv-ben',
                exporterVisibleAsCsv: 'A látható adatok exportálása csv-ben',
                exporterSelectedAsCsv: 'A kiválasztott sorok exportálása csv-ben',
                exporterAllAsPdf: 'Összes adat exportálása pdf-ben',
                exporterVisibleAsPdf: ' látható adatok exportálása pdf-ben',
                exporterSelectedAsPdf: 'A kiválasztott sorok exportálása pdf-ben',
                clearAllFilters: 'Összes szűrő eltávolítása'
            },
            importer: {
                noHeaders: 'Az oszlopok nevét nem sikerült beolvasni.',
                noObjects: 'Nem sikerült beolvasni az sorokat.',
                invalidCsv: 'Hiba a fájl feldolgozása során.',
                invalidJson: 'Hiba a fájl feldolgozása során.',
                jsonNotArray: 'Az importál JSON fájlnak egy tömböt kell tartalmaznia.'
            },
            pagination: {
                aria: {
                    pageToFirst: 'Ugrás az első oldalra',
                    pageBack: 'Lapozás vissza',
                    pageSelected: 'Kiválasztott oldal',
                    pageForward: 'Lapozás előre',
                    pageToLast: 'Ugrás az utolsó oldalra'
                },
                sizes: 'sorok száma oldalanként',
                totalItems: 'sorból',
                through: 'through',
                of: 'a'
            },
            grouping: {
                group: 'Csoportosítás',
                ungroup: 'Csoportosítás megszüntetése',
                aggregate_count: 'Agg: Darabszám',
                aggregate_sum: 'Agg: Összeg',
                aggregate_max: 'Agg: Max',
                aggregate_min: 'Agg: Min',
                aggregate_avg: 'Agg: Átlag',
                aggregate_remove: 'Agg: Eltávolítás'
            }
        });
        return $delegate;
    });
  }
})();
