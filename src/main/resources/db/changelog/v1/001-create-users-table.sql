CREATE TABLE users(
    id BIGSERIAL PRIMARY KEY ,
    username VARCHAR(15) NOT NULL,
    password VARCHAR(60) NOT NULL,
    login VARCHAR NOT NULL,
    login_type VARCHAR NOT NULL,
    role varchar NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    deleted BOOLEAN NOT NULL DEFAULT false
)