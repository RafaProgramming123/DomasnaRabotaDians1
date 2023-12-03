let allMuseums = [
    {
        name: "Природонаучен Музеј",
        description: "Macedonian Museum of Natural History",
        lat: 42.0057090,
        lon: 21.4174425,
    },
    {
        name: "Музеј на град Берово",
        description: "Berovo Town Museum",
        lat: 41.7077548,
        lon: 22.8566138,
    },

];

document.addEventListener('DOMContentLoaded', function () {
    const map = L.map('map').setView([41.7377, 21.7532], 8);

    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png').addTo(map);

    allMuseums.forEach(museum => {
        const marker = L.marker([museum.lat, museum.lon]).addTo(map);

        const popupContent = `<strong>${museum.name}</strong><br>${museum.description}`;

        marker.bindPopup(popupContent);
    });
});

function searchMuseums() {
    const searchTerm = document.getElementById('search-bar').value.toLowerCase();
    const filteredMuseums = allMuseums.filter(museum =>
        museum.name.toLowerCase().includes(searchTerm) || museum.description.toLowerCase().includes(searchTerm)
    );


    clearMarkers();


    filteredMuseums.forEach(museum => {
        const marker = L.marker([museum.lat, museum.lon]).addTo(map);

        const popupContent = `<strong>${museum.name}</strong><br>${museum.description}`;

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