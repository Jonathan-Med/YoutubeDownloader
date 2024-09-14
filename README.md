<h1>Youtube Downloader</h1>
<p>Este proyecto está desarrollado con java para el backend y javascript/HTML y CSS para el front.</p>    
<p>Es una aplicación muy sencilla como la interfaz, Los propósitos de este proyecto son 2: <ul>
  <li> Conectar un frontend de un lenguaje diferente al backend.</li>
  <li> Usar docker para poder meter en contenedores ambos servicios y comprobar el funcionamiento.</li>
</ul></p>
<p>La aplicación es muy sencilla, simplemente se agrega la url de youtube y mediante la libreria yt-dlp con el que se pueden realizar las descargas,
en este caso puede ser video o audio.</p>
<p>En la segunda rama esta el proyecto listo para agregarlos en contenedores, con sus respectivos dockerfiles. En ese caso la aplicación frontend requiere de un server.js
que sirve para conectar el fronten con el puerto para que se pueda consultar la página.</p>
<p>No se agregarón muchas funcionalidades debido a que el proposito principal era otro.</p>
