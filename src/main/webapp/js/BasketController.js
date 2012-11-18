function BasketController($scope, $location, basket, uuidGenerator) {
    $scope.basket = basket;

    $scope.clearBasket = function() {
        basket.clear();
        $location.path("/products/")
    };
    $scope.checkout = function() {
        var orderId = uuidGenerator.generateUUID();
        $location.path('/confirmOrder/' + orderId)
    }
}