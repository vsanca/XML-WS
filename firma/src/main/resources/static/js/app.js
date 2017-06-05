/**
 * Created by Hp on 6/5/2017.
 */

var app = angular.module('firmaApp', [
    'firmaApp.routes',
    'firmaApp.controllers',
    'firmaApp.services',
    'ngMaterial',
    'ngStorage']);

app.config(function($mdThemingProvider) {
    $mdThemingProvider.theme('default')
        .primaryPalette('grey')
        .accentPalette('yellow');
});
