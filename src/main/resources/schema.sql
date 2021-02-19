DROP TABLE vote IF EXISTS;
DROP TABLE meal IF EXISTS;
DROP TABLE menu IF EXISTS;
DROP TABLE restaurant IF EXISTS;
DROP TABLE users IF EXISTS;
DROP SEQUENCE GLOBAL_SEQ IF EXISTS;

CREATE SEQUENCE GLOBAL_SEQ AS INTEGER START WITH 100000;

CREATE TABLE users
(
    id         INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    name       VARCHAR(255)            NOT NULL,
    password   VARCHAR(255)            NOT NULL,
    registered DATE DEFAULT TODAY NOT NULL,
    enabled    BOOLEAN   DEFAULT TRUE  NOT NULL,
    admin    BOOLEAN   DEFAULT FALSE NOT NULL,
    CONSTRAINT user_name UNIQUE (name)
);

CREATE TABLE restaurant
(
    id         INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    name       VARCHAR(255)            NOT NULL,
    CONSTRAINT restaurant_name UNIQUE (name)
);

CREATE TABLE MENU
(
    id            INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    restaurant_id INTEGER NOT NULL,
    regist_date   DATE DEFAULT TODAY NOT NULL,
    CONSTRAINT menu_idx UNIQUE (restaurant_id, regist_date),
    FOREIGN KEY (restaurant_id) REFERENCES RESTAURANT (id) ON DELETE CASCADE
);

CREATE TABLE MEAL
(
    id      INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    name    VARCHAR(255)   NOT NULL,
    price   DECIMAL(19, 2) NOT NULL,
    menu_id INTEGER        NOT NULL,
    FOREIGN KEY (menu_id) REFERENCES MENU (id) ON DELETE CASCADE
);

CREATE TABLE VOTE
(
    id      INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    vote_date DATE DEFAULT TODAY NOT NULL,
    user_id    INTEGER                 NOT NULL,
    menu_id    INTEGER                 NOT NULL,
    CONSTRAINT vote_idx UNIQUE (vote_date, user_id),
    FOREIGN KEY (user_id) REFERENCES USERS(id) ON DELETE CASCADE,
    FOREIGN KEY (menu_id) REFERENCES MENU (id) ON DELETE CASCADE
);


