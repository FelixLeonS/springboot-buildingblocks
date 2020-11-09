insert into USER (ID, EMAIL_ADDRESS, FIRST_NAME, LAST_NAME, ROLE, SSN, USER_NAME) values(101, 'kreddy@stacksimplify.com', 'Kalyan', 'Reddy', 'admin', 'ssn101', 'kreddy');

insert into USER (ID, EMAIL_ADDRESS, FIRST_NAME, LAST_NAME, ROLE, SSN, USER_NAME) values(102, 'gwiser@stacksimplify.com', 'Greg', 'Wiser', 'admin', 'ssn102', 'gwiser');

insert into USER (ID, EMAIL_ADDRESS, FIRST_NAME, LAST_NAME, ROLE, SSN, USER_NAME) values(103, 'dmark@stacksimplify.com', 'David', 'Mark', 'admin', 'ssn103', 'dmark');

insert into ORDERS (ID,ORDER_DESCRIPTION, USER_ID) VALUES(2001,'oder11',101);
insert into ORDERS (ID,ORDER_DESCRIPTION, USER_ID) VALUES(2002,'oder12',101);
insert into ORDERS (ID,ORDER_DESCRIPTION, USER_ID) VALUES (2003,'oder13',101);
insert into ORDERS (ID,ORDER_DESCRIPTION, USER_ID) VALUES (2004,'oder21',102);
insert into ORDERS (ID,ORDER_DESCRIPTION, USER_ID) VALUES (2005,'oder22',102);
insert into ORDERS (ID,ORDER_DESCRIPTION, USER_ID) VALUES (2006,'oder31',103);