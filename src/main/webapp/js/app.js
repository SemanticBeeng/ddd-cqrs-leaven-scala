var leavenApp = angular.module('leavenApp', ['productListServices','basketServices']).
    config(['$routeProvider', function($routeProvider) {

        $routeProvider.
            when('/products/', {templateUrl: 'template/sales/productList.html', controller: ProductListController}).
            otherwise({redirectTo: '/products/'});
}]);