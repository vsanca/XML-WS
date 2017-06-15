/**
 * Created by Marko on 6/14/2017.
 */
angular.module('firmaApp.FakturaFactory', [])
    .factory('FakturaFactory', function($http){

        var factory = {};

        factory.faktureZaPotvrdu = function (id) {
            return $http.post('/faktureZaPotvrdu', id);
        };

        factory.potvrdiKupovinu = function (zaglavlje) {
            return $http.post('/potvrdiKupovinu', zaglavlje);
        };

        factory.odbijKupovinu = function (zaglavlje) {
            return $http.post('/odbijKupovinu', zaglavlje);
        };


        factory.potvrdjeneFakture = function (firma) {
            return $http.post('/potvrdjeneFakture', firma);
        };

        factory.get_nalog_za_prenos = function (zaglavlje) {
            return $http.post('/get_nalog_za_prenos', zaglavlje);
        };

        factory.posalji_nalog_za_prenos = function (nalogZaPrenos) {
            return $http.post('/posalji_nalog_za_prenos', nalogZaPrenos);
        };

        factory.novo_stanje_proizvoda = function (zaglavlje) {
            return $http.post('/novo_stanje_proizvoda', zaglavlje);
        };

        return factory;

    });