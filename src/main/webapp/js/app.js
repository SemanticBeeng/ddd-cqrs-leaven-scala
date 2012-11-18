var leavenApp = angular.module('leavenApp', ['productListServices','basketServices', 'utilServices']).
    config(['$routeProvider', function($routeProvider) {

        $routeProvider.
            when('/products/', {templateUrl: 'template/sales/productList.html', controller: ProductListController}).
            when('/confirmOrder/:orderId', {templateUrl: 'template/sales/orderConfirmation.html', controller: OrderConfirmationController}).
            otherwise({redirectTo: '/products/'});
}]);