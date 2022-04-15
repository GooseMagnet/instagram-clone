CREATE SCHEMA IF NOT EXISTS instagram;

CREATE TABLE IF NOT EXISTS instagram.post
(
    user_id       INT          NOT NULL,
    post_id       VARCHAR(255) NOT NULL,
    date_uploaded TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id, post_id)
);