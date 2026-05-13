CREATE TABLE ads(
    id BIGSERIAL PRIMARY KEY ,
    title VARCHAR(100) NOT NULL ,
    description TEXT NOT NULL,
    price NUMERIC(19, 2) NOT NULL ,
    status VARCHAR NOT NULL ,
    user_id BIGINT NOT NULL REFERENCES users(id),
    category_id BIGINT NOT NULL REFERENCES categories(id),
    location_id BIGINT NOT NULL REFERENCES locations(id),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    deleted BOOLEAN NOT NULL DEFAULT false
)