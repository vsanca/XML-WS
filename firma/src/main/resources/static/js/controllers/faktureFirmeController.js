/**
 * Created by Marko on 6/14/2017.
 */
angular.module('firmaApp.FaktureFirmeController',[])
    .controller('FaktureFirmeController', function ($scope, $localStorage, $location, $rootScope, $mdToast, FakturaFactory, ProizvodFactory, $mdDialog) {

        if($localStorage.tip != "Firma")
            $location.path("/");

        $scope.fakture = [];
        function faktureZaPotvrdu(){
            FakturaFactory.faktureZaPotvrdu($localStorage.logged.firma).success(function (data) {
                $scope.fakture = data;
            });
        }
        faktureZaPotvrdu();
    });