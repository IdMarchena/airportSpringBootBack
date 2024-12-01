import app from './app.js';
import { pool } from './db.js';

// Prueba inicial de la conexión
const testDB = async () => {
  try {
    const res = await pool.query("SELECT NOW()");
    console.log("Hora actual desde la base de datos:", res.rows[0]);
  } catch (err) {
    console.error("Error al probar la base de datos:", err);
  }
};

testDB();

app.listen(4000, () => {
  console.log('listening on port', 4000);
});
