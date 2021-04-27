/*create database FlightTicketBookingSystem;
*/

use FlightTicketBookingSystem;


/*
	��һ��������� TravelAgency
*/

create table TravelAgency(
	travelId int primary key,
	travelName varchar(20) not null,
	travelAddress varchar(50) not null,
)


/*
	������˿ͱ� Guest
*/

create table Guest(
	/*guestId int identity(1,1) primary key,*/
	guestId int not null primary key,
	guestName varchar(20) not null,
	guestNddress varchar(50), 
	guestIdCard nvarchar(18) not null,
	StartDate datetime not null,
	guestType varchar(10) not null,
	guestTelephone varchar(11) not null,
)



/*
	���ģ����б� city
*/

create table City(
	cityId int not null primary key,
	cityName varchar(20) not null,
)


/*
drop table Flight;
*/



/*
	����������� Flight
*/

create table Flight(
	flightId int not null primary key,
	flightStartCity int not null,
	flightEndCity int not null,
	startTime datetime not null,
	flightTime float not null,
	superprice money not null,
	generalPrice money not null,
	economicPrice money not null,
	seats int not null,
	foreign key (flightStartCity) references City(cityId),
	foreign key (flightEndCity) references City(cityId),
)



/*
	��������Ʊ�� Order


drop table OrderPapper;
*/
create table OrderPapper(
	orderId int not null primary key,
	guestId int not null,
	travelId int not null,
	producetime datetime not null,
	Numbers int,
	TotalPays money,
	foreign key (guestId) references Guest(guestId),
	foreign key (travelId) references TravelAgency(travelId),
)



/*
	���壺��Ʊ�� ticket
*/
create table Ticket(
	ticketId int not null primary key,
	flightId int not null,
	StartTime datetime not null,
	ticketType varchar(20) not null,
	discount int,
	orderId int not null,
	foreign key (flightId) references Flight(flightId),
	foreign key (orderId) references OrderPapper(orderId),
)




/*
	���ߣ�������Ϣ�� error
*/
create table error(
	errorId int not null primary key,
	errorName varchar(10) not null,
	errorContent varchar(50),
)
