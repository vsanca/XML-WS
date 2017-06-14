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

        $scope.openStavkeDialog = function(ev, stavke, oznakaValute, zaglavlje, faktura) {
            $mdDialog.show({
                controller: stavkeController,
                templateUrl: 'html/stavke.html',
                parent: angular.element(document.body),
                targetEvent: ev,
                clickOutsideToClose:true,
                locals:{stavke: stavke, oznakaValute: oznakaValute, zaglavlje: zaglavlje, faktura: faktura}
            })
                .then(function(answer) {
                    var index = $scope.fakture.zaglavljeStavkeDTOS.indexOf(answer);
                    if(index > -1)
                        $scope.fakture.zaglavljeStavkeDTOS.splice(index, 1);
                });
        }

        function stavkeController($scope, $mdDialog, stavke, oznakaValute, zaglavlje, faktura) {

            $scope.stavke = stavke;
            $scope.oznakaValute = oznakaValute;

            $scope.potvrdiKupovinu = function(){
                FakturaFactory.potvrdiKupovinu(zaglavlje).success(function (data) {
                    $mdToast.show(
                        $mdToast.simple()
                            .textContent('Uspešno ste potvrdili fakturu!')
                            .hideDelay(3000)
                            .position('top center')
                            .theme('success-toast')
                    );

                    $mdDialog.hide(faktura);
                });
            };

            $scope.odbijKupovinu = function(){
                FakturaFactory.odbijKupovinu(zaglavlje).success(function (data) {
                    $mdToast.show(
                        $mdToast.simple()
                            .textContent('Uspešno ste odbili fakturu!')
                            .hideDelay(3000)
                            .position('top center')
                            .theme('success-toast')
                    );

                    $mdDialog.hide(faktura);
                });
            };
            // $scope.kupovina = {
            //     "kupacID": null,
            //     "proizvodi": [],
            //     "kolicine": [],
            //     "oznakaValute": "RSD"
            // }
            // $scope.kupi = function () {
            //     $scope.kupovina.kupacID = firma.id;
            //     for(var i = 0; i < $scope.proizvodiZaKupovinu.length; i++){
            //         $scope.kupovina.proizvodi.push($scope.proizvodiZaKupovinu[i].proizvod);
            //         $scope.kupovina.kolicine.push($scope.proizvodiZaKupovinu[i].kolicina);
            //     }
            //
            //     ProizvodFactory.kupiProizvode($scope.kupovina).success(function () {
            //         $mdToast.show(
            //             $mdToast.simple()
            //                 .textContent('Uspešno ste poslali fakturu!')
            //                 .hideDelay(3000)
            //                 .position('top center')
            //                 .theme('success-toast')
            //         );
            //
            //         $mdDialog.hide();
            //     });
            // };

            $scope.cancel = function() {
                $mdDialog.cancel();
            };
        }
    });