app.value('grantedAuthorities', {
    list: ['NEM_VEDETT']
});

app.directive('requiredRole', function(grantedAuthorities) {
    return {
        restrict: 'A',
        link: function (scope, element, attrs) {
            if (attrs['requiredRole']) {
                var roles = grantedAuthorities.list;
                var requiredRole = attrs['requiredRole'];
                var hasRole = arrayContains(roles, requiredRole);
                if (!hasRole) {
                    element.remove();
                }
            }
        }
    };
});
