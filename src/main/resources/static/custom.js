//ADDING A SCRIPT TAG MEANS YOU ARE NOW TYPING IN JAVASCRIPT
let map;
//THIS IS THE FUNCTION TO GET 
function initMap() {
	map = new google.maps.Map(document.getElementById('map'), {
		// THE PARAMETERS above labeled ''coords'' WILL SAY THE MAP WILL CENTER AT 
		// {...} LATITUDE AND LONGITUDE, THE ZOOM WILL ZOOM
		center: coords,
		zoom: 9,
		scrollwheel: false,
		
	});
	//code for sizing icon image
	let image = {
		url: 'palmTree.png', scaledSize:
			new google.maps.Size(30, 30)
	};

	let marker = new google.maps.Marker({
		//this will position it at the coords
		position: coords,
		//this will put it on map "map"
		map: map,
		//adds an icon if you use icon, 
		//but will be as big as regurlar picture,
		//change to image instead of the name of 'palmTree.png'
		//and write code for it
		icon: image,
		//TEXT ANIMATION TO MAKE THE ICON BOUNCE
		animation: google.maps.Animation.BOUNCE
	});
	
	//creates a string to add info about marker
	//THESES BELOW ARE THE TWO VARIABLE WE SET IN INDEX.HTML
	var contentString = '<h2>' + city + ', ' + state + '</h2>';

	//makes an info window to hold the string with information inside
	var infowindow = new google.maps.InfoWindow({
		content: contentString
	});
	//adds an event listener, which is listening for clicks,
	// that adds something to click for info at marker
	google.maps.event.addListener(marker, 'click', function() {
		infowindow.open(map, marker);
	});


}