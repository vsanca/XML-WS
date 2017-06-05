/**
 * Created by Hp on 6/5/2017.
 */

var app = angular.module('firmaApp.routes', ['ngRoute']);

app.config(['$routeProvider','$httpProvider', function ($routeProvider, $httpProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'html/welcome.html'
        });
}]);
