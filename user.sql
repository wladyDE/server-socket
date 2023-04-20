CREATE TABLE user
(
    user_id  INT          NOT NULL AUTO_INCREMENT,
    version  INT,
    login    VARCHAR(50)  NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (user_id)
)