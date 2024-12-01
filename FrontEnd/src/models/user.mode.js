import { Sequelize, DataTypes } from 'sequelize';

// Configuración de la conexión con la base de datos
const sequelize = new Sequelize('postgres://postgres:12345678@localhost:5432/reservasuno');


// Definición del modelo User
const User = sequelize.define('clients', {
    username: {
        type: DataTypes.STRING,
        allowNull: false,
    },
    email: {
        type: DataTypes.STRING,
        allowNull: false,
        unique: true,
    },
    password: {
        type: DataTypes.STRING,
        allowNull: false,
    },
});

// Exporta el modelo y la conexión
export default { sequelize, User };
