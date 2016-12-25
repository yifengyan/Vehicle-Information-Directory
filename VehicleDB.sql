create table Manufacturer (
  id int primary key,
  `name` varchar(200) NOT NULL,
  headquartersLocation varchar(2000) NOT NULL
);

create table Admin (
  id int primary key,
  name varchar(200) NOT NULL,
  email varchar(2000) NOT NULL
);

create table Vehicle (
  id int primary key,
  model varchar(200) NOT NULL,
  carBodyType ENUM('Sedan','SUV','Coupe','Motorcycle','MiniVan') NOT NULL,
  modelYear int NOT NULL,
  transmissionType ENUM('Automatic','Manual') NOT NULL,
  spare ENUM('Full','Donut','None') NOT NULL,
  maximumSeatingCapacity int NOT NULL,
  driveTrainType ENUM('FWD','RWD') NOT NULL,
  addedOrRemovedBy int NOT NULL,
	FOREIGN KEY (addedOrRemovedBy)
    REFERENCES Admin (id)
    on update cascade
    on delete no action,
  manufacturedBy int NOT NULL,
	FOREIGN KEY (manufacturedBy)
    REFERENCES Manufacturer (id)
    on update cascade
    on delete no action 
);

create table Dealer (
  id int primary key,
  `name` varchar(200) NOT NULL,
  city varchar(200) NOT NULL,
  streetAddress varchar(200) NOT NULL,
  zipCode varchar(200) NOT NULL,
  cordinates varchar(200) NOT NULL,
  franchiseDealer Boolean NOT NULL,
  contactNumber varchar(200) NOT NULL,
  addedOrRemovedBy int NOT NULL,
	FOREIGN KEY (addedOrRemovedBy)
    REFERENCES Admin (id)
    on update cascade
    on delete no action
);

create table `User` (
  id int primary key,
  `name` varchar(200) NOT NULL,
  email varchar(2000) NOT NULL,
  purchasesFrom int NOT NULL,
	FOREIGN KEY (purchasesFrom)
    REFERENCES Dealer (id)
    on update cascade
    on delete no action
);

create table Inventory (
  carries int NOT NULL,
	FOREIGN KEY (carries)
    REFERENCES Vehicle (id)
    on update cascade
    on delete cascade,
  carriedBy int NOT NULL,
	FOREIGN KEY (carriedBy)
    REFERENCES Dealer (id)
    on update cascade
    on delete cascade,
  PRIMARY KEY (carries, carriedBy),
  stock int NOT NULL
);

create table DealerRating (
  rates int NOT NULL,
	FOREIGN KEY (rates)
    REFERENCES Dealer (id)
    on update cascade
    on delete cascade,
  ratedBy int NOT NULL,
	FOREIGN KEY (ratedBy)
    REFERENCES `User` (id)
    on update cascade
    on delete cascade,
  PRIMARY KEY (rates, ratedBy),
  rating int NOT NULL
);

create table GeneralUser (
  id int PRIMARY KEY,
	FOREIGN KEY (id)
    REFERENCES `User` (id)
    on update cascade
    on delete cascade
);

create table ProspectiveBuyer (
  id int NOT NULL,
	FOREIGN KEY (id)
    REFERENCES `User` (id)
    on update cascade
    on delete cascade,
  personalNumber varchar(200) NOT NULL
);