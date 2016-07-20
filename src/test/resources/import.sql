INSERT INTO USER (u_nr, NAME, PASSWORD, COLOR) VALUES (1, 'Hans', 'Password', 'BLUE'),
    (0, 'Lena', 'Password', 'GREEN');

INSERT INTO BILL (NR, USER_NR, STATUS, TIMESTAMP)
  VALUES (1, 1, 'NEW', 'Time1'),
    (2, 1, 'NEW', 'Time2'),
    (3, 1, 'ASSIGNED', 'Time3'),
    (4, 0, 'NEW', 'Time4'),
    (5, 0, 'ASSIGNED', 'Time5');


INSERT INTO ITEM (ID, BILL_NR, DESCRIPTION, PRICE) VALUES (1, 1, 'Suppe', 2.99),
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

INSERT INTO OWNER (USER_NR, ITEM_ID)
    VALUES (1, 10),
      (1, 11),
      (0, 11),
      (0, 15),
      (1, 16),
      (0, 17),
      (1, 17);



