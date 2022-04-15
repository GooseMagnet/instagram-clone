CREATE SCHEMA IF NOT EXISTS instagram;

CREATE TABLE IF NOT EXISTS instagram.post_like
(
    post_id VARCHAR(255) NOT NULL,
    user_id INT          NOT NULL,
    PRIMARY KEY (post_id, user_id)
);

CREATE TABLE IF NOT EXISTS instagram.follow
(
    user_id     INT NOT NULL,
    follower_id INT NOT NULL,
    PRIMARY KEY (user_id, follower_id)
);

CREATE TABLE IF NOT EXISTS instagram.comment
(
    id           INT          NOT NULL AUTO_INCREMENT,
    user_id      INT          NOT NULL,
    post_id      VARCHAR(255) NOT NULL,
    content      TEXT         NOT NULL,
    date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);
