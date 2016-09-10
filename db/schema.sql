use ElectronicsStoreDB

CREATE TABLE  Products (
  ProductID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  Name varchar(40) NOT NULL,
  UnitPrice decimal(10,2) NOT NULL,
  Description varchar(300),
  Manufacturer varchar(30) NOT NULL,
  Category varchar(20) NOT NULL,
  UnitsInStock int NOT NULL,
  Discontinued boolean NOT NULL,
  CHECK (UnitPrice>=0),
  CHECK (UnitsInStock>=0)
);

CREATE TABLE  Customers (
  CustomerID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  Email varchar(60) NOT NULL ,
  Password varchar(60) NOT NULL,
  Name varchar(30) NOT NULL,
  Surname varchar(30) NOT NULL,
  StreetName varchar(50) ,
  DoorNo smallint,
  AreaName varchar(20),
  State varchar(50),
  Country varchar(25),
  ZipCode varchar(10),
  PhoneNumber varchar(15),
  CHECK(LEN(Email)>5),
  CHECK(LEN(Password)>6)
);

CREATE TABLE  Orders (
  OrderID int AUTO_INCREMENT PRIMARY KEY,
  CustomerID int NOT NULL,
  OrderDate date NOT NULL,
  ShippingDate date NOT NULL,
  TotalPrice decimal(10,2) NOT NULL,
  FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID),
  CHECK (TotalPrice>=0),
  CHECK (ShippingDate>=OrderDate)
) ;

CREATE TABLE  OrderDetails (
  OrderDetailsID int AUTO_INCREMENT PRIMARY KEY,
  OrderID int,
  ProductID int,
  Quantity int NOT NULL,
  UnitPrice decimal(10,2) NOT NULL,
  FOREIGN KEY(OrderID) REFERENCES Orders(OrderID),
  FOREIGN KEY(ProductID) REFERENCES Products(ProductID),
  CHECK (Quantity>0),
  CHECK (UnitPrice>=0)
) ;

CREATE TABLE persistent_logins (
    Username VARCHAR(64) NOT NULL,
    Series VARCHAR(64) NOT NULL,
    Token VARCHAR(64) NOT NULL,
    LastUsed TIMESTAMP NOT NULL,
    PRIMARY KEY (series)
);