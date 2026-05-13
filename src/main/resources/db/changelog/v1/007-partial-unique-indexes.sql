-- changeset author:007-partial-unique-indexes
CREATE UNIQUE INDEX idx_users_username_active ON users(username) WHERE deleted = false;
CREATE UNIQUE INDEX idx_users_login_active ON users(login) WHERE deleted = false;