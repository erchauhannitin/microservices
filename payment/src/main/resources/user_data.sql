INSERT INTO USERS (username, password, enabled) values ('user', 'pass', true);
INSERT INTO USERS (username, password, enabled) values ('admin', 'pass', true);
INSERT INTO USERS (username, password, enabled) values ('perfect', 'perfect', true);

INSERT INTO AUTHORITIES (username, authority) values ('user', 'ROLE_USER');
INSERT INTO AUTHORITIES (username, authority) values ('admin', 'ROLE_ADMIN');