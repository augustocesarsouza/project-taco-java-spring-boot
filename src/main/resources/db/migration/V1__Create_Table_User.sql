CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE tb_users
(
    user_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    login VARCHAR(100),
    name VARCHAR(100),
    last_name VARCHAR(100),
    time_zone VARCHAR(50),
    cpf VARCHAR(100),
    date_of_birth DATE,
    telephone VARCHAR(100),
    email VARCHAR(100),
    password_hash VARCHAR(255),
    user_image VARCHAR(255)
);