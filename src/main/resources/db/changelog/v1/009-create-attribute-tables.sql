CREATE TABLE category_attributes
(
    id             BIGSERIAL PRIMARY KEY,
    name           VARCHAR(80) NOT NULL,
    attribute_type VARCHAR(15)  NOT NULL,
    required       BOOLEAN      NOT NULL DEFAULT false,
    category_id    BIGINT       NOT NULL REFERENCES categories (id),
    created_at     TIMESTAMP    NOT NULL,
    updated_at     TIMESTAMP    NOT NULL,
    deleted        BOOLEAN      NOT NULL DEFAULT false
);

CREATE TABLE category_attribute_options
(
    id           BIGSERIAL PRIMARY KEY,
    value        VARCHAR(80) NOT NULL,
    attribute_id BIGINT       NOT NULL REFERENCES category_attributes (id),
    created_at   TIMESTAMP    NOT NULL,
    updated_at   TIMESTAMP    NOT NULL,
    deleted      BOOLEAN      NOT NULL DEFAULT false
);

CREATE TABLE ad_attribute_values
(
    id           BIGSERIAL PRIMARY KEY,
    value        VARCHAR(255),
    ad_id        BIGINT NOT NULL REFERENCES ads (id),
    attribute_id BIGINT NOT NULL REFERENCES category_attributes (id),
    created_at   TIMESTAMP NOT NULL,
    updated_at   TIMESTAMP NOT NULL,
    deleted      BOOLEAN   NOT NULL DEFAULT false
);