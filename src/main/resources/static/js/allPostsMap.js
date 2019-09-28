var geocoder;
var map;
function initMap() {
    console.log(addresses);
    var map = new google.maps.Map(document.getElementById('map'), {
        zoom : 8,
        center : {
            lat : -34.397,
            lng : 150.644
        }
    });
    geocoder = new google.maps.Geocoder();
    addresses.forEach(address => {
        codeAddress(address, geocoder, map); 
    });
}

function codeAddress(address, geocoder, map) {
    geocoder.geocode({
        'address' : address
    }, function (results, status) {
        if (status === 'OK') {
            map.setCenter(results[0].geometry.location);
            var marker = new google.maps.Marker({
                map : map,
                position : results[0].geometry.location
            });
        } else {
            console.log('Geocode was not successful for the following reason: '
                    + status);
        }
    });
}