INSERT INTO users (login, password) VALUES ('user1', '1111');
INSERT INTO users (login, password) VALUES ('user2', '2222');
INSERT INTO users (login, password) VALUES ('user3', '3333');
INSERT INTO users (login, password) VALUES ('user4', '4444');
INSERT INTO users (login, password) VALUES ('user5', '5555');

INSERT INTO chatroom (name, owner) VALUES ('chat1', 1);
INSERT INTO chatroom (name, owner) VALUES ('chat2', 2);
INSERT INTO chatroom (name, owner) VALUES ('chat3', 3);
INSERT INTO chatroom (name, owner) VALUES ('chat4', 4);
INSERT INTO chatroom (name, owner) VALUES ('chat5', 5);

INSERT INTO message (author, chatroom, text) VALUES (1, 1, 'hello 11');
INSERT INTO message (author, chatroom, text) VALUES (1, 3, 'hello 13');
INSERT INTO message (author, chatroom, text) VALUES (2, 1, 'hello 21');
INSERT INTO message (author, chatroom, text) VALUES (4, 2, 'hello 42');
INSERT INTO message (author, chatroom, text) VALUES (5, 2, 'hello 52');
