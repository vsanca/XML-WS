/**
 * Created by Hp on 6/5/2017.
 */

var app = angular.module('firmaApp.routes', ['ngRoute']);

app.config(['$routeProvider','$httpProvider', function ($routeProvider, $httpProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'html/welcome.html'
        })
        .when('/proizvodiFirme', {
            templateUrl: 'html/proizvodiFirme.html'
        })
        .when('/faktureFirme', {
            templateUrl: 'html/faktureFirme.html'
        })
        .when('/potvrdjeneFaktureFirme', {
            templateUrl: 'html/potvrdjeneFaktureFirme.html'
        })
        .when('/izvodi', {
            templateUrl: 'html/izvodi.html'
        });
}]);
