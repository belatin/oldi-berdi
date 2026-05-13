CREATE TABLE locations(
    id BIGSERIAL PRIMARY KEY ,
    name VARCHAR(20) NOT NULL,
    location_type VARCHAR NOT NULL,
    parent_id BIGINT REFERENCES locations(id),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    deleted BOOLEAN NOT NULL DEFAULT false
)