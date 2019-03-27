CREATE DATABASE Lulus;

CREATE TABLE Customers
(
CustomerID INT PRIMARY KEY IDENTITY(1,1), -- Will Auto Number primary key
Email VARCHAR(50) NOT NULL,
PhoneNum INT NOT NULL,
Fname VARCHAR(50) NOT NULL,
Lname VARCHAR(50) NOT NULL,
PaymentID INT NOT NULL,
FoodID INT NOT NULL
);


INSERT INTO Customers
VALUES ('dallas@dw.com',3143143145,'Dallas','Wyciskalla',1,1),
('ben.raber89@gmail.com',6364898943,'Ben','Raber',1,1); -- place holder foods for now