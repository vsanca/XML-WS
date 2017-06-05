/**
 * Created by Hp on 6/5/2017.
 */

angular.module('firmaApp.LoginFactory', [])
    .factory('LoginFactory', function($http){

        var factory = {};

        factory.login = function (user) {
            return $http.post('/login', user);
        };

        return factory;

    });
