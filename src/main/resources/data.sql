-- Users
INSERT INTO user (password, roles, username) VALUES ('$2a$10$ZltUehuYfLYJ8d3iu7cqeOpwTxtQZt5pBUuz7QQRN.yBjvPLXDEBS', 'USER', 'user');
INSERT INTO user (password, roles, username) VALUES ('$2a$10$CX/W15SFI6/bwquOE3R5leUCQcmRt/tTQaB8gGYOs.Rw0nnnDpZi.', 'ADMIN', 'admin');

-- Posts
INSERT INTO post (author_id, content, published_on, title, updated_on) VALUES (1, 'This is the first post of this application, nice!', '2023-11-07', 'First post!', NULL);
INSERT INTO post (author_id, content, published_on, title, updated_on) VALUES (1, 'This is the second post of this application, nice!', '2023-11-07', 'Second post!', NULL);
INSERT INTO post (author_id, content, published_on, title, updated_on) VALUES (2, 'This is the third post of this application, nice!', '2023-11-07', 'Third post!', NULL);