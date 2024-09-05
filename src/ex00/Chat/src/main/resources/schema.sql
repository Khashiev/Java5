CREATE TABLE IF NOT EXISTS users (
    id serial PRIMARY KEY,
    login varchar(30) NOT NULL,
    password varchar(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS chatroom (
    id serial PRIMARY KEY,
    name varchar(30) NOT NULL,
    owner integer NOT NULL references users(id)
);

CREATE TABLE IF NOT EXISTS message (
    id serial PRIMARY KEY,
    author integer NOT NULL references users(id),
    chatroom integer NOT NULL references chatroom(id),
    text text NOT NULL ,
    dateTime timestamp default CURRENT_TIMESTAMP
);
