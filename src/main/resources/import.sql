INSERT INTO USER (USER_ID, USER_NAME, PASSWORD, SALT, COLOR)
VALUES (1, 'Hans', BINARY(10000010), BINARY(10000010), 'DARK_BLUE'),
  (0, 'Lena', BINARY(10000010), BINARY(10000010), 'GREEN');

INSERT INTO BILL (BILL_ID, USER_ID, STATUS, BILL_NAME, DATE)
VALUES (1, 1, 'NEW', 'Billa', '2016-07-28'),
  (2, 1, 'NEW', 'Spar fuer Party','2016-07-23'),
  (3, 1, 'ASSIGNED', 'Billa', '2016-07-20'),
  (4, 0, 'NEW', 'Fortgehn', '2016-07-19'),
  (5, 0, 'ASSIGNED', 'Spar fuer Eltnern', '2016-07-01');


INSERT INTO ITEM (ITEM_ID, BILL_ID, DESCRIPTION, PRICE) VALUES (1, 1, 'Suppe', 2.99),
  (2, 1, 'Suppe', 2.99),
  (3, 1, 'Schoko', 2.4),
  (4, 1, 'Brot', 1.7),
  (5, 2, 'Zahnpasta', 1.99),
  (6, 2, 'Aufstrich', 1.7),
  (7, 2, 'Kaese', 3.19),
  (8, 2, 'Chips', 2.5),
  (9, 3, 'Brot', 1.7),
  (10, 3, 'Kaese', 2.99),
  (11, 4, 'Duplo', 3.99),
  (12, 4, 'Banane', 0.7),
  (13, 4, 'Apfel', 0.3),
  (14, 4, 'Mehl', 0.99),
  (15, 5, 'Brot', 1.7),
  (16, 5, 'Semmel', 0.7),
  (17, 5, 'Kaffee', 9.9);

INSERT INTO OWNER (USER_ID, ITEM_ID)
VALUES (1, 10),
  (1, 11),
  (0, 11),
  (0, 15),
  (1, 16),
  (0, 17),
  (1, 17);



