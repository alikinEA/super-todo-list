CREATE TABLE users (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    email varchar(40) NOT NULL UNIQUE,
    login varchar(40) NOT NULL UNIQUE,
    password_hash varchar(120) NOT NULL
);