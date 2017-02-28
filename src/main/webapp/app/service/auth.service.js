angular.module('app')
    .factory('Auth', ['jwtHelper', '$http', '$localStorage', '$timeout','$rootScope',
        function (jwtHelper, $http, $localStorage, $timeout,$rootScope) {
        function urlBase64Decode(str) {
            var output = str.replace('-', '+').replace('_', '/');
            switch (output.length % 4) {
                case 0:
                    break;
                case 2:
                    output += '==';
                    break;
                case 3:
                    output += '=';
                    break;
                default:
                    throw 'Illegal base64url string!';
            }
            return window.atob(output);
        }

        return {
            login: function (data, success, error) {
                $http.post('/userlogin', data).success(
                    function (response) {
                        $localStorage.token = response.token;
                        // $localStorage.refresh_token = response.refresh_token;
                        success(response);
                    }
                ).error(error)
            },
            logout: function (success) {
                delete $localStorage.token;
            },
            getUser: function () {
                var token = $localStorage.token;
                var user = {}; var refresher = {};
                if (typeof token !== 'undefined') {
                    var encoded = token.split('.')[1];
                    user = JSON.parse(urlBase64Decode(encoded));
                }
                return user;
            }
        };
    }
]);