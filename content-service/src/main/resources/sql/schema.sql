CREATE SCHEMA IF NOT EXISTS instagram;

CREATE TABLE IF NOT EXISTS instagram.user_upload
(
    user_id       INT          NOT NULL,
    file_path     VARCHAR(255) NOT NULL,
    date_uploaded TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id, file_path)
);