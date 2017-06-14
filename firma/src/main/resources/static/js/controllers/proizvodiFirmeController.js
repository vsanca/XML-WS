/**
 * Created by Marko on 6/13/2017.
 */
angular.module('firmaApp.ProizvodiFirmeController',[])
    .controller('ProizvodiFirmeController', function ($scope, $localStorage, $location, $rootScope, $mdToast, FirmaFactory, ProizvodFactory, $mdDialog) {

        if($localStorage.tip != "Firma")
            $location.path("/");

        $scope.firme = [];
        function getAllFirme(){
            FirmaFactory.getAllFirme().success(function (data) {
                for(var i = 0; i < data.length; i++){
                    if(data[i].id != $localStorage.logged.firma.id)
                        $scope.firme.push(data[i]);
                }
            });
        }
        getAllFirme();
        $scope.firma = null;



        $scope.proizvodiByFirma = [];
        $scope.getProizvodiByFirma = function(firma){
            if(firma != null) {
                ProizvodFactory.getZaFirmu(firma.id).success(function (data) {
                    for(var i = 0; i < data.length; i++){
                        var temp = {"proizvod": null, "kolicina": 0, "kupujem": "Ne"};
                        temp.proizvod = data[i];
                        $scope.proizvodiByFirma.push(temp);
                    }
                });
            }else{
                $scope.proizvodiByFirma = [];
            }
        }


        $scope.openCartDialog = function(ev, proizvodi) {
            $mdDialog.show({
                controller: cartController,
                templateUrl: 'html/korpa.html',
                parent: angular.element(document.body),
                targetEvent: ev,
                clickOutsideToClose:true,
                locals:{proizvodi: proizvodi, firma: $localStorage.logged.firma}
            })
                .then(function() {

                });
        }

        function cartController($scope, $mdDialog, proizvodi, firma) {

            $scope.proizvodiZaKupovinu = [];
            function getZaKupovinu(){
                for(var i = 0; i < proizvodi.length; i++){
                    if(proizvodi[i].kupujem == "Da")
                        $scope.proizvodiZaKupovinu.push(proizvodi[i]);
                }
            }
            getZaKupovinu();

            $scope.ukupno = 0;
            $scope.izracunajUkupno = function () {
                $scope.ukupno = 0;
                for(var i = 0; i < $scope.proizvodiZaKupovinu.length; i++){
                    $scope.ukupno += $scope.proizvodiZaKupovinu[i].kolicina * $scope.proizvodiZaKupovinu[i].proizvod.cena;
                }
            }
            $scope.izracunajUkupno();

            $scope.kupovina = {
                "kupacID": null,
                "proizvodi": [],
                "kolicine": [],
                "oznakaValute": "RSD"
            }
            $scope.kupi = function () {
                $scope.kupovina.kupacID = firma.id;
                for(var i = 0; i < $scope.proizvodiZaKupovinu.length; i++){
                    $scope.kupovina.proizvodi.push($scope.proizvodiZaKupovinu[i].proizvod);
                    $scope.kupovina.kolicine.push($scope.proizvodiZaKupovinu[i].kolicina);
                }

                ProizvodFactory.kupiProizvode($scope.kupovina).success(function () {
                    $mdToast.show(
                        $mdToast.simple()
                            .textContent('Uspešno ste poslali fakturu!')
                            .hideDelay(3000)
                            .position('top center')
                            .theme('success-toast')
                    );

                    $mdDialog.hide();
                });
            };

            $scope.cancel = function() {
                $mdDialog.cancel();
            };
        }
    });