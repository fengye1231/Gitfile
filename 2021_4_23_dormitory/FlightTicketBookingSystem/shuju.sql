/*create database FlightTicketBookingSystem;

alter table Flight add flightTime float not null;
*/


use FlightTicketBookingSystem;


INSERT INTO  City VALUES (1,'武汉');
INSERT INTO  City VALUES (2,'广州');
INSERT INTO  City VALUES (3,'香港');
INSERT INTO  City VALUES (4,'深圳');
INSERT INTO  City VALUES (5,'北京');
INSERT INTO  City VALUES (6,'张家口');
INSERT INTO  City VALUES (7,'石家庄');

INSERT INTO  City VALUES (8,'四川');





INSERT INTO  Flight VALUES (1,1,2,'2013-1-1 01:00:00.000',1.0,65.0,60.0,50.0,72);
INSERT INTO  Flight VALUES (2,1,2,'2013-1-1 02:00:00.000',1.0,65.0,60.0,50.0,72);
INSERT INTO  Flight VALUES (3,1,3,'2013-1-1 03:00:00.000',1.0,65.0,60.0,50.0,72);
INSERT INTO  Flight VALUES (4,1,4,'2013-1-1 04:00:00.000',1.0,65.0,60.0,50.0,72);
INSERT INTO  Flight VALUES (5,1,5,'2013-1-1 05:00:00.000',1.0,65.0,60.0,50.0,72);
INSERT INTO  Flight VALUES (6,1,6,'2013-1-1 06:00:00.000',1.0,65.0,60.0,50.0,72);
INSERT INTO  Flight VALUES (7,1,7,'2013-1-1 07:00:00.000',1.0,65.0,60.0,50.0,72);
INSERT INTO  Flight VALUES (8,1,8,'2013-1-1 08:00:00.000',1.0,65.0,60.0,50.0,72);





INSERT INTO  Guest VALUES (1,'黎明','河北省保定市','130634198901161913','2013-1-1 00:30:00.000','学生','15226506882');
INSERT INTO  Guest VALUES (2,'张红','河北省保定市','130634198901161914','2013-1-1 00:30:00.000','学生','15226506883');
INSERT INTO  Guest VALUES (3,'张江','河北省保定市','130634198901161915','2013-1-1 00:30:00.000','学生','15226506884');



INSERT INTO  TravelAgency VALUES(1,'北京旅行社','北京市密云县');
INSERT INTO  TravelAgency VALUES(2,'河北旅行社','河北省保定市');
INSERT INTO  TravelAgency VALUES(3,'天津旅行社','天津协和区');




INSERT INTO  OrderPapper VALUES(1,1,1,'2013-1-1 00:45:00.000',4536,80.0);
INSERT INTO  OrderPapper VALUES(2,2,2,'2013-1-1 00:45:00.000',3369,80.0);
INSERT INTO  OrderPapper VALUES(3,3,3,'2013-1-1 00:45:00.000',3859,80.0);



INSERT INTO Ticket VALUES(1,1,'2013-1-1 00:30:00.000','Super',1,1);
INSERT INTO Ticket VALUES(2,2,'2013-1-1 00:30:00.000','General',1,2);
INSERT INTO Ticket VALUES(3,3,'2013-1-1 00:30:00.000','Economic',1,3);
