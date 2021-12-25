mapboxgl.accessToken = '<YOUR-API-KEY>';

let map

navigator.geolocation.getCurrentPosition(success, error, { enableHighAccuracy: true })

function success(position) {
    console.log(position);
    // mapbox takes lng first
    setupMap([position.coords.longitude, position.coords.latitude])
}

function error() {

}

document.getElementById('add-marker').addEventListener('click', addMarker)

function addMarker(event) {
    console.log("clicked", event);

    const coordinates = document.getElementById('coordinates');

    const marker = new mapboxgl.Marker({ draggable: true})
        .setLngLat([0, 0])
        .addTo(map);

    function onDragEnd() {
        const lngLat = marker.getLngLat();
        coordinates.style.display = 'block';
        coordinates.innerHTML = `Longitude: ${lngLat.lng}<br />Latitude: ${lngLat.lat}`;
    }

    marker.on('dragend', onDragEnd);
}

function setupMap(center) {
    // const map = new mapboxgl.Map({
    //     container: 'map', // container ID
    //     style: 'mapbox://styles/mapbox/streets-v11', // style URL
    //     // center: [-74.5, 40], // starting position [lng, lat]
    //     center,
    //     zoom: 16 // starting zoom
    // });

    map = new mapboxgl.Map({
        container: 'map',
        style: 'mapbox://styles/mapbox/streets-v11',
        center,
        zoom: 16
    });
}


