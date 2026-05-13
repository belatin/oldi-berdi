-- liquibase formatted sql

-- changeset author:010-insert-mobile-phone-attributes

-- 1. Holat (SELECT)
INSERT INTO category_attributes (name, attribute_type, required, category_id, created_at, updated_at, deleted)
VALUES ('Holat', 'SELECT', true, 3, now(), now(), false);

INSERT INTO category_attribute_options (value, attribute_id, created_at, updated_at, deleted)
VALUES ('Yangi', currval('category_attributes_id_seq'), now(), now(), false),
       ('Ishlatilgan', currval('category_attributes_id_seq'), now(), now(), false);

-- 2. IMEI ro'yxatga olindi (SELECT)
INSERT INTO category_attributes (name, attribute_type, required, category_id, created_at, updated_at, deleted)
VALUES ('IMEI ro''yxatga olindi', 'SELECT', false, 3, now(), now(), false);

INSERT INTO category_attribute_options (value, attribute_id, created_at, updated_at, deleted)
VALUES ('Ha', currval('category_attributes_id_seq'), now(), now(), false),
       ('Yo''q', currval('category_attributes_id_seq'), now(), now(), false);

-- 3. Brend (SELECT)
INSERT INTO category_attributes (name, attribute_type, required, category_id, created_at, updated_at, deleted)
VALUES ('Brend', 'SELECT', false, 3, now(), now(), false);

INSERT INTO category_attribute_options (value, attribute_id, created_at, updated_at, deleted)
VALUES ('Boshqa marka', currval('category_attributes_id_seq'), now(), now(), false),
       ('Alcatel-Lucent', currval('category_attributes_id_seq'), now(), now(), false),
       ('Apple', currval('category_attributes_id_seq'), now(), now(), false),
       ('Artel', currval('category_attributes_id_seq'), now(), now(), false),
       ('Asus', currval('category_attributes_id_seq'), now(), now(), false),
       ('BlackBerry', currval('category_attributes_id_seq'), now(), now(), false),
       ('Fly', currval('category_attributes_id_seq'), now(), now(), false),
       ('Google', currval('category_attributes_id_seq'), now(), now(), false),
       ('Honor', currval('category_attributes_id_seq'), now(), now(), false),
       ('HTC', currval('category_attributes_id_seq'), now(), now(), false),
       ('Huawei', currval('category_attributes_id_seq'), now(), now(), false),
       ('Infinix', currval('category_attributes_id_seq'), now(), now(), false),
       ('inoi', currval('category_attributes_id_seq'), now(), now(), false),
       ('Lenovo', currval('category_attributes_id_seq'), now(), now(), false),
       ('LG', currval('category_attributes_id_seq'), now(), now(), false),
       ('Meizu', currval('category_attributes_id_seq'), now(), now(), false),
       ('Mio', currval('category_attributes_id_seq'), now(), now(), false),
       ('Motorola', currval('category_attributes_id_seq'), now(), now(), false),
       ('Nokia', currval('category_attributes_id_seq'), now(), now(), false),
       ('Nothing', currval('category_attributes_id_seq'), now(), now(), false),
       ('Novey', currval('category_attributes_id_seq'), now(), now(), false),
       ('OnePlus', currval('category_attributes_id_seq'), now(), now(), false),
       ('Oppo', currval('category_attributes_id_seq'), now(), now(), false),
       ('Pantech', currval('category_attributes_id_seq'), now(), now(), false),
       ('Poco', currval('category_attributes_id_seq'), now(), now(), false),
       ('realme', currval('category_attributes_id_seq'), now(), now(), false),
       ('RedMagic', currval('category_attributes_id_seq'), now(), now(), false),
       ('Redmi', currval('category_attributes_id_seq'), now(), now(), false),
       ('Samsung', currval('category_attributes_id_seq'), now(), now(), false),
       ('Sony', currval('category_attributes_id_seq'), now(), now(), false),
       ('Sony Ericsson', currval('category_attributes_id_seq'), now(), now(), false),
       ('Tecno', currval('category_attributes_id_seq'), now(), now(), false),
       ('Vertu', currval('category_attributes_id_seq'), now(), now(), false),
       ('Vivo', currval('category_attributes_id_seq'), now(), now(), false),
       ('Xiaomi', currval('category_attributes_id_seq'), now(), now(), false),
       ('ZTE', currval('category_attributes_id_seq'), now(), now(), false);

-- 4. Batareya quvvati (NUMBER)
INSERT INTO category_attributes (name, attribute_type, required, category_id, created_at, updated_at, deleted)
VALUES ('Batareya quvvati (mAh)', 'NUMBER', false, 3, now(), now(), false);

-- 5. Operativ xotira (SELECT)
INSERT INTO category_attributes (name, attribute_type, required, category_id, created_at, updated_at, deleted)
VALUES ('Operativ xotira', 'SELECT', false, 3, now(), now(), false);

INSERT INTO category_attribute_options (value, attribute_id, created_at, updated_at, deleted)
VALUES ('2 GB', currval('category_attributes_id_seq'), now(), now(), false),
       ('4 GB', currval('category_attributes_id_seq'), now(), now(), false),
       ('6 GB', currval('category_attributes_id_seq'), now(), now(), false),
       ('8 GB', currval('category_attributes_id_seq'), now(), now(), false),
       ('12 GB', currval('category_attributes_id_seq'), now(), now(), false),
       ('16 GB', currval('category_attributes_id_seq'), now(), now(), false);

-- 6. Doimiy xotira (SELECT)
INSERT INTO category_attributes (name, attribute_type, required, category_id, created_at, updated_at, deleted)
VALUES ('Doimiy xotira', 'SELECT', false, 3, now(), now(), false);

INSERT INTO category_attribute_options (value, attribute_id, created_at, updated_at, deleted)
VALUES ('32 GB', currval('category_attributes_id_seq'), now(), now(), false),
       ('64 GB', currval('category_attributes_id_seq'), now(), now(), false),
       ('128 GB', currval('category_attributes_id_seq'), now(), now(), false),
       ('256 GB', currval('category_attributes_id_seq'), now(), now(), false),
       ('512 GB', currval('category_attributes_id_seq'), now(), now(), false),
       ('1 TB', currval('category_attributes_id_seq'), now(), now(), false);

-- 7. Ekran diagonali (NUMBER)
INSERT INTO category_attributes (name, attribute_type, required, category_id, created_at, updated_at, deleted)
VALUES ('Ekran diagonali (dyuym)', 'NUMBER', false, 3, now(), now(), false);