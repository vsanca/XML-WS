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

        return factory;

    });