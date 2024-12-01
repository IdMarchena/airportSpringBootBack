import pkg from 'pg';
const { Pool } = pkg;

export const pool = new Pool({
  user: "postgres", 
  host: "localhost",  
  database: "reservasuno", 
  password: "12345678",    
  port: 5432,        
});

export const query = (text, params) => pool.query(text, params);

pool.connect()
  .then(() => console.log("Conectado a PostgreSQL"))
  .catch(err => console.error("Error al conectar a PostgreSQL", err));
