/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  erictgb
 * Created: 25 Aug 2023
 */

CREATE TABLE IF NOT EXISTS Products (
    Product_ID VARCHAR PRIMARY KEY NOT NULL,
    Name VARCHAR NOT NULL,
    Description VARCHAR NOT NULL,
    Category VARCHAR NOT NULL,
    List_Price DECIMAL(10, 2) NOT NULL,
    Quantity_In_Stock DECIMAL(10, 3) NOT NULL,
    Image BLOB,
    Quantity_Sold DECIMAL DEFAULT 0 NOT NULL
);


CREATE TABLE IF NOT EXISTS Customers (
    Customer_ID INTEGER AUTO_INCREMENT(1000),
    Username VARCHAR NOT NULL PRIMARY KEY,
    First_Name VARCHAR NOT NULL,
    Surname VARCHAR NOT NULL,
    Password VARCHAR NOT NULL,
    Email_Address VARCHAR NOT NULL,
    Shipping_Address VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS Sale (
    Sale_ID INTEGER AUTO_INCREMENT(1000),
    Customer_Username VARCHAR NOT NULL,
    Date DATE NOT NULL,
    Status VARCHAR NOT NULL,
    PRIMARY KEY (Sale_ID),
    FOREIGN KEY (Customer_Username) REFERENCES Customers(Username)
);

CREATE TABLE IF NOT EXISTS Sale_Item (
    Sale_Item_ID INTEGER AUTO_INCREMENT(1000),
    Product_ID VARCHAR NOT NULL,
    Quantity_Purchased INTEGER NOT NULL,
    Sale_Price DECIMAL(10, 2) NOT NULL,
    Sale_ID INTEGER NOT NULL,
    PRIMARY KEY (Sale_Item_ID),
    FOREIGN KEY (Product_ID) REFERENCES Products(Product_ID),
    FOREIGN KEY (Sale_ID) REFERENCES Sale(Sale_ID)
);
