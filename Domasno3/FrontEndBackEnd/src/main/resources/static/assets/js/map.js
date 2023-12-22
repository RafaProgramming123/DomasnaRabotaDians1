document.addEventListener('DOMContentLoaded', function () {
    const map = L.map('map').setView([41.7377, 21.7532], 8);

    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png').addTo(map);

    // Check if allMuseums is a string and parse it if necessary
    let museumsData = allMuseums;
    if (typeof allMuseums === 'string') {
        museumsData = JSON.parse(allMuseums);
    }

    console.log(museumsData);

    museumsData.forEach(museum => {
        const marker = L.marker([museum.lat, museum.lon]).addTo(map);
        const popupContent = `<strong>${museum.name}</strong><br>${museum.description || ''}`;
        marker.bindPopup(popupContent);
    });
});

function searchMuseums() {
    const searchTerm = document.getElementById('search-bar').value.toLowerCase();
    let museumsData = allMuseums;
    if (typeof allMuseums === 'string') {
        museumsData = JSON.parse(allMuseums);
    }

    const filteredMuseums = museumsData.filter(museum =>
        (museum.name && museum.name.toLowerCase().includes(searchTerm)) ||
        (museum.description && museum.description.toLowerCase().includes(searchTerm))
    );

    clearMarkers();

    filteredMuseums.forEach(museum => {
        const marker = L.marker([museum.lat, museum.lon]).addTo(map);
      //  const buttonHTML = `<button onclick="buttonClicked('${museum.id}')">More Info</button>`;
        const popupContent = `<strong>${museum.name}</strong><br>${museum.description || ''}`;
        marker.bindPopup(popupContent);

    });


}

function clearMarkers() {
    map.eachLayer(layer => {
        if (layer instanceof L.Marker) {
            map.removeLayer(layer);
        }
    });
}
