import User from "../models/user.mode.js"; // Cambia la ruta según la ubicación real del archivo
export const register = (req, res) => {
    const {email,password,username} = req.body;

    const newUser = User.User.create({
        username,
        email,
        password
    });
    console.log(newUser);
    
}
export const login = (req, res) => res.send('login');
export const home = (req, res) => res.send('home');