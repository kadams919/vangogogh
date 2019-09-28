var geocoder;
var map;
var address = document.getElementById("location-address").value;
function initMap() {
	var map = new google.maps.Map(document.getElementById('map'), {
		zoom : 8,
		center : {
			lat : -34.397,
			lng : 150.644
		}
	});
	geocoder = new google.maps.Geocoder();
	codeAddress(geocoder, map);
}

function codeAddress(geocoder, map) {
	console.log(address)
	geocoder.geocode({
		'address' : address
	}, function(results, status) {
		if (status === 'OK') {
			map.setCenter(results[0].geometry.location);
			var marker = new google.maps.Marker({
				map : map,
				position : results[0].geometry.location
			});
		} else {
			alert('Geocode was not successful for the following reason: '
					+ status);
		}
	});
}
