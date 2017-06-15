/**
 * Created by Marko on 6/15/2017.
 */
angular.module('firmaApp.PotvrdjeneFaktureFirmeController',[])
    .controller('PotvrdjeneFaktureFirmeController', function ($scope, $localStorage, $location, $rootScope, $mdToast, FakturaFactory, ProizvodFactory, $mdDialog) {

        if($localStorage.tip != "Firma")
            $location.path("/");

        $scope.potvrdjeneFakture = [];
        function getPotvrdjeneFakture(){
            FakturaFactory.potvrdjeneFakture($localStorage.logged.firma).success(function (data) {
                $scope.potvrdjeneFakture = data;
            });
        }
        getPotvrdjeneFakture();

        $scope.openPotvrdjeneStavkeDialog = function(ev, stavke, oznakaValute, zaglavlje, faktura) {
            $mdDialog.show({
                controller: potvrdjeneStavkeController,
                templateUrl: 'html/potvrdjeneStavke.html',
                parent: angular.element(document.body),
                targetEvent: ev,
                clickOutsideToClose:true,
                locals:{stavke: stavke, oznakaValute: oznakaValute, zaglavlje: zaglavlje, faktura: faktura}
            })
                .then(function(answer) {
                    var index = $scope.potvrdjeneFakture.zaglavljeStavkeDTOS.indexOf(answer);
                    if(index > -1)
                        $scope.potvrdjeneFakture.zaglavljeStavkeDTOS.splice(index, 1);
                });
        }

        function potvrdjeneStavkeController($scope, $mdDialog, stavke, oznakaValute, zaglavlje, faktura) {

            $scope.stavke = stavke;
            $scope.oznakaValute = oznakaValute;

            $scope.getNalog = function(){
                FakturaFactory.get_nalog_za_prenos(zaglavlje).success(function (data) {
                    var nalogZaPlacanje = data;
                    FakturaFactory.posalji_nalog_za_prenos(nalogZaPlacanje).success(function (data) {
                        if(data){
                            FakturaFactory.novo_stanje_proizvoda(zaglavlje).success(function (data) {
                                $mdToast.show(
                                    $mdToast.simple()
                                        .textContent('Uspešno ste poslali nalog!')
                                        .hideDelay(3000)
                                        .position('top center')
                                        .theme('success-toast')
                                );

                                $mdDialog.hide(faktura);
                            });
                        }else{
                            $mdToast.show(
                                $mdToast.simple()
                                    .textContent('Neuspešno slanje fakture!')
                                    .hideDelay(3000)
                                    .position('top center')
                                    .theme('error-toast')
                            );

                            $mdDialog.hide(faktura);
                        }
                    });
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

            $scope.cancel = function() {
                $mdDialog.cancel();
            };
        }

    });