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
    FoodReady datetime,
	TotalPrice DECIMAL(7,2),
	ReadyForPickup boolean NOT NULL DEFAULT 0,
	PickedUp boolean NOT NULL DEFAULT 0
    );

CREATE TABLE food_list (
	ID BIGINT NOT NULL AUTO_INCREMENT,
    InvoiceID int references invoice(InvoiceID),
    FoodID int references foods(FoodID),
    Quantity int,
	
	Primary Key (ID),
	Foreign Key invoicekey (InvoiceID) REFERENCES invoice (InvoiceID)
    );
	
INSERT INTO invoice (CustomerID, FoodReady, TotalPrice)
VALUES (111,'2019-06-07 11:01:00', 44.75),
(1234,'2019-07-22 10:32:00', 16.85);

INSERT INTO food_list (InvoiceID, FoodID, Quantity)
VALUES (1, 1, 5),
(2, 1, 1),
(2, 2, 1),
(2, 3, 1);

INSERT INTO customer (Fname, Lname, Phone, Email, CCnumber) 
VALUES ('Dallas', 'Wyciskalla', 3145551212, 
'd@system.com', 1234123412341234),
('Scott', 'Simpson',3144920429,'wsimpson19@my.stlcc.edu', 1234567890123456),
('Anne', 'Westermeyer', 3145551234, 'abc@mailinator.com', 1112223334445556);

INSERT INTO foods (FoodName, Price, Category) 
VALUES ('Buffalo Cauliflower Bites', 5.95, 'Starter'),
('Kale Salad', 6.95, 'Starter'),
('Tater Tots', 3.95, 'Starter'),
('Smoked White Bean Hummus', 6.95, 'Starter'),
('Thai Crunch Salad', 8.95, 'Starter'),
('Poke Lettuce Wraps', 9.95, 'Starter'),
('BBQ Jack Tacos', 7.95, 'Burger'),
('Sweet Potato Falafel', 6.95, 'Burger'),
('Sweet Potato Black Bean Burger', 6.95, 'Burger'),
('Buffalo Blue Burger', 6.95, 'Burger'),
('Buffalo Cauliflower Wrap', 7.95, 'Burger'),
('Portabello Gyro', 8.95, 'Burger'),
('Thai Crunch Wrap', 8.95, 'Burger'),
('Kimchi Crunch Wrap', 9.95, 'Burger'),
('Lulus Tacos', 7.95, 'Tacos'),
('Banh Mi Tacos', 7.95, 'Tacos'),
('Bahn Mi Burrito', 8.95, 'Tacos'),
('Chipotle Black Bean Burrito', 8.95, 'Tacos'),
('Spring Roll Burrito', 8.95, 'Tacos'),
('Buddha Bowl', 9.95, 'Bowl'),
('Banh Mi Bowl', 9.95, 'Bowl'),
('Spring Roll Bowl', 9.95, 'Bowl'),
('Chipotle Black Bean Bowl', 9.95, 'Bowl'),
('Sushi Bowl', 9.95, 'Bowl'),
('Cauliflower Nuggets', 4.95, 'Kids'),
('Kids Taco', 2.95, 'Kids'),
('Mini Burger', 3.95, 'Kids'),
('BBQ Slider', 3.95, 'Kids');




 




