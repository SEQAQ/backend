SET NAMES 'utf8';
INSERT INTO users
VALUES (1, 'Cat', 'Cat', 'plain', 'test@reg.com', '114514', '--', NULL, NULL, 'Witchcraft', 'role', 'view', 0, 0, 0,0,0);
INSERT INTO users
VALUES (2, 'sAdmin', 'sAdmin', 'admin', 'test@reg.com', '114514', '--', NULL, NULL, 'Witchcraft', 'admin', 'view,edit',
        0, 0, 0,0,0);
INSERT INTO users
VALUES (3, 'Admin', 'Admin', 'admin', 'test@reg.com', '114514', '--', NULL, NULL, 'Witchcraft', 'role', 'view', 0, 0,
        0,0,0);
INSERT INTO questions
VALUES (1, '菜市场', CURRENT_TIMESTAMP, 0, 1, 0, CURRENT_TIMESTAMP, '为什么年轻人不讲武德？');
INSERT INTO answers
VALUES (1, CURRENT_TIMESTAMP, 0, 1, CURRENT_TIMESTAMP, 666, 233, 1);
INSERT INTO replies
VALUES (1, CURRENT_TIMESTAMP, 0, 1, 1, 1024, 1212, 0);
