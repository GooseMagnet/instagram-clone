CREATE SCHEMA IF NOT EXISTS instagram;

CREATE TABLE IF NOT EXISTS instagram.user
(
    id           INT          NOT NULL AUTO_INCREMENT,
    email        VARCHAR(255) NOT NULL,
    username     VARCHAR(255) NOT NULL,
    password     VARCHAR(255) NOT NULL,
    date_created TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    avatar_path  VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);