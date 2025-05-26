var app = angular.module('games', []);

app.controller("GamesController", function ($scope, $http) {
    
    function formatLocalDate(date) {
        const yyyy = date.getFullYear();
        const mm = String(date.getMonth() + 1).padStart(2, '0'); // месяцы с 0
        const dd = String(date.getDate()).padStart(2, '0');
        return `${yyyy}-${mm}-${dd}`;
    }

    $scope.getDevelopers = function () {
        return $http.get('/ComputerGames/developers')
            .then(function (response) {
                $scope.developersList = response.data;
            }, function (error) {
                console.error(error.data);
            });
    };

    $scope.getPublishers = function () {
        return  $http.get('/ComputerGames/publishers')
            .then(function (response) {
                $scope.publishersList = response.data;
            }, function (error) {
                console.error(error.data);
            });
    };

    $scope.getGames = function() {
        return $http.get('/ComputerGames/games')
            .then(function(response) {
                $scope.gamesList = response.data.map(function (game) {
                    const developer = $scope.developersList.find(d => d.id === game.developer);
                    const publisher = $scope.publishersList.find(p => p.id === game.publisher);
                    
                    return {
                        ...game,
                        developerName: developer ? developer.name : 'Unknown',
                        publisherName: publisher ? publisher.name : 'Unknown'
                    };
                });
            }, function (error) {
                console.error(error.data);
            });
    };

    $scope.addGame = function() {

        const params = new URLSearchParams();
        params.append('title', $scope.newGame.title);
        params.append('price', $scope.newGame.price);
        params.append('developer', $scope.newGame.developer);
        params.append('publisher', $scope.newGame.publisher);
        params.append('releaseDate', formatLocalDate($scope.newGame.releaseDate));

        $http({
            method: 'POST',
            url: '/ComputerGames/games',
            data: params.toString(),
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
        })
        .then(function(response) {
            console.log(response.data);
            return $scope.getGames();
        }, function(error) {
            console.error(error.data);
        });
    };

    $scope.deleteGame = function(id) {
        $http.delete('/ComputerGames/games/' + id)
            .then(function(response) {
                console.log(response.data);
                return $scope.getGames();
            }, function(error) {
                console.error(error.data);
            });
    }

    Promise.all([$scope.getDevelopers(), $scope.getPublishers()])
        .then(function() {
            return $scope.getGames();
    });
});