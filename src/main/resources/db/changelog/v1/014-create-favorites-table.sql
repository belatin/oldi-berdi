CREATE TABLE favorites (
        id BIGSERIAL PRIMARY KEY,
        user_id BIGINT NOT NULL REFERENCES users(id),
        ad_id BIGINT NOT NULL REFERENCES ads(id),
        created_at TIMESTAMP NOT NULL,
        updated_at TIMESTAMP NOT NULL,
        deleted BOOLEAN DEFAULT FALSE NOT NULL,
        CONSTRAINT uq_favorites_user_ad UNIQUE (user_id, ad_id)
);