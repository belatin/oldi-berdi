CREATE TABLE categories(
    id BIGSERIAL PRIMARY KEY ,
    name VARCHAR(80) NOT NULL,
    description TEXT,
    parent_id BIGINT REFERENCES categories(id),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    deleted BOOLEAN NOT NULL DEFAULT false
)