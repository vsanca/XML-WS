/**
 * Created by Marko on 6/13/2017.
 */
angular.module('firmaApp.FirmaFactory', [])
    .factory('FirmaFactory', function($http){

        var factory = {};

        factory.getAllFirme = function () {
            return $http.get('/getAllFirme');
        };

        return factory;

    });