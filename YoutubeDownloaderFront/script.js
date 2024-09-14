const backendUrl = 'http://localhost:8081';

function downloadContent(type) {
    const url = document.getElementById("url").value;

    if (!url) {
        alert("Por favor, ingrese una URL.");
        return;
    }

    // Construimos la URL completa del backend con el puerto incluido
    let endpoint = type === 'video' ? `${backendUrl}/download/video` : `${backendUrl}/download/audio`;

    fetch(endpoint + '?url=' + encodeURIComponent(url))
        .then(response => {
            if (response.ok) {
                return response.blob();
            } else {
                throw new Error("Error al descargar contenido");
            }
        })
        .then(blob => {
            const link = document.createElement('a');
            const objectUrl = URL.createObjectURL(blob);
            link.href = objectUrl;
            link.download = type === 'video' ? 'video.mp4' : 'audio.webm';
            link.click();
            URL.revokeObjectURL(objectUrl);
        })
        .catch(error => console.error("Hubo un problema con la descarga:", error));
}