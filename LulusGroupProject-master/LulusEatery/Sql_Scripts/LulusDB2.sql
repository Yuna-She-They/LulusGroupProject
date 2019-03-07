DROP DATABASE IF EXISTS lulus;
CREATE DATABASE lulus;
USE lulus;

CREATE TABLE invoice (
    InvoiceID BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    Food varchar(60),
    Price double
	);

CREATE TABLE customer (
    CustomerID BIGINT PRIMARY KEY AUTO_INCREMENT,
    Fname varchar(60),
    Lname varchar(60),
    Phone varchar(60),
    Email varchar(60),
    CCnumber varchar(60)	
);

INSERT INTO customer (Fname, Lname, Phone, Email, CCnumber) 
VALUES ('Dallas', 'Wyciskalla', 3145551212, 
'd@system.com', 1234123412341234),
('Scott', 'Simpson', 3144920429, 'wsimpson19@my.stlcc.edu', 1234123412341234);

INSERT INTO invoice (Food, Price) 
VALUES ('Loaded Nacho Tots', '6.95' ),
('Buffalo Cauliflower Bites, Buffalo Blue Burgerx2', '19.85' );


