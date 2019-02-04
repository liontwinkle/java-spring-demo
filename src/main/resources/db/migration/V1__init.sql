CREATE TABLE users (
    id IDENTITY NOT NULL,
    email VARCHAR(200) UNIQUE NOT NULL,
    role_value VARCHAR(50) NOT NULL,
    enabled BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL,
    created_by BIGINT,
    modified_at TIMESTAMP,
    modified_by BIGINT,

    PRIMARY KEY(id)
);

CREATE TABLE user_credentials (
    id IDENTITY NOT NULL,
    password_string VARCHAR(200) NOT NULL,
    user_id BIGINT NOT NULL,
    email VARCHAR(200) UNIQUE NOT NULL,

    PRIMARY KEY(id),
    CONSTRAINT UserCredentialUserFK FOREIGN KEY (user_id)  REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE user_token (
    id IDENTITY NOT NULL,
    token_string VARCHAR(255) NOT NULL,
    user_id BIGINT NOT NULL,
    expire_at TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL,
    created_by BIGINT,
    modified_at TIMESTAMP,
    modified_by BIGINT,
    PRIMARY KEY(id),
    CONSTRAINT UserTokenUserIdFK FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
)