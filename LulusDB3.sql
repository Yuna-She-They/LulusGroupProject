drop DATABASE lulus;
create database lulus;
use lulus;

create table foods (
	FoodId int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    FoodName varchar(60),
    Price DECIMAL(7,2),
    Category varchar(60)
    );
CREATE TABLE customer (
    CustomerID int NOT NULL AUTO_INCREMENT,
    Fname varchar(60),
    Lname varchar(60),
    Phone varchar(60),
    Email varchar(60),
    CCnumber varchar(60),

	PRIMARY KEY(CustomerID)	
);

CREATE TABLE invoice (
    InvoiceID int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	CustomerID int,
    InvoiceDate timestamp,
    FoodReady datetime  -- when the customer schedules a pick up --   
    );

CREATE TABLE food_list (
	ID BIGINT NOT NULL AUTO_INCREMENT,
    InvoiceID int references invoice(InvoiceID),
    FoodID int references foods(FoodID),
    Quantity int,
	
	Primary Key (ID)
    );

INSERT INTO customer (Fname, Lname, Phone, Email, CCnumber) 
VALUES ('Dallas', 'Wyciskalla', 3145551212, 
'd@system.com', 1234123412341234);

INSERT INTO foods (FoodName, Price, Category) 
VALUES ('Buffalo Cauliflower Bites', 5.95, Sides);




 




