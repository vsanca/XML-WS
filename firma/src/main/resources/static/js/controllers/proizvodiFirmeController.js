/**
 * Created by Marko on 6/13/2017.
 */
angular.module('firmaApp.ProizvodiFirmeController',[])
    .controller('ProizvodiFirmeController', function ($scope, $localStorage, $location, $rootScope, $mdToast, FirmaFactory, ProizvodFactory) {

        if($localStorage.tip != "Firma")
            $location.path("/");

        $scope.firme = [];
        function getAllFirme(){
            FirmaFactory.getAllFirme().success(function (data) {
                $scope.firme = data;
            });
        }
        getAllFirme();
        $scope.firma = null;

        // $scope.pacijenti = [];
        // function getPacijenti(){
        //     PacijentService.getPacijenti().success(function (data) {
        //         $scope.pacijenti = data;
        //     });
        // }
        // getPacijenti();
        // $scope.pacijent = null;
        //
        // $scope.search = function (user) {
        //     var searchText = $scope.searchText.toLowerCase();
        //     fullname = user.osoba.oime.toLowerCase() + ' ' + user.osoba.oprezime.toLowerCase();
        //     fullnameInverse = user.osoba.oprezime.toLowerCase() + ' ' + user.osoba.oime.toLowerCase();
        //
        //     if (fullname.indexOf(searchText) != -1) {
        //         return true;
        //     }
        //     if (fullnameInverse.indexOf(searchText) != -1) {
        //         return true;
        //     }
        //     if (user.osoba.ojmbg.indexOf(searchText) != -1) {
        //         return true;
        //     }
        //     return false;
        // };
        //
        // $scope.printNalaz = function(id) {
        //     NalazService.printNalaz(id).success(function (data) {
        //         $mdToast.show(
        //             $mdToast.simple()
        //                 .textContent('Успешно сте креирали извештај!')
        //                 .hideDelay(3000)
        //                 .position('top center')
        //                 .theme('success-toast')
        //         );
        //
        //         var pdfAsDataUri = "data:application/pdf;base64,"+data;
        //         window.open(pdfAsDataUri);
        //     });
        // }
        //
        $scope.proizvodiByFirma = [];
        $scope.getProizvodiByFirma = function(firma){
            if(firma != null) {
                ProizvodFactory.getZaFirmu(firma.id).success(function (data) {
                    $scope.proizvodiByFirma = data;
                });
            }else{
                $scope.proizvodiByFirma = [];
            }
        }
    });