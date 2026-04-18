CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE tb_address
(
    address_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    country VARCHAR(100),
    cep VARCHAR(100),
    street VARCHAR(100),
    number_house VARCHAR(50),
    complemenet VARCHAR(100),
    neighborhood VARCHAR(100),
    city VARCHAR(100),
    state_name VARCHAR(10),
    recipients_name VARCHAR(100)
);