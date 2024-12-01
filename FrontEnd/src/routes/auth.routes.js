import { Router } from 'express';
import { login, register, home } from '../controllers/auth.controller.js'; // Verifica que la ruta esté bien

const router = Router();

// Definir las rutas
router.post('/register', register);
router.post('/login', login);
router.post('/home', home);

// Exportar las rutas
export default router;
