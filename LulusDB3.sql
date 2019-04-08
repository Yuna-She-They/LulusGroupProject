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
    InvoiceDate timestamp,
    FoodReady datetime  -- when the customer schedules a pick up --   
    );

CREATE TABLE food_list (
    InvoiceID int references invoice(InvoiceID),
    FoodID int references foods(FoodID),
    CustomerID int references customer(CustomerID),
    Quantity int
    );

INSERT INTO customer
VALUES (1,'Dallas', 'Wyciskalla', 3145551212, 
'd@system.com', 1234123412341234);

select *
from foods;




 




