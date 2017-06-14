/**
 * Created by Marko on 6/13/2017.
 */
angular.module('firmaApp.ProizvodFactory', [])
    .factory('ProizvodFactory', function($http){

        var factory = {};

        factory.getZaFirmu = function (fid) {
            return $http.post('/getZaFirmu', parseInt(fid));
        };

        return factory;

    });