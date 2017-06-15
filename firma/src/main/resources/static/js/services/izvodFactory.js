/**
 * Created by Marko on 6/15/2017.
 */
angular.module('firmaApp.IzvodFactory', [])
    .factory('IzvodFactory', function($http){

        var factory = {};

        factory.salji_zahtev_za_izvod = function (zahtevDTO) {
            return $http.post('/salji_zahtev_za_izvod', zahtevDTO);
        };

        return factory;

    });