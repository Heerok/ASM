angular.module('app')
    .directive('jtable', function () {

        function deserialise(data) {
            var jsondata = {};
            var pairs = data.split('&');
            for (var i = 0; i < pairs.length; i++) {
                var nv = pairs[i].split("=");
                jsondata[nv[0]] = nv[1];
            }
            return jsondata;
        }

        return {
            restrict: 'AE',
            template: '<div id="{{id}}"></div>',

            link: function (scope, el, attr) {
                var divid = attr.id;
                var baseurl = attr.baseurl;
                var title = attr.title;
                var fields = scope.fields;
                var config = scope.config || {list: true, create: true, delete : true, edit : true};
                var selected = $('#' + divid);

                var actions = {};

                if (config.list) {
                    //actions.listAction = baseurl + '/list';
                    actions.listAction = function (postData, jtParams) {
                        console.log("Loading from custom function...");
                        console.log(postData);
                        return $.Deferred(function ($dfd) {
                            $.ajax({
                                url: baseurl, //+ '/list?jtStartIndex=' +  + '&jtPageSize=' + jtParams.jtPageSize + '&jtSorting=' + jtParams.jtSorting,
                                type: 'GET',
                                contentType: "application/json; charset=utf-8",
                                dataType: 'json',
                                data: {
                                    jtStartIndex: jtParams.jtStartIndex,
                                    jtPageSize: jtParams.jtPageSize,
                                    jtSorting: jtParams.jtSorting
                                },
                                success: function (data) {
                                    $dfd.resolve(data);
                                },
                                error: function () {
                                    $dfd.reject();
                                }
                            });
                        });
                    }
                }

                if (config.create) {
                    actions.createAction = function (postData) {
                        return $.Deferred(function ($dfd) {
                            $.ajax({
                                url: baseurl,
                                type: 'POST',
                                contentType: "application/json; charset=utf-8",
                                dataType: 'json',
                                data: JSON.stringify(deserialise(postData)),
                                success: function (data) {
                                    $dfd.resolve(data);
                                },
                                error: function () {
                                    $dfd.reject();
                                }
                            });
                        });
                    }
                }

                if (config.edit) {
                    actions.updateAction = function (postData) {
                        var jsondata = deserialise(postData);
                        return $.Deferred(function ($dfd) {
                            $.ajax({
                                url: baseurl + '/' + jsondata.Id,
                                type: 'PUT',
                                contentType: "application/json; charset=utf-8",
                                dataType: 'json',
                                data: JSON.stringify(jsondata),
                                success: function (data) {
                                    $dfd.resolve(data);
                                },
                                error: function () {
                                    $dfd.reject();
                                }
                            });
                        });
                    }
                }

                if (config.delete) {
                    actions.deleteAction = function (jsondata) {
                        return $.Deferred(function ($dfd) {
                            $.ajax({
                                url: baseurl + '/' + jsondata.id,
                                type: 'DELETE',
                                success: function (data) {
                                    $dfd.resolve(data);
                                },
                                error: function () {
                                    $dfd.reject();
                                }
                            });
                        });
                    }
                }

                selected.jtable({
                    title: title,
                    paging: config.paging || true,
                    pageSize: config.pageSize || 10,
                    sorting: config.sorting || true,
                    defaultSorting: config.defaultSorting || 'id ASC',
                    actions: actions,
                    fields: scope.fields
                });
                selected.jtable('load');
            }
        };
    });