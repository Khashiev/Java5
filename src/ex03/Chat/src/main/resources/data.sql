INSERT INTO chat.user (name, password) VALUES ('user1', '1111');
INSERT INTO chat.user (name, password) VALUES ('user2', '2222');
INSERT INTO chat.user (name, password) VALUES ('user3', '3333');
INSERT INTO chat.user (name, password) VALUES ('user4', '4444');
INSERT INTO chat.user (name, password) VALUES ('user5', '5555');

INSERT INTO chat.chatroom (chat_name, chat_owner) VALUES ('chat1', 1);
INSERT INTO chat.chatroom (chat_name, chat_owner) VALUES ('chat2', 2);
INSERT INTO chat.chatroom (chat_name, chat_owner) VALUES ('chat3', 3);
INSERT INTO chat.chatroom (chat_name, chat_owner) VALUES ('chat4', 4);
INSERT INTO chat.chatroom (chat_name, chat_owner) VALUES ('chat5', 5);

INSERT INTO chat.message (sender_id, room_id, text) VALUES (1, 2, 'Hello, world 1!');
INSERT INTO chat.message (sender_id, room_id, text) VALUES (2, 2, 'Hello, world 2!');
INSERT INTO chat.message (sender_id, room_id, text) VALUES (3, 2, 'Hello, world 3!');
INSERT INTO chat.message (sender_id, room_id, text) VALUES (1, 1, 'Hello, world 4!');
INSERT INTO chat.message (sender_id, room_id, text) VALUES (5, 1, 'Hello, world 5!');
