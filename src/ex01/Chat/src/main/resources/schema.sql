DROP SCHEMA IF EXISTS chat CASCADE;
CREATE SCHEMA IF NOT EXISTS chat;

CREATE TABLE IF NOT EXISTS chat.user
(
    id       serial PRIMARY KEY,
    name     varchar(30) NOT NULL UNIQUE,
    password varchar(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS chat.chatroom
(
    id         serial PRIMARY KEY,
    chat_name  varchar(30) NOT NULL UNIQUE,
    chat_owner integer     NOT NULL REFERENCES chat.user (id)
);

CREATE TABLE IF NOT EXISTS chat.message
(
    id        serial PRIMARY KEY,
    sender_id integer NOT NULL REFERENCES chat.user (id),
    room_id   integer NOT NULL REFERENCES chat.chatroom (id),
    text      text    NOT NULL,
    date_time timestamp DEFAULT CURRENT_TIMESTAMP
);