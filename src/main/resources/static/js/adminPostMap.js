function initAutocomplete() {

    var input = document.getElementById('autocomplete');
    var autocomplete = new google.maps.places.Autocomplete(input);
    autocomplete.setFields([ 'place_id', 'geometry', 'name',
            'formatted_address' ]);
    var geocoder = new google.maps.Geocoder;
    autocomplete
            .addListener(
                    'place_changed',
                    function () {
                        // infowindow.close();
                        var place = autocomplete.getPlace();

                        if (!place.place_id) {
                            return;
                        }
                        geocoder
                                .geocode(
                                        {
                                            'placeId' : place.place_id
                                        },
                                        function (results, status) {
                                            if (status !== 'OK') {
                                                window
                                                        .alert('Geocoder failed due to: '
                                                                + status);
                                                return;
                                            }

                                            console
                                                    .log(results[0].formatted_address);
                                            document
                                                    .getElementById('autocomplete').value = results[0].formatted_address;
                                        });
                    });
    google.maps.event.addDomListener(input, 'keydown', function (e) {
        if (e.keyCode == 13) {
            e.preventDefault();
        }
    });
}

// Bias the autocomplete object to the user's geographical location,
// as supplied by the browser's 'navigator.geolocation' object.
function geolocate() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
            var geolocation = {
                lat : position.coords.latitude,
                lng : position.coords.longitude
            };
        });
    }
}