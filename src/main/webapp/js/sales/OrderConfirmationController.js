function OrderConfirmationController($scope, basket) {

    $scope.basket = basket;

    $scope.count = function() { return basket.count() }
}