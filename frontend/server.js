const express = require('express');
const path = require('path');
const app = express();
const port = process.env.PORT || 3000;

const cors = require('cors');
app.use(cors());

// Servir archivos estáticos desde el directorio 'src'
app.use(express.static(path.join(__dirname, 'src')));

// Servir el archivo index.html
app.get('/', (req, res) => {
    res.sendFile(path.join(__dirname, 'src', 'index.html'));
});


app.listen(port, () => {
    console.log(`Frontend listening at http://localhost:${port}`);
});
