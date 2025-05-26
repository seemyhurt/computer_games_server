var app = angular.module('developers', []);

app.controller("DevelopersController", function ($scope, $http) {
    
    function formatLocalDate(date) {
        const yyyy = date.getFullYear();
        const mm = String(date.getMonth() + 1).padStart(2, '0'); // месяцы с 0
        const dd = String(date.getDate()).padStart(2, '0');
        return `${yyyy}-${mm}-${dd}`;
    }

    $scope.getDevelopers = function () {
        $http.get('/ComputerGames/developers')
            .then(function (response) {
                $scope.developersList = response.data;
            }, function (error) {
                console.error(error.data);
            });
    };

    $scope.addDeveloper = function() {

        const params = new URLSearchParams();
        params.append('name', $scope.newDeveloper.name);
        params.append('country', $scope.newDeveloper.country);
        params.append('foundedDate', formatLocalDate($scope.newDeveloper.foundedDate));

        $http({
            method: 'POST',
            url: '/ComputerGames/developers',
            data: params.toString(),
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
        })
        .then(function(response) {
            console.log(response.data);
            $scope.getDevelopers();
        }, function(error) {
            console.error(error.data);
        });
    };

    $scope.deleteDeveloper = function(id) {
        $http.delete('/ComputerGames/developers/' + id)
            .then(function(response) {
                console.log(response.data);
                $scope.getDevelopers();
            }, function(error) {
                console.error(error.data);
            });
    }

    $scope.getDevelopers();
});